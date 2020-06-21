package nl.ijmker.examples.messaging.send;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class MessageRequest {
    @NotEmpty(message="routingKey cannot be empty")
    private String routingKey;

    @NotEmpty(message="payload cannot be empty")
    private String payload;
}
