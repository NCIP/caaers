package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

import java.util.Map;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class BasicsTab extends AeTab {
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";
    private static final String CTC_OTHER_FIELD_GROUP = "ctcOther";

    private CtcDao ctcDao;

    public BasicsTab() {
        super("Enter basic AE information", "Basics", "ae/enterBasic");
    }

    @Override
    protected void initFields() {
        addField(MAIN_FIELD_GROUP, new DefaultDateField(
            "aeReport.primaryAdverseEvent.detectionDate", "Detection date", true));
        addField(MAIN_FIELD_GROUP, new CollectionSelectField(
            "aeReport.primaryAdverseEvent.grade", "Grade", true,
                Arrays.asList(Grade.values()), "name", "string"));
        addField(MAIN_FIELD_GROUP, new CollectionSelectField(
            "aeReport.primaryAdverseEvent.attribution", "Attribution", true,
                Arrays.asList(Attribution.values()), "name", "string"));

        AbstractInputField ctcTermField = new AutocompleterField("aeReport.primaryAdverseEvent.ctcTerm", "CTC term", true);
        ctcTermField.setExtraInformation(
            "Type a portion of the CTC term you are looking for.  If you select a category, only terms in that category will be shown.");
        addField(CTC_TERM_FIELD_GROUP, ctcTermField);
        addField(CTC_OTHER_FIELD_GROUP, new DefaultTextArea("aeReport.primaryAdverseEvent.detailsForOther", "Other (specify)", false));
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

    @Override
    public void validate(CreateAdverseEventCommand command, Errors errors) {
        super.validate(command, errors);
        AdverseEvent ae = command.getAeReport().getPrimaryAdverseEvent();
        CtcTerm ctcTerm = ae.getCtcTerm();
        if (ctcTerm != null && ctcTerm.isOtherRequired() && ae.getDetailsForOther() == null) {
            InputField field = getFieldGroups().get(CTC_OTHER_FIELD_GROUP).getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
    }

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
}
