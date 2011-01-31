/**
 * 
 */
package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException;
import gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI;
import gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException;
import gov.nih.nci.ess.safetyreporting.types.AdverseEventReportingPeriod;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import _21090.org.iso.DSET_II;
import _21090.org.iso.II;
import _21090.org.iso.TS;

/**
 * @author Denis G. Krylov
 * 
 */
public final class SafetyReportManagementImpl implements
		SafetyReportManagementI, MessageSourceAware {

	public static final String INVALID_PARTICIPANT_ID_ERR = "WS_AEMS_032";
	public static final String INVALID_STUDY_ID_ERR = "WS_AEMS_034";
	public static final String PARTICIPANT_NOT_FOUND_ERR = "WS_AEMS_033";
	public static final String STUDY_NOT_FOUND_ERR = "WS_AEMS_035";
	public static final String PARTICIPANT_NOT_ASSIGNED_ERR = "WS_AEMS_037";
	public static final String INVALID_COURSE_START_DATE_ERR = "WS_AEMS_036";
	public static final String NO_REPORTING_PERIOD_ERR = "WS_AEMS_038";
	private static final Log log = LogFactory
			.getLog(SafetyReportManagementImpl.class);
	private static final String TS_DATETIME_PATTERN = "yyyyMMddHHmmss";

	private MessageSource messageSource;
	private ParticipantRepository participantRepository;
	private StudyRepository studyRepository;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI
	 * #associateAdverseEventsToSafetyReport(ess.caaers.nci.nih.gov.Id,
	 * _21090.org.iso.DSET_II)
	 */
	public SafetyReportVersion associateAdverseEventsToSafetyReport(
			Id safetyReportId, DSET_II adverseEventIds) throws RemoteException,
			SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param id
	 * @return
	 */
	private Identifier convertIdentifier(II id) {
		Identifier identifier = new Identifier();
		if (id != null) {
			identifier.setValue(id.getExtension());
		}
		return identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI
	 * #dissociateAdverseEventsFromSafetyReport(ess.caaers.nci.nih.gov.Id,
	 * _21090.org.iso.DSET_II)
	 */
	public SafetyReportVersion dissociateAdverseEventsFromSafetyReport(
			Id safetyReportId, DSET_II adverseEventIds) throws RemoteException,
			SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param participantId
	 * @return
	 * @throws SafetyReportingServiceException
	 * @throws NoSuchMessageException
	 */
	private Participant getParticipantByPrimaryId(Id participantId)
			throws SafetyReportingServiceException, NoSuchMessageException {
		Identifier pid = convertIdentifier(participantId);
		if (StringUtils.isBlank(pid.getValue())) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					INVALID_PARTICIPANT_ID_ERR, getMessageSource().getMessage(
							INVALID_PARTICIPANT_ID_ERR, new Object[] {},
							Locale.getDefault()));
		}
		Participant participant = participantRepository.getByIdentifier(pid);
		if (participant == null) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					PARTICIPANT_NOT_FOUND_ERR, getMessageSource().getMessage(
							PARTICIPANT_NOT_FOUND_ERR,
							new Object[] { pid.getValue() },
							Locale.getDefault()));
		}
		return participant;
	}

	/**
	 * @return the participantRepository
	 */
	public final ParticipantRepository getParticipantRepository() {
		return participantRepository;
	}

	/**
	 * @param studyId
	 * @return
	 * @throws SafetyReportingServiceException
	 * @throws NoSuchMessageException
	 */
	private Study getStudyByPrimaryId(II studyId)
			throws SafetyReportingServiceException, NoSuchMessageException {
		Identifier sid = convertIdentifier(studyId);
		if (StringUtils.isBlank(sid.getValue())) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					INVALID_STUDY_ID_ERR, getMessageSource().getMessage(
							INVALID_STUDY_ID_ERR, new Object[] {},
							Locale.getDefault()));
		}
		Study study = studyRepository.getByIdentifier(sid);
		if (study == null) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					STUDY_NOT_FOUND_ERR, getMessageSource().getMessage(
							STUDY_NOT_FOUND_ERR,
							new Object[] { sid.getValue() },
							Locale.getDefault()));
		}
		return study;
	}

	/**
	 * @return the studyRepository
	 */
	public final StudyRepository getStudyRepository() {
		return studyRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI
	 * #initiateSafetyReport(ess.caaers.nci.nih.gov.Id,
	 * ess.caaers.nci.nih.gov.Id, ess.caaers.nci.nih.gov.Id,
	 * _21090.org.iso.DSET_II, _21090.org.iso.DSET_II,
	 * gov.nih.nci.ess.safetyreporting.types.AdverseEventReportingPeriod)
	 */
	public SafetyReportVersion initiateSafetyReport(Id studyId, Id subjectId,
			Id patientId, DSET_II adverseEventIds, DSET_II problemIds,
			AdverseEventReportingPeriod adverseEventReportingPeriod)
			throws RemoteException, SafetyReportingServiceException {
		Study study = getStudyByPrimaryId(studyId);
		Participant participant = getParticipantByPrimaryId(subjectId);
		StudyParticipantAssignment assignment = getStudyParticipantAssignment(
				participant, study);

		Date courseStartDate = convertToDate(adverseEventReportingPeriod
				.getStartDate());
		if (courseStartDate == null) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					INVALID_COURSE_START_DATE_ERR, getMessageSource()
							.getMessage(INVALID_COURSE_START_DATE_ERR,
									new Object[] {}, Locale.getDefault()));
		}
		gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod period = getReportingPeriod(
				courseStartDate, assignment);

		return null;
	}

	/**
	 * @param courseStartDate
	 * @param assignment
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod getReportingPeriod(
			Date courseStartDate, StudyParticipantAssignment assignment)
			throws NoSuchMessageException {
		gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod period = assignment
				.getReportingPeriod(courseStartDate);
		if (period == null) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					NO_REPORTING_PERIOD_ERR, getMessageSource().getMessage(
							NO_REPORTING_PERIOD_ERR, new Object[] {},
							Locale.getDefault()));
		}
		return period;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public final void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
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
	 * @param studyRepository
	 *            the studyRepository to set
	 */
	public final void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI
	 * #updateAdverseEventInformationInSafetyReport(ess.caaers.nci.nih.gov.Id,
	 * ess.caaers.nci.nih.gov.AdverseEvent)
	 */
	public SafetyReportVersion updateAdverseEventInformationInSafetyReport(
			Id safetyReportId, AdverseEvent adverseEvent)
			throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param participant
	 * @param study
	 * @throws SafetyReportingServiceException
	 * @throws NoSuchMessageException
	 */
	private StudyParticipantAssignment getStudyParticipantAssignment(
			Participant participant, Study study)
			throws SafetyReportingServiceException, NoSuchMessageException {
		StudyParticipantAssignment assignment = studyParticipantAssignmentDao
				.getAssignment(participant, study);
		if (assignment == null) {
			throw new gov.nih.nci.ess.sr.SafetyReportingServiceException(
					PARTICIPANT_NOT_ASSIGNED_ERR, getMessageSource()
							.getMessage(PARTICIPANT_NOT_ASSIGNED_ERR,
									new Object[] {}, Locale.getDefault()));

		}
		return assignment;
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

	public static Date convertToDate(TS tsDateTime) {
		try {
			if (tsDateTime != null && tsDateTime.getNullFlavor() == null) {
				String value = tsDateTime.getValue();
				if (value != null) {
					return DateUtils.parseDate(value,
							new String[] { TS_DATETIME_PATTERN });
				}
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
