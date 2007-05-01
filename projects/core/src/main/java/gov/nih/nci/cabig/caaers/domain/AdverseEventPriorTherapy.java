package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_prior_therapies")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_prior_therapies_id")
    }
)
public class AdverseEventPriorTherapy extends AbstractAdverseEventReportChild {
    private PriorTherapy priorTherapy;
    private String other;
    private Date startDate;
    private Date endDate;

    ////// LOGIC

    @Transient
    public String getName() {
        if (getPriorTherapy() != null) {
            return getPriorTherapy().getText();
        } else if (getOther() != null) {
            return "Other: " + getOther();
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @ManyToOne
	public PriorTherapy getPriorTherapy() {
		return priorTherapy;
	}

	public void setPriorTherapy(PriorTherapy priorTherapy) {
		this.priorTherapy = priorTherapy;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
}
