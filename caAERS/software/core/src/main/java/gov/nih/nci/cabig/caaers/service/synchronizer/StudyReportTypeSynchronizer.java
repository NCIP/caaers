package gov.nih.nci.cabig.caaers.service.synchronizer;

import org.apache.commons.collections.CollectionUtils;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ReportFormat;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

public class StudyReportTypeSynchronizer implements Migrator<Study>{

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		/**
		 *	If none of the below given elements are provided in the XML, then ReportFormats list will be null.
			In this case we do not modify the existing ReportFormats.
			If one or more of the below given elements are provided in the XML, 
			In this case the existing ReportFormats on the study will be replaced with the ones provided.
			
            <xs:element name="reportTypeCaaersXML" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
            <xs:element name="reportTypeAdeersPDF" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
            <xs:element name="reportTypeMedwatchPDF" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
            <xs:element name="reportTypeDCPSAEForm" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
            <xs:element name="reportTypeCIOMSForm" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
            <xs:element name="reportTypeCIOMSAEForm" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
		 */
		
		if(CollectionUtils.isEmpty(xmlStudy.getReportFormats())){
			return;
		}
		
		dbStudy.getReportFormats().clear();
		for(ReportFormat rf : xmlStudy.getReportFormats()){
			dbStudy.addReportFormat(rf);
		}
	}
}