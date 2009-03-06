package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.thoughtworks.xstream.XStream;

/**
 * @author Krikor Krumlian
 */
// TODO: this flow only has one tab, so it probably shouldn't be a flow
public class MigrateStudiesController extends
                AbstractTabbedFlowFormController<MigrateStudiesCommand> {

    private StudyDao studyDao;

    private OrganizationDao organizationDao;

    private AgentDao agentDao;

    public MigrateStudiesController() {
        setCommandClass(MigrateStudiesCommand.class);
        setAllowDirtyForward(false);

        Flow<MigrateStudiesCommand> flow = new Flow<MigrateStudiesCommand>("Create Studies");

        flow.addTab(new Tab<MigrateStudiesCommand>("Import Studies", "Import Studies",
                        "admin/migrate_studies") {
            @Override
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                refdata.put("action", "New");
                return refdata;
            }
        });

        setFlow(flow);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
                    throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        // binder.registerCustomEditor(Site.class, new DaoBasedEditor(siteDao));
    }

    /**
     * Create a nested object graph that Create Investigator Design needs
     * 
     * @param request -
     *                HttpServletRequest
     * @throws ServletException
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        return createCommandObject();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
     *      (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object command, BindException errors) throws Exception {

        MigrateStudiesCommand cObject = (MigrateStudiesCommand) command;
        for (int i = 0; i < cObject.getStudies().size(); i++) {
            Study s = cObject.getStudies().get(i);
            studyDao.save(s);
        }

        // ModelAndView modelAndView= new ModelAndView("admin/investigator_details");
        // modelAndView.addAllObjects(errors.getModel());
        // response.sendRedirect("createInvestigator");
        // response.sendRedirect("viewInvestigator?fullName=" + "hh" + "&type=confirm");
        response.sendRedirect("/caaers/pages/study/search");
        return null;
    }

    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors arg2,
                    int pageNo) throws Exception {

        switch (pageNo) {
            case 0:
                handleSiteInvestigatorAction((MigrateStudiesCommand) command, request
                                .getParameter("_action"), request.getParameter("_selected"));
                break;

            default:
                // do nothing
        }
    }

    private void handleSiteInvestigatorAction(MigrateStudiesCommand command, String action,
                    String selected) {

        XStream xstream = new XStream();

        // xstream.alias("studies", Studies.class);
        xstream.alias("study", gov.nih.nci.cabig.caaers.domain.Study.class);
        xstream.alias("identifier", gov.nih.nci.cabig.caaers.domain.Identifier.class);
        xstream.alias("site", gov.nih.nci.cabig.caaers.domain.Organization.class);
        xstream.alias("studySite", gov.nih.nci.cabig.caaers.domain.StudySite.class);
        xstream.alias("studyAgent", gov.nih.nci.cabig.caaers.domain.StudyAgent.class);
        xstream.alias("agent", gov.nih.nci.cabig.caaers.domain.Agent.class);
        xstream.alias("studyDisease", gov.nih.nci.cabig.caaers.domain.StudyDisease.class);
        xstream.alias("diseaseTerm", gov.nih.nci.cabig.caaers.domain.DiseaseTerm.class);
        xstream.alias("category", gov.nih.nci.cabig.caaers.domain.DiseaseCategory.class);

        // System.out.println("Here the text : " + command.getFileName() );
        File xmlFile = new File(command.getFileName());

        // declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
            // use buffering, reading one line at a time
            // FileReader always assumes default encoding is OK!
            input = new BufferedReader(new FileReader(xmlFile));
            ObjectInputStream in = xstream.createObjectInputStream(input);

            while (true) {
                Study studyy = (Study) in.readObject();
                createObjects(studyy, command);

                // System.out.println(studyy.getStudySites().size());
                // System.out.println(studyy.getShortTitle());
                // System.out.println(studyy.getIdentifiers().get(0).getSource());
                // System.out.println("Size : " + studyy.getIdentifiers().size());
                // System.out.println(studyy.getShortTitle());

            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // System.out.println("Hey : " + command.getStudies().size());

        }

    }

    private void createObjects(Study xstreamStudy, MigrateStudiesCommand command) {
        Study st = new Study();
        st.setShortTitle(xstreamStudy.getShortTitle());
        st.setLongTitle(xstreamStudy.getLongTitle());
        st.setDescription(xstreamStudy.getDescription());
        st.setPrecis(xstreamStudy.getPrecis());
        st.setDiseaseCode(xstreamStudy.getDiseaseCode());
        st.setMonitorCode(xstreamStudy.getMonitorCode());
        st.setPhaseCode(xstreamStudy.getPhaseCode());
        st.setPrimarySponsorCode(xstreamStudy.getPrimarySponsorCode());
        st.setStatus(xstreamStudy.getStatus());
        // Integer
        st.setTargetAccrualNumber(xstreamStudy.getTargetAccrualNumber());
        // Boolean
        st.setBlindedIndicator(xstreamStudy.getBlindedIndicator());
        st.setMultiInstitutionIndicator(xstreamStudy.getMultiInstitutionIndicator());
        st.setRandomizedIndicator(xstreamStudy.getRandomizedIndicator());
        // Identifiers
        if (xstreamStudy.getIdentifiers() != null) {
            for (int i = 0; i < xstreamStudy.getIdentifiers().size(); i++) {
                Identifier identifier = xstreamStudy.getIdentifiers().get(i);
                st.getIdentifiers().add(identifier);
            }
        }
        // StudySites
        if (xstreamStudy.getStudySites() != null) {
            for (int i = 0; i < xstreamStudy.getStudySites().size(); i++) {
                StudySite studySite = xstreamStudy.getStudySites().get(i);
                Organization organization = organizationDao.getByName(studySite.getOrganization()
                                .getName());
                st.addStudySite(createStudyOrganization(organization));

            }
        } else {
            st.addStudySite(createStudyOrganization(null));
        }

        // StudyAgents
        if (xstreamStudy.getStudyAgents() != null) {
            for (int i = 0; i < xstreamStudy.getStudyAgents().size(); i++) {
                StudyAgent studyAgent = xstreamStudy.getStudyAgents().get(i);
                Agent agent = null;
                if (studyAgent.getAgent().getName() != null) {
                    agent = agentDao.getByName(studyAgent.getAgent().getName());
                }
                if (studyAgent.getAgent().getNscNumber() != null && agent == null) {
                    agent = agentDao.getByNscNumber(studyAgent.getAgent().getNscNumber());
                }
                if (agent != null) {
                    st.addStudyAgent(createStudyAgent(agent));
                }
                // TODO: ADD error handling with user interaction

            }
        }

        command.getStudies().add(st);

    }

    private StudySite createStudyOrganization(Organization organization) {

        StudySite studySite = new StudySite();
        // studySite.setRoleCode("Site");
        studySite.setOrganization(organization == null ? organizationDao.getDefaultOrganization()
                        : organization);
        return studySite;
    }

    private StudyAgent createStudyAgent(Agent agent) {

        StudyAgent studyAgent = new StudyAgent();
        studyAgent.setAgent(agent);
        return studyAgent;
    }

    private MigrateStudiesCommand createCommandObject() {
        // do nothing
        MigrateStudiesCommand msc = new MigrateStudiesCommand();
        return msc;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

}