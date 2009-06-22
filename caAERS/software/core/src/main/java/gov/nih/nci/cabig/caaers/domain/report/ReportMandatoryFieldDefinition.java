package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "MANDATORY_FIELD_DEFS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_mandatory_field_defs_id") })
// TODO: why store field definitions for mandatory=false?
public class ReportMandatoryFieldDefinition extends AbstractMutableDomainObject implements
                Serializable {

    private String fieldPath;

    private Mandatory mandatory;

    public ReportMandatoryFieldDefinition() {
    }

    public ReportMandatoryFieldDefinition(String fieldPath) {
        this(fieldPath, Mandatory.OPTIONAL);
    }

    public ReportMandatoryFieldDefinition(String fieldPath, Mandatory mandatory) {
        this.mandatory = mandatory;
        this.fieldPath = fieldPath;
    }

    // @Transient
    // public boolean isMandatory() {
    // return getMandatory() != null && getMandatory();
    // }

    public String getFieldPath() {
        return fieldPath;
    }

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }
    @Type(type="mandatoryType")
    @Column(name="mandatory")
    public Mandatory getMandatory() {
        return mandatory;
    }

    public void setMandatory(Mandatory mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString() + "]";
    }
}
