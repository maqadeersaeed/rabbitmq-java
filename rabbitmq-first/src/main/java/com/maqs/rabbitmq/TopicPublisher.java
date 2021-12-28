package com.maqs.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicPublisher {

	public static void main(String[] args) {
		try {
			
			/**
			 * Topic Exchange has pattern key configured
			 * 
			 * '*' means exactly one word to match.
			 * '#' means any number of words to match. 
			 */

			
			ConnectionFactory cf = new ConnectionFactory();
			
			Connection connection = cf.newConnection();
			
			Channel channel = connection.createChannel();

			// Topic Exchange Matches the Keys Pattern and 
			// distribute message copy to all Queues matching key criteria
			String exchange = "Topic-Exchange1";
			
			
			// This key will be used by Direct exchange to route Message to Mobile Queue
			BasicProperties props = null;
			
			String routingKey = "tv.mobile.ac"; 
			String message = "This is Message for Mobile & AC";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			routingKey = "this.that.ac"; 
			message = "This is For AC";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			routingKey = "watch.tv.now"; 
			message = "This is TV Message";
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			channel.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
