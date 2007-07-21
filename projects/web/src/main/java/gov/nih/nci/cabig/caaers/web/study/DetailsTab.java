package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
*/
class DetailsTab extends StudyTab {
    private CtcDao ctcDao;
    InputFieldGroup fieldGroup;

    public DetailsTab() {
        super("Study Details", "Details", "study/study_details");
        setAutoPopulateHelpKey(true);
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        //TODO : to be removed from reference data, as they are no longer used.
        addConfigMapToRefdata(refdata, "phaseCodeRefData"); //remove
        addConfigMapToRefdata(refdata, "sponsorCodeRefData"); //remove
        refdata.put("ctcVersion", ctcDao.getAll()); //remove
        return refdata;
    }
    
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		if(fieldGroup ==  null){
			//set up the fields
	        fieldGroup  = new DefaultInputFieldGroup("studyDetails");
			List<InputField> fields = fieldGroup.getFields();
			DefaultTextField shortTitleField = new DefaultTextField("shortTitle","Short title", true);
			shortTitleField.setSize(50);
			fields.add(shortTitleField);
			DefaultTextArea longTitleField = new DefaultTextArea("longTitle", "Long title", true);
			longTitleField.setColumns(50);
			fields.add(longTitleField);
			DefaultTextArea precisField = new DefaultTextArea("precis", "Precis", false);
			precisField.setColumns(50);
			fields.add(precisField);
			DefaultTextArea descField = new DefaultTextArea("description", "Description", false);
			descField.setColumns(50);
			fields.add(descField);
			AutocompleterField sponsorField = new AutocompleterField("primaryFundingSponsorOrganization", "Primary sponsor", true);
			//sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the sponsor name you are looking for");
			fields.add(sponsorField);
			fields.add(new DefaultSelectField("phaseCode", "Phase", true, 
				collectOptionsFromConfig("phaseCodeRefData", "desc","desc")));
			fields.add(new DefaultSelectField("ctcVersion", "CTC version", true,
				collectOptions(ctcDao.getAll(), "id","name")));
		}
		InputFieldGroupMap map  = new InputFieldGroupMap();
		map.addInputFieldGroup(fieldGroup);
		return map;
	}


    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
}
