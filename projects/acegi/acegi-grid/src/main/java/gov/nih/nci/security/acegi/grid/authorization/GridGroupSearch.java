/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authorization;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public interface GridGroupSearch {
	List<String> getGridGroupNames(String identity);
}
