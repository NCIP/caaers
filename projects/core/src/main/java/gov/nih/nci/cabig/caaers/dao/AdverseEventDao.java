package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the AdverseEvent domain object.
 * 
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class AdverseEventDao extends CaaersDao<AdverseEvent> {

    @Override
    public Class<AdverseEvent> domainClass() {
        return AdverseEvent.class;
    }

    /** The Constant JOINS. */
    private static final String JOINS = " join o.adverseEventTerm as aeCtcTerm join aeCtcTerm.term as ctcTerm join ctcTerm.category as ctcCategory  "
                    + "  join o.reportingPeriod as rp "
                    + " join rp.assignment as spa join spa.studySite as studySite join studySite.study as study join study.identifiers as identifier"
                    + " join spa.participant as p join p.identifiers as pIdentifier";

   
    /**
     * Save the Adverse Event.
     * 
     * @param event
     *                The event to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final AdverseEvent event) {
        getHibernateTemplate().saveOrUpdate(event);
    }

    /**
     * Search for Adverse events with the supplied property list
     * 
     * @param props
     *                The criteria for searching Adverse Events
     * @return The list of Adverse events that match the criteria
     * @throws ParseException
     */
    @SuppressWarnings( { "unchecked" })
    public List<AdverseEvent> searchAdverseEvents(final Map props) throws ParseException {

        List<Object> params = new ArrayList<Object>();
        boolean firstClause = true;
        StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(
                        domainClass().getName()).append(" o ").append(JOINS);


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
            queryBuf.append("LOWER(").append("o.lowLevelTerm.meddraCode").append(") LIKE ?");
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
            
            String p = (String) props.get("participantDateOfBirth");
            DateValue dob = DateUtils.parseDateString(p);
            
            if(dob != null){
            	if(dob.getDay() != null){
            		queryBuf.append(firstClause ? " where " : " and ");
                    queryBuf.append(" p.dateOfBirth.day").append(" = ? ");
                    params.add(dob.getDay());
                    firstClause = false;
            	}
            	if(dob.getMonth() != null){
            		queryBuf.append(firstClause ? " where " : " and ");
                    queryBuf.append(" p.dateOfBirth.month").append(" = ? ");
                    params.add(dob.getMonth());
                    firstClause = false;
            	}
            	if(dob.getYear() != null){
            		queryBuf.append(firstClause ? " where " : " and ");
                    queryBuf.append(" p.dateOfBirth.year").append(" = ? ");
                    params.add(dob.getYear());
                    firstClause = false;
            	}
            }
        }

        log.debug("::: " + queryBuf.toString());
        getHibernateTemplate().setMaxResults(CaaersDao.DEFAULT_MAX_RESULTS_SIZE);
        return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
    }
    public List<AdverseEvent> getByParticipant(Participant participant) {		
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.createCriteria("reportingPeriod").createCriteria("assignment")
		.createCriteria("participant").add(getParticipantExample(participant));

		//.createCriteria("identifiers").add(getIdentifierExample(participant));
		return getHibernateTemplate().findByCriteria(criteria);	
	}

    public List<AdverseEvent> getByParticipant(Participant participant , AdverseEvent adverseEvent) {		
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.add(getAdverseEventExample(adverseEvent)).createCriteria("reportingPeriod").createCriteria("assignment")
		.createCriteria("participant").add(getParticipantExample(participant));

		return getHibernateTemplate().findByCriteria(criteria);	
	}
    
	public List<AdverseEvent> getByStudy(Study study) {
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.createCriteria("reportingPeriod").createCriteria("assignment").createCriteria("studySite")
		.createCriteria("study").add(getStudyExample(study));

		return getHibernateTemplate().findByCriteria(criteria);	
	}

	public List<AdverseEvent> getByStudy(Study study, AdverseEvent adverseEvent) {
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.add(getAdverseEventExample(adverseEvent)).createCriteria("reportingPeriod").createCriteria("assignment").createCriteria("studySite")
		.createCriteria("study").add(getStudyExample(study));
		//if (study.getIdentifiers().size() > 0) {
			//criteria = criteria.createCriteria("identifiers").add(getIdentifierExample(study.getIdentifiers().get(0)));
		//}
		return getHibernateTemplate().findByCriteria(criteria);	
	}

	public List<AdverseEvent> getByStudyParticipant(Study study, Participant participant, AdverseEvent adverseEvent) {
		
		StudyParticipantAssignmentDao studyParticipantAssignmentDao = null;
		StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(participant, study);
		
		
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.add(getAdverseEventExample(adverseEvent)).createCriteria("reportingPeriod").createCriteria("assignment").add(getAssignmentExample(assignment));
		//if (study.getIdentifiers().size() > 0) {
			//criteria = criteria.createCriteria("identifiers").add(getIdentifierExample(study.getIdentifiers().get(0)));
		//}
		return getHibernateTemplate().findByCriteria(criteria);			
	}
	
	public List<AdverseEvent> getByStudyParticipant(Study study, Participant participant) {
		
		StudyParticipantAssignmentDao studyParticipantAssignmentDao = null;
		StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(participant, study);
		
		
		DetachedCriteria criteria = DetachedCriteria.forClass(AdverseEvent.class);		
		criteria.createCriteria("reportingPeriod").createCriteria("assignment").add(getAssignmentExample(assignment));
		//if (study.getIdentifiers().size() > 0) {
			//criteria = criteria.createCriteria("identifiers").add(getIdentifierExample(study.getIdentifiers().get(0)));
		//}
		return getHibernateTemplate().findByCriteria(criteria);			
	}

	private Example getParticipantExample(Participant participant) {
		return addOptions(Example.create(participant));
	}
	private Example getStudyExample(Study study) {
		return addOptions(Example.create(study));
	}	
	private Example getAdverseEventExample(AdverseEvent adverseEvent) {
		return addOptions(Example.create(adverseEvent));
	}
	private Example getAssignmentExample( StudyParticipantAssignment assignment) {
		return addOptions(Example.create(assignment));
	}
	
	private Example addOptions(Example example) {
		example.enableLike();
		example.ignoreCase();
		return example;
	}   
   
}
