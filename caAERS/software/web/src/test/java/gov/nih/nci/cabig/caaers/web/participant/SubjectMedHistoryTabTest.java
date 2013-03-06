/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantDiseaseHistory;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * @author Ion C. Olaru
 * 
 */
public class SubjectMedHistoryTabTest extends SubjectFlowAbstractTabTestCase {

    protected SubjectMedHistoryTab createTab() {
        tab = new SubjectMedHistoryTab();
        return (SubjectMedHistoryTab)tab;
    }

    public void testValidateDiseaseInformation() {
        getAssignment().setDiseaseHistory(new StudyParticipantDiseaseHistory());
        getAssignment().getDiseaseHistory().setDiagnosisDate(new DateValue(DateUtils.today()));
        tab.validate(command, errors);
        assertEquals(0, errors.getFieldErrorCount("assignment.diseaseHistory.diagnosisDate"));
    }

    public void testValidateDiseaseInformationInvalidDate() {
        getAssignment().setDiseaseHistory(new StudyParticipantDiseaseHistory());
        getAssignment().getDiseaseHistory().setDiagnosisDate(new DateValue(2010, 15, 12));
        tab.validate(command, errors);
        assertEquals(1, errors.getFieldErrorCount("assignment.diseaseHistory.diagnosisDate"));
        assertEquals("SAE_036", errors.getFieldError("assignment.diseaseHistory.diagnosisDate").getCode());
    }

}
