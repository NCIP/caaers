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
		//return new ArrayList<Node>();
		nodes = new ArrayList<Node>();
		Node study= new Node();
		study.setName("");
		study.setObjectType("gov.nih.nci.cabig.caaers.domain.Study");
		study.setCollection(false);
		
		nodes.add(study);
		
		Node studyAgent = new Node();
		studyAgent.setName("studyAgents");
		studyAgent.setObjectType("gov.nih.nci.cabig.caaers.domain.StudyAgent");
		studyAgent.setCollection(true);
		nodes.add(studyAgent);
		
		Node studyAgentINDAssociation = new Node();
		studyAgentINDAssociation.setName("studyAgentINDAssociations");
		studyAgentINDAssociation.setObjectType("gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation");
		studyAgentINDAssociation.setCollection(true);
		nodes.add(studyAgentINDAssociation);
		
		Node investigationalNewDrug = new Node();
		investigationalNewDrug.setName("investigationalNewDrug");
		investigationalNewDrug.setObjectType("gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug");
		investigationalNewDrug.setCollection(false);
		nodes.add(investigationalNewDrug);
		
		return nodes;
	}

}
