package com.example.PactDemo;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import au.com.dius.pact.core.model.annotations.Pact;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "TopicProvider")
public class PactConsumerTest {

    @Autowired
    private ConsumerService consumerService;

    @Pact(consumer = "TopicConsumer", provider = "TopicProvider")
    public RequestResponsePact TopicDetailsConfig(PactDslWithProvider builder) {
        PactDslJsonBody body = new PactDslJsonBody()
                .integerType("id", 1)
                .stringType("name", "Web");

        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/json");

        return builder.given("topics exist")
                .uponReceiving("getting a specific course details")
                .path("/topics/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "TopicDetailsConfig", port = "9999")
    public void testGetTopicFromProvider(MockServer mockServer) throws JsonProcessingException {
        String expectedJson = "{\"id\":1,\"name\":\"Web\"}";
        consumerService.setBaseUrl(mockServer.getUrl());


        Topic topic = consumerService.getTopic();
        ObjectMapper obj = new ObjectMapper();
        String actualJson = obj.writeValueAsString(topic);

        assertEquals(expectedJson, actualJson);
    }
}
