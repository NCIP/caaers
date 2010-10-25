package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Biju Joseph
 */
public class StudyDeviceDao extends CaaersDao<StudyDevice>{


    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<StudyDevice> domainClass() {
        return StudyDevice.class;
    }
}
