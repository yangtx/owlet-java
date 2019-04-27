package com.xracoon.sys.owlet.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.xracoon.sys.owlet.component.GlobalConfig;
import com.xracoon.sys.owlet.config.UploadType;
import com.xracoon.util.basekit.StringEx;

@Component
public class UploadService {
	Logger logger= LoggerFactory.getLogger(UploadService.class);

	@Value("${app.upload.path}")
	public String uploadPath;
	@Value("${app.asset.server}")
	public String assetServer;


	public String upload(FileItem item, String prefix, UploadType type) throws IOException{
		if(StringEx.isBlank(uploadPath)){
			logger.error("upload path not set");
			return null;
		}
		if(assetServer!=null)
			assetServer=assetServer.replaceAll("[/\\\\]+$", "");
		if(StringEx.isBlank(assetServer)){
			logger.error("asset server not set");
			return null;
		}
		
        byte[] fileContent=item.get();
        String fileName=item.getName();
		//long size=item.getSize();

		String outputFileName=UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
		if(prefix!=null)
			outputFileName=prefix+outputFileName;
		
		File outputFile=new File( new File(uploadPath, type.getPath()), outputFileName );
		if(!outputFile.getParentFile().exists())
			outputFile.getParentFile().mkdirs();
		
		FileOutputStream outputStream=null;
		try{
			outputStream= new FileOutputStream(outputFile);
			outputStream.write(fileContent);
			logger.info("save file "+outputFile.toString());
		}
		finally{
			if(outputStream!=null)
				outputStream.close();
		}
		String url=assetServer+"/"+type.getPath()+"/"+outputFileName;
		return url;
	}
}
