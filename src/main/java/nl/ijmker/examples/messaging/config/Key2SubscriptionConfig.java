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

import static nl.ijmker.examples.messaging.MessageApplication.QUEUE_NAME_KEY_2;
import static nl.ijmker.examples.messaging.MessageApplication.ROUTING_KEY_2;

@Configuration
public class Key2SubscriptionConfig {

    @Bean
    MessageSubscription key2Subscription() {
        return MessageSubscription.builder()
                .routingKey(ROUTING_KEY_2)
                .build();
    }

    @Bean
    Queue key2Queue() {
        return new Queue(QUEUE_NAME_KEY_2, false);
    }

    @Bean
    Binding key2Binding(@Qualifier("key2Queue") Queue key2Queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(key2Queue)
                .to(exchange)
                .with(ROUTING_KEY_2);
    }

    @Bean
    SimpleMessageListenerContainer key2Listener(ConnectionFactory connectionFactory,
                                                @Qualifier("key2Subscription") MessageSubscription key2Subscription) {
        SimpleMessageListenerContainer key2Listener = new SimpleMessageListenerContainer();
        key2Listener.setConnectionFactory(connectionFactory);
        key2Listener.setQueueNames(QUEUE_NAME_KEY_2);
        MessageReceiver key2Receiver = new MessageReceiver(key2Subscription);
        MessageListenerAdapter key2Adapter = new MessageListenerAdapter(key2Receiver, "receiveMessage");
        key2Listener.setMessageListener(key2Adapter);
        return key2Listener;
    }
}
