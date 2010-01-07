package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Sameer Sawant
 */

public class AgentImporter extends Importer{
	
	private static Logger logger = Logger.getLogger(AgentImporter.class);
	private AgentDao agentDao;
	private List<String> ctepAgentRecords = new ArrayList<String>();
	private List<Agent> allAgents = null;
	
	
	public void processEntities(File ctepAgentsFile,ImportCommand command){
		
		//Get all the Agents from DB and store it in a Map.
		Map<String,Agent> agentMap = new HashMap<String,Agent>();
		allAgents = agentDao.getAll();
		for(Agent eachAgent : allAgents){
			agentMap.put(eachAgent.getNscNumber().trim(), eachAgent);
		}
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ctepAgentsFile));
			String line = null;
			// skip first 5 lines
			reader.readLine();reader.readLine();reader.readLine();
			reader.readLine();reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				ctepAgentRecords.add(line);
			}
			reader.close();
			
			for(String eachRecord : ctepAgentRecords){
				DomainObjectImportOutcome<Agent> agentImportOutcome = processAgent(eachRecord);
				if (agentImportOutcome != null && agentImportOutcome.getImportedDomainObject() != null) {
					Agent dbAgent = agentMap.get(agentImportOutcome.getImportedDomainObject().getNscNumber());
					if (dbAgent != null) {
						agentImportOutcome.setImportedDomainObject(dbAgent);
						command.addUpdateableAgent(agentImportOutcome);
					} else {
						command.addImportableAgent(agentImportOutcome);
					}
				}
			}
		} catch (Exception e) {
			throw new CaaersSystemException(
					"There was an error, while importing Agents from the file provided",e);
		}
	}
		

	private DomainObjectImportOutcome<Agent> processAgent(String agentString){
		
		DomainObjectImportOutcome<Agent> agentImportOutcome = null;
		Agent agent = null;
		StringTokenizer st = null;
		String nscNumber;
		String agentName;

		if (agentString != null && StringUtils.isNotEmpty(agentString)) {
	        
	        logger.debug("Orginial line from file -- >>> " + agentString);
	        agentString = agentString.trim();
	        //Replace ", with "|
	        //This is done to set a delimiter other than ,
	        agentString = StringUtils.replace(agentString, "\",", "\"|");
	        logger.debug("Modified line -- >>> " + agentString);
	        //Generate tokens from input String.
	        st = new StringTokenizer(agentString,"|");
	        
	        //If there are 2 tokens as expected, process the record. Create a Agent object.
	        if(st.hasMoreTokens() && st.countTokens() == 2) {
	        	agentImportOutcome = new DomainObjectImportOutcome<Agent>();
	        	agent = new Agent();
	        
	        	nscNumber = StringUtils.removeStart(st.nextToken(), "\"").trim();
	        	nscNumber = StringUtils.removeEnd(nscNumber, "\"");
	        	agentName = StringUtils.removeStart(st.nextToken(), "\"").trim();
	        	agentName = StringUtils.removeEnd(agentName, "\"");
	        	
	        	agent.setNscNumber(nscNumber);
	        	agent.setName(agentName);
	        	
	        	agentImportOutcome.setImportedDomainObject(agent);
	        	agentImportOutcome.setSavable(Boolean.TRUE);
       
        }else{
        	logger.debug("Error in record -- >>> " + agentString);
        }
	  }
		return agentImportOutcome;
	}
		
	public void save(ImportCommand command, HttpServletRequest request){
		
        for (DomainObjectImportOutcome<Agent> importOutcome : command.getImportableAgents()) {
        	agentDao.save(importOutcome.getImportedDomainObject());
        }
	}
	
	public void setAgentDao(AgentDao agentDao){
		this.agentDao = agentDao;
	}
}