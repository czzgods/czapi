package com.czapi.czapiinterface.controller;
import org.springframework.web.bind.annotation.*;
import com.cz.czapiclientsdk.model.User;
import javax.servlet.http.HttpServletRequest;

/**
 * 名称 api
 * @author 李钟意
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet(String name,HttpServletRequest request){
        System.out.println(request.getHeader("cz"));
        return "GET 你的名字是:"+name;
    }
    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){
        return "POST 你的名字是:"+name;
    }
    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){
       /* String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        if(!accessKey.equals("cz") ){
            throw new RuntimeException("无权限");
        }
        if(Long.parseLong(nonce)>10000){
            throw new RuntimeException("无权限");
        }
        String serverSign = SignUtils.getSign(body, "qnmd985211");
        if(!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }*/
        String result = "POST 用户名是:"+user.getUsername();
        //调用成功后，次数+1
        return result;
    }
}
