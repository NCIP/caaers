/**
 * 
 */
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.ctms.common.RegistrationConsumer;
import gov.nih.nci.cabig.ctms.grid.ParticipantType;
import gov.nih.nci.cabig.ctms.grid.RegistrationType;

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

    private ApplicationContext ctx;

    public CaaersRegistrationConsumer() {
        this.ctx = new ClassPathXmlApplicationContext(
                        new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.ctms.common.RegistrationConsumer#createRegistration(gov.nih.nci.cabig.ctms.grid.RegistrationType)
     */
    public void createRegistration(RegistrationType registration) {

        Study study = new Study();
        Participant participant = new Participant();
        Site site = new Site();
        StudyService svc = (StudyService) this.ctx.getBean(STUDY_SERVICE_BEAN_NAME);
        
        site.setGridId(registration.getHealthCareSiteGridId());
        logger.debug("Site gridId = " + site.getGridId());
        study.setGridId(registration.getStudyGridId());
        
        ParticipantType partBean = registration.getParticipant();
        participant.setGridId(partBean.getParticipantGridId());
        participant.setGender(partBean.getAdministrativeGenderCode());
        participant.setDateOfBirth(partBean.getBirthDate());
        participant.setEthnicity(partBean.getEthnicGroupCode());
        participant.setFirstName(partBean.getFirstName());
        participant.setLastName(partBean.getLastName());
        participant.setRace(partBean.getRaceCode());
        
        StudyParticipantAssignment assignment = svc.assignParticipant(study, participant, site);
        logger.debug("Created assignment " + assignment.getGridId());
    }

}
