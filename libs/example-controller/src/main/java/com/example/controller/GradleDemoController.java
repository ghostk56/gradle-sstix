package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.BaseException;
import com.example.rest.ExampleException;
import com.example.rest.ExampleException.ExampleErrorType;
import com.example.service.GradleDemoService;

@CrossOrigin(origins = "*")
@RestController
public class GradleDemoController extends BaseException {

	@Autowired
	private GradleDemoService demoService;

	@Value("${spring.profiles.active}")
	private String myProfile;

	@GetMapping
	public String home() {
		return "hello-gradledemo-" + myProfile;
	}

	@GetMapping("city")
	public Integer city() {
		return demoService.CityCount();
	}

	@GetMapping("/exe")
	public int baseExe() {
		int e = 1 / 0;
		return e;
	}

	@GetMapping("/exp")
	public void expExe() {
		throw new ExampleException(ExampleErrorType.EXAMPLE_UNKNOW_SYSTEM_ERROR, "未知錯誤");
	}
}
