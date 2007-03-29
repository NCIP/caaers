package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class BasicsTab extends AeTab {
    private static final String REPORT_FIELD_GROUP = "report";
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";
    private static final String CTC_OTHER_FIELD_GROUP = "ctcOther";

    private CtcDao ctcDao;
    private InputFieldGroup reportFieldGroup;
    private RepeatingFieldGroupFactory mainFieldFactory, ctcTermFieldFactory, ctcOtherFieldFactory;

    public BasicsTab() {
        super("Enter basic AE information", "Basics", "ae/enterBasic");

        reportFieldGroup = new DefaultInputFieldGroup(REPORT_FIELD_GROUP);
        reportFieldGroup.getFields().add(new DefaultDateField(
            "aeReport.detectionDate", "Detection date", true));

        mainFieldFactory = new RepeatingFieldGroupFactory(MAIN_FIELD_GROUP, "aeReport.adverseEvents");
        mainFieldFactory.addField(new CollectionSelectField("grade", "Grade", true,
                Arrays.asList(Grade.values()), "name", null));
        mainFieldFactory.addField(new CollectionSelectField(
            "hospitalization", "Hospitalization", true,
                Arrays.asList(Hospitalization.values()), "name", "displayName"));
        mainFieldFactory.addField(new BooleanSelectField(
            "expected", "Expected", true));
        mainFieldFactory.addField(new DefaultTextArea(
            "comments", "Comments", false));

        ctcTermFieldFactory = new RepeatingFieldGroupFactory(CTC_TERM_FIELD_GROUP, "aeReport.adverseEvents");
        AutocompleterField ctcTermField = new AutocompleterField("ctcTerm", "CTC term", true);
        ctcTermField.getAttributes().put(InputField.DETAILS,
            "Type a portion of the CTC term you are looking for.  " +
            "If you select a category, only terms in that category will be shown.");
        ctcTermFieldFactory.addField(ctcTermField);

        ctcOtherFieldFactory = new RepeatingFieldGroupFactory(CTC_OTHER_FIELD_GROUP, "aeReport.adverseEvents");
        ctcOtherFieldFactory.addField(new DefaultTextArea("detailsForOther", "Other (specify)", false));
    }

    @Override
    protected List<InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        List<InputFieldGroup> groups = new LinkedList<InputFieldGroup>();
        groups.add(reportFieldGroup);
        int aeCount = command.getAeReport().getAdverseEvents().size();
        for (int i = 0 ; i < aeCount ; i++) {
            groups.add(mainFieldFactory.createGroup(i));
            groups.add(ctcTermFieldFactory.createGroup(i));
            groups.add(ctcOtherFieldFactory.createGroup(i));
        }
        return groups;
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
    protected void validate(
        AdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        // TODO: validate that there is at least one AE
        for (ListIterator<AdverseEvent> lit = command.getAeReport().getAdverseEvents().listIterator(); lit.hasNext();) {
            AdverseEvent ae =  lit.next();
            validateAdverseEvent(ae, lit.previousIndex(), fieldGroups, errors);
        }
    }

    private void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
        CtcTerm ctcTerm = ae.getCtcTerm();
        if (ctcTerm != null && ctcTerm.isOtherRequired() && ae.getDetailsForOther() == null) {
            InputField field = groups.get(CTC_OTHER_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
    }

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
}
