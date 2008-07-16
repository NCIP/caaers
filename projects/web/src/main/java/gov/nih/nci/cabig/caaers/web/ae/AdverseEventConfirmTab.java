package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.study.StudyTab;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventConfirmTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	
	private static final String MAIN_FIELD_GROUP = "main";
	
	public AdverseEventConfirmTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
	
	@Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand command) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		MultipleFieldGroupFactory mainFieldFactory;
		List<SolicitedAdverseEvent> saeList;

		if(command.getAdverseEventReportingPeriod() != null){
			mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEventReportingPeriod.adverseEvents");
			// Check if the adverseEventReportingPeriod has any adverseEvents.
			// If yes then display the solicited adverseEvents in the second section (Solicited Adverse Events)
			if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null){
				for(int i = 0; i < command.getAdverseEventReportingPeriod().getAdverseEvents().size(); i++){
					AdverseEvent ae = command.getAdverseEventReportingPeriod().getAdverseEvents().get(i);
					mainFieldFactory.addField(InputFieldFactory.createLabelField("grade",""));
					mainFieldFactory.addField(InputFieldFactory.createLabelField("attributionSummary",""));
					mainFieldFactory.addField(InputFieldFactory.createLabelField("hospitalization",""));
					mainFieldFactory.addField(InputFieldFactory.createLabelField("expected",""));
					InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
					mainFieldFactory.addFieldGroup(fieldGroup);
					mainFieldFactory.clearFields();
				}
				map.addMultipleFieldGroupFactory(mainFieldFactory);
			}
		}
		
		return map;

    }
}
