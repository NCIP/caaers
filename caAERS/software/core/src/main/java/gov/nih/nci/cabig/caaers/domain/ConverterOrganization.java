package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class ConverterOrganization.
 */
@Entity
@Table(name = "organizations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_organizations_id") })
public class ConverterOrganization extends AbstractMutableDomainObject{
	
	/** The name. */
	private String name;
	
	/** The nci institute code. */
	private String nciInstituteCode;
	
	/** The description text. */
	private String descriptionText;
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The external id. */
	private String externalId;
	
	/** The type. */
	private String type;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the nci institute code.
	 *
	 * @return the nci institute code
	 */
	public String getNciInstituteCode() {
		return nciInstituteCode;
	}
	
	/**
	 * Sets the nci institute code.
	 *
	 * @param nciInstituteCode the new nci institute code
	 */
	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}
	
	/**
	 * Gets the description text.
	 *
	 * @return the description text
	 */
	public String getDescriptionText() {
		return descriptionText;
	}
	
	/**
	 * Sets the description text.
	 *
	 * @param descriptionText the new description text
	 */
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	@Column(name = "state")
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the external id.
	 *
	 * @return the external id
	 */
	public String getExternalId() {
		return externalId;
	}
	
	/**
	 * Sets the external id.
	 *
	 * @param externalId the new external id
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
