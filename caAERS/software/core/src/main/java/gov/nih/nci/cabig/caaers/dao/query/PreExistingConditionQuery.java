/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

/**
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public class PreExistingConditionQuery extends AbstractQuery {

    public PreExistingConditionQuery() {
        super("select p from PreExistingCondition p");
    }

    public void filterByMeddraCode(String meddraCode) {
        andWhere("p.meddraLltCode = :mc");
        setParameter("mc", meddraCode);
    }

    public void filterByRetiredStatus(Boolean status) {
        super.filterByRetiredStatus("p", status);
    }

}
