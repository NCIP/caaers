package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
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
import java.util.Date;
import java.io.*;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.semanticbits.rules.utils.RuleUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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
		 Study study = new LocalStudy();
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
		
		Study study = new LocalStudy();
		 converter.convertStudyDtoToStudyDomain(studyDto, study);
		 assertEquals("hello", study.getShortTitle());
		 assertNotNull(study.getStudyOrganizations());
		 assertNotNull(study.getStudySites());
		 assertEquals(1, study.getStudySites().size());
		 
		 assertEquals(0, DateUtils.compareDate(now.toGregorianCalendar().getTime(), study.getStudySites().get(0).getStartDate()));
	}

    public void testConvertStudyDomainToStudyDto() throws Exception {
        Study study = new LocalStudy();
        study.setShortTitle("Short Title test for export");
        study.setLongTitle("Long Title test for export");

        gov.nih.nci.cabig.caaers.webservice.Studies studies = converter.convertStudyDomainToStudyDto(study);

        assertEquals("Short Title test for export", studies.getStudy().get(0).getShortTitle());
        assertEquals("Long Title test for export", studies.getStudy().get(0).getLongTitle());

    }

    public void testExportStudy() throws Exception {
        Study study = new LocalStudy();
        study.setShortTitle("Short Title test for export");
        study.setLongTitle("Long Title test for export");
        study.setPrimaryIdentifierValue("ST-x8");
        study.setPhaseCode("Phase I Trial");
        study.setStatus("Administratively Complete");
        study.setMultiInstitutionIndicator(true);
        study.setAdeersReporting(true);
      
        study.setDesign(Design.PARTIAL);

        study.setAeTerminology(new AeTerminology());
        study.getAeTerminology().setCtcVersion(new Ctc());
        study.getAeTerminology().getCtcVersion().setId(3);
/*
        study.getAeTerminology().setMeddraVersion(new MeddraVersion());
        study.getAeTerminology().getMeddraVersion().setId(9);
*/

        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);


//////// FUNDING SPONSOR
        study.setFundingSponsor(new FundingSponsor());
        study.getFundingSponsor().setOrganizationAssignedIdentifier(new OrganizationAssignedIdentifier());
        study.getFundingSponsor().getOrganizationAssignedIdentifier().setValue("FS-x001");

        study.getFundingSponsor().setStudyFundingSponsor(new StudyFundingSponsor());
        study.getFundingSponsor().getStudyFundingSponsor().setOrganization(new LocalOrganization());
        study.getFundingSponsor().getStudyFundingSponsor().getOrganization().setNciInstituteCode("NCI-code-Russia-x789");
        study.getFundingSponsor().getStudyFundingSponsor().getOrganization().setName("Mayo Rochester - Russia");

        // Investigators

        Investigator i1 = new LocalInvestigator();
        i1.setFirstName("John");
        i1.setLastName("Karma");
        i1.setNciIdentifier("JKarma-01");

        StudyInvestigator si1 = new StudyInvestigator();
        si1.setStartDate(new Date());
        si1.setEndDate(new Date());
        si1.setRoleCode("PI");
        si1.setSiteInvestigator(new SiteInvestigator());
        si1.getSiteInvestigator().setInvestigator(i1);

        Investigator i2 = new LocalInvestigator();
        i2.setFirstName("Alex");
        i2.setLastName("Grails");
        i2.setNciIdentifier("AGrails-02");

        StudyInvestigator si2 = new StudyInvestigator();
        si2.setStartDate(new Date());
        si2.setEndDate(new Date());
        si2.setRoleCode("SI");
        si2.setSiteInvestigator(new SiteInvestigator());
        si2.getSiteInvestigator().setInvestigator(i2);

        List<StudyInvestigator> l = new ArrayList<StudyInvestigator>();
        study.getFundingSponsor().getStudyFundingSponsor().setStudyInvestigators(l);
        l.add(si1);
        l.add(si2);

        // Personnel

        ResearchStaff r1 = new LocalResearchStaff();
        r1.setFirstName("Kevin");
        r1.setLastName("Groovy");
        r1.setNciIdentifier("hhk-90");

        StudyPersonnel sp1 = new StudyPersonnel();
        sp1.setStartDate(new Date());
        sp1.setEndDate(new Date());
        sp1.setRoleCode("caaers_ae_cd");
        sp1.setSiteResearchStaff(new SiteResearchStaff());
        sp1.getSiteResearchStaff().setResearchStaff(r1);

        ResearchStaff r2 = new LocalResearchStaff();
        r2.setFirstName("Laura");
        r2.setLastName("Boyd");
        r2.setNciIdentifier("LB-6771");

        StudyPersonnel sp2 = new StudyPersonnel();
        sp2.setStartDate(new Date());
        sp2.setEndDate(new Date());
        sp2.setRoleCode("caaers_site_cd");
        sp2.setSiteResearchStaff(new SiteResearchStaff());
        sp2.getSiteResearchStaff().setResearchStaff(r2);

        List<StudyPersonnel> l2 = new ArrayList<StudyPersonnel>();
        study.getFundingSponsor().getStudyFundingSponsor().setStudyPersonnels(l2);
        l2.add(sp2);
        l2.add(sp1);


