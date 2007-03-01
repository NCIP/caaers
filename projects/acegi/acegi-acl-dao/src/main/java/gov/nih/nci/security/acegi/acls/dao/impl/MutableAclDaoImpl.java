package gov.nih.nci.security.acegi.acls.dao.impl;

import gov.nih.nci.security.acegi.acls.dao.AclCache;
import gov.nih.nci.security.acegi.acls.dao.AclLookupStrategy;
import gov.nih.nci.security.acegi.acls.dao.MutableAclDao;
import gov.nih.nci.security.acegi.acls.dao.beans.AclEntryBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclObjectIdentityBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclSidBean;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.ChildrenExistException;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.domain.AccessControlEntryImpl;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.acls.sid.GrantedAuthoritySid;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;




public class MutableAclDaoImpl extends HibernateDaoSupport implements
		MutableAclDao {

	private AclLookupStrategy lookupStrategy;

	private AclCache aclCache;

	// Methods from MutableAclDao
	// ===================================================================

	public MutableAcl createAcl(ObjectIdentity oid)
			throws AlreadyExistsException {

		if (findAclObjectIdentityBeanByObjectIdentity(oid) != null) {
			throw new AlreadyExistsException("Object identity '" + oid
					+ "' already exists");
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		PrincipalSid sid = new PrincipalSid(auth);

		// Create the acl_object_identity row
		createObjectIdentity(oid, sid);

		// Retrieve the ACL via superclass (ensures cache registration, proper
		// retrieval etc)
		Acl acl = readAclById(oid);
		Assert.isInstanceOf(MutableAcl.class, acl,
				"MutableAcl should be been returned");

		return (MutableAcl) acl;
	}

	public void deleteAcl(ObjectIdentity objectIdentity, boolean deleteChildren)
			throws ChildrenExistException {

		Assert.notNull(objectIdentity, "Object Identity required");
		Assert.notNull(objectIdentity.getIdentifier(),
				"Object Identity doesn't provide an identifier");

		// Recursively call this method for children, or handle children if they
		// don't want automatic recursion
		ObjectIdentity[] children = findChildren(objectIdentity);

		if (deleteChildren) {
			for (int i = 0; i < children.length; i++) {
				deleteAcl(children[i], true);
			}
		} else if (children.length > 0) {
			throw new ChildrenExistException("Cannot delete '" + objectIdentity
					+ "' (has " + children.length + " children)");
		}

		// Delete this ACL's ACEs in the acl_entry table
		deleteEntries(objectIdentity);

		// Delete this ACL's acl_object_identity row
		deleteObjectIdentity(objectIdentity);

		// Clear the cache
		getAclCache().evictFromCache(objectIdentity);

	}

	public MutableAcl updateAcl(MutableAcl acl) throws NotFoundException {

		Assert.notNull(acl.getId(),
				"Object Identity doesn't provide an identifier");

		// Delete this ACL's ACEs in the acl_entry table
		deleteEntries(acl.getObjectIdentity());
		
		// Create this ACL's ACEs in the acl_entry table
		createEntries(acl);
		
		// Change the mutable columns in acl_object_identity
		updateObjectIdentity(acl);

		// Clear the cache
		aclCache.evictFromCache(acl.getObjectIdentity());

		// Retrieve the ACL via superclass (ensures cache registration, proper
		// retrieval etc)
		MutableAcl mAcl = (MutableAcl) readAclById(acl.getObjectIdentity());
		
		return mAcl;

	}

	public ObjectIdentity[] findChildren(ObjectIdentity parentIdentity) {

		AclObjectIdentityBean oidBean = findAclObjectIdentityBeanByObjectIdentity(parentIdentity);

		Set children = oidBean.getChildren();
		ObjectIdentity[] oids = new ObjectIdentity[children.size()];
		int idx = 0;
		for (Iterator i = children.iterator(); i.hasNext(); idx++) {
			AclObjectIdentityBean b = (AclObjectIdentityBean) i.next();
			String[] identParts = b.getIdentity().split(":");
			ObjectIdentity oid = new ObjectIdentityImpl(identParts[1],
					identParts[0]);
			oids[idx] = oid;
		}

		return oids;
	}

	public Acl readAclById(ObjectIdentity object, Sid[] sids)
			throws NotFoundException {
		Map map = readAclsById(new ObjectIdentity[] { object }, sids);

		if (map.size() == 0) {
			throw new NotFoundException("Could not find ACL");
		} else {
			return (Acl) map.get(object);
		}
	}

	public Acl readAclById(ObjectIdentity object) throws NotFoundException {
		return readAclById(object, null);
	}

	public Map readAclsById(ObjectIdentity[] objects) {
		return readAclsById(objects, null);
	}

	public Map readAclsById(ObjectIdentity[] objects, Sid[] sids)
			throws NotFoundException {
		return getLookupStrategy().readAclsById(objects, sids);
	}

	// Methods from MutableAclDao interfaces
	// ========================================================

	// public Long saveAclClassBean(AclClassBean aclClassBean) {
	// return (Long) getHibernateTemplate().save(aclClassBean);
	// }
	//
	// public AclClassBean findAclClassBeanById(Long id) {
	// AclClassBean b = null;
	// Collection beans = getHibernateTemplate().find(
	// "from AclClassBean b where b.id=" + id);
	// if (beans.size() > 0) {
	// b = (AclClassBean) beans.iterator().next();
	// }
	// return b;
	// }

	public AclSidBean findAclSidBeanById(Long id) {
		AclSidBean b = null;
		Collection beans = getHibernateTemplate().find(
				"from AclSidBean b where b.id=" + id);
		if (beans.size() > 0) {
			b = (AclSidBean) beans.iterator().next();
		}
		return b;
	}

	public Long saveAclSidBean(AclSidBean sid) {
		return (Long) getHibernateTemplate().save(sid);
	}

	public Long saveAclObjectIdentityBean(AclObjectIdentityBean aclOidBean) {
		return (Long) getHibernateTemplate().save(aclOidBean);
	}

	public AclObjectIdentityBean findAclObjectIdentityBeanById(Long id) {
		AclObjectIdentityBean b = null;
		Collection beans = getHibernateTemplate().find(
				"from AclObjectIdentityBean b where b.id=" + id);
		if (beans.size() > 0) {
			b = (AclObjectIdentityBean) beans.iterator().next();
		}
		return b;
	}

	public AclEntryBean findAclEntryBeanById(Long id) {
		AclEntryBean b = null;
		Collection beans = getHibernateTemplate().find(
				"from AclEntryBean b where b.id=" + id);
		if (beans.size() > 0) {
			b = (AclEntryBean) beans.iterator().next();
		}
		return b;
	}

	public Long saveAclEntryBean(AclEntryBean aclEntry) {
		return (Long) getHibernateTemplate().save(aclEntry);
	}

	// Accessors/Mutators to support collaborators
	// ===============================================

	@Required
	public AclLookupStrategy getLookupStrategy() {
		return lookupStrategy;
	}

	public void setLookupStrategy(AclLookupStrategy lookupStrategy) {
		this.lookupStrategy = lookupStrategy;
	}

	public AclCache getAclCache() {
		return aclCache;
	}

	public void setAclCache(AclCache aclCache) {
		this.aclCache = aclCache;
	}

	// Package protected methods
	// =============================================================

	void createObjectIdentity(ObjectIdentity object, PrincipalSid owner) {

		AclSidBean aclSid = createOrRetrieveSid(owner, true);
		// AclClassBean aclClass = createOrRetrieveClass(object.getJavaType(),
		// true);

		String ident = object.getIdentifier() + ":"
				+ object.getJavaType().getName();
		AclObjectIdentityBean aclOid = new AclObjectIdentityBean();
		aclOid.setIdentity(ident);
		aclOid.setIsEntriesInheriting("true");
		// aclOid.setAclClass(aclClass);
		aclOid.setAclSid(aclSid);

		saveAclObjectIdentityBean(aclOid);

	}

	// AclClassBean createOrRetrieveClass(Class javaType, boolean allowCreate) {
	//
	// AclClassBean aclClass;
	//
	// Collection aclClasses = getHibernateTemplate().find(
	// "from AclClassBean b where b.className", javaType.getName());
	// if (aclClasses.size() == 0) {
	// AclClassBean b = new AclClassBean();
	// b.setClassName(javaType.getName());
	// Long id = (Long) getHibernateTemplate().save(b);
	// aclClass = (AclClassBean) getHibernateTemplate().find(
	// "from AclClassBean b where b.id=?", id).iterator().next();
	// } else if (aclClasses.size() == 1) {
	// aclClass = (AclClassBean) aclClasses.iterator().next();
	// } else {
	// throw new RuntimeException("Fetched " + aclClasses.size()
	// + " classes for className = " + javaType.getName());
	// }
	// return aclClass;
	// }

	AclSidBean createOrRetrieveSid(Sid sid, boolean allowCreate) {
		AclSidBean aclSid = null;

		Assert.notNull(sid, "Sid required");

		String sidName = null;
		String isPrincipal = "true";

		if (sid instanceof PrincipalSid) {
			sidName = ((PrincipalSid) sid).getPrincipal();
		} else if (sid instanceof GrantedAuthoritySid) {
			sidName = ((GrantedAuthoritySid) sid).getGrantedAuthority();
			isPrincipal = "false";
		} else {
			throw new IllegalArgumentException(
					"Unsupported implementation of Sid");
		}

		Collection aclSids = getHibernateTemplate().find(
				"from AclSidBean b where b.isPrincipal = ? and b.sid = ?",
				new Object[] { isPrincipal, sidName });
		if (aclSids.size() == 0) {
			AclSidBean b = new AclSidBean();
			b.setIsPrincipal(isPrincipal);
			b.setSid(sidName);
			Long id = (Long) getHibernateTemplate().save(b);
			aclSid = (AclSidBean) getHibernateTemplate().find(
					"from AclSidBean b where b.id=?", id).iterator().next();
		} else if (aclSids.size() == 1) {
			aclSid = (AclSidBean) aclSids.iterator().next();
		} else {
			throw new RuntimeException("Fetched " + aclSids.size()
					+ " sids for principal = " + isPrincipal + " and sid = "
					+ sidName);
		}
		return aclSid;
	}

	// Long retrieveObjectIdentityPrimaryKey(ObjectIdentity oid) {
	//
	// Long id = null;
	// Collection results = getHibernateTemplate().find(
	// "from AclObjectIdentityBean b where b.id = "
	// + oid.getIdentifier() + " and b.aclClass.className = "
	// + oid.getJavaType().getName());
	// if (results.size() == 1) {
	// AclObjectIdentityBean b = (AclObjectIdentityBean) results
	// .iterator().next();
	// } else if (results.size() > 1) {
	// throw new RuntimeException(results.size()
	// + " object identities retreived for '"
	// + oid.getIdentifier() + ":" + oid.getJavaType().getName());
	// }
	//
	// return id;
	// }

	void deleteObjectIdentity(ObjectIdentity objectIdentity) {
		AclObjectIdentityBean oidBean = findAclObjectIdentityBeanByObjectIdentity(objectIdentity);
		getHibernateTemplate().delete(oidBean);
	}

	void deleteEntries(ObjectIdentity objectIdentity) {
		AclObjectIdentityBean oidBean = findAclObjectIdentityBeanByObjectIdentity(objectIdentity);
		Set aceBeans = oidBean.getAclEntries();
		if (aceBeans.size() > 0) {
			aceBeans.clear();
//			oidBean.setAclEntries(new HashSet());
			getHibernateTemplate().deleteAll(aceBeans);

			getHibernateTemplate().update(oidBean);
			
			for(Iterator i = aceBeans.iterator(); i.hasNext();){
				AclEntryBean entryBean = (AclEntryBean)i.next();
				AclSidBean sidBean = entryBean.getSid();
				if(sidBean != null){
					sidBean.getAccessControlEntries().remove(entryBean);
					getHibernateTemplate().update(sidBean);
				}
			}
		}
	}

	AclObjectIdentityBean findAclObjectIdentityBeanByObjectIdentity(
			ObjectIdentity objectIdentity) {
		return findAclObjectIdentityBeanByObjectIdentity(objectIdentity, false);
	}

	AclObjectIdentityBean findAclObjectIdentityBeanByObjectIdentity(
			ObjectIdentity objectIdentity, boolean required) {

		AclObjectIdentityBean oidBean = null;
		String ident = objectIdentity.getIdentifier() + ":"
				+ objectIdentity.getJavaType().getName();
		List l = getHibernateTemplate().find(
				"from AclObjectIdentityBean b where identity = ?", ident);
		if (l.size() > 1) {
			throw new RuntimeException("Found " + l.size()
					+ " AclObjectIdentityBean objects for '" + ident + "'");
		} else if (l.size() == 0 && required) {
			throw new RuntimeException(
					"Required AclOBjectIdentityBean not found for " + ident);
		} else if(l.size() == 1) {
			oidBean = (AclObjectIdentityBean) l.iterator().next();
		}

		return oidBean;
	}

	void updateObjectIdentity(MutableAcl acl) {

		AclObjectIdentityBean oidBean = findAclObjectIdentityBeanByObjectIdentity(acl
				.getObjectIdentity());

		AclObjectIdentityBean parentOidBean = null;
		if (acl.getParentAcl() != null) {
			Assert.isInstanceOf(ObjectIdentityImpl.class, acl.getParentAcl()
					.getObjectIdentity(),
					"Implementation only supports ObjectIdentityImpl");

			ObjectIdentityImpl oii = (ObjectIdentityImpl) acl.getParentAcl()
					.getObjectIdentity();
			parentOidBean = findAclObjectIdentityBeanByObjectIdentity(oii);
		}

		Assert.notNull(acl.getOwner(),
				"Owner is required in this implementation");
		AclSidBean ownerSid = createOrRetrieveSid(acl.getOwner(), true);

		oidBean.setAclSid(ownerSid);
		oidBean.setParent(parentOidBean);
		oidBean.setIsEntriesInheriting(String
				.valueOf(acl.isEntriesInheriting()));

		getHibernateTemplate().update(oidBean);

	}

	void createEntries(MutableAcl acl) {
		AccessControlEntry[] entries = acl.getEntries();
		if (entries != null && entries.length > 0) {

			AclObjectIdentityBean oidBean = findAclObjectIdentityBeanByObjectIdentity(acl
					.getObjectIdentity());
			if (oidBean == null) {
				throw new RuntimeException(
						"No AclObjectIdentityBean found for oid "
								+ acl.getObjectIdentity());
			}


			for (int i = 0; i < entries.length; i++) {

				AclEntryBean entryBean = new AclEntryBean();
				Assert.isTrue(entries[i] instanceof AccessControlEntryImpl,
						"Unknown ACE class");
				AccessControlEntryImpl entry = (AccessControlEntryImpl) entries[i];

				Sid sid = entry.getSid();
				AclSidBean sidBean = createOrRetrieveSid(sid, true);

				entryBean.setObjectIdentity(oidBean);
				entryBean.setSid(sidBean);
				sidBean.getAccessControlEntries().add(entryBean);

				entryBean.setMask(entry.getPermission().getMask());

				entryBean.setIsGranting(String.valueOf(entry.isGranting()));
				entryBean.setIsAuditSuccess(String.valueOf(entry
						.isAuditSuccess()));
				entryBean.setIsAuditFailure(String.valueOf(entry
						.isAuditFailure()));
				entryBean.setAclOrder(new Integer(i));

				Long entryId = saveAclEntryBean(entryBean);
				
				entryBean = findAclEntryBeanById(entryId);
				
				getHibernateTemplate().update(sidBean);
				
				oidBean.getAclEntries().add(entryBean);
				
			}
			getHibernateTemplate().update(oidBean);
			oidBean = findAclObjectIdentityBeanById(oidBean.getId());
		}
	}

}
