package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class PersonnelTab extends StudyTab {
	private List<InputField> fields;
    public PersonnelTab() {
        super("Study Personnel", "Personnel", "study/study_personnel");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
    	String action = request.getParameter("_action");
		String selectedPersonnel = request.getParameter("_selectedPersonnel");
		String prevSiteIndex = request.getParameter("_prevSite");
		Study study = (Study) command;
		int selectedIndex = study.getStudySiteIndex();
		if ("removeStudyPersonnel".equals(action) &&  selectedIndex >=0){	
			study.getStudySites().get(study.getStudySiteIndex()).getStudyPersonnels()
				.remove(Integer.parseInt(selectedPersonnel));
		}else if ("changeSite".equals(action) && errors.hasErrors()){
			study.setStudySiteIndex(Integer.parseInt(prevSiteIndex));
		}	
    }

    
    @Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		InputFieldGroup siteFieldGroup = new DefaultInputFieldGroup("site");
		siteFieldGroup.getFields().add(new DefaultSelectField("studySiteIndex", "Site", true, 
				BaseSelectField.collectOptions(collectStudySiteDropdown(command), "code", "desc")));
		map.addInputFieldGroup(siteFieldGroup);
		
		if(fields == null){
			fields = new ArrayList<InputField>();
			AutocompleterField investigatorField = new AutocompleterField("researchStaff", "Research Staff", true);
			//sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the investigator name you are looking for");
			fields.add(investigatorField);
			fields.add(new DefaultSelectField("roleCode", "Role", true, 
					collectOptionsFromConfig("studyPersonnelRoleRefData", "desc","desc")));
			fields.add(new DefaultSelectField("statusCode", "Status", true, 
					collectOptionsFromConfig("studyPersonnelStatusRefData", "desc","desc")));
		}
		
		int ssIndex = command.getStudySiteIndex();
		if(ssIndex >= 0){
			RepeatingFieldGroupFactory rfgFactory = new RepeatingFieldGroupFactory("main", "studySites[" + ssIndex +"].studyPersonnels");
			
			for(InputField f : fields){
				rfgFactory.addField(f);
			}
			 map.addRepeatingFieldGroupFactory(rfgFactory, command.getStudySites().get(ssIndex).getStudyPersonnels().size());
		}
		return map;
	}	
 }
