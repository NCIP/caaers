package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;


/**
 * @author Kulasekaran
 */
@Entity
@Table (name="research_staffs")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_research_staffs_id")
    }
)
public class ResearchStaff extends AbstractMutableDomainObject {
	
	private String firstName;
    private String middleName; 
    private String maidenName; 
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String race;
    private String ethnicity; 
    private List<StudyPersonnel> studyPersonnels = new ArrayList<StudyPersonnel>();
    private List<ContactMechanism> contactMechanims = new ArrayList<ContactMechanism>();
    
    public void addStudyPersonnel(StudyPersonnel studyPersonnel) {
        getStudyPersonnels().add(studyPersonnel);        
    }
    
	
	@OneToMany
    @JoinColumn(name="staff_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN}) 
	public List<ContactMechanism> getContactMechanims() {
		return contactMechanims;
	}
    
	public void setContactMechanims(List<ContactMechanism> contactMechanims) {
		this.contactMechanims = contactMechanims;
	} 		
        
	//	 business methods	   	    
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
    
	@Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name = "maiden_name")
    public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name = "birth_date")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Column(name = "ethnicity")
    public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Column(name = "race")
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
		
	@OneToMany (mappedBy = "researchStaff", fetch = FetchType.LAZY)    
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })	
	public List<StudyPersonnel> getStudyPersonnels() {
		return studyPersonnels;
	}

	public void setStudyPersonnels(List<StudyPersonnel> studyPersonnels) {
		this.studyPersonnels = studyPersonnels;
	}
					            
    /*public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Participant that = (Participant) o;

        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (assignments != null ? !assignments.equals(that.assignments) : that.assignments != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 29 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 29 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    } */
}
