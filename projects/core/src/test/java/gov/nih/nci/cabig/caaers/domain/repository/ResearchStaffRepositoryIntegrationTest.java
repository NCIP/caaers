package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
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
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Biju Joseph
 */
public class ResearchStaffRepositoryIntegrationTest extends CaaersDbTestCase {

    private static Logger log = Logger.getLogger(ResearchStaffRepositoryIntegrationTest.class);

    private CSMObjectIdGenerator siteObjectIdGenerator;

    private ResearchStaffRepository researchStaffRepository;


    private OrganizationRepository organizationRepository;

    private UserProvisioningManager userProvisioningManager;

    private Organization organization;

    private String name;
    private JdbcTemplate jdbcTemplate;

    CSMUserRepositoryImpl csmUserRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        name = "" + Calendar.getInstance().getTime().getTime();
        name = name.substring(name.length() - 5, name.length() - 1);

        DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo
                ("admin", "localhost", new Date(), "/pages/task"));

        researchStaffRepository = (ResearchStaffRepository) getApplicationContext().getBean("researchStaffRepository");
        csmUserRepository = (CSMUserRepositoryImpl) getApplicationContext().getBean("csmUserRepository");
        // csmUserRepository.setMailSender(new );
        organizationRepository = (OrganizationRepository) getApplicationContext().getBean("organizationRepository");
        jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
        userProvisioningManager = (UserProvisioningManager) getApplicationContext().getBean("csmUserProvisioningManager");
        siteObjectIdGenerator = (CSMObjectIdGenerator) getApplicationContext().getBean("sitePrivilegeAndObjectIdGenerator");
        organization = Fixtures.createOrganization(name);
        organizationRepository.create(organization);
        assertNotNull(organization);


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
        deleteCsmDetailsForCreatingNewOrganization(organization);
        DataAuditInfo.setLocal(null);

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

        try {
            researchStaffRepository.save(researchStaff, "noURL");
        } catch (MailException e) {
            //ignore mail exception.
        }

        //now validate research staff, csm_user and csm_user_group
        ResearchStaff newResearchStaff = researchStaffRepository.getById(researchStaff.getId());
        assertEquals(2, newResearchStaff.getUserGroupTypes().size());

        valaidateResearchStaff(researchStaff, userGroupTypes);
        deleteCsmUser(researchStaff);

    }

    public void testSaveReserachStaffForExistingEmailAddress() throws Exception {

        List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

        userGroupTypes.add(UserGroupType.caaers_participant_cd);
        userGroupTypes.add(UserGroupType.caaers_site_cd);
        ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

        // there should be a mock emailer...
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

    public void testFindReserachStaffByEmailAddressOrLoginId() throws Exception {

        List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

        userGroupTypes.add(UserGroupType.caaers_participant_cd);
        userGroupTypes.add(UserGroupType.caaers_site_cd);
        ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

        // there should be a mock emailer...
        researchStaffRepository.save(researchStaff, "noURL");

        // now create new research staff with same email address and try to save it..

        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();

        researchStaffQuery.filterByLoginId(researchStaff.getLoginId());
        List<ResearchStaff> researchStaffList = researchStaffRepository.searchResearchStaff(researchStaffQuery);
        assertFalse(researchStaffList.isEmpty());
        for (ResearchStaff existingResearchStaff : researchStaffList) {
            assertTrue(StringUtils.equals(researchStaff.getLoginId(), existingResearchStaff.getLoginId())
                    || StringUtils.equals(researchStaff.getEmailAddress(), existingResearchStaff.getEmailAddress()));
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

        researchStaff = researchStaffRepository.getById(researchStaff.getId());
        researchStaff.setLastName("last name");
        researchStaff.addUserGroupType(UserGroupType.caaers_study_cd);
        researchStaff.removeUserGroupType(UserGroupType.caaers_site_cd);

        researchStaffRepository.save(researchStaff, "noURL");

        userGroupTypes = new ArrayList<UserGroupType>();
        userGroupTypes.add(UserGroupType.caaers_participant_cd);
        userGroupTypes.add(UserGroupType.caaers_study_cd);

        valaidateResearchStaff(researchStaff, userGroupTypes);
        deleteCsmUser(researchStaff);

    }

    public void testUpdateEmailAddressOfReserachStaff() throws Exception {

        List<UserGroupType> userGroupTypes = new ArrayList<UserGroupType>();

        userGroupTypes.add(UserGroupType.caaers_participant_cd);
        userGroupTypes.add(UserGroupType.caaers_site_cd);
        ResearchStaff researchStaff = Fixtures.createResearchStaff(organization, userGroupTypes, name);

        researchStaffRepository.save(researchStaff, "noURL");
        valaidateResearchStaff(researchStaff, userGroupTypes);

        // now update the research staff;
        String loginId = researchStaff.getLoginId();
        researchStaff = researchStaffRepository.getById(researchStaff.getId());
        researchStaff.setEmailAddress("newemail@email.com");
        researchStaff.addUserGroupType(UserGroupType.caaers_study_cd);
        researchStaff.removeUserGroupType(UserGroupType.caaers_site_cd);

        researchStaffRepository.save(researchStaff, "noURL");

        assertNotNull(researchStaff.getId());
        assertEquals("must not change login id if u update email address of research staff", loginId, researchStaff.getLoginId());
        userGroupTypes = new ArrayList<UserGroupType>();
        userGroupTypes.add(UserGroupType.caaers_participant_cd);
        userGroupTypes.add(UserGroupType.caaers_study_cd);

        valaidateResearchStaff(researchStaff, userGroupTypes);
        deleteCsmUser(researchStaff);

    }

    private void valaidateResearchStaff(final ResearchStaff researchStaff, final List<UserGroupType> userGroupTypes)
            throws CSObjectNotFoundException {
        User csmUser = userProvisioningManager.getUser(researchStaff.getLoginId());

        int assignedToGroups = jdbcTemplate.queryForInt(String.format("select count(*) from csm_user_group where user_id=%s"
                , csmUser.getUserId()));

        assertEquals(userGroupTypes.size() + 1, assignedToGroups);
        assertEquals(assignedToGroups, researchStaff.getUserGroupTypes().size() + 1);

        validateCsmUser(csmUser, researchStaff);

        for (UserGroupType userGroupType : userGroupTypes) {
            assertEquals(1, jdbcTemplate.queryForList(
                    "select group_id from csm_user_group where user_id=" + csmUser.getUserId()
                            + " and group_id=" + userGroupType.getCode().toString()).size());

        }
        assertEquals(1, jdbcTemplate.queryForList(
                "select distinct user_group_id from csm_user_group cug,csm_group cg where cug.group_id=cg.group_id and cug.user_id="
                        + csmUser.getUserId() + " and cg.group_name='"
                        + siteObjectIdGenerator.generateId(organization.getNciInstituteCode()) + "'").size());

    }

    private void validateCsmUser(final User csmUser, final ResearchStaff researchStaff) {
        assertNotNull(csmUser);
        assertNotNull(researchStaff);
        assertEquals(csmUser.getFirstName(), researchStaff.getFirstName());
        assertEquals(csmUser.getLastName(), researchStaff.getLastName());
        assertEquals(csmUser.getEmailId(), researchStaff.getEmailAddress());
        assertEquals(csmUser.getLoginName(), researchStaff.getLoginId());
        assertEquals(csmUser.getPhoneNumber(), researchStaff.getPhoneNumber());

    }

    private void validateGroup(final Group group, final Organization organization) {
        assertNotNull(organization);
        assertNotNull(group);
        assertEquals(group.getGroupDesc(), organization.getDescriptionText());
        String siteId = siteObjectIdGenerator.generateId(organization.getNciInstituteCode());
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
//        setComplete();
//        endTransaction();
//        startNewTransaction();
    }

    private void deleteCsmUser(final ResearchStaff researchStaff) {
        User csmUser = userProvisioningManager.getUser(researchStaff.getLoginId());

        jdbcTemplate.execute("delete from csm_user_group where user_id=" + csmUser.getUserId());
        jdbcTemplate.execute("delete from csm_user where user_id=" + csmUser.getUserId());
//        setComplete();
//        endTransaction();
//        startNewTransaction();
    }



}
