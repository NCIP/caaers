package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 * @author Priyatam
 */
@Entity
@Table (name="investigators")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_investigators_id")
    }
)
public class Investigator extends AbstractMutableDomainObject {
	
	private String firstName;
	private String middleName;
    private String lastName;
    private String nciIdentifier;
    private List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>(); 
    
    // business methods
    	   	    
    public void addSiteInvestigator(SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setInvestigator(this);
    }
    
    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = getFirstName() != null;
        if (getLastName() != null) {
            name.append(getLastName());
            if (hasFirstName) name.append(", ");
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
            if (hasLastName) name.append(' ');
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString();
    }
    
    /*
    public void addSiteInvestigator(SiteInvestigator studyInvestigator) {
        getSiteInvestigators().add(studyInvestigator);        
    }
    */
    
    // bean methods    
    
   public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   
	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}
	
	@OneToMany (mappedBy = "investigator", fetch = FetchType.LAZY)    
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<SiteInvestigator> getSiteInvestigators() {
		return siteInvestigators;
	}

	public void setSiteInvestigators(List<SiteInvestigator> siteInvestigators) {
		this.siteInvestigators = siteInvestigators;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nciIdentifier == null) ? 0 : nciIdentifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Investigator other = (Investigator) obj;
		if (nciIdentifier == null) {
			if (other.nciIdentifier != null)
				return false;
		} else if (!nciIdentifier.equals(other.nciIdentifier))
			return false;
		return true;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	            
}
