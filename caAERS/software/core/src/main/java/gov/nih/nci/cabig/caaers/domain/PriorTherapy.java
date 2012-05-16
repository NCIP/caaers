package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class PriorTherapy extends AbstractMutableRetireableDomainObject {
	
	//id of surgery prior therapy
	/** The SURGERY. */
	public static int SURGERY = 16;
	
	//id of radiation prior therapy
	/** The RADIATION. */
	public static int RADIATION = 17;
	
	//id of no-prior therapy
	/** The N o_ prio r_ therapy. */
	public static int NO_PRIOR_THERAPY = 13;
	
    /** The text. */
    private String text;

    /** The meddra term. */
    private String meddraTerm; // MedDRA v9.0 Lower Level Term (LLT)
    
    private String therapyType;

    /** The meddra code. */
    private String meddraCode; // MedDRA v9.0 LLT/CTEP Provisional Code
    
    private Date lastSynchedDate;

    public Date getLastSynchedDate() {
		return lastSynchedDate;
	}

	public void setLastSynchedDate(Date lastSynchedDate) {
		this.lastSynchedDate = lastSynchedDate;
	}

	/**
     * Gets the text.
     *
     * @return the text
     */
    @Column(name = "therapy_text")
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the meddra code.
     *
     * @return the meddra code
     */
    @Column(name = "meddra_code")
    public String getMeddraCode() {
        return meddraCode;
    }

    /**
     * Sets the meddra code.
     *
     * @param meddraCode the new meddra code
     */
    public void setMeddraCode(String meddraCode) {
        this.meddraCode = meddraCode;
    }

    /**
     * Gets the meddra term.
     *
     * @return the meddra term
     */
    @Column(name = "meddra_term")
    public String getMeddraTerm() {
        return meddraTerm;
    }

    /**
     * Sets the meddra term.
     *
     * @param meddraTerm the new meddra term
     */
    public void setMeddraTerm(String meddraTerm) {
        this.meddraTerm = meddraTerm;
    }

    public String getTherapyType() {
        return therapyType;
    }

    public void setTherapyType(String therapyType) {
        this.therapyType = therapyType;
    }

    /**
     * Will tell whether this prior therapy can have agents associated to it.
     *
     * @return true, if is agents possible
     */
    @Transient
    public boolean isAgentsPossible(){
    	int id = getId();
    	return !(id == SURGERY || id == RADIATION || id == NO_PRIOR_THERAPY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriorTherapy)) return false;

        PriorTherapy that = (PriorTherapy) o;

        if (meddraCode != null ? !meddraCode.equals(that.meddraCode) : that.meddraCode != null) return false;
        if (meddraTerm != null ? !meddraTerm.equals(that.meddraTerm) : that.meddraTerm != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (therapyType != null ? !therapyType.equals(that.therapyType) : that.therapyType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (meddraTerm != null ? meddraTerm.hashCode() : 0);
        result = 31 * result + (therapyType != null ? therapyType.hashCode() : 0);
        result = 31 * result + (meddraCode != null ? meddraCode.hashCode() : 0);
        return result;
    }
}
