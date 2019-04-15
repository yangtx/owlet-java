/**
* @author yangtianxin
* @date 2016年6月3日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.xracoon.util.basekit.FilesEx;
import com.xracoon.util.basekit.security.Codec;
import com.xracoon.util.basekit.security.Crypto;

@Component
public class GlobalConfig {
	public static Logger logger=LoggerFactory.getLogger(GlobalConfig.class);
	
	public static String ATTR_AUTHTOKEN = "OWLET_AUTH_TOKEN";
	public static String ATTR_USER= "OWLET_USER";
	public static String ATTR_ERROR= "OWLET_ERROR";
	public static String ATTR_THEME= "OWLET_THEME";
	public static String ATTR_THEME_EDITOR_NIGHT ="OWLET_THEME_EDITOR_NIGHT";
	public static String ATTR_THEME_EDITOR_DAY ="OWLET_THEME_EDITOR_DAY";
	
	public static String THEME_DAY_MODE= "day-mode";
	public static String THEME_NIGHT_MODE= "night-mode";
	public static String THEME_DEFAULT= "night-mode";
	public static String THEME_EDITOR_NIGHT_DEFUALT= "pastel-on-dark";
	public static String THEME_EDITOR_DAY_DEFAULT="eclipse";
	
	public static final int EDITOR_POST_INTERVAL = 10;
	public static SecretKey serverkey;

	static{
		try {
			serverkey = Crypto.loadSecretKey(Codec.decBase64(FilesEx.readString("serverkey").getBytes()), "AES");
		} catch (IOException e) {
			logger.error("failed to load server key", e);
		}
	}

	@Value("${app.upload.path}")
	public String uploadPath;
	@Value("${app.asset.server}")
	public String assetServer;
}