//////// COORDINATING CENTER        
        study.setCoordinatingCenter(new CoordinatingCenter());
        study.getCoordinatingCenter().setOrganizationAssignedIdentifier(new OrganizationAssignedIdentifier());
        study.getCoordinatingCenter().getOrganizationAssignedIdentifier().setValue("FS-x002");

        study.getCoordinatingCenter().setStudyCoordinatingCenter(new StudyCoordinatingCenter());
        study.getCoordinatingCenter().getStudyCoordinatingCenter().setOrganization(new LocalOrganization());
        study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().setNciInstituteCode("NCI-code-France-x789");
        study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().setName("Mayo Rochester - France");

        // Investigators

        Investigator i11 = new LocalInvestigator();
        i11.setFirstName("Angelo");
        i11.setLastName("Perotti");
        i11.setNciIdentifier("AP-101");

        StudyInvestigator si11 = new StudyInvestigator();
        si11.setStartDate(new Date());
        si11.setEndDate(new Date());
        si11.setRoleCode("PI");
        si11.setSiteInvestigator(new SiteInvestigator());
        si11.getSiteInvestigator().setInvestigator(i11);

        Investigator i12 = new LocalInvestigator();
        i12.setFirstName("Romano");
        i12.setLastName("Caruso");
        i12.setNciIdentifier("rc-402");

        StudyInvestigator si12 = new StudyInvestigator();
        si12.setStartDate(new Date());
        si12.setEndDate(new Date());
        si12.setRoleCode("SI");
        si12.setSiteInvestigator(new SiteInvestigator());
        si12.getSiteInvestigator().setInvestigator(i12);

        List<StudyInvestigator> l11 = new ArrayList<StudyInvestigator>();
        study.getCoordinatingCenter().getStudyCoordinatingCenter().setStudyInvestigators(l11);
        l11.add(si11);
        l11.add(si12);

        // Personnel

        ResearchStaff r11 = new LocalResearchStaff();
        r11.setFirstName("Paolo");
        r11.setLastName("Maldini");
        r11.setNciIdentifier("pm-90");

        StudyPersonnel sp11 = new StudyPersonnel();
        sp11.setStartDate(new Date());
        sp11.setEndDate(new Date());
        sp11.setRoleCode("caaers_ae_cd");
        sp11.setSiteResearchStaff(new SiteResearchStaff());
        sp11.getSiteResearchStaff().setResearchStaff(r11);

        ResearchStaff r12 = new LocalResearchStaff();
        r12.setFirstName("Claudio");
        r12.setLastName("Taffarel");
        r12.setNciIdentifier("CT-6771");

        StudyPersonnel sp12 = new StudyPersonnel();
        sp12.setStartDate(new Date());
        sp12.setEndDate(new Date());
        sp12.setRoleCode("caaers_site_cd");
        sp12.setSiteResearchStaff(new SiteResearchStaff());
        sp12.getSiteResearchStaff().setResearchStaff(r12);

        List<StudyPersonnel> l12 = new ArrayList<StudyPersonnel>();
        study.getCoordinatingCenter().getStudyCoordinatingCenter().setStudyPersonnels(l12);
        l12.add(sp12);
        l12.add(sp11);


