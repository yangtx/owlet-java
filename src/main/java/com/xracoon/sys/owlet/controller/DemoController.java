/**
* @author yangtianxin
* @date 2016年4月7日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping
	public String hello(){
		return "demo/index";
	}
}
