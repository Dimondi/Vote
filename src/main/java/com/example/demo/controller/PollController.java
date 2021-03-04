package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IPollRepository;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
public class PollController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPollRepository pollRepository;

    @GetMapping("/writePoll")
    public String poll(){
        return "createPoll";
    }

    @GetMapping("/poll/{id}")
    @ResponseBody
    public Poll getPollById(@PathVariable("id") long id){
        Poll poll = pollRepository.findPollById(id);
        return poll;
    }

    @GetMapping("/poll")
    @ResponseBody
    public List<Poll> getPolls(){
        List<Poll> polls = pollRepository.findAll();
        return polls;
    }

    @GetMapping("/vote/{id}")
    @ResponseBody
    public String vote(@PathVariable("id") long id){
        Poll poll = pollRepository.findPollById(id);
        for(int i = 0; i < poll.getOptions().size(); i ++){
            if(poll.getOptions().get(i).getId()==id){
                long newRate = poll.getOptions().get(i).getRate()+1;
                poll.getOptions().get(i).setRate(newRate);
            }
        }
        return "";
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

