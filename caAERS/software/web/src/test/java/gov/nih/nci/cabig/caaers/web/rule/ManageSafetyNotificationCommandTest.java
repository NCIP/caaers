/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Notification;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Biju Joseph
 * @date 3/23/12
 */
public class ManageSafetyNotificationCommandTest extends TestCase {

    public void testSyncRecipients(){
        ManageSafetyNotificationCommand cmd = new ManageSafetyNotificationCommand();
        cmd.setNotification(new Notification());

        cmd.setRecipientEmails(Arrays.asList("a","b"));

        cmd.syncRecipients();

        assertEquals("a,b", cmd.getNotification().getEmails());



    }
    
}
