package gov.nih.nci.cabig.caaers.audit;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RuleLogger {
	
	private static RuleLogger instance;
	Logger fileRuleLogger = Logger.getLogger("gov.nih.nci.cabig.caaers.audit");
	
	private  RuleLogger() throws Exception{
		
		try {
			
			
			//FileHandler h = new FileHandler("/rules.log", true);
			String fileLocation = this.getLogFileLocation();
			FileHandler h = new FileHandler(fileLocation, true);
			SimpleFormatter sf = new SimpleFormatter();
			h.setFormatter(sf);
			fileRuleLogger.addHandler(h);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		
	}
	
	public static synchronized RuleLogger getInstance() throws Exception{
		if(instance==null){
			instance = new RuleLogger();
		}
		return instance;
	}
	
	private String getLogFileLocation(){
		String rule_file_location = "";
		String CATALINA_HOME = System.getProperty("CATALINA_HOME");
		if(CATALINA_HOME==null){
			rule_file_location = System.getProperty("user.home")+File.separator+"rules.log";
		}else{
			rule_file_location=CATALINA_HOME+File.separator+"logs"+File.separator+"rules.log";
			
		}
		
		return rule_file_location;
	}
  
	public void logMessage(String message){
		fileRuleLogger.log(Level.INFO, message);
	}
	
	
}
