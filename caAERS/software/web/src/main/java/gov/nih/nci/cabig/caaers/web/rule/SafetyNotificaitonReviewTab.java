/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Map;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class SafetyNotificaitonReviewTab extends TabWithFields<ManageSafetyNotificationCommand> {

    public SafetyNotificaitonReviewTab() {
        super("Review the notification details", "Review", "rule/safetyNotificationReview");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ManageSafetyNotificationCommand command) {
        return new InputFieldGroupMap();
    }


}
