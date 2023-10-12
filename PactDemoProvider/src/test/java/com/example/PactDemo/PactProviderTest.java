package com.example.PactDemo;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("TopicProvider")
@PactFolder("src/main/java/pacts")
@Consumer("TopicConsumer")
public class PactProviderTest {

    @LocalServerPort
    public int port;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context){
        context.verifyInteraction();
    }

    @BeforeEach
    public void setup(PactVerificationContext context){
        System.out.println(port);
        context.setTarget(new HttpTestTarget("localhost",port));
    }

    @State(value="topics exist", action = StateChangeAction.SETUP)
    public void topicsExist(){
        System.out.println(port);
    }

    @State(value = "topics exist", action = StateChangeAction.TEARDOWN)
    public void topicsExistTearDown(){

    }
}
