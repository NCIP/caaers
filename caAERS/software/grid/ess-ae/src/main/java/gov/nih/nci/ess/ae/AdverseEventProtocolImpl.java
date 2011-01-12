package gov.nih.nci.ess.ae;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import ess.caaers.nci.nih.gov.AeTerminology;
import ess.caaers.nci.nih.gov.Id;
import ess.caaers.nci.nih.gov.Oid;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.ess.ae.service.protocol.common.AEProtocolI;

import java.rmi.RemoteException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import _21090.org.iso.II;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventProtocolImpl implements MessageSourceAware,
		AEProtocolI {

	public static final String BAD_OID = "WS_AEMS_047";
	public static final String INVALID_TERM_CODE = "WS_AEMS_046";
	private static final Log log = LogFactory
			.getLog(AdverseEventProtocolImpl.class);
	private static final ISO21090Helper h = null;
	private static final String INVALID_STUDY_ID_ERR = AdverseEventManagementImpl.INVALID_STUDY_ID_ERR;
	private static final String STUDY_NOT_FOUND_ERR = AdverseEventManagementImpl.STUDY_NOT_FOUND_ERR;
	private static final String INVALID_TERM_VERSION_CODE = "WS_AEMS_048";
	private static final String INVALID_MEDDRA_CODE = "WS_AEMS_049";
	private DomainToGridObjectConverter domainToGridConverter;
	private GridToDomainObjectConverter gridToDomainConverter;
	private StudyRepository studyRepository;
	private CtcDao ctcDao;
	private MeddraVersionDao meddraVersionDao;
	private MessageSource messageSource;

	/**
	 * @return the domainToGridConverter
	 */
	public final DomainToGridObjectConverter getDomainToGridConverter() {
		return domainToGridConverter;
	}

	/**
	 * @return the gridToDomainConverter
	 */
	public final GridToDomainObjectConverter getGridToDomainConverter() {
		return gridToDomainConverter;
	}

	/**
	 * @return the messageSource
	 */
	public final MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param st
	 * @return
	 */
	private String getSafeStringValue(II oid) {
		if (oid != null) {
			if (StringUtils.isNotBlank(oid.getExtension())) {
				return oid.getExtension();
			} else if (oid.getNullFlavor() != null) {
				return null;
			}
		}
		raiseError(BAD_OID);
		// will never reach this point, but needed in order to compile anyway.
		return null;
	}

	private void raiseError(String code) {
		throw new AdverseEventServiceException(code, getMessageSource()
				.getMessage(code, new Object[] {}, Locale.getDefault()));
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
	 * @param gridToDomainConverter
	 *            the gridToDomainConverter to set
	 */
	public final void setGridToDomainConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainConverter = gridToDomainObjectConverter;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public final void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ess.ae.service.protocol.common.AEProtocolI#
	 * updateCodingTerminologyForStudy(ess.caaers.nci.nih.gov.Id,
	 * ess.caaers.nci.nih.gov.Oid)
	 */
	public void updateCodingTerminologyForStudy(AeTerminology aeTerminology)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		Study study = getStudyByPrimaryId(aeTerminology.getStudyId());

		String termCodeStr = getSafeStringValue(aeTerminology.getTermCode());
		String termVersionStr = getSafeStringValue(aeTerminology.getTermVersion());
		String otherMeddraStr = getSafeStringValue(aeTerminology.getMeddra());

		Term term = null;
		Ctc ctc = null;
		MeddraVersion meddraVer = null;

		if (StringUtils.isNotBlank(termCodeStr)) {
			term = Term.getByName(termCodeStr);
			if (term == null) {
				raiseError(INVALID_TERM_CODE);
			}
		}
		if (StringUtils.isNotBlank(termVersionStr)) {
			ctc = ctcDao.getByName(termVersionStr);
			if (ctc == null) {
				raiseError(INVALID_TERM_VERSION_CODE);
			}
		}
		if (StringUtils.isNotBlank(otherMeddraStr)) {
			meddraVer = CollectionUtils.firstElement(meddraVersionDao
					.getMeddraByName(otherMeddraStr));
			if (meddraVer == null) {
				raiseError(INVALID_MEDDRA_CODE);
			}
		}

		study.getAeTerminology().setTerm(term);
		study.getAeTerminology().setCtcVersion(ctc);
		study.getAeTerminology().setMeddraVersion(meddraVer);
		studyRepository.save(study);
	}

	/**
	 * @param studyId
	 * @return
	 * @throws AdverseEventServiceException
	 * @throws NoSuchMessageException
	 */
	private Study getStudyByPrimaryId(II studyId)
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
	 * @return the ctcDao
	 */
	public final CtcDao getCtcDao() {
		return ctcDao;
	}

	/**
	 * @param ctcDao
	 *            the ctcDao to set
	 */
	public final void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}

	/**
	 * @return the meddraVersionDao
	 */
	public final MeddraVersionDao getMeddraVersionDao() {
		return meddraVersionDao;
	}

	/**
	 * @param meddraVersionDao
	 *            the meddraVersionDao to set
	 */
	public final void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
		this.meddraVersionDao = meddraVersionDao;
	}

}
