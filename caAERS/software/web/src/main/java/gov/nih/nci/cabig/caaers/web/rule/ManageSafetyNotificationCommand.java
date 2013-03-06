/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.domain.Notification;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections15.list.LazyList;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class ManageSafetyNotificationCommand {

    protected Map<String, String> roles;
    private String mode;
    private String substitution;
    
    private Notification notification;

    
    private List<String> recipientEmails;
    private List<String> recipientRoles;
    
    public void syncRecipients(){
        
        ArrayList<String> eList = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(recipientEmails)){
            for(String s :  recipientEmails)
            if(StringUtils.isNotEmpty(s)) eList.add(s);
        }
        notification.setRecipientEmails(eList);


        ArrayList<String> rList = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(recipientRoles)){
            for(String s :  recipientRoles)
                if(StringUtils.isNotEmpty(s)) rList.add(s);
        }
        notification.setRecipientRoles(rList);
    }

    public List<String> getRecipientEmails() {
        return recipientEmails;
    }

    public void setRecipientEmails(List<String> recipientEmails) {
        this.recipientEmails = recipientEmails;
    }

    public List<String> getRecipientRoles() {
        return recipientRoles;
    }

    public void setRecipientRoles(List<String> recipientRoles) {
        this.recipientRoles = recipientRoles;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public String getSubstitution() {
        return substitution;
    }

    public void setSubstitution(String substitution) {
        this.substitution = substitution;
    }

    
}
