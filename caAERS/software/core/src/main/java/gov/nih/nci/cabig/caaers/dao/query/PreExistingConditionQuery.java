package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

/**
 *@author Biju Joseph
 */
public class PreExistingConditionQuery extends AbstractQuery {

    public PreExistingConditionQuery() {
        super("select p from PreExistingCondition p");
    }
    
    public void filterByMeddraCode(String meddraCode){
        andWhere("p.meddraLltCode = :mc");
        setParameter("mc", meddraCode);
    }
}
