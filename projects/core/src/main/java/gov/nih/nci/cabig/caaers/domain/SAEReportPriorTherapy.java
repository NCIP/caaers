package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

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

    private PriorTherapy priorTherapy;
    private String other;
    private DateValue startDate;
    private DateValue endDate;
    private LazyListHelper lazyListHelper;

    public SAEReportPriorTherapy() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(PriorTherapyAgent.class);
        this.startDate = new DateValue();
        this.endDate = new DateValue();
    }

    private <T> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass, new SAEReportPriorTherapyFactory<T>(klass, this));
    }
    
    public boolean equals(PriorTherapy priorTherapy, String other){
    	return StringUtils.equals(this.other, other) && ObjectUtils.equals(this.priorTherapy, priorTherapy);
    }
    // //// LOGIC
    
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
        if (priorTherapyAgent != null) priorTherapyAgent.setSaeReportPriorTherapy(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate prior therapy agents")
    public List<PriorTherapyAgent> getPriorTherapyAgents() {
        return lazyListHelper.getLazyList(PriorTherapyAgent.class);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "ae_prior_therapy_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<PriorTherapyAgent> getPriorTherapyAgentsInternal() {
        return lazyListHelper.getInternalList(PriorTherapyAgent.class);
    }

    public void setPriorTherapyAgentsInternal(List<PriorTherapyAgent> priorTherapyAgentsInternal) {
        lazyListHelper.setInternalList(PriorTherapyAgent.class, priorTherapyAgentsInternal);
    }

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

    public void setEndDate(DateValue endDate) {
        this.endDate = endDate;
    }

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

    public void setStartDate(DateValue startDate) {
        this.startDate = startDate;
    }

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


    public static SAEReportPriorTherapy createSAEReportPriorTherapy(StudyParticipantPriorTherapy studyParticipantPriorTherapy) {

        if (studyParticipantPriorTherapy != null) {
            SAEReportPriorTherapy saeReportPriorTherapy = copyBasicProperties(studyParticipantPriorTherapy);

            for (StudyParticipantPriorTherapyAgent priorTherapyAgent : studyParticipantPriorTherapy.getPriorTherapyAgents()) {
                saeReportPriorTherapy.addPriorTherapyAgent(PriorTherapyAgent.createSaeReportPriorTherapyAgent(priorTherapyAgent));
            }


            return saeReportPriorTherapy;

        }
        return null;

    }

    private static SAEReportPriorTherapy copyBasicProperties(Object object) {
        SAEReportPriorTherapy saeReportPriorTherapy = new SAEReportPriorTherapy();
        BeanUtils.copyProperties(object, saeReportPriorTherapy, new String[]{"id", "gridId",
                "version", "priorTherapyAgents", "report", "priorTherapyAgentsInternal"});
        return saeReportPriorTherapy;
    }

    public SAEReportPriorTherapy copy() {
        SAEReportPriorTherapy saeReportPriorTherapy = copyBasicProperties(this);
        for (PriorTherapyAgent priorTherapyAgent : this.getPriorTherapyAgents()) {
            saeReportPriorTherapy.addPriorTherapyAgent(priorTherapyAgent.copy());
        }

        return saeReportPriorTherapy;

    }
}
