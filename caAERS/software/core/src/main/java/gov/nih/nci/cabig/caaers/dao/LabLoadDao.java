package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class implements the Data access related operations for the LabLoad domain
 * object.
 * 
 * @author Srini Akkala
 */

@Transactional(readOnly = true)
public class LabLoadDao extends GridIdentifiableDao<LabLoad>
                implements MutableDomainObjectDao<LabLoad>{
	
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<LabLoad> domainClass() {
        return LabLoad.class;
    }
    
    /**
     * Save or update the adverse event lab viewer labs in the db.
     * 
     * @param 
     */
    @Transactional(readOnly = false)
    public void save(final LabLoad labLoad) {
        getHibernateTemplate().saveOrUpdate(labLoad);
    }
    
    /**
     * Get the list of LabViewerLabs based on the Assignment.
     * This is needed to rightly update the side nav bar on addition of a new lab viewer lab data .
     */
    @SuppressWarnings("unchecked")
	public List<LabLoad> getByAssignment(StudyParticipantAssignment assignment) {
        List<LabLoad> results = getHibernateTemplate().find("from LabLoad where assignment_id= ? order by lab_date", assignment.getId());
        return results;
    }
    
    /**
     * Delete report from db.
     * 
     * @param rs
     * The labViewerLab object to be deleted.
     */
    @Transactional(readOnly = false)
    public void delete(LabLoad labLoad) {
        getHibernateTemplate().delete(labLoad);
    }
    
    /**
     * Delete labViewerLab from db
     * 
     * @param id
     *                The ID of the labViewerLab
     * @return True if LabLoad successfully deleted. False otherwise.
     */
    @Transactional(readOnly = false)
    public boolean deleteById(int id) {
        int count = getHibernateTemplate().bulkUpdate("delete LabLoad l where l.id=?",
                        new Object[] { id });
        return count >= 1;
    }
}