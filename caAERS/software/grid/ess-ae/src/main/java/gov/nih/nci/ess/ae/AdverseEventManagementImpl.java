package gov.nih.nci.ess.ae;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.logging.api.util.StringUtils;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventManagementImpl implements MessageSourceAware {

	private static final String INVALID_PARTICIPANT_ID_ERR = "WS_AEMS_032";
	private static final String PARTICIPANT_NOT_FOUND_ERR = "WS_AEMS_033";
	private GridToDomainObjectConverter converter;
	private MessageSource messageSource;
	private ParticipantRepository participantRepository;

	/**
	 * @param adverseEvent
	 * @param participantId
	 * @return
	 */
	public AdverseEvent initiateAdverseEvent(AdverseEvent adverseEvent,
			Id participantId) {
		Identifier id = converter.convertIdentifier(participantId);
		if (StringUtils.isBlank(id.getValue())) {
			throw new AdverseEventServiceException(INVALID_PARTICIPANT_ID_ERR,
					getMessageSource().getMessage(INVALID_PARTICIPANT_ID_ERR,
							new Object[] {}, Locale.getDefault()));
		}
		Participant participant = participantRepository.getByIdentifier(id);
		if (participant == null) {
			throw new AdverseEventServiceException(PARTICIPANT_NOT_FOUND_ERR,
					getMessageSource()
							.getMessage(PARTICIPANT_NOT_FOUND_ERR,
									new Object[] { id.getValue() },
									Locale.getDefault()));
		}
		return null;
	}

	public AdverseEvent updateAdverseEvent(AdverseEvent adverseEvent) {
		return null;
	}

	public AdverseEvent deactivateAdverseEvent(Id adverseEventIdentifier) {
		return null;
	}

	/**
	 * @return the converter
	 */
	public final GridToDomainObjectConverter getConverter() {
		return converter;
	}

	/**
	 * @param converter
	 *            the converter to set
	 */
	public final void setConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.converter = gridToDomainObjectConverter;
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

}
