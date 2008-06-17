package gov.nih.nci.cabig.caaers.service.migrator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;


public class IdentifierMigrator<E extends AbstractIdentifiableDomainObject> implements Migrator<E>{
	private OrganizationDao organizationDao;
	
	/**
	 * Will migrate the identifiers from source to destination
	 */
	public void migrate(E src,  E dest,
			DomainObjectImportOutcome<E> outcome) {

        // Identifiers
        for (Identifier identifier : src.getIdentifiers()) {
            if (identifier instanceof OrganizationAssignedIdentifier) {
            	OrganizationAssignedIdentifier orgIdentifier = (OrganizationAssignedIdentifier) identifier;
            	
            	Organization organization = null;
        		if(orgIdentifier.getOrganization().getNciInstituteCode() != null && orgIdentifier.getOrganization().getNciInstituteCode().length() > 0){
        			String nciInstituteCode = orgIdentifier.getOrganization().getNciInstituteCode();
        	        organization = fetchOrganization(nciInstituteCode);
        		}else{
        			String orgName = orgIdentifier.getOrganization().getName();
        			organization = organizationDao.getByName(orgName);
        		}
            	
            	outcome.ifNullObject(organization, Severity.ERROR,"The organization specified in identifier is invalid");
            	orgIdentifier.setOrganization(organization);
            }
            
            dest.getIdentifiers().add(identifier);
        }

        outcome.ifNullOrEmptyList(dest.getIdentifiers(), Severity.ERROR, "Identifiers are either Empty or Not Valid");
		
	}
	
	  /**
     * Fetches the organization from the DB
     * 
     * @param nciCode
     * @return
     */
    private Organization fetchOrganization(String nciInstituteCode) {
        OrganizationQuery orgQuery = new OrganizationQuery();

        if (StringUtils.isNotEmpty(nciInstituteCode)) {
            orgQuery.filterByNciCodeExactMatch(nciInstituteCode);
        }

        List<Organization> orgList = organizationDao.searchOrganization(orgQuery);

        if (orgList == null || orgList.isEmpty()) {
            return null;
        }
        if (orgList.size() > 1) {
            //("Multiple organizations exist with same NCI Institute Code :" + nciInstituteCode);
        }

        return orgList.get(0);
    }
    
	///BEAN PROPERTIES

    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}