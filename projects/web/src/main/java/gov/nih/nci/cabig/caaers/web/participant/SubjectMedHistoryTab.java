package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantMetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapyAgent;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Biju Joseph
 *
 */
public class SubjectMedHistoryTab <T extends ParticipantInputCommand> extends TabWithFields<T> {
	
	//the below static variables corresponds to the field group names
	private static final String GENERAL = "general";
	private static final String PRIOR_THERAPY = "priorTherapy";
	private static final String PRIOR_THERAPY_AGENT = "priorTherapyAgent";
	
	private static final String METASTATIC_DISEASE_SITE = "metastaticDiseaseSite";
	private static final String PRE_EXISTING_CONDITION = "preExistingCondition";
	private static final String CONCOMITANT_MEDICATION = "concomitantMedication";
	
    private static final Log log = LogFactory.getLog(SubjectMedHistoryTab.class);
    Map<String, String> methodNameMap = new HashMap<String, String>();

	protected HashMap<String, Boolean> emptyFieldNameMap;

    private PriorTherapyDao priorTherapyDao;
    private PreExistingConditionDao preExistingConditionDao;
    private ConfigProperty configurationProperty;
    
    
    //static options of dropdowns are cached at Tab level. 
    Map<Object,Object> priorTherapyOptions;
    Map<Object, Object> preExistingConditionOptions;
    Map<Object, Object> baselinePerformanceOptions;
    
	public SubjectMedHistoryTab() {
        super("Subject Medical History", "Subject Medical History", "par/par_subject_med_history");
        
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
    public Map<String, InputFieldGroup> createFieldGroups(T command) {
    	return new  InputFieldGroupMap();
	}

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, T command) {
    	Map<String, Object> refData = super.referenceData(request, command);
        command.refreshStudyDiseases();

        refData.put("preExistingConditionOptions", initializePreExistingConditionOptions());
    	refData.put("studyDiseasesOptions", command.getStudyDiseasesMap());
    	refData.put("baselinePerformanceOptions", initializeBaselinePerformanceOptions());
    	refData.put("priorTherapyOptions", initializePriorTherapyOptions());
        request.setAttribute("empties", emptyFieldNameMap);
        
        refData.put("_priorTherapy_surgery_id", PriorTherapy.SURGERY);
    	refData.put("_priorTherapy_radiation_id", PriorTherapy.RADIATION);
    	refData.put("_priorTherapy_nopriortherapy_id", PriorTherapy.NO_PRIOR_THERAPY);
    	
        return refData;
    	
    }
    
 
    
    //----- Create/Edit/Save/Delete operations (tasks) ----------------- 
    
    public ModelAndView addMetastaticDiseaseSite(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	ModelAndView modelAndView = new ModelAndView("par/ajax/metastaticDiseaseSiteFormSection");
    	List<StudyParticipantMetastaticDiseaseSite> sites = command.getAssignment().getDiseaseHistory().getMetastaticDiseaseSites();
    	modelAndView.getModel().put("metastaticDiseaseSites", sites);
    	int size = sites.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	// AnatomicSite site = command.getMetastaticDiseaseSite();
    	StudyParticipantMetastaticDiseaseSite metastaticSite = new StudyParticipantMetastaticDiseaseSite();
    	// metastaticSite.setCodedSite(site);
    	command.getAssignment().getDiseaseHistory().addMetastaticDiseaseSite(metastaticSite);
    	// command.setMetastaticDiseaseSite(null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removeMetastaticDiseaseSite(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	ModelAndView modelAndView = new ModelAndView("par/ajax/metastaticDiseaseSiteFormSection");
    	List<StudyParticipantMetastaticDiseaseSite> sites = command.getAssignment().getDiseaseHistory().getMetastaticDiseaseSites();
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
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantPreExistingCondition> preConditions = command.getAssignment().getPreExistingConditions();
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/preExistingConditionFormSection");
    	modelAndView.getModel().put("preExistingConditions", preConditions);
    	int size = preConditions.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	StudyParticipantPreExistingCondition preCondition = new StudyParticipantPreExistingCondition();
    	//preCondition.setPreExistingCondition(command.getPreExistingCondition());
    	command.getAssignment().addPreExistingCondition(preCondition);
    	//command.setPreExistingCondition(null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removePreExistingCondition(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantPreExistingCondition> preConditions = command.getAssignment().getPreExistingConditions();
    	preConditions.remove(preConditions.get(command.getIndex())); //remove the element
    	
    	//create the indexes in reverse order
    	int size = preConditions.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/preExistingConditionFormSection");
    	modelAndView.getModel().put("preExistingConditions", preConditions);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }    

    
    
