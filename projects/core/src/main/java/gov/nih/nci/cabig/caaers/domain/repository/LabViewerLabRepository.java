package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.LabViewerLabDao;
import gov.nih.nci.cabig.caaers.domain.LabViewerLab;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Srini Akkala
 */
@Transactional(readOnly = true)
public class LabViewerLabRepository {
	
	LabViewerLabDao labViewerLabDao;
	 /**
     * Saves a labViewerLab object
     *
     * @param labViewerLab the labViewerLab object
     * @throws Exception runtime exception object
     */

    @Transactional(readOnly = false)
    public void save(LabViewerLab labViewerLab) throws Exception {
    	labViewerLabDao.save(labViewerLab);
    }

	public void setLabViewerLabDao(LabViewerLabDao labViewerLabDao) {
		this.labViewerLabDao = labViewerLabDao;
	}
}