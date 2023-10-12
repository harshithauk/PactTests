package com.example.PactDemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {
    String baseUrl;

    public void setBaseUrl(String url){
        this.baseUrl = url;
    }
    public Topic getTopic() throws JsonProcessingException {
        return new RestTemplate().getForObject(baseUrl + "/topics/1", Topic.class);
    }
}
