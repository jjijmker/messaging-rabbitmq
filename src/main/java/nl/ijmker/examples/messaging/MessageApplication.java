package nl.ijmker.examples.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageApplication {

    public static final String EXCHANGE_NAME = "messaging-rabbitmq-exchange";
    public static final String QUEUE_NAME_KEY_BASE = "messaging-rabbitmq-queue-#";
    public static final String QUEUE_NAME_KEY_1 = "messaging-rabbitmq-queue-baz";
    public static final String QUEUE_NAME_KEY_2 = "messaging-rabbitmq-queue-boz";
    public static final String ROUTING_KEY_BASE = "foo.bar.#";
    public static final String ROUTING_KEY_1 = "foo.bar.baz";
    public static final String ROUTING_KEY_2 = "foo.bar.boz";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
