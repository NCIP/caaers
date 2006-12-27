/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import java.rmi.RemoteException;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;

import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public interface GridProxyTransformer {

	GlobusCredential transform(BasicAuthenticationCredential credential)
			throws InvalidCredentialFault, InsufficientAttributeFault,
			AuthenticationProviderFault, RemoteException;

}
