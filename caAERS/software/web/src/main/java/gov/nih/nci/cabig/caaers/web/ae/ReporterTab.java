package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
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
    private static final String REPORT_DEFN_LIST_PARAMETER = "reportDefnList";
    private static final String REPORT_ID_PARAMETER = "reportId";
    private static final String ACTION_PARAMETER = "action";


    protected NowFactory nowFactory;

    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

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
/*
 * BJ =============== Commented
    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand cmd) {
    	EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        super.onDisplay(request, command);

        boolean severe = true;
        request.setAttribute("oneOrMoreSevere", severe);
        
        //only process if we are editing the expedited report (ammend or edit flow)
        if (command.getAeReport().getId() != null) {
        	//Initialize, the newly selected report definition.
        	command.setNewlySelectedDefs(command.getSelectedReportDefinitions());
        	
        	//classify the newlySelectedReports into 2 list - 
            // a. newlySelectedSponsorReports
            // b. otherSelectedReports
        	command.classifyNewlySelectedReportsDefinitons();
        	
        	//Populate a map existingReportMap with the reports already existing.
        	command.initializeExistingReportMap();

        	String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
            if(StringUtils.equals("amendReport", action)){
            	processAmendReportFlow(command);
            	command.createReports(true);
            	
            	// Amend the reports to be amended
                command.amendReports();
            }else if(StringUtils.equals("editReport", action)){
            	processEditReportFlow(command);
            	command.createReports(false);
            }
            // Withdraw the reports to be withdrawn
            command.withdrawReports();

            if (command.getAeReport().getReports().size() > 0) {
                command.save();
            }
            
            //refresh the mandatory sections and properties
            command.refreshMandatorySectionsAndProperties();

            command.setSelectedReportDefinitions(new ArrayList<ReportDefinition>());
            
            // Remove the attributes from the session
            request.getSession().removeAttribute(ACTION_PARAMETER);
        }
        
    }
*/
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
    	
    	//- review result from session. 
		ReviewAndReportResult reviewResult = (ReviewAndReportResult)request.getSession().getAttribute("reviewResult"); 
    	
		EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
		if(reviewResult != null){
			
			//modify the signatures of the adverse events in this report.
	    	command.getAeReport().updateSignatureOfAdverseEvents();
	    	
	    	//save the data collection. 
	    	command.save();
			
			//- amend reports
	    	List<Report> toAmendList = new ArrayList<Report>();
	    	for(Report report : reviewResult.getReportsToAmmendList()){
	    		Report toAmend = command.getAeReport().findReportById(report.getId());
	    		if(toAmend != null) toAmendList.add(toAmend);
	    	}
			command.amendReports(toAmendList);
			
	    	
			//-withdraw reports
			List<Report> toWithdrawList = new ArrayList<Report>();
	    	for(Report report : reviewResult.getReportsToWithdraw()){
	    		Report toWithdraw = command.getAeReport().findReportById(report.getId());
	    		if(toWithdraw != null) toWithdrawList.add(toWithdraw);
	    	}
			command.withdrawReports(toWithdrawList);
			
			
			//-create reports & enact workflow.
			command.createReports(command.getNewlySelectedReportDefinitions(), reviewResult.getBaseDateMap(), reviewResult.getManualSelectionIndicatorMap());
			
			//-enact workflow
			if(command.getWorkflowEnabled()){
				command.enactWorkflow(command.getAeReport());
			}
			
        	
			//- remove review result from session.
			request.getSession().removeAttribute("reviewResult"); 
		}
		
        /*
        BJ : ===========================
        String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
        if (StringUtils.equals("createNew", action)) {
        	processForCreateNewFlow(command);
        	// Remove the attributes from the session
            request.getSession().removeAttribute(ACTION_PARAMETER);
        }*/
       
    }
    
    
