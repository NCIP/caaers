package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
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
public class AdverseEventConfirmTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	
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

		return map;
    }
	
	public List<InputField> createCustomFieldGroup(CaptureAdverseEventInputCommand command, int i){
		List<InputField> fields= new ArrayList<InputField>();
		if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null){
			Integer id = command.getAdverseEventReportingPeriod().getAdverseEvents().get(i).getAdverseEventTerm().getId();
			fields.add(InputFieldFactory.createCheckboxField("selectedAesMap[" + id + "]", "", null));
		}
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
