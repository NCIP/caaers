package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

import com.semanticbits.coppa.infrastructure.RemoteSession;

/**
 * This class implements the Data access related operations for the ResearchStaff domain object.
 * 
 * @author Kulasekaran
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> implements
                MutableDomainObjectDao<ResearchStaff> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName",
                    "lastName");
    
    private static final List<String> NCIIDENTIFIER_MATCH_PROPERTIES = Arrays.asList("nciIdentifier");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    private static final List<Object> EXTRA_PARAMS = Collections.emptyList();
    
    private RemoteSession remoteSession;
    
    private OrganizationDao organizationDao;

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<ResearchStaff> domainClass() {
        return ResearchStaff.class;
    }

    /**
     * Save or update the research staff in the db.
     * 
     * @param The
     *                research staff.
     */
    @Transactional(readOnly = false)
    public void save(final ResearchStaff researchStaff) {
    	if(researchStaff.getId() == null && researchStaff instanceof LocalResearchStaff){
    		List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(researchStaff);
    		if(remoteResearchStaffs != null && remoteResearchStaffs.size() > 0){
    			logger.error("ResearchStaff exists in external system");
    			throw new RuntimeException("ResearchStaff exists in external system");
    		}
    	}else{
    		getHibernateTemplate().saveOrUpdate(researchStaff);
    	}
    }
    
    @SuppressWarnings( { "unchecked" })
    @Transactional(readOnly = false)
    public List<ResearchStaff> findResearchStaff(final ResearchStaffQuery query) {
    	List<ResearchStaff> researchStaffs = searchResearchStaff(query);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    	//return searchResearchStaff(query);
    }
    
    /**
     * This method queries the caAERS DB to get all the matching ResearchStaff for the given query.
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
	public List<ResearchStaff> getResearchStaff(final ResearchStaffQuery query){
    	String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        List<ResearchStaff> researchStaffs = (List<ResearchStaff>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException,
                            SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);

                }
                return hiberanteQuery.list();
            }

        });
        return researchStaffs;
    }
    
    /**
     * This method queries the external system to fetch all the matching ResearchStaff
     * @param researchStaff
     * @return
     */
    public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	searchCriteria.setOrganization(researchStaff.getOrganization());
    	searchCriteria.setFirstName(researchStaff.getFirstName());
    	searchCriteria.setLastName(researchStaff.getLastName());
    	searchCriteria.setEmailAddress(researchStaff.getEmailAddress());
    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(searchCriteria); 
    	return remoteResearchStaffs;
    }
    
    
    
    /**
     * Search for research staffs using query.
     * 
     * @param query
     *                The query used to search for research staffs
     * @return The list of research staffs.
     */
    @SuppressWarnings( { "unchecked" })
    @Transactional(readOnly = false)
    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
    	//Get all the RS from caAERS DB
        List<ResearchStaff> researchStaffs = getResearchStaff(query);
        
        //Get all the RS from External System
        ResearchStaff searchCriteria = new RemoteResearchStaff();
    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(searchCriteria); 
    	
    	//Merge and Return
    	return merge (researchStaffs,remoteResearchStaffs);
    }

    /**
     * Get the list of research staffs matching the name fragments and belonging to specified site.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param site
     *                The organization ID of the site.
     * @return List of matching research staffs.
     */
    @Transactional(readOnly = false)
    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {

    	List<ResearchStaff> researchStaffs =  findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	Organization org = organizationDao.getById(site);
    	searchCriteria.setOrganization(org);
    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    }
    
    /**
     * Get the list of research staffs matching the NciIdentifier and belonging to specified site.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param site
     *                The organization ID of the site.
     * @return List of matching research staffs.
     */
    @Transactional(readOnly = false)
    public List<ResearchStaff> getByNciIdentifier(final String[] subnames, final int site) {

    	List<ResearchStaff> researchStaffs = findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
        		NCIIDENTIFIER_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
        ResearchStaff searchCriteria = new RemoteResearchStaff();
    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    }

	private List<ResearchStaff> merge(List<ResearchStaff> localList , List<ResearchStaff> remoteList) {
		for (ResearchStaff remoteResearchStaff:remoteList) {
			ResearchStaff rs = getByEmailAddress(remoteResearchStaff.getEmailAddress());
    		if (rs == null ) {
    			// look for his organization ;
    			Organization remoteOrganization = remoteResearchStaff.getOrganization();
    			//if associated organization is not there in our DB
    			Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    			if (organization == null) {
    				// TODO : need to get the remote organozation from coppa and save it ..
    				organizationDao.save(remoteOrganization);
    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    			} 
    			remoteResearchStaff.setOrganization(organization);
        		save(remoteResearchStaff);
        		rs = getByEmailAddress(remoteResearchStaff.getEmailAddress());
        		rs.setFirstName(remoteResearchStaff.getFirstName());
        		rs.setLastName(remoteResearchStaff.getLastName());
        		rs.setMiddleName(remoteResearchStaff.getMiddleName());
        		localList.add(rs);
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(rs)) {
        			localList.add(rs);
        		}
        	}
    	}
		
		return localList;
	}
	
    /**
     * Get the user who has specified email address.
     * 
     * @param loginId
     *                The loginId of the user.
     * @return The user.
     */
    public ResearchStaff getByLoginId(String loginId) {
        List<ResearchStaff> results = getHibernateTemplate().find(
                        "from ResearchStaff where loginId= ?", loginId);
        return results.size() > 0 ? results.get(0) : null;
    }

    public ResearchStaff getByEmailAddress(String email) {
        List<ResearchStaff> results = getHibernateTemplate().find(
                        "from ResearchStaff where emailAddress= ?", email);
        return results.size() > 0 ? results.get(0) : null;
    }
    
	public void setRemoteSession(RemoteSession remoteSession) {
		this.remoteSession = remoteSession;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}