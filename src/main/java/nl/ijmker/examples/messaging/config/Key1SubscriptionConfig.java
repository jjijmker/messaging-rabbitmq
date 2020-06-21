package nl.ijmker.examples.messaging.config;

import nl.ijmker.examples.messaging.receceive.MessageReceiver;
import nl.ijmker.examples.messaging.receceive.MessageSubscription;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static nl.ijmker.examples.messaging.MessageApplication.QUEUE_NAME_KEY_1;
import static nl.ijmker.examples.messaging.MessageApplication.ROUTING_KEY_1;

@Configuration
public class Key1SubscriptionConfig {

    @Bean
    MessageSubscription key1Subscription() {
        return MessageSubscription.builder()
                .routingKey(ROUTING_KEY_1)
                .build();
    }

    @Bean
    Queue key1Queue() {
        return new Queue(QUEUE_NAME_KEY_1, false);
    }

    @Bean
    Binding key1Binding(@Qualifier("key1Queue") Queue key1Queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(key1Queue)
                .to(exchange)
                .with(ROUTING_KEY_1);
    }

    @Bean
    SimpleMessageListenerContainer key1Listener(ConnectionFactory connectionFactory,
                                                @Qualifier("key1Subscription") MessageSubscription key1Subscription) {
        SimpleMessageListenerContainer key1Listener = new SimpleMessageListenerContainer();
        key1Listener.setConnectionFactory(connectionFactory);
        key1Listener.setQueueNames(QUEUE_NAME_KEY_1);
        MessageReceiver key1Receiver = new MessageReceiver(key1Subscription);
        MessageListenerAdapter key1Adapter = new MessageListenerAdapter(key1Receiver, "receiveMessage");
        key1Listener.setMessageListener(key1Adapter);
        return key1Listener;
    }
}
