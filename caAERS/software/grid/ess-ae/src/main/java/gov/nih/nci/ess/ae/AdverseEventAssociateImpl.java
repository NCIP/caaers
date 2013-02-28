/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import ess.caaers.nci.nih.gov.StudyProtocolVersion;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * @author Denis G. Krylov
 *
 */
public class AdverseEventAssociateImpl implements MessageSourceAware {
	
	private MessageSource messageSource;
	private GridToDomainObjectConverter gridToDomainConverter;
	private DomainToGridObjectConverter domainToGridConverter;	
	private StudyRepository studyRepository;	
	private AdverseEventDao adverseEventDao;
	

	public StudyProtocolVersion associateAdverseEventToStudy(
			Id aeId, Id studyId) {
		return null;
	}

	public StudyProtocolVersion dissociateAdverseEventFromStudy(
			Id adverseEventIdentifier, Id studyIdentifier) {
		return null;
	}

	public AdverseEvent associateAdditionalInformationToAdverseEvent(
			Id adverseEventIdentifier, Id additionalInformationIdentifier) {
		return null;
	}

	public AdverseEvent dissociateAdditionalInformationFromAdverseEvent(
			Id adverseEventIdentifier, Id additionalInformationIdentifier) {
		return null;
	}

	public AdverseEvent associateTreatmentInformationToAdverseEvent(
			Id adverseEventIdentifier, Id performedActivityIdentifier) {
		return null;
	}

	public AdverseEvent disassociateTreatmentInformationFromAdverseEvent(
			Id adverseEventIdentifier, Id performedActivityIdentifier) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.springframework.context.MessageSource)
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;		
	}

	/**
	 * @return the gridToDomainConverter
	 */
	public final GridToDomainObjectConverter getGridToDomainConverter() {
		return gridToDomainConverter;
	}

	/**
	 * @param gridToDomainConverter the gridToDomainConverter to set
	 */
	public final void setGridToDomainConverter(
			GridToDomainObjectConverter gridToDomainConverter) {
		this.gridToDomainConverter = gridToDomainConverter;
	}

	/**
	 * @return the domainToGridConverter
	 */
	public final DomainToGridObjectConverter getDomainToGridConverter() {
		return domainToGridConverter;
	}

	/**
	 * @param domainToGridConverter the domainToGridConverter to set
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
	 * @param studyRepository the studyRepository to set
	 */
	public final void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

	/**
	 * @return the adverseEventDao
	 */
	public final AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	/**
	 * @param adverseEventDao the adverseEventDao to set
	 */
	public final void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

}
