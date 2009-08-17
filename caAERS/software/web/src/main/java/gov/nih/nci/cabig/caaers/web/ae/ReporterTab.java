package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private static final Log log = LogFactory.getLog(ReporterTab.class);

    public ReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter", "ae/reporter");
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.REPORTER_INFO_SECTION};
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

        creator.createFieldGroup(person, StringUtils.capitalize(person) + " details", title, firstNameField, middleNameField, lastNameField, emailField, phoneField, faxField, streetField, cityField, stateField, zipField);
    }

    private InputField createContactField(String base, String contactType, boolean required) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType), required);
    }

    private InputField createContactField(String base, String contactType, String displayName, boolean required) {
        if (contactType.equals(ReportPerson.PHONE) || contactType.equals(ReportPerson.FAX)) {
            return InputFieldFactory.createPhoneField(base + "contactMechanisms[" + contactType + ']', displayName, required);
        } else {
            return InputFieldFactory.createEmailField(base + "contactMechanisms[" + contactType + ']', displayName, required);
        }
    }
    
    
    public void processReports(HttpServletRequest request,EditExpeditedAdverseEventCommand command){
    	ReviewAndReportResult reviewResult = (ReviewAndReportResult)request.getSession().getAttribute("reviewResult"); 
    	if(reviewResult != null){
    		
    		//modify the signatures of the adverse events in this report.
	    	command.getAeReport().updateSignatureOfAdverseEvents();
	    

	    	//save the data collection. 
	    	command.save();
	    	
	    	//process the reports
	    	reviewResult.updateBaseDateOnCreateList();
	    	reportRepository.processReports(command.getAeReport(), reviewResult.getReportsToAmmendList(), reviewResult.getReportsToUnAmendList(), 
	    			reviewResult.getReportsToWithdraw(), reviewResult.getCreateList());
	    	
			//-enact workflow
			if(CollectionUtils.isNotEmpty(command.getNewlySelectedReportDefinitions()) && command.getWorkflowEnabled()){
				command.enactWorkflow(command.getAeReport());
			}
	    	
			//- remove review result from session.
			request.getSession().removeAttribute("reviewResult"); 
    	}
    	
    }
    
    @Override
    public void onDisplay(HttpServletRequest request,ExpeditedAdverseEventInputCommand command) {
    	
    	if(!(command instanceof EditExpeditedAdverseEventCommand)) return;
    	
    	ExpeditedAdverseEventReport aeReport = command.getAeReport();
    	if(aeReport.getId() != null && aeReport.getReporter().isSavable()){
    		processReports(request, (EditExpeditedAdverseEventCommand)command);
    	}
    }

    /**
     * There is a chance of modification in reporter page, so suppress the warning
     * if it is from capture adverse events. 
     */
    @Override
    public String generateWarningMessage(ExpeditedAdverseEventInputCommand command) {
    	if(command instanceof EditExpeditedAdverseEventCommand){
    		String screenFlowSource = ((EditExpeditedAdverseEventCommand)command).getScreenFlowSource();
    		if(StringUtils.equals("captureAE", screenFlowSource)){
    			return null;
    		}
    	}
    	return super.generateWarningMessage(command);
    }
    
    /**
     * 
     */
    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand cmd, Errors errors) {
    	//only process if there are no validation errors.
    	if(errors.hasErrors()) return;
    	if(!(cmd instanceof EditExpeditedAdverseEventCommand)) return;
    	
    	EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
    	processReports(request, (EditExpeditedAdverseEventCommand)command);
    }
    
   
    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,	Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    	if(command.getWorkflowEnabled()){
    		if(command.getAeReport().getReporter().getUser() == null){
    			errors.rejectValue("aeReport.reporter.user", "SAE_019","Reporter should be selected in the drop down");
    		}
    		if(command.getAeReport().getPhysician().getUser() == null){
    			errors.rejectValue("aeReport.physician.user", "SAE_020", "Physician should be selected in the drop down");
    		}
    	}
    }
}
