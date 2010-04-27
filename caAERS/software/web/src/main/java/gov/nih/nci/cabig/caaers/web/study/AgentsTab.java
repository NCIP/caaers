package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class AgentsTab extends StudyTab {
    @Deprecated
    public static int IND_TYPE_NOT_USED = 0;

    @Deprecated
    public static int IND_TYPE_CTEP = 1;

    @Deprecated
    public static int IND_TYPE_OTHER = 2;

    @Deprecated
    public static int IND_TYPE_NA_COMMERCIAL_AGENT = 3;

    @Deprecated
    public static int IND_TYPE_EXEMPT = 4;

    @Deprecated
    public static int IND_TYPE_DCP_IND = 5;

    @Deprecated
    public static int CTEP_IND = -111;

    private LinkedHashMap<Object, Object> indTypeMap = new LinkedHashMap<Object, Object>();
    private AgentSpecificAdverseEventListService agentSpecificAdverseEventListService;

    public AgentsTab() {
        super("Agents", "Agents", "study/study_agents");
        // setAutoPopulateHelpKey(true);
        indTypeMap.put("", "Please select");
        for (INDType indType : INDType.values()) {
            indTypeMap.put(indType.name(), indType.getDisplayName());
        }

    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
    	
        for(StudyAgent studyAgent : command.getStudy().getStudyAgents()){
        	//IND should be initialized for CTEP and DCP.
        	//IND should be cleared for NA
        	if(studyAgent.getIndType() != null){
        		switch (studyAgent.getIndType()){
        		case CTEP_IND://associated with default CTEP IND
        			studyAgent.getStudyAgentINDAssociations().get(0).setInvestigationalNewDrug(command.fetchDefaultInvestigationalNewDrugForCTEP());
        			break;
        		case DCP_IND://associate with default DCP IND
        			studyAgent.getStudyAgentINDAssociations().get(0).setInvestigationalNewDrug(command.fetchDefaultInvestigationalNewDrugForDCP());
        			break;
        		case OTHER:
        			break; //leave it.
        		default:
        			studyAgent.getStudyAgentINDAssociations().clear();
            	}
        	}
        }

        command.synchronizeStudyWithAgentAEList(agentSpecificAdverseEventListService, command.getStudy(), false);
    }

    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        boolean isAgentEmpty = false;
        StudyAgent studyAgent = null;
        List<StudyAgent> studyAgents = command.getStudy().getStudyAgents();

        for (int i = 0; i < command.getStudy().getStudyAgents().size(); i++) {
            studyAgent = studyAgents.get(i);
            
            if(!studyAgent.isRetired()){
                if (studyAgent.getAgent() == null && studyAgent.getOtherAgent() == null) {
                    isAgentEmpty = true;
                    errors.rejectValue("study.studyAgents[" + i + "].otherAgent", "STU_007","Select either Agent or Other ");
                    continue;
                }

                if (studyAgent.getAgent() != null) {
                    studyAgent.setOtherAgent(null);
                }
            }
        }

        if (isAgentEmpty) {
            errors.rejectValue("study.studyAgents", "STU_008", "One or more Agents are missing or not in list");
        }

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        String baseName = "study.studyAgents";
        int i = -1;

        for (StudyAgent sa : command.getStudy().getStudyAgents()) {
            i++;
            InputFieldGroup fieldGrp = new DefaultInputFieldGroup("main" + i);
            
            List<InputField> fields = fieldGrp.getFields();
            InputField agentField = InputFieldFactory.createAutocompleterField(baseName + "[" + i + "].agent", "Agent", false);
            InputFieldAttributes.setSize(agentField, 70);
            agentField.getAttributes().put(InputField.ENABLE_CLEAR, false);
            fields.add(agentField);

            InputField otherAgentField = InputFieldFactory.createTextField(baseName + "[" + i + "].otherAgent", "Other", false);
            InputFieldAttributes.setSize(otherAgentField, 70);
            fields.add(otherAgentField);

            InputField indTypeField = InputFieldFactory.createSelectField(baseName + "[" + i + "].indType", "Enter IND information", indTypeMap);
            fields.add(indTypeField);
            

            InputField partOfLeadIND = InputFieldFactory.createBooleanSelectField(baseName + "[" + i + "].partOfLeadIND", "Lead IND ?");
            fields.add(partOfLeadIND);
            map.addInputFieldGroup(fieldGrp);
            
            //create the IND field group if necessary
            if(sa.getIndType() != null && sa.getIndType().equals(INDType.OTHER)){
            	map.addInputFieldGroup(createINDFieldGroup(command, i));
            }
            
            
        }
        return map;
    }
    
    //creates the IND lookup field. 
    private InputFieldGroup createINDFieldGroup(StudyCommand command, int studyAgentIndex){
    	//based on the fact that UI only supports one IND field for an agent, by default one field will be created.
    	 InputFieldGroup indFieldGroup = new DefaultInputFieldGroup("ind" + studyAgentIndex);
    	 InputField indField = InputFieldFactory.createAutocompleterField("study.studyAgents[" + studyAgentIndex + "].studyAgentINDAssociations[" + 0 + "].investigationalNewDrug", "IND #", false);
         indField.getAttributes().put(InputField.ENABLE_CLEAR, true);
         InputFieldAttributes.setSize(indField, 41);
         indFieldGroup.getFields().add(indField);
         return indFieldGroup;
    }
    
    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return task + currentItem;
    }
    
    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }
    
    /**
     * This method will add a new study agent, and return the view consisting the details of the new agent. 
     * @param request
     * @param object
     * @param errors
     * @return
     */
    public ModelAndView addStudyAgent(HttpServletRequest request, Object object, Errors errors) {
    	
        StudyCommand command = (StudyCommand)object;
        Study study = command.getStudy();
        
        int currentSize = study.getStudyAgents().size();
        
        StudyAgent studyAgent = new StudyAgent();
        study.addStudyAgent(studyAgent);
        
        ModelAndView modelAndView = new ModelAndView("study/ajax/studyAgentSection");
        modelAndView.getModel().put("indexes", new Integer[]{currentSize});
        modelAndView.getModel().put("addAgentFlow", true);
        return modelAndView;
        
    }
    
    /**
     * This method will remove a study agent, and return the view consisting of rest of the study agents
     * @param request
     * @param object
     * @param errors
     * @return
     */
    public ModelAndView removeStudyAgent(HttpServletRequest request, Object object, Errors errors) {

        StudyCommand command = (StudyCommand)object;
        Study study = command.getStudy();
        
        int size = study.getStudyAgents().size();
        int index;
 
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Unable to delete study agents, INVALID INDEX ", e);
        }
        
        //check if deletion is possible
        if(index >= size || index < 0){
        	log.debug("Unable to delete study agents, INVALID INDEX :"  + index);
        }else{
        	StudyAgent sa = command.deleteStudyAgentAtIndex(index);
            // delete
            // System.out.println("Synchronizing(DELETE) for: " + sa.getAgent().getName());
            command.synchronizeStudyWithAgentAEList(agentSpecificAdverseEventListService, command.getStudy(), sa, true);
        }
        
        size = study.getStudyAgents().size(); //new size
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = i;
    	}

        ModelAndView modelAndView = new ModelAndView("study/ajax/studyAgentSection");
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
        
    }
    
    /**
     * This method will add IND to the study agent. 
     * @param request
     * @param object
     * @param errors
     * @return
     */
    public ModelAndView addStudyAgentIND(HttpServletRequest request, Object object, Errors errors) {

        StudyCommand command = (StudyCommand)object;
        Integer studyAgentIndex = WebUtils.getIntParameter(request, "index");
        InputFieldGroupMap map = new InputFieldGroupMap();
        
        ModelAndView modelAndView = new ModelAndView("study/ajax/studyAgentINDSection");
        modelAndView.getModel().put("indfields", createINDFieldGroup(command, studyAgentIndex));
        modelAndView.getModel().put("index", studyAgentIndex);
        
        return modelAndView;
    }

    public AgentSpecificAdverseEventListService getAgentSpecificAdverseEventListService() {
        return agentSpecificAdverseEventListService;
    }

    public void setAgentSpecificAdverseEventListService(AgentSpecificAdverseEventListService agentSpecificAdverseEventListService) {
        this.agentSpecificAdverseEventListService = agentSpecificAdverseEventListService;
    }
}
