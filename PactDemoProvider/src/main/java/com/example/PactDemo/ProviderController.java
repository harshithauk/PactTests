package com.example.PactDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

//    @GetMapping("/topics")
//    public List<Topic> getAllTopics(){
//        return providerService.getAllTopics();
//    }

    @GetMapping("/topics/{id}")
    public Topic getTopic(@PathVariable(value="id") int id){
        return providerService.getTopic(id);
    }
}
