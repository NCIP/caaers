package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
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

public class StudyAmendmentTab extends StudyTab {

	private RepeatingFieldGroupFactory rfgFactory;

	public StudyAmendmentTab() {
		super("Amendment details", "Amendments", "study/study_amendments");
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		 if(rfgFactory == null){
			 rfgFactory = new RepeatingFieldGroupFactory("main", "studyAmendments");
			 InputField amVersionField = InputFieldFactory.createTextField("amendmentVersion", "Amendment version");
			 InputFieldAttributes.setSize(amVersionField, 5);
			 rfgFactory.addField(amVersionField);
			 InputField amDateField = InputFieldFactory.createDateField("amendmentDate", "Amendment date");
			 rfgFactory.addField(amDateField);
			 InputField irbDateField = InputFieldFactory.createDateField("irbApprovalDate", "IRB approval date", true);
			 rfgFactory.addField(irbDateField);
			 InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments");
			 InputFieldAttributes.setColumns(commentsField, 40);
			 InputFieldAttributes.setRows(commentsField, 1);
			 rfgFactory.addField(commentsField);
		 }
		 InputFieldGroupMap map = new InputFieldGroupMap();
		 map.addRepeatingFieldGroupFactory(rfgFactory, command.getStudyAmendments().size());
		 return map;
	}

	@Override
	protected void validate(Study command, BeanWrapper commandBean,
			Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		for(InputFieldGroup grp : fieldGroups.values()){
			List<InputField> fields = grp.getFields();
			String versionProperty = fields.get(0).getPropertyName();
			String dateProperty = fields.get(1).getPropertyName();
			Object amVersion = commandBean.getPropertyValue(versionProperty);
			Object amDate = commandBean.getPropertyValue(dateProperty);
			if(amVersion == null && amDate == null){
				errors.rejectValue(versionProperty, "REQUIRED", "Either Amendment version or date is mandatory");
				errors.rejectValue(dateProperty, "REQUIRED", "Either Amendment date or version is mandatory");
			}
		}//~for
	}

	@Override
	public void postProcess(HttpServletRequest request, Study command, Errors errors) {
		super.postProcess(request, command, errors);
		String action = request.getParameter("_action");
		String selected = request.getParameter("_selected");
		if ("removeAmendment".equals(action)) {
			command.getStudyAmendments().remove(Integer.parseInt(selected));
		}
	}
}
