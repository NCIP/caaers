package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListServiceImpl;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Ion C. Olaru
 *
 */
public abstract class AbstractAgentTab extends TabWithFields<AgentCommand> {

    protected static final Log log = LogFactory.getLog(AbstractAgentTab.class);
    private AgentSpecificTermDao agentSpecificTermDao;
    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;
    private StudyDao studyDao;
    private StudyAgentDao studyAgentDao;
    private AgentDao agentDao;
    private AgentSpecificAdverseEventListServiceImpl agentSpecificAdverseEventListService;
    
    public AbstractAgentTab(String lName, String sName, String vName) {
        super(lName, sName, vName);
    }

    protected Map<Object, Object> collectOptions(List list, String nameProperty, String valueProperty, String... exclusionProperties) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(list, nameProperty, valueProperty));
        for (String key : exclusionProperties) {
            options.remove(key);
        }
        return options;
    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, AgentCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        refdata.put("terminology", WebUtils.collectOptions(Arrays.asList(Term.values()), null, "displayName"));
        refdata.put("ctcVersion", collectOptions(ctcDao.getAll(), "id", "name"));
        refdata.put("meddraVersion", collectOptions(meddraVersionDao.getAll(), "id", "name"));
        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest request, AgentCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        System.out.println("postProcess...");
        if (request.getParameter(AbstractAjaxFacade.AJAX_REQUEST) != null) return;

        List<StudyAgent> l = null;
        if (command.getAgent().getId() != null) {
             l = getStudyAgentDao().getByAgentID(command.getAgent().getId());
        }
        
        List<AgentSpecificTerm> deleted = new ArrayList<AgentSpecificTerm>();
        for (AgentSpecificTerm t : command.getAgentSpecificTerms()) {
            if (t.getDeleted()) {
                    getAgentSpecificTermDao().delete(t);
                    deleted.add(t);
            } else {
                getAgentSpecificTermDao().save(t);
            }
        }

        // delete agent terms 
        for (AgentSpecificTerm t : deleted) {
            command.getAgentSpecificTerms().remove(t);

            // delete the term from studies
            if (l != null)
                for (StudyAgent s : l) {
                    getAgentSpecificAdverseEventListService().synchronizeStudyWithAgentTerm(s.getStudy(), t, true);
                }
        }

        if (l != null) {
            System.out.println("0:" + command.getAgent());


            for (StudyAgent s : l) {
                System.out.println(s.getAgent());
                agentDao.merge(s.getAgent());
            }

            command.setAgent(agentDao.merge(command.getAgent()));
            System.out.println("O2:"+command.getAgent());

            for (StudyAgent s : l) {
                System.out.println("X:"+s.getAgent() + (s.getAgent() == command.getAgent()));
            }

            // sync the agent terms with the study
            for (StudyAgent s : l) {
                getAgentSpecificAdverseEventListService().synchronizeStudyWithAgent(s.getStudy(), command.getAgent());
                studyDao.save(s.getStudy());
            }
        }
    }

    @Override
    public void onBind(HttpServletRequest request, AgentCommand command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(final AgentCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
    }

    //
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AgentCommand command) {
        return new InputFieldGroupMap();
    }

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
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

    public AgentSpecificAdverseEventListServiceImpl getAgentSpecificAdverseEventListService() {
        return agentSpecificAdverseEventListService;
    }

    public void setAgentSpecificAdverseEventListService(AgentSpecificAdverseEventListServiceImpl agentSpecificAdverseEventListService) {
        this.agentSpecificAdverseEventListService = agentSpecificAdverseEventListService;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public StudyAgentDao getStudyAgentDao() {
        return studyAgentDao;
    }

    public void setStudyAgentDao(StudyAgentDao studyAgentDao) {
        this.studyAgentDao = studyAgentDao;
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }
}