package gov.nih.nci.cabig.caaers.dao.query;

import java.util.Date;

public class IntegrationLogQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct il from IntegrationLog il ";

    private static final String ENTITY = "entity";

    private static final String CORRELATION_ID = "correlationId";

    private static final String NOTES = "notes";

    private static final String SYNCH_STATUS = "synchStatus";
    
    private static final String OPERATION = "operation";
    
    private static final String LOGGED_ON = "loggedOn";

    public IntegrationLogQuery() {
        super(queryString);
        orderBy("il.id");
    }
    
    public void filterByCorrelationId(String correlationId, String operator) {
    	andWhere("lower(correlationID) "+operator+" :CORRELATION_ID");
    	if (operator.equals("like")) {
    		setParameter("CORRELATION_ID", getLikeValue(correlationId.toLowerCase()));
    	} else {
    		setParameter("CORRELATION_ID", correlationId.toLowerCase());
    	}
    }

    public void filterByEntity(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(entity) LIKE :" + ENTITY);
        setParameter(ENTITY, searchString);
    }

    public void filterBySynchStatus(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(synchStatus) LIKE :" + SYNCH_STATUS);
        setParameter(SYNCH_STATUS, searchString);
    }
    
    public void filterByOperation(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(operation) LIKE :" + OPERATION);
        setParameter(OPERATION, searchString);
    }
    
    public void filterByLoggedOn(Date loggedOn, String operator) {
    	andWhere("loggedOn "+operator+" :LOGGED_ON");
    		setParameter("LOGGED_ON", loggedOn);
    }
    
}