/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
/**
 * The Class ExpectedAEMeddraLowLevelTerm.
 *
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("meddra")
public class ExpectedAEMeddraLowLevelTerm extends AbstractExpectedAE<LowLevelTerm> {

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE#getFullName()
     */
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE#getTerm()
     */
    @ManyToOne
    @JoinColumn(name = "term_id")
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
     * @see gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE#copy()
     */
    @Override
    public ExpectedAEMeddraLowLevelTerm copy() {
        return (ExpectedAEMeddraLowLevelTerm) super.copy();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE#isMedDRA()
     */
    @Override
    @Transient
    public boolean isMedDRA() {
    	return true;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE#isOtherRequired()
     */
    @Override
    @Transient
    public boolean isOtherRequired() {
        return false;
    }

}
