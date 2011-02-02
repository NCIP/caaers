package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

 
/**
 * The Class Address.
 *
 * @author Biju
 */
@Embeddable
public class Address {

    /** The code. */
    private int code; //to force hibernate to load the object
    
    /** The zip. */
    private String zip;
    
    /** The city. */
    private String city;
    
    /** The state. */
    private String state;
    
    /** The street. */
    private String street;
    
    /** The country. */
    private String country;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public int getCode(){
    	return code;
    }
    
    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(int code) {
		this.code = code;
	}
    
    /**
     * Gets the zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip.
     *
     * @param zip the new zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
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
     * Gets the street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street.
     *
     * @param street the new street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Street:" + street + ", City:" + city + ", State:" + state + ", ZIP:" + zip + ", Country:" + country;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
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
     * Clear.
     */
    public void clear(){
        setCountry(null);
        setZip(null);
        setState(null);
        setCity(null);
        setStreet(null);

    }

    /**
     * Sync.
     *
     * @param a the a
     */
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
