package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IOptionRepository;
import com.example.demo.repository.IPollRepository;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PollController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPollRepository pollRepository;

    @Autowired
    private IOptionRepository optionRepository;

    @GetMapping("/writePoll")
    public String writePoll(){
        return "createPoll";
    }

    @GetMapping("/getPollById/{id}")
    @ResponseBody
    public Poll getPollById(@PathVariable("id") long id){
        Poll poll = pollRepository.findPollById(id);
        return poll;
    }

    @GetMapping("/poll/{id}")
    public String pollPage(@PathVariable("id") long id, Model model){
        Poll poll = pollRepository.findPollById(id);
        long overallRate = 0;
        for(int i = 0; i < poll.getOptions().size(); i++){
            overallRate = overallRate + poll.getOptions().get(i).getRate();
        }
        for(int i = 0; i < poll.getOptions().size(); i++){
            long rate = poll.getOptions().get(i).getRate();
            float percentRate = (float) (((float) rate / (float)overallRate) * 100.0);
            poll.getOptions().get(i).setRate((long) percentRate);
        }
        model.addAttribute("poll",poll);
        return "poll";
    }

    @GetMapping("/polls")
    @ResponseBody
    public List<Poll> getPolls(){
        List<Poll> polls = pollRepository.findAll();
        return polls;
    }

    @GetMapping("/vote/{p_id}/{o_id}")
    @ResponseBody
    public String vote(@PathVariable("p_id") long p_id,@PathVariable("o_id") long o_id,
                       Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByUsername(userPrincipal.getUsername());
        Option option = optionRepository.findOptionById(o_id);
        Poll poll = pollRepository.findPollById(p_id);
        if(poll.getVotedUsers().contains(user)){
            return "You have already voted";
        }
        List<User> users = poll.getVotedUsers();
        users.add(user);
        poll.setVotedUsers(users);
        option.setRate(option.getRate() + 1);
        poll = pollRepository.save(poll);
        option = optionRepository.save(option);
        if(poll!=null && option!=null){
            return "Voted";
        }
        return null;
    }

    @GetMapping("/createPoll")
    @ResponseBody
    public Poll createPoll(@RequestParam(name = "title") String title,
                             @RequestParam(name = "optionList") List<String> optionList,
                             Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByUsername(userPrincipal.getUsername());
        List<Option> options = DefaultOptionSetter.getDefaultOption(optionList);
        Poll poll = new Poll(title,options,user);
        poll = pollRepository.save(poll);
        return poll;
    }

}

