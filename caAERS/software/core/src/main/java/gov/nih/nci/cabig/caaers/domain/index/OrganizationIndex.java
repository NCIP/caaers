package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.security.dao.hibernate.UserGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class OrganizationIndex.
 */
@Entity
@Table(name = "organization_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_organization_index_id") })
public class OrganizationIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The organization. */
	private Organization organization;
	
	/**
	 * Database Columns Role.
	 */
	private Boolean roleCode101;
	
	private Boolean roleCode102;
	
	private Boolean roleCode103;
	
	private Boolean roleCode104;
	
	private Boolean roleCode105;
	
	private Boolean roleCode106;
	
	private Boolean roleCode107;
	
	private Boolean roleCode108;
	
	private Boolean roleCode109;
	
	private Boolean roleCode110;
	
	private Boolean roleCode111;
	
	private Boolean roleCode112;
	
	private Boolean roleCode113;
	
	private Boolean roleCode114;
	
	private Boolean roleCode115;
	
	private Boolean roleCode116;
	
	private Boolean roleCode117;
	
	private Boolean roleCode118;
	
	private Boolean roleCode119;
	
	private Boolean roleCode120;
	
	private Boolean roleCode121;
	
	private Boolean roleCode122;
	
	private Boolean roleCode123;
	
	private Boolean roleCode7942;
	
	private Boolean roleCode7943;
		

	/**
     * Gets the login id.
     *
     * @return the login id
     */
    public String getLoginId() {
		return loginId;
	}
	
	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	@ManyToOne
    @JoinColumn(name = "organization_id")
	public Organization getOrganization() {
		return organization;
	}
	
	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name="R_101")
	public Boolean getRoleCode101() {
		return roleCode101;
	}

	public void setRoleCode101(Boolean roleCode101) {
		this.roleCode101 = roleCode101;
	}

	@Column(name="R_102")
	public Boolean getRoleCode102() {
		return roleCode102;
	}

	public void setRoleCode102(Boolean roleCode102) {
		this.roleCode102 = roleCode102;
	}

	@Column(name="R_103")
	public Boolean getRoleCode103() {
		return roleCode103;
	}

	public void setRoleCode103(Boolean roleCode103) {
		this.roleCode103 = roleCode103;
	}

	@Column(name="R_104")
	public Boolean getRoleCode104() {
		return roleCode104;
	}

	public void setRoleCode104(Boolean roleCode104) {
		this.roleCode104 = roleCode104;
	}

	@Column(name="R_105")
	public Boolean getRoleCode105() {
		return roleCode105;
	}

	public void setRoleCode105(Boolean roleCode105) {
		this.roleCode105 = roleCode105;
	}

	@Column(name="R_106")
	public Boolean getRoleCode106() {
		return roleCode106;
	}

	public void setRoleCode106(Boolean roleCode106) {
		this.roleCode106 = roleCode106;
	}

	@Column(name="R_107")
	public Boolean getRoleCode107() {
		return roleCode107;
	}

	public void setRoleCode107(Boolean roleCode107) {
		this.roleCode107 = roleCode107;
	}

	@Column(name="R_108")
	public Boolean getRoleCode108() {
		return roleCode108;
	}

	public void setRoleCode108(Boolean roleCode108) {
		this.roleCode108 = roleCode108;
	}

	@Column(name="R_109")
	public Boolean getRoleCode109() {
		return roleCode109;
	}

	public void setRoleCode109(Boolean roleCode109) {
		this.roleCode109 = roleCode109;
	}

	@Column(name="R_110")
	public Boolean getRoleCode110() {
		return roleCode110;
	}

	public void setRoleCode110(Boolean roleCode110) {
		this.roleCode110 = roleCode110;
	}

	@Column(name="R_111")
	public Boolean getRoleCode111() {
		return roleCode111;
	}

	public void setRoleCode111(Boolean roleCode111) {
		this.roleCode111 = roleCode111;
	}

	@Column(name="R_112")
	public Boolean getRoleCode112() {
		return roleCode112;
	}

	public void setRoleCode112(Boolean roleCode112) {
		this.roleCode112 = roleCode112;
	}

	@Column(name="R_113")
	public Boolean getRoleCode113() {
		return roleCode113;
	}

	public void setRoleCode113(Boolean roleCode113) {
		this.roleCode113 = roleCode113;
	}
	
	@Column(name="R_114")
	public Boolean getRoleCode114() {
		return roleCode114;
	}

	public void setRoleCode114(Boolean roleCode114) {
		this.roleCode114 = roleCode114;
	}

	@Column(name="R_115")
	public Boolean getRoleCode115() {
		return roleCode115;
	}

	public void setRoleCode115(Boolean roleCode115) {
		this.roleCode115 = roleCode115;
	}

	@Column(name="R_116")
	public Boolean getRoleCode116() {
		return roleCode116;
	}

	public void setRoleCode116(Boolean roleCode116) {
		this.roleCode116 = roleCode116;
	}

	@Column(name="R_117")
	public Boolean getRoleCode117() {
		return roleCode117;
	}

	public void setRoleCode117(Boolean roleCode117) {
		this.roleCode117 = roleCode117;
	}

	@Column(name="R_118")
	public Boolean getRoleCode118() {
		return roleCode118;
	}

	public void setRoleCode118(Boolean roleCode118) {
		this.roleCode118 = roleCode118;
	}

	@Column(name="R_119")
	public Boolean getRoleCode119() {
		return roleCode119;
	}

	public void setRoleCode119(Boolean roleCode119) {
		this.roleCode119 = roleCode119;
	}

	@Column(name="R_120")
	public Boolean getRoleCode120() {
		return roleCode120;
	}

	public void setRoleCode120(Boolean roleCode120) {
		this.roleCode120 = roleCode120;
	}
	
	@Column(name="R_121")
	public Boolean getRoleCode121() {
		return roleCode121;
	}

	public void setRoleCode121(Boolean roleCode121) {
		this.roleCode121 = roleCode121;
	}

	@Column(name="R_122")
	public Boolean getRoleCode122() {
		return roleCode122;
	}

	public void setRoleCode122(Boolean roleCode122) {
		this.roleCode122 = roleCode122;
	}

	@Column(name="R_123")
	public Boolean getRoleCode123() {
		return roleCode123;
	}

	public void setRoleCode123(Boolean roleCode123) {
		this.roleCode123 = roleCode123;
	}

	@Column(name="R_7942")
	public Boolean getRoleCode7942() {
		return roleCode7942;
	}

	public void setRoleCode7942(Boolean roleCode7942) {
		this.roleCode7942 = roleCode7942;
	}
	
	@Column(name="R_7943")
	public Boolean getRoleCode7943() {
		return roleCode7943;
	}

	public void setRoleCode7943(Boolean roleCode7943) {
		this.roleCode7943 = roleCode7943;
	}
}
