package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class will provide CURD operations for _User doamin object. 
 */
@Transactional(readOnly = true)
public class _UserDao extends CaaersDao<_User> implements MutableDomainObjectDao<_User>{
    /**
     * The domain class this Dao represents in this case  _User
     * @return
     */
	@Override
	public Class<_User> domainClass() {
		return _User.class;
	}

    /**
     * Will return the _User identified by the loginName
     * @param loginName  - The user login name
     * @return _User
     */
	@SuppressWarnings("unchecked")
	public _User getByLoginName(String loginName){
        List<_User> results = getHibernateTemplate().find("from _User where loginName= ?", loginName);
        return results.size() > 0 ? results.get(0) : null;
	}

    /**
     * Will save the _User object
     * @param _user  - The _User to save
     */
	@Transactional(readOnly = false)
	public void save(_User _user){
		getHibernateTemplate().saveOrUpdate(_user);
	}

    /**
     * Will fetch the user based on the gridId
     * @param arg0
     * @return
     */
	public _User getByGridId(String arg0) {
		return null;
	}
}
