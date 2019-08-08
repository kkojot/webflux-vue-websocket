package com.kojotdev.blog.webfluxvuewebsocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final GreetingsService greetingsService = new GreetingsService();

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        final Flux<WebSocketMessage> message = webSocketSession
                .receive()
                .map(webSocketMessage -> webSocketMessage.getPayloadAsText())
                .map(helloMessage -> greetingsService.greeting(helloMessage))
                .map(greetings -> webSocketSession.textMessage(greetings));

        return webSocketSession.send(message);
    }
}
