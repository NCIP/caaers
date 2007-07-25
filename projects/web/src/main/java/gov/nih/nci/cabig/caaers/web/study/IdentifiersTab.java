package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.Study;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class IdentifiersTab extends StudyTab {
    private OrganizationDao organizationDao;
	private RepeatingFieldGroupFactory rfgFactory;
	
    public IdentifiersTab() {
        super("Study Identifiers", "Identifiers", "study/study_identifiers");
        setAutoPopulateHelpKey(true);
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();

        refdata.put("identifiersSourceRefData", organizationDao.getAll());
        refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
            String action = request.getParameter("_action");
            String selected = request.getParameter("_selected");
            if ("removeIdentifier".equals(action)) {
            	Study study = (Study) command;
                study.getIdentifiersLazy().remove(Integer.parseInt(selected));
            }
    }

    
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		 if(rfgFactory == null){
			 rfgFactory = new RepeatingFieldGroupFactory("main", "identifiersLazy");
			 rfgFactory.addField(InputFieldFactory.createTextField("value","Identifier", true));
			 rfgFactory.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true,
				collectOptionsFromConfig("identifiersType", "desc","desc")));
			 //InputField sourceField = InputFieldFactory.createAutocompleterField("source", "Assigning Authority", true);
			 //sourceField.getAttributes().put(InputField.DETAILS,"Enter a portion of the site name you are looking for");
			 //rfgFactory.addField(sourceField);
			 rfgFactory.addField(InputFieldFactory.createSelectField("source", "Assigning Authority", true,
					 collectOptions(organizationDao.getAll(), "name", "name")));
			 rfgFactory.addField(InputFieldFactory.createCheckboxField("primaryIndicator","Primary Indicator"));
		 }
		 Study study = (Study)command;
		 InputFieldGroupMap map = new InputFieldGroupMap();
		 map.addRepeatingFieldGroupFactory(rfgFactory, study.getIdentifiersLazy().size());
		 return map;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
