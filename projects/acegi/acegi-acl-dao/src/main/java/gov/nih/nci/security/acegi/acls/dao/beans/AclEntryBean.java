package gov.nih.nci.security.acegi.acls.dao.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="acl_entry")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_acl_entry_id")
    }
)
public class AclEntryBean {
	
	private Long id;
	private int aclOrder;
	private int mask;
	private String isGranting;
	private String isAuditSuccess;
	private String isAuditFailure;
	
	private AclObjectIdentityBean objectIdentity;
	private AclSidBean sid;
	
	public String getIsAuditFailure() {
		return isAuditFailure;
	}
	public void setIsAuditFailure(String isAuditFailure) {
		this.isAuditFailure = isAuditFailure;
	}
	public String getIsAuditSuccess() {
		return isAuditSuccess;
	}
	public void setIsAuditSuccess(String isAuditSuccess) {
		this.isAuditSuccess = isAuditSuccess;
	}
	public String getIsGranting() {
		return isGranting;
	}
	public void setIsGranting(String isGranting) {
		this.isGranting = isGranting;
	}
	
	@Id @GeneratedValue(generator="id-generator")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getMask() {
		return mask;
	}
	public void setMask(int mask) {
		this.mask = mask;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acl_object_identity")
	public AclObjectIdentityBean getObjectIdentity() {
		return objectIdentity;
	}
	public void setObjectIdentity(AclObjectIdentityBean objectIdentity) {
		this.objectIdentity = objectIdentity;
	}
	public int getAclOrder() {
		return aclOrder;
	}
	public void setAclOrder(int order) {
		this.aclOrder = order;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid_id")
	public AclSidBean getSid() {
		return sid;
	}
	public void setSid(AclSidBean sid) {
		this.sid = sid;
	}
	
	
}
