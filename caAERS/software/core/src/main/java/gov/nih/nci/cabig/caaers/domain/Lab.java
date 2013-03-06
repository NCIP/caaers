/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the Lab domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_labs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_labs_id")})
public class Lab extends AbstractExpeditedReportCollectionElementChild {
    
    /** The lab term. */
    private LabTerm labTerm;

    // private String name;
    /** The other. */
    private String other;

    /** The units. */
    private String units; // TODO: source this from caDSR

    /** The baseline. */
    private LabValue baseline;

    /** The nadir. */
    private LabValue nadir;

    /** The recovery. */
    private LabValue recovery;

    /** The site. */
    private String site;

    /** The lab date. */
    private Date labDate;

    /** The infectious agent. */
    private String infectiousAgent;

    private String normalRange;

    // //// BEAN PROPERTIES

    /**
     * Gets the lab term.
     *
     * @return the lab term
     */
    @ManyToOne
    @JoinColumn(name = "lab_term_id")
    public LabTerm getLabTerm() {
        return labTerm;
    }

    /**
     * Sets the lab term.
     *
     * @param labTerm the new lab term
     */
    public void setLabTerm(LabTerm labTerm) {
        this.labTerm = labTerm;
    }

    /**
     * Gets the units.
     *
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Sets the units.
     *
     * @param units the new units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Gets the baseline.
     *
     * @return the baseline
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zone", column = @Column(name = "baseline_zone")),
            @AttributeOverride(name = "value", column = @Column(name = "baseline_value")),
            @AttributeOverride(name = "date", column = @Column(name = "baseline_date"))})
    public LabValue getBaseline() {
        if (baseline == null) baseline = new LabValue();
        return baseline;
    }

    /**
     * Sets the baseline.
     *
     * @param baseline the new baseline
     */
    public void setBaseline(LabValue baseline) {
        this.baseline = baseline;
    }

    /**
     * Gets the nadir.
     *
     * @return the nadir
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zone", column = @Column(name = "nadir_zone")),
            @AttributeOverride(name = "value", column = @Column(name = "nadir_value")),
            @AttributeOverride(name = "date", column = @Column(name = "nadir_date"))})
    public LabValue getNadir() {
        if (nadir == null) nadir = new LabValue();
        return nadir;
    }

    /**
     * Sets the nadir.
     *
     * @param nadir the new nadir
     */
    public void setNadir(LabValue nadir) {
        this.nadir = nadir;
    }

    /**
     * Gets the recovery.
     *
     * @return the recovery
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zone", column = @Column(name = "recovery_zone")),
            @AttributeOverride(name = "value", column = @Column(name = "recovery_value")),
            @AttributeOverride(name = "date", column = @Column(name = "recovery_date"))})
    public LabValue getRecovery() {
        if (recovery == null) recovery = new LabValue();
        return recovery;
    }

    /**
     * Sets the recovery.
     *
     * @param recovery the new recovery
     */
    public void setRecovery(LabValue recovery) {
        this.recovery = recovery;
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
     * Gets the infectious agent.
     *
     * @return the infectious agent
     */
    public String getInfectiousAgent() {
        return infectiousAgent;
    }

    /**
     * Sets the infectious agent.
     *
     * @param infectiousAgent the new infectious agent
     */
    public void setInfectiousAgent(String infectiousAgent) {
        this.infectiousAgent = infectiousAgent;
    }

    /**
     * Gets the lab date.
     *
     * @return the lab date
     */
    public Date getLabDate() {
        return labDate;
    }

    /**
     * Sets the lab date.
     *
     * @param labDate the new lab date
     */
    public void setLabDate(Date labDate) {
        this.labDate = labDate;
    }

    /**
     * Gets the site.
     *
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the site.
     *
     * @param site the new site
     */
    public void setSite(String site) {
        this.site = site;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        if (getLabTerm() != null) return getLabTerm().getTerm();
        return null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ("" + getName() + other).hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;

        if (!(obj instanceof Lab)) return false;

        Lab l = (Lab) obj;
        if (!StringUtils.equals(other, l.other)) return false;

        if (l.units != null && !units.equals(l.units)) return false;
        if (l.baseline != null && !baseline.equals(l.baseline)) return false;

        if (l.recovery != null && !recovery.equals(l.recovery)) return false;
        if (l.nadir != null && !nadir.equals(l.nadir)) return false;

        return true;
    }


    /**
     * Copy.
     *
     * @return the lab
     */
    public Lab copy() {
        Lab lab = new Lab();
        BeanUtils.copyProperties(this, lab, new String[]{"id", "gridId",
                "version", "report"});

        return lab;

    }


}
