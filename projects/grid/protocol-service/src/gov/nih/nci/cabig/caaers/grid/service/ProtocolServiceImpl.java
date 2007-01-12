package gov.nih.nci.cabig.caaers.grid.service;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.grid.beans.IdentifierType;
import gov.nih.nci.cabig.caaers.grid.cmd.DuplicateAssignmentException;
import gov.nih.nci.cabig.caaers.grid.cmd.RegisterParticipantCommand;
import gov.nih.nci.cabig.caaers.grid.stubs.types.InvalidRegistration;
import gov.nih.nci.cabig.caaers.grid.stubs.types.RegistrationError;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class ProtocolServiceImpl extends ProtocolServiceImplBase {

    // TODO: come up with global identifier naming strategy
    private static final String IDENTIFIER_TYPE = "caaers";

    private static Log logger = LogFactory.getLog(ProtocolServiceImpl.class);

    private ApplicationContext ctx;

    public ProtocolServiceImpl() throws RemoteException {
        super();
        this.ctx = new ClassPathXmlApplicationContext(
                        new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
    }

    public IdentifierType registerParticipant(
                    gov.nih.nci.cabig.caaers.grid.beans.RegistrationType registration)
                    throws RemoteException, InvalidRegistration, RegistrationError {

        IdentifierType ident = new IdentifierType();
        try {
            // Populate participant
            Participant p = new Participant();

            // Populate assignment
            StudyParticipantAssignment a = new StudyParticipantAssignment();
            a.setDateOfEnrollment(new Date());

            // TODO: locate the site based on input
            SiteDao sDao = (SiteDao) this.ctx.getBean("siteDao");
            StudySite s = sDao.getDefaultSite().getStudySites().get(0);

            RegisterParticipantCommand cmd = (RegisterParticipantCommand) this.ctx
                            .getBean("registerParticipantCommand");
            cmd.setParticipant(p);
            cmd.setStudySite(s);
            cmd.setStudyParticipantAssignment(a);

            cmd.execute();

            // TODO: this should be the identifier of the assignment

            ident.setType(IDENTIFIER_TYPE);
            ident.setIsprimary(false);
            ident.setSource(IDENTIFIER_TYPE);
            ident.setValue(p.getId().toString());

        } catch (DuplicateAssignmentException ex) {
            // TODO: change names of these faults and allow to take args
            logger.error("Duplicate assignment.", ex);
            throw new InvalidRegistration();
        } catch (Exception ex) {
            logger.error("Error encountered: " + ex.getMessage(), ex);
            throw new RegistrationError();
        }
        return ident;
    }

	
}
