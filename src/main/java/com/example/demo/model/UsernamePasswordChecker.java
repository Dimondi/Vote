package com.example.demo.model;
import java.util.concurrent.Callable;


public class UsernamePasswordChecker implements Callable<Boolean> {
    private String username;
    private String password;

    public UsernamePasswordChecker(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Boolean call() throws Exception {
        if(username.length()>=4) {
            if (password.matches(".*[0-9]{1,}.*") && password.matches(".*[@#$]{1,}.*") && password.length() >= 6 && password.length() <= 20) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
