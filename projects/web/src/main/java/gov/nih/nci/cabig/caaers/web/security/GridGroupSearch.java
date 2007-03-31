/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public interface GridGroupSearch {
	List<String> getGridGroupNames(String identity);
}
