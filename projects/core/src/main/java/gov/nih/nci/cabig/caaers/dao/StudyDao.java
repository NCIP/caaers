package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Priyatam
 */
@Transactional(readOnly=true)
public class StudyDao extends GridIdentifiableDao<Study> 
	implements MutableDomainObjectDao<Study>{
	
    private static final List<String> SUBSTRING_MATCH_PROPERTIES
        = Arrays.asList("shortTitle", "longTitle");
    private static final List<String> EXACT_MATCH_PROPERTIES
        = Collections.emptyList();
    private static final List<String> EXACT_MATCH_UNIQUE_PROPERTIES
		= Arrays.asList("longTitle");
    private static final List<String> EMPTY_PROPERTIES
		= Collections.emptyList();
    private static final String JOINS 
    	= "join o.identifiers as identifier " + 
		"join o.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p join p.identifiers as pIdentifier";
    
    public Class<Study> domainClass() {
        return Study.class;
    }

    @SuppressWarnings("unchecked")
    public List<Study> getAllStudies() {
        return (List<Study>) getHibernateTemplate().find("from Study");
    }

    /**
     //TODO - Refactor this code with Hibernate Detached objects !!!
      
	 * This is a hack to load all collection objects in memory. Useful
	 * for editing a Study when you know you will be needing all collections
	 * To avoid Lazy loading Exception by Hibernate, a call to .size() is done
	 * for each collection
	 * @param id
	 * @return Fully loaded Study
	 */
	public Study getStudyDesignById(int id) {				
        Study study =  (Study) getHibernateTemplate().get(domainClass(), id);
        study.getIdentifiers().size();
        study.getStudySites().size();
        for (StudySite studySite : study.getStudySites()) {
			studySite.getStudyInvestigators().size();
			studySite.getStudyPersonnels().size();		
		}
        study.getMeddraStudyDiseases().size();
        study.getStudyAgents().size();
       
        return study;
    }

    @Transactional(readOnly=false)
    public void save(Study study) {
		getHibernateTemplate().saveOrUpdate(study);
	}

    public List<Study> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
    
    public List<Study> getByCriteria(String[] subnames, List<String> subStringMatchProperties)
    {
    	return findBySubname(subnames,null,null,subStringMatchProperties,null,JOINS);
    }
    
	public List<Study> searchStudy(Map props) throws ParseException {

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ")
         .append(domainClass().getName()).append(" o ").append(JOINS);
		
		/*
		if (true) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" ss.class = StudySite ");
			firstClause = false;
		}*/
		
		if (props.get("studyIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("identifier.value").append(") LIKE ?");
			String p = (String)props.get("studyIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("studyShortTitle") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("o.shortTitle").append(") LIKE ?");
			String p = (String)props.get("studyShortTitle");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("pIdentifier.value").append(") LIKE ?");
			String p = (String)props.get("participantIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantFirstName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.firstName").append(") LIKE ?");
			String p = (String)props.get("participantFirstName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantLastName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.lastName").append(") LIKE ?");
			String p = (String)props.get("participantLastName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantEthnicity") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.ethnicity").append(") LIKE ?");
			String p = (String)props.get("participantEthnicity");
			params.add( p.toLowerCase() );
			firstClause = false;
		}
		if (props.get("participantGender") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.gender").append(") LIKE ?");
			String p = (String)props.get("participantGender");
			params.add( p.toLowerCase() );
			firstClause = false;
		}
		
		if (props.get("participantDateOfBirth") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" p.dateOfBirth").append(" = ? ");
			String p = (String)props.get("participantDateOfBirth");
			params.add(stringToDate(p));
			firstClause = false;
		}
		log.debug("::: " + queryBuf.toString() );
		return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
    }
    
    
    /**
     * @param subnames a set of substrings to match
     * @return a list of participants such that each entry in <code>subnames</code> is a
     *  case-insensitive substring match of the participant's name or other identifier
     */
    @SuppressWarnings("unchecked")
    public List<Study> getByUniqueIdentifiers(String[] subnames) {
        return findBySubname(subnames, EMPTY_PROPERTIES, EXACT_MATCH_UNIQUE_PROPERTIES);
    }

    public Study getByIdentifier(Identifier identifier) {
        return findByIdentifier(identifier);
    }
    
    @Override
    //TODO - Need to refactor the below into CaaersDao along with identifiers
    public List<Study> searchByExample(Study study, boolean isWildCard) {
		Example example = Example.create(study).excludeZeroes().ignoreCase();
		Criteria studyCriteria = getSession().createCriteria(Study.class);
	
		if (isWildCard)
		{
			example.excludeProperty("doNotUse").enableLike(MatchMode.ANYWHERE);
			studyCriteria.add(example);
			if (study.getIdentifiers().size() > 0) {
				studyCriteria.createCriteria("identifiers")
					.add(Restrictions.like("value", study.getIdentifiers().get(0)
					.getValue()+ "%"));
			} 
			return studyCriteria.list();
		}
		return studyCriteria.add(example).list();
	}
}