package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.admin.ResearchStaffCommand;
import gov.nih.nci.cabig.caaers.web.ae.ExpeditedAdverseEventInputCommand;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.math.NumberUtils;

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
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeTreatmentAssignment".equals(action)) {
        	command.deleteTreatmentAssignmentAtIndex(Integer.parseInt(selected));
        }
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

            InputField descriptionField = InputFieldFactory.createTextArea("description", "Description", true);

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

        List<TreatmentAssignment> treatmentAssignments = command.getStudy().getTreatmentAssignments();

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
            // System.out.println("index to be deleted=" + index);
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <treatment assignment> list: " + e.getMessage());
        }

        if (tas.size() - 1 < index) {
            log.debug("Wrong <index> for <treatment assignmen> list.");
        } else if (index >=0) {
//            TreatmentAssignment ta = (TreatmentAssignment)tas.get(index);
            tas.remove(index);
        }

        //System.out.println("Active TAs after deletion:" + command.getStudy().getTreatmentAssignments().size());

        int size = tas.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
//    		indexes[i] = i;
    	}

        ModelAndView modelAndView = new ModelAndView("study/ajax/treatmentAssignmentSection");
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }

}
