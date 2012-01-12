package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignmentAgent;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignmentDevice;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignmentOtherIntervention;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the TreatmentAssignment domain
 * object.
 * 
 * @author
 * 
 */
public class TreatmentAssignmentDao extends GridIdentifiableDao<TreatmentAssignment> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<TreatmentAssignment> domainClass() {
        return TreatmentAssignment.class;
    }

    /**
     * Gets the treatment assignment by for specified treatment assignment code and study. This
     * initializes the treatment assignment and loads all the objects.
     * 
     * @param code
     *                The treatment assignment code.
     * @param studyId
     *                The id of the study.
     * 
     * @return The list of treatment assignments.
     */
    @SuppressWarnings(value = "unchecked")
    public List<TreatmentAssignment> getAssignmentsByStudyId(String code, int studyId) {
        return getHibernateTemplate().findByNamedParam(
                        "from TreatmentAssignment ta where " + " ta.code like :tac "
                                        + " and ta.study.id = :studyId",
                        new String[] { "tac", "studyId" },
                        new Object[] { "%" + code + "%", studyId });
    }

    /**
     * Gets the treatment assignment by for specified treatment assignment code and study. This
     * initializes the treatment assignment and loads all the objects.
     * 
     * @param code
     *                The treatment assignment code.
     * @param studyId
     *                The id of the study.
     * 
     * @return The treatment assignment.
     */
    @SuppressWarnings(value = "unchecked")
    public TreatmentAssignment getAssignmentsByStudyIdExactMatch(String code, int studyId) {
        List<TreatmentAssignment> tas = getHibernateTemplate().findByNamedParam(
                        "from TreatmentAssignment ta where " + " ta.code like :tac "
                                        + " and ta.study.id = :studyId",
                        new String[] { "tac", "studyId" }, new Object[] { code, studyId });
        return tas.size() > 0 ? tas.get(0) : null;
    }

    /**
     * Get the list of all treatment assignments.
     * 
     * @return return the list of treatment assignments.
     */
    @SuppressWarnings(value = "unchecked")
    public List<TreatmentAssignment> getAll() {
        return getHibernateTemplate().find("from TreatmentAssignment ta order by ta.id");
    }

    public List<TreatmentAssignmentAgent> getTreatmentAssignmentAgentsByStudyId(int studyId) {
        return getHibernateTemplate().find("from TreatmentAssignmentAgent taa where taa.studyAgent.study.id = ?", new Object[] {studyId});
    }

    public List<TreatmentAssignmentDevice> getTreatmentAssignmentDevicesByStudyId(int studyId) {
        return getHibernateTemplate().find("from TreatmentAssignmentDevice tad where tad.studyDevice.study.id = ?", new Object[] {studyId});
    }

    public List<TreatmentAssignmentOtherIntervention> getTreatmentAssignmentOthersByStudyId(int studyId) {
        return getHibernateTemplate().find("from TreatmentAssignmentOtherIntervention toi where toi.otherIntervention.study.id = ? ", new Object[] {studyId});
    }
}
