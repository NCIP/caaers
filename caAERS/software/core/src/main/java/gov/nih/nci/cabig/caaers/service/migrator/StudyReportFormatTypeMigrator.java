/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyReportFormatTypeMigrator implements Migrator<Study> {

    public void migrate(Study src, Study dest, DomainObjectImportOutcome<Study> outcome) {
        for (ReportFormat rf : src.getReportFormats()) {
            if (dest.getReportFormat(rf.getReportFormatType()) == null) dest.addReportFormatType(rf.getReportFormatType());
        }
    }
}
