package nl.ijmker.examples.messaging.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static nl.ijmker.examples.messaging.MessageApplication.EXCHANGE_NAME;

@Slf4j
@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> send(@Valid @RequestBody MessageRequest request) {
        log.info("Sending  payload '{}' using routing key '{}'", request.getPayload(), request.getRoutingKey());
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, request.getRoutingKey(), request.getPayload());
        return ResponseEntity.ok(MessageResponse.builder()
                .routingKey(request.getRoutingKey())
                .payload(request.getPayload())
                .status(0)
                .build());
    }

    @GetMapping("/count")
    public Integer queueLength() {
        return 1;
    }
}
