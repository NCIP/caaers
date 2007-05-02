package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Identifier;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class IdentifiersTab extends StudyTab {
    private SiteDao siteDao;

    public IdentifiersTab() {
        super("Study Identifiers", "Identifiers", "study/study_identifiers");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();

        refdata.put("identifiersSourceRefData", siteDao.getAll());
        refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        handleIdentifierAction((Study) command,
            request.getParameter("_action"),
            request.getParameter("_selected"));
    }

    private void handleIdentifierAction(Study study, String action, String selected) {
        if ("addIdentifier".equals(action)) {
            Identifier id = new Identifier();
            id.setValue("<enter value>");
            study.addIdentifier(id);
        } else if ("removeIdentifier".equals(action)) {
            study.getIdentifiers().remove(Integer.parseInt(selected));
        }
    }
    
    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }
}
