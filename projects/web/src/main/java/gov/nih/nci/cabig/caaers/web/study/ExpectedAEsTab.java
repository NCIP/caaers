package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.fields.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;

/**
 * @author Ion C. Olaru
 */

public class ExpectedAEsTab extends StudyTab {

    private static final String MAIN_FIELD_GROUP = "main";

    public ExpectedAEsTab() {
        super("Expected AEs", "Expected AEs", "study/study_expected_aes");
    }

    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand command) {
        boolean isCTCStudy = command.getStudy().getAeTerminology().getTerm() == Term.CTC;
        if (isCTCStudy) {
            command.getStudy().getAeTerminology().getCtcVersion().getCategories();
        }
        return super.referenceData(request, command);
    }

    public void postProcess(HttpServletRequest request, StudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        System.out.println();
    }

    protected void validate(StudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);

        AbstractExpectedAE aee = command.getStudy().checkExpectedAEUniqueness();
        if (aee == null) return;

        String name = null;
        if (command.getStudy().getAeTerminology().getMeddraVersion() != null) name = ((ExpectedAEMeddraLowLevelTerm)aee).getTerm().getFullName();
        if (command.getStudy().getAeTerminology().getCtcVersion() != null) {
            name = ((ExpectedAECtcTerm)aee).getTerm().getFullName();
            if (aee.isOtherRequired()) name = name + ", " + ((ExpectedAECtcTerm)aee).getOtherMeddraTerm().getMeddraTerm();
        }

        errors.reject("DUPLICATE_EXPECTED_AE", new Object[] {name}, "ERR.");
    }
}