package com.czapi.czapiinterface.controller;


import com.cz.czapiclientsdk.model.Sentence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 毒鸡汤api
 * @author cz
 */
@RestController
@RequestMapping("/sentence")
public class SentenceController {
    @GetMapping("/get")
    public String getSentence(Sentence sentence){
        String result = "今日份毒鸡汤："+sentence.getSentence();
        return result;
    }
}
