package gov.nih.nci.cabig.caaers.web.admin;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

public class OrganizationTabTest extends AbstractTestCase {

	public void testCreateFieldGroupsOrganization() {
		OrganizationTab organizationTab = new OrganizationTab();
		InputFieldGroupMap map = (InputFieldGroupMap)organizationTab.createFieldGroups(new LocalOrganization());
		assertNotNull(map);
		assertEquals(1, map.size());
		InputFieldGroup inputFieldGroup = map.get("organization");
		assertEquals(3, inputFieldGroup.getFields().size());
	}

}
