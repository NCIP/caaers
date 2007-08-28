package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class StudyTherapiesTab extends StudyTab {

	private InputFieldGroup fieldGroup;

	public StudyTherapiesTab() {
		super("Study Identifiers", "Therapies", "study/study_therapies");
		setAutoPopulateHelpKey(true);
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(final Study command) {
		if (fieldGroup == null) {
			// set up the fields
			fieldGroup = new DefaultInputFieldGroup("studyTherapies");
			List<InputField> fields = fieldGroup.getFields();

			InputField drugAdministrationTherapyTypeField = InputFieldFactory.createCheckboxField(
					"drugAdministrationTherapyType", "Drug Administration");
			InputFieldAttributes.setSize(drugAdministrationTherapyTypeField, 50);
			fields.add(drugAdministrationTherapyTypeField);

			InputField deviceTherapyTypeField = InputFieldFactory.createCheckboxField("deviceTherapyType", "Device");
			InputFieldAttributes.setSize(deviceTherapyTypeField, 50);
			fields.add(deviceTherapyTypeField);

			InputField radiationTherapyTypeField = InputFieldFactory.createCheckboxField("radiationTherapyType",
					"Radiation");
			InputFieldAttributes.setSize(radiationTherapyTypeField, 50);
			fields.add(radiationTherapyTypeField);

			InputField surgeryTherapyTypeField = InputFieldFactory.createCheckboxField("surgeryTherapyType", "Surgery");
			InputFieldAttributes.setSize(surgeryTherapyTypeField, 50);
			fields.add(surgeryTherapyTypeField);
		}
		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addInputFieldGroup(fieldGroup);
		return map;
	}

}
