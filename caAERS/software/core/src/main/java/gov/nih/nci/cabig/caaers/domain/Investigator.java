package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.list.PredicatedList;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the Investigator domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 * @author Priyatam
 * @author Biju Joseph
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "investigators")
@DiscriminatorColumn(name="type")
@GenericGenerator(name = "id-generator", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "seq_users_id") })
public abstract class Investigator extends User {
	
	protected Integer id;
	protected String nciIdentifier;
	protected final LazyListHelper lazyListHelper;
	protected String externalId;
	protected String status;
	protected List<Investigator> externalInvestigators = new ArrayList<Investigator>();
	protected Boolean allowedToLogin = Boolean.TRUE;
	
	protected boolean wasLoginIdNull = true;
	protected boolean wasLoginDisallowed = true;
	
    public Investigator() {
        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(SiteInvestigator.class, new SiteInvestigatorFactory(this));
    }
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id-generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    // business methods

    public void addSiteInvestigator(final SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setInvestigator(this);
    }

    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = getFirstName() != null;
        if (getLastName() != null) {
            name.append(getLastName());
            if (hasFirstName) {
                name.append(", ");
            }
        }
        if (hasFirstName) {
            name.append(getFirstName());
        }
        return name.toString();
    }

    @Transient
    public String getFullName() {
        StringBuilder name = new StringBuilder();
        boolean hasLastName = getLastName() != null;
        if (getFirstName() != null) {
            name.append(getFirstName());
            if (hasLastName) {
                name.append(' ');
            }
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString();
    }

    /*
     * public void addSiteInvestigator(SiteInvestigator studyInvestigator) {
     * getSiteInvestigators().add(studyInvestigator); }
     */

    // bean methods
 

    @Transient
    public String getNciIdentifier() {
        return nciIdentifier;
    }

    public void setNciIdentifier(final String nciIdentifier) {
        this.nciIdentifier = nciIdentifier;
    }

    @OneToMany(mappedBy = "investigator", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<SiteInvestigator> getSiteInvestigatorsInternal() {
        return lazyListHelper.getInternalList(SiteInvestigator.class);
    }

    public void setSiteInvestigatorsInternal(final List<SiteInvestigator> investigators) {
        lazyListHelper.setInternalList(SiteInvestigator.class, investigators);
    }

    @Transient
    public List<SiteInvestigator> getSiteInvestigators() {
        return lazyListHelper.getLazyList(SiteInvestigator.class);
    }

    public void setSiteInvestigators(final List<SiteInvestigator> investigators) {
        setSiteInvestigatorsInternal(investigators);
    }
    
    @Transient
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
 
	@Transient
	public List<Investigator> getExternalInvestigators() {
		return externalInvestigators;
	}

	public void setExternalInvestigators(List<Investigator> externalInvestigators) {
		this.externalInvestigators = externalInvestigators;
	}
	
    // /OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investigator that = (Investigator) o;

        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier) : that.nciIdentifier != null)
            return false;

        return true && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nciIdentifier != null ? nciIdentifier.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
    	return getFullName();
    }
    

    
	public SiteInvestigator findSiteInvestigator(SiteInvestigator other){
		for(SiteInvestigator siteInvestigator : getSiteInvestigators()){
			if(siteInvestigator.getOrganization().equals(other.getOrganization())){
				return siteInvestigator;
			}
		}
		return null;
	}
	
	/**
	 * This method return a siteInvestigator for a given Organization.
	 * @param organization
	 * @return
	 */
	public SiteInvestigator findSiteInvestigator(Organization organization){
		for(SiteInvestigator siteInvestigator : getSiteInvestigators()){
			if(siteInvestigator.getOrganization().equals(organization)){
				return siteInvestigator;
			}
		}
		return null;
	}
    
    @Column(name = "allowed_to_login")
	public Boolean getAllowedToLogin() {
		return allowedToLogin;
	}

	public void setAllowedToLogin(Boolean allowedToLogin) {
		this.allowedToLogin = allowedToLogin;
	}

    @Transient
    public boolean getActive() {
        for (SiteInvestigator si : this.getSiteInvestigators()) {
            if (si.isActive()) return true;
        }
        return false;
    }
    
    @Transient
    public boolean getWasLoginIdNull() {
		return wasLoginIdNull;
	}
    public void setWasLoginIdNull(boolean wasLoginIdNull) {
		this.wasLoginIdNull = wasLoginIdNull;
	}
    
    @Transient
    public boolean getWasLoginDisallowed() {
		return wasLoginDisallowed;
	}
    public void setWasLoginDisallowed(boolean wasLoginDisallowed) {
		this.wasLoginDisallowed = wasLoginDisallowed;
	}

    /**
     * All the SiteInvestigator where the investigator is active.
     * @return
     */
    @Transient
    public List<SiteInvestigator> getActiveSiteInvestigators(){
        List<SiteInvestigator> sinvList = new ArrayList<SiteInvestigator>();
        for(SiteInvestigator si : getSiteInvestigators()){
            if(si.isActive()) sinvList.add(si);
        }
        return sinvList;
    }


    //Inner Class used instead of InstantiateFactory
    class SiteInvestigatorFactory implements Factory<SiteInvestigator>{

    	Investigator investigator;

    	public SiteInvestigatorFactory(Investigator investegator){
    		this.investigator=investegator;
    	}

		public SiteInvestigator create() {
			SiteInvestigator siteInvestigator = new SiteInvestigator();
			siteInvestigator.setInvestigator(investigator);
            siteInvestigator.setStartDate(DateUtils.today());
			return siteInvestigator;
		}
    }
        
    
}
