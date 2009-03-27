package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Biju Joseph
 * @author Sameer Sawant
 */
public class AdverseEventConfirmTab extends AdverseEventTab{
	
	private static final String MAIN_FIELD_GROUP = "main";
	private static final String RPD_FIELD_GROUP = "rpd";
	
	public AdverseEventConfirmTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
	
	@Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand command) {
		final boolean isBaseline = command.getAdverseEventReportingPeriod().isBaselineReportingType();
		InputFieldGroupMap map = new InputFieldGroupMap();
		
		//create fields for AEs
		final List<AdverseEvent> adverseEvents = command.getAdverseEventReportingPeriod().getAllReportedAndReportableAdverseEvents();
		if(adverseEvents != null){
			int size = adverseEvents.size();
			for(int i = 0; i < size; i++){
				String key = MAIN_FIELD_GROUP + i;
				final int j = i;
				InputFieldGroup fieldGroup = new InputFieldGroup(){
					public List<InputField> getFields() {
						return createCustomFieldGroup(adverseEvents.get(j),j, isBaseline);
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
		
		return map;
    }
	
	/**
	 * This method will create the fields (list of fileds) that need to be painted in a row on the review page.
	 * The propery each field is bound-to is more defined by the parameters like <code>isMeddraStudy</code> or <code>isBaseline</code>.
	 * @param ae
	 * @param i
	 * @param isMeddraStudy
	 * @param isBaseline
	 * @return
	 */
	public List<InputField> createCustomFieldGroup(AdverseEvent ae, int i, boolean isBaseline){
		//only not-associated-to-aereprot , non-baseline, and graded is selectable
		boolean notAssociatedToReport = ae.getReport() == null;
		boolean graded = ae.getGrade() != null && ae.getGrade()!= Grade.NOT_EVALUATED;
		
		List<InputField> fields= new ArrayList<InputField>();
		if(notAssociatedToReport && graded && !isBaseline){
			fields.add(InputFieldFactory.createCheckboxField("selectedAesMap[" + ae.getId() + "]", ""));
		}else {
			fields.add(InputFieldFactory.createImageField("selectedAesMap[" + ae.getId() + "]", "", ""));
		}
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[" + i + "].adverseEventTerm.universalTerm", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[" + i + "].detailsForOther", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[" + i + "].displayGrade", ""));

		InputField startDateField = null;
		if(ae.getStartDate() != null){
			startDateField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[" + i + "].startDateAsString", "", false);
		}else{
			startDateField = InputFieldFactory.createDateField("adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[" + i + "].startDate","", false);
		}
		
		fields.add(startDateField);
		InputField selectPrimaryField = InputFieldFactory.createRadioButtonField("primaryAdverseEventId", "", ae.getId().toString());
		fields.add(selectPrimaryField);
		
		return fields;
	}
	
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
	
		int i = 0;
		
		
		
		//if(command.getAdverseEventReportingPeriod().isBaselineReportingType())
		//	return refdata;
		
		//do the setup stuff
		
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
		
		//refresh the selectedAEMap
		command.refreshSelectedAesMap();
		
		//find primary AE
		//command.findPrimaryAdverseEvent();
		
		//Call the super class referenceData, so that the createFieldGroup is executed
		Map<String, Object> refdata = super.referenceData(request, command);
		
		//create the 3 column display for all report definitions.
		Map<String, ReportDefinitionDisplayTable> allReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		Map<String, ReportDefinitionDisplayTable> selectedReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		
		for(ReportDefinition rpDef : command.getAllReportDefinitions()){
			ReportDefinitionDisplayTable rdTable = new ReportDefinitionDisplayTable(command.getReportStatusMap().get(rpDef.getId()),
					command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()), 
					InputFieldFactory.createCheckboxField("reportDefinitionMap[" + rpDef.getId() + ']',
                    		rpDef.getLabel()));
			
			allReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable );
			
			//only add to selected map, if it is 'required' or 'selected'.
			if(command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()) || command.getReportDefinitionMap().get(rpDef.getId())){
				selectedReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable);
			}
			
			i++;
		}
		refdata.put("rpdAllTable", allReportDefDisplayTableMap);
		refdata.put("rpdSelectedTable", selectedReportDefDisplayTableMap);
		
		
		// these tables or statement saying no rows to render.
		Boolean displayReportableAeTable = false; // For "Select Adverse Events To Report" table.
		
		List<AdverseEvent> reportableAdverseEvents = command.getAdverseEventReportingPeriod().getReportableAdverseEvents();
		
		
		if(reportableAdverseEvents.size() > 0)	displayReportableAeTable = true;
		
		refdata.put("displayReportableAeTable", displayReportableAeTable);
		
		return refdata;
	}
	
	/**
	 * This method will verify the following
	 * 1. Makessure that atleast one AE is selected, when there is a report selected.
	 * 2. Primary AE is mentioned.
	 * 3. Start date of the primary AE is mentioned.
	 */
	@Override
    protected void validate(CaptureAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		
		// Check if there is a report selected with no aes selected.
		if(StringUtils.equals(command.get_action(), "createNew") && command.getSelectedReportDefinitions().size() > 0){
			//Iterate over the selectedAes map to check if atleast one is true. Otherwise throw an error.
			Boolean noAeSelected = true;
			for(Integer id: command.getSelectedAesMap().keySet()){
				if(command.getSelectedAesMap().get(id))
					noAeSelected = false;
			}
			if(noAeSelected)
				errors.reject("CAE_001","A report cannot be selected without selecting atleast one adverse event.");
		}
		//verify whether primary ae and start date is mentioned.
		if(command.getPrimaryAdverseEventId() == null){
			errors.reject("CAE_002", "A primary adverse event must be selected inorder to continue expedited reporting");
		}else{
			for(AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()){
				if(ae.getId().equals(command.getPrimaryAdverseEventId()) && ae.getStartDate() == null){
					errors.reject("CAE_003", "Start Date of the primary adverse event must be specified");
				}
			}
		}
		
				
	}
	
	@Override
	public void onDisplay(HttpServletRequest request,	CaptureAdverseEventInputCommand command) {
		command.reassociate();
	}
	
	/**
	 * Reassociated the Reporting Period to the running Hibernate session
	 */
	@Override
	public void beforeBind(HttpServletRequest request,CaptureAdverseEventInputCommand command) {
		command.reassociate();
	}
	
	@Override
	public void postProcess(HttpServletRequest request,	CaptureAdverseEventInputCommand command, Errors errors) {
		command.getRequiredReportDefinitionsMap().clear();
	}

	
 	/**
	 * This will remove all unselected report definitions from the report, by calling delete on the repository 
	 * @param aeReport
	 * @param removedDefinitions
	 */
	private void removeUnselectedReports(ExpeditedAdverseEventReport aeReport , Collection<ReportDefinition> removedDefinitions) {
		 List<Report> nonWitdrawnReports = aeReport.getNonWithdrawnReports();
		 for(Report report : nonWitdrawnReports){
			 for(ReportDefinition rpDef : removedDefinitions){
				 if(report.getReportDefinition().getId().equals(rpDef.getId())){
					 reportRepository.deleteReport(report); 
				 }
			 }
			 
		 }
	}
	
}

