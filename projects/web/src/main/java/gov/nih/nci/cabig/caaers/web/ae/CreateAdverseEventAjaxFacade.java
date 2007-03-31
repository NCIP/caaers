package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import static gov.nih.nci.cabig.caaers.tools.ObjectTools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Required;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.directwebremoting.WebContextFactory;

import javax.servlet.ServletException;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacade {
    private static final Log log = LogFactory.getLog(CreateAdverseEventAjaxFacade.class);

    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcTermDao ctcTermDao;
    private CtcDao ctcDao;
    private AdverseEventReportDao aeReportDao;
    private InteroperationService interoperationService;

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

    public List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }

    public boolean pushAdverseEventToStudyCalendar(int aeReportId) {
        AdverseEventReport report = aeReportDao.getById(aeReportId);
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
    public String addAdverseEventFormSection(int index, Integer aeReportId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("index", Integer.toString(index));
        return renderAjaxView("adverseEventFormSection", aeReportId, params);
    }

    private String renderAjaxView(String viewName, Integer aeReportId, Map<String, String> params) {
        String mode = aeReportId == null ? "create" : "edit";
        if (aeReportId != null) params.put("aeReport", aeReportId.toString());
        params.put(AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("/pages/ae/%s?%s", mode, createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        try {
            String html = WebContextFactory.get().forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
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
    public void setAeReportDao(AdverseEventReportDao aeReportDao) {
        this.aeReportDao = aeReportDao;
    }

    @Required
    public void setInteroperationService(InteroperationService interoperationService) {
        this.interoperationService = interoperationService;
    }
}
