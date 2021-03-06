/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyIntervention;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Saurabh Agrawal
 * @author Ion Olaru
 */
public class TreatmentAssignmentTab extends StudyTab {

    private RepeatingFieldGroupFactory rfgFactory;
    Map<String, String> methodNameMap = new HashMap<String, String>();

    public TreatmentAssignmentTab() {
        super("Treatment Assignments", "Treatment Assignments", "study/treatment_assignments");
        setAutoPopulateHelpKey(true);
        methodNameMap.put("addTA", "addTreatmentAssignment");
        methodNameMap.put("removeTA", "removeTreatmentAssignment");
    }

    @Override
    public Map<String, Object> referenceData(StudyCommand command) {
        Map<String, Object> rd = super.referenceData(command);
        command.getAllActiveInterventions(); //this is to initialize the study agents.
        command.populateTreatmentAssignmentSelectedStudyInterventionIds();
        return rd;
    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeTreatmentAssignment".equals(action)) {
        	command.deleteTreatmentAssignmentAtIndex(Integer.parseInt(selected));
        }
        command.synchronizeTreatmentAssignmentSelectedInterventions();
        request.setAttribute("tabFlashMessage", messageSource.getMessage(String.format("MSG_study.%s.flash_message", this.getClass().getSimpleName()), null, Locale.getDefault()));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        if (rfgFactory == null) {
            rfgFactory = new RepeatingFieldGroupFactory("main", "study.treatmentAssignments");
            InputField codeField = InputFieldFactory.createTextField("code", "Code", true);
            InputFieldAttributes.setSize(codeField, 20);
            rfgFactory.addField(codeField);

            InputField doseLevelOrderField = InputFieldFactory.createTextField("doseLevelOrder", "Dose level order", FieldValidator.NUMBER_VALIDATOR);
            InputFieldAttributes.setSize(doseLevelOrderField, 20);
            rfgFactory.addField(doseLevelOrderField);

            InputField descriptionField = InputFieldFactory.createTextArea("description", "Description", false);

            InputFieldAttributes.setColumns(descriptionField, 80);
            rfgFactory.addField(descriptionField);

            InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
            InputFieldAttributes.setColumns(commentsField, 80);
            rfgFactory.addField(commentsField);

        }
        Study study = command.getStudy();
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addRepeatingFieldGroupFactory(rfgFactory, study.getTreatmentAssignments().size());

        return map;
    }

    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);

        List<TreatmentAssignment> treatmentAssignments = command.getStudy().getActiveTreatmentAssignments();

        for (int j = 0; j < treatmentAssignments.size(); j++) {
            TreatmentAssignment treatmentAssignment = treatmentAssignments.get(j);
            if (treatmentAssignment.getDoseLevelOrder() != null && treatmentAssignment.getDoseLevelOrder().toString().length() > 2) {
                errors.rejectValue("study.treatmentAssignments[" + j + "].doseLevelOrder", "STU_018", "Does level order should be a two digit number only (less than 100)..!");
            }
        }

    }

    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return methodNameMap.get(task + currentItem);
    }

    //
    public ModelAndView addTreatmentAssignment(HttpServletRequest request, Object object, Errors errors) {
        StudyCommand command = (StudyCommand)object;
        TreatmentAssignment ta = new TreatmentAssignment();
        command.getStudy().addTreatmentAssignment(ta);
        ModelAndView modelAndView = new ModelAndView("study/ajax/treatmentAssignmentSection");
        modelAndView.getModel().put("indexes", new Integer[]{command.getStudy().getTreatmentAssignments().size() - 1});
        return modelAndView;
    }
    
    //
    public ModelAndView removeTreatmentAssignment(HttpServletRequest request, Object object, Errors errors) {

        StudyCommand command = (StudyCommand)object;
        List<TreatmentAssignment> tas = command.getStudy().getTreatmentAssignments();

        //System.out.println("Active TAs before deletion:" + command.getStudy().getTreatmentAssignments().size());

        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <treatment assignment> list: " + e.getMessage());
        }

        if (tas.size() - 1 < index) {
            log.debug("Wrong <index> for <treatment assignmen> list.");
        } else if (index >=0) {
            TreatmentAssignment ta = tas.get(index);
            //CAAERS-5584
            if(ta.getCode() == null || ta.getDescription() == null){
                ta.setCode("null");
                ta.setDescription("null");
            }
            ta.retire();
        }

        int size = tas.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}

        ModelAndView modelAndView = new ModelAndView("study/ajax/treatmentAssignmentSection");
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }

}
