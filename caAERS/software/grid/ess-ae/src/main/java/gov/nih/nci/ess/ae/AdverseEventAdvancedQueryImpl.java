package gov.nih.nci.ess.ae;

import java.rmi.RemoteException;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventQuery;
import ess.caaers.nci.nih.gov.LimitOffset;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI;
import gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventAdvancedQueryImpl implements MessageSourceAware, AEAdvancedQueryI {

	private GridToDomainObjectConverter gridToDomainConverter;
	private DomainToGridObjectConverter domainToGridConverter;
	private MessageSource messageSource;
	private AdverseEventDao adverseEventDao;

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
	 * @return the adverseEventDao
	 */
	public final AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	/**
	 * @param adverseEventDao
	 *            the adverseEventDao to set
	 */
	public final void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI#queryAdverseEvents(ess.caaers.nci.nih.gov.AdverseEventQuery, ess.caaers.nci.nih.gov.LimitOffset)
	 */
	public AdverseEvent[] findAdverseEvents(
			AdverseEventQuery adverseEventQuery, LimitOffset limitOffset)
			throws RemoteException, AdverseEventServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
