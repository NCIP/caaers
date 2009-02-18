package gov.nih.nci.cabig.caaers.web.ae;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.ae.AeTab.AeInputFieldCreator;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 *
 * @author Sameer Sawant
 */
public class CreateExpeditedReporterTab extends AeTab{
	private static final Log log = LogFactory.getLog(CreateExpeditedReporterTab.class);
	private static final String RPD_FIELD_GROUP = "rpd";
	
	public CreateExpeditedReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter",
                "ae/createExpeditedReporter");
    }
	
	@Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.REPORTER_INFO_SECTION};
    }
		
	
	@Override
	public Map<String, Object> referenceData(HttpServletRequest request,ExpeditedAdverseEventInputCommand cmd) {
		int i = 0;
		
		CreateExpeditedAdverseEventCommand command = (CreateExpeditedAdverseEventCommand)cmd;
		
		// evalutate available report definitions per session.
		command.findAllReportDefintionNames();
		
		//find the required report definitions (the map is refreshed by this)
		command.findRequiredReportDefinitions();
		
		//refresh the reportDefinition indicator map
		command.refreshReportDefinitionRequiredIndicatorMap();
		
		//refresh the reportDefinition map.
		command.refreshReportDefinitionMap();
		
		//refresh the status map.
		command.refreshReportStatusMap();
		
		Map<String, Object> refdata = super.referenceData(request, command);
		
		//create the 3 column display for all report definitions.
		Map<String, ReportDefinitionDisplayTable> allReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		Map<String, ReportDefinitionDisplayTable> selectedReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		
		for(ReportDefinition rpDef : command.getAllReportDefinitions()){
			ReportDefinitionDisplayTable rdTable = new ReportDefinitionDisplayTable(command.getReportStatusMap().get(rpDef.getId()),
					command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()), 
					InputFieldFactory.createCheckboxField("reportDefinitionMap[" + rpDef.getId() + ']',
                    		rpDef.getLabel() + " ("  + rpDef.getOrganization().getName()  + ')'));
			
			allReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable );
			
			//only add to selected map, if it is 'required' or 'selected'.
			if(command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()) || command.getReportDefinitionMap().get(rpDef.getId())){
				selectedReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable);
			}
			
			i++;
		}
		refdata.put("rpdAllTable", allReportDefDisplayTableMap);
		refdata.put("rpdSelectedTable", selectedReportDefDisplayTableMap);
		// TODO Auto-generated method stub
		return refdata;
	}
	
	@Override
	protected void validate(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,
			Errors errors) {
		if( (command.getNextPage() >= getNumber())){
			if(command.getSelectedReportDefinitions().isEmpty()){
				errors.reject("AT_LEAST_ONE_REPORT", "At least one expedited report must be selected to proceed");
			}
		}
	}
	
	@Override
	public void postProcess(HttpServletRequest request,	ExpeditedAdverseEventInputCommand cmd, Errors errors) {
		CreateExpeditedAdverseEventCommand command = (CreateExpeditedAdverseEventCommand)cmd;
		
		
		if(!errors.hasErrors() && (command.getNextPage() >= getNumber())){
			command.removeUnselectedReports();
			command.instantiateNewlySelectedReports();
			
			// find the new mandatory sections
	        command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));
	        
	        //initialize mandatory section fields
	        command.initializeMandatorySectionFields();
	        
	        // refresh the mandatory fields
	        command.refreshMandatoryProperties();

		}
	}
	
  @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        createPersonGroup(creator, "reporter");
        createPersonGroup(creator, "physician");
    }

    private void createPersonGroup(AeInputFieldCreator creator, String person) {
        String base = person + '.';
        InputField title = InputFieldFactory.createTextField(base + "title", "Position Title", false);
        InputFieldAttributes.setSize(title, 50);
        InputField firstNameField = InputFieldFactory.createTextField(base + "firstName", "First name", true);
        InputField middleNameField = InputFieldFactory.createTextField(base + "middleName", "Middle name", false);
        InputField lastNameField = InputFieldFactory.createTextField(base + "lastName", "Last name", true);
        InputField emailField = createContactField(base, ReportPerson.EMAIL, "E-mail address", true);
        InputFieldAttributes.setSize(emailField, 50);


        InputField phoneField = createContactField(base, ReportPerson.PHONE, false);
        phoneField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");

        InputField faxField = createContactField(base, ReportPerson.FAX, false);
        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");


        InputField streetField = InputFieldFactory.createTextField(base + "address.street", "Street");
        InputFieldAttributes.setColumns(streetField, 50);

        InputField cityField = InputFieldFactory.createTextField(base + "address.city", "City");
        InputFieldAttributes.setColumns(cityField, 50);

        InputField stateField = InputFieldFactory.createTextField(base + "address.state", "State");
        InputFieldAttributes.setColumns(stateField, 50);


        InputField zipField = InputFieldFactory.createZipCodeField(base + "address.zip", "Zip", false);
        InputFieldAttributes.setColumns(zipField, 5);


        creator.createFieldGroup(person, StringUtils.capitalize(person) + " details",
                title, firstNameField, middleNameField, lastNameField, emailField, phoneField, faxField, streetField, cityField, stateField, zipField);
    }

    private InputField createContactField(String base, String contactType, boolean required) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType), required);
    }

    private InputField createContactField(String base, String contactType, String displayName, boolean required) {
        if (contactType.equals(ReportPerson.PHONE) || contactType.equals(ReportPerson.FAX)) {
            return InputFieldFactory.createPhoneField(base + "contactMechanisms[" + contactType + ']',
                    displayName, required);

        } else {
            return InputFieldFactory.createEmailField(base + "contactMechanisms[" + contactType + ']',
                    displayName, required);

        }
    }

}