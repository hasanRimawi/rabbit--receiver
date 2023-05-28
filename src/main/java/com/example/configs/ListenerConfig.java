package com.example.configs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//import com.example.services.RabbitConsumer;

import org.springframework.amqp.core.Message;
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
	@Bean(name = "firstQueue")
	Queue queue() {
		return new Queue("spring-boot", false);
	}

	@Bean(name = "secondQueue")
	Queue queue2() {
		return new Queue("summer-boot", false);
	}

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setUsername("guest");
		return cachingConnectionFactory;
	}

	// create MessageListenerContainer using custom connection factory assigns a class to handle messages
//	@Bean
//	MessageListenerContainer messageListenerContainer() {
//		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
//		simpleMessageListenerContainer.setQueues(queue(), queue2());
//		simpleMessageListenerContainer.setMessageListener(new RabbitConsumer());
//		return simpleMessageListenerContainer;
//
//	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrentConsumers(5); // Set the desired number of consumer threads
		factory.setMaxConcurrentConsumers(10); // Set the maximum number of consumer threads
		return factory;
	}

	@RabbitListener(queues = "spring-boot")
	public void receiveMessage(Message message) {
		try {
			Thread.sleep(4 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("retrieved from queue ONE -- " + Thread.currentThread().getId());
		System.out.println("Listener Side (spring queue listener) Message was recieved: " + new String(message.getBody()) + "   "
				+ message.getMessageProperties().getConsumerQueue() + "\n\n");
//		System.out.println("Message is: " + message.getBody());
	}
	@RabbitListener(queues = "summer-boot")
	public void receiveMessageQ2(Message message) {
		try {
			Thread.sleep(4 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Listener Side (summer queue listener) Message was recieved: " + new String(message.getBody()) + "   "
				+ message.getMessageProperties().getConsumerQueue() + "\n\n");
	}

}
