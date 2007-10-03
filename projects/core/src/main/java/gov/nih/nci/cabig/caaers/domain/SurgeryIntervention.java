package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table(name="ae_surgery_interventions")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_surgery_interventions_id")
    }
)
public class SurgeryIntervention extends AbstractExpeditedReportCollectionElementChild {
   
    private String treatmentArm;
    private String description;
    private AnatomicSite anatomicSite;
    private Date interventionDate;
	

    ////// LOGIC

    ////// BEAN PROPERTIES
    
    public String getDescription() {
		return description;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getInterventionDate() {
		return interventionDate;
	}
	
	public void setInterventionDate(Date interventionDate) {
		this.interventionDate = interventionDate;
	}
	
	@ManyToOne
	public AnatomicSite getAnatomicSite() {
		return anatomicSite;
	}

	public void setAnatomicSite(AnatomicSite anatomicSite) {
		this.anatomicSite = anatomicSite;
	}
	
	public String getTreatmentArm() {
		return treatmentArm;
	}
	
	public void setTreatmentArm(String treatmentArm) {
		this.treatmentArm = treatmentArm;
	}

	
}
