package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
*/
class SitesTab extends StudyTab {


	private RepeatingFieldGroupFactory rfgFactory;

    public SitesTab() {
        super("Study Sites", "Sites", "study/study_sites");
    }

    @Override
	public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        String action = request.getParameter("_action");

        if ("removeSite".equals(action)) {
        	int index = Integer.parseInt(request.getParameter("_selected"));
        	StudySite site = command.getStudySites().get(index);
        	if(CollectionUtils.isEmpty(site.getStudyInvestigators()) &&
        	   CollectionUtils.isEmpty(site.getStudyPersonnels())){
        		command.getStudySites().remove(index);
        		return;
        	}
        	errors.reject("site.notempty", "Site that is being deleted is associated to investigators and research staffs");
        }
    }

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		 if(rfgFactory == null){
			 rfgFactory = new RepeatingFieldGroupFactory("main", "studySites");
			 InputField siteField = InputFieldFactory.createAutocompleterField("organization", "Site", true);
			 siteField.getAttributes().put(InputField.ENABLE_CLEAR, true);
			 //siteField.getAttributes().put(InputField.DETAILS,"Enter a portion of the site name you are looking for");
			 rfgFactory.addField(siteField);
		 }
		 InputFieldGroupMap map = new InputFieldGroupMap();
		 Study study = command;
		 map.addRepeatingFieldGroupFactory(rfgFactory, study.getStudySites().size());
		 return map;
	}

	@Override
	protected void validate(Study command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);
		//check if there are duplicate sites.
		HashSet<String> set = new HashSet<String>();
		int size = command.getStudySites().size();
		for(int i = 0; i < size ; i++){
			List<InputField> fields = fieldGroups.get("main"+i).getFields();
			if(!set.add(fieldValuesAsString(fields, commandBean))){
				rejectFields(fields, errors, "Duplicate");
			}
		}
	}

}
