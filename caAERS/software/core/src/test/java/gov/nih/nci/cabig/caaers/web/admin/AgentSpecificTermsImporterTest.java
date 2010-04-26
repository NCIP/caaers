package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.util.List;


/**
 * @author Ion C. Olaru
 *
 */
public class AgentSpecificTermsImporterTest extends CaaersDbTestCase {

    private static String fileName = "gov/nih/nci/cabig/caaers/asaelTest.xls";
    private AgentSpecificTermDao agentSpecificTermDao;
    private AgentDao agentDao;
    private TerminologyRepository tRepository;
    private AgentSpecificAdverseEventListService asaelService;
    private StudyDao studyDao;
    private StudyAgentDao studyAgentDao;

	protected void setUp() throws Exception {
        super.setUp();
        agentSpecificTermDao = (AgentSpecificTermDao)getDeployedApplicationContext().getBean("agentSpecificTermDao");
        agentDao = (AgentDao)getDeployedApplicationContext().getBean("agentDao");
        tRepository = (TerminologyRepository)getDeployedApplicationContext().getBean("terminologyRepository");
        agentSpecificTermDao = (AgentSpecificTermDao)getDeployedApplicationContext().getBean("agentSpecificTermDao");
        asaelService = (AgentSpecificAdverseEventListService)getDeployedApplicationContext().getBean("agentSpecificAdverseEventListService");
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
        studyAgentDao = (StudyAgentDao)getDeployedApplicationContext().getBean("studyAgentDao");
	}

    public void testImporter() throws Exception {
        String filePath = AgentSpecificTermsImporterTest.class.getClassLoader().getResource(fileName).getPath();
        AgentSpecificTermsImporter im = new AgentSpecificTermsImporter(new File(filePath));
        im.setAgentDao(agentDao);
        im.setTerminologyRepository(tRepository);
        im.setAgentSpecificTermDao(agentSpecificTermDao);
        im.setAsaelService(asaelService);
        im.setStudyDao(studyDao);
        im.setStudyAgentDao(studyAgentDao);
        im.importFile();
        List<AgentSpecificTerm> l = agentSpecificTermDao.getAll();
        Study s = studyDao.getById(-2);
        assertEquals(1, s.getExpectedAECtcTerms().size());
        assertEquals(4, l.size());
    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }


    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

*/
}