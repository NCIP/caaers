package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Factory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
public abstract class ResearchStaff extends User {

	protected String nciIdentifier;
	protected List<ResearchStaff> externalResearchStaff = new ArrayList<ResearchStaff>();
	protected String externalId;
	protected String status;
	protected final LazyListHelper lazyListHelper;
	private Address address;

	public ResearchStaff() {
		lazyListHelper = new LazyListHelper();
		// register with lazy list helper study site.
		lazyListHelper.add(SiteResearchStaff.class,new SiteResearchStaffFactory(this));
	}

	// LOGIC
	
    @OneToOne
    @JoinColumn(name = "user_id")	
	public _User getCaaersUser() {
		return caaersUser;
	}


	public boolean equals(Object o) {
		if (this == o)
			return true;

        if (o == null)
			return false;

        if(!(o instanceof ResearchStaff)) return false;

		if (!super.equals(o))
			return false;
		ResearchStaff that = (ResearchStaff) o;
		if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null)
			return false;
		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result
		+ (emailAddress != null ? emailAddress.hashCode() : 0);
		return result;
	}

	@Transient
	public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(final String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

	@Transient
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@Transient
	public List<ResearchStaff> getExternalResearchStaff() {
		return externalResearchStaff;
	}

	public void setExternalResearchStaff(
			List<ResearchStaff> externalResearchStaff) {
		this.externalResearchStaff = externalResearchStaff;
	}

	/**
	 * 
	 * This method will return a status text for display purpose's.
	 * 
	 * @return
	 */

	@OneToMany(mappedBy = "researchStaff", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<SiteResearchStaff> getSiteResearchStaffsInternal() {
		return lazyListHelper.getInternalList(SiteResearchStaff.class);
	}

	public void setSiteResearchStaffsInternal(final List<SiteResearchStaff> researchstaffs) {
		lazyListHelper.setInternalList(SiteResearchStaff.class, researchstaffs);
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Transient
	public List<SiteResearchStaff> getSiteResearchStaffs() {
		return lazyListHelper.getLazyList(SiteResearchStaff.class);
	}

	public void setSiteResearchStaffs(
			final List<SiteResearchStaff> researchstaffs) {
		setSiteResearchStaffsInternal(researchstaffs);
	}
	
	/**
	 * Utility method to SiteResearchStaff
	 * @param siteResearchStaff
	 */
    public void addSiteResearchStaff(final SiteResearchStaff siteResearchStaff) {
    	getSiteResearchStaffs().add(siteResearchStaff);
    	siteResearchStaff.setResearchStaff(this);
    }

	
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
     * @return
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

    @Transient
    public void setEndDate(Date date) {
        if (getSiteResearchStaffs() == null) return;
        for (SiteResearchStaff srs : this.getSiteResearchStaffs()) {
            srs.setEndDate(date);
        }
    }

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
     * @return
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
     * @return
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
     * Will return SiteResearchStaff having at least one active role provided in roleCodes parameter
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

    public SiteResearchStaff findSiteResearchStaffById(Integer id){
        for(SiteResearchStaff srs : getSiteResearchStaffs()) {
            if(srs.getId() != null && srs.getId().equals(id)) return srs;
        }
        return null;
    }


    /**
     * Will copy into this Person, the details from the input Person
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

	class SiteResearchStaffFactory implements Factory<SiteResearchStaff> {

		ResearchStaff researchStaff;
		public SiteResearchStaffFactory(ResearchStaff researchStaff) {
			this.researchStaff = researchStaff;
		}

		public SiteResearchStaff create() {
			SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
			siteResearchStaff.setResearchStaff(researchStaff);
			return siteResearchStaff;
		}
	}

}