package gov.nih.nci.security.acegi.acls.service;

import java.util.Map;

import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.ChildrenExistException;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.Sid;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DelegatingMutableAclService implements MutableAclService {
	
	/**
	 * 
	 */
	private MutableAclService service;
	

	public MutableAclService getService() {
		return service;
	}

	public void setService(MutableAclService service) {
		this.service = service;
	}

	public MutableAcl createAcl(ObjectIdentity oid)
			throws AlreadyExistsException {
		return getService().createAcl(oid);
	}

	public void deleteAcl(ObjectIdentity oid, boolean deleteChildren)
			throws ChildrenExistException {
		getService().deleteAcl(oid, deleteChildren);
	}

	public MutableAcl updateAcl(MutableAcl acl) throws NotFoundException {
		return getService().updateAcl(acl);
	}

	public ObjectIdentity[] findChildren(ObjectIdentity oid) {
		return getService().findChildren(oid);
	}

	public Acl readAclById(ObjectIdentity oid) throws NotFoundException {
		return getService().readAclById(oid);
	}

	public Acl readAclById(ObjectIdentity oid, Sid[] sids)
			throws NotFoundException {
		return getService().readAclById(oid, sids);
	}

	public Map readAclsById(ObjectIdentity[] oids) throws NotFoundException {
		return getService().readAclsById(oids);
	}

	public Map readAclsById(ObjectIdentity[] oids, Sid[] sids)
			throws NotFoundException {
		return getService().readAclsById(oids, sids);
	}

}
