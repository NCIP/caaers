package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the saved searches in the advancedsearch page.
 * @author Sameer Sawant
 */
@Entity
@Table(name = "searches")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_searches_id") })
public class Search extends AbstractMutableDomainObject{
	
	/**
	 * This is the loginId of the user who created the search
	 */
	private String loginId;
	
	/**
	 * This is the name of the saved search
	 */
	private String name;
	
	/**
	 * This is the optional description for the saved search
	 */
	private String description;
	
	/**
	 * This is the xml string of the list of criterias.
	 */
	private String criteriaXml;
	
	/**
	 * This is the date on which the search was created and saved.
	 */
	private Date createdDate;

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the criteria
	 */
	public String getCriteriaXml() {
		return criteriaXml;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteriaXml(String criteriaXml) {
		this.criteriaXml = criteriaXml;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}