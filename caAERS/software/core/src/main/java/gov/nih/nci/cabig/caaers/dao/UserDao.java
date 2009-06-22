package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the User domain object.
 * 
 * @author Jared Flatow
 */
@Transactional(readOnly = true)
public class UserDao extends GridIdentifiableDao<User> implements MutableDomainObjectDao<User> {

	
	private ResearchStaffDao researchStaffDao;
	private InvestigatorDao investigatorDao;
	
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<User> domainClass() {
        return User.class;
    }

    /**
     * Save or update the user in the db.
     * 
     * @param The
     *                user.
     */
    @Transactional(readOnly = false)
    public void save(final User user) {
    	if(user instanceof ResearchStaff){
    		researchStaffDao.save((ResearchStaff)user);
    	}
    	if(user instanceof Investigator){
    		investigatorDao.save((Investigator)user);
    	}
    }

    /**
     * Get the user who has specified email address.
     * 
     * @param emailAddress
     *                The email address of the user.
     * @return The user.
     */
    public User getByEmailAddress(String emailAddress) {
    	
    	User user = null;
    	
    	List<ResearchStaff> rsList = getHibernateTemplate().find(
                "from ResearchStaff where emailAddress= ?", emailAddress);
    	
    	if(rsList != null && rsList.size() > 0){
    		user = rsList.get(0);
    		return user;
    	}
    	
        List<Investigator> invList = getHibernateTemplate().find(
                        "from Investigator where emailAddress= ?", emailAddress);
        if(invList != null && invList.size() > 0){
        	user = invList.get(0);
        	return user;
        }
        
        return user;
    }
    
    /**
     * Get the user who has specified email address.
     * 
     * @param loginId
     *                The loginId of the user.
     * @return The user.
     */
    public User getByLoginId(String loginId) {
    	
    	User user = null;
    	
    	List<ResearchStaff> rsList = getHibernateTemplate().find(
                "from ResearchStaff where loginId= ?", loginId);
    	
    	if(rsList != null && rsList.size() > 0){
    		user = rsList.get(0);
    		return user;
    	}
        List<Investigator> invList = getHibernateTemplate().find(
                        "from Investigator where loginId= ?", loginId);
        if(invList != null && invList.size() > 0){
        	user = invList.get(0);
        	return user;
        }
        return user;
    }
    
    public User getById(int id){
    	User user = null;
    	user = researchStaffDao.getById(id);
    	if(user != null){
    		return user;
    	}
    	user = investigatorDao.getById(id);
    	if(user != null){
    		return user;
    	}
    	return user;
    }
    
	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
    
}
