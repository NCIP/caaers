package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/***************************************************************************************************
 * This tab has the details of Email Message.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class NotificationsTab extends TabWithFields<ReportDefinitionCommand> {

    private RepeatingFieldGroupFactory rfgFactory;

    public NotificationsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        rfgFactory = new RepeatingFieldGroupFactory("main", "reportDefinition.plannedNotifications");
        rfgFactory.addField(InputFieldFactory.createTextField("recipients", "Recipients", false));
        rfgFactory.addField(InputFieldFactory.createTextField("subjectLine", "Subject Line", false));
        rfgFactory.addField(InputFieldFactory.createTextField("notificationBodyContent.body", "Message", false));
    }

    public NotificationsTab() {
        this("Specify Notification Details", "Notifications", "rule/notification/notificationsTab");
    }

    @Override
    public void postProcess(HttpServletRequest req, ReportDefinitionCommand cmd, Errors errors) {
        super.postProcess(req, cmd, errors);
        String action = req.getParameter("_action");
        if (StringUtils.equals("delete", action)) {
            int indexToDelete = NumberUtils.toInt(cmd.getIndexToDelete());
            int indexOnTimeScale = NumberUtils.toInt(cmd.getPointOnScale());
            int i = 0;
            for (Iterator<PlannedNotification> it = cmd.getReportDefinition().getPlannedNotifications().iterator(); it.hasNext();) {
                PlannedNotification pen = it.next();
                if (pen.getIndexOnTimeScale() == indexOnTimeScale) {
                    if (i == indexToDelete) {
                        it.remove();
                        break;
                    }
                    i++;
                }
            }

        } else {
            if (errors.hasErrors()) cmd.setPointOnScale(cmd.getLastPointOnScale());
        }

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        List<PlannedNotification> pnfList = command.getReportDefinition().getPlannedNotifications();
        int size = (pnfList != null) ? pnfList.size() : 0;
        map.addRepeatingFieldGroupFactory(rfgFactory, size);
        return map;
    }

    @Override
    protected void validate(ReportDefinitionCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        if (CollectionUtils.isEmpty(command.getReportDefinition().getPlannedNotifications())) return;

        List<PlannedNotification> plannedNotifications = command.getEmailNotifications();
        if (CollectionUtils.isEmpty(plannedNotifications)) return;
        EmailValidator emailValidator = EmailValidator.getInstance();
        int i = 0;
        for (PlannedNotification plannedNotification : plannedNotifications) {
            i++;
            PlannedEmailNotification nf = (PlannedEmailNotification) plannedNotification;
            // Message
            if (nf.getNotificationBodyContent() == null || StringUtils.isEmpty(nf.getNotificationBodyContent().getBody())) {
                errors.rejectValue("tempProperty", "RPD_003",new Object[]{i}, "Message Invalid  in Email Notification(" + i + ")");
            }
            // Recipients
            if (nf.getRecipients() == null) {
                errors.rejectValue("tempProperty", "RPD_004",new Object[]{i}, "Invalid Recipient Information in Email Notification (" + i + ")");
            } else {
                for (Recipient recipient : nf.getRecipients()) {
                    if (StringUtils.isEmpty(recipient.getContact())) {
                        errors.rejectValue("tempProperty", "RPD_004",new Object[]{i}, "Invalid Recipient Information in Email Notification (" + i + ")");
                        break;
                    }
                    // valid email?
                    if (recipient instanceof ContactMechanismBasedRecipient) {
                        if (!emailValidator.isValid(recipient.getContact())) {
                            errors.rejectValue("tempProperty","RPD_005",new Object[]{recipient.getContact(), i},"Invalid email address ["+ recipient.getContact()+ "] in Email Notification (" + i + ")");
                            break;
                        }
                    }
                }
            }
            // subject line
            if (StringUtils.isEmpty(nf.getSubjectLine())) {
                errors.rejectValue("tempProperty", "RPD_006",new Object[]{i}, "Subject line Invalid  in Email Notification(" + i + ")");
            }
        }
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ReportDefinitionCommand command) {
        command.setIndexToFetch(command.getPointOnScale());
        command.setLastPointOnScale(command.getPointOnScale());
        Map<String, Object> refData = super.referenceData(request, command);
        return refData;
    }

    /* The binding of recipients are done here */
    @Override
    public void onBind(HttpServletRequest request, ReportDefinitionCommand cmd, Errors errors) {
        super.onBind(request, cmd, errors);
        int size = cmd.getEmailNotifications().size();
        for (int i = 0; i < size; i++) {
            String[] roleRecipients = request.getParameterValues("emailNotifications[" + i
                            + "].roleBasedRecipients");
            String[] contactRecipients = request.getParameterValues("emailNotifications[" + i
                            + "].contactMechanismBasedRecipients");
            PlannedNotification plannedNotification = cmd.getEmailNotifications().get(i);

            List<Recipient> recipients = plannedNotification.getRecipients();
            recipients.clear();

            if (roleRecipients != null) {
                for (String r : roleRecipients) {
                    Recipient roleRecipient = new RoleBasedRecipient(r);
                    if (!recipients.contains(roleRecipient)) plannedNotification
                                    .addRecipient(roleRecipient);
                }
            }

            if (contactRecipients != null) {
                for (String r : contactRecipients) {
                    Recipient cRecipient = new ContactMechanismBasedRecipient(r);
                    if (!recipients.contains(cRecipient)) plannedNotification
                                    .addRecipient(cRecipient);
                }
            }

        }
    }

}