    public ModelAndView addConcomitantMedication(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantConcomitantMedication> conmeds = command.getAssignment().getConcomitantMedications();
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/concomitantMedicationFormSection");
    	modelAndView.getModel().put("concomitantMedications", conmeds);
    	int size = conmeds.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	StudyParticipantConcomitantMedication conmed = new StudyParticipantConcomitantMedication();
    	conmed.setAgentName(command.getConcomitantMedication());
    	conmed.setStartDate(new DateValue());
    	conmed.setEndDate(new DateValue());
    	command.getAssignment().addConcomitantMedication(conmed);
    	command.setConcomitantMedication(null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removeConcomitantMedication(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantConcomitantMedication> conmeds = command.getAssignment().getConcomitantMedications();
    	conmeds.remove(conmeds.get(command.getIndex())); //remove the element
    	
    	//create the indexes in reverse order
    	int size = conmeds.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/concomitantMedicationFormSection");
    	modelAndView.getModel().put("concomitantMedications", conmeds);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }
    
    
    public ModelAndView addPriorTherapy(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantPriorTherapy> priorTherapies = command.getAssignment().getPriorTherapies();
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/priorTherapyFormSection");
    	modelAndView.getModel().put("priorTherapies", priorTherapies);
    	int size = priorTherapies.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	
    	StudyParticipantPriorTherapy priorTherapy = new StudyParticipantPriorTherapy();
    	// priorTherapy.setPriorTherapy(command.getPriorTherapy());
    	priorTherapy.setStartDate(new DateValue());
    	priorTherapy.setEndDate(new DateValue());
    	priorTherapy.setPriorTherapyAgents(new ArrayList<StudyParticipantPriorTherapyAgent>());
    	command.getAssignment().addPriorTherapy(priorTherapy);
    	// command.setPriorTherapy(null);
    	command.getPriorTherapyAgents().add(null); //increment the element size
    	
    	return modelAndView;
    }
    
    public ModelAndView removePriorTherapy(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	List<StudyParticipantPriorTherapy> priorTherapies = command.getAssignment().getPriorTherapies();
    	priorTherapies.remove(priorTherapies.get(command.getIndex())); //remove the element
    	command.getPriorTherapyAgents().remove(command.getIndex()); //decrement the size of priortherapy agents by 1. 
    	
    	//create the indexes in reverse order
    	int size = priorTherapies.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/priorTherapyFormSection");
    	modelAndView.getModel().put("priorTherapies", priorTherapies);
    	modelAndView.getModel().put("indexes", indexes);
    	
    	return modelAndView;
    }
    
    public ModelAndView addPriorTherapyAgent(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	StudyParticipantPriorTherapy priorTherapy = command.getAssignment().getPriorTherapies().get(command.getParentIndex());
    	List<StudyParticipantPriorTherapyAgent> priorTherapyAgents = priorTherapy.getPriorTherapyAgents();
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/priorTherapyAgentFormSection");
    	modelAndView.getModel().put("priorTherapyAgents", priorTherapyAgents);
    	modelAndView.getModel().put("parentIndex", command.getParentIndex());
    	int size = priorTherapyAgents.size();
    	Integer[] indexes = new Integer[]{size};
    	modelAndView.getModel().put("indexes", indexes);
    	//NOTE : firefox for some reason is chopping off the '[x]' in the variable name, so had to do this goof-up in obtaining the chemoagent.
    	StudyParticipantPriorTherapyAgent agent = new StudyParticipantPriorTherapyAgent();
    	// agent.setChemoAgent(command.getPriorTherapyAgent());
    	priorTherapy.addPriorTherapyAgent(agent);
    	// u command.setPriorTherapyAgent( null);
    	
    	return modelAndView;
    }
    
    public ModelAndView removePriorTherapyAgent(HttpServletRequest request , Object cmd, Errors errors){
    	ParticipantInputCommand command =(ParticipantInputCommand)cmd;
    	StudyParticipantPriorTherapy priorTherapy = command.getAssignment().getPriorTherapies().get(command.getParentIndex());
    	List<StudyParticipantPriorTherapyAgent> priorTherapyAgents = priorTherapy.getPriorTherapyAgents();
    	
    	priorTherapyAgents.remove(priorTherapyAgents.get(command.getIndex())); //remove the element
    	
    	//create the indexes in reverse order
    	int size = priorTherapyAgents.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/priorTherapyAgentFormSection");
    	modelAndView.getModel().put("priorTherapyAgents", priorTherapyAgents);
    	modelAndView.getModel().put("indexes", indexes);
    	modelAndView.getModel().put("parentIndex", command.getParentIndex());
    	
    	return modelAndView;
    }
   
  
    
    /**
     * Will initialize the Priortherapy drop down options
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
    
    //OBJECT METHODS
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

    @Override
    protected void validate(T command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        emptyFieldNameMap = new HashMap<String, Boolean>();
        
        // check PriorTherapies duplicates
        List list = command.getAssignment().getPriorTherapies();
        Set<String> set = new HashSet<String>();
        boolean hasDuplicatePT = false;
        boolean hasDuplicateAg = false;

        byte i = 0;
        for (Object object : list) {
            StudyParticipantPriorTherapy pt = (StudyParticipantPriorTherapy)object;

            // check Prior Therapy uniqueness
            if (pt != null) {
                StringBuffer ptUnique = new StringBuffer();
                ptUnique.append(pt.getName()).append(pt.getStartDate().getYear()).append(pt.getStartDate().getMonth());
                if (!set.add(ptUnique.toString())) {
                    hasDuplicatePT = true;
                    String propertyName = String.format("assignment.priorTherapies[%d].startDate", i);
                    errors.rejectValue(propertyName, "PTY_UK_ERR", "Two identical prior therapies cannot share the same starting month and year");
                }
                
            }

/*
            if (hasDuplicatePT) {
                String propertyName = String.format("assignment.priorTherapies[%d].startDate", i);
                errors.reject("PTY_UK_ERR", null, "Two identical prior therapies cannot share the same starting month and year");
                errors.rejectValue(propertyName, "", "Two identical prior therapies cannot share the same starting month and year");
            }
*/
            if (hasDuplicateAg) errors.reject("PTA_UK_ERR", null, "");

            
            // check PT dates
            String propertyName = String.format("assignment.priorTherapies[%d].endDate", i);
            if (!pt.getEndDate().isNull())
                if (pt.getStartDate().compareTo(pt.getEndDate()) > 0) {
                    errors.rejectValue(propertyName, "SAE_024", new Object[]{pt.getName()}, "The 'End date' can not be before the 'Start Date'");
                }

            i++;

            // all tge PT validation should go above these lines, because of teh continue keyword inside the next block
            // check the agents within the Prior Therapy objects
            List<StudyParticipantPriorTherapyAgent> agents = pt.getPriorTherapyAgents();
            if (agents == null || agents.size() < 2) continue;

            Set agentsSet = new HashSet();
            for (StudyParticipantPriorTherapyAgent agent : agents) {
                if (!agentsSet.add(agent.getChemoAgent().getName())) {
                    hasDuplicateAg = true;
                }
            }

        }

        // if (hasDuplicatePT) errors.reject("PTY_UK_ERR", null, "Two identical prior therapies cannot share the same starting month and year");
        if (hasDuplicateAg) errors.reject("PTA_UK_ERR", null, "");


        // check PreExistingConditions duplicates
        list = command.getAssignment().getPreExistingConditions();
        set = new HashSet();
        for (Object object : list) {
            StudyParticipantPreExistingCondition pt = (StudyParticipantPreExistingCondition)object;
            if (pt != null)
                if (!set.add(pt.getName())) errors.reject("PT_005", new Object[] {pt.getName()}, "Duplicate Preexisting Condition");
        }

        
        // check ConMeds duplicates
        list = command.getAssignment().getConcomitantMedications();
        set = new HashSet();
        i = 0;
        for (Object object : list) {
            StudyParticipantConcomitantMedication pt = (StudyParticipantConcomitantMedication)object;
            if (pt != null)
                if (!set.add(pt.getName())) errors.reject("PT_006", new Object[] {pt.getName()}, "Duplicate Concomitant Medication");

            String propertyName = String.format("assignment.concomitantMedications[%d].endDate", i);

            if (!pt.getEndDate().isNull())
                if (!pt.getStillTakingMedications() && pt.getStartDate().compareTo(pt.getEndDate()) > 0) {
                    errors.rejectValue(propertyName, "SAE_024", new Object[]{pt.getName()}, "The 'End date' can not be before the 'Start Date'");
                }

            i++;
        }



        // check MetaStaticDisease duplicates
        list = command.getAssignment().getDiseaseHistory().getMetastaticDiseaseSites();
        set = new HashSet();
        for (Object object : list) {
            StudyParticipantMetastaticDiseaseSite pt = (StudyParticipantMetastaticDiseaseSite)object;
            if (pt != null && pt.getCodedSite() != null)
                if (!set.add(pt.getCodedSite().getName())) errors.reject("PT_007", new Object[] {pt.getCodedSite().getName()}, "Duplicate Metastatic Disease Site Medication");
        }

        WebUtils.populateErrorFieldNames(emptyFieldNameMap, errors);
    }
}

