package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * This class provides methods to import Organizations from agents.txt, This is available on the CTEP site. 
 * http://ctep.cancer.gov/protocolDevelopment/docs/agents.txt 
 * 
 * @author Monish Dombla
 */

public class AgentImporter extends Importer{
	
	private static Logger logger = Logger.getLogger(AgentImporter.class);
	private AgentDao agentDao;
	private Map<String,Agent> agentMap = null;
	
	/**
	 * This method accepts the file selected by the user, which is agents.txt file from CTEP and process each record.
	 * 
	 * @param File
	 * @param ImportCommand
	 */
	public void processEntities(File ctepAgentsFile,ImportCommand command){
		
		agentMap = getAgentMap();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ctepAgentsFile));
			String line = null;
			// skip first 5 lines
			reader.readLine();reader.readLine();reader.readLine();
			reader.readLine();reader.readLine();
			int lineNumber = 5;
		
			while ((line = reader.readLine()) != null) {
				lineNumber = lineNumber + 1;
				//Process each line. If agents exists add it to updateable list else add it to Importable list.
				DomainObjectImportOutcome<Agent> agentImportOutcome = processAgent(line,lineNumber);
				if(agentImportOutcome != null){
					if(!agentImportOutcome.isSavable()){
						command.addNonImportableAgent(agentImportOutcome);
					}else{
						if(agentImportOutcome.getImportedDomainObject() != null) {
							Agent dbAgent = agentMap.get(agentImportOutcome.getImportedDomainObject().getNscNumber());
							if (dbAgent != null){
								if (!StringUtils.equals(dbAgent.getName(), agentImportOutcome.getImportedDomainObject().getName())) {
									dbAgent.setName(agentImportOutcome.getImportedDomainObject().getName());
									agentImportOutcome.setImportedDomainObject(dbAgent);
									command.addUpdateableAgent(agentImportOutcome);
								}
							}else{
								command.addImportableAgent(agentImportOutcome);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CaaersSystemException(
					"There was an error, while importing Agents from the file provided",e);
		}
	}
		

	 /**
	* This method accepts a String which should be like 
	* "723227","(161-180)ESO-1 Peptide" 
	* It splits the string into 2 tokens and creates a Agent object.
	* If the number of token are less than 2 the record/line is rejected.
	* @param agentString
	* @return
	*/	
	protected DomainObjectImportOutcome<Agent> processAgent(String agentString,int lineNumber){
		
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
        	agentImportOutcome = new DomainObjectImportOutcome<Agent>();
        	StringBuilder msgBuilder = new StringBuilder("Invalid agent record found at line ::: ");
        	msgBuilder.append(lineNumber);
        	agentImportOutcome.addErrorMessage(msgBuilder.toString(), Severity.ERROR);
        }
	  }
		return agentImportOutcome;
	}

	
	/**
	 * This method creates all the Agents in ImportableAgentss list in ImportCommand & also
	 * updates all the Agents in the UpdatableAgents list in ImportCommand.
	 * 
	 * @param ImportCommand
	 * @param HttpServletRequest
	 */
	public void save(ImportCommand command, HttpServletRequest request){
		//Create new agents.
        for (DomainObjectImportOutcome<Agent> importOutcome : command.getImportableAgents()) {
        	agentDao.save(importOutcome.getImportedDomainObject());
        }
        //Update existing agents.
        for (DomainObjectImportOutcome<Agent> importOutcome : command.getUpdateableAgents()) {
        	agentDao.save(importOutcome.getImportedDomainObject());
        }
	}
	
	public void setAgentDao(AgentDao agentDao){
		this.agentDao = agentDao;
	}

	public Map<String, Agent> getAgentMap() {
		if(agentMap == null){
			//Get all the Agents from DB and store it in a Map.
			agentMap = new HashMap<String,Agent>();
			List<Agent> allAgents = agentDao.getAll();
			for(Agent eachAgent : allAgents){
				agentMap.put(eachAgent.getNscNumber().trim(), eachAgent);
			}
		}
		return agentMap;
	}

	public void setAgentMap(Map<String, Agent> agentMap) {
		this.agentMap = agentMap;
	}
}