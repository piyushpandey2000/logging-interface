package com.project.logginginterface.websocket;

import com.project.logginginterface.log.LogFetcher;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/logpub")
public class LogPublisher {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("socket open");

        LogFetcher logFetcher = new LogFetcher();
        logFetcher.init("/Users/piyush.pandey/IdeaProjects/GitHub/logging-interface/log.out", session);
        logFetcher.run();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("socket close");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error occurred");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("message received");
    }
}
