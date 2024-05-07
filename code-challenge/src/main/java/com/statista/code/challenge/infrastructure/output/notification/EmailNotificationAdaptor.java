package com.statista.code.challenge.infrastructure.output.notification;

import com.statista.code.challenge.application.port.NotificationPort;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationAdaptor implements NotificationPort {

  @Override
  public void send(String context, @Email String emailAddress) {
    log.info(String.format("An email is being send to the addess : %s The email context : %s",
        emailAddress, context
    ));
  }
}
