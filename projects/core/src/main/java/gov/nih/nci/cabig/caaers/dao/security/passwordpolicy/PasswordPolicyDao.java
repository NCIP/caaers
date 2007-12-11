package gov.nih.nci.cabig.caaers.dao.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jared Flatow
 */
@Transactional(readOnly=true)
public class PasswordPolicyDao extends GridIdentifiableDao<PasswordPolicy> implements MutableDomainObjectDao<PasswordPolicy> {

    @Override
    public Class<PasswordPolicy> domainClass() {
	return PasswordPolicy.class;
    }
    
    @Transactional(readOnly=false)
    public void save(final PasswordPolicy passwordPolicy) {
	getHibernateTemplate().saveOrUpdate(passwordPolicy);
    }  
}
