/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public interface GridGrouperClientFactory {
	
	GrouperI newGridGrouperClient(String url);

}
