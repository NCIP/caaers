package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the PreExistingCondition domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "pre_existing_conditions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_pre_existing_conditions_id") })
public class PreExistingCondition extends AbstractImmutableDomainObject {
    private String text;

    private String meddraLlt; // MedDRA v9.0 Lower Level Term (LLT)

    private String meddraLltCode; // MedDRA v9.0 LLT/CTEP Provisional Code

    private String meddraHlgt; // MedDRA v9.0 High Level Group Term (HLGT)/CTEP Condition Category

    @Column(name = "condition_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "meddra_llt_code")
    public String getMeddraLltCode() {
        return meddraLltCode;
    }

    public void setMeddraLltCode(String meddraLltCode) {
        this.meddraLltCode = meddraLltCode;
    }

    @Column(name = "meddra_hlgt")
    public String getMeddraHlgt() {
        return meddraHlgt;
    }

    public void setMeddraHlgt(String meddraHlgt) {
        this.meddraHlgt = meddraHlgt;
    }

    @Column(name = "meddra_llt")
    public String getMeddraLlt() {
        return meddraLlt;
    }

    public void setMeddraLlt(String meddraLlt) {
        this.meddraLlt = meddraLlt;
    }
    
    
    ///OBJECT METHODS
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((meddraHlgt == null) ? 0 : meddraHlgt.hashCode());
		result = prime * result
				+ ((meddraLlt == null) ? 0 : meddraLlt.hashCode());
		result = prime * result
				+ ((meddraLltCode == null) ? 0 : meddraLltCode.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PreExistingCondition other = (PreExistingCondition) obj;
		if (meddraHlgt == null) {
			if (other.meddraHlgt != null)
				return false;
		} else if (!meddraHlgt.equals(other.meddraHlgt))
			return false;
		if (meddraLlt == null) {
			if (other.meddraLlt != null)
				return false;
		} else if (!meddraLlt.equals(other.meddraLlt))
			return false;
		if (meddraLltCode == null) {
			if (other.meddraLltCode != null)
				return false;
		} else if (!meddraLltCode.equals(other.meddraLltCode))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
    

    
    

}
