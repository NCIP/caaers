package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("INV")
public class InvestigatorHeldIND extends INDHolder {
    private Investigator investigator;

    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    @JoinColumn(name = "inv_id", nullable = false)
    public Investigator getInvestigator() {
        return investigator;
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

    @Override
    @Transient
    public String getName() {
        if (investigator != null) return investigator.getFullName();
        return null;
    }
}
