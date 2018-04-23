package com.daria.university.diploma.security;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

public class AuthChannelInterceptorAdapter extends ChannelInterceptorAdapter {
    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "passcode";

    private final WebSocketAuthenticationService webSocketAuthenticationService;

    public AuthChannelInterceptorAdapter(final WebSocketAuthenticationService webSocketAuthenticationService) {
        this.webSocketAuthenticationService = webSocketAuthenticationService;
    }

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            final String username = accessor.getFirstNativeHeader(USERNAME_HEADER);
            final String password = accessor.getFirstNativeHeader(PASSWORD_HEADER);

            final UsernamePasswordAuthenticationToken user =
                    webSocketAuthenticationService.getAuthenticatedOrFail(username, password);

            accessor.setUser(user);
        }
        return message;
    }
}
