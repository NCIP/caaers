package gov.nih.nci.cabig.caaers.rules.objectgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * The class repesents the navigation path between two domain objects
 * @author vinaykumar
 *
 */
public class NavigationPath {
	
	private String sourceObjectType;
	private String targetObjectType;
	
	private List<Node> nodes;
	
	public List<Node> getNodes() {
		if(nodes==null){
			nodes = buildNodes();
		}
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public String getSourceObjectType() {
		return sourceObjectType;
	}
	public void setSourceObjectType(String sourceObjectType) {
		this.sourceObjectType = sourceObjectType;
	}
	
	public String getTargetObjectType() {
		return targetObjectType;
	}
	public void setTargetObjectType(String targetObjectType) {
		this.targetObjectType = targetObjectType;
	}
	/**
	 * This method will be implemented using xml file.
	 * The xml file will have list of paths for objects.
	 * @return
	 */
	private List<Node> buildNodes(){
		return new ArrayList<Node>();
	}

}
