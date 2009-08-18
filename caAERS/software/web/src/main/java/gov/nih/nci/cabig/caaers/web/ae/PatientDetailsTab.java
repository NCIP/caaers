package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.validators.DecimalRangeValidator;
import gov.nih.nci.cabig.caaers.web.fields.validators.NumberRangeValidator;
import gov.nih.nci.cabig.caaers.web.fields.validators.DecimalValidator;
import gov.nih.nci.cabig.caaers.web.fields.validators.PositiveValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Position;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 * @author Biju Joseph
 *
 */
public class PatientDetailsTab extends AeTab {
	
	private static final Log log = LogFactory.getLog(PatientDetailsTab.class);
	
	private ConfigProperty configurationProperty;
    private PriorTherapyDao priorTherapyDao;
    private PreExistingConditionDao preExistingConditionDao;
    //static options of dropdowns are cached at Tab level. 
    Map<Object,Object> priorTherapyOptions;
    Map<Object,Object> priorTherapyOptionWithNoPriorTherapy;
    
    Map<Object, Object> preExistingConditionOptions;
    Map<Object, Object> baselinePerformanceOptions;
	
	//the below static variables corresponds to the field group names
	private static final String GENERAL = "general";
	private static final String PRIOR_THERAPY = "priorTherapy";
	private static final String PRIOR_THERAPY_AGENT = "priorTherapyAgent";
	
	private static final String METASTATIC_DISEASE_SITE = "metastaticDiseaseSite";
	private static final String PRE_EXISTING_CONDITION = "preExistingCondition";
	private static final String CONCOMITANT_MEDICATION = "concomitantMedication";
	
    Map<String, String> methodNameMap = new HashMap<String, String>();
	
    public PatientDetailsTab() {
        super("Patient Details", ExpeditedReportSection.MEDICAL_INFO_SECTION.getDisplayName(), "ae/patientDetails");
       /* addHelpKeyExclusion("baselinePerformanceStatus");*/
        
        methodNameMap.put("add" + METASTATIC_DISEASE_SITE, "addMetastaticDiseaseSite");
        methodNameMap.put("remove" + METASTATIC_DISEASE_SITE, "removeMetastaticDiseaseSite");
        
        methodNameMap.put("add" + PRE_EXISTING_CONDITION, "addPreExistingCondition");
        methodNameMap.put("remove" + PRE_EXISTING_CONDITION, "removePreExistingCondition");
        
        methodNameMap.put("add" + PRIOR_THERAPY, "addPriorTherapy");
        methodNameMap.put("remove" + PRIOR_THERAPY, "removePriorTherapy");
        
        methodNameMap.put("add" + PRIOR_THERAPY_AGENT, "addPriorTherapyAgent");
        methodNameMap.put("remove" + PRIOR_THERAPY_AGENT, "removePriorTherapyAgent");
        
        methodNameMap.put("add" + CONCOMITANT_MEDICATION, "addConcomitantMedication");
        methodNameMap.put("remove" + CONCOMITANT_MEDICATION, "removeConcomitantMedication");
    }
    
    
    
