/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.meddra;

import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

 
/**
 * The Class AbstractMeddraDomainObject.
 *
 * @author Krikor Krumlian
 */

@MappedSuperclass
public class AbstractMeddraDomainObject implements DomainObject {

    /** The id. */
    private Integer id;

    /** The meddra code. */
    private String meddraCode;

    /** The meddra term. */
    private String meddraTerm;

    /** The costart symbol. */
    private String costartSymbol;

    /** The harts code. */
    private String hartsCode;

    /** The who art code. */
    private String whoArtCode;

    /** The icd10 code. */
    private String icd10Code;

    /** The icd9 code. */
    private String icd9Code;

    /** The icd9 cm code. */
    private String icd9CmCode;

    /** The jart code. */
    private String jartCode;
    
    /** The meddra version. */
    private MeddraVersion meddraVersion;
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.DomainObject#getId()
     */
    @Id
    public Integer getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.DomainObject#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the costart symbol.
     *
     * @return the costart symbol
     */
    @Column(name = "costart_symbol")
    public String getCostartSymbol() {
        return costartSymbol;
    }

    /**
     * Sets the costart symbol.
     *
     * @param costartSymbol the new costart symbol
     */
    public void setCostartSymbol(String costartSymbol) {
        this.costartSymbol = costartSymbol;
    }

    /**
     * Gets the harts code.
     *
     * @return the harts code
     */
    @Column(name = "harts_code")
    public String getHartsCode() {
        return hartsCode;
    }

    /**
     * Sets the harts code.
     *
     * @param hartsCode the new harts code
     */
    public void setHartsCode(String hartsCode) {
        this.hartsCode = hartsCode;
    }

    /**
     * Gets the icd10 code.
     *
     * @return the icd10 code
     */
    @Column(name = "icd10_code")
    public String getIcd10Code() {
        return icd10Code;
    }

    /**
     * Sets the icd10 code.
     *
     * @param icd10Code the new icd10 code
     */
    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    /**
     * Gets the icd9 cm code.
     *
     * @return the icd9 cm code
     */
    @Column(name = "icd9_cm_code")
    public String getIcd9CmCode() {
        return icd9CmCode;
    }

    /**
     * Sets the icd9 cm code.
     *
     * @param icd9CmCode the new icd9 cm code
     */
    public void setIcd9CmCode(String icd9CmCode) {
        this.icd9CmCode = icd9CmCode;
    }

    /**
     * Gets the icd9 code.
     *
     * @return the icd9 code
     */
    @Column(name = "icd9_code")
    public String getIcd9Code() {
        return icd9Code;
    }

    /**
     * Sets the icd9 code.
     *
     * @param icd9Code the new icd9 code
     */
    public void setIcd9Code(String icd9Code) {
        this.icd9Code = icd9Code;
    }

    /**
     * Gets the jart code.
     *
     * @return the jart code
     */
    @Column(name = "jart_code")
    public String getJartCode() {
        return jartCode;
    }

    /**
     * Sets the jart code.
     *
     * @param jartCode the new jart code
     */
    public void setJartCode(String jartCode) {
        this.jartCode = jartCode;
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

    /**
     * Gets the who art code.
     *
     * @return the who art code
     */
    @Column(name = "who_art_code")
    public String getWhoArtCode() {
        return whoArtCode;
    }

    /**
     * Sets the who art code.
     *
     * @param whoArtCode the new who art code
     */
    public void setWhoArtCode(String whoArtCode) {
        this.whoArtCode = whoArtCode;
    }
    
    /**
     * Gets the meddra version.
     *
     * @return the meddra version
     */
    @ManyToOne
    @JoinColumn(name="version_id")
    @Cascade(value = { CascadeType.LOCK })
    public MeddraVersion getMeddraVersion(){
    	return meddraVersion;
    }
    
    /**
     * Sets the meddra version.
     *
     * @param meddraVersion the new meddra version
     */
    public void setMeddraVersion(MeddraVersion meddraVersion){
    	this.meddraVersion = meddraVersion;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (meddraCode == null ? 0 : meddraCode.hashCode());
        result = prime * result + (meddraTerm == null ? 0 : meddraTerm.hashCode());
        result = prime * result + (meddraVersion == null ? 0 : meddraVersion.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractMeddraDomainObject other = (AbstractMeddraDomainObject) obj;
        if (meddraCode == null) {
            if (other.meddraCode != null) {
                return false;
            }
        } else if (!meddraCode.equals(other.meddraCode)) {
            return false;
        }
        if (meddraTerm == null) {
            if (other.meddraTerm != null) {
                return false;
            }
        } else if (!meddraTerm.equals(other.meddraTerm)) {
            return false;
        }
        if (meddraVersion == null) {
            if (other.meddraVersion != null) {
                return false;
            }
        } else if (!meddraVersion.equals(other.meddraVersion)) {
            return false;
        }
        return true;
    }
    
    
}
