package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.*;

 
/**
 * The Class AgentSpecificMeddraLowLevelTerm.
 *
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("meddra")
public class AgentSpecificMeddraLowLevelTerm extends AgentSpecificTerm<LowLevelTerm> {

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#getFullName()
     */
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#getTerm()
     */
    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    @Override
    public LowLevelTerm getTerm() {
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
     * @see gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#copy()
     */
    @Override
    public AgentSpecificMeddraLowLevelTerm copy() {
        return (AgentSpecificMeddraLowLevelTerm) super.copy();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#isMedDRA()
     */
    @Override
    @Transient
    public boolean isMedDRA() {
    	return true;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm#isOtherRequired()
     */
    @Override
    @Transient
    public boolean isOtherRequired() {
        return false;
    }

    @Override
    public boolean isOfSameTerm(String termName, String termCategory, String terminologyVersion, String otherToxicity, String otherMeddra) {
        if(true) throw new UnsupportedOperationException("Not implemented");
        return false;
    }
}