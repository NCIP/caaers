package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;

import java.util.Map;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Rhett Sutphin
*/
public class BasicsTab extends AeTab {
    private CtcDao ctcDao;

    public BasicsTab() {
        super("Enter basic event information", "Basics", "ae/enterBasic");
    }

    @Override
    protected void initFields() {
        addField("main",
            new DateField("aeReport.primaryAdverseEvent.detectionDate", "Detection date", false));
        addField("main", new CollectionSelectField(
            "aeReport.primaryAdverseEvent.grade", "Grade", true,
                Arrays.asList(Grade.values()), "name", "string"));
        addField("main", new CollectionSelectField(
            "aeReport.primaryAdverseEvent.attribution", "Attribution", true,
                Arrays.asList(Attribution.values()), "name", "string"));

        InputField ctcTermField = new AutocompleterField("aeReport.primaryAdverseEvent.ctcTerm", "CTC term", true);
        ctcTermField.setExtraInformation(
            "Type a portion of the CTC term you are looking for.  If you select a category, only terms in that category will be shown.");
        addField("ctcTerm", ctcTermField);
        addField("ctcOther", new TextArea("aeReport.primaryAdverseEvent.detailsForOther", "Other (details)", false));
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("ctcVersions", ctcDao.getAll());
        return refdata;
    }

    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
}
