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
import gov.nih.nci.logging.api.util.StringUtils;

import java.util.Date;

/**
 * User: Biju Joseph
 * Date: 1/30/13
 */
public class AdverseEventReportingPeriodSynchronizer implements Migrator<AdverseEventReportingPeriod> {

    public void migrate(AdverseEventReportingPeriod src, AdverseEventReportingPeriod dest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome) {

         if(src.getStartDate() != null) dest.setStartDate(src.getStartDate());
         if(src.getEndDate() != null) dest.setEndDate(src.getEndDate());
         if(src.getCycleNumber() != null) dest.setCycleNumber(src.getCycleNumber());
         if(src.getEpoch() != null) dest.setEpoch(src.getEpoch());
         if(src.getExternalId() != null) dest.setExternalId(src.getExternalId());

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
                if(aeSrc.getExpected() != null) aeFound.setExpected(aeSrc.getExpected());
                if(aeSrc.getHospitalization() != null) aeFound.setHospitalization(aeSrc.getHospitalization());
                if(aeSrc.getAttributionSummary() != null) aeFound.setAttributionSummary(aeSrc.getAttributionSummary());
                if(aeSrc.getStartDate() != null) aeFound.setStartDate(aeSrc.getStartDate());
                if(aeSrc.getEndDate() != null)  aeFound.setEndDate(aeSrc.getEndDate());
                if(StringUtils.isBlank(aeFound.getReporterEmail())){
                	aeFound.setReporterEmail(aeSrc.getReporterEmail());
                }

                // Reactivating the AE, incase deactivated.
                aeFound.setRetiredIndicator(false);

                // Ensuring that previous and new are CTC terms.
                if ( aeFound.getAdverseEventTerm() instanceof AdverseEventCtcTerm && aeSrc.getAdverseEventTerm() instanceof AdverseEventCtcTerm ) {     // Refers to CTC terminology.
                    // Copy the CTC values from the Src.
                    if(aeSrc.getAdverseEventTerm().getTerm() != null) aeFound.getAdverseEventTerm().setTerm(aeSrc.getAdverseEventTerm().getTerm());
                    if(aeSrc.getOtherSpecify() != null) aeFound.setOtherSpecify(aeSrc.getOtherSpecify());
                    if(((AdverseEventCtcTerm) aeSrc.getAdverseEventTerm()).getCtcTerm() != null) ((AdverseEventCtcTerm) aeFound.getAdverseEventTerm()).setCtcTerm(((AdverseEventCtcTerm) aeSrc.getAdverseEventTerm()).getCtcTerm());

                    if ( aeSrc.getAdverseEventTerm().isOtherRequired()) {
                        if(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm() != null) aeFound.getAdverseEventMeddraLowLevelTerm().setTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm());
                        if(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm() != null) aeFound.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm());
                    }
                } else { // Refers to MEDRAA terminology.
                    if ( aeFound.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm && aeSrc.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm ) {
                        if(aeSrc.getLowLevelTerm() != null) aeFound.setLowLevelTerm(aeSrc.getLowLevelTerm());
                        if(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm() != null) aeFound.getAdverseEventMeddraLowLevelTerm().setTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getTerm());
                        if(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm() != null) aeFound.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(aeSrc.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm());
                    }

                }

                if(aeSrc.getEventApproximateTime() != null) aeFound.setEventApproximateTime(aeSrc.getEventApproximateTime());
                if(aeSrc.getEventLocation() != null) aeFound.setEventLocation(aeSrc.getEventLocation());

                if(aeSrc.getGrade() != null) aeFound.setGrade(aeSrc.getGrade());
                // Update only when there is a valid value is present.
                if ( aeSrc.getGradedDate() != null) aeFound.setGradedDate(aeSrc.getGradedDate());

                if(aeSrc.getDetailsForOther() != null) aeFound.setDetailsForOther(aeSrc.getDetailsForOther());
                if(aeSrc.getComments() != null) aeFound.setComments(aeSrc.getComments());
                if(aeSrc.getParticipantAtRisk() != null) aeFound.setParticipantAtRisk(aeSrc.getParticipantAtRisk());
                if(aeSrc.getRequiresReporting() != null) aeFound.setRequiresReporting(aeSrc.getRequiresReporting());
                if(aeSrc.getReported() != null) aeFound.setReported(aeSrc.getReported());


                aeFound.getOutcomes().clear();
                for(Outcome outcomeSrc : aeSrc.getOutcomes()) aeFound.addOutComeIfNecessary(outcomeSrc);
            }

         }
    }
}
