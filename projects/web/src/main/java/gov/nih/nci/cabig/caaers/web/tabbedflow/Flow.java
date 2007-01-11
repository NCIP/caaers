package gov.nih.nci.cabig.caaers.web.tabbedflow;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class Flow {
    private String name;
    private List<Tab> tabs;

    public Flow(String name, List<Tab> tabs) {
        this.name = name;
        this.tabs = tabs;
    }

    public int getTabCount() {
        return getTabs().size();
    }

    public Tab getTab(int number) {
        return getTabs().get(number);
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public String getName() {
        return name;
    }
}
