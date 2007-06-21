package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;


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
public class SiteDao extends GridIdentifiableDao<Site> {
    public Class<Site> domainClass() {
        return Site.class;
    }

    public Site getDefaultSite() {
        List<Site> results = getHibernateTemplate().find("from Site where name=?", Site.DEFAULT_SITE_NAME);
        if (results.size() == 0) {
            log.debug("No default site in database (should have a site named '" + Site.DEFAULT_SITE_NAME + "')");
        }
        return results.get(0);
    }

    public List<Site> getAll() {
        return getHibernateTemplate().find("from Site");
    }    
    
    public Site getByName(String name) {
        List<Site> results = getHibernateTemplate().find("from Site where name= ?", name);
        return results.size() > 0 ? results.get(0) : null;
    }
    
}
