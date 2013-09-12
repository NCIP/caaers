/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import com.semanticbits.coppa.infrastructure.RemoteSession;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationFromStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class implements the Data access related operations for the Organization domain object.
 * 
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 * @author Monish Dombla
 */
@Transactional(readOnly = false)
public class OrganizationDao extends GridIdentifiableDao<Organization> implements
                MutableDomainObjectDao<Organization> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name","nciInstituteCode");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();
    
    private RemoteSession remoteSession;
    
    public void initialize(Organization org){
    	getHibernateTemplate().initialize(org);
    }
    public void lock(Organization org){
    	getHibernateTemplate().lock(org, LockMode.NONE);
    }
    
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Organization> domainClass() {
        return Organization.class;
    }

    /**
     * Get the default organization.
     * 
     * @return The default organization.
     */
    @SuppressWarnings("unchecked")
	public Organization getDefaultOrganization() {
        List<Organization> results = getHibernateTemplate().find("from Organization where name=?",Organization.DEFAULT_SITE_NAME);
        if (results != null && !results.isEmpty()) {
        	return results.get(0);
        }
        return null;
    }

    //BJ : This method will save the organizations, as there is no transactions and it is intentional.
    public void saveAll(final List<Organization> organizations){
        StatelessSession session = getHibernateTemplate().getSessionFactory().openStatelessSession();
        try{
           for(Organization o : organizations) {
               log.info("processing :" + o.getNciInstituteCode());
               if(o.getId() == null){
                   if(log.isInfoEnabled()) log.info("Inserting " + o.getNciInstituteCode());
                   session.insert(o); 
               } else {
                   if(log.isInfoEnabled()) log.info("Updating " + o.getNciInstituteCode());
                   session.update(o);
               }
           }
        }catch (HibernateException e){
            throw getHibernateTemplate().convertHibernateAccessException(e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }


    /**
     * Get the list of all organizations.
     * 
     * @return return the list of organizations.
     */
    @SuppressWarnings("unchecked")
    public List<Organization> getAll() {
        //return getHibernateTemplate().find("from Organization");
    	List<Organization> organizations = getHibernateTemplate().find("from Organization");
    	return organizations;
    }

    /**
     * Get organization given organization name.
     * 
     * @param name
     *                The name of the organization.
     * @return The organization.
     */
    @SuppressWarnings("unchecked")
	public Organization getByName(final String name) {
        List<Organization> results = getHibernateTemplate().find("from Organization where name= ?", name);
        if (results != null && !results.isEmpty()) {
        	return results.get(0);
        }
        return null;
    }
    
    /**
     * Get organization given organization name.
     * 
     * @param name
     *                The name of the organization.
     * @return The organization.
     */
    @SuppressWarnings("unchecked")
	public Organization getByNCIcode(final String code) {
        List<Organization> results = getHibernateTemplate().find("from Organization where lower(nci_institute_code) = ?",code.toLowerCase());
        if (results != null && !results.isEmpty()) {
        	return results.get(0);
        }
        return null;
    }
    
    /**
     * Get the list of organizations matching the name fragments.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching organizations.
     */
    @SuppressWarnings("unchecked")
    public List<Organization> getBySubnames(final OrganizationQuery query) {
     	//List<Organization> organizations = findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    	List<Organization> organizations = (List<Organization>)super.search(query);
    	return organizations;

    }

    /**
     * Save or update the organization in the db.
     * 
     * @param organization
     *                the organization
     */
    @SuppressWarnings("unchecked")
    public void save(final Organization organization) {

    	if(StringUtils.isEmpty(organization.getName())){
    		throw new RuntimeException("Organization name should not be empty");
    	}
    	
    	if(organization.getId() == null && organization instanceof LocalOrganization){
    		Organization searchCriteria = new RemoteOrganization();
    		searchCriteria.setNciInstituteCode(organization.getNciInstituteCode());
    		List<Organization> remoteOrganizations = new ArrayList<Organization>();
			try {
				remoteOrganizations = (List)remoteSession.find(searchCriteria);
			} catch (Exception e) {
				log.warn("Error obtaining organizations from COPPA",e);
			}
			
    		if(remoteOrganizations != null && remoteOrganizations.size() > 0){
    			logger.error("Organization exists in external system");
    			throw new RuntimeException("Organization exists in external system");
    		}
    	}
    	if (organization instanceof RemoteOrganization && StringUtils.isBlank(organization.getName()) ) {
    		RemoteOrganization ro = (RemoteOrganization)remoteSession.load(RemoteOrganization.class, organization.getExternalId());
    		if(ro != null){
        		organization.setName(ro.getName());
        		organization.setCity(ro.getCity());
        		organization.setCountry(ro.getCountry());
    		}
    	}
    	getHibernateTemplate().saveOrUpdate(organization);
    }

    /**
     * This method is used in the Import Organizations flow. This will avoid calls to COPPA when importing Organization_Codes.txt from CTEP.
     * @param organization
     */
    public void saveImportedOrganization(Organization organization){
    	if(StringUtils.isEmpty(organization.getName())){
    		throw new RuntimeException("Organization name should not be empty");
    	}
    	getHibernateTemplate().saveOrUpdate(organization);
    }
    
    /**
     * Get list of organizations having study sites.
     * 
     * @return The list of organizations.
     */
    @SuppressWarnings("unchecked")
    public List<Organization> getOrganizationsHavingStudySites(final OrganizationFromStudySiteQuery query) {
    	return (List<Organization>) search(query);
        //return getHibernateTemplate().find("select distinct ss.organization from StudySite ss order by ss.organization.name");
    }
    
    /**
     * get list of organization which are study sites based on text matching and study identifier
     * @return The list of organizations
     */
    @SuppressWarnings("unchecked")
    public List<Organization> getApplicableOrganizationsFromStudySites(final OrganizationFromStudySiteQuery query){
    	return (List<Organization>) search(query);
    }

    
    /**
     * This method queries caAERS DB to get all the Organizations which have a matching
     * provided in the query.
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Organization> getLocalOrganizations(final OrganizationQuery query){
    	List localOrganizations = super.search(query);
    	return localOrganizations;
    }
    
    @SuppressWarnings("unchecked")
	public List<Organization> getRemoteOrganizations(Organization searchCriteria){
    	try {
			return (List)remoteSession.find(searchCriteria);
		} catch (Exception e) {
			log.warn("Error obtaining organizations from COPPA",e);
			return new ArrayList<Organization>();
		}
    }
    
   

	@Required
	public void setRemoteSession(RemoteSession remoteSession) {
		this.remoteSession = remoteSession;
	}

}
