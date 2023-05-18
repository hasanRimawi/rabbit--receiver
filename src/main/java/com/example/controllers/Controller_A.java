package com.example.controllers;

import java.time.Duration;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api")
public class Controller_A {
	@GetMapping(path = "/getAll")
	public List<String> getall(){
		return List.of("Tomorrow", " is", " better.");
	}
	@GetMapping(path="/stream")
	public Flux<String> stream(){
		return Flux.just("one", "two", "three").delayElements(Duration.ofSeconds(3));
	}
}
