package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table (name="disease_history")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_disease_history_id")
    }
)
public class DiseaseHistory extends AbstractIdentifiableDomainObject
{			
	private String diseaseTerm;
	private String diseaseSite;
	private Date diagnosisDate;

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}
	
	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	
	public String getDiseaseSite() {
		return diseaseSite;
	}
	
	public void setDiseaseSite(String diseaseSite) {
		this.diseaseSite = diseaseSite;
	}
	
	public String getDiseaseTerm() {
		return diseaseTerm;
	}
	
	public void setDiseaseTerm(String diseaseTerm) {
		this.diseaseTerm = diseaseTerm;
	}
}

