package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Term;
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
		InputFieldGroupMap map = new InputFieldGroupMap();
		
		//create fields for AEs
		final List<AdverseEvent> adverseEvents = command.getAdverseEventReportingPeriod().getAdverseEvents();
		if(adverseEvents != null){
			int size = adverseEvents.size();
			for(int i = 0; i < size; i++){
				String key = MAIN_FIELD_GROUP + i;
				final int j = i;
				InputFieldGroup fieldGroup = new InputFieldGroup(){
					public List<InputField> getFields() {
						return createCustomFieldGroup(adverseEvents.get(j),j, isMeddraStudy);
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
	
	public List<InputField> createCustomFieldGroup(AdverseEvent ae, int i, boolean isMeddraStudy){
		List<InputField> fields= new ArrayList<InputField>();
		fields.add(InputFieldFactory.createCheckboxField("selectedAesMap[" + ae.getId() + "]", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].adverseEventTerm.universalTerm", ""));
		if(!ae.getSolicited()){
			if(!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()) fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].lowLevelTerm", ""));
			fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].detailsForOther", ""));
		}
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].grade", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].attributionSummary", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].hospitalization", ""));
		fields.add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.adverseEvents[" + i + "].expected", ""));
		fields.add(InputFieldFactory.createRadioButtonField("primaryAdverseEventId", "", ae.getId().toString()));
		return fields;
	}
	
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
		int i = 0;
		
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
		command.findPrimaryAdverseEvent();
		
		
		//Call the super class referenceData, so that the createFieldGroup is executed
		Map<String, Object> refdata = super.referenceData(request, command);
		
		//create the 3 column display for all report definitions.
		Map<String, ReportDefinitionDisplayTable> allReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		Map<String, ReportDefinitionDisplayTable> selectedReportDefDisplayTableMap = new HashMap<String, ReportDefinitionDisplayTable>();
		
		for(ReportDefinition rpDef : command.getAllReportDefinitions()){
			ReportDefinitionDisplayTable rdTable = new ReportDefinitionDisplayTable(command.getReportStatusMap().get(rpDef.getId()),
					command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()), 
					InputFieldFactory.createCheckboxField("reportDefinitionMap[" + rpDef.getId() + ']',
                    		rpDef.getName() + " ("  + rpDef.getOrganization().getName()  + ')'));
			
			allReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable );
			
			//only add to selected map, if it is 'required' or 'selected'.
			if(command.getRequiredReportDefinitionIndicatorMap().get(rpDef.getId()) || command.getReportDefinitionMap().get(rpDef.getId())){
				selectedReportDefDisplayTableMap.put(RPD_FIELD_GROUP + i, rdTable);
			}
			
			i++;
		}
		refdata.put("rpdAllTable", allReportDefDisplayTableMap);
		refdata.put("rpdSelectedTable", selectedReportDefDisplayTableMap);
		
		
		
	
		
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
	public void postProcess(HttpServletRequest request,	CaptureAdverseEventInputCommand command, Errors errors) {
		command.getRequiredReportDefinitionsMap().clear();
	}

	
    /**
     * We do the following things here 
     * 	1. Find the newly checked report definitions 
     *  2. Remove the unselected report definitions
     *  3. Create the reports (by calling evaluation service)
     *  4. Save the AEReport
     *  Note:- We should avoid
     */
	public void saveExpeditedReport(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors){

        Collection<ReportDefinition> newlySelectedDefs = command.findNewlySelectedReportDefinitions();
        
        removeUnselectedReports(command);
        
        if(newlySelectedDefs.size() > 0){  // Only if there are new Reports to be created.
        	// Check if the ExpeditedReport is already created. If not create one.
        	if(command.getAdverseEventReportingPeriod().getAeReport() == null){
        		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        		aeReport.setCreatedAt(new Timestamp(new Date().getTime()));
        		aeReport.setReportingPeriod(command.getAdverseEventReportingPeriod());
        		
        		//Add the selected Aes to this aeReport object.
        		for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
        			Integer aeId = ae.getId();
        			if(command.getSelectedAesMap().containsKey(aeId) && command.getSelectedAesMap().get(aeId).equals(Boolean.TRUE))
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
	
	private void removeUnselectedReports(CaptureAdverseEventInputCommand command) {
			
	}

	
}

