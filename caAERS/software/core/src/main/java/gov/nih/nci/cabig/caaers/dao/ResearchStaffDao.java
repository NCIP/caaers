package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
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
    	if(researchStaff.getId() == null && researchStaff.getNciIdentifier() != null  && researchStaff instanceof LocalResearchStaff){
    		ResearchStaff rs = new RemoteResearchStaff();
    		rs.setNciIdentifier(researchStaff.getNciIdentifier());
    		List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(rs);
    		if(remoteResearchStaffs != null && remoteResearchStaffs.size() > 0){
    			logger.error("ResearchStaff exists in external system");
    			throw new RuntimeException("ResearchStaff exists in external system");
    		}
    	}
    	getHibernateTemplate().saveOrUpdate(researchStaff);
    }
    
    /**
     * This method queries the caAERS DB to get all the matching ResearchStaff for the given query.
     * @param query
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
	public List<ResearchStaff> getLocalResearchStaff(final ResearchStaffQuery query){
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
    @SuppressWarnings("unchecked")
	public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){

    	List<ResearchStaff> remoteResearchStaffs = (List)remoteSession.find(researchStaff); 
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
    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<ResearchStaff>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);
                }
                return hiberanteQuery.list();
            }

        });

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

    	List<ResearchStaff> researchStaffs =  findBySubname(subnames,
                        SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    	return researchStaffs;
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

    	List<ResearchStaff> researchStaffs = findBySubname(subnames,
        		NCIIDENTIFIER_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    	return researchStaffs;
    }

    /**
     * Get the user who has specified email address.
     * 
     * @param loginId
     *                The loginId of the user.
     * @return The user.
     */
    @SuppressWarnings("unchecked")
	public ResearchStaff getByLoginId(String loginId) {
        List<ResearchStaff> results = getHibernateTemplate().find(
                        "from ResearchStaff where loginId= ?", loginId);
        return results.size() > 0 ? results.get(0) : null;
    }

    @SuppressWarnings("unchecked")
	public ResearchStaff getByEmailAddress(String email) {
        List<ResearchStaff> results = getHibernateTemplate().find(
                        "from ResearchStaff where emailAddress= ?", email);
        return results.size() > 0 ? results.get(0) : null;
    }
    
	public void setRemoteSession(RemoteSession remoteSession) {
		this.remoteSession = remoteSession;
	}

}