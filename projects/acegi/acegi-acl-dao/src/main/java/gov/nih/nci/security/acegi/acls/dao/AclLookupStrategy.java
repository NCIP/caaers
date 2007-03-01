package gov.nih.nci.security.acegi.acls.dao;

import java.util.Map;

import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.Sid;

public interface AclLookupStrategy {
    public Map readAclsById(ObjectIdentity[] objects, Sid[] sids);
}