//    
//    /**
//     * This method takes care of extra-processing incase of Amend-Report flow.
//     *  - Find the reports that are to be amended, based on the newly selected report. 
//     *     ie. we will ammend an amendable report only if the newly selected reports list, has an amendable report from the same organization.
//     *     
//     */
//    public void processAmendReportFlow(EditExpeditedAdverseEventCommand command){
//
//    	
//    	command.populateCreationAmendmentAndWithdrawlList();
//    	
////    	// Check if existing amendable & submitted reports are present in the newlySelectedDefs.
////    	// If not they are withdrawn.
////    	// This is needed for the following scenario - 
////    	// The aeReport contains a 10 day report (SUBMITTED)
////    	// The users amends it with a 5 day reportDefinition
////    	// In this case we want to change the status of the 10 day report to ReportStatus.REPLACED.
////    	Map<ReportDefinition, Boolean> newlySelectedReportDefinitionsMap = new HashMap<ReportDefinition, Boolean>();
////    	for(ReportDefinition rd: command.getNewlySelectedDefs()){
////    		if(!newlySelectedReportDefinitionsMap.containsKey(rd))
////    			newlySelectedReportDefinitionsMap.put(rd, true);
////    	}
////    	for(Report report: command.getAeReport().getReports()){
////    		if(report.isSubmitted() && report.getReportDefinition().getAmendable() && !newlySelectedReportDefinitionsMap.containsKey(report.getReportDefinition()))
////    			command.getReportListForWithdrawal().add(report);
////    	}
//    }
//    
//    /**
//     * This method takes care of extra-processing incase of Edit-Report flow.
//     *  - If there is a PENDING, INPROCESS, FAILED report already available, dont create them. 
//     *  -
//     * @param command
//     */
//    public void processEditReportFlow(EditExpeditedAdverseEventCommand command){
//    	
//    	if(command.getNewlySelectedReportDefinitions() != null){
//    		//check if there is already a report persent, if not they should be created.
//    		for(ReportDefinition repDef : command.getNewlySelectedReportDefinitions()){
//    			if(!command.getAeReport().isAnActiveReportPresent(repDef)){
//    				command.getReportDefinitionListForCreation().add(command.reassociateReportDefinitionToSession(repDef));
//    			}
//    		}
//    		
//    		//If there are reports to create, make sure, other Reports already present belonging to the same 
//    		// organization is REPLACED. 
//    		if(!command.getReportDefinitionListForCreation().isEmpty()){
//    			for(ReportDefinition repDef : command.getReportDefinitionListForCreation()){
//    				String nciInstituteCode = repDef.getOrganization().getNciInstituteCode();
//    				command.getReportListForWithdrawal().addAll(command.getAeReport().findPendingAmendableReports(nciInstituteCode));
//    			}
//    		}
//    	}
//    	
////    	BJ: Do not delete the below commented lines, as we may need it later. 
////    	
////    	if(command.getNewlySelectedDefs() != null && !command.getNewlySelectedDefs().isEmpty()) {
////    		if(command.isNewlySelectedSponsorReportEarlier()) {
////    			// Case(A) Newly selected amendable sponsor report is earlier
////    			//         - withdraw the existing amendable sponsor report
////    			Map<ReportDefinition, Boolean> sponsorNewlySelectedMap = new HashMap<ReportDefinition, Boolean>();
////                for (ReportDefinition reportDefinition : command.getNewlySelectedSponsorReports())
////                    sponsorNewlySelectedMap.put(reportDefinition, Boolean.TRUE);
////
////                String nciInstituteCode = command.getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
////
////                for (Report report : command.getAeReport().getReports()) {
////                    if (report.getLastVersion().getReportStatus().equals(ReportStatus.PENDING) && report.isSponsorReport(nciInstituteCode)
////                            && report.getReportDefinition().getAmendable()) {
////                        command.getExistingReportMap().remove(report.getReportDefinition());
////                        command.getReportListForWithdrawal().add(report);
////                    }
////                }
////       	 	}else{
////       		 	// Case(B) Newly selected amendable sponsor report is later
////       		 	//			- ignore the newly selected amendable sponsor report
////       		 	command.setNewlySelectedDefs(command.getOtherSelectedReports());
////       	 	}
////        }
////
////   	command.populateCreationAndAmendmentList();
//    }
//        
//    public void processForCreateNewFlow(EditExpeditedAdverseEventCommand command){
//    	if(true) throw new UnsupportedOperationException("to be removed");
//    	//command.initializeNewlySelectedReportDefinitions();
//        command.setNewlySelectedReportDefinitions(command.getSelectedReportDefinitions());
//        command.classifyNewlySelectedReportsDefinitons();
//
//        command.getReportDefinitionListForCreation().addAll(command.getNewlySelectedReportDefinitions());
//        // Create the newly Selected Reports that need to be created.
//        if(command.getReportDefinitionListForCreation().size() > 0){
//        	command.createReports(null, null, null);
//        	command.save();
//        	
//        	//call workflow
//        	if(command.getWorkflowEnabled())   	
//        		command.enactWorkflow(command.getAeReport());
//        }
//
//        //figureout the mandatory sections
//        command.refreshMandatorySectionsAndProperties();
//        command.setSelectedReportDefinitions(new ArrayList<ReportDefinition>());
//    }
//    

    public NowFactory getNowFactory() {
        return nowFactory;
    }

    public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
    
   
    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
    
    public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
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
