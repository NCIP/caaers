package gov.nih.nci.cabig.caaers.web.rule;


import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class CreateSafetyNotificationController extends AbstractSafetyNotificationController {

    @Override
    public FlowFactory<ManageSafetyNotificationCommand> getFlowFactory() {
        return new FlowFactory<ManageSafetyNotificationCommand>() {

            public Flow<ManageSafetyNotificationCommand> createFlow(ManageSafetyNotificationCommand command) {
                Flow<ManageSafetyNotificationCommand> flow = new Flow<ManageSafetyNotificationCommand>("Create Safety Notification");
                flow.addTab(new SafetyNotificationTab());
                flow.addTab(new SafetyNotificaitonReviewTab());
                return flow;
            }
        };
    }


}
