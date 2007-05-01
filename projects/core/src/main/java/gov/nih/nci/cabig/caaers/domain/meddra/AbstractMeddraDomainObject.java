package gov.nih.nci.cabig.caaers.domain.meddra;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.AbstractImmutableDomainObject;
import gov.nih.nci.cabig.caaers.domain.DomainObject;

/**
 * @author Krikor Krumlian
 */

@MappedSuperclass
public class AbstractMeddraDomainObject implements DomainObject{
	
	private static final Log log = LogFactory.getLog(AbstractMeddraDomainObject.class);
	
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
	
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name= "costart_symbol")
	public String getCostartSymbol() {
		return costartSymbol;
	}
	public void setCostartSymbol(String costartSymbol) {
		this.costartSymbol = costartSymbol;
	}
	@Column(name= "harts_code")
	public String getHartsCode() {
		return hartsCode;
	}
	public void setHartsCode(String hartsCode) {
		this.hartsCode = hartsCode;
	}
	@Column(name= "icd10_code")
	public String getIcd10Code() {
		return icd10Code;
	}
	public void setIcd10Code(String icd10Code) {
		this.icd10Code = icd10Code;
	}
	@Column(name= "icd9_cm_code")
	public String getIcd9CmCode() {
		return icd9CmCode;
	}
	public void setIcd9CmCode(String icd9CmCode) {
		this.icd9CmCode = icd9CmCode;
	}
	@Column(name= "icd9_code")
	public String getIcd9Code() {
		return icd9Code;
	}
	public void setIcd9Code(String icd9Code) {
		this.icd9Code = icd9Code;
	}
	@Column(name= "jart_code")
	public String getJartCode() {
		return jartCode;
	}
	public void setJartCode(String jartCode) {
		this.jartCode = jartCode;
	}
	@Column(name= "meddra_code")
	public String getMeddraCode() {
		return meddraCode;
	}

	public void setMeddraCode(String meddraCode) {
		this.meddraCode = meddraCode;
	}

	@Column(name= "meddra_term")
	public String getMeddraTerm() {
		return meddraTerm;
	}

	public void setMeddraTerm(String meddraTerm) {
		this.meddraTerm = meddraTerm;
	}

	@Column(name= "who_art_code")
	public String getWhoArtCode() {
		return whoArtCode;
	}
	public void setWhoArtCode(String whoArtCode) {
		this.whoArtCode = whoArtCode;
	}	
}