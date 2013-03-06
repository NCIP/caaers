/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class MandatoryProperties {
    private static final Log log = LogFactory.getLog(MandatoryProperties.class);

    private Set<TreeNode> mandatoryNodes;
    private Set<String> realPropertyPaths;

    private ExpeditedReportTree tree;

    public MandatoryProperties(ExpeditedReportTree tree) {
        mandatoryNodes = new LinkedHashSet<TreeNode>();
        realPropertyPaths = new LinkedHashSet<String>();
        this.tree = tree;
    }

    private void add(TreeNode node) {
        if (node == null) throw new NullPointerException("Cannot add null nodes");
        mandatoryNodes.add(node);
    }

    /**
     * Adds and ExpeditedReportTree node identified by the realPropery
     * @param realProperty - a path to a node in the ExpeditedReportTree
     */
    public void addNode(String realProperty){
       TreeNode node = tree.find(realProperty);
       if(node != null) add(node);
    }

    /**
     * Will add the path of a self referenced field.
     * @param fieldPath
     */
    public void addRealPropertyPath(String fieldPath){
       if(tree.find(fieldPath) != null) realPropertyPaths.add(fieldPath);
    }


    public boolean isMandatory(String realProperty) {
        TreeNode node = tree.find(realProperty);
        if (node == null) {
            log.warn("No expedited tree node matching " + realProperty);
            return false;
        }
        boolean m1 = getMandatoryNodes().contains(node);
        boolean m2 = getRealPropertyPaths().contains(realProperty);

        boolean mandatory = m1 || m2;

        if (log.isDebugEnabled()) {
            log.debug(realProperty + "is " + (mandatory ? "" : "not ") + "mandatory");
        }
        return mandatory;
    }

    public boolean isMandatory(TreeNode node) {
        boolean m1 = mandatoryNodes.contains(node);
        boolean m2 = false;
        for(String name : realPropertyPaths){
            TreeNode n = tree.find(name);
            if(n != null && n.equals(node)){
                m2 = true;
                break;
            }
        }
        boolean mandatory = m1 || m2;
        if (log.isDebugEnabled()) {
            log.debug(node + "is " + (mandatory ? "" : "not ") + "mandatory");
        }
        return mandatory;
    }
    
    /**
     * Recursively checks whether the node or any of the nodes children is mandatory. 
     * @param node
     * @return
     */
    public boolean isAnyMandatory(TreeNode node){
    	boolean mandatory =  isMandatory(node);
    	if(mandatory || node.isLeaf()) return mandatory;
    	for(TreeNode childNode : node.getChildren()) {
    		mandatory |= isAnyMandatory(childNode);
    	}
    	return mandatory;
    }

    public List<UnsatisfiedProperty> getUnsatisfied(TreeNode section, ExpeditedAdverseEventReport aeReport) {

        BeanWrapper bw = new BeanWrapperImpl(aeReport);
        for(String path : realPropertyPaths){
            TreeNode node = tree.find(path);
            if(node != null){
                if(bw.getPropertyValue(path) == null) return Arrays.asList(new UnsatisfiedProperty(node, path));
            }
        }

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

    Set<String> getRealPropertyPaths(){
        return realPropertyPaths;
    }
}
