package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.SynchStatus;

import java.util.Date;

public class IntegrationLogDetailQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct ild from IntegrationLogDetail ild ";


    public IntegrationLogDetailQuery() {
        super(queryString);
        orderBy("ild.id");
    }
    
    public void joinIntegrationLogDetails() {
    	join("ild.integrationLog il");
    }
    
    public void filterByBusinessId(final String value) {
        andWhere("businessId = :bid");
        setParameter("bid", value);
    }
    
    public void filterByOutcome(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(outcome) LIKE :oc" );
        setParameter("oc", searchString);
    }

    public void filterBySynchStatus(final SynchStatus value) {
        andWhere("synchStatus = :ss");
        setParameter("ss", value);
    }
    
}