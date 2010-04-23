package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.tools.AgentSpecificTermsImporter;
import org.dbunit.operation.DatabaseOperation;

import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import java.io.File;


/**
 * @author Ion C. Olaru
 *
 */
public class AgentSpecificTermsImporterTest extends CaaersDbTestCase {

    private static String fileName = "gov/nih/nci/cabig/caaers/asaelTest.xls";
    private AgentSpecificTermDao agentSpecificTermDao;

	protected void setUp() throws Exception {
        super.setUp();
        agentSpecificTermDao = (AgentSpecificTermDao)getDeployedApplicationContext().getBean("agentSpecificTermDao");
	}

    public void testImporter() throws Exception {
        String filePath = AgentSpecificTermsImporterTest.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(filePath);
        AgentSpecificTermsImporter im = new AgentSpecificTermsImporter(new File(filePath));
        im.importFile();
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
}