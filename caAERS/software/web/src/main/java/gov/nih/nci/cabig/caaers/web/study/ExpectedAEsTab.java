/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.ExpectedAETermSorter;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

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
        boolean isAjax = request.getParameter(StudyController.AJAX_SUBVIEW_PARAMETER) != null;

        if (isCTCStudy) {
            command.getStudy().getAeTerminology().getCtcVersion().getCategories();
            if (!isAjax) Collections.sort(command.getStudy().getExpectedAECtcTerms(), new ExpectedAETermSorter());
        } else {
            if (!isAjax) Collections.sort(command.getStudy().getExpectedAEMeddraLowLevelTerms(), new ExpectedAETermSorter());
        }
        return super.referenceData(request, command);
    }

    public void postProcess(HttpServletRequest request, StudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        request.setAttribute("tabFlashMessage", messageSource.getMessage(String.format("MSG_study.%s.flash_message", this.getClass().getSimpleName()), null, Locale.getDefault()));

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
