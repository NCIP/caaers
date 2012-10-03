package gov.nih.nci.cabig.caaers.dao.query;

import java.util.ArrayList;
import java.util.Set;

/**
 *@author Biju Joseph
 */
public class ReconciliationReportQuery extends AbstractQuery {
     public ReconciliationReportQuery(){
         super("select rr from ReconciliationReport rr");
     }

     public void joinReportingPeriod(){
         leftJoinFetch("rr.adverseEventReportingPeriod rp");
     }
     public void filerByReportingPeriodId(Set<Integer> reportingPeriodIds){
         joinReportingPeriod();
         andWhere("rp.id in(:rpIds)");
         setParameterList("rpIds", new ArrayList(reportingPeriodIds));
     }
}
