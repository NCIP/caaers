package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
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
		final boolean isMeddraStudy = command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;
		final boolean isBaseline = command.getAdverseEventReportingPeriod().isBaselineReportingType();
		InputFieldGroupMap map = new InputFieldGroupMap();
		
		//create fields for AEs
		final List<AdverseEvent> adverseEvents = command.getAdverseEventReportingPeriod().getReportableAdverseEvents();
		if(adverseEvents != null){
			int size = adverseEvents.size();
			for(int i = 0; i < size; i++){
				String key = MAIN_FIELD_GROUP + i;
				final int j = i;
				InputFieldGroup fieldGroup = new InputFieldGroup(){
					public List<InputField> getFields() {
						return createCustomFieldGroup(adverseEvents.get(j),j, isMeddraStudy, isBaseline);
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
		
		
		if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null){
			
		}
		

		return map;
    }
	
	public List<InputField> createCustomFieldGroup(AdverseEvent ae, int i, boolean isMeddraStudy, boolean isBaseline){
		//only non-baseline, grade != NOT_EVALUATED is selectable, or can be opted as primary
		boolean isModifiable = ae.getGrade() != null && ae.getGrade()!= Grade.NOT_EVALUATED;
		
		List<InputField> fields= new ArrayList<InputField>();
		if(isModifiable){
			fields.add(InputFieldFactory.createCheckboxField("selectedAesMap[" + ae.getId() + "]", ""));
		}else if(!isBaseline){
			fields.add(InputFieldFactory.createImageField("selectedAesMap[" + ae.getId() + "]", "", ""));
		}
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].adverseEventTerm.universalTerm", ""));
		//if(!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()) fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].lowLevelTerm", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].detailsForOther", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].displayGrade", ""));
		String attributionFieldName = (ae.getAttributionSummary() != null)? "attributionSummary.displayName" : "attributionSummary";
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "]." + attributionFieldName, ""));
		String hospitalizationFieldName = (ae.getHospitalization() != null)? "hospitalization.displayName" : "hospitalization";
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "]." + hospitalizationFieldName, ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].displayExpected", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.reportableAdverseEvents[" + i + "].displaySerious", ""));
		//if(isModifiable){
		//	fields.add(InputFieldFactory.createRadioButtonField("primaryAdverseEventId", "", ae.getId().toString()));
		//}else{
		//	fields.add(InputFieldFactory.createImageField("primaryAdverseEventId", "", ""));
		//}
		return fields;
	}
	
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
	
		int i = 0;
		
		//Call the super class referenceData, so that the createFieldGroup is executed
		Map<String, Object> refdata = super.referenceData(request, command);
		
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
		
		
		// Add some flags refdata (displaySeriousTable, displayObservedTable, displaySolicitedTable) to determine if to display
		// these tables or statement saying no rows to render.
		Boolean displaySeriousTable = false; // For Serious Adverse Events table
		Boolean displayObservedTable = false; // For Observed Adverse Events table
		Boolean displaySolicitedTable = false; // For Solicited Adverse Events table
		Boolean displayReportableAeTable = false; // For "Select Adverse Events To Report" table.
		
		if(command.getAdverseEventReportingPeriod().getReportableAdverseEvents().size() > 0)
			displayReportableAeTable = true;
		
		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getReportableAdverseEvents()){
			if(BooleanUtils.isTrue(ae.getRequiresReporting()))
				displaySeriousTable = true;
		}
		
		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getReportableAdverseEvents()){
			if(!ae.getSolicited() && BooleanUtils.isNotTrue(ae.getRequiresReporting()))
				displayObservedTable = true;
		}
		
		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getReportableAdverseEvents()){
			if(ae.getSolicited() && BooleanUtils.isNotTrue(ae.getRequiresReporting()))
				displaySolicitedTable = true;
		}
		refdata.put("displaySeriousTable", displaySeriousTable);
		refdata.put("displayObservedTable", displayObservedTable);
		refdata.put("displaySolicitedTable", displaySolicitedTable);
		refdata.put("displayReportableAeTable", displayReportableAeTable);
		
		return refdata;
	}
	
	
	@Override
    protected void validate(CaptureAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		
		// Check if there is a report selected with no aes selected.
		if(command.getSelectedReportDefinitions().size() > 0){
			//Iterate over the selectedAes map to check if atleast one is true. Otherwise throw an error.
			Boolean noAeSelected = true;
			for(Integer id: command.getSelectedAesMap().keySet()){
				if(command.getSelectedAesMap().get(id))
					noAeSelected = false;
			}
			if(noAeSelected)
				errors.reject("AT_LEAST_ONE_AE","A report cannot be selected without selecting atleast one adverse event.");
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

