package com.example.configs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import DTO.EmployeeDto;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;

//import com.example.services.RabbitConsumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;

@Configuration
public class ListenerConfig {
	final static String queueName = "spring-boot";
	final static String queueName2 = "summer-boot";


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
		Queue x = new Queue(queueName, false);
		x.addArgument("x-max-length", 5);
//		x.addArgument("x-overflow", "reject-publish");
		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
		return x;
	}

	@Bean(name = "secondQueue")
	Queue queue2() {
		Queue x = new Queue(queueName2, false);
		x.addArgument("x-max-length", 5);
//		x.addArgument("x-overflow", "reject-publish");
		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
		return x;
	}

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setUsername("guest");
		return cachingConnectionFactory;
	}

	// create MessageListenerContainer using custom connection factory assigns a
	// class to handle messages
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
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrentConsumers(1); // Set the desired number of consumer threads
		factory.setMaxConcurrentConsumers(2); // Set the maximum number of consumer threads
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
		MessageConverter converter = jsonMessageConverter();
		List<EmployeeDto> x = (List<EmployeeDto>) converter.fromMessage(message);
		System.out.println(x.get(0));
		System.out.println("Listener Side (spring queue listener) Message was recieved: "
				+ new String(message.getBody()) + "   " + message.getMessageProperties().getConsumerQueue() + "\n\n");
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
		System.out.println("Listener Side (summer queue listener) Message was recieved: "
				+ new String(message.getBody()) + "   " + message.getMessageProperties().getConsumerQueue() + "\n\n");
	}

}