   @Override
   public Map<String, Object> referenceData(HttpServletRequest request,	ExpeditedAdverseEventInputCommand command) {
	   Map<String, Object> refData = super.referenceData(request, command);
	   refData.put("preExistingConditionOptions", initializePreExistingConditionOptions());
   	   refData.put("baselinePerformanceOptions", initializeBaselinePerformanceOptions());
   	   refData.put("priorTherapyOptions", initializePriorTherapyOptions());
   	   
   	   refData.put("_priorTherapy_surgery_id", PriorTherapy.SURGERY);
   	   refData.put("_priorTherapy_radiation_id", PriorTherapy.RADIATION);
   	   refData.put("_priorTherapy_nopriortherapy_id", PriorTherapy.NO_PRIOR_THERAPY);
   	   
   	   return refData;
   }
    
    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
    	createGeneralFields(creator, command);
    	createDiseaseInformantionFields(creator, command);
    	createMetastaticDiseaseFields(creator,command);
    	createPreExistingConditionFields(creator, command);
    	createConcomitantMedicationFields(creator, command);
    	createPriorTherapyFields(creator, command);
    }

    @Override
    public ExpeditedReportSection[] section() {
    	return new ExpeditedReportSection[] {
    			ExpeditedReportSection.MEDICAL_INFO_SECTION, 
    			ExpeditedReportSection.PRIOR_THERAPIES_SECTION, 
    			ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION, 
    			ExpeditedReportSection.PRE_EXISTING_CONDITION_SECTION
    	};
    }
    
    /**
     * Creates BaseLine
     * 
     */
    private void createGeneralFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        creator.createFieldGroup("participant", null, "participantHistory", createParticipantMeasureField("height", "Height", WebUtils.collectOptions(configurationProperty.getMap().get("heightUnitsRefData"), "code", "desc")), createParticipantMeasureField("weight", "Weight", WebUtils.collectOptions(configurationProperty.getMap().get("weightUnitsRefData"), "code", "desc")));
        creator.createFieldGroup("general", null, "participantHistory", InputFieldFactory.createSelectField("baselinePerformanceStatus", "Baseline performance", false, WebUtils.collectOptions(configurationProperty.getMap().get("bpsRefData"), "code", "desc", "Please select")));
    }
    

    private CompositeField createParticipantMeasureField(String baseName, String baseDisplayName, Map<Object, Object> unitOptions) {
        return new CompositeField(baseName,
            new DefaultInputFieldGroup(null, baseDisplayName)
                .addField(InputFieldFactory.createTextField("quantity", "", new PositiveValidator(true), new DecimalValidator(5, 2)))
                .addField(InputFieldFactory.createSelectField("unit", "units", false, unitOptions))
        );
    }
    
    /**
     * Create fields related to diseases history
     * 
     */
    private void createDiseaseInformantionFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
    	InputField studyDiseaseField = null;

        DiseaseCodeTerm dct = command.getAeReport().getStudy().getDiseaseTerminology().getDiseaseCodeTerm();
        if (dct == DiseaseCodeTerm.MEDDRA) {
            studyDiseaseField = InputFieldFactory.createSelectField("abstractStudyDisease", "Disease name", false, command.getStudyDiseasesOptions(DiseaseCodeTerm.MEDDRA));
        } else if (dct == DiseaseCodeTerm.OTHER) {
            studyDiseaseField = InputFieldFactory.createSelectField("abstractStudyDisease", "Disease name", false, command.getStudyDiseasesOptions(DiseaseCodeTerm.OTHER));
        } else {
            studyDiseaseField = InputFieldFactory.createSelectField("abstractStudyDisease", "Disease name", false, command.getStudyDiseasesOptions(DiseaseCodeTerm.CTEP));
        }

    	InputField diseaseSite = InputFieldFactory.createAutocompleterField("codedPrimaryDiseaseSite", "Primary site of disease", false);
        InputFieldAttributes.setSize(diseaseSite, 40);
        InputField otherDiseaseField = InputFieldFactory.createTextField("otherPrimaryDisease", "Other (disease)");
        InputFieldAttributes.setSize(otherDiseaseField, 50);
        InputField diganosisDateField = InputFieldFactory.createSplitDateField("diagnosisDate", "Date of initial diagnosis", false, true, true, false);
        
        
        creator.createFieldGroup("disease", null, "diseaseHistory",
        	studyDiseaseField,
            otherDiseaseField,
            diseaseSite,
            InputFieldFactory.createTextField("otherPrimaryDiseaseSite", "Other (site of primary disease)"),
            diganosisDateField
        );

    }
    
    /**
     * Create fields related to metastatic diseases 
     * 
     */
    private void createMetastaticDiseaseFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
    	List<MetastaticDiseaseSite> sites = command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSites();
    	if(sites == null || sites.isEmpty()) return;

        creator.createRepeatingFieldGroup("metastatic", "diseaseHistory.metastaticDiseaseSites",
            new SimpleNumericDisplayNameCreator("Metastatic disease site"),
            InputFieldFactory.createAutocompleterField("codedSite", "Site Name", false),
            InputFieldFactory.createTextField("otherSite", "Other(site of metastatic disease)", false)
        );
    }
    
    /**
     * Create fields related to pre-existing conditions
     * 
     */
    private void createPreExistingConditionFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        InputField preCondField = InputFieldFactory.createSelectField("preExistingCondition","Pre-existing condition", false, initializePreExistingConditionOptions());
        InputField otherField = InputFieldFactory.createTextField("other", "Other", false);
        InputFieldAttributes.setSize(otherField, 50);
        creator.createRepeatingFieldGroup("preExistingCondition", "saeReportPreExistingConditions", new SimpleNumericDisplayNameCreator("Pre-existing condition"), preCondField, otherField);
    }
    
    
    /**
     * Creates fields related to con-meds
     * 
     */
    private void createConcomitantMedicationFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        InputField agentNameField = InputFieldFactory.createTextField("agentName", "Medication name", false);
        InputFieldAttributes.setSize(agentNameField, 50);
        InputField startDateField = InputFieldFactory.createSplitDateField("startDate", "Start date", false, false, false, false);
        InputField endDateField = InputFieldFactory.createSplitDateField("endDate", "End date", false, false, false, false);
        InputField stillTakingMedicationField = InputFieldFactory.createCheckboxField("stillTakingMedications", "Still taking medication?");
        creator.createRepeatingFieldGroup("conmed", "concomitantMedications",new SimpleNumericDisplayNameCreator("Medication"), agentNameField,stillTakingMedicationField, startDateField, endDateField);
    }
    
    /**
     * Creates fields related to prior therpies
     * 
     */
    private void createPriorTherapyFields(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        InputField priorTherapyField = InputFieldFactory.createSelectField("priorTherapy", "Prior therapy", false, initializePriorTherapyOptions());
        InputField otherField = InputFieldFactory.createTextArea("other", "Comments", false);
        InputFieldAttributes.setColumns(otherField, 65);
        InputField startDateField = InputFieldFactory.createSplitDateField("startDate", "Therapy start date", false, true, true, false);
        InputField endDateField = InputFieldFactory.createSplitDateField("endDate", "Therapy end date", false, true, true, false);
        
        creator.createRepeatingFieldGroup("priorTherapy", "saeReportPriorTherapies",
            new SimpleNumericDisplayNameCreator("Prior therapy"),
            priorTherapyField,
            otherField,
            startDateField,
            endDateField
            
        );

        creator.createRepeatingFieldGroup("ptAgent", "saeReportPriorTherapies", InputFieldFactory.createTextField("priorTherapyAgents.chemoAgent", "Agent", false));
    }
    
    
    /**
     * This is the base validate method, which delegates the call to individual section validate. 
     */
    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    	validateDiseaseInformation(command, commandBean, fieldGroups, errors);
    	validateMetastaticDiseases(command, commandBean, fieldGroups, errors);
    	validatePreExistingConditions(command, commandBean, fieldGroups, errors);
    	validateConcomitantMedications(command, commandBean, fieldGroups, errors);
    	validatePriorTherapies(command, commandBean, fieldGroups, errors);
        WebUtils.populateErrorFieldNames(command.getRulesErrors(), errors);
    }
    
    // ---------------- validation on individual items -------------------------
    
    protected void validateDiseaseInformation(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
    }

    protected void validateMetastaticDiseases(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
    	//aeReport.diseaseHistory.metastaticDiseaseSites[1].otherSite
    	int i =0;
    	Set<MetastaticDiseaseSite> set = new HashSet<MetastaticDiseaseSite>();
    	for(MetastaticDiseaseSite mSite : command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSites()){
            if (mSite.getCodedSite() == null || mSite.getCodedSite().getId() == null) {
                errors.rejectValue(String.format("aeReport.diseaseHistory.metastaticDiseaseSites[%d].codedSite", i), "SAE_026","Missing Metastatic disease site");
            } else {
                if (mSite.getCodedSite().getId().equals(110) && StringUtils.isEmpty(mSite.getOtherSite())) {
                    errors.rejectValue(String.format("aeReport.diseaseHistory.metastaticDiseaseSites[%d].otherSite", i), "SAE_014", "Missing other metastatic site information");
                }

                if (!set.add(mSite)) {
                    errors.rejectValue(String.format("aeReport.diseaseHistory.metastaticDiseaseSites[%d].otherSite", i), "SAE_013", new Object[]{mSite.getCodedSite().getName()}, "Duplicate metastatic site information");
                }
            }
            i++;
    	}
    }
    
    protected void validatePreExistingConditions(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
    	int i = 0;
    	Set<SAEReportPreExistingCondition> set = new HashSet<SAEReportPreExistingCondition>();
    	for(SAEReportPreExistingCondition preCond : command.getAeReport().getSaeReportPreExistingConditions()){
    		if(preCond.getPreExistingCondition() == null && preCond.getOther() == null){
    			errors.rejectValue(String.format("aeReport.saeReportPreExistingConditions[%d]", i), "SAE_015", "Either a known pre Existing Condition or other is required");
    		}
    		if(!set.add(preCond)){
    			errors.rejectValue(String.format("aeReport.saeReportPreExistingConditions[%d]", i), "SAE_016", new Object[]{preCond.getName()}, "Duplicate pre Existing condition");
    		}
    		i++;
    	}
    }
    
    protected void validateConcomitantMedications(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
    	int i = 0;
    	Set<ConcomitantMedication> set = new HashSet<ConcomitantMedication>();
    	String propertyName = null;
        
        for(ConcomitantMedication conMed : command.getAeReport().getConcomitantMedications()){
    		propertyName = String.format("aeReport.concomitantMedications[%d].agentName", i);
            if (conMed.getName() == null) {
                errors.rejectValue(propertyName, "SAE_027",new Object[]{conMed.getName()}, "Missing Concomitant Medication");
            }

            if(!set.add(conMed)){
    			errors.rejectValue(propertyName, "SAE_017",new Object[]{conMed.getName()}, "Duplicate concomitant medication");
    		}

            if (!conMed.getStillTakingMedications() && !conMed.getEndDate().isNull() && conMed.getStartDate().compareTo(conMed.getEndDate()) > 0) {
                propertyName = String.format("aeReport.concomitantMedications[%d].endDate", i);
                errors.rejectValue(propertyName, "SAE_024", new Object[]{conMed.getName()}, "The 'End date' can not be before the 'Start Date'");
            }

            if (BooleanUtils.isTrue(conMed.getStillTakingMedications()) && !conMed.getEndDate().isNull()) {
                conMed.getEndDate().setDay(null);
                conMed.getEndDate().setMonth(null);
                conMed.getEndDate().setYear(null);
                // errors.rejectValue(propertyName, "SAE_018", "End date not allowed when continuing medication");
            } else {
                // here goes the end date validation
            }

            i++;
        }
    }
    
    protected void validatePriorTherapies(ExpeditedAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,Errors errors) {
        int i = 0;
        Set<PriorTherapy> set = new HashSet<PriorTherapy>();
        String propertyName = null;

        for(SAEReportPriorTherapy pt: command.getAeReport().getSaeReportPriorTherapies()){
            if (pt == null || pt.getName() == null) {
                propertyName = String.format("aeReport.saeReportPriorTherapies[%d].priorTherapy", i);
                errors.rejectValue(propertyName, "SAE_028", "Missing Prior Therapy");
            }

/*
            int j = 0;
            for (PriorTherapyAgent ptAgent : pt.getPriorTherapyAgents()) {
                String agentPropertyName = String.format("aeReport.saeReportPriorTherapies[%d].priorTherapyAgents[%d].chemoAgent", i, j);
                if (ptAgent == null || ptAgent.getName() == null || ptAgent.getName().trim().equals("")) {
                    errors.rejectValue(agentPropertyName, "SAE_034", "Missing Prior Therapy Agent");
                }
                j++;
            }
*/
            i++;
        }
    }
    
    //----- Create/Edit/Save/Delete operations (tasks) ----------------- 
    
    public ModelAndView addMetastaticDiseaseSite(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/metastaticDiseaseSiteFormSection");
    	List<MetastaticDiseaseSite> sites = command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSites();
    	modelAndView.getModel().put("metastaticDiseaseSites", sites);
    	int size = sites.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
//    	AnatomicSite site = command.getMetastaticDiseaseSite();
    	MetastaticDiseaseSite metastaticSite = new MetastaticDiseaseSite();
//    	metastaticSite.setCodedSite(site);
    	command.getAeReport().getDiseaseHistory().addMetastaticDiseaseSite(metastaticSite);
//    	command.setMetastaticDiseaseSite(null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removeMetastaticDiseaseSite(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/metastaticDiseaseSiteFormSection");
    	List<MetastaticDiseaseSite> sites = command.getAeReport().getDiseaseHistory().getMetastaticDiseaseSites();
    	sites.remove(sites.get(command.getIndex())); //remove the object from command. 
    	
    	//create the indexs to display in reverse order
    	int size = sites.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	modelAndView.getModel().put("metastaticDiseaseSites", sites);
    	modelAndView.getModel().put("indexes", indexes);
    	return modelAndView;
    }
    
    public ModelAndView addPreExistingCondition(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<SAEReportPreExistingCondition> preConditions = command.getAeReport().getSaeReportPreExistingConditions();
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/preExistingCondFormSection");
    	modelAndView.getModel().put("preExistingConditions", preConditions);
    	int size = preConditions.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	SAEReportPreExistingCondition preCondition = new SAEReportPreExistingCondition();
//    	preCondition.setPreExistingCondition(command.getPreExistingCondition());
    	command.getAeReport().addSaeReportPreExistingCondition(preCondition);
//    	command.setPreExistingCondition(null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removePreExistingCondition(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<SAEReportPreExistingCondition> preConditions = command.getAeReport().getSaeReportPreExistingConditions();
    	preConditions.remove(preConditions.get(command.getIndex())); //remove the element
    	
    	//create the indexes in reverse order
    	int size = preConditions.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/preExistingCondFormSection");
    	modelAndView.getModel().put("preExistingConditions", preConditions);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }    
    
    public ModelAndView addConcomitantMedication(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<ConcomitantMedication> conmeds = command.getAeReport().getConcomitantMedications();
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/conMedFormSection");
    	modelAndView.getModel().put("concomitantMedications", conmeds);
    	int size = conmeds.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	ConcomitantMedication conmed = new ConcomitantMedication();
    	conmed.setStartDate(new DateValue());
    	conmed.setEndDate(new DateValue());
    	command.getAeReport().addConcomitantMedication(conmed);
    	
    	return modelAndView;
    }
    
    public ModelAndView removeConcomitantMedication(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<ConcomitantMedication> conmeds = command.getAeReport().getConcomitantMedications();
    	ConcomitantMedication conMed = conmeds.get(command.getIndex()); 
    	command.deleteAttribution(conMed);
    	conmeds.remove(conMed); //remove the element
    	
    	//create the indexes in reverse order
    	int size = conmeds.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/conMedFormSection");
    	modelAndView.getModel().put("concomitantMedications", conmeds);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }
    
    public ModelAndView addPriorTherapy(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<SAEReportPriorTherapy> priorTherapies = command.getAeReport().getSaeReportPriorTherapies();
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/priorTherapyFormSection");
    	modelAndView.getModel().put("priorTherapies", priorTherapies);
    	int size = priorTherapies.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	SAEReportPriorTherapy priorTherapy = new SAEReportPriorTherapy();
    	// priorTherapy.setPriorTherapy(command.getPriorTherapy());
    	priorTherapy.setStartDate(new DateValue());
    	priorTherapy.setEndDate(new DateValue());
    	priorTherapy.setPriorTherapyAgentsInternal(new ArrayList<PriorTherapyAgent>());
    	command.getAeReport().addSaeReportPriorTherapies(priorTherapy);
    	// command.setPriorTherapy(null);
    	command.getPriorTherapyAgents().add(null); //increment the element size
    	
    	return modelAndView;
    }
    
    public ModelAndView removePriorTherapy(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	List<SAEReportPriorTherapy> priorTherapies = command.getAeReport().getSaeReportPriorTherapies();
    	priorTherapies.remove(priorTherapies.get(command.getIndex())); //remove the element
    	command.getPriorTherapyAgents().remove(command.getIndex()); //decrement the size of priortherapy agents by 1. 
    	
    	//create the indexes in reverse order
    	int size = priorTherapies.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/priorTherapyFormSection");
    	modelAndView.getModel().put("priorTherapies", priorTherapies);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }
    
    public ModelAndView addPriorTherapyAgent(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	SAEReportPriorTherapy priorTherapy = command.getAeReport().getSaeReportPriorTherapies().get(command.getParentIndex());
    	List<PriorTherapyAgent> priorTherapyAgents = priorTherapy.getPriorTherapyAgents();
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/priorTherapyAgentFormSection");
    	modelAndView.getModel().put("priorTherapyAgents", priorTherapyAgents);
    	modelAndView.getModel().put("parentIndex", command.getParentIndex());
    	int size = priorTherapyAgents.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	//NOTE : firefox for some reason is chopping off the '[x]' in the variable name, so had to do this hoof-up in obtaining the chemoagent.
    	PriorTherapyAgent agent = new PriorTherapyAgent();
//    	ChemoAgent chemoAgent = command.getPriorTherapyAgent();
//    	command.setPriorTherapyAgent(null);
    	/*
    	 * I dont know why ajax calls strip off array parameter - priorTherapyAgents[x]
    	 * 
    	 * if(chemoAgent == null){
    		chemoAgent = command.getPriorTherapyAgents().get(command.getParentIndex());
    		command.getPriorTherapyAgents().set(command.getParentIndex(), null);
    	}
    	*/
//    	agent.setChemoAgent(chemoAgent);
    	priorTherapy.addPriorTherapyAgent(agent);
    	
    	return modelAndView;
    }
    
    public ModelAndView removePriorTherapyAgent(HttpServletRequest request , Object cmd, Errors errors){
    	AbstractExpeditedAdverseEventInputCommand command =(AbstractExpeditedAdverseEventInputCommand)cmd;
    	SAEReportPriorTherapy priorTherapy = command.getAeReport().getSaeReportPriorTherapies().get(command.getParentIndex());
    	List<PriorTherapyAgent> priorTherapyAgents = priorTherapy.getPriorTherapyAgents();
    	
    	priorTherapyAgents.remove(priorTherapyAgents.get(command.getIndex())); //remove the element
    	
    	//create the indexes in reverse order
    	int size = priorTherapyAgents.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("ae/ajax/priorTherapyAgentFormSection");
    	modelAndView.getModel().put("priorTherapyAgents", priorTherapyAgents);
    	modelAndView.getModel().put("indexes", indexes);
    	modelAndView.getModel().put("parentIndex", command.getParentIndex());
    	
    	return modelAndView;
    }
   
    @Override
    public void postProcess(HttpServletRequest request,	ExpeditedAdverseEventInputCommand command, Errors errors) {
    	if(!errors.hasErrors()){
    		AbstractExpeditedAdverseEventInputCommand cmd =(AbstractExpeditedAdverseEventInputCommand)command;
    		cmd.synchronizeAndSaveAssignment();
    	}
    }
    
    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return methodNameMap.get(task + currentItem);
    }
    
    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }
    
    
    /**
     * Will initialize the Prior therapy drop down options
     * @return
     */
    private Map<Object, Object> initializePriorTherapyOptions() {
    	if(priorTherapyOptions == null){
    		this.priorTherapyOptions = WebUtils.collectOptions(priorTherapyDao.getAllExcludingNoPriorTherapy(),"id", "text","Please select");
            log.debug("Prior Therapies Found: " + this.priorTherapyOptions.size());
        }
        return priorTherapyOptions;
    }
    
    
    /**
     * Will initialize the pre-existing condition options.
     * @return
     */
    private Map<Object, Object> initializePreExistingConditionOptions(){
    	if(preExistingConditionOptions == null){
    		 List<PreExistingCondition> list = preExistingConditionDao.getAll();
    	        if (list != null) {
    	        	preExistingConditionOptions = new LinkedHashMap<Object, Object>();
    	        	preExistingConditionOptions.put(" ", " Please select                                    .");
    	        	preExistingConditionOptions.putAll(WebUtils.collectOptions(list, "id", "text", "Other, specify"));
    	        }
    	}
    	return preExistingConditionOptions;
    }
    /**
     * Will return the options for baseline performance
     * @return
     */
    private Map<Object, Object> initializeBaselinePerformanceOptions() {
    	if(baselinePerformanceOptions == null){
    		baselinePerformanceOptions = WebUtils.collectOptions(configurationProperty.getMap().get("bpsRefData"), "code", "desc", " Please select                                    .");
    	}
    	return baselinePerformanceOptions;
    }
    
  
    
    ///OBJECT METHODS
    public PriorTherapyDao getPriorTherapyDao() {
		return priorTherapyDao;
	}
    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}
    public PreExistingConditionDao getPreExistingConditionDao() {
		return preExistingConditionDao;
	}
    public void setPreExistingConditionDao(
			PreExistingConditionDao preExistingConditionDao) {
		this.preExistingConditionDao = preExistingConditionDao;
	}
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
    public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}
}