//////// STUDY SITE
        StudySite studySite1 = new StudySite();

        studySite1.setOrganization(new LocalOrganization());
        studySite1.getOrganization().setName("Study Site Name - 01");
        studySite1.getOrganization().setNciInstituteCode("code-01");
        studySite1.setStartDate(DateUtils.yesterday());
        studySite1.setEndDate(DateUtils.tomorrow());

        // Investigators

        Investigator i31 = new LocalInvestigator();
        i31.setFirstName("Vladimir");
        i31.setLastName("Putin");
        i31.setNciIdentifier("VP-601");

        StudyInvestigator si31 = new StudyInvestigator();
        si31.setStartDate(new Date());
        si31.setEndDate(new Date());
        si31.setRoleCode("PI");
        si31.setSiteInvestigator(new SiteInvestigator());
        si31.getSiteInvestigator().setInvestigator(i31);

        studySite1.addStudyInvestigators(si31);

        // Personnel

        ResearchStaff r32 = new LocalResearchStaff();
        r32.setFirstName("Igor");
        r32.setLastName("Smirnof");
        r32.setNciIdentifier("is-190");

        StudyPersonnel sp32 = new StudyPersonnel();
        sp32.setStartDate(new Date());
        sp32.setEndDate(new Date());
        sp32.setRoleCode("caaers_ae_cd");
        sp32.setSiteResearchStaff(new SiteResearchStaff());
        sp32.getSiteResearchStaff().setResearchStaff(r32);

        studySite1.addStudyPersonnel(sp32);

        study.addStudyOrganization(studySite1);


