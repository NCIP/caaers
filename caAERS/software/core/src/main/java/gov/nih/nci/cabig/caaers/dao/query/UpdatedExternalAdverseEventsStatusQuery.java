package gov.nih.nci.cabig.caaers.dao.query;


public class UpdatedExternalAdverseEventsStatusQuery extends HQLQuery {
    public static final String UPDATE_AE_STATUS_HQL = "update ExternalAdverseEvent eae set eae.status = :newStatus where " +
            "eae.status = :oldStatus and eae.externalId in (:externalIds)";

    public UpdatedExternalAdverseEventsStatusQuery(String updateQuery) {
        super(updateQuery);
    }
    
}