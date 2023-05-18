package com.example.services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
//public class RabbitConsumer {
//	@RabbitListener(queues = "spring-boot")
//	public void received(String msg) {
//		System.out.println("WORKED!!!  ->   " + msg);
//	}
@Service
public class RabbitConsumer implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("Message was recieved: " + new String(message.getBody()));
	}
	
}