package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResearchStaffRepositoryTest extends AbstractTestCase {
	
	ResearchStaffRepository repository;
	CSMUserRepository csmUserRepository;
	ResearchStaffDao researchStaffDao;
	StudyRepository studyRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		repository = new ResearchStaffRepository();
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		studyRepository = registerMockFor(StudyRepository.class);
		repository.setCsmUserRepository(csmUserRepository);
		researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
		repository.setResearchStaffDao(researchStaffDao);
		repository.setAuthenticationMode("local");
		repository.setStudyRepository(studyRepository);
	}

	public void testSave() throws Exception {
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_study_cd);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		siteResearchStaff.setEmailAddress("Joel@def.com");
		siteResearchStaff.setOrganization(org);
		SiteResearchStaffRole siteResearchStaffRole = new SiteResearchStaffRole();
		siteResearchStaffRole.setRoleCode("caaers_study_cd");
		siteResearchStaffRole.setStartDate(new Date());
		siteResearchStaff.addSiteResearchStaffRole(siteResearchStaffRole);
		siteResearchStaff.setAssociateAllStudies(Boolean.TRUE);
		staff.addSiteResearchStaff(siteResearchStaff);
		staff.setLoginId("Joel@def.com");
		String changeUrl = "/pages/url";
		expect(researchStaffDao.merge(staff)).andReturn(staff).anyTimes();
		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(staff, changeUrl);
		studyRepository.associateStudyPersonnel(staff);
		replayMocks();
		repository.save(staff, changeUrl);
		verifyMocks();
		assertEquals("Joel@def.com", staff.getLoginId());
		
	}
	
	public void testSaveWebSso() throws Exception {
		repository.setAuthenticationMode("webSSO");
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_study_cd);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		siteResearchStaff.setEmailAddress("Joel@def.com");
		siteResearchStaff.setOrganization(org);
		SiteResearchStaffRole siteResearchStaffRole = new SiteResearchStaffRole();
		siteResearchStaffRole.setRoleCode("caaers_study_cd");
		siteResearchStaffRole.setStartDate(new Date());
		siteResearchStaff.addSiteResearchStaffRole(siteResearchStaffRole);
		staff.addSiteResearchStaff(siteResearchStaff);
		staff.setLoginId("Joel2@def.com");
		String changeUrl = "/pages/url";
		expect(researchStaffDao.merge(staff)).andReturn(staff).anyTimes();
		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(staff, changeUrl);
		studyRepository.associateStudyPersonnel(staff);
		replayMocks();
		repository.save(staff, changeUrl);
		verifyMocks();
		assertEquals("Joel2@def.com", staff.getLoginId());
	}
	
	public void testSaveWebSso_NoLoginId() throws Exception{
		repository.setAuthenticationMode("webSSO");
		Organization org = Fixtures.createOrganization("NCI");
		List<UserGroupType> groupList = new ArrayList<UserGroupType>();
		groupList.add(UserGroupType.caaers_study_cd);
		ResearchStaff staff = Fixtures.createResearchStaff(org, groupList, "Joel");
		SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		siteResearchStaff.setEmailAddress("Joel@def.com");
		siteResearchStaff.setOrganization(org);
		SiteResearchStaffRole siteResearchStaffRole = new SiteResearchStaffRole();
		siteResearchStaffRole.setRoleCode("caaers_study_cd");
		siteResearchStaffRole.setStartDate(new Date());
		siteResearchStaff.addSiteResearchStaffRole(siteResearchStaffRole);
		staff.addSiteResearchStaff(siteResearchStaff);
		//staff.setLoginId("Joel2@def.com");
		String changeUrl = "/pages/url";
		expect(researchStaffDao.merge(staff)).andReturn(staff).anyTimes();
		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(staff, changeUrl);
		studyRepository.associateStudyPersonnel(staff);
		replayMocks();
		repository.save(staff, changeUrl);
		verifyMocks();
		assertNull(staff.getLoginId());
	}
	

}
