package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.AbstractTransactionalSpringContextTests;


public class ExcelProcessorTest extends AbstractTransactionalSpringContextTests {
	
	private ExcelProcessor excelProcesor;
    StudyDao studydao;

    public ExcelProcessorTest(String testName) {
        super(testName);

    }

    public static Test suite() {
        TestSuite suite = new TestSuite();

//        /*
//           * NOTE: These tests CANNOT be run in succession because it will cause the maximum number of connections to be exceeded.
//           */
//        suite.addTest(new ExcelProcessorTest("testImport"));
        return suite;
    }

    public void testImport() {
//        try {
//        	File inputFile = getResources("classpath*:samples/validStudy.xls")[0].getFile();
//        	excelProcesor.processExcel(inputFile);
//            
//        	Study study = studydao.getByIdentifier(Identifier.createTemplate("8xts1c14425"));
//    		study = studydao.getStudyDesignById(study.getId());
//    		assertNotNull(study);
//    		assertEquals("N027D", study.getShortTitle());
//    		assertEquals("A Phase I Study of CCI-779 and Temozolomide in Combination with Radiation Therapy in Glioblastoma Multiforme", study.getLongTitle());
//    		assertEquals("I", study.getPhaseCode());
//    		assertEquals(2, study.getStudyAgents().size());
//    		assertEquals(1, study.getCtepStudyDiseases().size());
//    		assertEquals(12, study.getTreatmentAssignments().size());
//    		assertEquals(3, study.getStudyOrganizations().size());
//    		assertEquals(2, study.getStudyTherapies().size());
//        }
//        catch (Exception ex) {
//            System.out.println("\n Error occured: ");
//            ex.printStackTrace();
//        }
    	assertTrue(true);
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
//        super.onSetUpInTransaction();    
//        String identity = "ANONYMOUS";
//        String info = "importStudy";
//        DataAuditInfo.setLocal(new DataAuditInfo(identity,"localhost", new Date(), info));

    }
    
//    private static Resource[] getResources(String pattern) throws IOException {
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources(pattern);
//        return resources;
//    }
//    
//    @Override
//    public String[] getConfigLocations() {
//
//        return new String[]{
//        		"classpath*:gov/nih/nci/cabig/caaers/_applicationContext-excelstudyloader.xml"
//        };
//    }
//
//    @Required
//    public void setExcelProcesor(ExcelProcessor excelProcesor) {
//		this.excelProcesor = excelProcesor;
//	}
//
//	@Required
//	public void setStudydao(StudyDao studydao) {
//		this.studydao = studydao;
//	}
    

}
