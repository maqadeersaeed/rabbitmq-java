package com.maqs.rabbitmq;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RealtimePublisherExample {

	public static void main(String[] args) {
		try {
			ConnectionFactory cf = new ConnectionFactory();
			Connection con = cf.newConnection();
			Channel channel = con.createChannel();
			
			JSONObject json = new JSONObject();
			json.put("fromDate", "01/01/2021");
			json.put("toDate", "31/12/2021");
			json.put("user", "user@gmail.com");
			
			channel.basicPublish("", "Queue-1", null, json.toString().getBytes());
			
			channel.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
