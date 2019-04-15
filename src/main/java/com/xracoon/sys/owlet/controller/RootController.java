package com.xracoon.sys.owlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
	@RequestMapping
	public String index(){
		return "redirect:/article";
	}
}
