package com.maqs.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeaderPublisher {

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

			// Header Exchange accepts messages with a header, it does has any routing key.
			// Each Queue is bounded with x-match of header.
			// Message is forwarded to all Queues matching header conditions.
			String exchange = "Header-Exchange1";
			
			
			// This key will be used by Direct exchange to route Message to Mobile Queue
			String routingKey = ""; 
			String message = "This is Message for Mobile & TV";
			// header
//			BasicProperties props = null;
			Map<String, Object> heaerMap = new HashMap<String, Object>();
			heaerMap.put("item1", "mobile");
			heaerMap.put("item2", "tv");

			BasicProperties props = new BasicProperties();
			props.builder().headers(heaerMap).build();
			
			channel.basicPublish(exchange, routingKey, props, (message).getBytes());
			
			
			channel.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
