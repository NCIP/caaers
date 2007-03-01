package gov.nih.nci.security.acegi.acls.dao;

import gov.nih.nci.security.acegi.acls.dao.beans.AclEntryBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclObjectIdentityBean;
import gov.nih.nci.security.acegi.acls.dao.beans.AclSidBean;

import org.acegisecurity.acls.MutableAclService;



public interface MutableAclDao extends MutableAclService {

//	Long saveAclClassBean(AclClassBean aclClass);

//	AclClassBean findAclClassBeanById(Long id);

	Long saveAclSidBean(AclSidBean sid);

	AclSidBean findAclSidBeanById(Long id);

	Long saveAclObjectIdentityBean(AclObjectIdentityBean aclOidBean);

	AclObjectIdentityBean findAclObjectIdentityBeanById(Long id);

	Long saveAclEntryBean(AclEntryBean aclEntry);

	AclEntryBean findAclEntryBeanById(Long id);

}
