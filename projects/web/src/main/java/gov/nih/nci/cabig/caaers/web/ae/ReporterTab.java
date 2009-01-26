package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private static final Log log = LogFactory.getLog(ReporterTab.class);
    private static final String REPORT_DEFN_LIST_PARAMETER = "reportDefnList";
    private static final String REPORT_ID_PARAMETER = "reportId";
    private static final String ACTION_PARAMETER = "action";

    private ConfigProperty configurationProperty;

    protected NowFactory nowFactory;

    private EvaluationService evaluationService;
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

    public ReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter",
                "ae/reporter");
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.REPORTER_INFO_SECTION};
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                                     ExpeditedAdverseEventInputCommand command) {
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

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand cmd) {
    	EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        super.onDisplay(request, command);

        boolean severe = true;
        request.setAttribute("oneOrMoreSevere", severe);
        
        //only process if we are editing the expedited report (ammend or edit flow)
        if (command.getAeReport().getId() != null) {
        	String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
            if(StringUtils.equals("amendReport", action) || StringUtils.equals("editReport", action)){
            	
            	//process the ammend and editflow
            	processEditAndAmmendReportFlow(command, StringUtils.equals("amendReport", action));
            	
            	// Remove the attributes from the session
                request.getSession().removeAttribute(ACTION_PARAMETER);
            }
        }
        
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
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand cmd,
                            Errors errors) {
        EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
        if (StringUtils.equals("createNew", action)) {
        	processForCreateNewFlow(command);
        	// Remove the attributes from the session
            request.getSession().removeAttribute(ACTION_PARAMETER);
        }
       
    }
    
    
    public void refreshMandatorySectionsAndProperties(EditExpeditedAdverseEventCommand command) {
    	// find the new mandatory sections
        command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));

        // refresh the mandatory fields
        command.refreshMandatoryProperties();
    }
    
    
    public void processEditAndAmmendReportFlow(EditExpeditedAdverseEventCommand command, boolean isAmmendFlow){
    	 List<ReportDefinition> newReportDefs = new ArrayList<ReportDefinition>();
         List<Report> amendReportList = new ArrayList<Report>();
         List<Report> withdrawReportList = new ArrayList<Report>();

         //command.initializeNewlySelectedReportDefinitions();
         command.setNewlySelectedDefs(command.getSelectedReportDefinitions());
         command.classifyNewlySelectedReportsDefinitons();
         Map<ReportDefinition, ReportStatus> existingReportMap = initilizeExistingReportMap(command);

    

         // Logic for EDIT-FLOW
         // CASE(A) Newly selected amendable sponsor report is earlier
         //         - withdraw the existing amendable sponsor report
         // CASE(B) Newly selected amendable sponsor report is later
         //         - ignore the newly selected amendable sponsor report

         // - add all non-amendable reports if they don't exist or are already submitted/withdrawn
         // - add all non-organizational reports if they don't exist or are already submitted/withdrawn.
         if(!isAmmendFlow){
        	 if (command.getNewlySelectedDefs() != null && !command.getNewlySelectedDefs().isEmpty()) {
                 if (command.isNewlySelectedReportEarlier()) {
                     // This is CASE(A)
                     // Firstly, Withdraw the pending amendable sponsor reports.
                     Map<ReportDefinition, Boolean> sponsorNewlySelectedMap = new HashMap<ReportDefinition, Boolean>();
                     for (ReportDefinition reportDefinition : command.getNewlySelectedSponsorReports())
                         sponsorNewlySelectedMap.put(reportDefinition, Boolean.TRUE);

                     String nciInstituteCode = command.getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();

                     for (Report report : command.getAeReport().getReports()) {
                         if (report.getLastVersion().getReportStatus().equals(ReportStatus.PENDING) && report.isSponsorReport(nciInstituteCode)
                                 && report.getReportDefinition().getAmendable()) {
                             existingReportMap.remove(report.getReportDefinition());
                             withdrawReportList.add(report);
                         }
                     }
                 } else {
                     // This is CASE(B)
                     // Here we ignore the newlySelectedSponsorReports.
                     // So we set the newlySelectedDefs with OtherSelectedReports
                     command.setNewlySelectedDefs(command.getOtherSelectedReports());
                 }
             }
         }


         // For Sponsor/amendable newlySelectedReport take the following action
         //        - create New report if it doesnt exist
         //        - amend if it exists (also instantiate Notifications)

         // For other reports take the following action
         //         - create New report if it doesnt exist
         //         - amend if it exists and has status = SUBMITTED/WITHDRAWN
         //         - ignore if it exists and the status = PENDING.
         for (ReportDefinition reportDefinition : command.getNewlySelectedDefs()) {
             if (!existingReportMap.containsKey(reportDefinition))
                 newReportDefs.add(reportDefinition);
             else {
                 if (existingReportMap.get(reportDefinition).equals(ReportStatus.COMPLETED) ||
                         existingReportMap.get(reportDefinition).equals(ReportStatus.WITHDRAWN))
                     for (Report report : command.getAeReport().getReports()) {
                         if (report.getReportDefinition().equals(reportDefinition))
                             amendReportList.add(report);
                     }
             }
         }
     

         // Create the newly Selected Reports that need to be created.
         if (newReportDefs.size() > 0) {
         	List<Report> newlyCreatedReports = null;
         	// Incase of amend the new reportVersion is incremented and assigned to the reports created 
         	// Incase of edit the currentVersion number is assigned to the new reports created.
         	if(isAmmendFlow)
         		newlyCreatedReports = evaluationService.addOptionalReports(command.getAeReport(), newReportDefs, false);
         	else
         		newlyCreatedReports = evaluationService.addOptionalReports(command.getAeReport(), newReportDefs, true);
         	
//         	enactWorkflow(newlyCreatedReports);
         }
         
         // Withdraw the reports to be withdrawn
         command.withdrawReports(withdrawReportList);

         // Amend the reports to be amended
         command.amendReports(amendReportList);

         if (command.getAeReport().getReports().size() > 0) {
             command.save();
         }
         
         //refresh the mandatory sections and properties
         refreshMandatorySectionsAndProperties(command);

         command.setSelectedReportDefinitions(new ArrayList<ReportDefinition>());
    }
    
    public void processForCreateNewFlow(EditExpeditedAdverseEventCommand command){
    	
    	//command.initializeNewlySelectedReportDefinitions();
        command.setNewlySelectedDefs(command.getSelectedReportDefinitions());
        command.classifyNewlySelectedReportsDefinitons();
        
    	List<ReportDefinition> newReportDefs = new ArrayList<ReportDefinition>();
    	newReportDefs.addAll(command.getNewlySelectedDefs());
        // Create the newly Selected Reports that need to be created.
        if (newReportDefs.size() > 0) {
        	List<Report> newlyCreatedReports = null;
        	// Incase createNew the new reportVersion is incremented and assigned to the reports created 
        	newlyCreatedReports = evaluationService.addOptionalReports(command.getAeReport(), newReportDefs, false);
        	command.save();
        	//call workflow
        	enactWorkflow(command.getAeReport());
        }
        //figureout the mandatory sections
        refreshMandatorySectionsAndProperties(command);
        
        command.setSelectedReportDefinitions(new ArrayList<ReportDefinition>());
    }
    
    /**
     * It creates a map with the reportDefinitons of the "command.aeReport.reports" as the key
     * and "command.aeReport.report.lastVersion.reportStatus" as the value.
     * It is needed as helper data structure to implement the create/amend-report logic.
     *
     * @param command
     * @return
     */
    public Map<ReportDefinition, ReportStatus> initilizeExistingReportMap(ExpeditedAdverseEventInputCommand command) {
        Map<ReportDefinition, ReportStatus> map = new HashMap<ReportDefinition, ReportStatus>();
        for (Report report : command.getAeReport().getReports()) {
            if (!map.containsKey(report.getReportDefinition()))
                map.put(report.getReportDefinition(), report.getLastVersion().getReportStatus());
        }

        return map;
    }
    
    /**
     * This method will spawn the workflow for newly created reports
     * @param newlyCreatedReports
     */
    public void enactWorkflow(ExpeditedAdverseEventReport aeReport){
    	adverseEventRoutingAndReviewRepository.enactReportWorkflow(aeReport);
    	
    }

    @Required
    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Required
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

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
}
