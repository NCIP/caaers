package gov.nih.nci.security.acegi.acls.dao.impl;

import gov.nih.nci.security.acegi.acls.dao.AclCache;
import gov.nih.nci.security.acegi.acls.dao.AclLookupStrategy;
import gov.nih.nci.security.acegi.acls.dao.beans.AclEntryBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclObjectIdentityBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclSidBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.UnloadedSidException;
import org.acegisecurity.acls.domain.AccessControlEntryImpl;
import org.acegisecurity.acls.domain.AclAuthorizationStrategy;
import org.acegisecurity.acls.domain.AclImpl;
import org.acegisecurity.acls.domain.AuditLogger;
import org.acegisecurity.acls.domain.BasePermission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.acls.sid.GrantedAuthoritySid;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.util.FieldUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;




public class BasicAclLookupStrategy extends HibernateDaoSupport implements
		AclLookupStrategy {

	private int batchSize;

	private AclAuthorizationStrategy aclAuthorizationStrategy;

	private AuditLogger auditLogger;

	private AclCache aclCache;

	public BasicAclLookupStrategy() {
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public Map readAclsById(ObjectIdentity[] objects, Sid[] sids) {

		Assert.isTrue(batchSize >= 1, "BatchSize must be >= 1");
		Assert.notEmpty(objects, "Objects to lookup required");

		// Map<ObjectIdentity,Acl>
		Map result = new HashMap(); // contains FULLY loaded Acl objects

		Set currentBatchToLoad = new HashSet(); // contains ObjectIdentitys

		for (int i = 0; i < objects.length; i++) {
			// Check we don't already have this ACL in the results
			if (result.containsKey(objects[i])) {
				continue; // already in results, so move to next element
			}

			// Check cache for the present ACL entry
			Acl acl = aclCache.getFromCache(objects[i]);

			// Ensure any cached element supports all the requested SIDs
			// (they should always, as our base impl doesn't filter on SID)
			if (acl != null) {
				if (acl.isSidLoaded(sids)) {
					result.put(acl.getObjectIdentity(), acl);

					continue; // now in results, so move to next element
				} else {
					throw new IllegalStateException(
							"Error: SID-filtered element detected when implementation does not perform SID filtering - have you added something to the cache manually?");
				}
			}

			// To get this far, we have no choice but to retrieve it from DB
			// (although we don't do it until we get a batch of them to load)
			currentBatchToLoad.add(objects[i]);

			// Is it time to load from DB the currentBatchToLoad?
			if ((currentBatchToLoad.size() == this.batchSize)
					|| ((i + 1) == objects.length)) {
				Map loadedBatch = lookupObjectIdentities(
						(ObjectIdentity[]) currentBatchToLoad
								.toArray(new ObjectIdentity[] {}), sids);

				// Add loaded batch (all elements 100% initialized) to results
				result.putAll(loadedBatch);

				// Add the loaded batch to the cache
				Iterator loadedAclIterator = loadedBatch.values().iterator();

				while (loadedAclIterator.hasNext()) {
					aclCache.putInCache((AclImpl) loadedAclIterator.next());
				}

				currentBatchToLoad.clear();
			}
		}

		// Now we're done, check every requested object identity was found
		// (throw NotFoundException if needed)
		for (int i = 0; i < objects.length; i++) {
			if (!result.containsKey(objects[i])) {
				throw new NotFoundException(
						"Unable to find ACL information for object identity '"
								+ objects[i].toString() + "'");
			}
		}

		return result;

	}

	private Map lookupObjectIdentities(ObjectIdentity[] identities, Sid[] sids) {

		Map acls = new HashMap();
		/*
		 * Retrieve the AclObjectIdentityBean objects. This assumes that the
		 * identity field is of the form <id>:<className>.
		 */
		String hql = "from AclObjectIdentityBean b "
				+ " where b.identity in (:listParam)";
		String[] params = new String[] { "listParam" };
		String[] idents = new String[identities.length];
		for (int i = 0; i < identities.length; i++) {
			idents[i] = identities[i].getIdentifier().toString() + ":"
					+ identities[i].getJavaType().getName();
		}

		List oidBeans = this.getHibernateTemplate().findByNamedParam(hql,
				params, idents);
//		logger.debug("Got " + oidBeans.size() + " beans");

		// Construct the real objects
		for (Iterator i = oidBeans.iterator(); i.hasNext();) {
			AclObjectIdentityBean oidBean = (AclObjectIdentityBean) i.next();

			Acl acl = constructAcl(oidBean);

			// logger.debug("Putting acl for oid " + oid + " in map under " +
			// oidBean.getId());
			acls.put(acl.getObjectIdentity(), acl);

		}

		// Finally, convert our "acls" containing StubAclParents into true Acls
		// Map resultMap = new HashMap();
		// Iterator iter = acls.values().iterator();
		//
		// while (iter.hasNext()) {
		// Acl inputAcl = (Acl) iter.next();
		// Assert.isInstanceOf(AclImpl.class, inputAcl, "Map should have
		// contained an AclImpl");
		// Assert.isInstanceOf(Long.class, ((AclImpl) inputAcl).getId(),
		// "Acl.getId() must be Long");
		//
		// Long id = (Long) ((AclImpl) inputAcl).getId();
		// logger.debug("Calling convert with id=" + id);
		// Acl result = convert(acls, id);
		// resultMap.put(result.getObjectIdentity(), result);
		// }

		// return resultMap;
		return acls;

	}

	private Acl constructAcl(AclObjectIdentityBean oidBean) {
		String[] identParts = oidBean.getIdentity().split(":");
		ObjectIdentity oid = new ObjectIdentityImpl(identParts[1], Long
				.valueOf(identParts[0]));

		// Contruct parent
		Acl parentAcl = null;

		// Set stub parent; ancestors handled later
		AclObjectIdentityBean parentOidBean = oidBean.getParent();
		if (parentOidBean != null) {
			parentAcl = constructAcl(parentOidBean);
		}

		boolean entriesInheriting = Boolean.valueOf(oidBean
				.getIsEntriesInheriting());

		// Contruct owner Sid
		Sid owner = null;
		AclSidBean ownerBean = oidBean.getAclSid();
		if (Boolean.valueOf(ownerBean.getIsPrincipal())) {
			owner = new PrincipalSid(ownerBean.getSid());
		} else {
			owner = new GrantedAuthoritySid(ownerBean.getSid());
		}

		// Construct the ACL
		Acl acl = new AclImpl(oid, oidBean.getId(),
				getAclAuthorizationStrategy(), getAuditLogger(), parentAcl,
				null, entriesInheriting, owner);

		Map orderedEntries = new TreeMap();
		Collection unorderedEntries = oidBean.getAclEntries();
		for (Iterator j = unorderedEntries.iterator(); j.hasNext();) {
			AclEntryBean aceBean = (AclEntryBean) j.next();
			Integer order = new Integer(aceBean.getAclOrder());
			List l = (List) orderedEntries.get(order);
			if (l == null) {
				l = new ArrayList();
				orderedEntries.put(order, l);
			}
			l.add(aceBean);
		}
		for (Iterator j = orderedEntries.values().iterator(); j.hasNext();) {

			List l = (List) j.next();
			for (Iterator k = l.iterator(); k.hasNext();) {
				

				AclEntryBean aceBean = (AclEntryBean) k.next();

				// Construct recipient Sid
				Sid recipient = null;
				AclSidBean recipientBean = aceBean.getSid();
				if (Boolean.valueOf(recipientBean.getIsPrincipal())) {
					recipient = new PrincipalSid(recipientBean.getSid());
				} else {
					recipient = new GrantedAuthoritySid(recipientBean.getSid());
				}

				Permission permission = BasePermission.buildFromMask(aceBean
						.getMask());
				boolean granting = Boolean.valueOf(aceBean.getIsGranting());
				boolean auditSuccess = Boolean.valueOf(aceBean
						.getIsAuditSuccess());
				boolean auditFailure = Boolean.valueOf(aceBean
						.getIsAuditFailure());

				AccessControlEntryImpl ace = new AccessControlEntryImpl(aceBean
						.getId(), acl, recipient, permission, granting,
						auditSuccess, auditFailure);

				Field acesField = FieldUtils.getField(AclImpl.class, "aces");
				List aces;

				try {
					acesField.setAccessible(true);
					aces = (List) acesField.get(acl);
				} catch (IllegalAccessException ex) {
					throw new IllegalStateException(
							"Could not obtain AclImpl.ace field: cause["
									+ ex.getMessage() + "]");
				}

				// Add the ACE if it doesn't already exist in the ACL.aces field
				if (!aces.contains(ace)) {
					aces.add(ace);
				}
			}
		}

		return acl;
	}

	// private Acl convert(Map inputMap, Long currentIdentity) {
	// Assert.notEmpty(inputMap, "InputMap required");
	// Assert.notNull(currentIdentity, "CurrentIdentity required");
	//
	// // Retrieve this Acl from the InputMap
	// logger.debug("Looking for acl under id " + currentIdentity);
	// Acl uncastAcl = (Acl) inputMap.get(currentIdentity);
	// Assert.isInstanceOf(AclImpl.class, uncastAcl, "The inputMap contained a
	// non-AclImpl");
	//
	// AclImpl inputAcl = (AclImpl) uncastAcl;
	//
	// Acl parent = inputAcl.getParentAcl();
	//
	// if ((parent != null) && parent instanceof StubAclParent) {
	// // Lookup the parent
	// StubAclParent stubAclParent = (StubAclParent) parent;
	// parent = convert(inputMap, stubAclParent.getId());
	// }
	//
	// // Now we have the parent (if there is one), create the true AclImpl
	// AclImpl result = new AclImpl(inputAcl.getObjectIdentity(), (Long)
	// inputAcl.getId(), aclAuthorizationStrategy,
	// auditLogger, parent, null, inputAcl.isEntriesInheriting(),
	// inputAcl.getOwner());
	//
	// // Copy the "aces" from the input to the destination
	// Field field = FieldUtils.getField(AclImpl.class, "aces");
	//
	// try {
	// field.setAccessible(true);
	// field.set(result, field.get(inputAcl));
	// } catch (IllegalAccessException ex) {
	// throw new IllegalStateException("Could not obtain or set AclImpl.ace
	// field");
	// }
	//
	// return result;
	// }

	public AclCache getAclCache() {
		return aclCache;
	}

	public void setAclCache(AclCache aclCache) {
		this.aclCache = aclCache;
	}

	private class StubAclParent implements Acl {
		private AclObjectIdentityBean bean;

		public StubAclParent(AclObjectIdentityBean bean) {
			this.bean = bean;
		}

		public AccessControlEntry[] getEntries() {
			throw new UnsupportedOperationException("Stub only");
		}

		public Long getId() {
			return bean.getId();
		}

		public ObjectIdentity getObjectIdentity() {
			throw new UnsupportedOperationException("Stub only");
		}

		public Sid getOwner() {
			throw new UnsupportedOperationException("Stub only");
		}

		public Acl getParentAcl() {
			throw new UnsupportedOperationException("Stub only");
		}

		public boolean isEntriesInheriting() {
			throw new UnsupportedOperationException("Stub only");
		}

		public boolean isGranted(Permission[] permission, Sid[] sids,
				boolean administrativeMode) throws NotFoundException,
				UnloadedSidException {
			throw new UnsupportedOperationException("Stub only");
		}

		public boolean isSidLoaded(Sid[] sids) {
			throw new UnsupportedOperationException("Stub only");
		}
	}

	public AclAuthorizationStrategy getAclAuthorizationStrategy() {
		return aclAuthorizationStrategy;
	}

	public void setAclAuthorizationStrategy(
			AclAuthorizationStrategy aclAuthorizationStrategy) {
		this.aclAuthorizationStrategy = aclAuthorizationStrategy;
	}

	public AuditLogger getAuditLogger() {
		return auditLogger;
	}

	public void setAuditLogger(AuditLogger auditLogger) {
		this.auditLogger = auditLogger;
	}

}
