package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_labs")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_labs_id")
    }
)
public class Lab extends AbstractExpeditedReportCollectionElementChild {
    private String name;
    private String units;  // TODO: source this from caDSR

    private LabValue baseline;
    private LabValue nadir;
    private LabValue recovery;

    ////// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "baseline_value")),
        @AttributeOverride(name = "date", column = @Column(name = "baseline_date"))
    })
    public LabValue getBaseline() {
        if (baseline == null) baseline = new LabValue();
        return baseline;
    }

    public void setBaseline(LabValue baseline) {
        this.baseline = baseline;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "nadir_value")),
        @AttributeOverride(name = "date", column = @Column(name = "nadir_date"))
    })
    public LabValue getNadir() {
        if (nadir == null) nadir = new LabValue();
        return nadir;
    }

    public void setNadir(LabValue nadir) {
        this.nadir = nadir;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "recovery_value")),
        @AttributeOverride(name = "date", column = @Column(name = "recovery_date"))
    })
    public LabValue getRecovery() {
        if (recovery == null) recovery = new LabValue();
        return recovery;
    }

    public void setRecovery(LabValue recovery) {
        this.recovery = recovery;
    }
}
