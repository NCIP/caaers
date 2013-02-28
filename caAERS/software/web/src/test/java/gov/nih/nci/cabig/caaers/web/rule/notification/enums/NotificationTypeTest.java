/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.notification.enums;

import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class NotificationTypeTest extends TestCase {


    public void testGetCode() throws Exception {
       assertEquals(new Integer(1),  NotificationType.EMAIL_NOTIFICATION.getCode());
       assertEquals(new Integer(2),  NotificationType.FAX_NOTIFICATION.getCode());
       assertEquals(new Integer(3),  NotificationType.PAGER_NOTIFICATION.getCode());
       assertEquals(new Integer(4),  NotificationType.XML_NOTIFICATION.getCode());
    }

    public void testGetName() throws Exception {
        assertEquals("EMAIL_NOTIFICATION",  NotificationType.EMAIL_NOTIFICATION.getName());
        assertEquals("FAX_NOTIFICATION",  NotificationType.FAX_NOTIFICATION.getName());
        assertEquals("PAGER_NOTIFICATION",  NotificationType.PAGER_NOTIFICATION.getName());
        assertEquals("XML_NOTIFICATION",  NotificationType.XML_NOTIFICATION.getName());
    }

    public void testGetDisplayName() throws Exception {
        assertEquals("Email Notification",  NotificationType.EMAIL_NOTIFICATION.getDisplayName());
    }
}
