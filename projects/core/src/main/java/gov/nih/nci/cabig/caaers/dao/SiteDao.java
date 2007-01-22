package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;


import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 */
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
    
    public void save(Site site) {
        getHibernateTemplate().saveOrUpdate(site);
    }
    
    public Site getByName(String name) {
        List<Site> results = getHibernateTemplate().find("from Site where name= ?", name);
        return results.get(0);
    }
    
}
