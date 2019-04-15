/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xracoon.sys.owlet.orm.mappers.TagMapper;
import com.xracoon.sys.owlet.orm.mappersex.TagAssignMapperEx;
import com.xracoon.sys.owlet.orm.modelsex.TagEx;
import com.xracoon.sys.owlet.vo.JsonModel;

@Controller
@RequestMapping("/tag")
public class TagController {
	@Autowired private TagMapper tagMapper;
	@Autowired private TagAssignMapperEx taMapper;
	
	@RequestMapping(produces="application/json")
	public @ResponseBody
	ResponseEntity<JsonModel> jsonIndex(@RequestParam(value="num", defaultValue="20") long num)
			throws JsonGenerationException, JsonMappingException, IOException{
		List<TagEx> tagStatics=taMapper.assignStatisc(num);
		return ResponseEntity.ok(JsonModel.success(tagStatics));
	}
	
	@RequestMapping(produces="!application/json")
	public ModelAndView index(){
		ModelAndView mv=new ModelAndView("tag/index");
		mv.addObject("tags", tagMapper.selectByExample(null));
		return mv;
	}
}
