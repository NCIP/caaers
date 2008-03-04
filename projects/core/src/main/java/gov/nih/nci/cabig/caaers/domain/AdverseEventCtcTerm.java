package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * This class represents the CTC terminology associated to the adverse event.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("ctep")
public class AdverseEventCtcTerm extends AbstractAdverseEventTerm<CtcTerm> {
    /**
     * Refers to the associated CTC terminology {@link CtcTerm}
     * 
     * @return AdverseEventCtcTerm The CTC term
     */
    @OneToOne
    @JoinColumn(name = "term_id")
    @Override
    public CtcTerm getTerm() {
        return super.getTerm();
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
        } else if (getTerm().isOtherRequired()
                        && getAdverseEvent() != null
                        && (getAdverseEvent().getDetailsForOther() != null || getAdverseEvent()
                                        .getLowLevelTerm() != null)) {
            StringBuilder sb = new StringBuilder(getTerm().getFullName());
            // strip everything after "Other", if it is in the name
            int otherAt = sb.indexOf("Other");
            if (otherAt >= 0) {
                sb.delete(otherAt + 5, sb.length());
            }
            if (getAdverseEvent().getDetailsForOther() != null) sb.append(": ").append(
                            getAdverseEvent().getDetailsForOther());
            if (getAdverseEvent().getLowLevelTerm() != null) sb.append(": ").append(
                            getAdverseEvent().getLowLevelTerm().getMeddraTerm());
            return sb.toString();
        } else {
            return getTerm().getFullName();
        }
    }

    /**
     * Get CTC term associated to the adverse event
     * 
     * @param
     * @return CtcTerm
     */
    @Transient
    public CtcTerm getCtcTerm() {
        return super.getTerm();
    }

    /**
     * Set CTC term associated to the adverse event
     * 
     * @param ctcTerm
     *                The CTC term
     * 
     */
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        super.setTerm(ctcTerm);
    }

}
