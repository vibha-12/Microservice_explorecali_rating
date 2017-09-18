package com.example.ec.explorecali;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest/hello/server")

public class HelloResource {
	@GetMapping
	public String hello()
	{
		
				return  "\nINSIDE PRODUCER MICROSERVICE";
	}
	

}
