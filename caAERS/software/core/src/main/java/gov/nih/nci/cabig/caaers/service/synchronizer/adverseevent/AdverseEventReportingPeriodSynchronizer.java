/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.adverseevent;

import gov.nih.nci.cabig.caaers.domain.*;
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
         dest.setExternalId(src.getExternalId());

         int i = -1;
         for(AdverseEvent aeSrc : src.getAdverseEvents()){
            i++;
            AdverseEvent aeFound = dest.findAdverseEventByIdTermAndDates(aeSrc);
            //set the attributes for AE
            if(aeFound == null){
                aeFound = aeSrc;
                //Always populate the graded date when creating the AE.
                if(aeFound.getGradedDate() == null) aeFound.setGradedDate(new Date());
                dest.addAdverseEvent(aeFound);
            } else {
                aeFound.setExpected(aeSrc.getExpected());
                aeFound.setHospitalization(aeSrc.getHospitalization());
                aeFound.setAttributionSummary(aeSrc.getAttributionSummary());
                aeFound.setStartDate(aeSrc.getStartDate());
                aeFound.setEndDate(aeSrc.getEndDate());

                // Reactivating the AE, incase deactivated.
                aeFound.setRetiredIndicator(false);

                // Ensuring that previous and new are CTC terms.
                if ( aeFound.getAdverseEventTerm() instanceof AdverseEventCtcTerm && aeSrc.getAdverseEventTerm() instanceof AdverseEventCtcTerm ) {     // Refers to CTC terminology.
                    // Copy the CTC values from the Src.
                    aeFound.getAdverseEventTerm().setTerm(aeSrc.getAdverseEventTerm().getTerm());
                    ((AdverseEventCtcTerm) aeFound.getAdverseEventTerm()).setCtcTerm(((AdverseEventCtcTerm) aeSrc.getAdverseEventTerm()).getCtcTerm());

                    if ( aeSrc.getAdverseEventTerm().isOtherRequired()) {
                        aeFound.getAdverseEventMeddraLowLevelTerm().setTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm());
                        aeFound.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm());
                    }
                } else { // Refers to MEDRAA terminology.
                    if ( aeFound.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm && aeSrc.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm ) {
                        aeFound.setLowLevelTerm(aeSrc.getLowLevelTerm());
                        aeFound.getAdverseEventMeddraLowLevelTerm().setTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm());
                        aeFound.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm());
                    }

                }

                aeFound.setEventApproximateTime(aeSrc.getEventApproximateTime());
                aeFound.setEventLocation(aeSrc.getEventLocation());

                aeFound.setGrade(aeSrc.getGrade());
                // Update only when there is a valid value is present.
                if ( aeSrc.getGradedDate() != null) aeFound.setGradedDate(aeSrc.getGradedDate());

                aeFound.setDetailsForOther(aeSrc.getDetailsForOther());
                aeFound.setComments(aeSrc.getComments());
                aeFound.setParticipantAtRisk(aeSrc.getParticipantAtRisk());
                aeFound.setRequiresReporting(aeSrc.getRequiresReporting());
                aeFound.setReported(aeSrc.getReported());
                aeFound.setSignature(aeSrc.getSignature());

                aeFound.getOutcomes().clear();
                for(Outcome outcomeSrc : aeSrc.getOutcomes()) aeFound.addOutComeIfNecessary(outcomeSrc);
            }

         }
    }
}
