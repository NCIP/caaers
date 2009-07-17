package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "site_research_staff_roles")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_srstaff_roles_id") })
public class SiteResearchStaffRole extends AbstractMutableDomainObject{
	
	private SiteResearchStaff siteResearchStaff;
	private String roleCode;
	private Date startDate;
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "site_research_staffs_id")
	public SiteResearchStaff getSiteResearchStaff() {
		return siteResearchStaff;
	}
	public void setSiteResearchStaff(SiteResearchStaff siteResearchStaff) {
		this.siteResearchStaff = siteResearchStaff;
	}
	
	@Column(name = "role_code")
	public String getRoleCode() {
		return roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Transient
    public boolean isActive(){
    	return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
    }
   
    @Transient
    public boolean isInActive(){
    	return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    }
}
