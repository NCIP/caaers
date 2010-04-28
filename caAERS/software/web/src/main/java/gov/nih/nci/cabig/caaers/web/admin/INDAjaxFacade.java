package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class INDAjaxFacade {
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigatorDao investigatorDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public InvestigatorDao getInvestigatorDao() {
        return investigatorDao;
    }

    public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

    public List<Investigator> matchInvestigators(String text) {
        List<Investigator> investigators = investigatorDao.getBySubnames(new String[] { text });
        return ObjectTools.reduceAll(investigators, "id", "firstName", "lastName");
    }

    public List<Organization> matchOrganization(String text) {

    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
        List<Organization> orgs = organizationDao.getBySubnames(query);
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode");
    }
    /*
     * added this method to call this wherever any security filtering on organization is required
     */
    public List<Organization> restrictOrganization(String text) {
        List<Organization> orgs = organizationRepository.restrictBySubnames(new String[] { text });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode","externalId");
    }

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}

	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}    
}
