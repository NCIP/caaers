/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;

import java.util.List;

 
/**
 * User: Ion
 * Date: Jun 19, 2009
 * Time: 12:05:31 PM.
 */
public class ConfigPropertyRepositoryImpl implements ConfigPropertyRepository {
    
    /** The cp dao. */
    ConfigPropertyDao cpDao;
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository#getByTypeAndCode(gov.nih.nci.cabig.caaers.domain.ConfigPropertyType, java.lang.String)
     */
    public ConfigProperty getByTypeAndCode(ConfigPropertyType cpType, String cpCode) {
        return cpDao.getByTypeAndCode(cpType, cpCode);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository#getByType(gov.nih.nci.cabig.caaers.domain.ConfigPropertyType)
     */
    public List<ConfigProperty> getByType(ConfigPropertyType cpType) {
        return cpDao.getByType(cpType);
    }

    //-----------------------------------------------------------------------------------------------------------------
    
    /**
     * Gets the cp dao.
     *
     * @return the cp dao
     */
    public ConfigPropertyDao getCpDao() {
        return cpDao;
    }

    /**
     * Sets the cp dao.
     *
     * @param cpDao the new cp dao
     */
    public void setCpDao(ConfigPropertyDao cpDao) {
        this.cpDao = cpDao;
    }

    //-----------------------------------------------------------------------------------------------------------------

}
