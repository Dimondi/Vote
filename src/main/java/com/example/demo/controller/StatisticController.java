package com.example.demo.controller;

import com.example.demo.model.Poll;
import com.example.demo.repository.IPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticController {

    @Autowired
    private IPollRepository pollRepository;

    @GetMapping("/latest")
    @ResponseBody
    public List<Poll> getLatestPolls(){
        List<Poll> polls = pollRepository.findAll();
        List<Poll> latest = new ArrayList<>();
        int size = polls.size();
        Poll temp = null;
        int count = 5;
        if(polls.size()>5){
            while(count!=0){
                latest.add(polls.get(size-1));
                count--;
                size -- ;
            }
        }else{
            latest.add(polls.get(polls.size()-1));
        }

        return latest;
    }

    @GetMapping("/popular")
    @ResponseBody
    public List<Poll> getPopularPools(){
        List<Poll> polls = pollRepository.findAll();
        List<Poll> popular = new ArrayList<>();
        int max = polls.get(0).getVotedUsers().size();
        Poll temp = null;
        if(polls.size()>5) {
            for(int i=0; i<5; i++) {
                for (Poll poll : polls) {
                    if (poll.getVotedUsers().size() > max) {
                        max = poll.getVotedUsers().size();
                        popular.add(poll);
                        temp = poll;
                    }
                }
                max = polls.get(0).getVotedUsers().size();
                polls.remove(temp);
            }
        }else{
            for (Poll poll : polls) {
                if (poll.getVotedUsers().size() >= max) {
                    max = poll.getVotedUsers().size();
                    popular.add(poll);
                    temp = poll;
                }
            }
        }
        return popular;
    }

}
