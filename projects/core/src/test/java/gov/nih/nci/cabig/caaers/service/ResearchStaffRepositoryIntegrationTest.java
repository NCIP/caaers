package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.accesscontrol.SiteSecurityAfterInvocationCollectionFilteringProvider;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author Biju Joseph
 */
public class ResearchStaffRepositoryIntegrationTest extends AbstractTransactionalDataSourceSpringContextTests {

	private static Logger log = Logger.getLogger(ResearchStaffRepositoryIntegrationTest.class);

	private CSMObjectIdGenerator siteObjectIdGenerator;

	private ResearchStaffRepository researchStaffRepository;

	private SiteSecurityAfterInvocationCollectionFilteringProvider siteSecurityAfterInvocationCollectionFilteringProvider;

	private OrganizationService organizationService;

	private UserProvisioningManager userProvisioningManager;

	private Organization organization;

	private String name;

	// public void testSearchStudy() throws Exception {
	// Study study = new Study();
	// study.setDescription("test");
	// List<Identifier> identifiers = new ArrayList<Identifier>();
	// identifiers.add(new SystemAssignedIdentifier());
	// study.setIdentifiers(identifiers);
	// StudySite studySite = new StudySite();
	// Organization organization = createOrganization("abc");
	// organization.setId(1);
	// studySite.setOrganization(organization);
	// study.addStudyOrganization(studySite);
	// studyDao.getStudyHavingStudySites(studySite);
	// studyDao.searchByExample(study, true);
	//
	// }

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:applicationContext-test.xml",
				"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
				"classpath*:gov/nih/nci/cabig/caaers/applicationContext-test-security.xml",
				"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
		// "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
		};

	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

		name = "" + Calendar.getInstance().getTime().getTime();
		name = name.substring(name.length() - 5, name.length() - 1);

        DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo
                ("admin", "localhost", new Date(), "/pages/task"));

        organization = Fixtures.createOrganization(name);
		organizationService.create(organization);
		assertNotNull(organization);

	}

	@Override
	protected void onTearDownAfterTransaction() throws Exception {
		super.onTearDownAfterTransaction();
		deleteCsmDetailsForCreatingNewOrganization(organization);
		DataAuditInfo.setLocal(null);
        super.endTransaction();
	}

	public void testSaveOrganization() throws Exception {

		// now validate the csm_protection_element & csm_protection_group & csm_user_group_role_pg

		String groupId = (String) jdbcTemplate.queryForObject("select group_id from csm_group where group_desc='"
				+ organization.getDescriptionText() + "'", String.class);
		assertNotNull(groupId);
		String protectionElementName = (String) jdbcTemplate.queryForObject(
				"select group_name from csm_group where group_id=" + groupId, String.class);
		assertNotNull(protectionElementName);

		assertNotNull(jdbcTemplate
				.queryForInt("select user_group_role_pg_id from csm_user_group_role_pg pg where pg.protection_group_id=(select cpg.protection_group_id from csm_protection_group cpg where protection_group_name ='"
						+ protectionElementName + "')" + " and role_id=-14"));

		Group group = userProvisioningManager.getGroupById(groupId);
		validateGroup(group, organization);

	}

	public void testSaveReserachStaff() throws Exception {

		List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

		userGroupTypes.add(UserGroupType.caaers_super_user);
		userGroupTypes.add(UserGroupType.caaers_ae_cd);
		ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

		researchStaffRepository.save(researchStaff, "noURL");

		// now validate research staff, csm_user and csm_user_group
		// ResearchStaff newResearchStaff = researchStaffRepository.getById(researchStaff.getId());
		// assertEquals(2, newResearchStaff.getUserGroupTypes().size());

		valaidateResearchStaff(researchStaff, userGroupTypes);
		deleteCsmUser(researchStaff);

	}

	public void testSaveReserachStaffForExistingEmailAddress() throws Exception {

		List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

		userGroupTypes.add(UserGroupType.caaers_participant_cd);
		userGroupTypes.add(UserGroupType.caaers_site_cd);
		ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

		researchStaffRepository.save(researchStaff, "noURL");

		// now create new research staff with same email address and try to save it..

		ResearchStaff newResearchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);
		try {
		    researchStaffRepository.save(newResearchStaff, "noURL");
		    fail("email address should be unique");
		}
		catch (CaaersSystemException e) {
		    //assertEquals("Email address allready exists..!", e.getMessage());
		    assertNotNull(e.getMessage());
		}

	}

	public void testUpdateReserachStaff() throws Exception {

		List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

		userGroupTypes.add(UserGroupType.caaers_participant_cd);
		userGroupTypes.add(UserGroupType.caaers_site_cd);
		ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

		researchStaffRepository.save(researchStaff, "noURL");
		valaidateResearchStaff(researchStaff, userGroupTypes);

		// now update the research staff;

		// researchStaff = researchStaffRepository.getById(researchStaff.getId());
		// researchStaff.setLastName("last name");
		// researchStaff.addUserGroupType(UserGroupType.caaers_study_cd);
		// researchStaff.removeUserGroupType(UserGroupType.caaers_site_cd);
		//
		// researchStaffRepository.save(researchStaff, "noURL");
		//
		// userGroupTypes = new ArrayList<UserGroupType>();
		// userGroupTypes.add(UserGroupType.caaers_participant_cd);
		// userGroupTypes.add(UserGroupType.caaers_study_cd);
		//
		// valaidateResearchStaff(researchStaff, userGroupTypes);
		// deleteCsmUser(researchStaff);

	}

	private void valaidateResearchStaff(final ResearchStaff researchStaff, final List<UserGroupType> userGroupTypes)
			throws CSObjectNotFoundException {

		int assignedToGroups = jdbcTemplate.queryForInt("select count(*) from csm_user_group where user_id="
				+ researchStaff.getLoginId());

		assertEquals(userGroupTypes.size() + 1, assignedToGroups);
		assertEquals(assignedToGroups, researchStaff.getUserGroupTypes().size() + 1);

		User csmUser = userProvisioningManager.getUserById(researchStaff.getLoginId());
		validateCsmUser(csmUser, researchStaff);

		for (UserGroupType userGroupType : userGroupTypes) {
			assertEquals(1, jdbcTemplate.queryForList(
					"select group_id from csm_user_group where user_id=" + researchStaff.getLoginId()
							+ " and group_id=" + userGroupType.getCode().toString()).size());

		}
		assertEquals(1, jdbcTemplate.queryForList(
				"select distinct user_group_id from csm_user_group cug,csm_group cg where cug.group_id=cg.group_id and cug.user_id="
						+ researchStaff.getLoginId() + " and cg.group_name='"
						+ siteObjectIdGenerator.generateId(organization) + "'").size());

	}

	private void validateCsmUser(final User csmUser, final ResearchStaff researchStaff) {
		assertNotNull(csmUser);
		assertNotNull(researchStaff);
		assertEquals(csmUser.getFirstName(), researchStaff.getFirstName());
		assertEquals(csmUser.getLastName(), researchStaff.getLastName());
		assertEquals(csmUser.getEmailId(), researchStaff.getEmailAddress());
		assertEquals(csmUser.getOrganization(), researchStaff.getOrganization().getNciInstituteCode());
		assertEquals(csmUser.getLoginName(), researchStaff.getEmailAddress());
		assertEquals(csmUser.getUserId().toString(), researchStaff.getLoginId());
		assertEquals(csmUser.getPhoneNumber(), researchStaff.getPhoneNumber());

	}

	private void validateGroup(final Group group, final Organization organization) {
		assertNotNull(organization);
		assertNotNull(group);
		assertEquals(group.getGroupDesc(), organization.getDescriptionText());
		String siteId = siteObjectIdGenerator.generateId(organization);
		assertEquals(group.getGroupName(), siteId);

	}

	private void deleteCsmDetailsForCreatingNewOrganization(final Organization organization) {

		String groupId = (String) jdbcTemplate.queryForObject("select group_id from csm_group where group_desc='"
				+ organization.getDescriptionText() + "'", String.class);
		String protectionElementName = (String) jdbcTemplate.queryForObject(
				"select group_name from csm_group where group_id=" + groupId, String.class);

		jdbcTemplate
				.execute("delete from csm_user_group_role_pg pg where pg.protection_group_id=(select cpg.protection_group_id from csm_protection_group cpg where protection_group_name ='"
						+ protectionElementName + "')" + " and role_id=-14");
		jdbcTemplate.execute("delete from csm_protection_element where protection_element_name='"
				+ protectionElementName + "'");
		jdbcTemplate.execute("delete from csm_protection_group where protection_group_name='" + protectionElementName
				+ "'");

		jdbcTemplate.execute("delete from csm_group where group_id=" + groupId);
		setComplete();
		endTransaction();
		startNewTransaction();
	}

	private void deleteCsmUser(final ResearchStaff researchStaff) {

		jdbcTemplate.execute("delete from csm_user_group where user_id=" + researchStaff.getLoginId());
		jdbcTemplate.execute("delete from csm_user where user_id=" + researchStaff.getLoginId());
		setComplete();
		endTransaction();
		startNewTransaction();
	}

	@Required
	public void setSiteSecurityAfterInvocationCollectionFilteringProvider(
			final SiteSecurityAfterInvocationCollectionFilteringProvider siteSecurityAfterInvocationCollectionFilteringProvider) {
		this.siteSecurityAfterInvocationCollectionFilteringProvider = siteSecurityAfterInvocationCollectionFilteringProvider;
	}

	@Required
	public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}

	@Required
	public void setOrganizationDao(final OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@Required
	public void setSiteObjectIdGenerator(final CSMObjectIdGenerator siteObjectIdGenerator) {
		this.siteObjectIdGenerator = siteObjectIdGenerator;
	}

	@Required
	public void setResearchStaffRepository(final ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

}
