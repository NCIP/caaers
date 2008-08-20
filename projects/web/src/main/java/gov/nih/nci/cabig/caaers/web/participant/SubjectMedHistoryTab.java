package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapyAgent;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

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
	
	private int[] agentsPossiblePriorTherapies = {3,4,5,7,8,11};
	
	
    private static final Log log = LogFactory.getLog(SubjectMedHistoryTab.class);
    Map<String, String> methodNameMap = new HashMap<String, String>();

	
    private PriorTherapyDao priorTherapyDao;
    
    //static options of dropdowns are cached at Tab level. 
    Map<Object,Object> priorTherapyOptions;
    
	public SubjectMedHistoryTab() {
        super("Subject Medical History", "Subject Medical History", "par/par_subject_med_history");
        methodNameMap.put("create" + GENERAL, "createGeneral");
        methodNameMap.put("edit" + GENERAL, "createGeneral");
        methodNameMap.put("save" + GENERAL, "createGeneral");
        
        methodNameMap.put("create" + PRIOR_THERAPY,"createPriorTherapy");
        methodNameMap.put("edit" + PRIOR_THERAPY,"editPriorTherapy");
        methodNameMap.put("save" + PRIOR_THERAPY,"savePriorTherapy");
        
        methodNameMap.put("create" + PRIOR_THERAPY_AGENT,"createPriorTherapyAgent");
        
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(T command) {
    	InputFieldGroupMap map = new  InputFieldGroupMap();

    	//selectively add the fields
    	String currentItem = command.getCurrentItem();
    	if(StringUtils.isEmpty(currentItem) || StringUtils.equals(currentItem, GENERAL)) initializeGeneralFieldGroup(command, map);
    	if(StringUtils.isEmpty(currentItem) || StringUtils.equals(currentItem, PRIOR_THERAPY)) initializePriorTherapyFieldGroup(command, map);
    	if(StringUtils.isEmpty(currentItem) || StringUtils.equals(currentItem, PRIOR_THERAPY_AGENT)) initializePriorTherapyFieldGroup(command, map);
    	
    	return map;
    }
    
    private void initializeGeneralFieldGroup(T command, InputFieldGroupMap map){
    	InputFieldGroup generalFieldGroup = new DefaultInputFieldGroup();
    	List<InputField> fields = generalFieldGroup.getFields();
    	map.put(GENERAL, generalFieldGroup);
    }
    
  
    /**
     * This method will create the field groups for the prior therapy screen.
     */
    private void initializePriorTherapyFieldGroup(T command, InputFieldGroupMap map){
    	RepeatingFieldGroupFactory rfgFactory = new RepeatingFieldGroupFactory(PRIOR_THERAPY, "priorTherapies");
    	
        InputField priorTherapyField = InputFieldFactory.createSelectField("priorTherapy", "Prior therapy", true, fetchPriorTherapyOptions());
        InputField otherField = InputFieldFactory.createTextArea("other", "Comments", false);
        InputFieldAttributes.setColumns(otherField, 65);
        InputField startDateField = InputFieldFactory.createSplitDateField("startDate", "Therapy start Date", false, true, true, false);
        InputField endDateField = InputFieldFactory.createSplitDateField("endDate", "Therapy end date", false, true, true, false);
        rfgFactory.addField(priorTherapyField);
        rfgFactory.addField(otherField);
        rfgFactory.addField(startDateField);
        rfgFactory.addField(endDateField);
        
        int i = 0; 
        for(StudyParticipantPriorTherapy pt : command.getPriorTherapies()){
            
            //set up the agents
            RepeatingFieldGroupFactory rfgAgentFactory = new RepeatingFieldGroupFactory(PRIOR_THERAPY_AGENT, "priorTherapies" + "[" + i +"].priorTherapyAgents"  );
            InputField agentField = InputFieldFactory.createAutocompleterField("chemoAgent", "Agent", true);
            rfgAgentFactory.addField(agentField);
            map.addRepeatingFieldGroupFactory(rfgAgentFactory, pt.getPriorTherapyAgents().size());
            i++;
        }
        
        
        map.addRepeatingFieldGroupFactory(rfgFactory, command.getAssignment().getPriorTherapies().size());
    }
    
    
    //----- Create/Edit/Save/Delete operations (tasks) ----------------- 
    
    public ModelAndView createPriorTherapy(HttpServletRequest request , Object cmd, Errors errors) {
    	T command =(T)cmd;

    	ModelAndView mv = new ModelAndView("par/ajax/priorTherapyFormSection");
    	mv.getModel().put("index",command.getPriorTherapies().size());
    	command.setIndex(command.getPriorTherapies().size());
    	mv.getModel().put("agentIndex", 0);
    	mv.getModel().put("agentCount", -1);
    	
    	//create an empty therapy and add it
    	StudyParticipantPriorTherapy priorTherapy = new StudyParticipantPriorTherapy();
    	priorTherapy.setStartDate(new DateValue());
    	priorTherapy.setEndDate(new DateValue());
//    	priorTherapy.setPriorTherapyAgents(new ArrayList<StudyParticipantPriorTherapyAgent>());
    	priorTherapy.setAssignment(command.getAssignment());
    	
    	command.getPriorTherapies().add(priorTherapy);

    	return mv;
    	
	}
    
    public ModelAndView savePriorTherapy(HttpServletRequest request , Object cmd, Errors errors) {
 		T command =(T)cmd;

	 	String view = (errors.hasErrors()) ? "par/ajax/priorTherapyFormSection" : "par/ajax/savedSucessfully";
	 	
	 	ModelAndView mv = new ModelAndView(view);
	 	mv.getModel().put("index",command.getIndex());
    	return mv;
    	
	}
    
    public ModelAndView createPriorTherapyAgent(HttpServletRequest request , Object cmd, Errors errors) {
 		T command =(T)cmd;
 		
    	StudyParticipantPriorTherapy priorTherapy = command.getPriorTherapies().get(command.getIndex());
    	StudyParticipantPriorTherapyAgent priorTherapyAgent = new StudyParticipantPriorTherapyAgent();
    	int size = priorTherapy.getPriorTherapyAgents().size();
    	ModelAndView mv = new ModelAndView("par/ajax/priorTherapyAgentFormSection");
    	mv.getModel().put("index",command.getIndex());
    	mv.getModel().put("agentIndex", size);
    	mv.getModel().put("agentCount", size + 1);
    	
    	priorTherapy.addPriorTherapyAgent(priorTherapyAgent);
    	
    	return mv;
    }
    
    /**
     * Will initialize the Priortherapy drop down options
     * @return
     */
    private Map<Object, Object> fetchPriorTherapyOptions() {
    	if(priorTherapyOptions == null){
    		this.priorTherapyOptions = new LinkedHashMap<Object, Object>();
    		List<PriorTherapy> therapies = priorTherapyDao.getAll();
    		priorTherapyOptions.put("", "Please select");
    		if(therapies != null){
        		for(PriorTherapy therapy : therapies ){
        			priorTherapyOptions.put(therapy.getId(), therapy.getText());
        		}
        	}
    	}
        return priorTherapyOptions;
    }
    
    @Override
    protected void validate(T command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,	Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    }
    
    //TAB methods
    @Override
    public void onDisplay(HttpServletRequest request, T command) {
    	//for non-ajax request, refresh the index fixed lists.
    	if(!StringUtils.equalsIgnoreCase(request.getParameter(getAjaxRequestParamName()), "true")){
//    		command.refreshIndexFixedLists();
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
    	return WebUtils.hasSubmitParameter(request, "currentItem") && WebUtils.hasSubmitParameter(request, "task");
    }
    
    //OBJECT METHODS
    public PriorTherapyDao getPriorTherapyDao() {
		return priorTherapyDao;
	}
    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}

}

