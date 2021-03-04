package com.example.demo.model;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.util.*;

public class DefaultOptionSetter {
    public static List<Option> getDefaultOption(List<String> optionList){
        List<Option> options = new ArrayList<>();
        for(int i = 0;i<optionList.size();i++){
            options.add(new Option(optionList.get(i)));
        }
        return options;
    }
}
