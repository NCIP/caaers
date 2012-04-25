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
}
