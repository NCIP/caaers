/**
 *
 */
package gov.nih.nci.cabig.caaers.grid;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.ctms.common.RegistrationConsumer;
import gov.nih.nci.cabig.ctms.grid.IdentifierType;
import gov.nih.nci.cabig.ctms.grid.ParticipantType;
import gov.nih.nci.cabig.ctms.grid.RegistrationType;
import gov.nih.nci.cabig.ctms.stubs.types.InvalidRegistration;
import gov.nih.nci.cabig.ctms.stubs.types.RegistrationFailed;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class CaaersRegistrationConsumer implements RegistrationConsumer {
    private static final Log logger = LogFactory.getLog(CaaersRegistrationConsumer.class);

    public static final String STUDY_SERVICE_BEAN_NAME = "studyServiceAPI";

    private ApplicationContext applicationContext;

    protected synchronized ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
        }
        return applicationContext;
    }

    /*
     * (non-Javadoc)
     *
     * @see gov.nih.nci.cabig.ctms.common.RegistrationConsumer#createRegistration(gov.nih.nci.cabig.ctms.grid.RegistrationType)
     */
    public void register(RegistrationType registration) throws RemoteException, InvalidRegistration, RegistrationFailed {
        Study study = new Study();
        study.setGridId(registration.getStudyGridId());

        Participant participant = createParticipant(registration);

        Organization organization = new Organization();
        organization.setGridId(registration.getHealthCareSiteGridId());

        String registrationGridId = registration.getStudyParticipantIdentifier();

        StudyService svc = (StudyService) getApplicationContext().getBean(STUDY_SERVICE_BEAN_NAME);
        StudyParticipantAssignment assignment = svc.assignParticipant(study, participant, organization, registrationGridId);
        logger.debug("Created assignment " + assignment.getGridId());
    }

    private Participant createParticipant(RegistrationType registration) {
        ParticipantType partBean = registration.getParticipant();
        Participant participant = new Participant();
        participant.setGridId(partBean.getParticipantGridId());
        participant.setGender(partBean.getAdministrativeGenderCode());
        participant.setDateOfBirth(partBean.getBirthDate());
        participant.setEthnicity(partBean.getEthnicGroupCode());
        participant.setFirstName(partBean.getFirstName());
        participant.setLastName(partBean.getLastName());
        participant.setRace(partBean.getRaceCode());

        if (partBean.getIdentifier() != null) {
            List<Identifier> identifiersList =  new ArrayList<Identifier>();
            for (IdentifierType identifierType : partBean.getIdentifier()) {
                Identifier identifier = new Identifier();
                identifier.setSource(identifierType.getSource());
                identifier.setType(identifierType.getType());
                identifier.setValue(identifierType.getValue());

                identifiersList.add(identifier);
            }
            participant.setIdentifiers(identifiersList);
        }
        return participant;
    }

    public ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
