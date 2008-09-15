package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

/**
 * 
 * @author Biju
 *
 */
@Embeddable
public class Address {
	
	private int code; //to force hibernate to load the object
	private Integer zip;
	private String city;
	private String state;
	private String street;
	
	
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "" + street + " " + city + " " + state + " " + zip;
	}
	
	
}
