package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jared Flatow
 */
@Transactional(readOnly=true)
public class UserDao extends GridIdentifiableDao<User> implements MutableDomainObjectDao<User> {
    
    @Override
    public Class<User> domainClass() {
	return User.class;
    }

    @Transactional(readOnly=false)
    public void save(final User user) {
	getHibernateTemplate().saveOrUpdate(user);
    }

    public User getByEmailAddress(String emailAddress) {
	List<User> results = getHibernateTemplate().find("from ResearchStaff where emailAddress= ?", emailAddress);
	return results.size() > 0 ? results.get(0) : null;
    }
}
