package com.lk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lk.config.Info;

@RestController
@RequestMapping("info")
public class InfoController {

	@Autowired
	private Info info;
	
	@GetMapping
	public String info() {
		return info.getInfo() + "----" + info.getValue();
	}
}
