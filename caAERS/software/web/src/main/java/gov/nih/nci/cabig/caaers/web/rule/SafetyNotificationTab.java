package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class SafetyNotificationTab  extends TabWithFields<ManageSafetyNotificationCommand> {


    public SafetyNotificationTab() {
        super("Notification details", "Notification", "rule/safetyNotification");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ManageSafetyNotificationCommand command) {
        return new InputFieldGroupMap();
    }

    @Override
    public void onBind(HttpServletRequest request, ManageSafetyNotificationCommand command, Errors errors) {
        command.syncRecipients();
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(ManageSafetyNotificationCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        ValidationErrors validationErrors = command.getNotification().validate();
        if(validationErrors.containsErrorWithCode("NF_001")) errors.rejectValue("notification.emails","NF_001");
        if(validationErrors.containsErrorWithCode("NF_002")) errors.rejectValue("notification.subject","NF_002");
        if(validationErrors.containsErrorWithCode("NF_003")) errors.rejectValue("notification.study","NF_003");
        if(validationErrors.containsErrorWithCode("NF_004")) errors.rejectValue("notification.content","NF_004");
        if(validationErrors.containsErrorWithCode("NF_005")) errors.rejectValue("notification.name","NF_005");

    }
}
