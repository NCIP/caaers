package gov.nih.nci.security.acegi.acls.dao;

import java.io.Serializable;

import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;

public interface AclCache {
    public void evictFromCache(Serializable pk);

    public void evictFromCache(ObjectIdentity objectIdentity);

    public MutableAcl getFromCache(ObjectIdentity objectIdentity);

    public MutableAcl getFromCache(Serializable pk);

    public void putInCache(MutableAcl acl);
}
