package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class InvestigatorsTab extends StudyTab {
    public InvestigatorsTab() {
        super("Study Investigators", "Investigators", "study/study_investigator");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "invRoleCodeRefData");
        addConfigMapToRefdata(refdata, "invStatusCodeRefData");
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        if ("siteChange".equals(request.getParameter("_action"))) {
            request.getSession().setAttribute("site_id", request.getParameter("_selected"));
            StudySite studySite = command.getStudySites().get(Integer.parseInt(request.getParameter("_selected")));
            if (studySite.getStudyInvestigators().size() == 0) {
                StudyInvestigator studyInvestigator = new StudyInvestigator();
                studyInvestigator.setSiteInvestigator(new SiteInvestigator());
                studySite.addStudyInvestigators(studyInvestigator);
            }
        } else {
            handleStudyInvestigatorAction(command, request.getParameter("_action"),
                request.getParameter("_selected"), request.getParameter("_studysiteindex"));
        }
    }

    private void handleStudyInvestigatorAction(Study study, String action, String selected, String studysiteindex) {
        if ("addInv".equals(action)) {
            StudyInvestigator studyInvestigator = new StudyInvestigator();
            studyInvestigator.setSiteInvestigator(new SiteInvestigator());
            StudySite studySite = study.getStudySites().get(Integer.parseInt(studysiteindex));
            studySite.addStudyInvestigators(studyInvestigator);
        } else if ("removeInv".equals(action)) {
            study.getStudySites().get(Integer.parseInt(studysiteindex)).getStudyInvestigators().remove(Integer.parseInt(selected));
        }
    }
}
