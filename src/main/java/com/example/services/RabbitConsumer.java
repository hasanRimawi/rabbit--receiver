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
//@Service
//public class RabbitConsumer implements MessageListener{
//
//	@Override
//	public void onMessage(Message message) {
//		if(message.getMessageProperties().getConsumerQueue().compareTo("spring-boot2") == 0) {
//			try {
//				Thread.sleep(3 * 1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("retrieved from queue TWO -- " + Thread.currentThread().getId());
//		}
//		// TODO Auto-generated method stub
//		else {
//			System.out.println("retrieved from queue ONE -- " + Thread.currentThread().getId());
//		}
//		System.out.println("Message was recieved: " + new String(message.getBody()) + "   " + message.getMessageProperties().getConsumerQueue() + "\n\n");
//	}
//	
//}