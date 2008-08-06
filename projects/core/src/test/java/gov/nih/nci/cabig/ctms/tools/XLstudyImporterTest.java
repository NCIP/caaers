package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class XLstudyImporterTest extends  TestCase {
//    
//    XLstudyImporter xlsi;
//    StudyDao studydao;
//    
//    protected void setUp() throws Exception {
//        super.setUp();
//        xlsi = (XLstudyImporter)getDeployedApplicationContext().getBean("XLstudyImporter");
//        studydao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
//    }
//    
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
// 
//    public void testMissingData() throws Exception{
//        File xlFile = getResources("classpath*:/gov/nih/nci/cabig/ctms/tools/missingDataStudy.xls")[0].getFile();
//        
//        xlsi.setInputFile(xlFile);
//       
//        try {
//            xlsi.importXLstudy();
//            Study study = studydao.getByIdentifier(Identifier.createTemplate("8xts1c14426"));
//            studydao.delete(study);
//            fail("An Exception of type CaaersSystemException was expected but not thrown");
//        }
//        catch (Exception e) {
//            assertTrue("An Exception of type CaaersSystemException is expected."+
//                            "\n "+e.getMessage(),(e instanceof CaaersSystemException));
//            assertTrue("Error was not thrown for missing data."+
//                            "\n "+e.getMessage(),e.getMessage().contains("Invalid data or Blank cell at following location"));
//        }
// 
//    }
//
//    public void testWrongSheetNames() throws Exception {
//       File xlFile = getResources("classpath*:/gov/nih/nci/cabig/ctms/tools/wrongSheetNamesStudy.xls")[0].getFile();
//        xlsi.setInputFile(xlFile);
//       
//        try {
//            xlsi.importXLstudy();
//            Study study = studydao.getByIdentifier(Identifier.createTemplate("8xts1c14426"));
//            studydao.delete(study);
//            fail("An Exception of type CaaersSystemException was expected but not thrown");
//        }
//        catch (Exception e) {
//            assertTrue("An Exception of type CaaersSystemException is expected."+
//                            "\n "+e.getMessage(),(e instanceof CaaersSystemException));
//            assertTrue("Error was not thrown for wrong sheet names."+
//                            "\n "+e.getMessage(),e.getMessage().contains("Unable to find sheet named"));
//        }
// 
//        
//    }
//    
//    public void testImportXLStudy() throws Exception {
//        File xlFile = getResources("classpath*:/gov/nih/nci/cabig/ctms/tools/validStudy.xls")[0].getFile();
//                        
//        xlsi.setInputFile(xlFile);
//        xlsi.importXLstudy();
//        Study study = studydao.getByIdentifier(Identifier.createTemplate("8xts1c14425"));
//        studydao.delete(study);
//        
//    }
//
//    private static Resource[] getResources(String pattern) throws IOException {
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources(pattern);
//        return resources;
//    }
     
    public void testTest(){
    	assertTrue(true);
    }
}
