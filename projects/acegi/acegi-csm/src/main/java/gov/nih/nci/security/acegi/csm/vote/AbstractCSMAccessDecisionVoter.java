/**
 * 
 */
package gov.nih.nci.security.acegi.csm.vote;

import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;

import java.util.Iterator;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AbstractAclVoter;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author joshua
 * 
 */
public abstract class AbstractCSMAccessDecisionVoter extends AbstractAclVoter {

	private static final Log logger = LogFactory
			.getLog(AbstractCSMAccessDecisionVoter.class);

	private String processConfigAttribute;

	private CSMObjectIdGenerator objectIdGenerator;

	public CSMObjectIdGenerator getObjectIdGenerator() {
		return objectIdGenerator;
	}

	public void setObjectIdGenerator(CSMObjectIdGenerator objectIdGenerator) {
		this.objectIdGenerator = objectIdGenerator;
	}

	public String getProcessConfigAttribute() {
		return processConfigAttribute;
	}

	public void setProcessConfigAttribute(String processConfigAttribute) {
		this.processConfigAttribute = processConfigAttribute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute attribute) {

		boolean supports = false;
		if ((attribute.getAttribute() != null)
				&& attribute.getAttribute().equals(getProcessConfigAttribute())) {
			supports = true;
		}
		return supports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.vote.AccessDecisionVoter#vote(org.acegisecurity.Authentication,
	 *      java.lang.Object, org.acegisecurity.ConfigAttributeDefinition)
	 */
	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition config) {

		int vote = AccessDecisionVoter.ACCESS_ABSTAIN;

		for (Iterator i = config.getConfigAttributes(); i.hasNext();) {
			ConfigAttribute att = (ConfigAttribute) i.next();
			if (supports(att)) {
				Object objInstance = getDomainObjectInstance(object);
				vote = checkAuthorization(authentication, objInstance);
				break;
			}
		}

		return vote;
	}

	protected abstract int checkAuthorization(Authentication authentication, Object object);

}
