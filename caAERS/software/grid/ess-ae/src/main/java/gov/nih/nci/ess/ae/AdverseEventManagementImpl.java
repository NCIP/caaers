package gov.nih.nci.ess.ae;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.logging.api.util.StringUtils;
import _21090.org.iso.TSDateTime;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventManagementImpl implements MessageSourceAware {

	private static final String INVALID_PARTICIPANT_ID_ERR = "WS_AEMS_032";
	private static final String PARTICIPANT_NOT_FOUND_ERR = "WS_AEMS_033";
	private static final String INVALID_STUDY_ID_ERR = "WS_AEMS_034";
	private static final String STUDY_NOT_FOUND_ERR = "WS_AEMS_035";
	private static final String INVALID_COURSE_START_DATE_ERR = "WS_AEMS_036";
	private static final String PARTICIPANT_NOT_ASSIGNED_ERR = "WS_AEMS_037";
	private static final String NO_REPORTING_PERIOD_ERR = "WS_AEMS_038";
	private GridToDomainObjectConverter gridToDomainConverter;
	private DomainToGridObjectConverter domainToGridConverter;
	private MessageSource messageSource;
	private ParticipantRepository participantRepository;
	private StudyRepository studyRepository;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;

	/**
	 * @param adverseEvent
	 * @param participantId
	 * @return
	 */
	public AdverseEvent initiateAdverseEvent(AdverseEvent adverseEvent,
			Id participantId, Id studyId, TSDateTime courseStartISODate) {
		Participant participant = getParticipantByPrimaryId(participantId);
		Study study = getStudyByPrimaryId(studyId);
		Date courseStartDate = gridToDomainConverter
				.convertToDate(courseStartISODate);
		if (courseStartDate == null) {
			throw new AdverseEventServiceException(
					INVALID_COURSE_START_DATE_ERR, getMessageSource()
							.getMessage(INVALID_COURSE_START_DATE_ERR,
									new Object[] {}, Locale.getDefault()));
		}
		StudyParticipantAssignment assignment = getStudyParticipantAssignment(participant, study);
		AdverseEventReportingPeriod period = getReportingPeriod(courseStartDate, assignment);
		gov.nih.nci.cabig.caaers.domain.AdverseEvent ae = gridToDomainConverter
				.convertAdverseEvent(adverseEvent);
		ae.setReportingPeriod(period);
		period.addAdverseEvent(ae);
		adverseEventReportingPeriodDao.save(period);		
		return domainToGridConverter.convertAdverseEvent(ae);
	}

	/**
	 * @param courseStartDate
	 * @param assignment
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private AdverseEventReportingPeriod getReportingPeriod(Date courseStartDate,
			StudyParticipantAssignment assignment)
			throws AdverseEventServiceException, NoSuchMessageException {
		AdverseEventReportingPeriod period = assignment.getReportingPeriod(courseStartDate);
		if (period==null) {
			throw new AdverseEventServiceException(
					NO_REPORTING_PERIOD_ERR, getMessageSource()
							.getMessage(NO_REPORTING_PERIOD_ERR,
									new Object[] {}, Locale.getDefault()));
		}
		return period;
	}

	/**
	 * @param participant
	 * @param study
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private StudyParticipantAssignment getStudyParticipantAssignment(Participant participant,
			Study study) throws AdverseEventServiceException,
			NoSuchMessageException {
		StudyParticipantAssignment assignment = studyParticipantAssignmentDao
				.getAssignment(participant, study);
		if (assignment == null) {
			throw new AdverseEventServiceException(
					PARTICIPANT_NOT_ASSIGNED_ERR, getMessageSource()
							.getMessage(PARTICIPANT_NOT_ASSIGNED_ERR,
									new Object[] {}, Locale.getDefault()));

		}
		return assignment;
	}

	/**
	 * @param studyId
	 * @return
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private Study getStudyByPrimaryId(Id studyId)
			throws AdverseEventServiceException, NoSuchMessageException {
		Identifier sid = gridToDomainConverter.convertIdentifier(studyId);
		if (StringUtils.isBlank(sid.getValue())) {
			throw new AdverseEventServiceException(INVALID_STUDY_ID_ERR,
					getMessageSource().getMessage(INVALID_STUDY_ID_ERR,
							new Object[] {}, Locale.getDefault()));
		}
		Study study = studyRepository.getByIdentifier(sid);
		if (study == null) {
			throw new AdverseEventServiceException(STUDY_NOT_FOUND_ERR,
					getMessageSource().getMessage(STUDY_NOT_FOUND_ERR,
							new Object[] { sid.getValue() },
							Locale.getDefault()));
		}
		return study;
	}

	/**
	 * @param participantId
	 * @return
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private Participant getParticipantByPrimaryId(Id participantId)
			throws AdverseEventServiceException, NoSuchMessageException {
		Identifier pid = gridToDomainConverter.convertIdentifier(participantId);
		if (StringUtils.isBlank(pid.getValue())) {
			throw new AdverseEventServiceException(INVALID_PARTICIPANT_ID_ERR,
					getMessageSource().getMessage(INVALID_PARTICIPANT_ID_ERR,
							new Object[] {}, Locale.getDefault()));
		}
		Participant participant = participantRepository.getByIdentifier(pid);
		if (participant == null) {
			throw new AdverseEventServiceException(PARTICIPANT_NOT_FOUND_ERR,
					getMessageSource().getMessage(PARTICIPANT_NOT_FOUND_ERR,
							new Object[] { pid.getValue() },
							Locale.getDefault()));
		}
		return participant;
	}

	public AdverseEvent updateAdverseEvent(AdverseEvent adverseEvent) {
		return null;
	}

	public AdverseEvent deactivateAdverseEvent(Id adverseEventIdentifier) {
		return null;
	}

	/**
	 * @return the gridToDomainConverter
	 */
	public final GridToDomainObjectConverter getGridToDomainConverter() {
		return gridToDomainConverter;
	}

	/**
	 * @param gridToDomainConverter
	 *            the gridToDomainConverter to set
	 */
	public final void setGridToDomainConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainConverter = gridToDomainObjectConverter;
	}

	/**
	 * @return the messageSource
	 */
	public final MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public final void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the participantRepository
	 */
	public final ParticipantRepository getParticipantRepository() {
		return participantRepository;
	}

	/**
	 * @param participantRepository
	 *            the participantRepository to set
	 */
	public final void setParticipantRepository(
			ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	/**
	 * @return the domainToGridConverter
	 */
	public final DomainToGridObjectConverter getDomainToGridConverter() {
		return domainToGridConverter;
	}

	/**
	 * @param domainToGridConverter
	 *            the domainToGridConverter to set
	 */
	public final void setDomainToGridConverter(
			DomainToGridObjectConverter domainToGridConverter) {
		this.domainToGridConverter = domainToGridConverter;
	}

	/**
	 * @return the studyRepository
	 */
	public final StudyRepository getStudyRepository() {
		return studyRepository;
	}

	/**
	 * @param studyRepository
	 *            the studyRepository to set
	 */
	public final void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

	/**
	 * @return the studyParticipantAssignmentDao
	 */
	public final StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
		return studyParticipantAssignmentDao;
	}

	/**
	 * @param studyParticipantAssignmentDao
	 *            the studyParticipantAssignmentDao to set
	 */
	public final void setStudyParticipantAssignmentDao(
			StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
		this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
	}

	/**
	 * @return the adverseEventReportingPeriodDao
	 */
	public final AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}

	/**
	 * @param adverseEventReportingPeriodDao the adverseEventReportingPeriodDao to set
	 */
	public final void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}

}
