package com.daria.university.diploma.client;

import com.daria.university.diploma.model.messaging.output.SensorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@Slf4j
public class MySessionHandler extends StompSessionHandlerAdapter {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        //session.subscribe("/topic/greetings", this);
        session.subscribe("/sensors/greetings", this);
        //session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());
        try {
            session.send("/app/output", mapper.writeValueAsBytes(new SensorMessage("test", 0L, "sound",  "11")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("New session: {}" + session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return SensorMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received: {}" +  ((SensorMessage) payload));
    }
}
