package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly = true)
public class InvestigatorDao extends GridIdentifiableDao<Investigator> implements MutableDomainObjectDao<Investigator> {
	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	@Override
	public Class<Investigator> domainClass() {
		return Investigator.class;
	}

	@Transactional(readOnly = false)
	public void save(final Investigator investigator) {
		getHibernateTemplate().saveOrUpdate(investigator);
	}

	public List<Investigator> getBySubnames(final String[] subnames) {
		return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}

	/**
	 * Gets the Investigator by id. This initialize the Investigator and load all the objects.
	 * 
	 * @param id the id
	 * 
	 * @return the Investigator by id
	 */
	public Investigator getInvestigatorById(final int id) {
		Investigator investigator = (Investigator) getHibernateTemplate().get(domainClass(), id);
		initialize(investigator);

		return investigator;
	}

	public Investigator initialize(final Investigator investigator) {
		HibernateTemplate ht = getHibernateTemplate();
		ht.initialize(investigator.getSiteInvestigatorsInternal());

		return investigator;
	}

	@SuppressWarnings("unchecked")
	public List<Investigator> searchInvestigator(final InvestigatorQuery query) {
		String queryString = query.getQueryString();
		log.debug("::: " + queryString.toString());
		return (List<Investigator>) getHibernateTemplate().execute(new HibernateCallback() {

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

}