package nl.ijmker.examples.messaging.receceive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceiver {

    private final MessageSubscription subscription;

    public MessageReceiver(@Qualifier("baseSubscription") MessageSubscription subscription) {
        this.subscription = subscription;
    }

    public void receiveMessage(String message) {
        log.info("Receiving  payload '{}' using routing key '{}'", message, subscription.getRoutingKey());
    }
}
