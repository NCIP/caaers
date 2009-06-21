package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Search domain
 * object.
 * 
 * @author Sameer Sawant
 */

@Transactional(readOnly = true)
public class SearchDao extends GridIdentifiableDao<Search> implements MutableDomainObjectDao<Search>{

	/**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<Search> domainClass() {
        return Search.class;
    }
    
    /**
     * Save or update the searches in the db.
     * 
     * @param The search.
     */
    @Transactional(readOnly = false)
    public void save(final Search search) {
        getHibernateTemplate().saveOrUpdate(search);
      
    }
	
    
    /**
     * Get the list of Searches based on the loginId.
     * This is needed to display all the saved searches on the query page.
     */
    @SuppressWarnings("unchecked")
	public List<Search> getByLogin(String loginId) {
        List<Search> results = getHibernateTemplate().find("from Search where login_id= ? order by created_date desc", loginId);
        return results;
    }
    
    /**
     * Get the list of Searches based on the loginId and name.
     * This is needed to check if another search with a same name already exists.
     */
    @SuppressWarnings("unchecked")
    public List<Search> getByLoginAndName(String loginId, String name){
    	List<Search> results = getHibernateTemplate().find("from Search where login_id=? and name= ?",
    			new Object[] { loginId , name});
    	return results;
    }
	
    /**
     * Delete search based on the loginId and searchName
     * 
     * @param searchName
     *                The name of the search to be deleted
     * @param loginId
     * 				  The loginId of the user who saved the search.
     * @return True if report successfully deleted. False otherwise.
     */
    @Transactional(readOnly = false)
    public boolean deleteByLoginIdAndName(String searchName, String loginId) {
        int count = getHibernateTemplate().bulkUpdate("delete Search s where s.name = ? and s.loginId = ?",
                        new Object[] { searchName, loginId });
        return count >= 1;
    }
}