package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantDiseaseHistory;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * @author Ion C. Olaru
 * 
 */
public class SubjectMedHistoryTabTest extends SubjectFlowAbstractTabTest {

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
        getAssignment().getDiseaseHistory().setDiagnosisDate(new Date("17/17/2009"));
        tab.validate(command, errors);
        assertEquals(1, errors.getFieldErrorCount("assignment.diseaseHistory.diagnosisDate"));
        assertEquals("SAE_035", errors.getFieldError("assignment.diseaseHistory.diagnosisDate").getCode());
    }

}