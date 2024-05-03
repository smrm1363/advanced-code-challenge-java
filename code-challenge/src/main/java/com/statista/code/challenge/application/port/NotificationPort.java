package com.statista.code.challenge.application.port;

public interface NotificationPort {

  void send(String context, String notificationContact);
}
