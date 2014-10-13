/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

 
/**
 * This class represents the CTC terminology associated to the adverse event.
 *
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("ctep")
public class AdverseEventCtcTerm extends AbstractAdverseEventTerm<CtcTerm> {
    
    /**
     * Refers to the associated CTC terminology {@link CtcTerm}.
     *
     * @return AdverseEventCtcTerm The CTC term
     */
    @OneToOne
    @JoinColumn(name = "term_id")
    @Cascade(value = {CascadeType.LOCK})
    @Override
    public CtcTerm getTerm() {
        return super.getTerm();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getFullName()
     */
    @Override
    @Transient
    public String getFullName() {
    	if(getTerm() == null)	return "";
    	return getTerm().getFullName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getUniversalTerm()
     */
    @Override
    @Transient
    public String getUniversalTerm() {
        if (getTerm() == null) {
            return null;
        } else if (getAdverseEvent() != null && (getAdverseEvent().getDetailsForOther() != null || getAdverseEvent().getLowLevelTerm() != null)) {
            StringBuilder sb = new StringBuilder(getTerm().getFullName());
            // strip everything after "Other", if it is in the name
            int otherAt = sb.indexOf("Other");
            if (otherAt >= 0) {
                sb.delete(otherAt + 5, sb.length());
            }
            if (getAdverseEvent().getDetailsForOther() != null) sb.append(": ").append(getAdverseEvent().getDetailsForOther());
            if (getAdverseEvent().getLowLevelTerm() != null && getAdverseEvent().getLowLevelTerm().getMeddraTerm() != null) {
            	sb.append(": ").append(getAdverseEvent().getLowLevelTerm().getMeddraTerm());
            }
            return sb.toString();
        } else {
            return getTerm().getFullName();
        }
    }

    /**
     * Get CTC term associated to the adverse event.
     *
     * @return CtcTerm
     */
    @Transient
    public CtcTerm getCtcTerm() {
        return super.getTerm();
    }

    /**
     * Set CTC term associated to the adverse event.
     *
     * @param ctcTerm The CTC term
     */
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        super.setTerm(ctcTerm);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isOtherRequired()
     */
    @Override
    @Transient
    public boolean isOtherRequired() {
        if (getTerm() == null) return false;
        return getTerm().isOtherRequired();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#copy()
     */
    @Override
    public AdverseEventCtcTerm copy() {
        return (AdverseEventCtcTerm) super.copy();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isMedDRA()
     */
    @Override
    @Transient
    public boolean isMedDRA() {
    	return false;
    }

    @Override
    @Transient
    public boolean isPositiveAttributionNeeded() {
        return !(getCtcTerm() != null && ArrayUtils.contains(
                new String[]{"10042434","10011912","10011914","10042435","10016479"}, getCtcTerm().getCtepCode()));
    }
}
