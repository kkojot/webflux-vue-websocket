package com.kojotdev.blog.webfluxvuewebsocket

import spock.lang.Specification

class GreetingsServiceTest extends Specification {

    def "JSON greeting from JSON hello message should be correct"() {
        given: "json message from client"
        def helloMessage = "{\"name\": \"kojot\"}"
        when: "creating greetings"
        def greeting = new GreetingsService().greeting(helloMessage)
        then: "greetings should be correct JSON"
        greeting == "{\"content\":\"Hello, kojot!\"}"
    }

    def "invalid hello message JSON should return empty String"() {
        given: "invalid json message from client"
        def helloMessage = "{\"invalid\": \"json\"}"
        when: "creating greetings"
        def greeting = new GreetingsService().greeting(helloMessage)
        then: "greetings should be empty String"
        greeting == ""
    }
}
