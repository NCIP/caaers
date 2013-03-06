/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /** The outcome type. */
    private OutcomeType outcomeType;

    /** The date. */
    private Date date;

    /** The other. */
    private String other;

    /**
     * Gets the outcome type.
     *
     * @return the outcome type
     */
    @Column(name = "outcome_type_code")
    @Type(type = "outcomeType")
    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    /**
     * Sets the outcome type.
     *
     * @param outcomeType the new outcome type
     */
    public void setOutcomeType(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    @Column(name = "incident_date")
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

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
     * Copy.
     *
     * @return the outcome
     */
    public Outcome copy() {
        Outcome outcome = new Outcome();
        BeanUtils.copyProperties(this, outcome, new String[]{"id", "gridId", "version"});
        return outcome;
    }
}
