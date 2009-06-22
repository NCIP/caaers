package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


public class ExcelImporter {
	
	public static void main(String[] args) {
        try {
    		
        	if(StringUtils.isBlank(args[0])){
        		System.out.println("Error::  Excel file not provided");
        		System.out.println("Useage::  ant importexcel -Dfilelocation=<absolutefilepath>");
        		return;
        	}
        	checkDsPropertiesExistence();
        	String identity = "ANONYMOUS";
            String info = "importStudy";
            DataAuditInfo.setLocal(new DataAuditInfo(identity,"localhost", new Date(), info));
            File inputFile = new File(args[0]);
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            ExcelProcessor excelProcessor = (ExcelProcessor) applicationContext.getBean("excelProcessor");
            excelProcessor.processExcel(inputFile);
        }
        catch (Exception ex) {
            System.out.println("\n Error occured: ");
            ex.printStackTrace();
        }
    }
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	/**
	 * Check if datasource.properties file exisits.
     * If it does not then create oracle and postgres templates under
     * $USER_HOME/.caaers/
	 * @throws Exception
	 */
	private static void checkDsPropertiesExistence() throws Exception{
		String userHome = System.getenv().get("HOME");
		String caaersDbTemplateDir = userHome + "/.caaers";
		File file = new File(caaersDbTemplateDir+"/datasource.properties");
		if(file.exists()){
			//datasource.properties file exists
		}else{
			File oraTemplate = getResources("classpath*:dbtemplates/oracle.datasource.properties")[0].getFile();
			File postgresTemplate = getResources("classpath*:dbtemplates/postgres.datasource.properties")[0].getFile();
			FileUtils.copyFileToDirectory(oraTemplate,new File(caaersDbTemplateDir));
			FileUtils.copyFileToDirectory(postgresTemplate,new File(caaersDbTemplateDir));
		}
	}
	
    private static String[] getConfigLocations() {
        return new String[]{
                "classpath*:gov/nih/nci/cabig/caaers/_applicationContext-excelstudyloader.xml"
        };
    }
}
