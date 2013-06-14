/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ReportPerson#copy()
     */
    @Override
    public Reporter copy() {
        return (Reporter) super.copy();    //To change body of overridden methods use File | Settings | File Templates.
    }
    
    /**
     * This method will copy a user, and set it as the referenced user of this reporter.
     *
     * @param person the person
     */
    public void copy(Person person){
    	if(person == null) return;
    	
    	this.setFirstName(person.getFirstName());
    	this.setLastName(person.getLastName());
    	this.setMiddleName(person.getMiddleName());
    	this.setTitle(person.getTitle());
    	this.setFax(person.getFaxNumber());
    	this.setPhoneNumber(person.getPhoneNumber());
    	this.setEmailAddress(person.getEmailAddress());
        if(person.isUser()) setCaaersUser(person.getCaaersUser());
    	
    }

    /**
     * Will copy the details from the supplied user. 
     * @param user - A user in caAERS. 
     */
    public void copy(User user) {
        if(user == null) return;
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
    	this.setMiddleName(user.getMiddleName());
    	this.setTitle(user.getTitle());
    	this.setFax(user.getFaxNumber());
    	this.setPhoneNumber(user.getPhoneNumber());
    	this.setEmailAddress(user.getEmailAddress());
        this.setCaaersUser(user);
    }


}
