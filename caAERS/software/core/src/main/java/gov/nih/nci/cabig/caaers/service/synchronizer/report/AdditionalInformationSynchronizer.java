/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author medaV
 */
public class AdditionalInformationSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        AdditionalInformation src = xmlAeReport.getAdditionalInformation();
        AdditionalInformation dest = dbAeReport.getAdditionalInformation();

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

        // Check for duplicates
        for(AdditionalInformationDocument srcAdditionalInformationDocument: src.getAdditionalInformationDocuments()){

            AdditionalInformationDocument document = null ;
            document = findDuplicateDocuments(document, dest.getAdditionalInformationDocuments());

            // Only Add when it is not already existed.
            if (document == null ) {
                document = new AdditionalInformationDocument();
                dest.getAdditionalInformationDocuments().add(document);
            }
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

        }
    }

    private AdditionalInformationDocument findDuplicateDocuments(AdditionalInformationDocument document, List<AdditionalInformationDocument> existingDocs) {

        AdditionalInformationDocument result = null;

        for ( AdditionalInformationDocument docIter: existingDocs) {
            if ( docIter.getFileName().equals(document.getFileName()) && docIter.getFilePath().equals(document.getFilePath())) {
                result = document;
                break;
            }
        }
        return result;
    }

}
