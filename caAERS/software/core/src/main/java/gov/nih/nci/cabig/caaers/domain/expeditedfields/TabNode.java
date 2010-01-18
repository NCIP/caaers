package gov.nih.nci.cabig.caaers.domain.expeditedfields;


/**
 * @author Rhett Sutphin
 */
class TabNode extends PropertylessNode {
    private CaaersTab tab;

    public TabNode(){
    }
    
    public TabNode(CaaersTab tab) {
        this.tab = tab;
        setDisplayNameCreator(new StaticDisplayNameCreator(tab.name()));
    }

    public CaaersTab getTab() {
        return tab;
    }
    
    public static TreeNode tab(CaaersTab tab, TreeNode... children) {
        return new TabNode(tab).add(children);
    }
}
