package gov.nih.nci.cabig.caaers.web.study;

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
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class CreateStudyController extends AbstractTabbedFlowFormController<Study> {
    private StudyDao studyDao;
    private SiteDao siteDao;
    private AgentDao agentDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;

    public CreateStudyController() {
        setCommandClass(Study.class);

        Flow<Study> flow = new Flow<Study>("Create Study");
        flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
        flow.addTab(new EmptyStudyTab("Review and Submit", "Review and Submit", "study/study_reviewsummary"));

        setFlow(flow);
    }

    @Override
    protected void initBinder(
        HttpServletRequest request, ServletRequestDataBinder binder
    ) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        ControllerTools.registerDomainObjectEditor(binder, siteDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
    }

    /**
     * Create a nested object graph that Create Study Design needs
     *
     * @param request -
     *                HttpServletRequest
     * @throws ServletException
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        return createDefaultStudyWithDesign();
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

    private Study createDefaultStudyWithDesign() {

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
    
    ////// CONFIGURATION

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }
}