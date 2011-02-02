package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.LabLoadDao;
import gov.nih.nci.cabig.caaers.domain.LabLoad;

import org.springframework.transaction.annotation.Transactional;

 
/**
 * The Class LabLoadRepository.
 *
 * @author Srini Akkala
 */
@Transactional(readOnly = true)
public class LabLoadRepository {
	
	/** The lab load dao. */
	LabLoadDao labLoadDao;
	 
 	/**
 	 * Saves a labLoad object.
 	 *
 	 * @param labLoad the labLoad object
 	 * @throws Exception runtime exception object
 	 */

    @Transactional(readOnly = false)
    public void save(LabLoad labLoad) throws Exception {
    	labLoadDao.save(labLoad);
    }


	/**
	 * Sets the lab load dao.
	 *
	 * @param labLoadDao the new lab load dao
	 */
	public void setLabLoadDao(LabLoadDao labLoadDao) {
		this.labLoadDao = labLoadDao;
	}
}