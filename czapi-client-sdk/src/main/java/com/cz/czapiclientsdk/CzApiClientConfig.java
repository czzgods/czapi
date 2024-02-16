package com.cz.czapiclientsdk;

import com.cz.czapiclientsdk.client.CzApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("czapi.client")
@Data
@ComponentScan
public class CzApiClientConfig {
    private String accessKey;
    private String secretKey;
    @Bean
    public CzApiClient czApiClient(){
        return new CzApiClient(accessKey,secretKey);
    }
}
