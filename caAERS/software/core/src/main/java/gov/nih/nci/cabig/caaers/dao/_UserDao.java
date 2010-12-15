package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class _UserDao extends CaaersDao<_User> implements MutableDomainObjectDao<_User>{

	@Override
	public Class<_User> domainClass() {
		return _User.class;
	}

	@SuppressWarnings("unchecked")
	public _User getByLoginName(String loginName){
        List<_User> results = getHibernateTemplate().find("from _User where loginName= ?", loginName);
        return results.size() > 0 ? results.get(0) : null;
	}
	
	@Transactional(readOnly = false)
	public void save(_User _user){
		getHibernateTemplate().saveOrUpdate(_user);
	}

	public _User getByGridId(String arg0) {
		return null;
	}
}
