package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Rhett Sutphin
 */
public class MandatoryProperties {
    private static final Log log = LogFactory.getLog(MandatoryProperties.class);

    private Set<TreeNode> mandatoryNodes;

    private ExpeditedReportTree tree;

    public MandatoryProperties(ExpeditedReportTree tree) {
        mandatoryNodes = new LinkedHashSet<TreeNode>();
        this.tree = tree;
    }

    public void add(TreeNode node) {
        if (node == null) throw new NullPointerException("Cannot add null nodes");
        mandatoryNodes.add(node);
    }

    public void add(ReportMandatoryFieldDefinition definition) {
        if (definition.getMandatory().equals(Mandatory.MANDATORY)) {
            TreeNode node = tree.find(definition.getFieldPath());
            if (node != null) add(node);
        }
    }

    public boolean isMandatory(String realProperty) {
        TreeNode node = tree.find(realProperty);
        if (node == null) {
            log.debug("No expedited tree node matching " + realProperty);
            return false;
        }
        boolean mandatory = isMandatory(node);
        if (log.isDebugEnabled()) {
            log.debug(realProperty + "is " + (mandatory ? "" : "not ") + "mandatory");
        }
        return mandatory;
    }

    public boolean isMandatory(TreeNode node) {
        boolean mandatory = getMandatoryNodes().contains(node);
        if (log.isDebugEnabled()) {
            log.debug(node + "is " + (mandatory ? "" : "not ") + "mandatory");
        }
        return mandatory;
    }

    public List<UnsatisfiedProperty> getUnsatisfied(TreeNode section, ExpeditedAdverseEventReport aeReport) {
        List<TreeNode> filtered = new LinkedList<TreeNode>();
        for (TreeNode node : getMandatoryNodes()) {
            if (section.isAncestorOf(node)) filtered.add(node);
        }
        return tree.verifyNodesSatisfied(filtered, aeReport);
    }

    // exposed for testing
    Set<TreeNode> getMandatoryNodes() {
        return mandatoryNodes;
    }
}
