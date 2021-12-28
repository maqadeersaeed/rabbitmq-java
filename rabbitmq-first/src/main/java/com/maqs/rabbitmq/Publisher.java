package com.maqs.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		try {
			// We need connection factory & connection interface to send message to a queue.
			
			ConnectionFactory cf = new ConnectionFactory();
			
			Connection connection = cf.newConnection();
			
			Channel channel = connection.createChannel();
			
			String exchange = "";
			String routingKey = "Queue-1";
			BasicProperties props = null;
			
			String message = " Message From From To Queue";
			
			for (int i = 0; i < 100; i++) {
				channel.basicPublish("", routingKey, null, (i + message).getBytes());
			}
			
			channel.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
