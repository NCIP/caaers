package gov.nih.nci.security.acegi.acls.dao.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="acl_sid")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_acl_sid_id")
    }
)
public class AclSidBean {
	
	private Long id;
	private String isPrincipal;
	private String sid;
	
	private Set<AclEntryBean> accessControlEntries = new HashSet<AclEntryBean>(); 
	private Set<AclObjectIdentityBean> objectIdentities = new HashSet<AclObjectIdentityBean>(); 
	
	@Id @GeneratedValue(generator="id-generator")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIsPrincipal() {
		return isPrincipal;
	}
	public void setIsPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@OneToMany (mappedBy = "sid", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL })
	public Set<AclEntryBean> getAccessControlEntries() {
		return accessControlEntries;
	}
	public void setAccessControlEntries(Set<AclEntryBean> accessControlEntries) {
		this.accessControlEntries = accessControlEntries;
	}
	
	@OneToMany (mappedBy = "aclSid", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL })
	public Set<AclObjectIdentityBean> getObjectIdentities() {
		return objectIdentities;
	}
	public void setObjectIdentities(Set<AclObjectIdentityBean> objectIdentities) {
		this.objectIdentities = objectIdentities;
	}

}
