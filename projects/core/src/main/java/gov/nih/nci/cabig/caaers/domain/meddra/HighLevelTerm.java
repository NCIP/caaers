package gov.nih.nci.cabig.caaers.domain.meddra;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "meddra_hlt")
public class HighLevelTerm extends AbstractMeddraDomainObject {

    private HighLevelGroupTerm highLevelGroupTerms;

    @ManyToOne
    @JoinTable(name = "meddra_hlgt_hlt", joinColumns = { @JoinColumn(name = "meddra_hlt_id") }, inverseJoinColumns = { @JoinColumn(name = "meddra_hlgt_id") })
    @Cascade(value = { CascadeType.LOCK })
    public HighLevelGroupTerm getHighLevelGroupTerms() {
        return highLevelGroupTerms;
    }

    public void setHighLevelGroupTerms(HighLevelGroupTerm highLevelGroupTerms) {
        this.highLevelGroupTerms = highLevelGroupTerms;
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