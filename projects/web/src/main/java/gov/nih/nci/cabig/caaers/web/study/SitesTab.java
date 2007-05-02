package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class SitesTab extends StudyTab {
    private SiteDao siteDao;

    public SitesTab() {
        super("Study Sites", "Sites", "study/study_studysite");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();

        refdata.put("sitesRefData", siteDao.getAll());
        addConfigMapToRefdata(refdata, "studySiteStatusRefData");
        addConfigMapToRefdata(refdata, "studySiteRoleCodeRefData");
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        String action = request.getParameter("_action");
        if ("addSite".equals(action)) {
            StudySite studySite = new StudySite();
            command.addStudySite(studySite);
        } else if ("removeSite".equals(action)) {
            command.getStudySites().remove(Integer.parseInt(request.getParameter("_selected")));
        }
    }

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }
}
