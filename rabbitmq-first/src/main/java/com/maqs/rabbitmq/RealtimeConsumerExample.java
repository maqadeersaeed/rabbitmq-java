package com.maqs.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RealtimeConsumerExample {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection connection = cf.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody());
			
			JSONObject obj = new JSONObject(message);
					
			System.out.println("Message Received" + obj.toString());
		};
		
		CancelCallback cancelCallback = consumerTag -> {
//			consumerTag
		};
		
		channel.basicConsume("Queue-1", true, deliverCallback, cancelCallback);
	}
	
}
