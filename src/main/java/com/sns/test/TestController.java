package com.sns.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	public @ResponseBody String test() {
		return"helloWord";
	}
	
	@RequestMapping("/test/jsp")
	public String testView() {
		return"test/test";
	}
}
