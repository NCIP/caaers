package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
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
public class OrganizationImporter extends Importer {

	private OrganizationRepository organizationRepository;
	private static Logger logger = Logger.getLogger(OrganizationImporter.class);
	private List<String> ctepOrganizationRecords = new ArrayList<String>();
	private List<Organization> allOrganizations = null;
	
	
	public void processEntities(File ctepOrganizationsFile, ImportCommand command) {
		
		//Get all the Organizations from DB and store it in a Map.
		Map<String,Organization> organizationMap = new HashMap<String,Organization>();
		allOrganizations = organizationRepository.getAllOrganizations();
		for(Organization eachOrg : allOrganizations){
			organizationMap.put(eachOrg.getNciInstituteCode().trim(), eachOrg);
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ctepOrganizationsFile));
			String line = null;
			// skip first 6 lines
			reader.readLine();reader.readLine();reader.readLine();
			reader.readLine();reader.readLine();reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				ctepOrganizationRecords.add(line);
			}
			reader.close();
			
			for(String eachRecord : ctepOrganizationRecords){
				DomainObjectImportOutcome<Organization> organizationImportOutcome = processOrganization(eachRecord);
				if (organizationImportOutcome != null && organizationImportOutcome.getImportedDomainObject() != null) {
					Organization dbOrg = organizationMap.get(organizationImportOutcome.getImportedDomainObject().getNciInstituteCode());
					if (dbOrg != null) {
						organizationImportOutcome.setImportedDomainObject(dbOrg);
						command.addUpdateableOrganization(organizationImportOutcome);
					} else {
						command.addImportableOrganization(organizationImportOutcome);
					}
				}
			}
		} catch (Exception e) {
			throw new CaaersSystemException(
					"There was an error, while importing Organizations from the file provided",e);
		}
	}

	public void save(ImportCommand command, HttpServletRequest request) {
        for (DomainObjectImportOutcome<Organization> importOutcome : command.getImportableOrganizations()) {
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
	private DomainObjectImportOutcome<Organization> processOrganization(String organizationString){
		
		DomainObjectImportOutcome<Organization> organizationImportOutcome = null;
		LocalOrganization localOrganization = null;
		StringTokenizer st = null;
		String institutionCode;
		String institutionName;
		String city;
		String state;
		String country;

		if (organizationString != null && StringUtils.isNotEmpty(organizationString)) {
	        
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
        }
	  }
		return organizationImportOutcome;
	}
}