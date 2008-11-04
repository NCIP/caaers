package org.tp23.antinstaller.input;

import java.util.Locale;

import org.tp23.antinstaller.InstallerContext;


/**
 * A no args constructor should be provided
 * @author Paul Hinds
 * @version $Id: Validator.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface Validator {

	/**
	 * Validate the user entry.  The InstallerContext is provided to allow
	 * conditional failure based on user input. for example the implementation
	 * of this class could call the following code after failing to open a socket
	 * <pre>
	 * boolean usrOverride = ctx.getMessageRenderer().prompt("Prot not available are you sure?");
	 * if(userOverride)return true;
	 * else{
	 * 	throw new SocketException();
	 * }
	 * </pre>
	 * @param text  may be null it is up to the validator to decide if null or ""
	 * is acceptable
	 * @throws Exception
	 */
	public void validate(String text,InstallerContext ctx)throws Exception;
	/**
	 * This method should return a string for every exception that might be 
	 * thrown by the validate method.  The top level Throwable should be 
	 * handled at least.  
	 * @param ex
	 * @param l Locale (ignored, but one day we should be internationalized)
	 * @return
	 */
	public String getErrorMessage(Throwable ex,Locale l);
}
