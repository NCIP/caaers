/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.dao.query.PasswordPolicyQuery;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jared Flatow
 */
@Transactional(readOnly = true)
public class PasswordPolicyDao extends GridIdentifiableDao<PasswordPolicy> implements
                MutableDomainObjectDao<PasswordPolicy> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<PasswordPolicy> domainClass() {
        return PasswordPolicy.class;
    }

    /**
     * Save or update the password policy in the db.
     * 
     * @param passwordPolicy .
     */
    @Transactional(readOnly = false)
    public void save(final PasswordPolicy passwordPolicy) {
        getHibernateTemplate().saveOrUpdate(passwordPolicy);
    }

    @Transactional(readOnly = true)
    public List<PasswordPolicy> getPasswordPolicy(){
        PasswordPolicyQuery passwordPolicyQuery = new PasswordPolicyQuery();
        return (List<PasswordPolicy>)search(passwordPolicyQuery);
    }

}
