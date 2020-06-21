package nl.ijmker.examples.messaging.receceive;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageSubscription {
    private String routingKey;
}
