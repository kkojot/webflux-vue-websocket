package com.kojotdev.blog.webfluxvuewebsocket

import spock.lang.Specification

class GreetingTest extends Specification {

    def "Greeting from HelloMessage should be correct"() {
        given: "simple HelloMessage"
        def helloMessage = new HelloMessage("kojot")
        when: "creating greetings from HelloMessage"
        def greeting = Greeting.from(helloMessage)
        then: "greeting should say hello with name!"
        greeting.content == "Hello, kojot!"
    }
}
