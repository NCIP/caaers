package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.webservice.OrganizationType;
import gov.nih.nci.cabig.caaers.webservice.PersonnelRoleCodeType;
import gov.nih.nci.cabig.caaers.webservice.RoleCodeType;
import gov.nih.nci.cabig.caaers.webservice.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.webservice.StatusType;
import gov.nih.nci.cabig.caaers.webservice.StudyInvestigatorType;
import gov.nih.nci.cabig.caaers.webservice.StudyPersonnelType;
import gov.nih.nci.cabig.caaers.webservice.StudyPhaseType;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType;
import gov.nih.nci.cabig.caaers.webservice.Study.StudyOrganizations;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType.StudyInvestigators;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType.StudyPersonnels;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyConverterTest extends AbstractTestCase {
	gov.nih.nci.cabig.caaers.webservice.Study studyDto;
	
	XMLGregorianCalendarImpl now = new XMLGregorianCalendarImpl();
	OrganizationType orgType;
	
	StudyConverter converter = new StudyConverter();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		//create the base studyDto
		studyDto = new gov.nih.nci.cabig.caaers.webservice.Study();
		studyDto.setShortTitle("hello");
		studyDto.setPhaseCode(StudyPhaseType.PHASE_0_TRIAL);
		studyDto.setStatus(StatusType.ACTIVE_TRIAL_IS_OPEN_TO_ACCRUAL);
		
		//org
		orgType = new OrganizationType();
		orgType.setName("test");
		orgType.setNciInstituteCode("abc");
		
		StudyOrganizations studyOrgs = new gov.nih.nci.cabig.caaers.webservice.Study.StudyOrganizations();
		List<StudySiteType> studySites = new ArrayList<StudySiteType>();
		studyOrgs.setStudySite(studySites);
		studyDto.setStudyOrganizations(studyOrgs);
		
	
	}
	
	//checks whether startDate and endDate is properly populated for
	//StudySite, StudyPersonnel and StudyInvestigators.
	public void testConvertStudyDtoToStudyDomain_StudySite_StartDate_And_EndDate() {
		
		StudySiteType sitesType  = new StudySiteType();
		sitesType.setEndDate(now);
		sitesType.setStartDate(now);
		sitesType.setOrganization(orgType);
		studyDto.getStudyOrganizations().getStudySite().add(sitesType);
		
		//add study person
		StudyPersonnels personnel =  new gov.nih.nci.cabig.caaers.webservice.StudySiteType.StudyPersonnels();
		List<StudyPersonnelType> studyPersonnelList = new ArrayList<StudyPersonnelType>();
		StudyPersonnelType person = new StudyPersonnelType();
		person.setStartDate(now);
		person.setRoleCode(PersonnelRoleCodeType.CAAERS_AE_CD);
		gov.nih.nci.cabig.caaers.webservice.ResearchStaffType staff = new gov.nih.nci.cabig.caaers.webservice.ResearchStaffType();
		staff.setFirstName("abc");
		staff.setLastName("hov");
		person.setResearchStaff(staff);
		studyPersonnelList.add(person);
		personnel.setStudyPersonnel(studyPersonnelList);
		sitesType.setStudyPersonnels(personnel);
		
		//add investigator
		StudyInvestigators studyInvestigators =  new gov.nih.nci.cabig.caaers.webservice.StudySiteType.StudyInvestigators();
		List<StudyInvestigatorType> studyInvestigatorList = new ArrayList<StudyInvestigatorType>();
		StudyInvestigatorType inv = new StudyInvestigatorType();
		inv.setRoleCode(RoleCodeType.PI);
		inv.setStartDate(now);
		
		SiteInvestigatorType siteInv = new SiteInvestigatorType();
		gov.nih.nci.cabig.caaers.webservice.InvestigatorType invType = new gov.nih.nci.cabig.caaers.webservice.InvestigatorType();
		invType.setFirstName("avc");
		invType.setNciIdentifier("hig");
		invType.setLastName("efg");
		
		siteInv.setInvestigator(invType);
		inv.setSiteInvestigator(siteInv);
		studyInvestigatorList.add(inv);
		studyInvestigators.setStudyInvestigator(studyInvestigatorList);
		sitesType.setStudyInvestigators(studyInvestigators);
		
		// now test--------
		 Study study = new Study();
		 converter.convertStudyDtoToStudyDomain(studyDto, study);
		 assertEquals("hello", study.getShortTitle());
		 assertNotNull(study.getStudyOrganizations());
		 assertNotNull(study.getStudySites());
		 assertEquals(1, study.getStudySites().size());
		 
		 StudySite site = study.getStudySites().get(0);
		 assertEquals(0, DateUtils.compareDate(now.toGregorianCalendar().getTime(), site.getEndDate()));
		 assertEquals(0, DateUtils.compareDate(now.toGregorianCalendar().getTime(), site.getStartDate()));
		 assertFalse(site.getStudyInvestigators().isEmpty());
		 assertFalse(site.getStudyPersonnels().isEmpty());
		 
		 StudyPersonnel sp = site.getStudyPersonnels().get(0);
		 assertSame(site, sp.getStudyOrganization());
		 //assertNotNull(sp.getResearchStaff());
		 //assertEquals("abc", sp.getResearchStaff().getFirstName());
		 assertNotNull(sp.getStartDate());
		 assertNull(sp.getEndDate());
		 assertEquals(0, DateUtils.compareDate(now.toGregorianCalendar().getTime(), sp.getStartDate()));
		 
		 
		 StudyInvestigator si = site.getStudyInvestigators().get(0);
		 assertNotNull(si.getStartDate());
		 assertNull(si.getEndDate());
		 assertEquals(0, DateUtils.compareDate(si.getStartDate(), now.toGregorianCalendar().getTime()));
		 assertEquals("avc",si.getSiteInvestigator().getInvestigator().getFirstName());
		 assertSame(site, si.getStudyOrganization());
		 
	}
	
	
	public void testConvertStudyDtoToStudyDomain_StudySite_empty_end_date(){
		StudySiteType sitesType  = new StudySiteType();
		sitesType.setStartDate(now);
		sitesType.setOrganization(orgType);
		studyDto.getStudyOrganizations().getStudySite().add(sitesType);
		
		Study study = new Study();
		 converter.convertStudyDtoToStudyDomain(studyDto, study);
		 assertEquals("hello", study.getShortTitle());
		 assertNotNull(study.getStudyOrganizations());
		 assertNotNull(study.getStudySites());
		 assertEquals(1, study.getStudySites().size());
		 
		 assertEquals(0, DateUtils.compareDate(now.toGregorianCalendar().getTime(), study.getStudySites().get(0).getStartDate()));
	}

}
