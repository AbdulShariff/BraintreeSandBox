package com.sandbox.bdd.stepDefs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.sandbox.bdd.stepDefs.config.EndPointsInfo;


//@ContextConfiguration(classes=TestManagerConfig.class)

public class BaseStepDef {
    @Bean
    public RestTemplate getRestTemplate(){
    return new RestTemplate();
}

    @Bean
    public EndPointsInfo getBraintreeInfo(){
        return new EndPointsInfo();
    }

}



