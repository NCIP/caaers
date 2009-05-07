package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class represents the Reporter domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("R")
public class Reporter extends ReportPerson {

    @Override
    public Reporter copy() {
        return (Reporter) super.copy();    //To change body of overridden methods use File | Settings | File Templates.
    }
    
    /**
     * This method will copy a user, and set it as the referenced user of this reporter.
     * @param user
     */
    public void copy(User user){
    	if(user == null) return;
    	
    	this.setFirstName(user.getFirstName());
    	this.setLastName(user.getLastName());
    	this.setMiddleName(user.getMiddleName());
    	this.setTitle(user.getTitle());
    	this.setFax(user.getFaxNumber());
    	this.setPhoneNumber(user.getPhoneNumber());
    	this.setEmailAddress(user.getEmailAddress());
    	this.setUser(user);
    	
    }
}
