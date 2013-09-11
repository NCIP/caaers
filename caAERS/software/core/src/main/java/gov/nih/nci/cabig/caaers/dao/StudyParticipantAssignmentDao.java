/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the StudyParticipantAssignment
 * domain object.
 * 
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class StudyParticipantAssignmentDao extends GridIdentifiableDao<StudyParticipantAssignment> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<StudyParticipantAssignment> domainClass() {
        return StudyParticipantAssignment.class;
    }

    /*
     * I guess this is not safe to use , A study might have multiple sites.
     */
    /**
     * TODO kkk
     * 
     * @param participant
     * @param study
     * @return assignment
     */
    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(Participant participant, Study study) {
        return CollectionUtils.firstElement((List<StudyParticipantAssignment>) getHibernateTemplate().find("from StudyParticipantAssignment a where a.participant = ? and a.studySite.study = ?", new Object[] { participant, study }));
    }

    /**
     * TODO kkk
     * 
     * @param participant
     * @param studySite
     * @return
     */
    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(Participant participant, StudySite studySite) {
        return CollectionUtils.firstElement((List<StudyParticipantAssignment>) getHibernateTemplate().find("from StudyParticipantAssignment a where a.participant = ? and a.studySite = ?", new Object[] { participant, studySite }));
    }
    
    
    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(AdverseEventReportingPeriod rp) {
        return CollectionUtils.firstElement((List<StudyParticipantAssignment>) getHibernateTemplate().find("Select a from StudyParticipantAssignment a, AdverseEventReportingPeriod rp where rp.externalId = ? and rp = any elements (a.reportingPeriods) ", new Object[] {rp.getExternalId()}));
    }

    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     * 
     * @param assignment -
     *                the domain object instance that is to be reassociated.
     */
    @Override
    public void reassociate(StudyParticipantAssignment assignment) {
        getHibernateTemplate().lock(assignment, LockMode.NONE);
    }
    
    
    /**
     * Gets the StudyParticipantAssignment based on study identifier and study subject identifier.
     * @param assignment - A StudyParticipantAssignment
     * @return
     */
    @SuppressWarnings("unchecked")
	public StudyParticipantAssignment getByStudySubjectIdAndStudyId(String studySubjectId, String studyId) {
    	studySubjectId = studySubjectId.toLowerCase();
    	studyId = studyId.toLowerCase();
    	
    	return CollectionUtils.firstElement((List<StudyParticipantAssignment>) getHibernateTemplate().find("Select a from StudyParticipantAssignment a, Identifier i where " +
    			"lower(a.studySubjectIdentifier) = ? and i = any elements (a.studySite.study.identifiers) and lower(i.value) = ? " +
        		"and i.type like 'Protocol Authority Identifier'", new Object[]{studySubjectId,studyId}));
    }
    

    /**
     * TODO kkk
     * 
     * @param participant
     * @param site
     * @return
     */
    public Boolean isAssignmentExist(final Participant participant, final StudySite site) {
        return (Boolean) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session
                                .createSQLQuery("select id from participant_assignments where "
                                                + " participant_id = "
                                                + participant.getId().toString()
                                                + " and study_site_id = " + site.getId().toString());
                Object result = query.uniqueResult();

                return (result != null);

            }
        });

    }
    
    public Integer getCountByTAC(Integer id){
    	String query = "Select count(distinct rp.assignment) from AdverseEventReportingPeriod rp where rp.treatmentAssignment.id=:id";
    	return ((Long)getHibernateTemplate().findByNamedParam(query, "id", id).get(0)).intValue();
    }

}