// IDENTIFIERS

        SystemAssignedIdentifier sid1 = new SystemAssignedIdentifier();
        sid1.setSystemName("BAA_SYSTEM");
        sid1.setValue("BAA-jii7");
        sid1.setType(SystemAssignedIdentifier.MRN_IDENTIFIER_TYPE);
        study.getIdentifiers().add(sid1);

        SystemAssignedIdentifier sid2 = new SystemAssignedIdentifier();
        sid2.setSystemName("GRADE_SYSTEM");
        sid2.setValue("GR-jjk11");
        sid2.setType(SystemAssignedIdentifier.MRN_IDENTIFIER_TYPE);
        study.getIdentifiers().add(sid2);

        OrganizationAssignedIdentifier oid1 = new OrganizationAssignedIdentifier();
        oid1.setOrganization(new LocalOrganization());
        oid1.getOrganization().setName("Full House Organization");
        oid1.setValue("FHO-aaakk");
        oid1.setType(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        study.getIdentifiers().add(oid1);

        // TREATMENT ASSIGNMENT
        TreatmentAssignment ta = new TreatmentAssignment();
        ta.setDescription("TA description");
        ta.setComments("TA Comments");
        ta.setCode("TA CODE");
        ta.setDoseLevelOrder(5);
        study.setTreatmentAssignments(new ArrayList<TreatmentAssignment>());
        study.getTreatmentAssignments().add(ta);
        // exporting...

        // AGENTS
        study.setStudyAgents(new ArrayList<StudyAgent>());
        StudyAgent sa = new StudyAgent();
        sa.setAgent(new Agent()); sa.getAgent().setName("Agent name"); sa.getAgent().setNscNumber("Agent Nsc Number");
        sa.setIndType(INDType.IND_EXEMPT);
        sa.setPartOfLeadIND(true);
        study.getStudyAgents().add(sa);

        // STUDY DISEASE
        study.setCtepStudyDiseases(new ArrayList<CtepStudyDisease>());
        CtepStudyDisease ctep = new CtepStudyDisease();
        ctep.setDiseaseTerm(new DiseaseTerm());
        ctep.getDiseaseTerm().setMeddraCode("CTEP-Meddra-code-01");
        ctep.getDiseaseTerm().setTerm("CTEP_Term-01");
        ctep.setLeadDisease(false);
        study.getCtepStudyDiseases().add(ctep);

        study.setMeddraStudyDiseases(new ArrayList<MeddraStudyDisease>());
        MeddraStudyDisease meddra = new MeddraStudyDisease();
        meddra.setMeddraCode("Meddra-code-877");
        study.getMeddraStudyDiseases().add(meddra);

        // SAES
        study.setEpochs(new ArrayList<Epoch>());
        
        Epoch e = new Epoch(); study.getEpochs().add(e);
        e.setEpochOrder(1);
        e.setDescriptionText("Description");
        e.setName("Epoch name - 01");
        e.setArms(new ArrayList<Arm>());
        e.getArms().add(new Arm());
        List<SolicitedAdverseEvent> saes = new ArrayList<SolicitedAdverseEvent>();
        e.getArms().get(0).setSolicitedAdverseEvents(saes);
        SolicitedAdverseEvent sae = new SolicitedAdverseEvent(); saes.add(sae);
        sae.setCtcterm(new CtcTerm()); sae.getCtcterm().setCtepCode("AE-1 Ctep-code"); sae.getCtcterm().setCtepTerm("Ctep-Term-1"); sae.getCtcterm().setTerm("Term-1");
        sae = new SolicitedAdverseEvent(); saes.add(sae);
        sae.setCtcterm(new CtcTerm()); sae.getCtcterm().setCtepCode("AE-2 Ctep-code"); sae.getCtcterm().setCtepTerm("Ctep-Term-2"); sae.getCtcterm().setTerm("Term-2");
        sae = new SolicitedAdverseEvent(); saes.add(sae);
        sae.setCtcterm(new CtcTerm()); sae.getCtcterm().setCtepCode("AE-3 Ctep-code"); sae.getCtcterm().setCtepTerm("Ctep-Term-3"); sae.getCtcterm().setTerm("Term-3");
        sae = new SolicitedAdverseEvent(); saes.add(sae);
        sae.setCtcterm(new CtcTerm()); sae.getCtcterm().setCtepCode("AE-4 Ctep-code"); sae.getCtcterm().setCtepTerm("Ctep-Term-4"); sae.getCtcterm().setTerm("Term-4");

        // Expected AEs
        study.setExpectedAECtcTerms(new ArrayList<ExpectedAECtcTerm>());
        ExpectedAECtcTerm term = new ExpectedAECtcTerm();
        term.setCtcTerm(new CtcTerm()); term.getCtcTerm().setCtepCode("ctcTerm - 5001");
        study.getExpectedAECtcTerms().add(term);

        gov.nih.nci.cabig.caaers.webservice.Studies studies = converter.convertStudyDomainToStudyDto(study);

        try {
            // String tempDir = java.io. "/home/dell/Desktop/";
            String tempDir = System.getProperty("java.io.tmpdir");
            String fileName = "study_" + study.getPrimaryIdentifierValue();
            fileName = RuleUtil.getStringWithoutSpaces(fileName);

            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) ;
            marshaller.marshal(studies, sw);
            BufferedWriter out = new BufferedWriter(new FileWriter(tempDir + fileName + ".xml"));
            out.write(sw.toString());
            out.flush();
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

}
