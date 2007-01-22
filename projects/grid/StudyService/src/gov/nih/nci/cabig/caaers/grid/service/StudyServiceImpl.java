package gov.nih.nci.cabig.caaers.grid.service;

import gov.nih.nci.cabig.caaers.api.StudyService;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.math.BigInteger;
import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.0 // *
 */
public class StudyServiceImpl extends StudyServiceImplBase {

    private static final Log logger = LogFactory.getLog(StudyServiceImpl.class);

    public static final String STUDY_SERVICE_BEAN_NAME = "studyServiceAPI";

    private ApplicationContext ctx;

    public StudyServiceImpl() throws RemoteException {
        super();
        this.ctx = new ClassPathXmlApplicationContext(
                        new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
    }

    public gov.nih.nci.cabig.caaers.grid.beans.StudyParticipantAssignment assignParticipant(
                    gov.nih.nci.cabig.caaers.grid.beans.Study studyBean,
                    gov.nih.nci.cabig.caaers.grid.beans.Participant participantBean,
                    gov.nih.nci.cabig.caaers.grid.beans.Site siteBean) throws RemoteException {
        gov.nih.nci.cabig.caaers.grid.beans.StudyParticipantAssignment assignmentBean = new gov.nih.nci.cabig.caaers.grid.beans.StudyParticipantAssignment();
        try {
            BigInteger studyId = studyBean.getId();
            if (studyId == null) {
                throw new IllegalArgumentException("Study ID is null");
            }
            BigInteger siteId = siteBean.getId();
            if (siteId == null) {
                throw new IllegalArgumentException("Site ID is null");
            }

            Study study = new Study();
            study.setId(studyId.intValue());
            Site site = new Site();
            site.setId(siteId.intValue());

            BigInteger participantId = participantBean.getId();
            Participant participant = new Participant();
            if (participantId != null) {
                participant.setId(participantId.intValue());
            } else {
                participant.setFirstName(participantBean.getFirstName());
                participant.setMiddleName(participantBean.getMiddleName());
                participant.setLastName(participantBean.getLastName());
                participant.setMaidenName(participantBean.getMaidenName());
                participant.setInstitution(participantBean.getInstitution());
                participant.setInstitutionalPatientNumber(participantBean
                                .getInstitutionalPatientNumber());
                participant.setDateOfBirth(participantBean.getDateOfBirth());
                participant.setRace(participantBean.getRace());
                participant.setGender(participantBean.getGender());
            }

            StudyService svc = (StudyService) this.ctx.getBean(STUDY_SERVICE_BEAN_NAME);

            startTransaction();

            StudyParticipantAssignment assignment = svc.assignParticipant(study, participant, site);

            endTransaction();

            // assignment =
            // ((DefaultStudyService)svc).getStudyParticipantAssignment(assignment.getParticipant(),
            // assignment.getStudySite().getStudy());

            // assignmentBean.setId(BigInteger.valueOf(assignment.getId().longValue()));
            assignmentBean.setId(BigInteger.valueOf(-1));
        } catch (Exception ex) {
            String msg = "Error assigning participant: " + ex.getMessage();
            logger.debug(msg, ex);
            throw new RuntimeException(msg, ex);
        }
        return assignmentBean;

    }

    private void endTransaction() {
        SessionFactory fact = (SessionFactory) this.ctx.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(fact, true);
        TransactionSynchronizationManager.unbindResource(fact);
        SessionFactoryUtils.releaseSession(session, fact);
    }

    private void startTransaction() {
        SessionFactory fact = (SessionFactory) this.ctx.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(fact, true);
        TransactionSynchronizationManager.bindResource(fact, new SessionHolder(session));
    }

}
