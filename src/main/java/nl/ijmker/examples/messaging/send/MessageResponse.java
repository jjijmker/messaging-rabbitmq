package nl.ijmker.examples.messaging.send;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private String routingKey;
    private String payload;
    private int status;
}
