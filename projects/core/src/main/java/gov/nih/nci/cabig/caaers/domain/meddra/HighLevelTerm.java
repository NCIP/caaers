package gov.nih.nci.cabig.caaers.domain.meddra;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meddra_hlt")
public class HighLevelTerm extends AbstractMeddraDomainObject {

    private List<PreferredTerm> preferredTerms;

    private List<HighLevelGroupTerm> highLevelGroupTerms;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "highlevelterms")
    public List<HighLevelGroupTerm> getHighLevelGroupTerms() {
        return highLevelGroupTerms;
    }

    public void setHighLevelGroupTerms(List<HighLevelGroupTerm> highLevelGroupTerms) {
        this.highLevelGroupTerms = highLevelGroupTerms;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "meddra_hlt_pt", joinColumns = { @JoinColumn(name = "meddra_hlt_id") }, inverseJoinColumns = { @JoinColumn(name = "meddra_pt_id") })
    public List<PreferredTerm> getPreferredTerms() {
        return preferredTerms;
    }

    public void setPreferredTerms(List<PreferredTerm> preferredTerms) {
        this.preferredTerms = preferredTerms;
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