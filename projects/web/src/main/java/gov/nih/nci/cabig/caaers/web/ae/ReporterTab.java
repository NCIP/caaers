package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Physician;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private ResearchStaffDao researchStaffDao;

    public ReporterTab() {
        super("Reporter info", "Reporter", "ae/reporter");
    }

    @Override
    public void preProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        if (command.getAeReport().getReporter() == null) {
            command.getAeReport().setReporter(new Reporter());
        }
        if (command.getAeReport().getPhysician() == null) {
            command.getAeReport().setPhysician(new Physician());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(createPersonGroup("reporter"));
        map.addInputFieldGroup(createPersonGroup("physician"));
        return map;
    }

    private InputFieldGroup createPersonGroup(String person) {
        InputFieldGroup group = new DefaultInputFieldGroup(person, StringUtils.capitalize(person) + " details");
        String base = "aeReport." + person  + '.';
        group.getFields().add(new DefaultTextField(base + "firstName", "First name", true));
        group.getFields().add(new DefaultTextField(base + "middleName", "Middle name", false));
        group.getFields().add(new DefaultTextField(base + "lastName", "Last name", true));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.EMAIL, "E-mail address", true));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.PHONE));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.FAX));
        return group;
    }

    private InputField createContactField(String base, String contactType) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType), false);
    }

    private InputField createContactField(
        String base, String contactType, String displayName, boolean required
    ) {
        return new DefaultTextField(
            base + "contactMechanisms[" + contactType + ']', displayName, required);
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        // TODO: this probably ought to be limited by Site, at least
        refdata.put("staffRefData", researchStaffDao.getAll());
        return refdata;
    }

    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }

    @Override
    public boolean isAllowDirtyBack() {
        return false;
    }

    public ResearchStaffDao getResearchStaffDao() {
        return researchStaffDao;
    }

    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }
}
