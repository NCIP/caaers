/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.meddra;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

 
/**
 * The Class PreferredTerm.
 */
@Entity
@Table(name = "meddra_pt")
public class PreferredTerm extends AbstractMeddraDomainObject {

    /** The high level terms. */
    private HighLevelTerm highLevelTerms;

    /**
     * Gets the high level terms.
     *
     * @return the high level terms
     */
    @ManyToOne
    @JoinTable(name = "meddra_hlt_pt", joinColumns = { @JoinColumn(name = "meddra_pt_id") }, inverseJoinColumns = { @JoinColumn(name = "meddra_hlt_id") })
    @Cascade(value = { CascadeType.LOCK })
    public HighLevelTerm getHighLevelTerms() {
        return highLevelTerms;
    }

    /**
     * Sets the high level terms.
     *
     * @param highLevelTerms the new high level terms
     */
    public void setHighLevelTerms(HighLevelTerm highLevelTerms) {
        this.highLevelTerms = highLevelTerms;
    }

    /*
     * public boolean equals(Object o) { if (this == o) return true; if (o == null || getClass() !=
     * o.getClass()) return false;
     * 
     * final DiseaseTerm that = (DiseaseTerm) o;
     * 
     * if (ctepTerm != null ? !ctepTerm.equals(that.ctepTerm) : that.ctepTerm != null) return false;
     * if (medraCode != null ? !medraCode.equals(that.medraCode) : that.medraCode != null) return
     * false;
     * 
     * return true; }
     * 
     * public int hashCode() { int result; result = (ctepTerm != null ? ctepTerm.hashCode() : 0);
     * result = 29 * result + (medraCode != null ? medraCode.hashCode() : 0); return result; }
     */
}
