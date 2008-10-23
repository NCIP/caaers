package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */

public class ExpectedAEsTab extends StudyTab {

    public ExpectedAEsTab() {
        super("Expected AEs", "Expected AEs", "study/study_expected_aes");
    }

    public Map<String, Object> referenceData(HttpServletRequest request, Study command) {
        return super.referenceData(request, command);
    }
}