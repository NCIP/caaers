package gov.nih.nci.security.acegi.acls.dao.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="acl_object_identity")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_acl_oid_id")
    }
)
public class AclObjectIdentityBean {

	private Long id;
	private String isEntriesInheriting;
	private AclSidBean aclSid;
	private Set<AclEntryBean> aclEntries = new HashSet<AclEntryBean>();
//	private AclClassBean aclClass;
	private String identity;
	private AclObjectIdentityBean parent;
	private Set<AclObjectIdentityBean> children = new HashSet<AclObjectIdentityBean>();
	
	@OneToMany (mappedBy = "parent", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL })
	public Set<AclObjectIdentityBean> getChildren() {
		return children;
	}
	public void setChildren(Set<AclObjectIdentityBean> children) {
		this.children = children;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_object")
	public AclObjectIdentityBean getParent() {
		return parent;
	}
	public void setParent(AclObjectIdentityBean parent) {
		this.parent = parent;
	}
	@Column(name="object_id_identity")
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "object_id_class")
//	public AclClassBean getAclClass() {
//		return aclClass;
//	}
//	public void setAclClass(AclClassBean aclClass) {
//		this.aclClass = aclClass;
//	}
	
	@OneToMany (mappedBy = "objectIdentity", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL })
	public Set<AclEntryBean> getAclEntries() {
		return aclEntries;
	}
	public void setAclEntries(Set<AclEntryBean> aclEntries) {
		this.aclEntries = aclEntries;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_sid")
	public AclSidBean getAclSid() {
		return aclSid;
	}
	public void setAclSid(AclSidBean aclSid) {
		this.aclSid = aclSid;
	}
	public String getIsEntriesInheriting() {
		return isEntriesInheriting;
	}
	public void setIsEntriesInheriting(String isEntriesInheriting) {
		this.isEntriesInheriting = isEntriesInheriting;
	}
	
	@Id @GeneratedValue(generator="id-generator")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
