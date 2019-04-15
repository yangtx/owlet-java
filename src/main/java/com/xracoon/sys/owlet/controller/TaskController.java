/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@RequestMapping
	public String hello(){
		return "task/index";
	}
}
