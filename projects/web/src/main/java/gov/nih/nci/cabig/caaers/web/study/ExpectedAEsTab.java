package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.fields.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */

public class ExpectedAEsTab extends StudyTab {

    private static final String MAIN_FIELD_GROUP = "main";

    public ExpectedAEsTab() {
        super("Expected AEs", "Expected AEs", "study/study_expected_aes");
    }

    public Map<String, Object> referenceData(HttpServletRequest request, Study command) {
        boolean isCTCStudy = command.getAeTerminology().getTerm() == Term.CTC;
        if (isCTCStudy) {
            command.getAeTerminology().getCtcVersion().getCategories();
        }
        return super.referenceData(request, command);
    }
}