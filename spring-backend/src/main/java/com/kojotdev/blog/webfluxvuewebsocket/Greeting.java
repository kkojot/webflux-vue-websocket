package com.kojotdev.blog.webfluxvuewebsocket;

public class Greeting {

    public final String content;

    public Greeting(String content) {
        this.content = content;
    }

    public static Greeting from(HelloMessage helloMessage) {
        return new Greeting("Hello, " + helloMessage.name + "!");
    }
}
