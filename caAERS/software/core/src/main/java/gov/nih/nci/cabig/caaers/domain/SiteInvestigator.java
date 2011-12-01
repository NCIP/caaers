package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;


/**
 * This class represents the SiteInvestigator domain object associated with the Adverse event
 * report.
 * 
 * @author
 * 
 */
@Entity
@Table(name = "site_investigators")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_site_investigators_id") })
public class SiteInvestigator extends AbstractMutableDomainObject {

    /** The organization. */
    private Organization organization;
    
    /** The investigator. */
    private Investigator investigator;
    
    /** The email address. */
    private String emailAddress;

    /** The phone number. */
    private String phoneNumber;

    /** The fax number. */
    private String faxNumber;

    /** The address. */
    private Address address;

    /** The start date. */
    private Date startDate;
    
    /** The end date. */
    private Date endDate;
    
    /** The status. */
    private String status;

    /** The study investigators. */
    private List<StudyInvestigator> studyInvestigators = new ArrayList<StudyInvestigator>();

    /**
     * Instantiates a new site investigator.
     */
    public SiteInvestigator() {
		super();
		this.setStartDate(DateUtils.today());
	}

	/**
	 * Gets the investigator.
	 *
	 * @return the investigator
	 */
	@ManyToOne
    @JoinColumn(name = "investigator_id", nullable = false)
    public Investigator getInvestigator() {
        return investigator;
    }

    /**
     * Gets the study investigators.
     *
     * @return the study investigators
     */
    @OneToMany(mappedBy = "siteInvestigator", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyInvestigator> getStudyInvestigators() {
        return studyInvestigators;
    }

    /**
     * Sets the study investigators.
     *
     * @param studyInvestigators the new study investigators
     */
    public void setStudyInvestigators(List<StudyInvestigator> studyInvestigators) {
        this.studyInvestigators = studyInvestigators;
    }

    /**
     * Sets the investigator.
     *
     * @param investigator the new investigator
     */
    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

    /**
     * Gets the organization.
     *
     * @return the organization
     */
    @ManyToOne
    @JoinColumn(name = "site_id")
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

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     *
     * @param emailAddress the new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
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
	@Column(name = "end_date")
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

    @Embedded
    public Address getAddress() {
        if(address == null) address = new Address();
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the fax number.
     *
     * @return the fax number
     */
    @Column(name = "fax_number")
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the fax number.
     *
     * @param faxNumber the new fax number
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

    // /OBJECT METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((investigator == null) ? 0 : investigator.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SiteInvestigator other = (SiteInvestigator) obj;
        if (emailAddress == null) {
            if (other.emailAddress != null) return false;
        } else if (!emailAddress.equals(other.emailAddress)) return false;
        if (investigator == null) {
            if (other.investigator != null) return false;
        } else if (!investigator.equals(other.investigator)) return false;
        if (organization == null) {
            if (other.organization != null) return false;
        } else if (!organization.equals(other.organization)) return false;
       // if (statusDate == null) {
        //    if (other.statusDate != null) return false;
        //} else if (!statusDate.equals(other.statusDate)) return false;
        return true;
    }
    
    /**
     * This method will return a status text for display purpose's.
     *
     * @return the status
     */
    @Transient
    public String getStatus(){
    	status = "New";
    	if(getInvestigator() != null){
	    	if(getInvestigator().getId() == null){
	    		return status;
	    	}else{
	    		if(isActive()){
	    			status = "Active";
	        	}
	        	if(isInActive()){
	        		status = "InActive";
	        	}
	    	}
    	}
    	return status;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return String.valueOf(investigator);
    }

}
