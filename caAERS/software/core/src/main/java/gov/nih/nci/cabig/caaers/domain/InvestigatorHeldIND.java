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
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
/**
 * The Class InvestigatorHeldIND.
 */
@Entity
@DiscriminatorValue("INV")
public class InvestigatorHeldIND extends INDHolder {
    
    /** The investigator. */
    private Investigator investigator;

    /**
     * Gets the investigator.
     *
     * @return the investigator
     */
    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    @JoinColumn(name = "inv_id", nullable = false)
    public Investigator getInvestigator() {
        return investigator;
    }

    /**
     * Sets the investigator.
     *
     * @param investigator the new investigator
     */
    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.INDHolder#getName()
     */
    @Override
    @Transient
    public String getName() {
        if (investigator != null) return investigator.getFullName();
        return null;
    }

    @Override
    @Transient
    public String getNciIdentifier() {
        if(getInvestigator() != null) return getInvestigator().getNciIdentifier() ;
        return null;
    }
}
