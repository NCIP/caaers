package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.security.util.StringUtilities;

import java.util.Date;

public class IntegrationLogQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct il from IntegrationLog il ";

    private static final String ENTITY = "entity";

    private static final String CORRELATION_ID = "correlationId";

    private static final String NOTES = "notes";

    private static final String SYNCH_STATUS = "synchStatus";
    
    private static final String OPERATION = "operation";
    
    private static final String LOGGED_ON = "loggedOn";
    
    private static final String INTEGRATION_LOG_ALIAS = "il";
    
    private static final String STATUS = "status";
    
    public IntegrationLogQuery() {
        super(queryString);
        orderBy("il.id");
    }
    
    public void joinIntegrationLogDetail() {
        join(INTEGRATION_LOG_ALIAS+".integrationLogDetails as ild");
    }
    
    public void filterByCorrelationId(String correlationId) {
    	andWhere("lower(correlationId) LIKE :" + ":CORRELATION_ID");
    	setParameter("CORRELATION_ID", correlationId.toLowerCase());
    }

    public void filterByEntity(final String value) {
    	if(!StringUtilities.isBlank(value)) {
	        String searchString = "%" + value.toLowerCase() + "%";
	        andWhere("lower(entity) LIKE :" + ENTITY);
	        setParameter(ENTITY, searchString);
    	}
    }

    public void filterBySynchStatus(final SynchStatus synchStatus) {
        andWhere("synchStatus LIKE :" + SYNCH_STATUS);
        setParameter(SYNCH_STATUS, synchStatus);
    }
    
    public void filterByOperation(final String value) {
    	if(!StringUtilities.isBlank(value)) {
	        String searchString = "%" + value.toLowerCase() + "%";
	        andWhere("lower(operation) LIKE :" + OPERATION);
	        setParameter(OPERATION, searchString);
    	}
    }
    
    public void filterByLoggedOn(Date loggedOn, String operator) {
    	andWhere("loggedOn "+operator+" :LOGGED_ON");
    		setParameter("LOGGED_ON", loggedOn);
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
    
    public void filterByFailed(){
    	excludeHavingSynchStatus(SynchStatus.REQUEST_COMPLETION);
    }
    
    public void filterBySuccess(){
    	filterBySynchStatus(SynchStatus.REQUEST_COMPLETION);
    }
    
    public void excludeHavingSynchStatus(final SynchStatus synchStatus) {
        andWhere("synchStatus != :" + SYNCH_STATUS);
        setParameter(SYNCH_STATUS, synchStatus);
    }
    
}