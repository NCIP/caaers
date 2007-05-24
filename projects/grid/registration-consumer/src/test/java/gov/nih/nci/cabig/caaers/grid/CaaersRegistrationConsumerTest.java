/**
 * 
 */
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.ctms.common.RegistrationConsumer;
import gov.nih.nci.cabig.ctms.grid.RegistrationType;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import gov.nih.nci.cagrid.common.Utils;
import static org.easymock.EasyMock.expect;
import org.easymock.IArgumentMatcher;
import static org.easymock.classextension.EasyMock.*;
import org.globus.wsrf.encoding.DeserializationException;
import org.springframework.context.ApplicationContext;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class CaaersRegistrationConsumerTest extends CaaersTestCase {
    private String clientConfigFile;
    private String registrationResourceName;
    private String serviceUrl;

    private RegistrationConsumer consumer;
    private ApplicationContext applicationContext;
    private StudyService studyService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.clientConfigFile = System.getProperty("caaers.test.clientConfigFile",
            "/gov/nih/nci/cabig/ctms/client/client-config.wsdd");
        this.registrationResourceName = System.getProperty("caaers.test.sampleRegistrationFile",
            "/SampleRegistrationMessage.xml");
        this.serviceUrl = System.getProperty("caaers.test.serviceUrl",
            "http://localhost:8080/wsrf/services/cagrid/RegistrationConsumer");

        applicationContext = registerMockFor(ApplicationContext.class);
        consumer = new CaaersRegistrationConsumer() {
            @Override protected synchronized ApplicationContext getApplicationContext() { return applicationContext; }
        };
        studyService = registerMockFor(StudyService.class);
        expect(applicationContext.getBean(CaaersRegistrationConsumer.STUDY_SERVICE_BEAN_NAME))
            .andReturn(studyService).anyTimes();
    }

    public void testCreateRegistrationLocal() throws Exception {
        expect(studyService.assignParticipant(studyMatcher(), participantMatcher(), siteMatcher(), eq("studyParticipantIdentifier0")))
            .andReturn(new StudyParticipantAssignment());

        RegistrationType reg = getRegistration();

        replayMocks();
        consumer.register(reg);
        verifyMocks();
    }

    private static class TestRegistrationMatcher<T extends MutableDomainObject> implements IArgumentMatcher {
        private String name;
        private String expectedGridId;

        protected TestRegistrationMatcher(String name, String expectedGridId) {
            this.name = name;
            this.expectedGridId = expectedGridId;
        }

        public final boolean matches(Object argument) {
            T actual = (T) argument;
            assertMatches(actual);
            return true;
        }

        protected void assertMatches(T actual) {
            assertEquals("Wrong gridId", expectedGridId, actual.getGridId());
        }

        public final void appendTo(StringBuffer buffer) {
            buffer.append("Test registration's ").append(name);
        }
    }

    private static Study studyMatcher() {
        reportMatcher(new TestRegistrationMatcher<Study>("study", "gridStudy"));
        return null;
    }

    private static Site siteMatcher() {
        reportMatcher(new TestRegistrationMatcher<Site>("site", "gridSite"));
        return null;
    }

    private static Participant participantMatcher() {
        reportMatcher(new TestRegistrationMatcher<Participant>("particpant", "gridParticipant") {
            @Override public void assertMatches(Participant actual) {
                super.assertMatches(actual);
                assertEquals("Wrong gender", "administrativeGenderCode0", actual.getGender());
                assertEquals("Wrong first name", "firstName0", actual.getFirstName());
                assertEquals("Wrong last name", "lastName0", actual.getLastName());
                assertEquals("Wrong number of identifiers", 3, actual.getIdentifiers().size());
            }
        });
        return null;
    }

    /*
     * TODO: this test needs to be able to run in isolation (i.e., w/o another server) somehow.
    public void testCreateRegistrationRemote() {
        RegistrationType reg = getRegistration();
        try {
            RegistrationConsumerClient client = new RegistrationConsumerClient(this.serviceUrl);
            client.register(reg);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error making call: " + ex.getMessage());
        }
    }
    */

    private RegistrationType getRegistration() throws DeserializationException, SAXException {
        RegistrationType reg = null;
        InputStream config = getClass().getResourceAsStream(clientConfigFile);
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(registrationResourceName));
        reg = (RegistrationType) Utils
                        .deserializeObject(reader, RegistrationType.class, config);
        return reg;
    }
}
