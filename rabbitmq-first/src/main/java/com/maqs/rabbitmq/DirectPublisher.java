package com.maqs.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectPublisher {

	public static void main(String[] args) {
		try {
			// We need connection factory & connection interface to send message to a queue.
			
			ConnectionFactory cf = new ConnectionFactory();
			
			Connection connection = cf.newConnection();
			
			Channel channel = connection.createChannel();

			// Direct Exchange matches exact key and forward message to corresponding Queue.
			String exchange = "Direct-Exchange";
			
			// This key will be used by Direct exchange to route Message to Mobile Queue
			BasicProperties props = null;
			
			String routingKey = "tv"; 
			String message = "This is Tv";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			routingKey = "mobile"; 
			message = "This is Mobile";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			routingKey = "ac"; 
			message = "This is AC";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			channel.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
