/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

 
/**
 * The Class LocalOrganization.
 */
@Entity
@DiscriminatorValue(value="LOCAL")
public class LocalOrganization extends Organization{
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Organization#getName()
     */
    @Column(name = "name")
    public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getDescriptionText()
	 */
	@Transient
	public String getDescriptionText() {
		return descriptionText;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#setDescriptionText(java.lang.String)
	 */
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getCity()
	 */
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getState()
	 */
	@Column(name = "state")
	public String getState() {
		return state;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#setState(java.lang.String)
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getCountry()
	 */
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#setCountry(java.lang.String)
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Organization#getExternalId()
	 */
	@Transient
    public String getExternalId() {
		return externalId;
	}
}
