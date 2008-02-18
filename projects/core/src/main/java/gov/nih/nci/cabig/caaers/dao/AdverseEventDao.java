package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class AdverseEventDao extends CaaersDao<AdverseEvent> {
	/**
	 * Get the Class representation of the domain object that this DAO is representing.
	 * @return Class representation of the domain object that this DAO is representing.
	 */
	@Override
	public Class<AdverseEvent> domainClass() {
		return AdverseEvent.class;
	}

	private static final String JOINS = " join o.adverseEventTerm as aeCtcTerm join aeCtcTerm.term as ctcTerm join ctcTerm.category as ctcCategory  "
			+ "  join o.report as expeditedReport "
			+ " join expeditedReport.assignment as spa join spa.studySite as studySite join studySite.study as study join study.identifiers as identifier"
			+ " join spa.participant as p join p.identifiersInternal as pIdentifier";

	/**
	 * Get list of Adverse events with supplied criteria.
	 * @param subnames The name fragments to search on.
	 * @param subStringMatchProperties A list of properties of the implementing object which should be matched as case-insensitive
	 *            substrings
	 * @see findBySubname {@link CaaersDao#findBySubname(String[], String, List, List, List)}
	 * @return
	 */
	public List<AdverseEvent> getByCriteria(final String[] subnames, final List<String> subStringMatchProperties) {
		return findBySubname(subnames, null, null, subStringMatchProperties, null, JOINS);
	}
	/**
	 * Save the Adverse Event.
	 * @param event The event to be saved.
	 * 
	 */
	@Transactional(readOnly = false)
	public void save(final AdverseEvent event) {
		getHibernateTemplate().saveOrUpdate(event);
	}
	/**
	 * Search for Adverse events with the supplied property list
	 * @param props The criteria for searching Adverse Events
	 * @return The list of Adverse events that match the criteria
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked" })
	public List<AdverseEvent> searchAdverseEvents(final Map props) throws ParseException {

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(
				" o ").append(JOINS);

		if (props.get("notimplemented") != null) {
			// queryBuf.append(firstClause ? " where " : " and ");
			// queryBuf.append("LOWER(").append("sIdentifier.value").append(") LIKE ?");
			// String p = (String)props.get("studyIdentifier");
			// params.add('%' + p.toLowerCase() + '%');
			// firstClause = false;
		}

		if (true) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" aeCtcTerm.class = AdverseEventCtcTerm	 ");
			firstClause = false;
		}

		if (props.get("studyIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("identifier.value").append(") LIKE ?");
			String p = (String) props.get("studyIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}

		if (props.get("studyShortTitle") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("study.shortTitle").append(") LIKE ?");
			String p = (String) props.get("studyShortTitle");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("ctcCategory") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcCategory.name").append(") LIKE ?");
			String p = (String) props.get("ctcCategory");
			params.add(p.toLowerCase());
			firstClause = false;
		}
		if (props.get("ctcTerm") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcTerm.term").append(") LIKE ?");
			String p = (String) props.get("ctcTerm");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("ctcMeddra") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcTerm.ctepCode").append(") LIKE ?");
			String p = (String) props.get("ctcMeddra");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("grade") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("grade_code").append(") LIKE ?");
			String p = (String) props.get("grade");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}

		if (props.get("participantIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("pIdentifier.value").append(") LIKE ?");
			String p = (String) props.get("participantIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantFirstName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.firstName").append(") LIKE ?");
			String p = (String) props.get("participantFirstName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantLastName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.lastName").append(") LIKE ?");
			String p = (String) props.get("participantLastName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantEthnicity") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.ethnicity").append(") LIKE ?");
			String p = (String) props.get("participantEthnicity");
			params.add(p.toLowerCase());
			firstClause = false;
		}
		if (props.get("participantGender") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.gender").append(") LIKE ?");
			String p = (String) props.get("participantGender");
			params.add(p.toLowerCase());
			firstClause = false;
		}

		if (props.get("participantDateOfBirth") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" p.dateOfBirth").append(" = ? ");
			String p = (String) props.get("participantDateOfBirth");
			params.add(stringToDate(p));
			firstClause = false;
		}

		log.debug("::: " + queryBuf.toString());
		getHibernateTemplate().setMaxResults(CaaersDao.DEFAULT_MAX_RESULTS_SIZE);
		return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
	}
}
