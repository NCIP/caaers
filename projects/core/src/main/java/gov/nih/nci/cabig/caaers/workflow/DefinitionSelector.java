package gov.nih.nci.cabig.caaers.workflow;

import org.apache.commons.lang.StringUtils;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

/**
 * The purpose of this class it to determine the workflow definition. 
 * @author biju
 *
 */
public class DefinitionSelector {
	/**
	 * This method will figure out the workflow definition to use for the given domain object.
	 * eg: For {@link AdverseEventReportingPeriod} "routineflow", which is the name given to the &lt;process-definition /&gt; tag.
	 *    
	 * @param o
	 * @return
	 */
	public String findWorkflowDefinitionName(DomainObject o){
		return "routineflow";
	}
	/**
	 * This method will return the DomainObject class, associated to a workflow definition.
	 * @param workflowDefName
	 * @return
	 */
	public Class<? extends DomainObject> findDomainObjectType(String workflowDefName){
		if(StringUtils.equals("routineflow", workflowDefName)) return AdverseEventReportingPeriod.class;
		return AdverseEventReportingPeriod.class;
	}
}
