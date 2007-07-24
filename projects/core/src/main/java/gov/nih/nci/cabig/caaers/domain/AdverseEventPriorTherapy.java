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
	
	
	
}
