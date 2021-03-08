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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class PollController {

    ExecutorService threadExecutor = Executors.newCachedThreadPool();

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
    public String pollPage(@PathVariable("id") long id, Model model) throws ExecutionException, InterruptedException {
        Poll poll = pollRepository.findPollById(id);
        List<User> users =userRepository.findAll();
        Future<Poll> futureCall = threadExecutor.submit(new PollOptionRateSetter(poll));
        Poll result =  futureCall.get();
        int x = (int) (((float)poll.getVotedUsers().size()/(float)users.size())*100);
        model.addAttribute("statistics",String.valueOf(x));
        model.addAttribute("poll",result);
        return "poll";

    }

    @GetMapping("/editPage/{id}")
    public String editPage(@PathVariable("id") long id, Model model) throws ExecutionException, InterruptedException {
        Poll poll = pollRepository.findPollById(id);
        Future<Poll> futureCall = threadExecutor.submit(new PollOptionRateSetter(poll));
        Poll result =  futureCall.get();
        model.addAttribute("poll",result);
        return "editPoll";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePoll(@PathVariable("id") long id) throws ExecutionException, InterruptedException {
        Poll poll = pollRepository.findPollById(id);
        poll.setVotedUsers(null);
        poll.setAuthor(null);
        pollRepository.delete(poll);
        return new ModelAndView("redirect:/mainPage");
    }


    @GetMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") long id,
                       @RequestParam(name = "title") String title,
                       @RequestParam(name = "optionList") List<String> optionList,
                        Model model) throws ExecutionException, InterruptedException {
        System.out.println(optionList + " " + title);
        Poll poll = pollRepository.findPollById(id);
        poll.setPollTitle(title);
        if(title!=null && !title.equals("")) {
            poll.setOptions(DefaultOptionSetter.getDefaultOption(optionList));
            poll = pollRepository.save(poll);
            if(poll!=null) {
                return "Edited";
            }
        }
        return "Invalid input";
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

