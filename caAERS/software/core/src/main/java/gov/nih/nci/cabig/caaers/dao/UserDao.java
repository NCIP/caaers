package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class will provide CURD operations for User doamin object.
 */
@Transactional(readOnly = true)
public class UserDao extends CaaersDao<User> implements MutableDomainObjectDao<User>{
    /**
     * The domain class this Dao represents in this case  User
     * @return
     */
	@Override
	public Class<User> domainClass() {
		return User.class;
	}

    /**
     * Will return the User identified by the loginName
     * @param loginName  - The user login name
     * @return User
     */
	@SuppressWarnings("unchecked")
	public User getByLoginName(String loginName){
        List<User> results = getHibernateTemplate().find("from User where loginName= ?", loginName);
        return results.size() > 0 ? results.get(0) : null;
	}

    /**
     * Will save the User object
     * @param _user  - The User to save
     */
	@Transactional(readOnly = false)
	public void save(User _user){
		getHibernateTemplate().saveOrUpdate(_user);
	}

    /**
     * Will fetch the user based on the gridId
     * @param arg0
     * @return
     */
	public User getByGridId(String arg0) {
		return null;
	}
}
