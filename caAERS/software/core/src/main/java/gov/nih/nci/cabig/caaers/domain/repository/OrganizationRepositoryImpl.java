package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationConverterDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationFromStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyOrganizationsQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;


 
/**
 * The Class OrganizationRepositoryImpl.
 *
 * @author Srini Akala
 * @author Monish Dombla
 */

@Transactional
public class OrganizationRepositoryImpl implements OrganizationRepository {

	/** The log. */
	private Logger log = Logger.getLogger(getClass());
    
    /** The organization dao. */
    private OrganizationDao organizationDao;
    
    /** The organization converter dao. */
    private OrganizationConverterDao organizationConverterDao;
    
    /** The coppa mode for auto completers. */
    private boolean coppaModeForAutoCompleters;
    

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#createOrUpdate(gov.nih.nci.cabig.caaers.domain.Organization)
     */
    public void createOrUpdate(Organization organization) {
        if (organization.getId() == null) {
            create(organization);
        } else {
            organizationDao.save(organization);
        }
    }

    /**
     * Create a new organization. Note that this method must be used when entering a new
     * organization (not {@link OrganizationDao#save}). As written, it is not suitable for updating
     * an existing organization.
     *
     * @param site the site
     * @throws CaaersSystemException the caaers system exception
     */
    public void create(Organization site) throws CaaersSystemException {
    	organizationDao.save(site);
    }
    
    /**
     * This method is used in the Import Organizations flow. This will avoid calls to COPPA when importing Organization_Codes.txt from CTEP.
     *
     * @param organization the organization
     * @throws CaaersSystemException the caaers system exception
     */
    public void saveImportedOrganization(Organization organization)  throws CaaersSystemException{
        if (organization.getId() == null) {
        	organizationDao.saveImportedOrganization(organization);
        } else {
            organizationDao.saveImportedOrganization(organization);
        }
    }

