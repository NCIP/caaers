/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User: Ramakrishna Gundala
 * Date: 4/29/13
 */
public class AdditionalInformationMigrator implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        AdditionalInformation src = aeReportSrc.getAdditionalInformation();
        AdditionalInformation dest = aeReportDest.getAdditionalInformation();
        
        dest.setAutopsyReport(src.getAutopsyReport());
		dest.setConsults(src.getConsults());
		dest.setDischargeSummary(src.getDischargeSummary());
		dest.setFlowCharts(src.getFlowCharts());
		dest.setIrbReport(src.getIrbReport());
		dest.setLabReports(src.getLabReports());
		dest.setObaForm(src.getObaForm());
		dest.setOther(src.getOther());
		dest.setPathologyReport(src.getPathologyReport());
		dest.setProgressNotes(src.getProgressNotes());
		dest.setRadiologyReports(src.getRadiologyReports());
		dest.setReferralLetters(src.getReferralLetters());
		dest.setOtherInformation(src.getOtherInformation());
		
		for(AdditionalInformationDocument srcAdditionalInformationDocument: src.getAdditionalInformationDocuments()){
			AdditionalInformationDocument document = new AdditionalInformationDocument();
			document.setFileId(srcAdditionalInformationDocument.getFileId());
			document.setFileName(srcAdditionalInformationDocument.getFileName());
			document.setFilePath(srcAdditionalInformationDocument.getFilePath());
			document.setFileSize(srcAdditionalInformationDocument.getFileSize());
			document.setOriginalFileName(srcAdditionalInformationDocument.getOriginalFileName());
			document.setRelativePath(srcAdditionalInformationDocument.getRelativePath());
			if(srcAdditionalInformationDocument.getAdditionalInformationDocumentType() != null){
				document.setAdditionalInformationDocumentType(AdditionalInformationDocumentType.valueOf
						(srcAdditionalInformationDocument.getAdditionalInformationDocumentType().name()));
			}
			dest.getAdditionalInformationDocuments().add(document);
		}

    }

}
