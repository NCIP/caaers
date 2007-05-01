package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "prior_therapies")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_prior_therapies_id")
    }
)
public class PriorTherapy extends AbstractImmutableDomainObject {
    private String text;
    private String meddraTerm; //MedDRA v9.0 Lower Level Term (LLT)
    private String meddraCode; //MedDRA v9.0 LLT/CTEP Provisional Code

    @Column(name = "therapy_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "meddra_code")
	public String getMeddraCode() {
		return meddraCode;
	}

	public void setMeddraCode(String meddraCode) {
		this.meddraCode = meddraCode;
	}
	@Column(name = "meddra_term")
	public String getMeddraTerm() {
		return meddraTerm;
	}

	public void setMeddraTerm(String meddraTerm) {
		this.meddraTerm = meddraTerm;
	}
    
    
}
