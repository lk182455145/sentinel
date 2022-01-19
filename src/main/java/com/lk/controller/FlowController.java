package com.lk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lk.service.FlowService;

@RestController
public class FlowController {

	@Autowired
	private FlowService flowService;
	
	@GetMapping("flow")
	public String flow() {
		return flowService.flow();
	}
}
