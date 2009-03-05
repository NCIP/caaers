package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import edu.nwu.bioinformatics.commons.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
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

}
