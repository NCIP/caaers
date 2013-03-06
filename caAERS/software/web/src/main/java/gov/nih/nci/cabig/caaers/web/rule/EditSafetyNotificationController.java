/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class EditSafetyNotificationController extends AbstractSafetyNotificationController {

    @Override
    public FlowFactory<ManageSafetyNotificationCommand> getFlowFactory() {
        return new FlowFactory<ManageSafetyNotificationCommand>() {

            public Flow<ManageSafetyNotificationCommand> createFlow(ManageSafetyNotificationCommand command) {
                Flow<ManageSafetyNotificationCommand> flow = new Flow<ManageSafetyNotificationCommand>("Edit Safety Notification");
                flow.addTab(new SafetyNotificaitonReviewTab());
                flow.addTab(new SafetyNotificationTab());
                return flow;
            }
        };
    }

}
