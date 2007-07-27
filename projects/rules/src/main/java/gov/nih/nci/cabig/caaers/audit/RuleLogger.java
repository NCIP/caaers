package gov.nih.nci.cabig.caaers.audit;

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
			
			FileHandler h = new FileHandler("/rules.log", true);
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
  
	public void logMessage(String message){
		fileRuleLogger.log(Level.INFO, message);
	}
}
