package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * This class implements the Data access related operations for the Study domain object.
 *
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
            + "join o.studyOrganizations as ss left outer join ss.studyParticipantAssignments as spa left outer join spa.participant as p left outer join p.identifiers as pIdentifier";

    private static final String QUERY_BY_SHORT_TITLE = "select s from " + Study.class.getName()
            + " s where shortTitle = :st";

    /**
     * Get the Class representation of the domain object that this DAO is
     * representing.
     *
     * @return Class representation of the domain object that this DAO is
     *         representing.
     */
    @Override
    public Class<Study> domainClass() {
        return Study.class;
    }

    /**
     * Get the list of all studies.
     *
     * @return return the list of studies.
     */
    @SuppressWarnings("unchecked")
    public List<Study> getAllStudies() {
        return getHibernateTemplate().find("from Study");
    }


    /**
     * //TODO - Refactor this code with Hibernate Detached objects !!!
     * <p/>
     * This is a hack to load all collection objects in memory. Useful for editing a Study when you know you will be needing all collections
     * To avoid Lazy loading Exception by Hibernate, a call to .size() is done for each collection
     *
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
        if (study.getStudyTherapy(StudyTherapyType.BEHAVIORAL) != null) {
            study.setBehavioralTherapyType(Boolean.TRUE);
        }

        return study;
    }


    /**
     * //TODO - Refactor this code with Hibernate Detached objects !!!
     * <p/>
     * This is a hack to load all collection objects in memory. Useful for editing a Study when you know you will be needing all collections
     * To avoid Lazy loading Exception by Hibernate, a call to .size() is done for each collection
     *
     * @param Identifier
     * @return Fully loaded Study
     */
    public Study getStudyDesignByIdentifier(final Identifier identifier) {
        Study study = getByIdentifier(identifier);
        if (study != null) {

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
            if (study.getStudyTherapy(StudyTherapyType.BEHAVIORAL) != null) {
                study.setBehavioralTherapyType(Boolean.TRUE);
            }


        }
        return study;
    }

    /**
     * This will initialize a lazy collection, consisting of study objects.
     *
     * @param study The study to be initialized.
     */

    public Study initialize(final Study study) {
        HibernateTemplate ht = getHibernateTemplate();
        ht.initialize(study.getIdentifiers());
        ht.initialize(study.getStudyOrganizations());
        if (study.getAeTerminology().getTerm().equals(Term.CTC)) {
            ht.initialize(study.getAeTerminology().getCtcVersion().getCategories());
        }
        for (StudyOrganization studyOrg : study.getStudyOrganizations()) {
            if (studyOrg == null) {
                continue;
            }
            ht.initialize(studyOrg.getStudyInvestigatorsInternal());
            ht.initialize(studyOrg.getStudyPersonnelsInternal());
        }
        ht.initialize(study.getStudyConditions());
        ht.initialize(study.getMeddraStudyDiseases());
        ht.initialize(study.getCtepStudyDiseases());
        ht.initialize(study.getStudyAgentsInternal());
        ht.initialize(study.getStudyTherapies());
        ht.initialize(study.getTreatmentAssignmentsInternal());
        ht.initialize(study.getReportFormats());
        ht.initialize(study.getEpochs());
        for (StudyAgent sa : study.getStudyAgents()) {
            ht.initialize(sa.getStudyAgentINDAssociationsInternal());
            for(StudyAgentINDAssociation saa : sa.getStudyAgentINDAssociationsInternal()){
            	ht.initialize(saa.getInvestigationalNewDrug());
            }
        }
        
        return study;
    }

    /**
     * Save or update the study in the db.
     *
     * @param The study.
     */
    @Transactional(readOnly = false)
    public void save(final Study study) {
        getHibernateTemplate().saveOrUpdate(study);
    }

    /**
     * TODO kkk
     *
     * @param domainObjectImportOutcome
     */
    @Transactional(readOnly = false)
    public void batchSave(final List<DomainObjectImportOutcome<Study>> domainObjectImportOutcome) {
        log.debug("Batch saving start time : " + new java.util.Date());
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        for (DomainObjectImportOutcome<Study> outcome : domainObjectImportOutcome) {
            final Study study = outcome.getImportedDomainObject();
            session.merge(study);
        }
    }

    /**
     * Get the list of studies matching the name fragments.
     *
     * @param subnames the name fragments to search on.
     * @return List of matching studies.
     */
    public List<Study> getBySubnames(final String[] subnames) {
        return findBySubname(subnames, null, null, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }


    /**
     * Search for studies given search criteria.
     *
     * @param props The search criteria.
     * @return The list of studies.
     * @throws ParseException
     */
    public List<Study> searchStudy(final Map props) throws ParseException {

        List<Object> params = new ArrayList<Object>();
        boolean firstClause = true;
        StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(
                " o ").append(JOINS);


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
            String p = (String) props.get("participantDateOfBirth");
            DateValue dob = DateValue.stringToDateValue(p);
            queryBuf.append(" p.dateOfBirth.year").append(" = ? ");
            params.add(dob.getYear());
            if (dob.getMonth() > 0) {
                queryBuf.append(" and p.dateOfBirth.month").append(" = ? ");
                params.add(dob.getMonth());
            }
            if (dob.getDay() > 0) {
                queryBuf.append(" and p.dateOfBirth.day").append(" = ? ");
                params.add(dob.getDay());
            }
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
        return findBySubname(subnames, null, null, EMPTY_PROPERTIES, EXACT_MATCH_UNIQUE_PROPERTIES);
    }

    /**
     * Gets the study by id. This initializes the study and loads all
     * the objects.
     *
     * @param id the id.
     * @return the study by id.
     */
    public Study getByIdentifier(final Identifier identifier) {
        return findByIdentifier(identifier);
    }

    /**
     * This will do an exact match on the <code>shortTitle</code>, and will return the first available Study. Note:- Biz rule should be
     * made that short title is unique.
     */
    public Study getByShortTitle(final String shortTitle) {
        List<Study> studies = findBySubname(new String[]{shortTitle}, null, null, null, EXACT_MATCH_TITLE_PROPERTIES);
        if (studies != null && studies.size() > 0) {
            return studies.get(0);
        }
        return null;
    }

    /**
     * TODO kkk
     *
     * @param study
     * @param isWildCard
     * @return
     */
    @Override
    // TODO - Need to refactor the below into CaaersDao along with identifiers
    public List<Study> searchByExample(final Study study, final boolean isWildCard) {
        Example example = Example.create(study).excludeZeroes().ignoreCase();
        Criteria studyCriteria = getSession().createCriteria(Study.class);
        studyCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (isWildCard) {
            example.excludeProperty("multiInstitutionIndicator").enableLike(MatchMode.ANYWHERE);
            studyCriteria.add(example);
            if (study.getIdentifiers().size() > 0) {
                studyCriteria.createCriteria("identifiers").add(Restrictions.ilike("value", study.getIdentifiers().get(0).getValue() + "%"));
            }
            if (study.getStudyOrganizations().size() > 0) {
                studyCriteria.createCriteria("studyOrganizations").add(Restrictions.eq("organization.id", study.getStudyOrganizations().get(0).getOrganization().getId()));
            }
            return studyCriteria.list();
        }
        return studyCriteria.add(example).list();
    }

    /**
     * TODO kkk
     *
     * @param query
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public List<Study> find(final AbstractQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<Study>) getHibernateTemplate().execute(new HibernateCallback() {

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

    /**
     * Delete the study.
     *
     * @param study The study to be deleted.
     */
    @Transactional(readOnly = false)
    public void delete(Study study) {
        getHibernateTemplate().delete(study);
    }

}
