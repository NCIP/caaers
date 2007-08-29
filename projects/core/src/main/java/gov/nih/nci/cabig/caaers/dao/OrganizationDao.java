package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class OrganizationDao extends GridIdentifiableDao<Organization> implements MutableDomainObjectDao<Organization> {

	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	@Override
	public Class<Organization> domainClass() {
		return Organization.class;
	}

	public Organization getDefaultOrganization() {
		List<Organization> results = getHibernateTemplate().find("from Organization where name=?",
				Organization.DEFAULT_SITE_NAME);
		if (results.size() == 0) {
			log.debug("No default site in database (should have a organization named '"
					+ Organization.DEFAULT_SITE_NAME + "')");
		}
		return results.get(0);
	}

	public List<Organization> getAll() {
		return getHibernateTemplate().find("from Organization");
	}

	public Organization getByName(final String name) {
		List<Organization> results = getHibernateTemplate().find("from Organization where name= ?", name);
		return results.size() > 0 ? results.get(0) : null;
	}

	public List<Organization> getBySubnames(final String[] subnames) {
		return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}

	/**
	 * Save or update the organization in the db.
	 * 
	 * @param organization the organization
	 */
	@Transactional(readOnly = false)
	public void save(final Organization organization) {
		getHibernateTemplate().saveOrUpdate(organization);
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationsHavingStudySites() {

		return getHibernateTemplate().find("select distinct ss.organization from StudySite ss");
	}
}