package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    public StudyController() {
        setCommandClass(Study.class);
        Flow<C> flow = new Flow<C>("Create Study");
        layoutTabs(flow);
        setFlow(flow);
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
    }

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
    protected abstract void layoutTabs(Flow flow);

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcDao);
    }

    /**
     * Template method for individual controllers
     * @param request
     * @param command
     * @param errors
     * @param page
     * @return
     */
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
            refdata.put("summary", summary);
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

    protected Study createDefaultStudyWithDesign() {
        Study study = new Study();

        StudySite studySite = new StudySite();
        study.addStudySite(studySite);

        List<Identifier> identifiers = new ArrayList<Identifier>();
        Identifier id = new Identifier();
        identifiers.add(id);
        study.setIdentifiers(identifiers);

        List<Organization> organizations = organizationDao.getAll();

        for (Organization organization : organizations) {
            studySite.setOrganization(organization);
        }

        return study;
    }

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
    
    

}