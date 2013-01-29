package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis.utils.StringUtils;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/28/13
 */
public class ReportMigrator implements Migrator<ExpeditedAdverseEventReport> {
	

	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
    	Reporter srcReporter = aeReportSrc.getReporter();
    	if ( aeReportDest.getPhysician() == null ) aeReportDest.setReporter(new Reporter());
    	
    	 if(srcReporter == null ||  (StringUtils.isEmpty(srcReporter.getPrimaryIdentifierValue())  && StringUtils.isEmpty(srcReporter.getEmailAddress()) 
    			 && StringUtils.isEmpty(srcReporter.getFirstName()) && StringUtils.isEmpty(srcReporter.getLastName())))  {
             outcome.addError("ER-RM-1", "Reporter is missing in the source");
             return;
         }
       
        	// 1. Load the study.
        Study study = aeReportDest.getStudy();
        	
        StudyParticipantAssignment assignment = null;
        	
        if ( aeReportDest.getReportingPeriod() != null && aeReportDest.getReportingPeriod().getAssignment() != null ) {
        	assignment = aeReportDest.getReportingPeriod().getAssignment();
        }
        
        if ( assignment == null ) {
        	 outcome.addError("ER-RM-2", "Assignment is missing in the source");
             return;
        }
        
    }

	
	   private Report getReport(Report report) throws Exception {
		   Report r = new Report();
		   r.setId(report.getId());
		   r.setSubmissionMessage(report.getSubmissionMessage());
           r.setSubmittedOn(report.getSubmittedOn());
           r.setStatus(report.getStatus());
		   r.setAdeersReportTypeIndicator(report.deriveAdeersReportTypeIndicator());
		   r.setAssignedIdentifer(report.getAssignedIdentifer());
		   r.setReportDefinition(getReportDefinition(report, report.getReportDefinition()));
		   r.setEmailAddresses(report.getEmailRecipients());
           r.setMandatoryFields(report.getMandatoryFields());
           r.setCaseNumber(report.getCaseNumber());
           if(report.getReportDeliveries() != null)   {
              for(ReportDelivery rd : report.getReportDeliveries()){
                r.addReportDelivery(ReportDelivery.copy(rd));
              } 
           }


           // determine the FDA delivery
           if (report.getReportDefinition().getGroup().getCode().equals("RT_FDA")) {
               for (ReportDelivery rd : report.getReportDeliveries()) {
                   if (rd.getDeliveryStatus().equals(DeliveryStatus.DELIVERED)) {
                        r.setSubmittedToFDA("Yes");
                   }
               }
           }

		   return r;
	   }

	   private ReportDefinition getReportDefinition(Report report, ReportDefinition rd) throws Exception {
		   ReportDefinition reportDefinition = new ReportDefinition();
		   reportDefinition.setId(rd.getId());
		   reportDefinition.setDuration(rd.getDuration());
		   reportDefinition.setDescription(rd.getDescription());
		   reportDefinition.setLabel(rd.getLabel());
		   reportDefinition.setHeader(rd.getHeader());
		   reportDefinition.setFooter(rd.getFooter());
		   reportDefinition.setTimeScaleUnitType(rd.getTimeScaleUnitType());
		   reportDefinition.setGroup(rd.getGroup());
           reportDefinition.setDeliveryDefinitionsInternal(adjustDeliveryDefinitions(report, rd.getDeliveryDefinitions()));
		   return reportDefinition;
	   }

        /**
         * This method adds the deliveryStatus to every Delivery definition
         * The delivery status is computed from Report.deliveries.deliveryStatus
         * with delivery.reportDeliveryDefinition = current ReportDeliveryDefinition
         *
         * */
        private List<ReportDeliveryDefinition> adjustDeliveryDefinitions(Report report, List<ReportDeliveryDefinition> ddl) {
            if (report == null) return ddl;
            List<ReportDeliveryDefinition> rddList = new ArrayList<ReportDeliveryDefinition>();
            for (ReportDeliveryDefinition rdd : ddl) {
                for (ReportDelivery rd : report.getReportDeliveries()) {
                    if (rd.getReportDeliveryDefinition().getId().equals(rdd.getId())) {
                        rdd.setStatus(rd.getDeliveryStatus().getName());
                    }
                }
                rddList.add(ReportDeliveryDefinition.copy(rdd));
            }
            return rddList;
        }
}
