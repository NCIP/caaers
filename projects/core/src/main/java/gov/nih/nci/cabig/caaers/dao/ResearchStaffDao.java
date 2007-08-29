package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> implements
		MutableDomainObjectDao<ResearchStaff> {

	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	private static final List<Object> EXTRA_PARAMS = Collections.emptyList();

	public List<ResearchStaff> getAll() {
		return getHibernateTemplate().find("from ResearchStaff");
	}

	@Override
	public Class<ResearchStaff> domainClass() {
		return ResearchStaff.class;
	}

	@Transactional(readOnly = false)
	public void save(final ResearchStaff researchStaff) {
		getHibernateTemplate().saveOrUpdate(researchStaff);
	}

	public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {

		return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS, SUBSTRING_MATCH_PROPERTIES,
				EXACT_MATCH_PROPERTIES);
	}
}