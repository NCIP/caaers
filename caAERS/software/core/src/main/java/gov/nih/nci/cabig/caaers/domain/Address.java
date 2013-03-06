/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

/**
 * @author Biju
 */
@Embeddable
public class Address {

    private int code; //to force hibernate to load the object
    private String zip;
    private String city;
    private String state;
    private String street;
    private String country;

    public int getCode(){
    	return code;
    }
    public void setCode(int code) {
		this.code = code;
	}
    
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
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
        return "Street:" + street + ", City:" + city + ", State:" + state + ", ZIP:" + zip + ", Country:" + country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void clear(){
        setCountry(null);
        setZip(null);
        setState(null);
        setCity(null);
        setStreet(null);

    }

    public void sync(Address a){
        if(a == null){
            clear();
            return;
        }
        setCountry(a.getCountry());
        setZip(a.getZip());
        setState(a.getState());
        setCity(a.getCity());
        setStreet(a.getStreet());
    }
}
