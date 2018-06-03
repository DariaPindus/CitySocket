package com.daria.university.diploma.client;

import com.daria.university.diploma.model.messaging.output.SensorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ServiceClient {
    public static void main(String... argv) throws ExecutionException, InterruptedException, JsonProcessingException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        String url = "ws://127.0.0.1:8181/input";
        StompSessionHandler sessionHandler = new MySessionHandler();
        StompSession session = stompClient.connect(url, sessionHandler).get();

        //session.send("/app/chat", "{\"name\":\"Client\"}".getBytes());
        //new Scanner(System.in).nextLine(); //Don't close immediately.
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();

        while (true) {

            System.out.print("Enter your value : ");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }

            session.send("/app/output", mapper.writeValueAsBytes(new SensorMessage("test", 0L, input)));
        }

        scanner.close();
    }
}
