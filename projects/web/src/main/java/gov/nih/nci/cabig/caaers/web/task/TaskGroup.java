package gov.nih.nci.cabig.caaers.web.task;

import gov.nih.nci.cabig.ctms.web.chrome.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class TaskGroup {

    private String displayName;

    private List<Task> taskList = new ArrayList<Task>();

    public String getDisplayName() {
        return displayName;
    }

    @Required
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    @Required
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
