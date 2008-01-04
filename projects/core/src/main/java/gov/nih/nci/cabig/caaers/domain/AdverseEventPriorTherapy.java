package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import java.util.List;

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
public class AdverseEventPriorTherapy extends AbstractExpeditedReportCollectionElementChild {
    private PriorTherapy priorTherapy;
    private String other;
    private Date startDate;
    private Date endDate;
    
    private LazyListHelper lazyListHelper;
    
    public AdverseEventPriorTherapy() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(PriorTherapyAgent.class);
    }

    private <T> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass,
            new AdverseEventPriorTherapyFactory<T>(klass, this));
    }

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
	
	public void addPriorTherapyAgent(PriorTherapyAgent priorTherapyAgent) {
		getPriorTherapyAgentsInternal().add(priorTherapyAgent);
	        if (priorTherapyAgent != null) priorTherapyAgent.setAdverseEventPriorTherapy(this);
	}

	/**
	 * @return a wrapped list which will never throw an
	 *         {@link IndexOutOfBoundsException}
	 */
	@Transient
	public List<PriorTherapyAgent> getPriorTherapyAgents() {
		return lazyListHelper.getLazyList(PriorTherapyAgent.class);
	}
	
	// This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="ae_prior_therapy_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<PriorTherapyAgent> getPriorTherapyAgentsInternal() {
        return lazyListHelper.getInternalList(PriorTherapyAgent.class);
    }

    protected void setPriorTherapyAgentsInternal(List<PriorTherapyAgent> priorTherapyAgentsInternal) {
        lazyListHelper.setInternalList(PriorTherapyAgent.class, priorTherapyAgentsInternal);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		result = prime * result	+ ((priorTherapy == null) ? 0 : priorTherapy.hashCode());
		result = prime * result	+ ((startDate == null) ? 0 : startDate.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		final AdverseEventPriorTherapy other = (AdverseEventPriorTherapy) obj;
		if (endDate == null) {
			if (other.endDate != null) return false;
		} else if (!endDate.equals(other.endDate))	return false;
		if (this.other == null) {
			if (other.other != null) return false;
		} else if (!this.other.equals(other.other)) return false;
		if (priorTherapy == null) {
			if (other.priorTherapy != null) return false;
		} else if (!priorTherapy.equals(other.priorTherapy))return false;
		if (startDate == null) {
			if (other.startDate != null) return false;
		} else if (!startDate.equals(other.startDate)) return false;
		return true;
	}
	
}
