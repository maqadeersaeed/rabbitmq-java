package com.maqs.rabbitmqspringboot.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -182843254279281164L;
	
	private Long id;
	private String name;
	
}
