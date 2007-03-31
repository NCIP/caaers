/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cagrid.gridgrouper.client.GridGrouper;
import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class DefaultGridGrouperClientFactory implements
		GridGrouperClientFactory {

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.security.GridGrouperClientFactory#newGridGrouperClient(java.lang.String)
	 */
	public GrouperI newGridGrouperClient(String url) {
		return new GridGrouper(url);
	}

}
