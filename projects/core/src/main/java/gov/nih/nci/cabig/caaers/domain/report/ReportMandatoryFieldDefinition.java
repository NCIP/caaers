package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name="MANDATORY_FIELD_DEFS")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
    @Parameter(name="sequence", value="seq_mandatory_field_defs_id")
        }
)
public class ReportMandatoryFieldDefinition extends AbstractMutableDomainObject implements Serializable {

	private String fieldPath;
	private Boolean mandatory;


	public ReportMandatoryFieldDefinition(){
		this("", Boolean.FALSE);
	}

	public ReportMandatoryFieldDefinition( String fieldPath){
		this(fieldPath, Boolean.FALSE);
	}
	public ReportMandatoryFieldDefinition(String fieldPath, Boolean mandatory){
		this.mandatory = mandatory;
		this.fieldPath = fieldPath;
	}

	public String getFieldPath() {
		return fieldPath;
	}
	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String toString() {
		return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString()+ "]";
	}
}
