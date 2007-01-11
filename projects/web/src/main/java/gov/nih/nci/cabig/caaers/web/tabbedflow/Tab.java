package gov.nih.nci.cabig.caaers.web.tabbedflow;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Rhett Sutphin
*/
public class Tab {
    private int number;
    private String longTitle;
    private String shortTitle;
    private String viewName;

    public Tab(int number, String longTitle, String shortTitle, String viewName) {
        this.number = number;
        this.longTitle = longTitle;
        this.shortTitle = shortTitle;
        this.viewName = viewName;
    }

    public Map<String, Object> referenceData() {
        return new HashMap<String, Object>();
    }

    public int getTargetNumber() {
        return getNumber() + 1;
    }

    public int getNumber() {
        return number;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getViewName() {
        return viewName;
    }
}
