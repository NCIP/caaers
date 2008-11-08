package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.List;

public class WorkflowManagerService {
	
	/**
	 * The purpose of this method is to retrieve all the tasks, available for a user.
	 * @param loginId - the logged-in user id.
	 * @return
	 */
	public List<Object> retrieveTaskList(String loginId){
		return null;
	}
	
	/**
	 * This method will create an instance of a workflow definition. 
	 * 
	 * eg:- The workflow for RoutineReport should be created when, the {@link AdverseEventReportingPeriod} is created.
	 * @param domainObject - A valid domain object
	 */
	public void enactWorkflow(DomainObject domainObject){
		
	}
}
