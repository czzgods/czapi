package com.czapi.czapiinterface;

import com.cz.czapiclientsdk.client.CzApiClient;
import com.cz.czapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CzapiInterfaceApplicationTests {

    @Resource
    private CzApiClient czApiClient;
    @Test
    void contextLoads() {
        String cz = czApiClient.getNameByGet("cz");
        User user = new User();
        user.setUsername("cz");
        String username = czApiClient.getUsernameByPost(user);
        System.out.println(cz);
        System.out.println(username);
    }

}
