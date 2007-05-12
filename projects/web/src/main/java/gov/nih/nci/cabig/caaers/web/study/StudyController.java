package gov.nih.nci.cabig.caaers.web.study;

import edu.nwu.bioinformatics.commons.StringUtils;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;


/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a Study Design
 * This uses AbstractTabbedFlowFormController to implement tabbed workflow
 * @author Priyatam
 */
public abstract class StudyController extends AbstractTabbedFlowFormController<Study>{
    private static final Log log = LogFactory.getLog(StudyController.class);
    protected StudyDao studyDao;
    private SiteDao siteDao;
    private AgentDao agentDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;

    public StudyController() {
        setCommandClass(Study.class);
        Flow<Study> flow = new Flow<Study>("Create Study");
        layoutTabs(flow);
        setFlow(flow);
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
        ControllerTools.registerDomainObjectEditor(binder, siteDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
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
        if (isUpdate()) {
            refdata.put("isUpdate", "isUpdate");
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

    /**
     * Override this in sub controller if update is needed
     * @return
     */
    protected boolean isUpdate() {
        return false;
    }


    /**
     * Hook to imlement this in subclass (depending on create/edit)
     * @param request - HttpServletRequest
     * @throws ServletException
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        // implement this in sub class
        return null;
    }

    /* (non-Javadoc)
    * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
    * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
    * java.lang.Object, org.springframework.validation.BindException)
    */
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

        // adding studyinvestigator.
        //StudyInvestigator studyInvestigator = new StudyInvestigator();
        //studySite.addStudyInvestigators(studyInvestigator);

        // adding studypersonnel
        //StudyPersonnel studyPersonnel = new StudyPersonnel();
        //studySite.addStudyPersonnel(studyPersonnel);

        //StudyAgent studyAgent = new StudyAgent();
        //studyAgent.setAgent(new Agent());
        //study.addStudyAgent(studyAgent);

        List<Identifier> identifiers = new ArrayList<Identifier>();
        Identifier id = new Identifier();
        identifiers.add(id);
        study.setIdentifiers(identifiers);

        List<Site> sites = siteDao.getAll();

        // XXX: this code doesn't make any sense
        for (Site site : sites) {
            studySite.setSite(site);
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

    public SiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
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

}