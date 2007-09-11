package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Priyatam
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
@Transactional(readOnly = true)
public class StudyDao extends GridIdentifiableDao<Study> implements MutableDomainObjectDao<Study> {

	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("shortTitle", "longTitle");

	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	private static final List<String> EXACT_MATCH_UNIQUE_PROPERTIES = Arrays.asList("longTitle");

	private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

	private static final List<String> EXACT_MATCH_TITLE_PROPERTIES = Arrays.asList("shortTitle");

	private static final String JOINS = "join o.identifiers as identifier "
			+ "join o.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiersInternal as pIdentifier";

	private static final String QUERY_BY_SHORT_TITLE = "select s from " + Study.class.getName()
			+ " s where shortTitle = :st";

	@Override
	public Class<Study> domainClass() {
		return Study.class;
	}

	@SuppressWarnings("unchecked")
	public List<Study> getAllStudies() {
		return getHibernateTemplate().find("from Study");
	}

	/**
	 * //TODO - Refactor this code with Hibernate Detached objects !!!
	 *
	 * This is a hack to load all collection objects in memory. Useful for editing a Study when you know you will be needing all collections
	 * To avoid Lazy loading Exception by Hibernate, a call to .size() is done for each collection
	 * @param id
	 * @return Fully loaded Study
	 */
	public Study getStudyDesignById(final int id) {
		Study study = (Study) getHibernateTemplate().get(domainClass(), id);
		initialize(study);

		// now select the therapies types
		if (study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION) != null) {
			study.setDrugAdministrationTherapyType(Boolean.TRUE);
		}
		if (study.getStudyTherapy(StudyTherapyType.DEVICE) != null) {
			study.setDeviceTherapyType(Boolean.TRUE);
		}
		if (study.getStudyTherapy(StudyTherapyType.RADIATION) != null) {
			study.setRadiationTherapyType(Boolean.TRUE);
		}
		if (study.getStudyTherapy(StudyTherapyType.SURGERY) != null) {
			study.setSurgeryTherapyType(Boolean.TRUE);
		}
		if (study.getStudyTherapy(StudyTherapyType.BEHAVIORAL) != null){
			study.setBehavioralTherapyType(Boolean.TRUE);
		}
		return study;
	}

	public Study initialize(final Study study) {
		HibernateTemplate ht = getHibernateTemplate();
		ht.initialize(study.getIdentifiers());
		ht.initialize(study.getStudyOrganizations());
		for (StudyOrganization studyOrg : study.getStudyOrganizations()) {
			if (studyOrg == null) {
				continue;
			}
			ht.initialize(studyOrg.getStudyInvestigatorsInternal());
			ht.initialize(studyOrg.getStudyPersonnelsInternal());
		}
		ht.initialize(study.getMeddraStudyDiseases());
		ht.initialize(study.getCtepStudyDiseases());
		ht.initialize(study.getStudyAgentsInternal());
		ht.initialize(study.getStudyAmendmentsInternal());
		ht.initialize(study.getStudyTherapies());
		ht.initialize(study.getTreatmentAssignmentsInternal());

		for (StudyAgent sa : study.getStudyAgents()) {
			ht.initialize(sa.getStudyAgentINDAssociationsInternal());
		}
		return study;
	}

	@Transactional(readOnly = false)
	public void save(final Study study) {
		getHibernateTemplate().saveOrUpdate(study);
	}

	public List<Study> getBySubnames(final String[] subnames) {
		return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}

	public List<Study> getByCriteria(final String[] subnames, final List<String> subStringMatchProperties) {
		return findBySubname(subnames, null, null, subStringMatchProperties, null, JOINS);
	}

	public List<Study> matchStudyByParticipant(final Integer participantId, final String text) {

		String joins = " join o.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p ";

		List<Object> params = new ArrayList<Object>();
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(
				" o ").append(joins);

		queryBuf.append(" where ");
		queryBuf.append("p.id = ?");
		params.add(participantId);

		queryBuf.append(" and ( ");
		queryBuf.append("LOWER(").append("o.shortTitle").append(") LIKE ?");
		params.add('%' + text.toLowerCase() + '%');

		queryBuf.append(" or ");
		queryBuf.append("LOWER(").append("o.longTitle").append(") LIKE ? ) ");
		params.add('%' + text.toLowerCase() + '%');

		log.debug("::: " + queryBuf.toString());
		return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
	}

	public List<Study> searchStudy(final Map props) throws ParseException {

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(
				" o ").append(JOINS);

		/*
		 * if (true) { queryBuf.append(firstClause ? " where " : " and "); queryBuf.append(" ss.class = StudySite "); firstClause = false; }
		 */

		if (props.get("studyIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("identifier.value").append(") LIKE ?");
			String p = (String) props.get("studyIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("studyShortTitle") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("o.shortTitle").append(") LIKE ?");
			String p = (String) props.get("studyShortTitle");
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

	/**
	 * @param subnames a set of substrings to match
	 * @return a list of participants such that each entry in <code>subnames</code> is a case-insensitive substring match of the
	 *         participant's name or other identifier
	 */
	@SuppressWarnings("unchecked")
	public List<Study> getByUniqueIdentifiers(final String[] subnames) {
		return findBySubname(subnames, EMPTY_PROPERTIES, EXACT_MATCH_UNIQUE_PROPERTIES);
	}

	public Study getByIdentifier(final Identifier identifier) {
		return findByIdentifier(identifier);
	}

	/**
	 * This will do an exact match on the <code>shortTitle</code>, and will return the first available Study. Note:- Biz rule should be
	 * made that short title is unique.
	 */
	public Study getByShortTitle(final String shortTitle) {
		List<Study> studies = findBySubname(new String[] { shortTitle }, null, EXACT_MATCH_TITLE_PROPERTIES);
		if (studies != null && studies.size() > 0) {
			return studies.get(0);
		}
		return null;
	}

	@Override
	// TODO - Need to refactor the below into CaaersDao along with identifiers
	public List<Study> searchByExample(final Study study, final boolean isWildCard) {
		Example example = Example.create(study).excludeZeroes().ignoreCase();
		Criteria studyCriteria = getSession().createCriteria(Study.class);

		if (isWildCard) {
			example.excludeProperty("multiInstitutionIndicator");
			example.excludeProperty("doNotUse").enableLike(MatchMode.ANYWHERE);
			studyCriteria.add(example);
			if (study.getIdentifiers().size() > 0) {
				studyCriteria.createCriteria("identifiers").add(
						Restrictions.like("value", study.getIdentifiers().get(0).getValue() + "%"));
			}
			if (study.getStudyOrganizations().size() > 0) {
				if (study.getStudyOrganizations().get(0) instanceof StudySite) {
					studyCriteria.createCriteria("studyOrganizations").add(
							Restrictions.eq("organization.id", study.getStudyOrganizations().get(0).getOrganization()
									.getId())).add(Restrictions.sqlRestriction("type='SST'"));
				}
				else {
					studyCriteria.createCriteria("studyOrganizations").add(
							Restrictions.eq("organization.id", study.getStudyOrganizations().get(0).getOrganization()
									.getId()));

				}

			}
			return studyCriteria.list();
		}
		return studyCriteria.add(example).list();
	}
}
