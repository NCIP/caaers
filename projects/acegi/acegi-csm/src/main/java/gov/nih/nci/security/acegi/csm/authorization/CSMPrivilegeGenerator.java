/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public interface CSMPrivilegeGenerator {
	
	/**
	 * Returns a CSM privilege name, given an object.
	 * 
	 * @param object from which to determine privilege
	 * @return CSM privilege name
	 */
	String generatePrivilege(Object object);

}
