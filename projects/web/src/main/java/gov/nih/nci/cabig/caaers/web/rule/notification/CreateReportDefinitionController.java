package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * */
public class CreateReportDefinitionController  extends AbstractReportDefinitionController{

	@Override
	public String getFlowName() {
		return "Create Report Definition";
	}

	/**
	 * In the create flow of report definition, we should make sure that
	 * there exists at least one ReportDeliveryDefinition.
	 */
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		ReportDefinition rpDef = new ReportDefinition();
		List<ReportMandatoryFieldDefinition> mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
		populateMandatoryFields(mandatoryFields, new ExpeditedReportTree());
		rpDef.setMandatoryFields(mandatoryFields);

		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand(rpDef, reportDefinitionDao);
		rpDefCmd.setRoles(roles);
		return rpDefCmd;
	}
}
