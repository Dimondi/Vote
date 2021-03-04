package com.example.demo.model;

import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailChecker implements Callable<Boolean> {
    private String email;
    private static final String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    public EmailChecker(String email){
        this.email = email;
    }

    @Override
    public Boolean call() throws Exception {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
