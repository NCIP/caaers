package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class SitesTab extends StudyTab {
	
	
	private RepeatingFieldGroupFactory rfgFactory;
	
    private OrganizationDao organizationDao;

    public SitesTab() {
        super("Study Sites", "Sites", "study/study_sites");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();

        refdata.put("sitesRefData", organizationDao.getAll());
        addConfigMapToRefdata(refdata, "studySiteStatusRefData"); //TODO : Remove
        addConfigMapToRefdata(refdata, "studySiteRoleCodeRefData"); //TODO : Remove
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        String action = request.getParameter("_action");
        if ("removeSite".equals(action)) {
            command.getStudySites().remove(Integer.parseInt(request.getParameter("_selected")));
        }
    }
    
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		 if(rfgFactory == null){
			 rfgFactory = new RepeatingFieldGroupFactory("main", "studySites");
			 InputField siteField = InputFieldFactory.createAutocompleterField("organization", "Site", true);
			 //siteField.getAttributes().put(InputField.DETAILS,"Enter a portion of the site name you are looking for");
			 rfgFactory.addField(siteField);
			 rfgFactory.addField(InputFieldFactory.createSelectField("roleCode", "Role", true, 
						collectOptionsFromConfig("studySiteRoleCodeRefData", "desc","desc")));
			 rfgFactory.addField(InputFieldFactory.createDateField("irbApprovalDate","IRB Approval Date", false));
		 }
		 InputFieldGroupMap map = new InputFieldGroupMap();
		 Study study = (Study)command;
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

	public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
    
}
