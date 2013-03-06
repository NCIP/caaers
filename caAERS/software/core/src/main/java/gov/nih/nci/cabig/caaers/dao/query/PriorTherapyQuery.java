/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

/**
 * 
 * @author Biju Joseph
 */
public class PriorTherapyQuery extends AbstractQuery {
    
    public PriorTherapyQuery() {
        super("select p from PriorTherapy p");
    }
    
    public void filterByMeddraCode(String meddraCode){
        andWhere("p.meddraCode = :mc");
        setParameter("mc", meddraCode);
    }

    public void filterOutNoPriorTherapy() {
        andWhere("lower(p.text) != 'no prior therapy'");
    }

    public void filterByRetiredStatus(Boolean status) {
        super.filterByRetiredStatus("p", status);
    }

}
