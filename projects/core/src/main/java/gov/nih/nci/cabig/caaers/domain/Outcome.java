package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the Outcome domain object associated with the Adverse event report.
 *
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Entity
@Table(name = "outcomes")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_outcomes_id")})
public class Outcome extends AbstractMutableDomainObject {

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

    public Outcome copy() {
        Outcome outcome = new Outcome();
        BeanUtils.copyProperties(this, outcome, new String[]{"id", "gridId", "version"});
        return outcome;
    }
}
