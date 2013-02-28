/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class MonitorThread implements Runnable {

    private static Logger log = Logger.getLogger(MonitorThread.class);
    boolean interruped;
    private long checkIntervalMillis = 10000;
    private String fileName ;
    private File file;
    
    // stores the last modification time of the file
    private long lastModified = 0;
    
	public void run() {

		System.out.println("Initialize " + fileName);
		file = new File(fileName);
		DOMConfigurator.configure(fileName);
		lastModified = file.lastModified();

		monitor();
	}
 
	private void monitor() {
    	log.info("Starting log4j monitor");
     
		while (!interruped) {

		// check if File changed
	    	long temp = file.lastModified();
		    	if (lastModified != temp) {
		    	log.info("Initialize log4j configuration " + fileName);
				DOMConfigurator.configure(fileName);
				lastModified = temp;
	    	} else
	    		log.debug("Log4j configuration is not modified");
		    try {
		    	Thread.currentThread().sleep(checkIntervalMillis);
		    }catch (InterruptedException e) {
		    	interruped = true;
		    }
    	}
    	
		log.info("Shutting down log4j monitor");

        }

    public long getCheckIntervalMillis() {
    	return checkIntervalMillis;
    }
    /**
     * Sets the interval for checking the url for changes. Unit is
     * milliseconds, 10000 = 10 seconds
     * 
     * @param checkIntervalMillis
     */
	public void setCheckIntervalMillis(long checkIntervalMillis) {
		this.checkIntervalMillis = checkIntervalMillis;
	}
	
	public boolean isInterruped() {
		return interruped;
	}
	
	public void setInterruped(boolean interruped) {
		this.interruped = interruped;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
