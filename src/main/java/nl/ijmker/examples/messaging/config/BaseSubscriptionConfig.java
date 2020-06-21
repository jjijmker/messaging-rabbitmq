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

import static nl.ijmker.examples.messaging.MessageApplication.QUEUE_NAME_KEY_BASE;
import static nl.ijmker.examples.messaging.MessageApplication.ROUTING_KEY_BASE;

@Configuration
public class BaseSubscriptionConfig {

    @Bean
    MessageSubscription baseSubscription() {
        return MessageSubscription.builder()
                .routingKey(ROUTING_KEY_BASE)
                .build();
    }

    @Bean
    Queue keyBaseQueue() {
        return new Queue(QUEUE_NAME_KEY_BASE, false);
    }

    @Bean
    Binding keyBaseBinding(@Qualifier("keyBaseQueue") Queue keyBaseQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(keyBaseQueue)
                .to(exchange)
                .with(ROUTING_KEY_BASE);
    }

    @Bean
    SimpleMessageListenerContainer baseListener(ConnectionFactory connectionFactory,
                                                @Qualifier("baseSubscription") MessageSubscription baseSubscription) {
        SimpleMessageListenerContainer baseListener = new SimpleMessageListenerContainer();
        baseListener.setConnectionFactory(connectionFactory);
        baseListener.setQueueNames(QUEUE_NAME_KEY_BASE);
        MessageReceiver baseReceiver = new MessageReceiver(baseSubscription);
        MessageListenerAdapter baseAdapter = new MessageListenerAdapter(baseReceiver, "receiveMessage");
        baseListener.setMessageListener(baseAdapter);
        return baseListener;
    }
}
