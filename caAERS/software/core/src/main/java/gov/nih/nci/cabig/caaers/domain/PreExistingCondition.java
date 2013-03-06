/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    /** The text. */
    private String text;

    /** The meddra llt. */
    private String meddraLlt; // MedDRA v9.0 Lower Level Term (LLT)

    /** The meddra llt code. */
    private String meddraLltCode; // MedDRA v9.0 LLT/CTEP Provisional Code

    /** The meddra hlgt. */
    private String meddraHlgt; // MedDRA v9.0 High Level Group Term (HLGT)/CTEP Condition Category


    public PreExistingCondition(){
       this(null);
    }

    public PreExistingCondition(String text){
        this.text = text;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    @Column(name = "condition_text")
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
     * Gets the meddra llt code.
     *
     * @return the meddra llt code
     */
    @Column(name = "meddra_llt_code")
    public String getMeddraLltCode() {
        return meddraLltCode;
    }

    /**
     * Sets the meddra llt code.
     *
     * @param meddraLltCode the new meddra llt code
     */
    public void setMeddraLltCode(String meddraLltCode) {
        this.meddraLltCode = meddraLltCode;
    }

    /**
     * Gets the meddra hlgt.
     *
     * @return the meddra hlgt
     */
    @Column(name = "meddra_hlgt")
    public String getMeddraHlgt() {
        return meddraHlgt;
    }

    /**
     * Sets the meddra hlgt.
     *
     * @param meddraHlgt the new meddra hlgt
     */
    public void setMeddraHlgt(String meddraHlgt) {
        this.meddraHlgt = meddraHlgt;
    }

    /**
     * Gets the meddra llt.
     *
     * @return the meddra llt
     */
    @Column(name = "meddra_llt")
    public String getMeddraLlt() {
        return meddraLlt;
    }

    /**
     * Sets the meddra llt.
     *
     * @param meddraLlt the new meddra llt
     */
    public void setMeddraLlt(String meddraLlt) {
        this.meddraLlt = meddraLlt;
    }
    
    
    ///OBJECT METHODS
    
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
