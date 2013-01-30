package gov.nih.nci.cabig.caaers.api.impl;


import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.migrator.ExpeditedAdverseEventReportConverter;
import gov.nih.nci.cabig.caaers.service.migrator.ExpeditedReportMigrator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class SafetyReportManagementServiceImpl {
	private static Log logger = LogFactory.getLog(SafetyReportManagementServiceImpl.class);
	
	/**	Expedited Report Converter. **/
	private ExpeditedAdverseEventReportConverter eaeConverter;
	
	/** Expedited Report Migrator. **/
	private ExpeditedReportMigrator migrator;
	
	/** Report Validation Service. **/
	private ReportValidationService reportValidationService;
	
	  /** The Evaluation Service. */
    private EvaluationService evaluationService;
    
    /** Report Submission Service.**/
    private ReportSubmissionService reportSubmissionService;
	
	@Transactional(readOnly=false)
	public CaaersServiceResponse submitSafetyReport(AdverseEventReport adverseEventReport) {

	   CaaersServiceResponse response = Helper.createResponse();
	   
	   try {
	        
		   // Steps to be followed are
		        
		   // 1. Call the Converter(s) to construct the domain object. 
		   ExpeditedAdverseEventReport aeSrcReport = eaeConverter.convert(adverseEventReport);
		   
		   ExpeditedAdverseEventReport aeDestReport = new ExpeditedAdverseEventReport();
		   
		   // 2. Call the GenericValidator to make sure input is correct.
		      	
		        	
		   // 3. Call the list of Migrators to fully populate the domain object.
		   DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
		   migrator.migrate(aeSrcReport, aeDestReport, outCome);
		   
		   if ( outCome.hasErrors() ) {
			   Helper.populateError(response, "GEN_ORH_001", "Error(s) occured during Migration step.");
			   return response;
		   }
		        	
		   // 4. Call the ReportValidationService to ensure all valid business rules and Report Definitions criteria is met.
		   // 5. Call Report Submission Service 
		   for ( Report  rpt: aeDestReport.getReports()) {
			   ReportSubmittability rptSubmittability =  reportValidationService.isSubmittable(rpt);
			   if ( rptSubmittability.isSubmittable() ) {
				   reportSubmissionService.submitReport(rpt);
			   } else {
				   outCome.addError("ER_ORH_001", "Validation failed for Report " + rpt.getName());
			   }
		   }
		   
		   if ( outCome.hasErrors() ) {
			   Helper.populateError(response, "GEN_ORH_001", "Error(s) occured during Report Submission step.");
			   return response;
		   }
	       
	   }catch(Exception e) {
		   logger.error("Unable to Create a Report from Safety Management Service");
		   Helper.populateError(response, "ERR_ORH_001",e.getMessage() );
	   }
	   
	    return response;
	}
	
	public ExpeditedReportMigrator getMigrator() {
		return migrator;
	}
	public void setMigrator(ExpeditedReportMigrator migrator) {
		this.migrator = migrator;
	}
	public ExpeditedAdverseEventReportConverter getEaeConverter() {
		return eaeConverter;
	}
	public void setEaeConverter(ExpeditedAdverseEventReportConverter eaeConverter) {
		this.eaeConverter = eaeConverter;
	}

	public ReportValidationService getReportValidationService() {
		return reportValidationService;
	}

	public void setReportValidationService(
			ReportValidationService reportValidationService) {
		this.reportValidationService = reportValidationService;
	}

	public EvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

}
