package gov.nih.nci.cabig.caaers.domain.meddra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meddra_llt")
public class LowLevelTerm extends AbstractMeddraDomainObject {

    private String preferredTermId;

    @Column(name = "meddra_pt_id")
    public String getPreferredTermId() {
        return preferredTermId;
    }

    public void setPreferredTermId(String preferredTermId) {
        this.preferredTermId = preferredTermId;
    }

    @Transient
    public String getFullName() {
        return getMeddraCode() + " - " + getMeddraTerm();
    }
}