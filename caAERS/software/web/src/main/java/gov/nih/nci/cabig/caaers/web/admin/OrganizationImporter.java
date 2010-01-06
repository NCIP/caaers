package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
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
	
	public void processEntities(File ctepOrganizationsFile, ImportCommand command) {
		OrganizationQuery query = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					ctepOrganizationsFile));
			String line = null;
			// skip first 6 lines
			reader.readLine();
			reader.readLine();
			reader.readLine();
			reader.readLine();
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				DomainObjectImportOutcome<Organization> organizationImportOutcome = processOrganization(line);
				if (organizationImportOutcome != null
						&& organizationImportOutcome.getImportedDomainObject() != null) {
					query = new OrganizationQuery();
					query.filterByNciCodeExactMatch(organizationImportOutcome
							.getImportedDomainObject().getNciInstituteCode());
					List<Organization> localOrgList = organizationRepository.getLocalOrganizations(query);
					if (localOrgList != null && localOrgList.size() > 0) {
						organizationImportOutcome
								.setImportedDomainObject(localOrgList.get(0));
						command
								.addUpdateableOrganization(organizationImportOutcome);
					} else {
						command
								.addImportableOrganization(organizationImportOutcome);
					}
				}
			}
		} catch (Exception e) {
			throw new CaaersSystemException(
					"There was an error, while importing Organizations from the file provided",
					e);
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


	        while (organizationString != null && StringUtils.isNotEmpty(organizationString)) {

	        
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