    /**
     * Sets the organization dao.
     *
     * @param organizationDao the new organization dao
     */
    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }


    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getOrganizationsHavingStudySites(gov.nih.nci.cabig.caaers.dao.query.OrganizationFromStudySiteQuery)
     */
    public List<Organization> getOrganizationsHavingStudySites(OrganizationFromStudySiteQuery query ) {
        return organizationDao.getOrganizationsHavingStudySites(query);
    }
    
    /**
     * This method converts a LocalOrganization to a RemoteOrganization.
     *
     * @param localOrganization the local organization
     * @param remoteOrganization the remote organization
     */
 	public void convertToRemote(Organization localOrganization,
			Organization remoteOrganization) {
		ConverterOrganization conOrg = organizationConverterDao.getById(localOrganization.getId());
		conOrg.setType("REMOTE");
		conOrg.setExternalId(remoteOrganization.getExternalId());
		conOrg.setName(remoteOrganization.getName());
		conOrg.setNciInstituteCode(remoteOrganization.getNciInstituteCode());
		conOrg.setCity(remoteOrganization.getCity());
		conOrg.setState(remoteOrganization.getState());
		conOrg.setCountry(remoteOrganization.getCountry());
		organizationConverterDao.save(conOrg);
	}
 	
 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getLocalOrganizations(gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery)
	  */
	 public List<Organization> getLocalOrganizations(final OrganizationQuery query){
 		return organizationDao.getLocalOrganizations(query);
 	}
 	
 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#searchRemoteOrganization(java.lang.String, java.lang.String)
	  */
	 @SuppressWarnings("unchecked")
	public List<Organization> searchRemoteOrganization(String coppaSearchText, String sType){
 		//List organizations =  organizationDao.getLocalOrganizations(query);
 		Organization searchCriteria = new RemoteOrganization();
 		if (sType.equals("name")) {
 			searchCriteria.setName(coppaSearchText);
 		}
 		if (sType.equals("nciInstituteCode")) {
 			searchCriteria.setNciInstituteCode(coppaSearchText);
 		}        
    	List<Organization> remoteOrganizations = organizationDao.getRemoteOrganizations(searchCriteria);
    	return remoteOrganizations;
 	}
 	
 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getApplicableOrganizationsFromStudySites(java.lang.String, java.lang.Integer)
	  */
	 @SuppressWarnings("unchecked")
 	public List<Organization> getApplicableOrganizationsFromStudySites(String text, Integer studyId){
 		OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
 		if(StringUtils.isNotBlank(text))
 			query.filterByOrganizationNameOrNciCode(text);
 		if(studyId != null)
 			query.filterByStudy(studyId);
 		List<Organization> organizations = organizationDao.getApplicableOrganizationsFromStudySites(query);
 		return organizations;
 	}
 	
 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#searchOrganization(gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery)
	  */
	 @SuppressWarnings("unchecked")
	public List<Organization> searchOrganization(final OrganizationQuery query){
 		List organizations =  organizationDao.getLocalOrganizations(query);
 		// to get remote organizations ...
 		Organization searchCriteria = new RemoteOrganization();
 		Map<String, Object> queryParameterMap = query.getParameterMap();
 		for(Map.Entry<String, Object> entry : queryParameterMap.entrySet()){
 			if (entry.getKey().equals("name")) {
				searchCriteria.setName(entry.getValue().toString());
			}
			if (entry.getKey().equals("nciInstituteCode")) {
				searchCriteria.setNciInstituteCode(entry.getValue().toString());
			}
 		}

		List<Organization> remoteOrganizations = organizationDao.getRemoteOrganizations(searchCriteria);
		if (remoteOrganizations.size() > 0) {
			return mergeOrgs(organizations,remoteOrganizations);
		}
 		
    	return organizations;
 	}
 	
 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#restrictBySubnames(java.lang.String[])
	  */
	 public List<Organization> restrictBySubnames(final String[] subnames) {
         return restrictBySubnames(subnames, false, true);
    }

 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getApplicableOrganizationsFromStudyOrganizations(java.lang.String, java.lang.Integer)
	  */
	 public List<StudyOrganization> getApplicableOrganizationsFromStudyOrganizations(final String text, Integer studyId) {
         StudyOrganizationsQuery query = new StudyOrganizationsQuery();

         if(text != null && !text.equals(""))
             query.filterByOrganizationName(text);

         if(studyId != null) 
             query.filterByStudyId(studyId);

         List<StudyOrganization> organizations = (List<StudyOrganization>)organizationDao.search(query);
         return organizations;
    }

 	/* (non-Javadoc)
	  * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#restrictBySubnames(java.lang.String[], boolean)
	  */
	 public List<Organization> restrictBySubnames(final String[] subnames, boolean skipFiltering, boolean filterByRetired) {
 		String text = subnames[0];
    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
        query.setFiltered(skipFiltering);
        if (filterByRetired) query.filterByRetiredStatus(false);
        query.filterByOrganizationTypesOrNull(new String[] {"CCR", "CLC", "NCP"});
        List<Organization> localOrganizations;
 		localOrganizations = organizationDao.getBySubnames(query);

 		//get organizations from remote service
 		List<Organization> remoteOrganizations = null;
 		if (coppaModeForAutoCompleters) {
	    	Organization searchCriteria = new RemoteOrganization();
	    	searchCriteria.setNciInstituteCode(subnames[0]);
	    	remoteOrganizations = organizationDao.getRemoteOrganizations(searchCriteria);
 		} else { 			
	    	return localOrganizations;
 		}
 		return mergeOrgs (localOrganizations,remoteOrganizations);
 	}
 	
 	/**
	  * Merge orgs.
	  *
	  * @param localList the local list
	  * @param remoteList the remote list
	  * @return the list
	  */
	 private List<Organization> mergeOrgs(List<Organization> localList , List<Organization> remoteList) {
    		for (Organization remoteOrganization:remoteList) {
        		Organization org = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
        		if (org == null ) {
        			createOrUpdate(remoteOrganization);
            		localList.add(remoteOrganization);
            	} else {
            		if (!localList.contains(org)) {
        				localList.add(org);
        			}
             	}
        	}
    	return localList;
	}

	/**
	 * Sets the organization converter dao.
	 *
	 * @param organizationConverterDao the new organization converter dao
	 */
	public void setOrganizationConverterDao(
			OrganizationConverterDao organizationConverterDao) {
		this.organizationConverterDao = organizationConverterDao;
	}

	/**
	 * Sets the coppa mode for auto completers.
	 *
	 * @param coppaModeForAutoCompleters the new coppa mode for auto completers
	 */
	public void setCoppaModeForAutoCompleters(boolean coppaModeForAutoCompleters) {
		this.coppaModeForAutoCompleters = coppaModeForAutoCompleters;
	}
	
	/**
	 * This method will fetch all the organizations in caAERS database.
	 *
	 * @return the all organizations
	 */
	public List<Organization> getAllOrganizations() {
		return organizationDao.getAll();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getAllNciInstitueCodes()
	 */
	public List<Organization> getAllNciInstitueCodes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#getById(int)
	 */
	public Organization getById(int id) {
		return organizationDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository#evict(gov.nih.nci.cabig.caaers.domain.Organization)
	 */
	public void evict(Organization org) {
		organizationDao.evict(org);		
	}


}