package gov.nih.nci.cabig.caaers.service.synchronizer.adverseevent;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.Date;

/**
 * User: Biju Joseph
 * Date: 1/30/13
 */
public class AdverseEventReportingPeriodSynchronizer implements Migrator<AdverseEventReportingPeriod> {

    public void migrate(AdverseEventReportingPeriod src, AdverseEventReportingPeriod dest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome) {
         dest.setStartDate(src.getStartDate());
         dest.setEndDate(src.getEndDate());
         dest.setCycleNumber(src.getCycleNumber());
         dest.setEpoch(src.getEpoch());

         int i = -1;
         for(AdverseEvent aeSrc : src.getAdverseEvents()){
            i++;
            AdverseEvent aeFound = dest.findAdverseEventByIdTermAndDates(aeSrc);
            //set the attributes for AE
            if(aeFound == null){
                aeFound = aeSrc;
                dest.addAdverseEvent(aeFound);
                if(aeFound.getGradedDate() == null) aeFound.setGradedDate(new Date());
            } else {
                aeFound.setExpected(aeSrc.getExpected());
                aeFound.setHospitalization(aeSrc.getHospitalization());
                aeFound.setAttributionSummary(aeSrc.getAttributionSummary());

                aeFound.setEventApproximateTime(aeSrc.getEventApproximateTime());
                aeFound.setEventLocation(aeSrc.getEventLocation());

                aeFound.setGrade(aeSrc.getGrade());
                if(aeFound.getGradedDate() == null) aeFound.setGradedDate(new Date());

                aeFound.setDetailsForOther(aeSrc.getDetailsForOther());
                aeFound.setComments(aeSrc.getComments());

                aeFound.getOutcomes().clear();
                for(Outcome outcomeSrc : aeSrc.getOutcomes()) aeFound.addOutComeIfNecessary(outcomeSrc);
            }

         }
    }
}
