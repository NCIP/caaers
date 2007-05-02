package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class PersonnelTab extends StudyTab {
    public PersonnelTab() {
        super("Study Personnel", "Personnel", "study/study_personnels");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "invRoleCodeRefData");
        addConfigMapToRefdata(refdata, "invStatusCodeRefData");
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        //if(request.getParameter("_action").equals("siteChange"))
        if ("siteChange".equals(request.getParameter("_action"))) {
            request.getSession().setAttribute("site_id_for_per", request.getParameter("_selected"));

            StudySite studySite = ((Study) command).getStudySites().get(Integer.parseInt(request.getParameter("_selected")));
            if (studySite.getStudyPersonnels().size() == 0) {
                StudyPersonnel studyPersonnel = new StudyPersonnel();
                studySite.addStudyPersonnel(studyPersonnel);
            }
        } else {
            handleStudyPersonnelAction((Study) command, request.getParameter("_action"),
                request.getParameter("_selected"), request.getParameter("_studysiteindex"));
        }
    }

    private void handleStudyPersonnelAction(Study study, String action, String selected, String studysiteindex) {
        if ("addInv".equals(action)) {
            StudyPersonnel studyPersonnel = new StudyPersonnel();
            StudySite studySite = study.getStudySites().get(Integer.parseInt(studysiteindex));
            studySite.addStudyPersonnel(studyPersonnel);
        } else if ("removeInv".equals(action)) {
            study.getStudySites().get(Integer.parseInt(studysiteindex)).getStudyPersonnels().remove(Integer.parseInt(selected));
        }

    }
}
