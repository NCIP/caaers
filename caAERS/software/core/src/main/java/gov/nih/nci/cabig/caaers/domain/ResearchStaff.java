package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.*;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * 
 * @author Kulasekaran
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "research_staffs")
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "seq_users_id") })
public abstract class ResearchStaff extends User {

	protected Integer id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id-generator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// LOGIC

	@Transient
	public boolean isAssociatedToUserGroup(UserGroupType groupType) {
		return getUserGroupTypes().contains(groupType);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		ResearchStaff that = (ResearchStaff) o;
		if (emailAddress != null ? !emailAddress.equals(that.emailAddress)
				: that.emailAddress != null)
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
    	if(address == null) address = new Address();
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
	
	@Transient
	public List<String> getAllRoles(){
		Set<String> roleSet = new HashSet<String>();
		for(SiteResearchStaff siteResearchStaff : getSiteResearchStaffs()){
			for(SiteResearchStaffRole siteResearchStaffRole : siteResearchStaff.getSiteResearchStaffRoles()){
				roleSet.add(siteResearchStaffRole.getRoleCode());
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
				siteRolesMap.put(siteResearchStaff.getOrganization().getNciInstituteCode(), roleCodeList);
			}
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
	
    @Transient
    public Date getActiveDate() {
        SortedSet<Date> dates = new TreeSet<Date>();
        for (SiteResearchStaff srs : this.getSiteResearchStaffs()) {
            dates.add(srs.getActiveDate());
        }
        if (dates.size() > 0) return dates.first(); else return null;
    }
}