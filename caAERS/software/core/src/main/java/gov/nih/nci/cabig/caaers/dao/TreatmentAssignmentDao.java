package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.util.List;

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

}
