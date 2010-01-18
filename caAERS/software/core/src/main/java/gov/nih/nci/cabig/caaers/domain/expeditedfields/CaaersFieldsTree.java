package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersTab.CAPTURE_ADVERSE_EVENTS_TAB;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sameer Sawant
 * This tree represents the fields (attributes) in various pages of tabs of the caAERS UI.
 * Its used in the page under admin to configure the various fields to make it Mandatory/
 * Optional/Not applicable on the page.
 */
public class CaaersFieldsTree extends TabNode{
	private Map<CaaersTab, TreeNode> tabs;
	
	private ExpeditedReportTree expeditedReportTree;
	
	public CaaersFieldsTree(){
		tabs = new LinkedHashMap<CaaersTab, TreeNode>();
		expeditedReportTree = new ExpeditedReportTree();
		add(tab(CAPTURE_ADVERSE_EVENTS_TAB, 
				expeditedReportTree.getNodeForSection(ExpeditedReportSection.ADVERSE_EVENT_SECTION)));
	}
	
	public void setTabs(Map<CaaersTab, TreeNode> tabs){
		this.tabs = tabs;
	}
	
	public Map<CaaersTab, TreeNode> getTabs(){
		return tabs;
	}

	public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree){
		this.expeditedReportTree = expeditedReportTree;
	}
	
	public ExpeditedReportTree getExpeditedReportTree(){
		return expeditedReportTree;
	}
	
	@Override
    public TreeNode add(TreeNode... subnodes) {
        super.add(subnodes);
        for (TreeNode subnode : subnodes) {
            if (subnode instanceof TabNode) {
                tabs.put(((TabNode) subnode).getTab(), subnode);
            }
        }
        return this;
    }
	
	public TreeNode getNodeForTab(CaaersTab tab) {
        TreeNode node = tabs.get(tab);
        if (node == null && log.isDebugEnabled()) {
            log.debug("No node in the Caaers fields tree for " + tab);
        }
        return node;
    }
	
	public CaaersTab getTabForNode(TreeNode node) {
        if (node == null) throw new NullPointerException("No node provided");
        if (node instanceof TabNode) return ((TabNode) node).getTab();
        if (node.getParent() == null) throw new CaaersSystemException(node + " doesn't belong to a tab");
        return getTabForNode(node.getParent());
    }

    @Override
    public String getPropertyName() {
        return null;
    }
}