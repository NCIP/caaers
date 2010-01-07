package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.AgentDao;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * @author Sameer Sawant
 */

public class AgentImporter extends Importer{
	
	private static Logger logger = Logger.getLogger(AgentImporter.class);
	private AgentDao agentDao;
	
	public void AgentImporter(){
	}
	
	public void processEntities(File xmlFile,ImportCommand command){
		//TODO
	}
	
	public void save(ImportCommand command, HttpServletRequest request){
		//TODO
	}
	
	public void setAgentDao(AgentDao agentDao){
		this.agentDao = agentDao;
	}
}