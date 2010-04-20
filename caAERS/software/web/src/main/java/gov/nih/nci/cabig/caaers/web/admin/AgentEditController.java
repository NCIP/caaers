package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* @author Ion C. Olaru
* 
* */

public class AgentEditController extends AutomaticSaveAjaxableFormController<AgentCommand, Agent, AgentDao> {

    private AgentRepository agentRepository;
    private AgentSpecificAdverseEventListService service;
    private TerminologyRepository termRepository;
    private static final Log log = LogFactory.getLog(AgentEditController.class);
    private CtcTermDao ctcTermDao;
    private AgentSpecificTermDao agentSpecificTermDao;
    private CtcCategoryDao ctcCategoryDao;
    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;

    @Override
    protected Agent getPrimaryDomainObject(AgentCommand command) {
        return command.getAgent();
    }

    @Override
    protected AgentDao getDao() {
        return null;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return null;  
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Agent agent = null;
        agent = agentRepository.getAgentByID(Integer.parseInt(request.getParameter("agentID")));
        AgentCommand c = new AgentCommand();
        c.setAgent(agent);
        c.getAgentSpecificTerms().addAll(service.getListByAgent(agent.getId()));

        int categoryID;

        // need to determine the category of the first element in the previous list
        if (c.getAgentSpecificTerms().size() > 0) {
            AgentSpecificTerm at = c.getAgentSpecificTerms().get(0);
            if (at instanceof AgentSpecificCtcTerm) {
                AgentSpecificCtcTerm t = (AgentSpecificCtcTerm)at;
                c.setCtcVersion(ctcDao.getById(t.getTerm().getCategory().getCtc().getId()));
                c.setMeddraVersion(meddraVersionDao.getById(10));
            } else {
                AgentSpecificMeddraLowLevelTerm t = (AgentSpecificMeddraLowLevelTerm)at;
                c.setMeddraVersion(meddraVersionDao.getById(t.getTerm().getMeddraVersion().getId()));
            }
        }

        return c;
    }

    @Override
	public FlowFactory<AgentCommand> getFlowFactory() {
		return new FlowFactory<AgentCommand>() {
			public Flow<AgentCommand> createFlow(AgentCommand cmd) {
				Flow<AgentCommand> flow = new Flow<AgentCommand>("Agents");
				flow.addTab(new AgentTab<AgentCommand>());
				return flow;
			}
		};
	}

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final AgentCommand command, final Tab<AgentCommand> tab) {
        if (request.getParameter(AbstractAjaxFacade.AJAX_REQUEST) != null) return false;
        return true;
    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        String suvbiew = request.getParameter(AbstractAjaxFacade.AJAX_SUBVIEW_PARAMETER);
        if (suvbiew != null) {
            return "admin/ajax/" + suvbiew;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
    }

    @Override
    protected void onBind(HttpServletRequest request, Object command) throws Exception {
        super.onBind(request, command);
    }

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public AgentSpecificAdverseEventListService getService() {
        return service;
    }

    public void setService(AgentSpecificAdverseEventListService service) {
        this.service = service;
    }

    public TerminologyRepository getTermRepository() {
        return termRepository;
    }

    public void setTermRepository(TerminologyRepository termRepository) {
        this.termRepository = termRepository;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
    }

    public CtcCategoryDao getCtcCategoryDao() {
        return ctcCategoryDao;
    }

    public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
        this.ctcCategoryDao = ctcCategoryDao;
    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public MeddraVersionDao getMeddraVersionDao() {
        return meddraVersionDao;
    }

    public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }
}