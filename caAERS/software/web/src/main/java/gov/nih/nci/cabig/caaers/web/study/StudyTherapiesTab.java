package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.validation.Errors;

/**
 * @author Saurabh Agrawal
 */
public class StudyTherapiesTab extends StudyTab {

    private InputFieldGroup fieldGroup;

    public StudyTherapiesTab() {
        super("Therapies", "Therapies", "study/study_therapies");
        setAutoPopulateHelpKey(true);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        if (fieldGroup == null) {
            // set up the fields
            fieldGroup = new DefaultInputFieldGroup("studyTherapies");
            List<InputField> fields = fieldGroup.getFields();

            InputField drugAdministrationTherapyTypeField = InputFieldFactory.createCheckboxField("study.drugAdministrationTherapyType", "Agent");
            InputFieldAttributes.setSize(drugAdministrationTherapyTypeField, 50);
            fields.add(drugAdministrationTherapyTypeField);

            InputField deviceTherapyTypeField = InputFieldFactory.createCheckboxField("study.deviceTherapyType", "Device");
            InputFieldAttributes.setSize(deviceTherapyTypeField, 50);
            fields.add(deviceTherapyTypeField);

            InputField radiationTherapyTypeField = InputFieldFactory.createCheckboxField("study.radiationTherapyType", "Radiation");
            InputFieldAttributes.setSize(radiationTherapyTypeField, 50);
            fields.add(radiationTherapyTypeField);

            InputField surgeryTherapyTypeField = InputFieldFactory.createCheckboxField("study.surgeryTherapyType", "Surgery");
            InputFieldAttributes.setSize(surgeryTherapyTypeField, 50);
            fields.add(surgeryTherapyTypeField);

            InputField behavioralTherapyTypeField = InputFieldFactory.createCheckboxField("study.behavioralTherapyType", "Behavioral");
            InputFieldAttributes.setSize(behavioralTherapyTypeField, 50);
            fields.add(behavioralTherapyTypeField);
            
            InputField biologicalTherapyTypeField = InputFieldFactory.createCheckboxField("study.biologicalTherapyType", "Biological-Vaccine");
            InputFieldAttributes.setSize(biologicalTherapyTypeField, 50);
            fields.add(biologicalTherapyTypeField);
            
            InputField geneticTherapyTypeField = InputFieldFactory.createCheckboxField("study.geneticTherapyType", "Genetic");
            InputFieldAttributes.setSize(geneticTherapyTypeField, 50);
            fields.add(geneticTherapyTypeField);
            
            InputField diaterySupplementTherapyTypeField = InputFieldFactory.createCheckboxField("study.diaterySupplementTherapyType", "Dietary Supplement");
            InputFieldAttributes.setSize(diaterySupplementTherapyTypeField, 50);
            fields.add(diaterySupplementTherapyTypeField);
            
            InputField otherTherapyTypeField = InputFieldFactory.createCheckboxField("study.otherTherapyType", "Other");
            InputFieldAttributes.setSize(otherTherapyTypeField, 50);
            fields.add(otherTherapyTypeField);
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(fieldGroup);
        return map;
    }

    @Override
    public void onBind(HttpServletRequest request, StudyCommand cmd, Errors errors) {
        super.onBind(request, cmd, errors);
        updateStudyTherapies(cmd);
    }

    protected void updateStudyTherapies(final StudyCommand cmd) {
    	for(StudyTherapyType therapyType : StudyTherapyType.values()){
    		if(cmd.isTherapyTypeSelected(therapyType)){
    			StudyTherapy therapy = cmd.getStudy().getStudyTherapy(therapyType);
    			if(therapy == null) cmd.getStudy().addStudyTherapy(therapyType);
    		}else{
    			cmd.getStudy().removeTherapiesOfType(therapyType);
    		}
    	}
    }
    
}
