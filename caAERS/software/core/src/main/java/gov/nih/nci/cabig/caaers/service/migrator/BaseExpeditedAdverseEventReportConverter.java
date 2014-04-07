/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BaseAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportedAE;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import org.apache.commons.lang.StringUtils;

/**
 * @author chandrasekaravr
 */

public class BaseExpeditedAdverseEventReportConverter {
	
	private ExpeditedAdverseEventReportConverterUtility expeditedAdverseEventReportConverterUtility;
    
	public ExpeditedAdverseEventReport convert( BaseAdverseEventReport aeReportDto) {
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();

		aeReport.setCreatedAt(XMLUtil.toTimestamp(aeReportDto.getCreatedAt()));
        aeReport.setExternalId(aeReportDto.getExternalId());
        //reporting period
        if(aeReportDto.getAdverseEventReportingPeriod() != null) {
            AdverseEventReportingPeriod reportingPeriod = expeditedAdverseEventReportConverterUtility.convertAdverseEventReportingPeriod(aeReportDto.getAdverseEventReportingPeriod());
            reportingPeriod.addAeReport(aeReport);
        }
        
        //Adverse Events
        for(ReportedAE aeType : aeReportDto.getReportedAE()){
            if ( aeReport.getReportingPeriod() != null) {

                AdverseEvent ae = new AdverseEvent();
                ae.setExternalId(aeType.getExternalId());
                aeReport.getReportingPeriod().addAdverseEvent(ae);
                aeReport.addAdverseEvent(ae);
                
            }
        }
       
        //reporter
        if(aeReportDto.getReporter() != null){
            aeReport.setReporter(expeditedAdverseEventReportConverterUtility.convertReporter(aeReportDto.getReporter()));
        }


		//physician
        if(aeReportDto.getPhysician() != null){
            aeReport.setPhysician(expeditedAdverseEventReportConverterUtility.convertPhysician(aeReportDto.getPhysician()));
        }

        
        //Reports
		for(ReportType xmlReportType : aeReportDto.getReport()){
            Report report = expeditedAdverseEventReportConverterUtility.convertReport(xmlReportType, null ); //no submitter info
			aeReport.addReport(report);

            //special case if external data collection do not have external Id, add case number
            if(StringUtils.isEmpty(aeReport.getExternalId())) aeReport.setExternalId(report.getCaseNumber());
		}
		
		// Set the study Information to the Source Report.
		return aeReport;
	}

	public void setExpeditedAdverseEventReportConverterUtility(
			ExpeditedAdverseEventReportConverterUtility expeditedAdverseEventReportConverterUtility) {
		this.expeditedAdverseEventReportConverterUtility = expeditedAdverseEventReportConverterUtility;
	}
}