package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;

import java.util.List;

public class INDAjaxFacade {
    private OrganizationDao organizationDao;

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
        List<Organization> orgs = organizationDao.getBySubnames(new String[] { text });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode");
    }
    
    public List<Organization> restrictOrganization(String text) {
        List<Organization> orgs = organizationDao.restrictBySubnames(new String[] { text });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode");
    }    
}
