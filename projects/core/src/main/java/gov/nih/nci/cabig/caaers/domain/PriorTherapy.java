package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the PriorTherapy domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "prior_therapies")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_prior_therapies_id") })
public class PriorTherapy extends AbstractImmutableDomainObject {
	
	//id of surgery prior therapy
	public static int SURGERY = 16;
	
	//id of radiation prior therapy
	public static int RADIATION = 17;
	
	//id of no-prior therapy
	public static int NO_PRIOR_THERAPY = 13;
	
    private String text;

    private String meddraTerm; // MedDRA v9.0 Lower Level Term (LLT)

    private String meddraCode; // MedDRA v9.0 LLT/CTEP Provisional Code

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
    
    /**
     * Will tell whether this prior therapy can have agents associated to it. 
     * @return
     */
    @Transient
    public boolean isAgentsPossible(){
    	int id = getId();
    	return !(id == SURGERY || id == RADIATION || id == NO_PRIOR_THERAPY);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((meddraCode == null) ? 0 : meddraCode.hashCode());
        result = prime * result + ((meddraTerm == null) ? 0 : meddraTerm.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final PriorTherapy other = (PriorTherapy) obj;
        if (meddraCode == null) {
            if (other.meddraCode != null) return false;
        } else if (!meddraCode.equals(other.meddraCode)) return false;
        if (meddraTerm == null) {
            if (other.meddraTerm != null) return false;
        } else if (!meddraTerm.equals(other.meddraTerm)) return false;
        if (text == null) {
            if (other.text != null) return false;
        } else if (!text.equals(other.text)) return false;
        return true;
    }

}
