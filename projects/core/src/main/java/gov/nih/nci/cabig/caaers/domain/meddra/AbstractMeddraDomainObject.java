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
 * @author Krikor Krumlian
 */

@MappedSuperclass
public class AbstractMeddraDomainObject implements DomainObject {

    private Integer id;

    private String meddraCode;

    private String meddraTerm;

    private String costartSymbol;

    private String hartsCode;

    private String whoArtCode;

    private String icd10Code;

    private String icd9Code;

    private String icd9CmCode;

    private String jartCode;
    
    private MeddraVersion meddraVersion;
    
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "costart_symbol")
    public String getCostartSymbol() {
        return costartSymbol;
    }

    public void setCostartSymbol(String costartSymbol) {
        this.costartSymbol = costartSymbol;
    }

    @Column(name = "harts_code")
    public String getHartsCode() {
        return hartsCode;
    }

    public void setHartsCode(String hartsCode) {
        this.hartsCode = hartsCode;
    }

    @Column(name = "icd10_code")
    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    @Column(name = "icd9_cm_code")
    public String getIcd9CmCode() {
        return icd9CmCode;
    }

    public void setIcd9CmCode(String icd9CmCode) {
        this.icd9CmCode = icd9CmCode;
    }

    @Column(name = "icd9_code")
    public String getIcd9Code() {
        return icd9Code;
    }

    public void setIcd9Code(String icd9Code) {
        this.icd9Code = icd9Code;
    }

    @Column(name = "jart_code")
    public String getJartCode() {
        return jartCode;
    }

    public void setJartCode(String jartCode) {
        this.jartCode = jartCode;
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

    @Column(name = "who_art_code")
    public String getWhoArtCode() {
        return whoArtCode;
    }

    public void setWhoArtCode(String whoArtCode) {
        this.whoArtCode = whoArtCode;
    }
    
    @ManyToOne
    @JoinColumn(name="version_id")
    @Cascade(value = { CascadeType.LOCK })
    public MeddraVersion getMeddraVersion(){
    	return meddraVersion;
    }
    
    public void setMeddraVersion(MeddraVersion meddraVersion){
    	this.meddraVersion = meddraVersion;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (meddraCode == null ? 0 : meddraCode.hashCode());
        result = prime * result + (meddraTerm == null ? 0 : meddraTerm.hashCode());
        result = prime * result + (meddraVersion == null ? 0 : meddraVersion.hashCode());
        return result;
    }

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