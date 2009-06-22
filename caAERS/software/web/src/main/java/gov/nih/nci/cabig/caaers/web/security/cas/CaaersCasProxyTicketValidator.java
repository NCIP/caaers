/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security.cas;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.cas.TicketResponse;
import org.acegisecurity.providers.cas.ticketvalidator.CasProxyTicketValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CaaersCasProxyTicketValidator extends CasProxyTicketValidator {

	private static final Log logger = LogFactory
			.getLog(CaaersCasProxyTicketValidator.class);

	public TicketResponse confirmTicketValid(String serviceTicket)
			throws AuthenticationException {
		// Attempt to validate presented ticket using CAS' ProxyTicketValidator
		// class
		ProxyTicketValidator pv = new ProxyTicketValidator();

		pv.setCasValidateUrl(super.getCasValidate());
		pv.setServiceTicket(serviceTicket);
		pv.setService(super.getServiceProperties().getService());

		if (super.getServiceProperties().isSendRenew()) {
			logger
					.warn("The current CAS ProxyTicketValidator does not support the 'renew' property. The ticket cannot be validated as having been issued by a 'renew' authentication. It is expected this will be corrected in a future version of CAS' ProxyTicketValidator.");
		}

		if ((getProxyCallbackUrl() != null)
				&& (!"".equals(getProxyCallbackUrl()))) {
			pv.setProxyCallbackUrl(getProxyCallbackUrl());
		}

		return validateNow(pv);
	}

	protected TicketResponse validateNow(ProxyTicketValidator pv)
			throws AuthenticationServiceException, BadCredentialsException {
		try {
			pv.validate();
		} catch (Exception internalProxyTicketValidatorProblem) {
			throw new AuthenticationServiceException(
					internalProxyTicketValidatorProblem.getMessage());
		}

		if (!pv.isAuthenticationSuccesful()) {
			throw new BadCredentialsException(pv.getErrorCode() + ": "
					+ pv.getErrorMessage());
		}

		return new TicketResponse(pv.getUser(), pv.getProxyList(), pv
				.getPgtIou());
	}
}
