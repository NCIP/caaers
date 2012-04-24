package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.SynchStatus;

import java.util.Date;

public class IntegrationLogDetailQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct ild from IntegrationLogDetail ild ";

    private static final String BUSINESS_ID = "businessId";

    private static final String OUTCOME = "outcome";

    private static final String SYNCH_STATUS = "synchStatus";
    
    private static final String CORRELATION_ID = "correlationId";
    
    private static final String OPERATION = "operation";
    
    private static final String ENTITY = "entity";
    
    private static final String LOGGED_ON = "loggedOn";

    public IntegrationLogDetailQuery() {
        super(queryString);
        orderBy("ild.id");
    }
    
    public void joinIntegrationLog() {
    	join("ild.integrationLog il");
    }
    
    public void filterByCorrelationId(String correlationId, String operator) {
    	andWhere("lower(il.correlationID) "+operator+" :CORRELATION_ID");
    	if (operator.equals("like")) {
    		setParameter("CORRELATION_ID", getLikeValue(correlationId.toLowerCase()));
    	} else {
    		setParameter("CORRELATION_ID", correlationId.toLowerCase());
    	}
    }

    public void filterByBusinessId(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(businessId) LIKE :" + BUSINESS_ID);
        setParameter(BUSINESS_ID, searchString);
    }
    
    public void filterByOutcome(final String value) {
    	joinIntegrationLog();
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(outcome) LIKE :" + OUTCOME);
        setParameter(OUTCOME, searchString);
    }

    public void filterBySynchStatus(final SynchStatus value) {
        String searchString = "%" + value + "%";
        andWhere("lower(synchStatus) LIKE :" + SYNCH_STATUS);
        setParameter(SYNCH_STATUS, searchString);
    }
    
    public void filterByOperation(final String value) {
    	joinIntegrationLog();
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(il.operation) LIKE :" + OPERATION);
        setParameter(OPERATION, searchString);
    }
    
    public void filterByEntity(final String value) {
    	joinIntegrationLog();
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(il.entity) LIKE :" + ENTITY);
        setParameter(ENTITY, searchString);
    }
    
    
    public void filterByLoggedOn(Date loggedOn, String operator) {
    	joinIntegrationLog();
    	andWhere("il.loggedOn "+operator+" :LOGGED_ON");
    		setParameter("LOGGED_ON", loggedOn);
    }
    
    public void filterByLoggedOnStartDateAndEndDate(Date startDate, Date endDate) {
    	joinIntegrationLog();
    	andWhere("il.loggedOn >= " + " :START_DATE");
    		setParameter("START_DATE", startDate);
		andWhere("il.loggedOn <= " + " :END_DATE");
		setParameter("END_DATE", endDate);
    }
    
}