/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.workflow;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowServiceImpl;
import gov.nih.nci.cabig.caaers.workflow.handler.NodeSkipActionHandler;

import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.def.ProcessDefinition;
import org.springframework.context.ApplicationContext;
/**
 * 
 * @author Biju Joseph
 *
 */
public class LoadWorkflowTest extends CaaersTestCase {
	private NodeSkipActionHandler actionHandler;
	static JbpmConfiguration jbpmConfiguration = null;
	private WorkflowService workflowService = new WorkflowServiceImpl();
	
	protected void setUp() throws Exception {
		super.setUp();
		actionHandler = (NodeSkipActionHandler) getDeployedApplicationContext().getBean("nodeSkipActionHandler");
	}
	
	public void testLoadWorkflow() throws Exception {
		ApplicationContext ctx = getDeployedApplicationContext();
		Object o = ctx.getBean("jbpmTemplate");
		assertNotNull(o);
	}
	
	public void testLoadWorkflowHasCorrectProcessDefinitions(){
		ApplicationContext ctx = getDeployedApplicationContext();
		ProcessDefinition defExpeditedReportDomestic = (ProcessDefinition)ctx.getBean("expedited_domestic");
		assertNotNull(defExpeditedReportDomestic);
		assertEquals("expedited_domestic", defExpeditedReportDomestic.getName());
		ProcessDefinition defReportingPeriodCC = (ProcessDefinition)ctx.getBean("reportingperiod_coordinatingcenter");
		assertNotNull(defReportingPeriodCC);
		assertEquals("reportingperiod_coordinating_center", defReportingPeriodCC.getName());
	}
/*	
	public void testFetchNextTransitionsMultiple() throws Exception {
		WorkflowConfig wc = createWorkFlowConfig();
		//ProcessDefinition pDefinition = (ProcessDefinition)getDeployedApplicationContext().getBean("routineFlow");
		
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
			    "<process-definition>" +
			    	"<start-state name='Ready For Review'>" +
			    		"<transition to='Perform Level 1 Review' name='Level 1 Review'/>" +
			    		"<transition to='Perform Level 2 Review' name='Level 2 Review'/>" +
			    		"<transition to='Publish Report For Review' name='Cancel Review'/>" +
			    	"</start-state>" +
			    	"<node name='Perform Level 1 Review'>"+
			    		"<transition to='Perform Level 2 Review' name='Level 2 Review'/>"+
			    	"</node>" +
			    	"<node name='Perform Level 2 Review'>"+
			    		"<transition to='Publish Report For Review' name='Cancel Review'/>"+
			    		"<transition to='Locked' name='Lock'/>" +
			    	"</node>" +
			    	"<node name='Publish Report For Review'>"+
			    		"<transition to='Locked' name='Lock'/>"+
			    	"</node>" +
					"<end-state name='Locked' />" +
			    "</process-definition>"
			  );
		
		
		
		ProcessInstance pInstance = new ProcessInstance(processDefinition);
		
		
		//pInstance.signal();
		List<String> transitions = PossibleTransitionsResolver.fetchNextTransitions(wc, pInstance);
		System.out.println(transitions);
	}
	
	public void testFetchNextTransitionsSingle() throws Exception {
		WorkflowConfig wc = createWorkFlowConfig();
		
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
				"<process-definition>" +
					"<start-state name='Start Routine Report Flow'>" +
						"<transition to='Publish Report For Review'/>" +
					"</start-state>" +
					"<state name='Review Completed'>" +
						"<transition to='Locked' name='Lock'/>" +
						"<transition to='Publish Report For Review' name='To Draft/Info'/>" +
					"</state>" +
					"<node name='Ready For Review'>" +
						"<transition to='Perform Level 1 Review' name='Level 1 Review'/>" +
						"<transition to='Perform Level 2 Review' name='Level 2 Review'/>" +
						"<transition to='Publish Report For Review' name='Cancel Review'/>" +
					"</node>" +
					"<node name='Publish Report For Review'>" +
						"<transition to='Ready For Review' name='Publish For Review'/>" +
					"</node>" +
					"<node name='Perform Level 1 Review'>" +
						"<transition to='Perform Level 2 Review' name='Level 2 Review'/>" +
						"<transition to='Provide Additional Info for First Review' name='Additional Info Needed'/>" +
						"<transition to='Publish Report For Review' name='Cancel Review'/>" +
					"</node>" +
					"<node name='Perform Level 2 Review'>" +
						"<transition to='Review Completed' name='Review Complete'/>" +
						"<transition to='Provide Additional Info for Second Review' name='Additional Info Needed'/>" +
						"<transition to='Publish Report For Review' name='Cancel Review'/>" +
					"</node>" +
					"<node name='Provide Additional Info for First Review'>" +
						"<transition to='Perform Level 1 Review' name='Publish For Review'/>" +
					"</node>" +
					"<node name='Provide Additional Info for Second Review'>" +
						"<transition to='Perform Level 2 Review' name='Publish For Review'/>" +
					"</node>" +
					"<end-state name='Locked'/>" +
				"</process-definition>" 
		);
		ProcessInstance pInstance = new ProcessInstance(processDefinition);
		wc.getTaskConfigs().get(0).setApplicable(false);
		
		
		//pInstance.signal();
		List<String> transitions = PossibleTransitionsResolver.fetchNextTransitions(wc, pInstance);
		System.out.println(transitions);
	}
	
	public void testNodeSkipActionHandlerCreateTasks() throws Exception {
		jbpmConfiguration = (JbpmConfiguration)getDeployedApplicationContext().getBean("jbpmConfiguration");
		workflowService.setJbpmConfiguration(jbpmConfiguration);
		workflowService.setSessionFactory((SessionFactory)getDeployedApplicationContext().getBean("sessionFactory"));
		workflowService.setCaaersJavaMailSender((CaaersJavaMailSender)getDeployedApplicationContext().getBean("mailer"));
		
		JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
		
		WorkflowConfig wc = createWorkFlowConfig();
		ProcessDefinition pDefinition = (ProcessDefinition)getDeployedApplicationContext().getBean("routineFlow");
		workflowService.setRoutineFlowProcessDefinition(pDefinition);
		Map<String, String> variableMap = new HashMap<String, String>();
		variableMap.put("workflowDefinitionName", "routineFlow");
		// TODO ... 
		// Add the workflowConfig id to the variableMap
		
		variableMap.put("domainObjectId", "1");
		ProcessInstance pInstance = new ProcessInstance(pDefinition, variableMap);
		jbpmContext.save(pInstance);
		jbpmContext.close();
		Token token = pInstance.getRootToken();
		ExecutionContext context = new ExecutionContext(token);
		String[] assigneeArray = {"SYSTEM_ADMIN"};
		//actionHandler.createTasks(wc, assigneeArray, context);
		ArrayList<String> assigneeList = new ArrayList();
		assigneeList.add("sameer.sawant@semanticbits.com");
		workflowService.createTaskInstances(context, assigneeList);
		
		System.out.println("Ganapati Bappa Morya");
	
	}
	
	
	//Create workflowConfig
	public WorkflowConfig createWorkFlowConfig(){
		WorkflowConfig wConfig = new WorkflowConfig();
		
		wConfig.setEnabled(true);
		wConfig.setWorkflowDefinitionName("routineFlow");
		
		// Add TaskConfigs.
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Publish Report For Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Ready For Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Perform Level 1 Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Provide Additional Info for First Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Perform Level 2 Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Provide Additional Info for Second Review", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("Review Completed", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("", true));
		wConfig.addTaskConfigs(Fixtures.createTaskConfig("", true));
		return wConfig;
	}*/
}
