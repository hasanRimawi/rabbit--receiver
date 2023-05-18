package com.example.configs;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.services.RabbitConsumer;
import org.springframework.amqp.core.Queue;


@Configuration
public class ListenerConfig {
//	 @Bean
//	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	      MessageListenerAdapter listenerAdapter) {
//	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	    container.setConnectionFactory(connectionFactory);
//	    container.setQueueNames("spring-boot");
//	    container.setMessageListener(listenerAdapter);
//	    return container;
//	  }
//
//	  @Bean
//	  MessageListenerAdapter listenerAdapter(RabbitConsumer receiver) {
//	    return new MessageListenerAdapter(receiver, "receiveMessage");
//	  }
	@Bean
	Queue queue() {
		return new Queue("spring-boot", false);
	}
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setUsername("guest");
		return cachingConnectionFactory;
	}  
	
    //create MessageListenerContainer using custom connection factory
	@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitConsumer());
		return simpleMessageListenerContainer;

	}
}
