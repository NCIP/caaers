package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> {

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
	public void save(ResearchStaff researchStaff) {
		getHibernateTemplate().saveOrUpdate(researchStaff);
	}

	public List<ResearchStaff> getBySubnames(String[] subnames, int site) {

		return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS, SUBSTRING_MATCH_PROPERTIES,
				EXACT_MATCH_PROPERTIES);
	}
}