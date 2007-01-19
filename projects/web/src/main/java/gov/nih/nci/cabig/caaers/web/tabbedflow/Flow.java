package gov.nih.nci.cabig.caaers.web.tabbedflow;

import java.util.List;
import java.util.LinkedList;

/**
 * @author Rhett Sutphin
 */
public class Flow<C> {
    private String name;
    private List<Tab<C>> tabs;

    public Flow(String name) {
        this.name = name;
        this.tabs = new LinkedList<Tab<C>>();
    }

    public void addTab(Tab<C> tab) {
        tab.setNumber(tabs.size());
        this.tabs.add(tab);
    }

    public int getTabCount() {
        return getTabs().size();
    }

    public Tab<C> getTab(int number) {
        return getTabs().get(number);
    }

    public List<Tab<C>> getTabs() {
        return tabs;
    }

    public String getName() {
        return name;
    }

    ////// OBJECT METHODS

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append('[').append(getName()).append(']')
            .toString();
    }
}
