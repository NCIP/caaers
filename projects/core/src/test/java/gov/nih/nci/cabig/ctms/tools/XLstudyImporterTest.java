package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.AbstractTransactionalSpringContextTests;

import javax.naming.NamingException;
import java.io.File;
import java.util.Date;

public class XLstudyImporterTest extends AbstractTransactionalSpringContextTests {


    private XLstudyImporter XLstudyImporter;
    private static String fileName;

    public XLstudyImporterTest(String testName) {
        super(testName);

    }

    public static void main(final String[] args) {
        if (StringUtils.isBlank(args[0])) {
            System.out.println("no file name specified");
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
        // suite.addTest(new PSCAdverseEventConsumerTest("testCreateNotificationLocal"));
        return suite;
    }

    public void testImport() {
        try {
            File inputFile = new File(fileName);

            XLstudyImporter.importXLstudy(inputFile);
            setComplete();
            endTransaction();
        }
        catch (Exception ex) {
            System.out.println("\n Error occured: ");
            ex.printStackTrace();
        }


    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();    //To change body of overridden methods use File | Settings | File Templates.
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

    @Override
    public String[] getConfigLocations() {

//        return new String[]{
//                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
//                "classpath*:applicationContext-test.xml"
//        };
        return new String[]{
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
                "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
                //"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml",
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
}
