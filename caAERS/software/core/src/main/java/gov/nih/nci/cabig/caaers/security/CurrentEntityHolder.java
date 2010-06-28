package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

/**
 * Holds the current entity, which is normally an instance of
 * {@link DomainObject}, based on which fabricated authentication has been
 * created. See http://jira.semanticbits.com/browse/CAAERS-4098.
 * 
 * @author dkrylov
 * @see http://jira.semanticbits.com/browse/CAAERS-4098
 */
public final class CurrentEntityHolder {

	private static final String NAME_ID_SEP = "_";
	private static final ThreadLocal<DomainObject> holder = new ThreadLocal<DomainObject>();

	private CurrentEntityHolder() {
	}

	/**
	 * Sets {@link DomainObject}. It will be bound to the current
	 * {@link Thread}.
	 * 
	 * @param authentication
	 */
	public static void setEntity(DomainObject domainObject) {
		holder.set(domainObject);
	}

	/**
	 * @return
	 */
	public static DomainObject getEntity() {
		return holder.get();
	}

	/**
	 * Returns the cache key discriminator to be used for caching CSM calls in
	 * {@link CaaersSecurityFacade}. See
	 * http://jira.semanticbits.com/browse/CAAERS
	 * -4098?focusedCommentId=19639#action_19639
	 * 
	 * @return
	 */
	public static String getEntityCacheKeyDiscriminator() {
		String key = "";
		DomainObject doObject = getEntity();
		if (doObject!=null) {
			key = doObject.getClass().getName()+NAME_ID_SEP+doObject.getId();
		}
		return key;
	}

}
