package com.example.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenderStatistic {
    private List<User> userList;

    public GenderStatistic(List<User> userList) {
        this.userList = userList;
    }

    public Map<String,Integer> getRate(){
        Map<String,Integer> rate = new HashMap<>();
        int maleCount = 0,femaleCount = 0;
        int overall = 0;
        for(int i = 0; i<userList.size();i++){
            if(userList.get(i).getGender()!=null) {
                overall = overall + 1;
            }
        }
        for(User user :userList){
            if(user.getGender()!=null){
                if(user.getGender().equalsIgnoreCase("male")) maleCount++;
                else if(user.getGender().equalsIgnoreCase("female")) femaleCount++;
            }
        }

            int maleRate = (int) (((float) maleCount / (float) overall) * 100);
            rate.put("male",maleRate);

            int femaleRate = (int) (((float) femaleCount / (float) overall) * 100);
            rate.put("female",femaleRate);




        return rate;
    }
}