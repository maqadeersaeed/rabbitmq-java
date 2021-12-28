package com.maqs.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPublisher {

	public static void main(String[] args) {
		try {
			// We need connection factory & connection interface to send message to a queue.
			
			ConnectionFactory cf = new ConnectionFactory();
			
			Connection connection = cf.newConnection();
			
			Channel channel = connection.createChannel();
			
			// Fanout Exchange distributes same message to all binding Queues
			String exchange = "Fanout-Exchange1";
			
			// Key Remains Empty in Fanout Exchange 
			// Because it will forward message to all binded Queues
			String routingKey = ""; 
			BasicProperties props = null;
			
			String message = "This is Message";
			
			for (int i = 0; i < 100; i++) {
				channel.basicPublish(exchange, routingKey, props, (i + message).getBytes());
			}
			
			
			channel.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
