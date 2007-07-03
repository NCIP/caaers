package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class OrganizationDao extends GridIdentifiableDao<Organization> {
	
	private static final List<String> SUBSTRING_MATCH_PROPERTIES
    = Arrays.asList("name");
	private static final List<String> EXACT_MATCH_PROPERTIES
    = Collections.emptyList();
	
    public Class<Organization> domainClass() {
        return Organization.class;
    }

    public Organization getDefaultOrganization() {
        List<Organization> results = getHibernateTemplate().find("from Organization where name=?", Organization.DEFAULT_SITE_NAME);
        if (results.size() == 0) {
            log.debug("No default site in database (should have a organization named '" + Organization.DEFAULT_SITE_NAME + "')");
        }
        return results.get(0);
    }

    public List<Organization> getAll() {
        return getHibernateTemplate().find("from Organization");
    }    
    
    public Organization getByName(String name) {
        List<Organization> results = getHibernateTemplate().find("from Organization where name= ?", name);
        return results.size() > 0 ? results.get(0) : null;
    }
    
    public List<Organization> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
}
