package com.xracoon.sys.owlet.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xracoon.sys.owlet.vo.JsonModel;
import com.xracoon.util.basekit.StreamsEx;

@Controller
@RequestMapping("/tool")
public class ToolController {
	@RequestMapping
	public String index(){
		return "tool/index";
	}
	
	@RequestMapping(value="/scout", method=RequestMethod.GET)
	public String scout(){
		return "tool/scout";
	}
	
	@RequestMapping(value="/scout", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException{
		req.getInputStream();
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		StreamsEx.copy(req.getInputStream(), os);
		String str=os.toString();
		ObjectMapper om=new ObjectMapper();
		return om.writeValueAsString(JsonModel.success("done"));
	}
}
