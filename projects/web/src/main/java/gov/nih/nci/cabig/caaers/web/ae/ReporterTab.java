package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections15.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private static final Log log = LogFactory.getLog(ReporterTab.class);
    private static final String REPORT_DEFN_LIST_PARAMETER ="reportDefnList";
    
    private ConfigProperty configurationProperty;

    private EvaluationService evaluationService;

    public ReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter",
                        "ae/reporter");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.REPORTER_INFO_SECTION;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        createPersonGroup(creator, "reporter");
        createPersonGroup(creator, "physician");
    }

    private void createPersonGroup(AeInputFieldCreator creator, String person) {
        String base = person + '.';
        InputField title = InputFieldFactory.createSelectField(base + "title", "Title",false,
        		WebUtils.collectOptions(configurationProperty.getMap().get("titleType"), "code", "desc", "Please select"));
        InputField firstNameField = InputFieldFactory.createTextField(base + "firstName", "First name", true);
        InputField middleNameField = InputFieldFactory.createTextField(base + "middleName","Middle name", false);
        InputField lastNameField = InputFieldFactory.createTextField(base + "lastName","Last name", true);
        InputField emailField = createContactField(base, ReportPerson.EMAIL, "E-mail address", FieldValidator.NOT_NULL_VALIDATOR);
        InputFieldAttributes.setSize(emailField, 50);

        
        InputField phoneField =createContactField(base, ReportPerson.PHONE, FieldValidator.PHONE_VALIDATOR);
        phoneField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        InputField faxField = createContactField(base, ReportPerson.FAX, FieldValidator.PHONE_VALIDATOR);
        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        
        InputField streetField = InputFieldFactory.createTextField(base + "address.street", "Street");
        InputFieldAttributes.setColumns(streetField,50);

        InputField cityField = InputFieldFactory.createTextField(base + "address.city", "City");
        InputFieldAttributes.setColumns(cityField,50);
        
        InputField stateField = InputFieldFactory.createTextField(base + "address.state", "State");
        InputFieldAttributes.setColumns(stateField,50);
        
        InputField zipField = InputFieldFactory.createTextField(base + "address.zip", "Zip");
        InputFieldAttributes.setColumns(zipField,5);
        
        
        creator.createFieldGroup(person, StringUtils.capitalize(person) + " details",
        		title,firstNameField, middleNameField,lastNameField, emailField, phoneField, faxField,  streetField, cityField, stateField, zipField);
    }

    private InputField createContactField(String base, String contactType, FieldValidator... validators) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType));
    }

    private InputField createContactField(String base, String contactType, String displayName,FieldValidator... validators) {
        return InputFieldFactory.createTextField(base + "contactMechanisms[" + contactType + ']',
                        displayName, validators);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        super.onDisplay(request, command);
        
        boolean severe = false;
        for (AdverseEvent event : command.getAeReport().getAdverseEvents()) {
            severe |= evaluationService.isSevere(command.getAssignment(), event);
        }
        request.setAttribute("oneOrMoreSevere", severe);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    /**
     * We do the following things here 
     * 	1. Find the newly checked report definitions 
     *  2. Remove the unselected report definitions
     *  3. Create the reports (by calling evaluation service)
     *  4. Save the AEReport
     *  5. Fetch the mandatorySections based on the reports selected 
     *  6. Pre-instantiate the mandatory section's repeating fields (biz rule) 
     *  7. Refresh the mandatory fields map.
     */
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {

        // only do postProcess, if we are moving forward or if the AE report is already persistent
        if ((command.getNextPage() >= getNumber()) || command.getAeReport().getId() != null) {
        	
            List<ReportDefinition> newlySelectedDefs = newlySelectedReportDefinitions(command);

            if (newlySelectedDefs != null) {
                evaluationService.addOptionalReports(command.getAeReport(), newlySelectedDefs);
            }

            if (command.getAeReport().getReports().size() > 0) {
                command.save();
            }
            // find the new mandatory sections
            command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));

            // pre-init the mandatory section fields
            command.initializeMandatorySectionFields(getExpeditedReportTree());

            // refresh the mandatory fields
            command.refreshMandatoryProperties();
        }

    }
    
    private List<ReportDefinition> newlySelectedReportDefinitions(ExpeditedAdverseEventInputCommand command) {
    		List<ReportDefinition> instantiatedReportDefs = command.getInstantiatedReportDefinitions();
    		List<ReportDefinition> difference = ListUtils.subtract(command.getSelectedReportDefinitions(),instantiatedReportDefs);
    		return difference;
    }
    

    @Required
    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    
    @Required
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
}
