package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.Date;

/**
 * This class represents the Outcome domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "outcomes")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ae_outcome_id") })
public class Outcome extends AbstractExpeditedReportCollectionElementChild {

    private OutcomeType outcomeType;

    private Date date;

    private String other;

    @Column(name = "outcome_type_code")
    @Type(type = "outcomeType")
    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    @Column(name = "incident_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
