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
        command.setParticipant(command.getAssignment().getParticipant());
        tab.validate(command, errors);
        assertEquals(0, errors.getFieldErrorCount("assignment.diseaseHistory.diagnosisDate"));
    }

    public void testValidateDiseaseInformationInvalidDate() {
        getAssignment().setDiseaseHistory(new StudyParticipantDiseaseHistory());
        getAssignment().getDiseaseHistory().setDiagnosisDate(new DateValue(2010, 15, 12));
        command.setParticipant(command.getAssignment().getParticipant());
        tab.validate(command, errors);
        assertEquals(1, errors.getFieldErrorCount("assignment.diseaseHistory.diagnosisDate"));
        assertEquals("SAE_036", errors.getFieldError("assignment.diseaseHistory.diagnosisDate").getCode());
    }

}