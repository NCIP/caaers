package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.CodedGrade;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import static gov.nih.nci.cabig.caaers.tools.ObjectTools.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.AbstractFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacade {
    private static final Log log = LogFactory.getLog(CreateAdverseEventAjaxFacade.class);

    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcTermDao ctcTermDao;
    private CtcCategoryDao ctcCategoryDao;
    private CtcDao ctcDao;
    private LowLevelTermDao lowLevelTermDao;
    private ExpeditedAdverseEventReportDao aeReportDao;
    private ResearchStaffDao researchStaffDao;
    private AnatomicSiteDao anatomicSiteDao;
    private InteroperationService interoperationService;
    private PriorTherapyDao priorTherapyDao;
    private PreExistingConditionDao preExistingConditionDao;
    private AgentDao agentDao;

    public List<AnatomicSite> matchAnatomicSite(String text) {
        return anatomicSiteDao.getBySubnames(extractSubnames(text));
    } 
    
    public List<PriorTherapy> matchPriorTherapies(String text) {
        return priorTherapyDao.getBySubnames(extractSubnames(text));
    }
    
    public List<PreExistingCondition> matchPreExistingConds(String text) {
        return preExistingConditionDao.getBySubnames(extractSubnames(text));
    }
    
    public List<LowLevelTerm> matchLowLevelTermsByCode(String text) {
        return lowLevelTermDao.getBySubnames(extractSubnames(text));
    }
    
    public List<Agent> matchAgents(String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(agents,"id", "name", "nscNumber","description");
    }


    public ResearchStaff getResearchStaff(String text) {
        ResearchStaff researchStaff = researchStaffDao.getById(Integer.parseInt(text));
        return reduce(researchStaff, "id", "firstName", "lastName", "middleName");
    }
    
    public List<Participant> matchParticipants(String text, Integer studyId) {
        List<Participant> participants = participantDao.getBySubnames(extractSubnames(text));
        if (studyId != null) {
            for (Iterator<Participant> it = participants.iterator(); it.hasNext();) {
                Participant participant = it.next();
                if (!onStudy(participant, studyId)) it.remove();
            }
        }
        // cut down objects for serialization
        return reduceAll(participants, "firstName", "lastName", "id");
    }

    private boolean onStudy(Participant participant, Integer studyId) {
        boolean onStudy = false;
        for (StudyParticipantAssignment assignment : participant.getAssignments()) {
            if (assignment.getStudySite().getStudy().getId().equals(studyId)) {
                onStudy = true;
                break;
            }
        }
        return onStudy;
    }

    public List<Study> matchStudies(String text, Integer participantId) {
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));
        if (participantId != null) {
            for (Iterator<Study> it = studies.iterator(); it.hasNext();) {
                Study study = it.next();
                if (!onStudy(study, participantId)) it.remove();
            }
        }
        // cut down objects for serialization
        return reduceAll(studies, "id", "shortTitle");
    }

    private boolean onStudy(Study study, Integer participantId) {
        boolean onStudy = false;
        for (StudySite studySite : study.getStudySites()) {
            for (StudyParticipantAssignment assignment : studySite.getStudyParticipantAssignments()) {
                if (assignment.getParticipant().getId().equals(participantId)) {
                    onStudy = true;
                    break;
                }
            }
        }
        return onStudy;
    }

    public List<CtcTerm> matchTerms(String text, Integer ctcVersionId, Integer ctcCategoryId, int limit) throws Exception {
        List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames(text), ctcVersionId, ctcCategoryId);
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        while (terms.size() > limit) {
            terms.remove(terms.size() - 1);
        }
        return terms;
    }
    
    public List<CtcTerm> getTermsByCategory(Integer ctcCategoryId) throws Exception {
        List<CtcTerm> terms;
        if (ctcCategoryId == 0) {
            terms = ctcTermDao.getAll();
        } else {
            terms = ctcCategoryDao.getById(ctcCategoryId).getTerms();
        }
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        return terms;
    }

    public List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }
    
    public List<? extends CodedGrade> getTermGrades(int ctcTermId) {
        List<CodedGrade> list = ctcTermDao.getById(ctcTermId).getGrades();
        // have to detect whether it's a collection of Grade or CtcGrade;
        // if the latter, need to call reduce
        if (list.size() == 0) {
            return list;
        } else if (list.get(0) instanceof Grade) {
            return list;
        } else {
            return reduceAll(list, "grade", "text");
        }
    }

    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }

    public boolean pushAdverseEventToStudyCalendar(int aeReportId) {
        ExpeditedAdverseEventReport report = aeReportDao.getById(aeReportId);
        try {
            interoperationService.pushToStudyCalendar(report);
            return true;
        } catch (CaaersSystemException ex) {
            // this happens if the interoperationService isn't correctly configured
            return false;
        } catch (RuntimeException re) {
            log.error("Unexpected error in communicating with study calendar", re);
            return false;
        }
    }

    /**
     * Returns the HTML for the section of the basic AE entry form for
     * the adverse event with the given index
     * @param index
     * @return
     */
    public String addAdverseEvent(int index, Integer aeReportId) {
        return renderIndexedAjaxView("adverseEventFormSection", index, aeReportId);
    }
    
    /**
     * Returns the HTML for the section of the basic AE entry form for
     * the adverse event with the given index
     * @param index
     * @return
     */
    public String addAdverseEventMeddra(int index, Integer aeReportId) {
        return renderIndexedAjaxView("adverseEventMeddraFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the lab form for
     * the lab with the given index
     * @param index
     * @return
     */
    public String addLab(int index, Integer aeReportId) {
        return renderIndexedAjaxView("labFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the concomitant medications form for
     * the concomitant medication with the given index
     * @param index
     * @return
     */
    public String addConcomitantMedication(int index, Integer aeReportId) {
        return renderIndexedAjaxView("conMedFormSection", index, aeReportId);
    }
    
    /**
     * Returns the HTML for the section of the concomitant medications form for
     * the concomitant medication with the given index
     * @param index
     * @return
     */
    public String addPreExistingCond(int index, Integer aeReportId) {
        return renderIndexedAjaxView("preExistingCondFormSection", index, aeReportId);
    }
    
    /**
     * Returns the HTML for the section of the metastatic disease site form for
     * the metastatic disease with the given index
     * @param index
     * @return
     */
    public String addMetastaticDiseaseSite(int index, Integer aeReportId) {
        return renderIndexedAjaxView("metastaticFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the course agent form for
     * the course agent with the given index
     * @param index
     * @return
     */
    public String addCourseAgent(int index, Integer aeReportId) {
        return renderIndexedAjaxView("courseAgentFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     * @param index
     * @return
     */
    public String addOtherCause(int index, Integer aeReportId) {
        return renderIndexedAjaxView("otherCauseFormSection", index, aeReportId);
    }
    
    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     * @param index
     * @return
     */
    public String addPriorTherapy(int index, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyFormSection", index, aeReportId);
    }
    
    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     * @param index
     * @return
     */
    public String addPriorTherapyAgent(int index,int parentIndex, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyAgentFormSection", index, parentIndex, aeReportId);
    }
    
    private String renderIndexedAjaxView(String viewName, int index, int parentIndex, Integer aeReportId) {
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("index", Integer.toString(index));
        params.put("parentIndex", Integer.toString(parentIndex));
        return renderAjaxView(viewName, aeReportId, params);
    }

    private String renderIndexedAjaxView(String viewName, int index, Integer aeReportId) {
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("index", Integer.toString(index));
        return renderAjaxView(viewName, aeReportId, params);
    }

    private String renderAjaxView(String viewName, Integer aeReportId, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        if (aeReportId != null) params.put("aeReport", aeReportId.toString());
        params.put(AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s",
            getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        try {
            String html = webContext.forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }

    private String getCurrentPageContextRelative(WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();
        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }

    // TODO: there's got to be a library version of this somewhere
    private String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    ////// CONFIGURATION

    @Required
    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    @Required
    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    @Required
    public void setAeReportDao(ExpeditedAdverseEventReportDao aeReportDao) {
        this.aeReportDao = aeReportDao;
    }
    
    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    @Required
    public void setInteroperationService(InteroperationService interoperationService) {
        this.interoperationService = interoperationService;
    }

    
    @Required
    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }
    
    @Required
	public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}
    @Required
	public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
		this.ctcCategoryDao = ctcCategoryDao;
	}
    
    @Required
	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

	public void setPreExistingConditionDao(
			PreExistingConditionDao preExistingConditionDao) {
		this.preExistingConditionDao = preExistingConditionDao;
	}
	@Required
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
	
    
    
    
    
}
