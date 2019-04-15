package com.xracoon.sys.owlet.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xracoon.sys.owlet.component.GlobalConfig;
import com.xracoon.sys.owlet.orm.mappersex.ArticleMapperEx;

@Component
public class SearchService {
	Logger logger= LoggerFactory.getLogger(UploadService.class);
	@Autowired GlobalConfig globalConfig;
	@Autowired ArticleMapperEx articleMapper;
	
	public List<Long> searchArticle(String kw){
		return null;
	}
	
}
