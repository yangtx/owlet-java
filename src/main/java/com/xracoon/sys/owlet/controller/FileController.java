/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xracoon.sys.owlet.component.MultipartFormDataUtil;
import com.xracoon.sys.owlet.config.UploadType;
import com.xracoon.sys.owlet.services.UploadService;
import com.xracoon.util.basekit.StringEx;

@Controller
@RequestMapping("/file")
public class FileController {
	Logger logger=LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private UploadService uploadService;
	
	
	@RequestMapping(value="upload_editorMd",method=RequestMethod.POST)
	public @ResponseBody Object uploadImgEditorMD(HttpServletRequest request) throws IOException{
		EditorMdUploadJson model=new EditorMdUploadJson();
		
		Map<String,FileItem> params=MultipartFormDataUtil.parse(request);
//        String forProject=params.get("forProject").getString("UTF-8");
        String typeString=params.containsKey("type")?params.get("type").getString("UTF-8"):null;
        String userId=params.containsKey("uid")?params.get("uid").getString("UTF-8"):"0";
        String articleId=params.containsKey("aid")?params.get("aid").getString("UTF-8"):"0";
        
        int type=!StringEx.isBlank(typeString)&&StringEx.isNumber(typeString)?Integer.parseInt(typeString):0;
        UploadType ut=UploadType.ValueOf(type);
        if(ut==null){
			model.setSuccess(0);
			model.setMessage("上传失败: 未知的上传类型 "+type);
			return model;
		}
        
		FileItem item=params.get("editormd-image-file");
		String url=null;
		try{
			url=uploadService.upload(item, userId+"_"+articleId+"_", ut);
		}
		catch(Exception e){
			logger.error("图片上传失败",e);
		}
		if(url==null){
			model.setSuccess(0);
			model.setMessage("上传失败");
			return model;
		}
		
		model.setSuccess(1);
		model.setMessage("上传成功");
		model.setUrl(url);
        //success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
        //message : "提示的信息，上传成功或上传失败及错误信息等。",
        //url     : "图片地址"        // 上传成功时才返回
		return model;
	}
	
	public class EditorMdUploadJson{
		private int success;
		private String message;
		private String url;
		public int getSuccess() {
			return success;
		}
		public void setSuccess(int success) {
			this.success = success;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
}
