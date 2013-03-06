/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class AgentsTab extends StudyTab {


    private LinkedHashMap<Object, Object> indTypeMap = new LinkedHashMap<Object, Object>();
    private AgentSpecificAdverseEventListService agentSpecificAdverseEventListService;

    public AgentsTab() {
        super("Interventions", "Interventions", "study/study_agents");
        indTypeMap.put("", "Please select");
        indTypeMap.put(INDType.NA.name(), INDType.NA.getDisplayName());
        indTypeMap.put(INDType.IND_EXEMPT.name(), INDType.IND_EXEMPT.getDisplayName());
        indTypeMap.put(INDType.NA_COMMERCIAL.name(), INDType.NA_COMMERCIAL.getDisplayName());
        indTypeMap.put(INDType.OTHER.name(), INDType.OTHER.getDisplayName());

    }

    @Override
    public Map<String, Object> referenceData(StudyCommand command) {
        Map<String, Object> rd = super.referenceData(command);
        Map tt = new TreeMap<Integer, String>();
        for (StudyTherapyType stt : StudyTherapyType.values()) {
            if (stt.equals(StudyTherapyType.DRUG_ADMINISTRATION) || stt.equals(StudyTherapyType.DEVICE)) continue;
            tt.put(stt, EnumHelper.titleCasedName(stt));
        }
        rd.put("studyTherapyTypes", tt);
        //Initialize expectedAECtcTerms. If this is not done, there is a lazy error while deleting study agents
        command.getStudy().getExpectedAECtcTerms().size();
        command.getStudy().getExpectedAEMeddraLowLevelTerms().size();
        for(TreatmentAssignment ta: command.getStudy().getTreatmentAssignments()){
        	ta.getTreatmentAssignmentStudyInterventions().size();
        	for(AbstractStudyInterventionExpectedAE as : ta.getAbstractStudyInterventionExpectedAEs()){
				as.getTreatmentAssignmentAgents().size();
			}
        }
        //initialize the study agent INDs also
        for(StudyAgent sa : command.getStudy().getStudyAgents()){
        	if(sa.getAgent()!=null){
        		sa.getAgent().getAgentSpecificTerms().size();
        		sa.getTreatmentAssignmentAgents().size();
                sa.getStudyAgentINDAssociations().size();
                for(StudyAgentINDAssociation saa : sa.getStudyAgentINDAssociations()){
                    saa.getInvestigationalNewDrug();
                }
        	}
        }

        return rd; 
    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
    	
        for(StudyAgent studyAgent : command.getStudy().getStudyAgents()){
        	//IND should be initialized for CTEP and DCP.
        	//IND should be cleared for NA
        	if(studyAgent.getIndType() != null){
        		switch (studyAgent.getIndType()){

        		case OTHER:
        			break; //leave it.
        		default:
        			studyAgent.getStudyAgentINDAssociations().clear();
            	}
        	}
        }

        command.synchronizeStudyWithAgentAEList(agentSpecificAdverseEventListService, command.getStudy(), false);
        request.setAttribute("tabFlashMessage", messageSource.getMessage(String.format("MSG_study.%s.flash_message", this.getClass().getSimpleName()), null, Locale.getDefault()));
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
        
        boolean isDeviceEmpty = false;
        
        List<StudyDevice> devices = command.getStudy().getStudyDevices();
        for ( int i = 0 ; i < command.getStudy().getStudyDevices().size(); i++ ) {
        	StudyDevice device = devices.get(i);
        		
        	if ( device.getDevice() == null && device.getOtherCommonName() == null && device.getOtherBrandName() == null ) {
        		isDeviceEmpty = true;
        		errors.rejectValue("study.studyDevices[" + i + "].device", "STU_025","Select either Device or Other ");
        		continue;
        	}
        }
        
        if ( isDeviceEmpty) {
        	errors.rejectValue("study.studyDevices", "STU_024", "One or more Devices are missing or not in list");
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

    /**
     * Creates the IND lookup field.
     * */
    private InputFieldGroup createINDFieldGroup(StudyCommand command, int studyAgentIndex){
    	//based on the fact that UI only supports one IND field for an agent, by default one field will be created.
    	 InputFieldGroup indFieldGroup = new DefaultInputFieldGroup("ind" + studyAgentIndex);
    	 InputField indField = InputFieldFactory.createAutocompleterField("study.studyAgents[" + studyAgentIndex + "].studyAgentINDAssociations[" + 0 + "].investigationalNewDrug", "IND #", false);
         indField.getAttributes().put(InputField.ENABLE_CLEAR, true);
         InputFieldAttributes.setSize(indField, 41);
         indFieldGroup.getFields().add(indField);
         return indFieldGroup;
    }
    
    /**
     * Creates the IND lookup field.
     * */
    private InputFieldGroup createStudyDeviceINDFieldGroup(StudyCommand command, int studyDeviceIndex){
    	//based on the fact that UI only supports one IND field for a device, by default one field will be created.
    	 InputFieldGroup indFieldGroup = new DefaultInputFieldGroup("ind" + studyDeviceIndex);
    	 InputField indField = InputFieldFactory.createAutocompleterField("study.studyDevices[" + studyDeviceIndex + "].studyDeviceINDAssociations[" + 0 + "].investigationalNewDrug", "IDE #", false);
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
        	//delete/retire study agent from study
        	StudyAgent sa = command.deleteStudyAgentAtIndex(index);
        	//delete agent specific ae from tac
        	//sa.syncTreatmentAssignmentAgentSpecificTerm(AgentSpecificTerm.EXPTECTED_AE_DELETED);
        	//remove tac associations from study
        	//delete agent specific ae from study
        	agentSpecificAdverseEventListService.synchronizeStudyWithAgent(sa, AgentSpecificTerm.EXPTECTED_AE_DELETED);
            //command.synchronizeStudyWithAgentAEList(agentSpecificAdverseEventListService, command.getStudy(), sa, true);
        	sa.removeTreatmentAssignmentAgents();
        }
        
        size = study.getStudyAgents().size(); //new size
    	Integer[] indexes = new Integer[size];
        for(int i = 0 ; i < size ; i++){
            indexes[i] = size - (i + 1);
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
    
    
    /**
     * This method will add IND to the study device.
     * @param request
     * @param object
     * @param errors
     * @return
     */
    public ModelAndView addStudyDeviceIND(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        Integer studyDeviceIndex = WebUtils.getIntParameter(request, "index");
        InputFieldGroupMap map = new InputFieldGroupMap();
        ModelAndView modelAndView = new ModelAndView("study/ajax/studyAgentINDSection");
        modelAndView.getModel().put("indfields", createStudyDeviceINDFieldGroup(command, studyDeviceIndex));
        modelAndView.getModel().put("index", studyDeviceIndex);

        return modelAndView;
    }

    public AgentSpecificAdverseEventListService getAgentSpecificAdverseEventListService() {
        return agentSpecificAdverseEventListService;
    }

    public void setAgentSpecificAdverseEventListService(AgentSpecificAdverseEventListService agentSpecificAdverseEventListService) {
        this.agentSpecificAdverseEventListService = agentSpecificAdverseEventListService;
    }

    /**
     * Add an item to the collection through AJAX
     * */
    public ModelAndView addStudyDevice(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        List<StudyDevice> studyDevices = command.getStudy().getStudyDevices();

        // add new StudyDevice
        StudyDevice sd = new StudyDevice();
        command.getStudy().addStudyDevice(sd);
        //

        ModelAndView modelAndView = new ModelAndView("study/ajax/studyDeviceSection");
        modelAndView.getModel().put("studyDevices", studyDevices);
        modelAndView.getModel().put("indexes", new Integer[]{studyDevices.size() - 1});
        return modelAndView;
    }

    /**
     * Add an item to the collection through AJAX
     * */
    public ModelAndView addOtherIntervention(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        List<OtherIntervention> list = command.getStudy().getOtherInterventions();

        // add 
        OtherIntervention sd = new OtherIntervention();
        command.getStudy().addOtherIntervention(sd);
        //

        ModelAndView modelAndView = new ModelAndView("study/ajax/studyOtherInterventionSection");
        modelAndView.getModel().put("otherInterventions", list);
        modelAndView.getModel().put("indexes", new Integer[]{list.size() - 1});
        return modelAndView;
    }

    /**
     * Remove an item from the collection through AJAX
     * */
    public ModelAndView removeOtherIntervention(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        List<OtherIntervention> list = command.getStudy().getOtherInterventions();

        // remove
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <otherInterventions> list: " + e.getMessage());
        }

        if (list.size() - 1 < index) {
            log.debug("Wrong <index> for <otherInterventions> list.");
        } else if (index >=0) {
            OtherIntervention o = (OtherIntervention)list.get(index);
            o.retire();
        }
        //

        int size = list.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}

        ModelAndView modelAndView = new ModelAndView("study/ajax/studyOtherInterventionSection");
        modelAndView.getModel().put("otherInterventions", list);
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }

    /**
     * Remove an item from the collection through AJAX
     * */
    public ModelAndView removeStudyDevice(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        List<StudyDevice> list = command.getStudy().getStudyDevices();

        // remove
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <StudyDevice> list: " + e.getMessage());
        }

        if (list.size() - 1 < index) {
            log.debug("Wrong <index> for <StudyDevice> list.");
        } else if (index >=0) {
            StudyDevice o = (StudyDevice)list.get(index);
            if ( o.isOtherDevice() == false && o.getDevice() == null ) {   // Remove Device if the device information is empty. 
            	list.remove(index);
            } else {
            	o.retire();
            }
        }
        //

        int size = list.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}

        ModelAndView modelAndView = new ModelAndView("study/ajax/studyDeviceSection");
        modelAndView.getModel().put("studyDevices", list);
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }
}
