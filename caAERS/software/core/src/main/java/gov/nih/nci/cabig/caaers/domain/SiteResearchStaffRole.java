/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

 
/**
 * The Class SiteResearchStaffRole.
 */
@Entity
@Table(name = "site_rs_staff_roles")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_site_rs_staff_roles_id") })
public class SiteResearchStaffRole extends AbstractMutableDomainObject{
	
	/** The site research staff. */
	private SiteResearchStaff siteResearchStaff;
	
	/** The role code. */
	private String roleCode;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/**
	 * Gets the site research staff.
	 *
	 * @return the site research staff
	 */
	@ManyToOne
	@JoinColumn(name = "site_research_staffs_id")
	public SiteResearchStaff getSiteResearchStaff() {
		return siteResearchStaff;
	}
	
	/**
	 * Sets the site research staff.
	 *
	 * @param siteResearchStaff the new site research staff
	 */
	public void setSiteResearchStaff(SiteResearchStaff siteResearchStaff) {
		this.siteResearchStaff = siteResearchStaff;
	}
	
	/**
	 * Gets the role code.
	 *
	 * @return the role code
	 */
	@Column(name = "role_code")
	public String getRoleCode() {
		return roleCode;
	}
	
	/**
	 * Sets the role code.
	 *
	 * @param roleCode the new role code
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	@Transient
    public boolean isActive(){
    	return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
    }
   
    /**
     * Checks if is in active.
     *
     * @return true, if is in active
     */
    @Transient
    public boolean isInActive(){
    	return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    }
}
