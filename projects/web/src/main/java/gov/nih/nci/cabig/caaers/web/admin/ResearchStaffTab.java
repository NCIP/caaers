package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Saurabh Agrawal
 */
public class ResearchStaffTab extends TabWithFields<ResearchStaff> {

	protected static final Log log = LogFactory.getLog(ResearchStaffTab.class);

	private static final String RESEARCH_STAFF_FIELD_GROUP = "researchStaff";

	private static final String SITE_FIELD_GROUP = "site";

	public ResearchStaffTab() {
		super("Research Staff Details", "Research Staff Details", "admin/research_staff_details");
		setAutoPopulateHelpKey(true);
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(final ResearchStaff command) {
		InputFieldGroup researchStaffFieldGroup;

		InputFieldGroup siteFieldGroup;

		siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);
		siteFieldGroup.getFields().add(InputFieldFactory.createAutocompleterField("organization", "Organization",true));
		researchStaffFieldGroup = new DefaultInputFieldGroup(RESEARCH_STAFF_FIELD_GROUP);

		InputField firstNameField = InputFieldFactory.createTextField("firstName", "First name", true);
		InputFieldAttributes.setSize(firstNameField, 30);
		researchStaffFieldGroup.getFields().add(firstNameField);

		InputField middleNameField = InputFieldFactory.createTextField("middleName", "Middle name", false);
		InputFieldAttributes.setSize(middleNameField, 30);
		researchStaffFieldGroup.getFields().add(middleNameField);

		InputField lastNameField = InputFieldFactory.createTextField("lastName", "Last name", true);
		InputFieldAttributes.setSize(lastNameField, 30);
		researchStaffFieldGroup.getFields().add(lastNameField);

		InputField ncidIdField = InputFieldFactory.createTextField("nciIdentifier", "NCI Identifier", true);
		InputFieldAttributes.setSize(ncidIdField, 30);
		researchStaffFieldGroup.getFields().add(ncidIdField);

		InputField emailAddressField = InputFieldFactory.createTextField("emailAddress", "Email address", 
				FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.EMAIL_VALIDATOR);
		InputFieldAttributes.setSize(emailAddressField, 30);
		researchStaffFieldGroup.getFields().add(emailAddressField);

		InputField phoneNumberField = InputFieldFactory.createTextField("phoneNumber", "Phone", 
				FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.PHONE_VALIDATOR);
		InputFieldAttributes.setSize(phoneNumberField, 30);
		researchStaffFieldGroup.getFields().add(phoneNumberField);

		InputField faxNumberField = InputFieldFactory.createTextField("faxNumber", "Fax", 
				FieldValidator.PHONE_VALIDATOR);
		InputFieldAttributes.setSize(faxNumberField, 30);
		researchStaffFieldGroup.getFields().add(faxNumberField);

		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addInputFieldGroup(researchStaffFieldGroup);
		map.addInputFieldGroup(siteFieldGroup);

		return map;
	}


	private OrganizationDao organizationDao;

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}