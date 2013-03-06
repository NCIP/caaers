/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 * @date 3/26/12
 */
public class NotificationTest extends TestCase {
    public void testValidate() throws Exception {
        Notification nf = new Notification();
        ValidationErrors errors = nf.validate();

        assertTrue(errors.hasErrors());
        assertTrue(errors.containsErrorWithCode("NF_001"));
        assertTrue(errors.containsErrorWithCode("NF_002"));
        assertTrue(errors.containsErrorWithCode("NF_003"));
        assertTrue(errors.containsErrorWithCode("NF_004"));
        assertTrue(errors.containsErrorWithCode("NF_005"));
    }

    public void testValidate_InvalidEmail() throws Exception {
        Notification nf = new Notification();
        nf.setEmails("abcd@kk.com,jj,ll@ll.com");
        nf.setStudy(Fixtures.createStudy("junk"));
        nf.setSubject("hello");
        nf.setContent("hai");
        nf.setName("jai");
        ValidationErrors errors = nf.validate();

        assertTrue(errors.hasErrors());
        assertTrue(errors.containsErrorWithCode("NF_001"));
        System.out.println(errors);
        assertTrue(1 == errors.getErrorCount());

    }
}
