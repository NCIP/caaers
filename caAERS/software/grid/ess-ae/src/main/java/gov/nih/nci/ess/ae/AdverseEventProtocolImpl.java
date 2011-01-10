package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.Id;
import ess.caaers.nci.nih.gov.Oid;
import gov.nih.nci.ess.ae.service.protocol.common.AEProtocolI;

import java.rmi.RemoteException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import _21090.org.iso.ST;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventProtocolImpl implements MessageSourceAware,
		AEProtocolI {

	private static final Log log = LogFactory
			.getLog(AdverseEventProtocolImpl.class);
	private static final ISO21090Helper h = null;
	private DomainToGridObjectConverter domainToGridConverter;
	private GridToDomainObjectConverter gridToDomainConverter;
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
	private String getStringValue(ST st) {
		return (st != null && st.getValue() != null) ? st.getValue() : "";
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
	public void updateCodingTerminologyForStudy(Id id, Oid oid)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		
	}

}
