package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * 
 */
public class MedicalInfoTab extends AeTab {
    private ConfigProperty configurationProperty;

    public MedicalInfoTab() {
        super("Medical information", ExpeditedReportSection.MEDICAL_INFO_SECTION.getDisplayName(), "ae/medical");
        super.addHelpKeyExclusion("height","weight","quantity","unit","baselinePerformanceStatus", "otherPrimaryDiseaseSite","otherSite");
        super.setAutoPopulateHelpKey(true);
    }

    // TODO: configurationProperty and all things associated with it are abhorrent
    private Map<Object, Object> optionsFromConfigurationProperty(String propName) {
        return optionsFromConfigurationProperty(propName, null);
    }

    private Map<Object, Object> optionsFromConfigurationProperty(String propName, String blankValue) {
        List<Lov> lov = configurationProperty.getMap().get(propName);
        if (lov == null) throw new CaaersSystemException("No LOV for " + propName);
        return WebUtils.collectOptions(lov, "code", "desc", blankValue);
    }
    
    private List<AbstractStudyDisease> optionsForStudyDiseases(Study study){
    	List<AbstractStudyDisease> diseases = new ArrayList<AbstractStudyDisease>();
    	diseases.addAll(study.getCtepStudyDiseases());
    	diseases.addAll(study.getMeddraStudyDiseases());
    	return diseases;
    }
    
    private Map<Object, Object> optionsForCtep(ExpeditedAdverseEventInputCommand command){
    	Map<Object, Object> ctepStudyDiseaseOptions = new LinkedHashMap<Object, Object>();
    	ctepStudyDiseaseOptions.put("","Please select");
    	ctepStudyDiseaseOptions.putAll(WebUtils.collectOptions(command.getStudy().getCtepStudyDiseases(),"id", "term.term"));
    	return ctepStudyDiseaseOptions;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        creator.createFieldGroup("participant", null, "participantHistory",
            createParticipantMeasureField("height", "Height",
                optionsFromConfigurationProperty("heightUnitsRefData")),
            createParticipantMeasureField("weight", "Weight",
                optionsFromConfigurationProperty("weightUnitsRefData")),
            InputFieldFactory.createSelectField("baselinePerformanceStatus", "Baseline performance",
                false, optionsFromConfigurationProperty("bpsRefData", "Please select"))
        );

        InputField studyDisease = null;
        // Business Rule default to CTEP , if MedDRA then return MedDRA
        Map<Object, Object> ctepStudyDiseaseOptions = WebUtils
				.collectOptions(command.getStudy().getCtepStudyDiseases(),
						"id", "term.term","");
		studyDisease = InputFieldFactory.createSelectField("ctepStudyDisease",
				"Disease name", false, optionsForCtep(command));
        
        if (((ExpeditedAdverseEventInputCommand)command).getAeReport().getStudy().getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA) {
    		
        	 Map<Object, Object> meddraStudyDiseaseOptions = WebUtils.collectOptions(
             		command.getStudy().getMeddraStudyDiseases(), "id", "term.meddraTerm", "");
        	 studyDisease = InputFieldFactory.createSelectField("meddraStudyDisease", "Disease name", false,
                 meddraStudyDiseaseOptions);
		}
        
       
        
        /*InputFieldAttributes.setDetails(studyDisease, "If the correct disease is not listed in the drop down lists, type the appropriate disease name in the Other (disease) field below.");*/
        InputField diseaseSite = InputFieldFactory.createAutocompleterField("codedPrimaryDiseaseSite", "Primary site of disease", false);
        InputFieldAttributes.setSize(diseaseSite, 33);
        /*InputFieldAttributes.setDetails(diseaseSite, "If the appropriate site cannot be found in the list above, type the primary site of the disease in the Other (site of primary disease) field below.");*/
        InputField otherDiseaseField = InputFieldFactory.createTextField("otherPrimaryDisease", "Other (disease)");
        InputFieldAttributes.setSize(otherDiseaseField, 50);
        /*InputFieldAttributes.setDetails(otherDiseaseField, "If this is a prevention trial, and disease is not applicable, enter Disease Not Applicable.");*/
        InputField diganosisDateField = InputFieldFactory.createSplitDateField("diagnosisDate", "Date of initial diagnosis", false, true, true, false);
        /*InputFieldAttributes.setDetails(diganosisDateField, "If known, enter the date of the initial diagnosis.");*/

        creator.createFieldGroup("disease", null, "diseaseHistory",
        		studyDisease,
            otherDiseaseField,
            diseaseSite,
            InputFieldFactory.createTextField("otherPrimaryDiseaseSite", "Other (site of primary disease)"),
            diganosisDateField
        );

        InputField codedSiteField = InputFieldFactory.createAutocompleterField("codedSite", "Site Name", false);
        /*InputFieldAttributes.setDetails(codedSiteField, "If the appropriate site is not listed, type the specific site in the &quot;Other(site of metastatic disease)&quot; field");*/

        creator.createRepeatingFieldGroup("metastatic", "diseaseHistory.metastaticDiseaseSites",
            new SimpleNumericDisplayNameCreator("Metastatic disease site"),
            codedSiteField,
            InputFieldFactory.createTextField("otherSite", "Other(site of metastatic disease)", false)
        );
    }

    private CompositeField createParticipantMeasureField(String baseName, String baseDisplayName, Map<Object, Object> unitOptions) {
        return new CompositeField(baseName,
            new DefaultInputFieldGroup(null, baseDisplayName)
                .addField(InputFieldFactory.createTextField("quantity", ""))
                .addField(InputFieldFactory.createSelectField("unit", "units", false, unitOptions))
        );
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.MEDICAL_INFO_SECTION;
    }

}
