package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

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
public class Lab extends AbstractDomainObject {
    private AdverseEventReport report;

    private String name;
    private String units;  // TODO: source this from caDSR

    private LabValue baseline = new LabValue();
    private LabValue nadir = new LabValue();
    private LabValue recovery = new LabValue();

    ////// BEAN PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false, nullable=false)
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }

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
        return recovery;
    }

    public void setRecovery(LabValue recovery) {
        this.recovery = recovery;
    }
}
