package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the SAEReportPriorTherapy domain object associated with the Adverse event
 * report.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_prior_therapies")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_prior_therapies_id")})
public class SAEReportPriorTherapy extends AbstractExpeditedReportCollectionElementChild {

    /** The prior therapy. */
    private PriorTherapy priorTherapy;
    
    /** The other. */
    private String other;
    
    /** The start date. */
    private DateValue startDate;
    
    /** The end date. */
    private DateValue endDate;
    
    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;

    /**
     * Instantiates a new sAE report prior therapy.
     */
    public SAEReportPriorTherapy() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(PriorTherapyAgent.class);
        this.startDate = new DateValue();
        this.endDate = new DateValue();
    }

    /**
     * Adds the report child lazy list.
     *
     * @param <T> the generic type
     * @param klass the klass
     */
    private <T> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass, new SAEReportPriorTherapyFactory<T>(klass, this));
    }
    
    /**
     * Equals.
     *
     * @param priorTherapy the prior therapy
     * @param other the other
     * @return true, if successful
     */
    public boolean equals(PriorTherapy priorTherapy, String other){
    	return StringUtils.equals(this.other, other) && ObjectUtils.equals(this.priorTherapy, priorTherapy);
    }
    // //// LOGIC
    
    /**
     * Gets the name.
     *
     * @return the name
     */
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

    // //// BOUND PROPERTIES

    /**
     * Gets the other.
     *
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * Sets the other.
     *
     * @param other the new other
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * Gets the prior therapy.
     *
     * @return the prior therapy
     */
    @ManyToOne
    public PriorTherapy getPriorTherapy() {
        return priorTherapy;
    }

    /**
     * Sets the prior therapy.
     *
     * @param priorTherapy the new prior therapy
     */
    public void setPriorTherapy(PriorTherapy priorTherapy) {
        this.priorTherapy = priorTherapy;
    }

    /**
     * Adds the prior therapy agent.
     *
     * @param priorTherapyAgent the prior therapy agent
     */
    public void addPriorTherapyAgent(PriorTherapyAgent priorTherapyAgent) {
        getPriorTherapyAgentsInternal().add(priorTherapyAgent);
        if (priorTherapyAgent != null) priorTherapyAgent.setSaeReportPriorTherapy(this);
    }

    /**
     * Gets the prior therapy agents.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate prior therapy agents")
    public List<PriorTherapyAgent> getPriorTherapyAgents() {
        return lazyListHelper.getLazyList(PriorTherapyAgent.class);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the prior therapy agents internal.
     *
     * @return the prior therapy agents internal
     */
    @OneToMany
    @JoinColumn(name = "ae_prior_therapy_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<PriorTherapyAgent> getPriorTherapyAgentsInternal() {
        return lazyListHelper.getInternalList(PriorTherapyAgent.class);
    }

    /**
     * Sets the prior therapy agents internal.
     *
     * @param priorTherapyAgentsInternal the new prior therapy agents internal
     */
    public void setPriorTherapyAgentsInternal(List<PriorTherapyAgent> priorTherapyAgentsInternal) {
        lazyListHelper.setInternalList(PriorTherapyAgent.class, priorTherapyAgentsInternal);
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "end_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "end_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "end_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "end_date_zone"))
    })
    public DateValue getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(DateValue endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "start_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "start_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "start_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "start_date_zone"))
    })
    public DateValue getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(DateValue startDate) {
        this.startDate = startDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((other == null) ? 0 : other.hashCode());
        result = prime * result + ((priorTherapy == null) ? 0 : priorTherapy.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());

        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final SAEReportPriorTherapy other = (SAEReportPriorTherapy) obj;
        if (endDate == null) {
            if (other.endDate != null) return false;
        } else if (!endDate.equals(other.endDate)) return false;
        if (this.other == null) {
            if (other.other != null) return false;
        } else if (!this.other.equals(other.other)) return false;
        if (priorTherapy == null) {
            if (other.priorTherapy != null) return false;
        } else if (!priorTherapy.equals(other.priorTherapy)) return false;
        if (startDate == null) {
            if (other.startDate != null) return false;
        } else if (!startDate.equals(other.startDate)) return false;
        return true;
    }


    /**
     * Creates the sae report prior therapy.
     *
     * @param studyParticipantPriorTherapy the study participant prior therapy
     * @return the sAE report prior therapy
     */
    public static SAEReportPriorTherapy createSAEReportPriorTherapy(StudyParticipantPriorTherapy studyParticipantPriorTherapy) {
        if (studyParticipantPriorTherapy != null) {
            SAEReportPriorTherapy saeReportPriorTherapy = copyBasicProperties(studyParticipantPriorTherapy);
            for (StudyParticipantPriorTherapyAgent priorTherapyAgent : studyParticipantPriorTherapy.getPriorTherapyAgents()) {
                if (priorTherapyAgent.getChemoAgent() == null) continue;
                saeReportPriorTherapy.addPriorTherapyAgent(PriorTherapyAgent.createSaeReportPriorTherapyAgent(priorTherapyAgent));
            }
            return saeReportPriorTherapy;
        }
        return null;
    }

    /**
     * Copy basic properties.
     *
     * @param object the object
     * @return the sAE report prior therapy
     */
    private static SAEReportPriorTherapy copyBasicProperties(Object object) {
        SAEReportPriorTherapy saeReportPriorTherapy = new SAEReportPriorTherapy();
        BeanUtils.copyProperties(object, saeReportPriorTherapy, new String[]{"id", "gridId",
                "version", "priorTherapyAgents", "report", "priorTherapyAgentsInternal"});
        return saeReportPriorTherapy;
    }

    /**
     * Copy.
     *
     * @return the sAE report prior therapy
     */
    public SAEReportPriorTherapy copy() {
        SAEReportPriorTherapy saeReportPriorTherapy = copyBasicProperties(this);
        for (PriorTherapyAgent priorTherapyAgent : this.getPriorTherapyAgents()) {
            saeReportPriorTherapy.addPriorTherapyAgent(priorTherapyAgent.copy());
        }

        return saeReportPriorTherapy;

    }
}
