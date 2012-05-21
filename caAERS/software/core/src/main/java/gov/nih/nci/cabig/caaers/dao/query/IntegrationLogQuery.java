package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.security.util.StringUtilities;

import java.util.Date;

public class IntegrationLogQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct il from IntegrationLog il ";

    public IntegrationLogQuery() {
        super(queryString);
        orderBy("il.loggedOn desc");
    }
    
    public void joinIntegrationLogDetail() {
        join("il.integrationLogDetails as ild");
    }
    
    public void filterByCorrelationId(String correlationId) {
    	andWhere("lower(correlationId) LIKE :cid");
    	setParameter("cid", correlationId.toLowerCase());
    }

    public void filterByEntity(final String value) {
    	if(!StringUtilities.isBlank(value)) {
	        String searchString = "%" + value.toLowerCase() + "%";
	        andWhere("lower(entity) LIKE :et");
	        setParameter("et", searchString);
    	}
    }

    public void filterBySynchStatus(final SynchStatus synchStatus) {
        andWhere("synchStatus LIKE :sys");
        setParameter("sys", synchStatus);
    }
    
    public void filterByOperation(final String value) {
    	if(!StringUtilities.isBlank(value)) {
	        String searchString = "%" + value.toLowerCase() + "%";
	        andWhere("lower(operation) LIKE :oper" );
	        setParameter("oper", searchString);
    	}
    }
    
    public void filterByOperation(final String operation1, final String operation2) {
    	if(!StringUtilities.isBlank(operation1) && !StringUtilities.isBlank(operation2) ) {
	        String searchString1 = "%" + operation1.toLowerCase() + "%";
	        String searchString2 = "%" + operation2.toLowerCase() + "%";
	        andWhere("lower(operation) LIKE :operation1 or lower(operation) LIKE :operation2" );
	        setParameter("operation1", searchString1);
	        setParameter("operation2", searchString2);
    	}
    }
    
    public void filterByLoggedOn(Date loggedOn, String operator) {
    	andWhere("loggedOn "+operator+" :lgdon");
    		setParameter("lgdon", loggedOn);
    }
    
    public void filterByLoggedOnStartDateAndEndDate(Date startDate, Date endDate) {

    	if(startDate != null){
    		andWhere("loggedOn >= " + " :START_DATE");
    		setParameter("START_DATE", startDate);
    	}
    	
    	if(endDate != null){
    		andWhere("loggedOn <= " + " :END_DATE");
    		setParameter("END_DATE", endDate);
    	}
    }
    
    public void filterByIncomplete(){
    	excludeHavingSynchStatus(SynchStatus.REQUEST_COMPLETION);
    }
    
    public void filterByComplete(){
    	filterBySynchStatus(SynchStatus.REQUEST_COMPLETION);
    }
    
    public void excludeHavingSynchStatus(final SynchStatus synchStatus) {
        andWhere("synchStatus != :synst" );
        setParameter("synst", synchStatus);
    }
    
}