package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * This class represents the MeddraStudyDisease domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class MeddraStudyDisease extends AbstractStudyDisease<LowLevelTerm> {

	private static final long serialVersionUID = -2191830866356536610L;
	private String meddraCode;

    public String getMeddraCode() {
        return meddraCode;
    }

    public void setMeddraCode(String meddraCode) {
        this.meddraCode = meddraCode;
    }

    @ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    @Override
    @Transient
    public String getTermName() {
        return getTerm().getFullName();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MeddraStudyDisease that = (MeddraStudyDisease) o;
        
        if(this.isRetired() || that.isRetired()) return false;
        
        if (getTerm() != null ? !getTerm().equals(that.getTerm()) : that.getTerm() != null) return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.getTerm() == null ? 0 : this.getTerm().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
    	return meddraCode + " : "+ this.getTermName();
    }
    
}
