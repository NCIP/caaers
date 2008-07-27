package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections15.ListUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory.RepeatingFieldGroup;
import gov.nih.nci.cabig.caaers.web.study.StudyTab;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Sameer Sawant
 */
public class AdverseEventConfirmTab extends AdverseEventTab{
	
	private static final String MAIN_FIELD_GROUP = "main";
	

	
	public AdverseEventConfirmTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
	
	@Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand command) {
		Map<String, InputFieldGroup> map = new HashMap<String, InputFieldGroup>();
		final CaptureAdverseEventInputCommand cmd = command;

		if(command.getAdverseEventReportingPeriod() != null){
			if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null){
				for(int i = 0; i < command.getAdverseEventReportingPeriod().getAdverseEvents().size(); i++){
					String key = MAIN_FIELD_GROUP + i;
					final int j = i;
					InputFieldGroup fieldGroup = new InputFieldGroup(){
						public List<InputField> getFields() {
							return createCustomFieldGroup(cmd,j);
						}
						public String getDisplayName() {
							return "";
						}
						public String getName() {
							return "";
						}
					};
					map.put(key, fieldGroup);
				}
			}
		}
		
		
		InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (ReportDefinition reportDefinition : command.getOptionalReportDefinitionsMap().keySet()) {
            optional.getFields().add(
                            InputFieldFactory.createCheckboxField("optionalReportDefinitionsMap["
                                            + reportDefinition.getId() + ']', reportDefinition
                                            .getName()
                                            + " ("
                                            + reportDefinition.getOrganization().getName()
                                            + ')'));
        }
        map.put(optional.getName(), optional);
		return map;
    }
	
	public List<InputField> createCustomFieldGroup(CaptureAdverseEventInputCommand command, int i){
		List<InputField> fields= new ArrayList<InputField>();
		if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null){
			Integer id = command.getAdverseEventReportingPeriod().getAdverseEvents().get(i).getAdverseEventTerm().getId();
			fields.add(InputFieldFactory.createCheckboxField("selectedAesMap[" + id + "]", "", null));
		}
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].adverseEventTerm.term.term", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].grade", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].attributionSummary", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].hospitalization", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].expected", ""));
		return fields;
	}
	
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
		Map<String, Object> refdata = super.referenceData(request, command);
		//Apply the rules.
		Map<ReportDefinition, List<AdverseEvent>> serious_map = command.applyRules();
		populateSelectedAesMap(command, serious_map);
		refdata.put("serious_aes", serious_map);
		
		return refdata;
	}
	
	/**
	 * Reassociated the Reporting Period to the running Hibernate session
	 */
	@Override
	public void beforeBind(HttpServletRequest request,CaptureAdverseEventInputCommand command) {
		command.reassociate();
	}
	
	@Override
    /**
     * We do the following things here 
     * 	1. Find the newly checked report definitions 
     *  2. Remove the unselected report definitions
     *  3. Create the reports (by calling evaluation service)
     *  4. Save the AEReport
     */
    public void postProcess(HttpServletRequest request, CaptureAdverseEventInputCommand command,
                    Errors errors) {

            List<ReportDefinition> newlySelectedDefs = newlySelectedReportDefinitions(command);
            removeUnselectedReports(command);
            if(newlySelectedDefs.size() > 0){  // Only if there are new Reports to be created.
            	// Check if the ExpeditedReport is already created. If not create one.
            	if(command.getAdverseEventReportingPeriod().getAeReport() == null){
            		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
            		aeReport.setReportingPeriod(command.getAdverseEventReportingPeriod());
            		//Add the selected Aes to this aeReport object.
            		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
            			Integer aeId = ae.getId();
            			if(command.getSelectedAesMap().containsKey(aeId))
            				aeReport.addAdverseEvent(ae);
            		}
            		command.getAdverseEventReportingPeriod().setAeReport(aeReport);
            	}
            	else{
            		// The expeditedReport already exists. Add newly selected Aes to this aeReport.
            		Map<Integer, Boolean> aesInReportMap = new HashMap<Integer, Boolean>();
            		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAeReport().getAdverseEvents())
            			if(!aesInReportMap.containsKey(ae.getId()))
            				aesInReportMap.put(ae.getId(), Boolean.TRUE);
            		// Now add the Aes in selectedAesMap to aeReport if its new
            		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
            			Integer aeId = ae.getId();
            			if(command.getSelectedAesMap().containsKey(aeId) && !aesInReportMap.containsKey(aeId))
            				command.getAdverseEventReportingPeriod().getAeReport().addAdverseEvent(ae);
            		}
            	}
           		evaluationService.addOptionalReports(command.getAdverseEventReportingPeriod().getAeReport(), newlySelectedDefs);
           		expeditedAdverseEventReportDao.save(command.getAdverseEventReportingPeriod().getAeReport());
            }

    }
	
	private List<ReportDefinition> newlySelectedReportDefinitions(
							CaptureAdverseEventInputCommand command) {
			List<ReportDefinition> selectedReportDefs = command.getSelectedReportDefinitions();
			List<ReportDefinition> instantiatedReportDefs = command.getInstantiatedReportDefinitions();
			List<ReportDefinition> difference = ListUtils.subtract(selectedReportDefs,
							instantiatedReportDefs);
			return difference;
	}

	private void removeUnselectedReports(CaptureAdverseEventInputCommand command) {
			if(command.getAdverseEventReportingPeriod().getAeReport() != null){
				List<Report> reports = command.getAdverseEventReportingPeriod().getAeReport().getReports();
				for (Report report : reports) {
					if (report.getStatus() == ReportStatus.WITHDRAWN) continue;
					if (!reportSelected(command, report.getReportDefinition())) {
						reportRepository.deleteReport(report);
					}
				}
			}
	}

	private boolean reportSelected(CaptureAdverseEventInputCommand command, ReportDefinition def) {
			Boolean val = command.getOptionalReportDefinitionsMap().get(def);
			return val == null ? false : val;
	}
	
	@Override
    public void onDisplay(HttpServletRequest request, CaptureAdverseEventInputCommand command) {

        // evalutate available report definitions per session.
        if (command.getAllReportDefinitions() == null
                        || command.getAllReportDefinitions().isEmpty()) {
            command.setAllReportDefinitions(evaluationService.applicableReportDefinitions(command
                            .getAssignment()));
        }

        // identify the report definitions mandated by Rules engine
        if (command.getRequiredReportDefinitions().isEmpty()) {
        	//command.refreshAssignment(command.getAdverseEventReportingPeriod().getId());
        	command.initialize();
            command.setRequiredReportDefinition(evaluationService
                            .findRequiredReportDefinitions(command.getAdverseEventReportingPeriod().getAeReport(),
                            		command.getAdverseEventReportingPeriod().getAdverseEvents(), command.getAdverseEventReportingPeriod().getStudy()));
        }

        // already AE report is saved.
        if (command.getAdverseEventReportingPeriod().getAeReport() != null) {
            // set up selected reports
            command.refreshSelectedReportDefinitionsMap(command.getInstantiatedReportDefinitions());
            // set up the optional reports
            command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
        } else {
            // set up the optional reports
            command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
            // new, so no reports are associated with this yet.
            command.setSelectedReportDefinitions(command.getRequiredReportDefinitions());
        }
    }
	
	private List<ReportDefinition> createOptionalReportDefinitionsList(CaptureAdverseEventInputCommand command) {
		if (command.getSelectedReportDefinitions() == null) return command.getAllReportDefinitions();

		return ListUtils.subtract(command.getAllReportDefinitions(), command.getSelectedReportDefinitions());
}
	
	
	/**
	 * This method populates the SelectedAesMap member of the command object. The Aes that were selected when this page was accessed
	 * the last time(through Report object) and the adverse events that were serious on triggering of the rules are set to true in this
	 * Map. All the adverse events in the reporting period are keys in this map.
	 * @param command
	 */
	public void populateSelectedAesMap(CaptureAdverseEventInputCommand command, Map<ReportDefinition, List<AdverseEvent>> serious_map){
		//first put all the AEs in the reportingperiod as keys in the map.
		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
			if(ae != null && !(command.getSelectedAesMap().containsKey(ae))){
				Integer id = ae.getAdverseEventTerm().getId();
				command.getSelectedAesMap().put(id, Boolean.FALSE);
			}		
		}
		
		//Now we need to update the the selected AEs to true by using the serious_map and report object
		//Using Report object
		if(command.getAdverseEventReportingPeriod().getAeReport() != null)
			for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAeReport().getAdverseEvents())
				if(ae != null)
					command.getSelectedAesMap().put(ae.getAdverseEventTerm().getId(), Boolean.TRUE);
		
		//Using serious_map
		for(ReportDefinition repDefinition: serious_map.keySet()){
			List<AdverseEvent> aeList = serious_map.get(repDefinition);
			for(AdverseEvent ae: aeList)
				if(ae != null)
					command.getSelectedAesMap().put(ae.getAdverseEventTerm().getId(), Boolean.TRUE);
		}
	}
	

}
