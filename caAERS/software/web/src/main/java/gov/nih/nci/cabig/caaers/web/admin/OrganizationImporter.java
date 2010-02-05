package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
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
 * This class provides methods to import Organizations from Organization_Codes.txt, This is available on the CTEP site. 
 * http://ctep.cancer.gov/protocolDevelopment/docs/Organization_Codes.txt 
 *
 * @author Monish Dombla
 */
public class OrganizationImporter extends Importer {

	private OrganizationRepository organizationRepository;
	private static Logger logger = Logger.getLogger(OrganizationImporter.class);
	Map<String,Organization> organizationMap = null;
	
	
	/**
	 * This method accepts the file selected by the user, which is Organization_Codes.txt file from CTEP and process each record.
	 * 
	 * @param ctepOrganizationsFile
	 * @param command
	 */
	public void processEntities(File ctepOrganizationsFile, ImportCommand command) {
		
		organizationMap = getOrganizationMap();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ctepOrganizationsFile));
			String line = null;
			
			// skip first 6 lines
			reader.readLine();reader.readLine();reader.readLine();
			reader.readLine();reader.readLine();reader.readLine();
			int lineNumber = 6;

			while ((line = reader.readLine()) != null) {
				lineNumber = lineNumber + 1;
				//Process each line. If Organization exists add it to updateable list else add it to Importable list.
				DomainObjectImportOutcome<Organization> organizationImportOutcome = processOrganization(line,lineNumber);
				if (organizationImportOutcome != null){
					if(!organizationImportOutcome.isSavable()){
						command.addNonImportableOrganization(organizationImportOutcome);
					}else{
						if (organizationImportOutcome.getImportedDomainObject() != null){
							Organization dbOrg = organizationMap.get(organizationImportOutcome.getImportedDomainObject().getNciInstituteCode());
							if (dbOrg != null) {
								if (!StringUtils.equals(dbOrg.getName(), organizationImportOutcome.getImportedDomainObject().getName())) {
									dbOrg.setName(organizationImportOutcome.getImportedDomainObject().getName());
									organizationImportOutcome.setImportedDomainObject(dbOrg);
									command.addUpdateableOrganization(organizationImportOutcome);
								}
							}else{
								command.addImportableOrganization(organizationImportOutcome);
							}
						}
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			throw new CaaersSystemException(
					"There was an error, while importing Organizations from the file provided",e);
		}
	}

	/**
	 * This method creates all the Organizations in ImportableOrganizations list in ImportCommand & also
	 * updates all the Organizations in the UpdatableOrganizations list in ImportCommand.
	 * The Organization is created as a LocalOrganization in caAERS.
	 * 
	 * @param command
	 * @param request
	 */
	public void save(ImportCommand command, HttpServletRequest request) {
		//Create new Organizations.
        for (DomainObjectImportOutcome<Organization> importOutcome : command.getImportableOrganizations()) {
        	organizationRepository.createOrUpdate(importOutcome.getImportedDomainObject());
        }
        //Update existing Organizations.
        for (DomainObjectImportOutcome<Organization> importOutcome : command.getUpdateableOrganizations()) {
        	organizationRepository.createOrUpdate(importOutcome.getImportedDomainObject());
        }
	}

	/**
	 * @param organizationRepository the organizationRepository to set
	 */
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
	 /**
	* This method accepts a String which should be like 
	* "CA531","California Hematology Oncology Medical Group","Torrance","CA","USA" 
	* It splits the string into 5 tokens and creates a LocalOrganization object.
	* If the number of token are less than 5 the record/line is rejected.
	* @param organizationString
	* @return
	*/
	protected DomainObjectImportOutcome<Organization> processOrganization(String organizationString,int lineNumber){
		
		DomainObjectImportOutcome<Organization> organizationImportOutcome = null;
		LocalOrganization localOrganization = null;
		StringTokenizer st = null;
		String institutionCode;
		String institutionName;
		String city;
		String state;
		String country;

		if (StringUtils.isNotEmpty(organizationString)) {
	        
	        logger.debug("Orginial line from file -- >>> " + organizationString);
	        organizationString = organizationString.trim();
	        //Replace ", with "|
	        //This is done to set a delimiter other than ,
	        organizationString = StringUtils.replace(organizationString, "\",", "\"|");
	        logger.debug("Modified line -- >>> " + organizationString);
	        //Generate tokens from input String.
	        st = new StringTokenizer(organizationString,"|");
	        
	        //If there are 5 tokens as expected, process the record. Create a LocalOrganization object.
	        if(st.hasMoreTokens() && st.countTokens() == 5) {
		        organizationImportOutcome = new DomainObjectImportOutcome<Organization>();
		        localOrganization = new LocalOrganization();
		        
		        institutionCode = StringUtils.removeStart(st.nextToken(), "\"").trim();
		        institutionCode = StringUtils.removeEnd(institutionCode, "\"");
		        institutionName = StringUtils.removeStart(st.nextToken(), "\"").trim();
		        institutionName = StringUtils.removeEnd(institutionName, "\"");
		        city = StringUtils.removeStart(st.nextToken(), "\"").trim();
		        city = StringUtils.removeEnd(city, "\"");
		        state = StringUtils.removeStart(st.nextToken(), "\"").trim();
		        state = StringUtils.removeEnd(state, "\"");
		        country = StringUtils.removeStart(st.nextToken(), "\"").trim();
		        country = StringUtils.removeEnd(country, "\"");
		        localOrganization.setName(institutionName);
		        localOrganization.setNciInstituteCode(institutionCode);
		        localOrganization.setCity(city);
		        localOrganization.setState(state);
		        localOrganization.setCountry(country);
		       
		        organizationImportOutcome.setImportedDomainObject(localOrganization);
		        organizationImportOutcome.setSavable(Boolean.TRUE);
       
	        }else{
	        	logger.debug("Error in record -- >>> " + organizationString);
	        	organizationImportOutcome = new DomainObjectImportOutcome<Organization>();
	        	StringBuilder msgBuilder = new StringBuilder("Invalid organization record found at line ::: ");
	        	msgBuilder.append(lineNumber);
	        	organizationImportOutcome.addErrorMessage(msgBuilder.toString(), Severity.ERROR);
        }
	  }
		return organizationImportOutcome;
	}

	/**
	 * Return a HashMap of all the Organization in database. Key will be NCI Institute Code, Value will be Organization. 
	 * @return
	 */
	public Map<String, Organization> getOrganizationMap() {
		if(organizationMap == null){
			//Get all the Organizations from DB and store it in a Map. This is done to avoid the 8000+ DB calls to get an organization.
			organizationMap = new HashMap<String,Organization>();
			List<Organization> allOrganizations = organizationRepository.getAllOrganizations();
			for(Organization eachOrg : allOrganizations){
				organizationMap.put(eachOrg.getNciInstituteCode(), eachOrg);
			}
		}
		return organizationMap;
	}

	public void setOrganizationMap(Map<String, Organization> organizationMap) {
		this.organizationMap = organizationMap;
	}
}