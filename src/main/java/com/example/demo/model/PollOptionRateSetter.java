package com.example.demo.model;

import java.util.concurrent.Callable;

public class PollOptionRateSetter implements Callable<Poll> {
    private Poll poll;

    public PollOptionRateSetter(Poll poll) {
        this.poll = poll;
    }

    @Override
    public Poll call() throws Exception {
        long overallRate = 0;
        for(int i = 0; i < poll.getOptions().size(); i++){
            overallRate = overallRate + poll.getOptions().get(i).getRate();
        }
        for(int i = 0; i < poll.getOptions().size(); i++){
            long rate = poll.getOptions().get(i).getRate();
            float percentRate = (float) (((float) rate / (float)overallRate) * 100.0);
            poll.getOptions().get(i).setRate((long) percentRate);
        }
        return poll;
    }
}
