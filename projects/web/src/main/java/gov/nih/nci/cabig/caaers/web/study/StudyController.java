package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;


/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a Study Design
 * This uses AbstractTabbedFlowFormController to implement tabbed workflow
 * @author Priyatam
 */
public abstract class StudyController<C extends Study> extends AutomaticSaveFlowFormController<C, Study, StudyDao>{
    private static final Log log = LogFactory.getLog(StudyController.class);
    protected StudyDao studyDao;
    private OrganizationDao organizationDao;
    private AgentDao agentDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private CtcDao ctcDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;

    public StudyController() {
        setCommandClass(Study.class);
        Flow<C> flow = new Flow<C>("Create Study");
        layoutTabs(flow);
        setFlow(flow);
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
    }

    ///LOGIC
	@Override
	protected Study getPrimaryDomainObject(C command) {
		 return command;
	}

    @Override
    protected StudyDao getDao() {
        return studyDao;
    }

    /**
     * Template method to let the subclass decide the order of tab
     */
    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws Exception {
        super.initBinder(request, binder);
    	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcDao);
        ControllerTools.registerDomainObjectEditor(binder, investigationalNewDrugDao);
        ControllerTools.registerEnumEditor(binder, Term.class);
    }


    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(
        HttpServletRequest request, Object command, Errors errors, int page
    ) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, command, errors, page);

        Study study = (Study) command;

        if (isSummaryEnabled()) {
            Map<String, String> summary = new LinkedHashMap<String, String>();
            if (study.getPrimaryIdentifier()!= null )
            {
                summary.put("Primary identifier", study.getPrimaryIdentifier().toString());
            }
            if (study.getShortTitle() != null) {
                summary.put("Short title", study.getShortTitle());
            }
            if (study.getPrimarySponsorCode() != null) {
                summary.put("Sponsor", study.getPrimarySponsorCode().toString());
            }
            if (study.getPhaseCode() != null) {
                summary.put("Phase", study.getPhaseCode().toString());
            }
            if(page != 7) refdata.put("summary", summary);
        }

        return refdata;
    }

    /**
     * Override this in sub controller if summary is needed
     * @return
     */
    protected boolean isSummaryEnabled() {
        return false;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                                         Object command, BindException errors) throws Exception {
        Study study = (Study) command;
        studyDao.save(study);

        ModelAndView modelAndView = new ModelAndView("study_confirmation");
        modelAndView.addAllObjects(errors.getModel());
        response.sendRedirect("view?studyName=" + study.getShortTitle() + "&type=confirm");
        return null;
    }

	@Override
	protected String getViewName(HttpServletRequest request, Object command, int page) {
		Object subviewName = findInRequest(request, "_subview");
		if (subviewName != null) {
            return "study/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
	}

	/**
	 *  Returns the value associated with the <code>attributeName</code>, if present in
	 *  HttpRequest parameter, if not available, will check in HttpRequest attribute map.
	 */
	private Object findInRequest(HttpServletRequest request, String attributName){

	  	Object attr = request.getParameter(attributName);
	  	if(attr == null) attr = request.getAttribute(attributName);
	   	return attr;
	}


	@Override
	protected boolean suppressValidation(HttpServletRequest request, Object command) {
		//supress for ajax and delete requests
		Object isAjax = findInRequest(request, "_isAjax");
		if(isAjax != null) return true;
		String action = (String)findInRequest(request, "_action");
		if(StringUtils.isNotEmpty(action)) return true;
		return super.suppressValidation(request, command);
	}

	@Override
	protected boolean shouldSave(HttpServletRequest request, C command, Tab<C> tab) {
		return super.shouldSave(request, command, tab) &&
		 (findInRequest(request, "_isAjax") == null);
	}

	///BEAN PROPERTIES

	public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public ResearchStaffDao getResearchStaffDao() {
        return researchStaffDao;
    }

    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public SiteInvestigatorDao getSiteInvestigatorDao() {
        return siteInvestigatorDao;
    }

    public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

	public CtcDao getCtcDao() {
		return ctcDao;
	}

	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}


	public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
		return investigationalNewDrugDao;
	}


	public void setInvestigationalNewDrugDao(
			InvestigationalNewDrugDao investigationalNewDrugDao) {
		this.investigationalNewDrugDao = investigationalNewDrugDao;
	}




}
