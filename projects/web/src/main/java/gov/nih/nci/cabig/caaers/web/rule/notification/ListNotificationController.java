package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table.
 * 
 */
public class ListNotificationController extends SimpleFormController {

    private ReportDefinitionDao rdDao;

    public ListNotificationController() {

        setCommandClass(ListNotificationCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/notification/list");
        setSuccessView("rule/notification/list");
    }

    @Override
    public Object formBackingObject(HttpServletRequest request) {
        return new ListNotificationCommand(rdDao);
    }

    /**
     * @return the reportCalendarTemplateDao
     */
    public ReportDefinitionDao getRdDao() {
        return rdDao;
    }

    /**
     * @param reportDefinitionDao
     *                the reportCalendarTemplateDao to set
     */
    public void setRdDao(ReportDefinitionDao rdDao) {
        this.rdDao = rdDao;
    }

}
