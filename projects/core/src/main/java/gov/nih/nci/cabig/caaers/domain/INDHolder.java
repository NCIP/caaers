package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the INDHolder domain object associated with the Adverse event report.
 * 
 * @author
 * 
 */
@Entity
@Table(name = "ind_holders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dtype")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ind_holders_id") })
public abstract class INDHolder extends AbstractMutableDomainObject {
    private InvestigationalNewDrug investigationalNewDrug;

    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "drug_id")
    public InvestigationalNewDrug getInvestigationalNewDrug() {
        return investigationalNewDrug;
    }

    public void setInvestigationalNewDrug(InvestigationalNewDrug invDrug) {
        investigationalNewDrug = invDrug;
    }

    @Transient
    public abstract String getName();

}
