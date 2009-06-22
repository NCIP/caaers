package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.LabCategoryDao;
import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class LabsTab extends AeTab {
    
    private ConfigProperty configurationProperty;
    private LabCategoryDao labCategoryDao;
    private LabTermDao labTermDao;

    public LabsTab() {
        super("Diagnostic test and lab results", ExpeditedReportSection.LABS_SECTION.getDisplayName(), "ae/labs");
    }

    private void addLabValueFields(RepeatingFieldGroupFactory fieldFactory, String propName, String displayName) {
        fieldFactory.addField(createLabValueField(propName, displayName));
        fieldFactory.addField(createLabDateField(propName, displayName));
    }

    private InputField createLabDateField(String propName, String displayName) {
        return InputFieldFactory.createPastDateField(propName + ".date", displayName + " date", false);
    }

    private InputField createLabValueField(String propName, String displayName) {
        return InputFieldFactory.createTextField(propName + ".value", displayName + " value", false);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        refdata.put("labCategories", labCategoryDao.getAll());
        return refdata;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        // InputField labNameField = InputFieldFactory.createAutocompleterField("labTerm", "Lab test
        // name");
        InputField labNameField = InputFieldFactory.createSelectField("labTerm", "Lab test name", false, createOptions());
        InputFieldAttributes.setSize(labNameField, 60);
        InputField otherField = InputFieldFactory.createTextField("other", "Other", false);
        InputFieldAttributes.setSize(otherField, 60);

        InputField siteField = InputFieldFactory.createTextField("site", "Site", false);
        InputField labDateField = InputFieldFactory.createPastDateField("labDate", "Date", false);
        InputField infectiousAgentField = InputFieldFactory.createTextArea("infectiousAgent", "Infectious Agent", false);
        InputFieldAttributes.setColumns(infectiousAgentField, 60);

        creator.createRepeatingFieldGroup("lab", "labs", createNameCreator(), labNameField,
                otherField, InputFieldFactory.createSelectField("units", "Units", false,
                        WebUtils.collectOptions(configurationProperty
                                .getMap().get("labUnitsRefData"), "code",
                                "desc", "Please select")),
                createLabValueField("baseline", "Baseline"), createLabDateField("baseline",
                        "Baseline"), createLabValueField("nadir", "Worst"),
                createLabDateField("nadir", "Worst"), createLabValueField("recovery",
                        "Recovery"), createLabDateField("recovery", "Recovery"),
                siteField, labDateField, infectiousAgentField);
    }

    private RepeatingFieldGroupFactory.DisplayNameCreator createNameCreator() {
        return new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                char c = (char) ('A' + index);
                return "Lab " + c;
            }
        };
    }

    private Map<Object, Object> createOptions() {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        List<LabTerm> list = labTermDao.getAll();
        options.put(" ", "Please select");
        options.put("", "Other, specify");
        if (list != null) {
            for (LabTerm l : list) {
                options.put(l.getId(), l.getTerm());
            }
        }
        return options;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.LABS_SECTION};
    }

    public LabCategoryDao getLabCategoryDao() {
        return labCategoryDao;
    }

    public void setLabCategoryDao(LabCategoryDao labCategoryDao) {
        this.labCategoryDao = labCategoryDao;
    }

    public LabTermDao getLabTermDao() {
        return labTermDao;
    }

    public void setLabTermDao(LabTermDao labTermDao) {
        this.labTermDao = labTermDao;
    }
    
    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command, HttpServletRequest request) {
    	boolean hasEmptyFields =  super.hasEmptyMandatoryFields(command, request);
    	hasEmptyFields |= CollectionUtils.isEmpty(command.getAeReport().getLabs());
    	return hasEmptyFields;
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        int i =0;
        Set<Lab> set = new HashSet<Lab>();
        String propertyName;

        for(Lab lab : command.getAeReport().getLabs()){

            if (StringUtils.isEmpty(lab.getName()) && StringUtils.isEmpty(lab.getOther())) {
//                if (StringUtils.isEmpty(lab.getName())) {
                    propertyName = String.format("aeReport.labs[%d].labTerm", i);
                    errors.rejectValue(propertyName, "SAE_029", new Object[]{lab.getName()}, "Missing Lab Name");
/*

                    if (StringUtils.isEmpty(lab.getOther())) {
                        propertyName = String.format("aeReport.labs[%d].other", i);
                        errors.rejectValue(propertyName, "SAE_030", new Object[]{lab.getName()}, "Missing Lab Other Information");
                    }
*/
//                }
            }
            i++;
        }
        WebUtils.populateErrorFieldNames(command.getRulesErrors(), errors);
    }
}
