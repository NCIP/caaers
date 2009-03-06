package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.web.task.TaskGroup;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import java.util.List;

import org.acegisecurity.Authentication;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class TaskGroupAuthorizationCheck implements CSMAuthorizationCheck {

    private CSMAuthorizationCheck taskAuthorizationCheck;

    public boolean checkAuthorization(Authentication authentication, String privilege, Object object) {
        boolean authorized = false;
        String resolvedPrivilege = privilege;
        if (object != null && object instanceof TaskGroup) {
            List<Task> taskList = ((TaskGroup) object).getTaskList();
            for (Task task : taskList) {
                authorized = taskAuthorizationCheck.checkAuthorization(authentication, privilege,
                                task);
                if (authorized) break;
            }
        }

        return authorized;
    }

    /**
     * NOT IMPLEMENTED
     * 
     * @param authentication
     * @param privilege
     * @param objectId
     * @return
     */
    public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege,
                    String objectId) {
        return false;
    }

    /**
     * NOT IMPLEMENTED
     * 
     * @param authentication
     * @param privilege
     * @param objectId
     * @return
     */
    public boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege,
                    String[] objectId) {
        return false;
    }

    @Required
    public void setTaskAuthorizationCheck(CSMAuthorizationCheck taskAuthorizationCheck) {
        this.taskAuthorizationCheck = taskAuthorizationCheck;
    }
}
