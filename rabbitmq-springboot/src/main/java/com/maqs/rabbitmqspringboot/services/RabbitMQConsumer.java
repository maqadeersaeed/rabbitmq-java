package com.maqs.rabbitmqspringboot.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.maqs.rabbitmqspringboot.model.Person;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQConsumer {

//	@RabbitListener(queues = "Mobile")
//	public void getMessageFromMobile(Person p) { // If sure about class then specify otherwise byte[]
//		log.info(">>>>>>>>> " + p.getName());
//	}
	
	@RabbitListener(queues = "Mobile")
	public void getMessageFromMobile(byte[] message) throws IOException, ClassNotFoundException { // If sure about class then specify otherwise byte[]
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Person p = (Person)in.readObject();
		in.close();
		bis.close();
		
		log.info(">>>>>>>>> From Mobile Queue " + p.getName());
	}
	
	@RabbitListener(queues = "TV")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException { // If sure about class then specify otherwise byte[]
		
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Person p = (Person)in.readObject();
		in.close();
		bis.close();
		
		log.info(">>>>>>>>> From TV Queue " + p.getName());
	}
	
}
