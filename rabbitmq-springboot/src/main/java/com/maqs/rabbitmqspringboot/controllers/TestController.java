package com.maqs.rabbitmqspringboot.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maqs.rabbitmqspringboot.model.Person;

@RestController
@RequestMapping("/api/v1")
public class TestController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping("send-q-mobile/{name}")
	public String sendToMobileQueue(@PathVariable("name") String name) {
		
		Person person = new Person(1l, name);
		
		rabbitTemplate.convertAndSend("Mobile", person);
		
		return "success";
	}
	
	@GetMapping("send-d-exchange/{name}")
	public String sendToDirectExchange(@PathVariable("name") String name) {
		
		Person person = new Person(1l, name);
		
		String routingKey = "mobile";
		
		rabbitTemplate.convertAndSend("Direct-Exchange", routingKey, person);
		
		return "success";
	}
	
	@GetMapping("send-fo-exchange/{name}")
	public String sendToFanoutExchange(@PathVariable("name") String name) {
		
		Person person = new Person(1l, name);
		
		rabbitTemplate.convertAndSend("Fanout-Exchange1", "", person);
		
		return "success";
	}
	
	@GetMapping("send-t-exchange/{name}")
	public String sendToTopicExchange(@PathVariable("name") String name) {
		
		Person person = new Person(1l, name);
		
		String routingKey = "tv.mobile.ac";
		
		rabbitTemplate.convertAndSend("Topic-Exchange1", routingKey, person);
		
		return "success";
	}

	@GetMapping("send-h-exchange/{name}")
	public String sendToHeaderExchange(@PathVariable("name") String name) throws IOException {
		
		Person person = new Person(1l, name);
		
		String routingKey = "";
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput ou = new ObjectOutputStream(bos);
		ou.writeObject(person);
		ou.flush();
		ou.close();
		Message message = MessageBuilder.withBody(bos.toByteArray())
								.setHeader("item1", "mobile")
								.setHeader("item2", "television")
								.build();
		bos.close();
		
		rabbitTemplate.send("Header-Exchange1", routingKey, message);
		
		return "success";
	}
	
}
