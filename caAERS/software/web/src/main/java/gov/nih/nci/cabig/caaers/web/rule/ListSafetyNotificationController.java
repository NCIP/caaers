package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.domain.Notification;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class ListSafetyNotificationController extends SimpleFormController {

    private NotificationDao notificationDao;

    public ListSafetyNotificationController() {
        setFormView("rule/safetyNotificationList");
        setSuccessView("rule/safetyNotificationList");
        setBindOnNewForm(false);
    }


    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        List<Notification> notifications = notificationDao.getAll();
        return new ListSafetyNotificationCommand(notifications);
    }


    @Required
    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }
}
