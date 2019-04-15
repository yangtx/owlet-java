/**
* @author yangtianxin
* @date 2016年3月14日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.component;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogService {
	private volatile static LogService instance;
	private Logger logger=null;
	public LogService(){
		logger=LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		start();
	}
	
	public static LogService getInstance(){
		if(instance==null){
			synchronized (LogService.class) {
				if(instance==null){
					instance=new LogService();
					instance.start();
				}
			}
		}
		return instance;
	}
	
	private LinkedBlockingQueue<LogInfo> logInfoQueue=new LinkedBlockingQueue<LogInfo>();
	private Thread thread=null;
	
	private void start()
	{
		thread=new Thread(new Runnable() {
			public void run() {
				logger.info("logservice is running...");
				LogInfo loginfo=null;
				while (!Thread.currentThread().isInterrupted()) {
					loginfo=logInfoQueue.poll();
					if(loginfo==null){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							break;
						};
					}
					else{
						if(loginfo.level==Level.DEBUG)
							logger.debug(loginfo.msg);
						else if(loginfo.level==Level.INFO)
							logger.info(loginfo.msg);
						else if(loginfo.level==Level.WARN)
							logger.warn(loginfo.msg);
						else if(loginfo.level==Level.ERROR && loginfo.throwable==null)
							logger.error(loginfo.msg);
						else if(loginfo.level==Level.ERROR && loginfo.throwable!=null)
							logger.error(loginfo.msg, loginfo.throwable);
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

    public void debug(String msg) {
		try {
			logInfoQueue.put(new LogInfo(Level.DEBUG,msg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void info(String msg) {
		try {
			logInfoQueue.put(new LogInfo(Level.INFO,msg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void warn(String msg) {
		try {
			logInfoQueue.put(new LogInfo(Level.WARN,msg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void error(String msg) {
		try {
			logInfoQueue.put(new LogInfo(Level.ERROR,msg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void error(String msg, Throwable throwable){
		try {
			logInfoQueue.put(new LogInfo(Level.ERROR, msg, throwable));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
class LogInfo
{
	public Level level=Level.INFO;
	public String msg;
	public Throwable throwable;
	
	public LogInfo(){}
	public LogInfo(String msg){
		this.msg=msg;
	} 
	public LogInfo(Level level, String msg){
		this.level=level;
		this.msg=msg;
	}
	public LogInfo(Level level, String msg, Throwable throwable){
		this.level=level;
		this.msg=msg;
		this.throwable=throwable;
	}
	public LogInfo Msg(String msg){
		this.msg=msg;
		return this;
	}	
	public LogInfo Level(Level level){
		this.level=level;
		return this;
	}
}
enum Level
{
	DEBUG,
	INFO,
	WARN,
	ERROR
}
