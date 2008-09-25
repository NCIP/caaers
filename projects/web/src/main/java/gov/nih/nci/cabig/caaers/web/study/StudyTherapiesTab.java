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
    public Map<String, InputFieldGroup> createFieldGroups(final Study command) {
        if (fieldGroup == null) {
            // set up the fields
            fieldGroup = new DefaultInputFieldGroup("studyTherapies");
            List<InputField> fields = fieldGroup.getFields();

            InputField drugAdministrationTherapyTypeField = InputFieldFactory.createCheckboxField(
                            "drugAdministrationTherapyType", "Agent");
            InputFieldAttributes.setSize(drugAdministrationTherapyTypeField, 50);
            fields.add(drugAdministrationTherapyTypeField);

            InputField deviceTherapyTypeField = InputFieldFactory.createCheckboxField(
                            "deviceTherapyType", "Device");
            InputFieldAttributes.setSize(deviceTherapyTypeField, 50);
            fields.add(deviceTherapyTypeField);

            InputField radiationTherapyTypeField = InputFieldFactory.createCheckboxField(
                            "radiationTherapyType", "Radiation");
            InputFieldAttributes.setSize(radiationTherapyTypeField, 50);
            fields.add(radiationTherapyTypeField);

            InputField surgeryTherapyTypeField = InputFieldFactory.createCheckboxField(
                            "surgeryTherapyType", "Surgery");
            InputFieldAttributes.setSize(surgeryTherapyTypeField, 50);
            fields.add(surgeryTherapyTypeField);

            InputField behavioralTypeField = InputFieldFactory.createCheckboxField(
                            "behavioralTherapyType", "Behavioral");
            fields.add(behavioralTypeField);
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(fieldGroup);
        return map;
    }

    @Override
    public void onBind(HttpServletRequest request, Study study, Errors errors) {
        super.onBind(request, study, errors);
        updateStudyTherapies(study);
    }

    protected void updateStudyTherapies(final Study study) {
        List<StudyTherapy> studyTherapies = study.getStudyTherapies();

        if (study.getDrugAdministrationTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION) == null) {
            StudyTherapy drugAdministrationTherapy = new StudyTherapy();
            drugAdministrationTherapy.setStudy(study);
            drugAdministrationTherapy.setStudyTherapyType(StudyTherapyType.DRUG_ADMINISTRATION);
            study.getStudyTherapies().add(drugAdministrationTherapy);
        } else if (!study.getDrugAdministrationTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION) != null) {
            studyTherapies.remove(study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION));
        }
        if (study.getDeviceTherapyType() && study.getStudyTherapy(StudyTherapyType.DEVICE) == null) {
            StudyTherapy deviceTherapy = new StudyTherapy();
            deviceTherapy.setStudy(study);
            deviceTherapy.setStudyTherapyType(StudyTherapyType.DEVICE);
            study.getStudyTherapies().add(deviceTherapy);
        } else if (!study.getDeviceTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.DEVICE) != null) {
            studyTherapies.remove(study.getStudyTherapy(StudyTherapyType.DEVICE));
        }
        if (study.getRadiationTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.RADIATION) == null) {
            StudyTherapy radiationTherapy = new StudyTherapy();
            radiationTherapy.setStudy(study);
            radiationTherapy.setStudyTherapyType(StudyTherapyType.RADIATION);
            study.getStudyTherapies().add(radiationTherapy);
        } else if (!study.getRadiationTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.RADIATION) != null) {
            studyTherapies.remove(study.getStudyTherapy(StudyTherapyType.RADIATION));
        }
        if (study.getSurgeryTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.SURGERY) == null) {
            StudyTherapy surgeryTherapy = new StudyTherapy();
            surgeryTherapy.setStudy(study);
            surgeryTherapy.setStudyTherapyType(StudyTherapyType.SURGERY);
            study.getStudyTherapies().add(surgeryTherapy);
        } else if (!study.getSurgeryTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.SURGERY) != null) {
            studyTherapies.remove(study.getStudyTherapy(StudyTherapyType.SURGERY));
        }
        if (study.getBehavioralTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.BEHAVIORAL) == null) {
            StudyTherapy behavioralTherapy = new StudyTherapy();
            behavioralTherapy.setStudy(study);
            behavioralTherapy.setStudyTherapyType(StudyTherapyType.BEHAVIORAL);
            study.getStudyTherapies().add(behavioralTherapy);
        } else if (!study.getBehavioralTherapyType()
                        && study.getStudyTherapy(StudyTherapyType.BEHAVIORAL) != null) {
            studyTherapies.remove(study.getStudyTherapy(StudyTherapyType.BEHAVIORAL));
        }
    }
    
    @Override
    public void beforeBind(HttpServletRequest request, Study command) {
    	// TODO Auto-generated method stub
    	super.beforeBind(request, command);
    }
    
    @Override
    public void onDisplay(HttpServletRequest request, Study command) {
    	// TODO Auto-generated method stub
    	super.onDisplay(request, command);
    }
    
    @Override
    public void postProcess(HttpServletRequest request, Study command,
    		Errors errors) {
    	// TODO Auto-generated method stub
    	super.postProcess(request, command, errors);
    }
    
    

}
