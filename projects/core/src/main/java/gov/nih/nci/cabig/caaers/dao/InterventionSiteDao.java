package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.InterventionSite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class InterventionSiteDao extends CaaersDao<InterventionSite> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    @Override
	public Class<InterventionSite> domainClass() {
        return InterventionSite.class;
    }
    @Override
    public InterventionSite getById(int id) {
    	return super.getById(id);
    }
    public List<InterventionSite> getBySubname(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }

    @SuppressWarnings("unchecked")
    public List<InterventionSite> getAll() {
        return getHibernateTemplate().find("from InterventionSite");
    }

}
