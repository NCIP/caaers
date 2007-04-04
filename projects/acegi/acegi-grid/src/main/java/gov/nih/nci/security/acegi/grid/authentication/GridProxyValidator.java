/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authentication;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public interface GridProxyValidator {

  boolean validate(String proxy) throws GridProxyValidationException;

}
