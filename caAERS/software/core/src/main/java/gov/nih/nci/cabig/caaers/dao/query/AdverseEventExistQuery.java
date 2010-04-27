package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventExistQuery extends AbstractQuery {
	private static String queryString = "select ae from AdverseEventReportingPeriod rp" ;
		//	" left join rp.adverseEvents as ae " +
		//	" left outer join ae.lowLevelTerm as llt " +
		//	" left outer join ae.adverseEventTerm as term ";
	public AdverseEventExistQuery() {
		super(queryString);
		leftJoin("rp.adverseEvents as ae");
		leftOuterJoin("ae.lowLevelTerm as llt");
		leftOuterJoin("ae.adverseEventTerm as term");
	}
	
	
    public void filterByDifferentAdverseEventId(Integer id){
    	if(id == null) return;
    	andWhere(" ae.id != :aeid");
    	setParameter("aeid", id);
    }
    
    public void filterByAdverseEventTerm(AbstractAdverseEventTerm<? extends DomainObject> term){
    	if(term == null || term.getTerm() == null) return;
    	andWhere("term.term.id = :termId");
    	setParameter("termId",term.getTerm().getId());
    }
    
    public void filterByLowLevelTerm(LowLevelTerm llt){
    	if(llt == null) return;
    	andWhere("llt.id = :lltId");
    	setParameter("lltId", llt.getId());
    }
    
    public void filterByReportingPeriodId(Integer id){
    	if(id == null) return;
    	andWhere(" rp.id = :rpid");
    	setParameter("rpid", id);
    }
}
