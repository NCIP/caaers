package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryCleaner;
import gov.nih.nci.cabig.caaers.tools.CaaersDataSourcePropertiesFactoryBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;

public abstract class RulesTestCase extends AbstractNoSecurityTestCase {
    CaaersDataSourcePropertiesFactoryBean propertiesBean;

    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        propertiesBean = CaaersDataSourcePropertiesFactoryBean.getLoadedInstance();
        String url = propertiesBean.getProperties().getProperty("rules.repository");
        new RepositoryCleaner(url);
    }

    public abstract Class<? extends TestCase> getTestClass();

    public String getFileContext(String fileName) throws Exception {
    	
        File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/rules/deploy/" + fileName).getFile();
        BufferedReader ds = new BufferedReader(new FileReader(testFile));
        String line = null;
        StringBuffer xml = new StringBuffer();
        while ((line = ds.readLine()) != null) {
            xml.append(line);
        }
        assertTrue("Content of the xml should not be null", xml.toString().length() > 0);
        return xml.toString();
    }
}
