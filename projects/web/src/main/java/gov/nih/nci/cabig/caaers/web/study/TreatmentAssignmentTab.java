package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Saurabh Agrawal
 */
public class TreatmentAssignmentTab extends StudyTab {

    private RepeatingFieldGroupFactory rfgFactory;

    public TreatmentAssignmentTab() {
        super("Treatment Assignments", "Treatment Assignments", "study/treatment_assignments");
        setAutoPopulateHelpKey(true);
    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeTreatmentAssignment".equals(action)) {
            Study study = command.getStudy();
            study.getTreatmentAssignments().remove(Integer.parseInt(selected));
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        if (rfgFactory == null) {
            rfgFactory = new RepeatingFieldGroupFactory("main", "study.treatmentAssignments");
            InputField codeField = InputFieldFactory.createTextField("code", "Code", true);
            InputFieldAttributes.setSize(codeField, 20);
            rfgFactory.addField(codeField);

            InputField doseLevelOrderField = InputFieldFactory.createTextField("doseLevelOrder", "Dose level order", false);
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
                errors.rejectValue("treatmentAssignments[" + j + "].doseLevelOrder", "REQUIRED", "Does level order should be a two digit number only (less than 100)..!");
            }
        }

    }
}
