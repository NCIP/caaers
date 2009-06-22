package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;

import java.util.List;

/**
 * User: Ion
 * Date: Jun 19, 2009
 * Time: 12:05:31 PM
 */
public class ConfigPropertyRepositoryImpl implements ConfigPropertyRepository {
    ConfigPropertyDao cpDao;
    
    public ConfigProperty getByTypeAndCode(ConfigPropertyType cpType, String cpCode) {
        return cpDao.getByTypeAndCode(cpType, cpCode);
    }

    public List<ConfigProperty> getByType(ConfigPropertyType cpType) {
        return cpDao.getByType(cpType);
    }

    //-----------------------------------------------------------------------------------------------------------------
    
    public ConfigPropertyDao getCpDao() {
        return cpDao;
    }

    public void setCpDao(ConfigPropertyDao cpDao) {
        this.cpDao = cpDao;
    }

    //-----------------------------------------------------------------------------------------------------------------

}
