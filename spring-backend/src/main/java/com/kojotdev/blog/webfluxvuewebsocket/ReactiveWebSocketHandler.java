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
    private final GreetingsPublisher greetingsPublisher;
    private final Flux<String> publisher;

    public ReactiveWebSocketHandler(GreetingsPublisher greetingsPublisher) {
        this.greetingsPublisher = greetingsPublisher;
        this.publisher = Flux.create(greetingsPublisher).share();
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        webSocketSession
                .receive()
                .map(webSocketMessage -> webSocketMessage.getPayloadAsText())
                .map(helloMessage -> greetingsService.greeting(helloMessage))
                .doOnNext(greetings -> greetingsPublisher.push(greetings))
                .subscribe();

        final Flux<WebSocketMessage> message = publisher
                .map(greetings -> webSocketSession.textMessage(greetings));

        return webSocketSession.send(message);
    }
}
