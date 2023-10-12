package com.example.PactDemo;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProviderService {

//    private List<Topic> topics = Arrays.asList(
//            new Topic(1, "a"),
//            new Topic(2, "b"),
//            new Topic(3, "c")
//    );

//    public List<Topic> getAllTopics() {
//        return topics;
//    }

    public Topic getTopic(int id) {
        return new Topic(id, "topic1");
    }
}
