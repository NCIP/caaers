/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.query.PreExistingConditionQuery;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 5/16/12 -5:55 PM
 */
public class PreExistingConditionRepository {

    PreExistingConditionDao preExistingConditionDao;

    public List<PreExistingCondition> getAll(boolean activeOnly) {
        PreExistingConditionQuery q = new PreExistingConditionQuery();
        if (activeOnly) q.filterByRetiredStatus(false);
        q.orderBy("text");
        return (List<PreExistingCondition>)preExistingConditionDao.search(q);
    }

    public PreExistingConditionDao getPreExistingConditionDao() {
        return preExistingConditionDao;
    }

    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
    }
}
