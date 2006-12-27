/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;

import javax.jbi.messaging.NormalizedMessage;

/**
 * Implimentations of this class are responsible for populating a 
 * BasicAuthenticationCredential object from a NormalizedMessage object.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public interface BasicAuthenticationTransformer {
	
	/**
	 * Returns a BasicAuthenticationCredential given a NormalizedMessage.
	 * 
	 * @param inMsg NormalizeMessage containing username and password
	 * @return BasicAuthenticationCredential containing username and password
	 * @throws InvalidCredentialException if the username and/or password can't be found
	 */
	BasicAuthenticationCredential transform(NormalizedMessage inMsg)
	throws InvalidCredentialException;

}
