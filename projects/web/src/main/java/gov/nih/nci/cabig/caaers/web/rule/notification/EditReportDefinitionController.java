package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * This controller is used during the NotificatonDefinition edit flow.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : Jun 12, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional
public class EditReportDefinitionController  extends AbstractReportDefinitionController {
	

	@Override	
	public String getFlowName() {
		return "Edit Report Definition";
	}
	
	/**
	 * The request parameter should contain <code>repDefId</code>, which is 
	 * used to obtain the {@link ReportDefinition} from the DB.
	 */
	public Object formBackingObject(HttpServletRequest req) throws Exception {
		//fetch report definition Id
		Integer rpDefId = Integer.valueOf(req.getParameter("repDefId"));
		//feth the ReportDefinition by Id
		ReportDefinition rpDef = reportDefinitionDao.getById(rpDefId);
		//initialize all the lazy collections in rpDef
		reportDefinitionDao.initialize(rpDef);
		
		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand();
		rpDefCmd.setReportDefinition(rpDef);
		rpDefCmd.setReportDefinitionDao(reportDefinitionDao);
		rpDefCmd.setRoles(roles);
		
		//find the index of the first planned notificaiton
		List<PlannedNotification> pnfList = rpDef.getPlannedNotifications();
		PlannedNotification pnf = (pnfList.size() > 0)? pnfList.get(0) : null;
		if(pnf != null) rpDefCmd.setPointOnScale("" + pnf.getIndexOnTimeScale());
		rpDefCmd.populate();
		return rpDefCmd;
		
	}
	
}
