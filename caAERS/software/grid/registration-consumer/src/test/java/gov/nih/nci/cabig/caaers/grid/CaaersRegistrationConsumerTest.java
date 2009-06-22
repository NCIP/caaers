/**
 *
 */
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cabig.ccts.domain.Registration;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class CaaersRegistrationConsumerTest extends CaaersDbTestCase {
    private String clientConfigFile;

    private String registrationResourceName;

    @Override
    protected void setUp() throws Exception {
    	super.setUp();
    	//avoided calling super.setup
        setUpAuthorization();
        setUpAuditing();
        // TODO Auto-generated method stub
        this.clientConfigFile = "/gov/nih/nci/ccts/grid/client/client-config.wsdd";
        this.registrationResourceName = "/SampleRegistrationMessage.xml"; // "C:/devtools/workspace/REF-RegistrationConsumer/test/resources/SampleRegistrationMessage.xml";
        // this.serviceUrl = "http://localhost:8080/wsrf/services/cagrid/RegistrationConsumer";

    }

    @Override
    protected void tearDown() throws Exception {
       // TODO Auto-generated method stub
       // super.tearDown();
    }

    public CaaersRegistrationConsumer getRegistrationConsumer() {
        CaaersRegistrationConsumer consumer = new CaaersRegistrationConsumer();
        OpenSessionInViewInterceptor os = (OpenSessionInViewInterceptor) getDeployedApplicationContext()
                        .getBean("openSessionInViewInterceptor");
        consumer.setOpenSessionInViewInterceptor(os);
        consumer.setAuthorizationSwitch((AuthorizationSwitch) getDeployedApplicationContext()
                        .getBean("authorizationSwitch"));
        consumer.setConfigurationProperty((ConfigProperty) getDeployedApplicationContext().getBean(
                        "configurationProperty"));
        consumer.setOrganizationDao((OrganizationDao) getDeployedApplicationContext().getBean(
                        "organizationDao"));
        consumer.setParticipantDao((ParticipantDao) getDeployedApplicationContext().getBean(
                        "participantDao"));
        consumer.setStudyDao((StudyDao) getDeployedApplicationContext().getBean("studyDao"));
        consumer
                        .setStudyParticipantAssignmentAspect((StudyParticipantAssignmentAspect) getDeployedApplicationContext()
                                        .getBean("studyParticipantAssignmentAspect"));
        consumer
                        .setStudyParticipantAssignmentDao((StudyParticipantAssignmentDao) getDeployedApplicationContext()
                                        .getBean("studyParticipantAssignmentDao"));
        consumer.setAuditHistoryRepository((AuditHistoryRepository) getDeployedApplicationContext()
                        .getBean("auditHistoryRepository"));
        consumer.setRegistrationConsumerGridServiceUrl("/pages/task");
        consumer.setRollbackInterval(1);
        return consumer;
    }

    public void testRegistrationLocal() throws Exception {
        System.out.println("***********************************************");
        try {
            Registration reg = obtainRegistrationDTO();
            CaaersRegistrationConsumer consumer = getRegistrationConsumer();
            consumer.register(reg);

        } catch (Exception e) {
            e.printStackTrace();
           // throw e;
        }
    }

    public void testRollbackLocal() throws Exception {
        try {
            Registration reg = obtainRegistrationDTO();
            CaaersRegistrationConsumer consumer = getRegistrationConsumer();
            consumer.rollback(reg);

        } catch (Exception e) {
            e.printStackTrace();
            //throw e;
        }
    }

    public Registration obtainRegistrationDTO() throws Exception {
        Registration registration = null;
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream(
                            registrationResourceName));
            InputStream fis = getClass().getResourceAsStream(clientConfigFile);
            registration = (Registration) Utils.deserializeObject(reader, Registration.class, fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return registration;
    }

}
