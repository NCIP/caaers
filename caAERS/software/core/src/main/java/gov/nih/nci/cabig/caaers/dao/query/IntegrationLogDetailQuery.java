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
    
    public void joinIntegrationLogDetails() {
    	join("ild.integrationLog il");
    }
    
    public void filterByBusinessId(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(businessId) LIKE :" + BUSINESS_ID);
        setParameter(BUSINESS_ID, searchString);
    }
    
    public void filterByOutcome(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(outcome) LIKE :" + OUTCOME);
        setParameter(OUTCOME, searchString);
    }

    public void filterBySynchStatus(final SynchStatus value) {
        andWhere("lower(synchStatus) LIKE :" + SYNCH_STATUS);
        setParameter(SYNCH_STATUS, value);
    }
    
}