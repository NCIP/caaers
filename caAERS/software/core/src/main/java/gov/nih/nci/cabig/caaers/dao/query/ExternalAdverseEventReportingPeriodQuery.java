package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

public class ExternalAdverseEventReportingPeriodQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct eaerp from  ExternalAdverseEventReportingPeriod eaerp ";

    public ExternalAdverseEventReportingPeriodQuery() {
        super(queryString);
        orderBy("eaerp.startDate");
    }
    
    public void filterByReviewStatus(final ReviewStatus reviewStatus) {
        andWhere("reviewStatus LIKE :rstatus");
        setParameter("rstatus", reviewStatus);
    }
    
    public void filterByUnApproved(){
    	excludeHavingReviewStatus(ReviewStatus.APPROVED);
    }
    
    public void filterByApproved(){
    	filterByReviewStatus(ReviewStatus.APPROVED);
    }
    
    public void excludeHavingReviewStatus(final ReviewStatus reviewStatus) {
        andWhere("reviewStatus != :rstatus" );
        setParameter("rstatus", reviewStatus);
    }
    
}