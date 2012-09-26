package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;

import java.util.Date;

/**
 * Will query external adverse events.
 * @author Biju Joseph
 */
public class ExternalAdverseEventQuery extends AbstractQuery {

    public ExternalAdverseEventQuery(){
     super("select eae from ExternalAdverseEvent eae");
    }

    public void filterByStatus(ExternalAEReviewStatus status){
         andWhere("eae.status = :st");
         setParameter("st", status);
    }

    public void filterByCreatedOnAfter(Date created){
        andWhere("eae.created_on > :cdt" );
        setParameter("cdt", created);
    }
    public void filterByCreatedOnBefore(Date created){
        andWhere("eae.created_on <= :cdt" );
        setParameter("cdt", created);
    }
    public void joinExternalReportingPeriod(){
        join("eae.externalAdverseEventReportingPeriod erp");
    }

    public void joinReportingPeriod(){
        join("erp.domainReportingPeriod rp");
    }
    public void filterByReportingPeriod(Integer id){
          joinExternalReportingPeriod();
          joinReportingPeriod();
          andWhere("rp.id = :rpId");
          setParameter("rpId", id);
    }
}
