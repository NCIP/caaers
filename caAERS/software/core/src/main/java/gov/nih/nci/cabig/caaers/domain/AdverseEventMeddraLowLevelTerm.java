package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

 
/**
 * This class represents the AdverseEventMeddraLowLevelTerm domain object associated with the
 * Adverse event report.
 *
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class AdverseEventMeddraLowLevelTerm extends AbstractAdverseEventTerm<LowLevelTerm> {

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getFullName()
     */
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getUniversalTerm()
     */
    @Transient
    public String getUniversalTerm() {
    	if (getTerm() == null) {
            return null;
    	} else if(getAdverseEvent() != null && (getAdverseEvent().getDetailsForOther() != null)) {
    		StringBuilder sb = new StringBuilder(getTerm().getFullName());
            // strip everything after "Other", if it is in the name
            int otherAt = sb.indexOf("Other");
            if (otherAt >= 0) {
                sb.delete(otherAt + 5, sb.length());
            }
            if (getAdverseEvent().getDetailsForOther() != null) sb.append(": ").append(getAdverseEvent().getDetailsForOther());
            return sb.toString();
    	} else {
            return getTerm().getFullName();
        }
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getTerm()
     */
    @OneToOne
    @JoinColumn(name = "term_id")
    @Cascade(value = {CascadeType.LOCK})
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    /**
     * Gets the low level term.
     *
     * @return the low level term
     */
    @Transient
    public LowLevelTerm getLowLevelTerm() {
        return super.getTerm();
    }

    /**
     * Sets the low level term.
     *
     * @param lowlevelTerm the new low level term
     */
    @Transient
    public void setLowLevelTerm(LowLevelTerm lowlevelTerm) {
        super.setTerm(lowlevelTerm);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isOtherRequired()
     */
    @Override
    @Transient
    public boolean isOtherRequired() {
        //there is no other specify for MedDRA
        return false;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#copy()
     */
    @Override
    public AdverseEventMeddraLowLevelTerm copy() {
        return (AdverseEventMeddraLowLevelTerm) super.copy();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isMedDRA()
     */
    @Override
    @Transient
    public boolean isMedDRA() {
    	return true;
    }
}
