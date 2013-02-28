/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import org.apache.commons.collections15.Factory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.annotations.Parameter;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 
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
public abstract class Investigator extends Person {
	

	
	/** The nci identifier. */
	protected String nciIdentifier;
	
	/** The lazy list helper. */
	protected final LazyListHelper lazyListHelper;
	
	/** The external id. */
	protected String externalId;
	
	/** The status. */
	protected String status;
	
	/** The external investigators. */
	protected List<Investigator> externalInvestigators = new ArrayList<Investigator>();
	
	/** The allowed to login. */
	protected Boolean allowedToLogin = Boolean.TRUE;
	
	/** The was login id null. */
	protected boolean wasLoginIdNull = true;
	
	/** The was login disallowed. */
	protected boolean wasLoginDisallowed = true;
	
    /**
     * Instantiates a new investigator.
     */
    public Investigator() {
        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(SiteInvestigator.class, new SiteInvestigatorFactory(this));
    }
   
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getCaaersUser()
     */
    @OneToOne
    @JoinColumn(name = "user_id")	
	public User getCaaersUser() {
		return caaersUser;
	}
    
    // business methods

    /**
     * Adds the site investigator.
     *
     * @param siteInvestigator the site investigator
     */
    public void addSiteInvestigator(final SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setInvestigator(this);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getLastFirst()
     */
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getFullName()
     */
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
     * Gets the site investigators internal.
     *
     * @return the site investigators internal
     */
    @OneToMany(mappedBy = "investigator", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<SiteInvestigator> getSiteInvestigatorsInternal() {
        return lazyListHelper.getInternalList(SiteInvestigator.class);
    }

    /**
     * Sets the site investigators internal.
     *
     * @param investigators the new site investigators internal
     */
    public void setSiteInvestigatorsInternal(final List<SiteInvestigator> investigators) {
        lazyListHelper.setInternalList(SiteInvestigator.class, investigators);
    }

    /**
     * Gets the site investigators.
     *
     * @return the site investigators
     */
    @Transient
    public List<SiteInvestigator> getSiteInvestigators() {
        return lazyListHelper.getLazyList(SiteInvestigator.class);
    }

    /**
     * Sets the site investigators.
     *
     * @param investigators the new site investigators
     */
    public void setSiteInvestigators(final List<SiteInvestigator> investigators) {
        setSiteInvestigatorsInternal(investigators);
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
	 * Gets the external investigators.
	 *
	 * @return the external investigators
	 */
	@Transient
	public List<Investigator> getExternalInvestigators() {
		return externalInvestigators;
	}

	/**
	 * Sets the external investigators.
	 *
	 * @param externalInvestigators the new external investigators
	 */
	public void setExternalInvestigators(List<Investigator> externalInvestigators) {
		this.externalInvestigators = externalInvestigators;
	}
	
    // /OBJECT METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if(!(o instanceof Investigator)) return false;

        Investigator that = (Investigator) o;
        if(that.getId() != null && getId() != null) return that.getId().equals(getId());

		if (!super.equals(o))
			return false;

        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier) : that.nciIdentifier != null)
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nciIdentifier != null ? nciIdentifier.hashCode() : 0);
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return getFullName();
    }
    
    /**
     * Find site investigator by id.
     *
     * @param id the id
     * @return the site investigator
     */
    public SiteInvestigator findSiteInvestigatorById(Integer id){
        for(SiteInvestigator si : getSiteInvestigators()){
            if(si.getId() != null && si.getId().equals(id)) return si;
        }
        return null;
    }
    
	/**
	 * Find site investigator.
	 *
	 * @param other the other
	 * @return the site investigator
	 */
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
	 *
	 * @param organization the organization
	 * @return the site investigator
	 */
	public SiteInvestigator findSiteInvestigator(Organization organization){
		for(SiteInvestigator siteInvestigator : getSiteInvestigators()){
			if(siteInvestigator.getOrganization().equals(organization)){
				return siteInvestigator;
			}
		}
		return null;
	}
    
	/**
	 * Gets the allowed to login.
	 *
	 * @return the allowed to login
	 */
	@Transient
    @Column(name = "allowed_to_login")
	public Boolean getAllowedToLogin() {
		return allowedToLogin;
	}

	/**
	 * Sets the allowed to login.
	 *
	 * @param allowedToLogin the new allowed to login
	 */
	public void setAllowedToLogin(Boolean allowedToLogin) {
		this.allowedToLogin = allowedToLogin;
	}

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive() {
        for (SiteInvestigator si : this.getSiteInvestigators()) {
            if (si.isActive()) return true;
        }
        return false;
    }
    
    /**
     * Gets the was login id null.
     *
     * @return the was login id null
     */
    @Transient
    public boolean getWasLoginIdNull() {
		return wasLoginIdNull;
	}
    
    /**
     * Sets the was login id null.
     *
     * @param wasLoginIdNull the new was login id null
     */
    public void setWasLoginIdNull(boolean wasLoginIdNull) {
		this.wasLoginIdNull = wasLoginIdNull;
	}
    
    /**
     * Gets the was login disallowed.
     *
     * @return the was login disallowed
     */
    @Transient
    public boolean getWasLoginDisallowed() {
		return wasLoginDisallowed;
	}
    
    /**
     * Sets the was login disallowed.
     *
     * @param wasLoginDisallowed the new was login disallowed
     */
    public void setWasLoginDisallowed(boolean wasLoginDisallowed) {
		this.wasLoginDisallowed = wasLoginDisallowed;
	}

    /**
     * All the SiteInvestigator where the investigator is active.
     *
     * @return the active site investigators
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
    /**
     * A factory for creating SiteInvestigator objects.
     */
    class SiteInvestigatorFactory implements Factory<SiteInvestigator>{

    	/** The investigator. */
	    Investigator investigator;

    	/**
	     * Instantiates a new site investigator factory.
	     *
	     * @param investegator the investegator
	     */
	    public SiteInvestigatorFactory(Investigator investegator){
    		this.investigator=investegator;
    	}

		/* (non-Javadoc)
		 * @see org.apache.commons.collections15.Factory#create()
		 */
		public SiteInvestigator create() {
			SiteInvestigator siteInvestigator = new SiteInvestigator();
			siteInvestigator.setInvestigator(investigator);
            siteInvestigator.setStartDate(DateUtils.today());
			return siteInvestigator;
		}
    }

    @Transient
     public void setActive(boolean _active) {
         if (getSiteInvestigators() == null) return;
         for (SiteInvestigator srs : this.getSiteInvestigators()) {
             if (!_active) srs.setEndDate(new Date()); else srs.setEndDate(null);
         }
     }
}
