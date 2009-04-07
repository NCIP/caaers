package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_STUDIES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LoadStatus;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Ram Chilukuri
 * @author Krikor Krumlian
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * @author <a href="mailto:ion.olaru@semanticbits.com">Ion C. Olaru</a>
 */
@CaaersUseCases({CREATE_STUDY, STUDY_ABSTRACTION, IMPORT_STUDIES})
public class StudyDaoTest extends DaoNoSecurityTestCase<StudyDao> {

    private OrganizationDao sitedao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
    private AgentDao agentDao = (AgentDao) getApplicationContext().getBean("agentDao");
    private InvestigationalNewDrugDao indDao = (InvestigationalNewDrugDao) getApplicationContext().getBean("investigationalNewDrugDao");

    public void testGet() throws Exception {
        Study loaded = getDao().getById(-2);
        assertNotNull("Study not found", loaded);
        assertEquals("Short Title", loaded.getShortTitle());
    }

    public void testGetByGridId() throws Exception {
        Study study = getDao().getByGridId("f2321");
        assertNotNull("Study not found", study);

        Study study2 = getDao().getByGridId(study);
        assertNotNull("Study not found", study2);
    }

    public void testSaveWithCtc() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();
            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setAdeersReporting(Boolean.TRUE);
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
            assertNotNull("Ctc Version is null", reloaded.getAeTerminology().getCtcVersion());
            assertEquals("Term should be Ctc", Term.CTC, reloaded.getAeTerminology().getTerm());
        }
    }

    public void testSaveWithMedDRA() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();
            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.setAeTerminology(Fixtures.createMedDRATerminology(newStudy));
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setAdeersReporting(Boolean.TRUE);
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
            assertNull("Ctc Version should be null", reloaded.getAeTerminology().getCtcVersion());
            assertEquals("Term should be MedDRA", Term.MEDDRA, reloaded.getAeTerminology().getTerm());
        }
    }

    public void testSaveWithNewCondition() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();

            StudyCondition sc = new StudyCondition();
            Condition condition = new Condition();
            condition.setConditionName("NEW Condition 101.");

            sc.setStudy(newStudy);
            sc.setTerm(condition);
            newStudy.addStudyCondition(sc);

            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setAdeersReporting(Boolean.TRUE);
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            assertEquals(1, newStudy.getStudyConditions().size());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
            assertNull("Ctc Version should be null", reloaded.getAeTerminology().getCtcVersion());
        }
    }
    public void testSaveWithExistingCondition() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();

            StudyCondition sc = new StudyCondition();
            Condition condition = new Condition();
            condition.setId(-18);
            condition.setConditionName("NEW Condition 001.");
            sc.setStudy(newStudy);
            sc.setTerm(condition);
            newStudy.addStudyCondition(sc);

            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setAdeersReporting(Boolean.TRUE);
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
            assertNull("Ctc Version should be null", reloaded.getAeTerminology().getCtcVersion());
            assertEquals(1, reloaded.getStudyConditions().size());
        }
    }

    public void testGetBySubnameMatchesShortTitle() throws Exception {
        List<Study> actual = getDao().getBySubnames(new String[]{"orter"});
        assertEquals("Wrong number of matches", 1, actual.size());
        assertEquals("Wrong match", -3, (int) actual.get(0).getId());
    	assertTrue(true);
    }

    public void testGetBySubnameMatchesLongTitle() throws Exception {
        List<Study> actual = getDao().getBySubnames(new String[]{"long"});
        assertEquals("Wrong number of matches", 1, actual.size());
        assertEquals("Wrong match", -2, (int) actual.get(0).getId());
    	assertTrue(true);
    }

    public void testGetBySubnameMatchesIntersectionOfSubnames() throws Exception {
        List<Study> actual = getDao().getBySubnames(new String[]{"long", "title"});
        assertEquals("Wrong number of matches", 1, actual.size());
        assertEquals("Wrong match", -2, (int) actual.get(0).getId());
    	assertTrue(true);
    }

    public void testGetBySubnameWithNullSubnamesReturnsNothing() throws Exception {
        List<Study> actual = getDao().getBySubnames(null);
        assertEquals(0, actual.size());
    }

    public void testGetBySubnameWithNoSubnamesReturnsNothing() throws Exception {
        List<Study> actual = getDao().getBySubnames(new String[]{});
        assertEquals(0, actual.size());
    }

    public void testSearchByExactExample() throws Exception {
        Study example = new Study();
        example.setDescription("Description");

        List<Study> actual = getDao().searchByExample(example, false);
        assertEquals("Wrong number of matches", 1, actual.size());
        assertEquals("Wrong match", -2, (int) actual.get(0).getId());
    	assertTrue(true);
    }

    public void testSearchByExactExampleWithIdentifiers() throws Exception {
        Study example = new Study();
        example.setDescription("Description");

        OrganizationAssignedIdentifier idOne = new OrganizationAssignedIdentifier();
        SystemAssignedIdentifier idTwo= new SystemAssignedIdentifier();
        idOne.setValue("FSSI-01");

        example.addIdentifier(idOne);
        example.addIdentifier(idTwo);

        List<Study> actual = getDao().searchByExample(example, true);
        assertEquals("Wrong number of matches", 1, actual.size());
        assertEquals("Wrong match", -5, (int) actual.get(0).getId());
    	assertTrue(true);
    }

    public void testSearchByWildcardExample() throws Exception {
        Study example = new Study();
        example.setShortTitle("orte");

        List<Study> actual = getDao().searchByExample(example, true);
        assertEquals("Wrong number of matches", 1, actual.size());
        Set<Integer> ids = new HashSet<Integer>();
        for (Study study : actual) {
            ids.add(study.getId());
        }
        assertTrue(ids.contains(-3));
    	assertTrue(true);
    }

    public void testGetByIdentifierByTypeAndValue() throws Exception {
        Identifier id = Identifier.createTemplate("local", "1138-42");
        Study match = getDao().getByIdentifier(id);
        assertNotNull("No matches found", match);
        assertEquals("Wrong study matched", -3, (int) match.getId());
    }

    /**
     * Test for retrieving all study sites associated with this Study
     *
     * @throws Exception
     */
    public void testGetStudySites() throws Exception {
        Study study = getDao().getById(-2);
        List<StudySite> sites = study.getStudySites();
        assertEquals("Wrong number of study sites", 2, sites.size());
        List<Integer> ids = collectIds(sites);

        assertContains("Missing expected study site", ids, -1000);
    }

    /**
     * Test for retrieving all study funding sponsors associated with this Study
     *
     * @throws Exception
     */
    public void testGetStudyFundingSponsors() throws Exception {
        Study study = getDao().getById(-2);
        List<StudyFundingSponsor> sponsors = study.getStudyFundingSponsors();
        assertEquals("Wrong number of study funding sponsors", 1, sponsors.size());
        System.out.println("Study funding sponsor is: " + sponsors.get(0).getOrganization().getName());
        List<Integer> ids = collectIds(sponsors);

        assertContains("Missing expected study funding sponsor", ids, -1001);
    }

    /**
     * Test for retrieving all study coordinating centers associated with this Study
     *
     * @throws Exception
     */
    public void testGetStudyCoordinatingCenters() throws Exception {
        {
            Study study = getDao().getById(-2);
            List<StudyCoordinatingCenter> centers = study.getStudyCoordinatingCenters();
            assertEquals("Wrong number of study coordinating centers", 1, centers.size());
            List<Integer> ids = collectIds(centers);

            assertContains("Missing expected study funding sponsor", ids, -1004);

            StudyCoordinatingCenter sc = centers.get(0);
            study.removeStudyOrganization(sc);
            getDao().save(study);
        }
        interruptSession();
        {
            Study study = getDao().getById(-2);
            List<StudyCoordinatingCenter> centers = study.getStudyCoordinatingCenters();
            int size = 0;
            if (centers != null) size = centers.size();
            assertEquals("Wrong number of study co-ordinating centers", 0, size);
        }

    }

    public void testSaveNewStudyWithFundingSponsor() throws Exception {
        Integer savedId;
        {
            Organization sponsor = sitedao.getById(-1001);
            Organization organization = sitedao.getById(-1003);

            Study study = new Study();
            study.setShortTitle("ShortTitleText");
            study.setLongTitle("LongTitleText");
            study.setPhaseCode("PhaseCode");
            study.setStatus("Status");
            study.setTargetAccrualNumber(150);
            // study.setType("Type");
            study.setMultiInstitutionIndicator(true);
            study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
            study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            study.setAdeersReporting(Boolean.TRUE);

            // Study Site
            StudySite studySite = new StudySite();
            studySite.setOrganization(organization);
            studySite.setStatusCode("active");

            study.addStudySite(studySite);

            // Study funding sponsor
            StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
            fundingSponsor.setOrganization(sponsor);
            study.addStudyOrganization(fundingSponsor);

            getDao().save(study);

            savedId = study.getId();
            assertNotNull("The saved study didn't get an id", savedId);
        }

        interruptSession();
        {
            Study loaded = getDao().getById(savedId);
            assertNotNull("Could not reload study with id " + savedId, loaded);
            // assertNotNull("GridId not updated", loaded.getGridId());
            assertEquals("Wrong name", "ShortTitleText", loaded.getShortTitle());
            assertEquals("Wrong study funding sponsor", "National Cancer Institute", loaded.getStudyFundingSponsors()
                    .get(0).getOrganization().getName());
        }
    }

    public void testSaveNewStudyWithCoordinatingCenter() throws Exception {
        Integer savedId;
        {
            Organization sponsor = sitedao.getById(-1001);
            Organization organization = sitedao.getById(-1003);
            Organization center = sitedao.getById(-1002);

            Study study = new Study();
            study.setShortTitle("ShortTitleText");
            study.setLongTitle("LongTitleText");
            study.setPhaseCode("PhaseCode");
            study.setStatus("Status");
            study.setTargetAccrualNumber(150);
            study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            // study.setType("Type");
            study.setMultiInstitutionIndicator(true);
            study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
            study.setAdeersReporting(Boolean.TRUE);
            // study.setCtcVersion(ctc);

            // Study Site
            StudySite studySite = new StudySite();
            studySite.setOrganization(organization);
            studySite.setStatusCode("active");

            study.addStudySite(studySite);

            // Study funding sponsor
            StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
            fundingSponsor.setOrganization(sponsor);
            study.addStudyOrganization(fundingSponsor);

            // Study coordinating center
            StudyCoordinatingCenter coCenter = new StudyCoordinatingCenter();
            coCenter.setOrganization(center);
            study.addStudyOrganization(coCenter);

            getDao().save(study);

            savedId = study.getId();
            assertNotNull("The saved study didn't get an id", savedId);
        }

        interruptSession();
        {
            Study loaded = getDao().getById(savedId);
            assertNotNull("Could not reload study with id " + savedId, loaded);
            // assertNotNull("GridId not updated", loaded.getGridId());
            assertEquals("Wrong name", "ShortTitleText", loaded.getShortTitle());
            assertEquals("Wrong study funding sponsor", "National Cancer Institute", loaded.getStudyFundingSponsors()
                    .get(0).getOrganization().getName());
            assertEquals("Wrong study coordinating center", "CALGB", loaded.getStudyCoordinatingCenters().get(0)
                    .getOrganization().getName());
        }
    }

    private List<Integer> collectIds(List<? extends DomainObject> actual) {
        List<Integer> ids = new ArrayList<Integer>(actual.size());
        for (DomainObject object : actual) {
            ids.add(object.getId());
        }
        return ids;
    }


    public void testGetByShortTitle() throws Exception {
//        Study s = getDao().getByShortTitle("Short Title");
//        assertNotNull("Study should not be null", s);
assertTrue(true);
    }

    public void testSaveNewStudyWithINDAgent() {
        int studyId = 0;
        {

            Study newStudy = new Study();
            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setAdeersReporting(Boolean.TRUE);
            // study agent
            Agent agent = agentDao.getById(-990);
            assertNotNull(agent);
            StudyAgent sa = new StudyAgent();
            sa.setAgent(agent);
            newStudy.addStudyAgent(sa);
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            sa.setStudy(newStudy);
            agent = agentDao.getById(-991);
            sa = new StudyAgent();
            sa.setAgent(agent);
            InvestigationalNewDrug ind1 = indDao.getById(-881);
            InvestigationalNewDrug ind2 = indDao.getById(-882);
            StudyAgentINDAssociation ass1 = new StudyAgentINDAssociation();
            ass1.setStudyAgent(sa);
            sa.addStudyAgentINDAssociation(ass1);

            ass1.setInvestigationalNewDrug(ind1);
            StudyAgentINDAssociation ass2 = new StudyAgentINDAssociation();
            ass2.setStudyAgent(sa);
            ass1.setInvestigationalNewDrug(ind1);
            ass2.setInvestigationalNewDrug(ind2);
            sa.addStudyAgentINDAssociation(ass2);

            newStudy.addStudyAgent(sa);

            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            studyId = newStudy.getId();
        }
        interruptSession();
        {
            Study loaded = getDao().getById(studyId);
            assertNotNull("Could not reload study with id " + studyId, loaded);
            // assertNotNull("GridId not updated", loaded.getGridId());
            assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
            assertNotNull("Agents should not be null", loaded.getStudyAgents());
            assertEquals("Study agents size", 2, loaded.getStudyAgents().size());
            assertEquals("Agent ID is wrong", -990, loaded.getStudyAgents().get(0).getAgent().getId().intValue());
            List<StudyAgentINDAssociation> aList = loaded.getStudyAgents().get(1).getStudyAgentINDAssociations();
            assertEquals("IND size", 2, aList.size());
        }
    }


    public void testSaveUpdateStudyWithTreatmentAssignment() {
        int studyId = 0;
        Date d = new Date();
        {
            Study newStudy = new Study();
            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");
            newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            newStudy.setAdeersReporting(Boolean.TRUE);

            TreatmentAssignment ta = new TreatmentAssignment();
            ta.setCode("111");
            ta.setComments("no comments");
            ta.setDescription("sample description");
            ta.setDoseLevelOrder(33);
            ta.setStudy(newStudy);
            newStudy.addTreatmentAssignment(ta);


            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            assertNotNull("Treatment is not saved", newStudy.getTreatmentAssignments().get(0).getId());
            studyId = newStudy.getId();
        }
        interruptSession();
        {
            Study loaded = getDao().getById(studyId);
            assertNotNull("Could not reload study with id " + studyId, loaded);
            // assertNotNull("GridId not updated", loaded.getGridId());
            assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
            assertEquals("Treatment Assigments are not loaded properly", 1, loaded.getTreatmentAssignments().size());
            TreatmentAssignment ta = loaded.getTreatmentAssignments().get(0);
            assertEquals("Invalid treatment code", "111", ta.getCode());
            loaded.getTreatmentAssignments().remove(ta);
            getDao().save(loaded);
        }
        interruptSession();
        {
            Study loaded = getDao().getById(studyId);
            assertNotNull("Could not reload study with id " + studyId, loaded);
            assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
            assertEquals("Treatment Assigments are not removed properly", 0, loaded.getTreatmentAssignments().size());
        }

    }

    public void testSearchWithStudyQuery() {
        StudyQuery query = new StudyQuery();
        query.filterByIdentifierValueExactMatch("1138-42");
        List<Study> studies = getDao().find(query);
        for (Study study : studies) {
            System.out.println("Short title :" + study.getShortTitle());
        }
        assertNotNull("There should be study corresponding to identifier 1138-42");
        assertEquals("There should be exactly one study with identifier 1138-42", 1, studies.size());
    }

    public void testSearchWithStudyQueryHavingIdentifierTypeAndValue() {
        StudyQuery query = new StudyQuery();
        query.filterByIdentifierValue("1138-42");
        query.filterByIdentifierType("local");
        List<Study> studies = getDao().find(query);
        assertNotNull("There should be study corresponding to identifier 1138-421", studies.get(0));
        assertEquals("There should be two identifiers for the study with identifier 1138-421", 2, studies.size());
    }

    public void testUnableToLoadInprogressStudy() throws Exception {
        Study study = getDao().getById(-4);
        assertNull("Study should be null", study);
    }


    public void testSaveStudyInInprogressStatus() throws Exception {
        Integer savedId;

        Study newStudy = new Study();
        newStudy.setShortTitle("Short Title Inserted");
        newStudy.setLongTitle("Long Title Inserted");
        newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
        newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
        newStudy.setLoadStatus(LoadStatus.INPROGRESS.getCode());
        newStudy.setAdeersReporting(Boolean.TRUE);
        getDao().save(newStudy);
        assertNotNull("No ID for newly saved study", newStudy.getId());
        savedId = newStudy.getId();

        System.out.println(savedId);


        interruptSession();
        System.out.println("loadstatus :" + newStudy.getLoadStatus());
        assertNotNull("Study must be saved", savedId);
        final int studyId = savedId.intValue();
        Study study = (Study) getJdbcTemplate().execute(new StatementCallback() {
            public Object doInStatement(Statement st) throws SQLException,
                    DataAccessException {
                ResultSet rs = st.executeQuery("select * from studies where load_status = 0 and id = " + studyId);
                if (rs.next()) {
                    Study s = new Study();
                    s.setId(rs.getInt(1));
                    s.setShortTitle("Shortest");
                    return s;
                }
                return null;
            }
        });
        System.out.println(study.getId());
        assertEquals("Study Id should be same", studyId, study.getId().intValue());
    }


    public void testDeleteStudy() {
        Study study = getDao().getById(-3);
        getDao().delete(study);
        interruptSession();
        study = getDao().getById(-3);
        assertNull("Study identified by id -3 must be null", study);
    }

    public void testSaveSolicitedAdverseEvents() {

        Integer studyId = null;
        {
            Study newStudy = Fixtures.createStudy("Arun Study 1");
            newStudy.setShortTitle("ARUN Short Title Inserted");
            newStudy.setLongTitle("ARUN Long Title Inserted");
            newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
            newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setLoadStatus(LoadStatus.INPROGRESS.getCode());
            newStudy.setAdeersReporting(Boolean.TRUE);
            //getDao().save(newStudy);
            //	assertNotNull("No ID for newly saved study", newStudy.getId());
            //	Integer savedId = newStudy.getId();
            //   System.out.println("Study created : " + savedId);


            Epoch epoch1 = new Epoch("Epoch1", 1, "Arm 1", "Arm 2");
            epoch1.setDescriptionText("Epoch 1 description");
            epoch1.setEpochOrder(1);


            Epoch epoch2 = new Epoch("Epoch2", 2, "Arm 3", "Arm 4");
            epoch2.setDescriptionText("Epoch 2 description");
            epoch2.setEpochOrder(2);


            SolicitedAdverseEvent sae11 = new SolicitedAdverseEvent();
            CtcTerm ctcterm = new CtcTerm();
            ctcterm.setId(-5);
            sae11.setCtcterm(ctcterm);

            SolicitedAdverseEvent sae12 = new SolicitedAdverseEvent();

            LowLevelTerm llt = new LowLevelTerm();
            llt.setId(-7);
            sae12.setLowLevelTerm(llt);

            SolicitedAdverseEvent sae31 = new SolicitedAdverseEvent();
            CtcTerm ctcterm2 = new CtcTerm();
            ctcterm2.setId(-51);
            sae31.setCtcterm(ctcterm2);

            SolicitedAdverseEvent sae32 = new SolicitedAdverseEvent();
            LowLevelTerm llt2 = new LowLevelTerm();
            llt2.setId(-71);
            sae32.setLowLevelTerm(llt2);

            List<SolicitedAdverseEvent> listOfSAE1 = new LinkedList<SolicitedAdverseEvent>();
            listOfSAE1.add(sae11);
            listOfSAE1.add(sae12);

            List<SolicitedAdverseEvent> listOfSAE2 = new LinkedList<SolicitedAdverseEvent>();
            listOfSAE2.add(sae31);
            listOfSAE2.add(sae32);


            List<Arm> armlist1 = epoch1.getArms();
            List<Arm> armlist2 = epoch2.getArms();
            armlist1.get(0).setSolicitedAdverseEvents(listOfSAE1);
            armlist2.get(0).setSolicitedAdverseEvents(listOfSAE2);

            List<Epoch> listOfEpochs = new LinkedList<Epoch>();
            listOfEpochs.add(epoch1);
            listOfEpochs.add(epoch2);

            newStudy.setEpochs(listOfEpochs);
            getDao().save(newStudy);

            studyId = newStudy.getId();
            System.out.println("New Study ID:" + studyId);
            System.out.println("1 New Study ID:" + getDao().getById(studyId));
        }
        System.out.println("2 New Study ID:" + getDao().getById(studyId));
//		interruptSession();
        System.out.println("3 New Study ID:" + getDao().getById(studyId));

        {
            System.out.println("4 New Study ID:" + getDao().getById(studyId));

            System.out.println("Testing Study ID:" + studyId);
            Study newStudy = getDao().getById(studyId);
            System.out.println("Testing newStudy:" + newStudy);

            List<Epoch> listOfEpochs = newStudy.getEpochs();

            assertTrue(listOfEpochs.size() == 2);

            for (Epoch e : listOfEpochs) {
                assertTrue(e.getArms().size() == 2);
            }

            Epoch epoch1 = listOfEpochs.get(0);
            assertEquals("Name of first epoch should be Epoch1", epoch1.getName(), "Epoch1");
            assertEquals("Order of first epoch should be 1", epoch1.getEpochOrder(), new Integer(1));

            List<Arm> armList1 = epoch1.getArms();
            assertEquals("There should be two arms in Epoch1", armList1.size(), 2);

            for (int i = 1; i <= armList1.size(); i++) {
                Arm arm = armList1.get(i - 1);
                assertEquals("Epoch1 should contain arm" + i, arm.getName(), "Arm " + i);
            }

            Arm arm1 = armList1.get(0);

            List<SolicitedAdverseEvent> saelist1 = arm1.getSolicitedAdverseEvents();
            assertEquals("There should be two SAEs in Arm1 of Epoch1", saelist1.size(), 2);

        }
        assertNotNull("No ID for newly saved study", studyId);


    }

    public void testCondition() {
        Condition condition = new Condition();
        condition.setConditionName("Condition_001");
        assertNotNull(condition);
    }

    public void testLoadCtcBasedTerm() throws Exception {
        Study loaded = getDao().getById(-2);
        assertNotNull("Ctc Term List is null", loaded.getExpectedAECtcTerms());
        assertEquals("This StudyTerm is not Ctc", true, loaded.getExpectedAECtcTerms().get(0) instanceof ExpectedAECtcTerm);
        assertEquals("This term is not CtcTerm", true, loaded.getExpectedAECtcTerms().get(0).getTerm() instanceof CtcTerm);
        assertEquals("Wrong Ctc Id", 3010, loaded.getExpectedAECtcTerms().get(0).getTerm().getId().intValue());
    }

    public void testLoadCtcBasedTermWithOtherMeddra() throws Exception {
        Study loaded = getDao().getById(-2);
        assertNotNull("Ctc Term List is null", loaded.getExpectedAECtcTerms());
        assertEquals("This StudyTerm is not Ctc", true, loaded.getExpectedAECtcTerms().get(0) instanceof ExpectedAECtcTerm);
        assertEquals("This term is not CtcTerm", true, loaded.getExpectedAECtcTerms().get(0).getTerm() instanceof CtcTerm);
        assertEquals("Wrong Ctc Id", 3010, loaded.getExpectedAECtcTerms().get(0).getTerm().getId().intValue());
    }

    public void testLoadMeddraBasedTerm() throws Exception {
        Study loaded = getDao().getById(-3);
        assertNotNull("Meddra Term List is null", loaded.getExpectedAEMeddraLowLevelTerms());
        assertEquals("This term is not MedDRA", true, loaded.getExpectedAEMeddraLowLevelTerms().get(0) instanceof ExpectedAEMeddraLowLevelTerm);
        assertEquals("This term is not LowLevelTerm", true, loaded.getExpectedAEMeddraLowLevelTerms().get(0).getTerm() instanceof LowLevelTerm);
        assertEquals("Wrong Meddra Id", -11, loaded.getExpectedAEMeddraLowLevelTerms().get(0).getTerm().getId().intValue());
    }

    public void testGetStudyDesignById() throws Exception {
        Study study = getDao().getStudyDesignById(-3);
        assertNotNull(study);
        assertEquals(Boolean.TRUE, study.getRadiationTherapyType());
        assertEquals(Boolean.TRUE, study.getSurgeryTherapyType());
        assertEquals(Boolean.TRUE, study.getBehavioralTherapyType());
        assertEquals(Boolean.TRUE, study.getDrugAdministrationTherapyType());
        assertEquals(Boolean.TRUE, study.getDeviceTherapyType());
    }

    public void testGetStudyDesignByIdentifier() throws Exception {
        Identifier identifier = new Identifier();
        identifier.setId(-15);
        Study study = getDao().getStudyDesignByIdentifier(identifier);
        assertNotNull(study);
        //REVISIT
        //assertEquals(-3, study.getId().intValue());
    }
}
