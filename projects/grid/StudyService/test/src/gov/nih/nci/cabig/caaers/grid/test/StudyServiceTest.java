/**
 * 
 */
package gov.nih.nci.cabig.caaers.grid.test;

import gov.nih.nci.cabig.caaers.grid.beans.Participant;
import gov.nih.nci.cabig.caaers.grid.beans.Site;
import gov.nih.nci.cabig.caaers.grid.beans.Study;
import gov.nih.nci.cabig.caaers.grid.beans.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.grid.client.StudyServiceClient;
import gov.nih.nci.cabig.caaers.grid.stubs.AssignParticipantRequest;
import gov.nih.nci.cagrid.common.Utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.namespace.QName;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.custommonkey.xmlunit.XMLTestCase;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class StudyServiceTest extends XMLTestCase {

    private static String DEFAULT_SERVICE_URL = "http://localhost:8080/wsrf/services/cagrid/StudyService";

    private StudyServiceClient client;

    /**
     * 
     */
    public StudyServiceTest() {
    }

    /**
     * @param arg0
     */
    public StudyServiceTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        try {
            this.client = new StudyServiceClient(System.getProperty("test.service.url",
                            DEFAULT_SERVICE_URL));
        } catch (Exception ex) {
            throw new RuntimeException("Error setting up: " + ex.getMessage(), ex);
        }
    }

    public void testAssignParticipant() {

        String configFileName = "src/gov/nih/nci/cabig/caaers/grid/client/client-config.wsdd";
        String inputFileName = "test/resources/AssignParticipantRequest.xml";
        QName assignmentQName = new QName("gme://caaers.caBIG/1.0/gov.nih.nci.cabig.caaers", "StudyParticipantAssignment");
        
        AssignParticipantRequest request = null;
        try {
            request = (AssignParticipantRequest) Utils.deserializeObject(new FileReader(
                            inputFileName),
                            AssignParticipantRequest.class, new FileInputStream(
                                            configFileName));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error deserializing request: " + ex.getMessage());
        }

        try {
            Study study = request.getStudy().getStudy();
            Site site = request.getSite().getSite();
            Participant participant = request.getParticipant().getParticipant();
            
            assertNotNull("Study ID is null", study.getId());
            assertNotNull("Site ID is null", site.getId());
            
            InputStream config = new FileInputStream(configFileName);
            StudyParticipantAssignment assignment = this.client.assignParticipant(request.getStudy().getStudy(), request.getParticipant()
                            .getParticipant(), request.getSite().getSite());
            StringWriter w = new StringWriter();
            Utils.serializeObject(assignment, assignmentQName, w, config);
            String xml = w.getBuffer().toString();
            assertXpathExists("/*[local-name()='StudyParticipantAssignment' and @id]", xml);
            assertXpathExists("/*[local-name()='StudyParticipantAssignment' and @gridId]", xml);
            
        } catch (Exception ex) {
            fail("Error making call: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    public static Test suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new StudyServiceTest("testAssignParticipant"));
        return suite;
    }

}
