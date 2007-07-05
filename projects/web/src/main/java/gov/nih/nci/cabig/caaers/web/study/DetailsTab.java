package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import java.util.Map;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class DetailsTab extends StudyTab {
    private CtcDao ctcDao;

    public DetailsTab() {
        super("Study Details", "Details", "study/study_details");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "phaseCodeRefData");
        addConfigMapToRefdata(refdata, "sponsorCodeRefData");
        refdata.put("ctcVersion", ctcDao.getAll());
        return refdata;
    }

    @Override
    public void validate(Study command, Errors errors) {
        boolean longTitle = command.getLongTitle() == null || "".equals(command.getLongTitle());
        boolean ctc = command.getCtcVersion() == null ? true : false;
        if (longTitle) errors.rejectValue("longTitle", "REQUIRED", "Missing Long Title");
        if (ctc) errors.rejectValue("ctcVersion", "REQUIRED", "Missing CTC Version");

    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
}
