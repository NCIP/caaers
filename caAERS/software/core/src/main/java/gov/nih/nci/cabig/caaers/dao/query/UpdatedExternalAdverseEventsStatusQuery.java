package gov.nih.nci.cabig.caaers.dao.query;


public class UpdatedExternalAdverseEventsStatusQuery extends HQLQuery {
    public static final String UPDATE_AE_STATUS_HQL = "update ExternalAdverseEvent eae set eae.status = :newStatus where " +
            "eae.status = :oldStatus and eae.externalId in (:externalIds) and eae.id in (select eaa1.id from ExternalAdverseEvent eaa1 " +
            "join eaa1.externalAdverseEventReportingPeriod earp join earp.domainReportingPeriod drp where drp.id = :domainReportingPeriodId)";

    public UpdatedExternalAdverseEventsStatusQuery(String updateQuery) {
        super(updateQuery);
    }
    
}