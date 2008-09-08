package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.naming.NamingException;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class XLstudyImporterTest extends AbstractTransactionalSpringContextTests {


    private XLstudyImporter XLstudyImporter;
    private static String fileName;
    StudyDao studydao;

    public XLstudyImporterTest(String testName) {
        super(testName);

    }

    public static void main(final String[] args) {
        if (StringUtils.isBlank(args[0])) {
            System.out.println("No file name specified");
            return;
        }
        fileName = args[0];
        System.out.println("file name :" + args[0]);
        junit.textui.TestRunner.run(suite());

    }

    public static Test suite() {
        TestSuite suite = new TestSuite();

        /*
           * NOTE: These tests CANNOT be run in succession because it will cause the maximum number of connections to be exceeded.
           */
        suite.addTest(new XLstudyImporterTest("testImport"));
        return suite;
    }

    public void testImport() {
        try {
        	File inputFile = null;
        	boolean runAsTestCase = true;
        	if(StringUtils.isBlank(fileName)){
        		inputFile = getResources("classpath*:/gov/nih/nci/cabig/ctms/tools/validStudy.xls")[0].getFile();
        	}else{
        		runAsTestCase = false;
        		inputFile = new File(fileName);
        	}
            
        	XLstudyImporter.importXLstudy(inputFile);
            
            if(runAsTestCase){
            	Study study = studydao.getByIdentifier(Identifier.createTemplate("8xts1c14425"));
        		study = studydao.getStudyDesignById(study.getId());
        		assertNotNull(study);
        		assertEquals("N027D", study.getShortTitle());
        		assertEquals("A Phase I Study of CCI-779 and Temozolomide in Combination with Radiation Therapy in Glioblastoma Multiforme", study.getLongTitle());
        		assertEquals("I", study.getPhaseCode());
        		assertEquals(2, study.getStudyAgents().size());
        		assertEquals(1, study.getCtepStudyDiseases().size());
        		assertEquals(12, study.getTreatmentAssignments().size());
        		assertEquals(3, study.getStudyOrganizations().size());
        		assertEquals(2, study.getStudyTherapies().size());
            }else{
            	setComplete();
                endTransaction();
            }
        }
        catch (Exception ex) {
            System.out.println("\n Error occured: ");
            ex.printStackTrace();
        }
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();    
        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        } catch (NamingException e) {
            throw new RuntimeException("", e);
        }

        SecurityTestUtils.switchToSuperuser();
        AuthorizationSwitch sw = (AuthorizationSwitch) applicationContext.getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(false);
        String identity = "ANONYMOUS";
        String info = "importStudy";
        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo.setLocal(new DataAuditInfo(identity,
                "localhost", new Date(), info));

    }
    
    private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
    
    @Override
    public String[] getConfigLocations() {

        return new String[]{
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-security.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-service.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-test-security.xml",
                "classpath*:applicationContext-test.xml"
        };
    }


    @Required
    public void setXLstudyImporter(XLstudyImporter XLstudyImporter) {
        this.XLstudyImporter = XLstudyImporter;
    }
    
    @Required
	public void setStudydao(StudyDao studydao) {
		this.studydao = studydao;
	}
    
    
}
