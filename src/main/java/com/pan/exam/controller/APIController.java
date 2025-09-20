package com.pan.exam.controller;

import com.pan.exam.processor.CoreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class APIController {
    @Autowired
    private CoreProcessor coreProcessor;

    @Value("${char.maxLength}")
    private int maxLength;

    @Value("${char.regex}")
    private String regex;

    @RequestMapping(value = "/get-new-string", method = {RequestMethod.GET})
    public String getNewString(@RequestParam(required = true) String input, @RequestParam(required = true) int mode) throws Exception{
        Pattern pattern = Pattern.compile(regex);
        if(input == null || !pattern.matcher(input).matches() || input.length() > maxLength){
            //Here only for simple testing without customized exception and error code
            throw new IllegalArgumentException("Invalid input parameter : Non empty lowercase letters and length less then " + maxLength);
        }
        if(!coreProcessor.MODE_MAPPING.containsKey(mode)){
            throw new IllegalArgumentException("Invalid mode parameter ：[1->Delete consecutive char; 2->Replace consecutive char]");
        }
        return coreProcessor.execute(input,mode);
    }
}
