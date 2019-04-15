package com.xracoon.sys.owlet.component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultipartFormDataUtil {
	static public Map<String, FileItem> parse(HttpServletRequest request)
	{
		Map<String,FileItem> maps=new HashMap<>();
		try{
		if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
   	 		for(FileItem i: items)
   	 			maps.put(i.getFieldName(), i);
            return maps;
		}
		else
			return null;
		}catch(Exception e)
		{
			return null;
		}
	}
}
