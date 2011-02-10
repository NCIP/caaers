package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Factory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

 
/**
 * 
 * This class represents the ResearchStaff domain object associated with the
 * Adverse event report.
 * 
 * 
 * 
 * @author Priyatam
 * @author Kulasekaran
 * @author Biju Joseph
 * @author Monish Dombla
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "research_staffs")
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "seq_users_id") })
public abstract class ResearchStaff extends Person {

	/** The nci identifier. */
	protected String nciIdentifier;
	
	/** The external research staff. */
	protected List<ResearchStaff> externalResearchStaff = new ArrayList<ResearchStaff>();
	
	/** The external id. */
	protected String externalId;
	
	/** The status. */
	protected String status;
	
	/** The lazy list helper. */
	protected final LazyListHelper lazyListHelper;
	
	/** The address. */
	private Address address;

	/**
	 * Instantiates a new research staff.
	 */
	public ResearchStaff() {
		lazyListHelper = new LazyListHelper();
		// register with lazy list helper study site.
		lazyListHelper.add(SiteResearchStaff.class,new SiteResearchStaffFactory(this));
	}

	// LOGIC
	
    /* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getCaaersUser()
	 */
	@OneToOne
    @JoinColumn(name = "user_id")	
	public User getCaaersUser() {
		return caaersUser;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {

        if (o == null)
			return false;

        if(o == this) return true;

        if(!(o instanceof ResearchStaff)) return false;
        ResearchStaff that = (ResearchStaff) o;
        if(that.getId() != null && getId() != null) return that.getId().equals(getId());

		if (!super.equals(o))
			return false;

		if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result
		+ (emailAddress != null ? emailAddress.hashCode() : 0);
		return result;
	}

	/**
	 * Gets the nci identifier.
	 *
	 * @return the nci identifier
	 */
	@Transient
	public String getNciIdentifier() {
		return nciIdentifier;
	}

	/**
	 * Sets the nci identifier.
	 *
	 * @param nciIdentifier the new nci identifier
	 */
	public void setNciIdentifier(final String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

	/**
	 * Gets the external id.
	 *
	 * @return the external id
	 */
	@Transient
	public String getExternalId() {
		return externalId;
	}

	/**
	 * Sets the external id.
	 *
	 * @param externalId the new external id
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * Gets the external research staff.
	 *
	 * @return the external research staff
	 */
	@Transient
	public List<ResearchStaff> getExternalResearchStaff() {
		return externalResearchStaff;
	}

	/**
	 * Sets the external research staff.
	 *
	 * @param externalResearchStaff the new external research staff
	 */
	public void setExternalResearchStaff(
			List<ResearchStaff> externalResearchStaff) {
		this.externalResearchStaff = externalResearchStaff;
	}

	/**
	 * This method will return a status text for display purpose's.
	 *
	 * @return the site research staffs internal
	 */

	@OneToMany(mappedBy = "researchStaff", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<SiteResearchStaff> getSiteResearchStaffsInternal() {
		return lazyListHelper.getInternalList(SiteResearchStaff.class);
	}

	/**
	 * Sets the site research staffs internal.
	 *
	 * @param researchstaffs the new site research staffs internal
	 */
	public void setSiteResearchStaffsInternal(final List<SiteResearchStaff> researchstaffs) {
		lazyListHelper.setInternalList(SiteResearchStaff.class, researchstaffs);
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Embedded
	public Address getAddress() {
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
	 * Gets the site research staffs.
	 *
	 * @return the site research staffs
	 */
	@Transient
	public List<SiteResearchStaff> getSiteResearchStaffs() {
		return lazyListHelper.getLazyList(SiteResearchStaff.class);
	}

	/**
	 * Sets the site research staffs.
	 *
	 * @param researchstaffs the new site research staffs
	 */
	public void setSiteResearchStaffs(
			final List<SiteResearchStaff> researchstaffs) {
		setSiteResearchStaffsInternal(researchstaffs);
	}
	
	/**
	 * Utility method to SiteResearchStaff.
	 *
	 * @param siteResearchStaff the site research staff
	 */
    public void addSiteResearchStaff(final SiteResearchStaff siteResearchStaff) {
    	getSiteResearchStaffs().add(siteResearchStaff);
    	siteResearchStaff.setResearchStaff(this);
    }

	
	/**
	 * Gets the all roles.
	 *
	 * @return the all roles
	 */
	@Transient
	public List<String> getAllRoles(){
		Set<String> roleSet = new HashSet<String>();
		for(SiteResearchStaff siteResearchStaff : getSiteResearchStaffs()){
			for(SiteResearchStaffRole siteResearchStaffRole : siteResearchStaff.getSiteResearchStaffRoles()){
				if(siteResearchStaffRole.isActive()){
					roleSet.add(siteResearchStaffRole.getRoleCode());
				}
			}
		}
		List<String> roleList = new ArrayList<String>();  
		roleList.addAll(roleSet);
		return roleList;
	}
	
	/**
	 * Gets the site roles mapping.
	 *
	 * @return the site roles mapping
	 */
	@Transient
	public Map<String,List<String>> getSiteRolesMapping(){
		Map<String, List<String>> siteRolesMap = new HashMap<String,List<String>>();
		List<String> roleCodeList = null;
		for(SiteResearchStaff siteResearchStaff : getSiteResearchStaffs()){
			for(SiteResearchStaffRole siteResearchStaffRole : siteResearchStaff.getSiteResearchStaffRoles()){
				roleCodeList = new ArrayList<String>();
				roleCodeList.add(siteResearchStaffRole.getRoleCode());
			}
			siteRolesMap.put(siteResearchStaff.getOrganization().getNciInstituteCode(), roleCodeList);
		}
		return siteRolesMap;
	}
	
	/**
	 * Find site research staff.
	 *
	 * @param other the other
	 * @return the site research staff
	 */
	public SiteResearchStaff findSiteResearchStaff(SiteResearchStaff other){
		for(SiteResearchStaff siteResearchStaff : getSiteResearchStaffs()){
			if(siteResearchStaff.getOrganization().equals(other.getOrganization())){
				return siteResearchStaff;
			}
		}
		return null;
	}

    /**
     * The earliest start date of this research staff.
     *
     * @return the active date
     */
    @Transient
    public Date getActiveDate() {
        SortedSet<Date> dates = new TreeSet<Date>();
        for (SiteResearchStaff srs : this.getSiteResearchStaffs()) {
        	Date activeDate = srs.getActiveDate();
        	if(activeDate != null) dates.add(activeDate);
        }
        if (dates.size() > 0) return dates.first(); else return null;
    }

    /**
     * Sets the end date.
     *
     * @param date the new end date
     */
    @Transient
    public void setEndDate(Date date) {
        if (getSiteResearchStaffs() == null) return;
        for (SiteResearchStaff srs : this.getSiteResearchStaffs()) {
            srs.setEndDate(date);
        }
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive() {
    	if(getSiteResearchStaffs() == null) return false;

        for (SiteResearchStaff srs : this.getSiteResearchStaffs()) {
            if (srs.isActive()) return true;
        }
        return false;
    }

    /**
     * Will return all the SiteResearchStaff  which are currently active.
     *
     * @return the active site research staff
     */
    @Transient
    public List<SiteResearchStaff> getActiveSiteResearchStaff(){
        List<SiteResearchStaff> srsList = new ArrayList<SiteResearchStaff>();
        for(SiteResearchStaff  srs : getSiteResearchStaffs()){
            if(srs.isActive()) srsList.add(srs);
        }
        return srsList;
    }
    
    /**
     * Will return all the SiteResearchStaff  which are currently active.
     *
     * @return the in active site research staff
     */
    @Transient
    public List<SiteResearchStaff> getInActiveSiteResearchStaff(){
        List<SiteResearchStaff> srsList = new ArrayList<SiteResearchStaff>();
        for(SiteResearchStaff  srs : getSiteResearchStaffs()){
            if(!srs.isActive()) srsList.add(srs);
        }
        return srsList;
    }

    /**
     * Will return SiteResearchStaff having at least one active role provided in roleCodes parameter.
     *
     * @param roleCodes - roles to check
     * @return  A list of SiteResearchStaff
     */
    public List<SiteResearchStaff> findSiteResearchStaffByRoles(final String... roleCodes){
       List<SiteResearchStaff> srsList = new ArrayList<SiteResearchStaff>();
        for(SiteResearchStaff  srs : getSiteResearchStaffs()){
            if(srs.hasActiveRolesOfType(roleCodes)) srsList.add(srs);
        }

        return srsList;

    }

    /**
     * Find site research staff by id.
     *
     * @param id the id
     * @return the site research staff
     */
    public SiteResearchStaff findSiteResearchStaffById(Integer id){
        for(SiteResearchStaff srs : getSiteResearchStaffs()) {
            if(srs.getId() != null && srs.getId().equals(id)) return srs;
        }
        return null;
    }


    /**
     * Find site research staff by id.
     *
     * @param id the id
     * @return the site research staff
     */
    public SiteResearchStaff findActiveSiteResearchStaffByOrganizationId(Integer id){
        for(SiteResearchStaff srs : getActiveSiteResearchStaff()) {
           if(srs.getOrganization() != null && srs.getOrganization().getId().equals(id)) return srs;
        }
        return null;
    }



    /**
     * Will copy into this Person, the details from the input Person.
     *
     * @param rs - The Person from which the details to be copied from.
     */
    public void sync(final ResearchStaff rs) {
        super.sync(rs);
        setNciIdentifier(rs.getNciIdentifier());
        if (getAddress() != null) {
            getAddress().sync(rs.getAddress());
        } else {
            setAddress(rs.getAddress());
        }

        //sync the site researchstaffs
        CollectionUtils.forAllDo(getSiteResearchStaffs(), new Closure<SiteResearchStaff>() {
            public void execute(SiteResearchStaff srs) {
                SiteResearchStaff otherSRS = rs.findSiteResearchStaff(srs);
                srs.sync(otherSRS);
            }
        });

        //add new site researchstaff if needed
        for (SiteResearchStaff srs : rs.getSiteResearchStaffs()) {
            SiteResearchStaff availableSRS = findSiteResearchStaff(srs);
            if (availableSRS == null) addSiteResearchStaff(srs);
        }
    }


	// Inner Class used instead of InstantiateFactory

	/**
	 * A factory for creating SiteResearchStaff objects.
	 */
	class SiteResearchStaffFactory implements Factory<SiteResearchStaff> {

		/** The research staff. */
		ResearchStaff researchStaff;
		
		/**
		 * Instantiates a new site research staff factory.
		 *
		 * @param researchStaff the research staff
		 */
		public SiteResearchStaffFactory(ResearchStaff researchStaff) {
			this.researchStaff = researchStaff;
		}

		/* (non-Javadoc)
		 * @see org.apache.commons.collections15.Factory#create()
		 */
		public SiteResearchStaff create() {
			SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
			siteResearchStaff.setResearchStaff(researchStaff);
			return siteResearchStaff;
		}
	}

}