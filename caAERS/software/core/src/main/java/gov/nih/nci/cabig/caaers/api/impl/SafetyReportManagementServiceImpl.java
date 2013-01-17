package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class SafetyReportManagementServiceImpl {
	private static Log logger = LogFactory.getLog(SafetyReportManagementServiceImpl.class);
	
	  @Transactional(readOnly=false)
	    public CaaersServiceResponse submitSafetyReport(AdverseEventReport adverseEventReport) {

	        CaaersServiceResponse response = Helper.createResponse();
	        //TODO
	        // implement the core service

	        return response;
	    }

}
