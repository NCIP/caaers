package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> implements
		MutableDomainObjectDao<ResearchStaff> {

	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	private static final List<Object> EXTRA_PARAMS = Collections.emptyList();

	@Override
	public Class<ResearchStaff> domainClass() {
		return ResearchStaff.class;
	}

	@Transactional(readOnly = false)
	public void save(final ResearchStaff researchStaff) {
		getHibernateTemplate().saveOrUpdate(researchStaff);
	}

	@SuppressWarnings( { "unchecked" })
	public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
		String queryString = query.getQueryString();
		log.debug("::: " + queryString.toString());
		return (List<ResearchStaff>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
				Map<String, Object> queryParameterMap = query.getParameterMap();
				for (String key : queryParameterMap.keySet()) {
					Object value = queryParameterMap.get(key);
					hiberanteQuery.setParameter(key, value);

				}
				return hiberanteQuery.list();
			}

		});

	}

	public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {

		return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS, SUBSTRING_MATCH_PROPERTIES,
				EXACT_MATCH_PROPERTIES);
	}

}