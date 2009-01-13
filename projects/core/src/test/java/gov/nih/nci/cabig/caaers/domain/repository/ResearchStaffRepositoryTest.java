package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.List;

public class ResearchStaffRepositoryTest extends AbstractTestCase {
	
	ResearchStaffRepository repository;
	CSMUserRepository csmUserRepository;
	ResearchStaffDao researchStaffDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		repository = new ResearchStaffRepository();
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		repository.setCsmUserRepository(csmUserRepository);
		researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
		repository.setResearchStaffDao(researchStaffDao);
		repository.setAuthenticationMode("local");
		
	}

	public void testSave() {
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_physician);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		String changeUrl = "/pages/url";
		researchStaffDao.save(staff);
		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(staff, changeUrl);
		
		replayMocks();
		repository.save(staff, changeUrl);
		verifyMocks();
		assertEquals("Joel@def.com", staff.getLoginId());
		
	}
	
	public void testSaveWebSso() {
		repository.setAuthenticationMode("webSSO");
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_physician);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		staff.setLoginId("Joel2@def.com");
		String changeUrl = "/pages/url";
		researchStaffDao.save(staff);
		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(staff, changeUrl);
		
		replayMocks();
		repository.save(staff, changeUrl);
		verifyMocks();
		assertEquals("Joel2@def.com", staff.getLoginId());
	}
	
	public void testSaveWebSso_NoLoginId() {
		repository.setAuthenticationMode("webSSO");
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_physician);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		String changeUrl = "/pages/url";
		
		replayMocks();
		try {
			repository.save(staff, changeUrl);
			fail("Should throw exception");
		} catch (Exception e) {
		}
		
	}
	

}
