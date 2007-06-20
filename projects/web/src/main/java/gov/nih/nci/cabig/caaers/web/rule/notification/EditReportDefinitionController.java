package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
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
public class EditReportDefinitionController  extends AbstractTabbedFlowFormController<RuleInputCommand> {
	
	private ReportDefinitionDao rpDefDao;
	private Map<String, String> roles;
	public EditReportDefinitionController(){
		initFlow();
	}
	protected void initFlow() {
		setFlow(new Flow<RuleInputCommand>(getFlowName()));
        FirstTab firstTab = new FirstTab();
        ReportDeliveryDefinitionTab deliveryDefTab = new ReportDeliveryDefinitionTab();
        SecondTab secondTab = new SecondTab();
        ThirdTab thirdTab = new ThirdTab();
        
        getFlow().addTab(firstTab);
        getFlow().addTab(deliveryDefTab);
        getFlow().addTab(secondTab);
        getFlow().addTab(thirdTab);
	}
		
	protected String getFlowName() {
		return "Edit Report Calendar";
	}
	
	
	public ModelAndView processFinish(HttpServletRequest req, HttpServletResponse res, Object cmd, BindException e) throws Exception {
		ReportDefinitionCommand rpDefCmd = (ReportDefinitionCommand) cmd;
		
		//save
		rpDefDao.save(rpDefCmd.getReportDefinition());

		Map<String, Object> model = new ModelMap();
        return new ModelAndView("redirectToNotificationList", model);
	}
	
	/**
	 * The request parameter should contain <code>repDefId</code>, which is 
	 * used to obtain the {@link ReportDefinition} from the DB.
	 */
	public Object formBackingObject(HttpServletRequest req) throws Exception {
		System.out.println(this);
		//fetch report definition Id
		Integer rpDefId = Integer.valueOf(req.getParameter("repDefId"));
		//feth the ReportDefinition by Id
		ReportDefinition rpDef = rpDefDao.getById(rpDefId);
		//initialize all the lazy collections in rpDef
		rpDefDao.initialize(rpDef);
		
		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand();
		rpDefCmd.setReportDefinition(rpDef);
		rpDefCmd.setReportDefinitionDao(rpDefDao);
		rpDefCmd.setRoles(roles);
		
		//find the index of the first planned notificaiton
		List<PlannedNotification> pnfList = rpDef.getPlannedNotifications();
		PlannedNotification pnf = (pnfList.size() > 0)? pnfList.get(0) : null;
		if(pnf != null) rpDefCmd.setPointOnScale("" + pnf.getIndexOnTimeScale());
		rpDefCmd.populate();
		return rpDefCmd;
		
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController#postProcessPage(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors, int)
	 */
	@Override
	protected void postProcessPage(HttpServletRequest req, Object cmd, Errors errors, int page) throws Exception {
		super.postProcessPage(req,cmd, errors, page);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors, int)
	 */
	@Override
	protected Map referenceData(HttpServletRequest req, Object cmd, Errors errors, int page) throws Exception {
		return super.referenceData(req,cmd, errors, page);
	}
	
	
	public void setRoles(Map<String,String> roles){
		this.roles = roles;
	}

	public Map<String,String> getRoles(){
		return roles;
	}
	public ReportDefinitionDao getReportDefinitionDao() {
		return rpDefDao;
	}
	
	public void setReportDefinitionDao(ReportDefinitionDao rdDao) {
		this.rpDefDao = rdDao;
	}
	
}
