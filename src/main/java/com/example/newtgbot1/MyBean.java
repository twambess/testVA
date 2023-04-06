package com.example.newtgbot1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public static String arg1Value;
    public static long arg2Value;

    @Autowired
    public MyBean(ApplicationArguments args){
         arg2Value= Long.parseLong(args.getNonOptionArgs().get(1));
         arg1Value=args.getNonOptionArgs().get(0);
    }
}
