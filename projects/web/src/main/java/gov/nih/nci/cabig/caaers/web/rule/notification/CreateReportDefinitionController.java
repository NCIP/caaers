package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateReportDefinitionController  extends AbstractReportDefinitionController{
	
	@Override
	public String getFlowName() {
		return "Create Report Definition";
	}
	
	/**
	 * In the create flow of report definiton, we should make sure that
	 * there exists at least one ReportDeliveryDefinition.
	 */
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		ReportDefinition rpDef = new ReportDefinition();
		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand();
		rpDefCmd.setReportDefinition(rpDef);
		rpDefCmd.setReportDefinitionDao(reportDefinitionDao);
		rpDefCmd.setRoles(roles);
		return rpDefCmd;
	}
	
}
