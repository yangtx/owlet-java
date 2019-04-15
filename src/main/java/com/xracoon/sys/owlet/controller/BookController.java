/**
* @author yangtianxin
* @date 2016年4月7日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notebook")
public class BookController {
	
	@RequestMapping
	public String index(){
		return "notebook/index";
	}
}
