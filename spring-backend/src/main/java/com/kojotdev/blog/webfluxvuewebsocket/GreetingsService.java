package com.kojotdev.blog.webfluxvuewebsocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingsService {

    private static final Logger log = LoggerFactory.getLogger(GreetingsService.class);

    private ObjectMapper jsonMapper = new ObjectMapper();

    public String greeting(String message) {
        return Try.of(() -> {
//            Thread.sleep(1000); //simulate delay
            final HelloMessage helloMessage = jsonMapper.readValue(message, HelloMessage.class);
            final Greeting greeting = Greeting.from(helloMessage);
            return jsonMapper.writeValueAsString(greeting);
        })
                .onFailure(parserException -> log.error("Could not parse JSON object", parserException))
                .getOrElse("");
    }

}
