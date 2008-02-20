package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class represents the SAEReportPreExistingCondition domain object associated with the Adverse event report.
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_pre_existing_conds")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_pre_existing_conds")
    }
)
public class SAEReportPreExistingCondition extends AbstractExpeditedReportCollectionElementChild {
    private PreExistingCondition preExistingCondition;
    private String other;

    ////// LOGIC

    @Transient
    public String getName() {
        if (getPreExistingCondition() != null) {
            return getPreExistingCondition().getText();
        } else if (getOther() != null) {
            return "Other: " + getOther();
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    @ManyToOne
	public PreExistingCondition getPreExistingCondition() {
		return preExistingCondition;
	}

	public void setPreExistingCondition(PreExistingCondition preExistingCondition) {
		this.preExistingCondition = preExistingCondition;
	}
   
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

   
}
