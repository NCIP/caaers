package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class DiseaseTab extends StudyTab {
    private static Log log = LogFactory.getLog(DiseaseTab.class);

    private DiseaseTermDao diseaseTermDao;
    private LowLevelTermDao lowLevelTermDao;

    public DiseaseTab() {
        super("Study Disease", "Disease", "study/study_studydisease");
    }

    @Override
    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        handleStudyDiseaseAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
    }

    private void handleStudyDiseaseAction(Study study, String action, String selected) {
        if ("addMeddraStudyDisease".equals(action)) {
            String diseaseCode = study.getDiseaseLlt();
            MeddraStudyDisease meddraStudyDisease = new MeddraStudyDisease();
            meddraStudyDisease.setMeddraCode(diseaseCode);
            meddraStudyDisease.setTerm(lowLevelTermDao.getById(Integer.parseInt(diseaseCode)) == null ?
                lowLevelTermDao.getById(1) : lowLevelTermDao.getById(Integer.parseInt(diseaseCode)));
            study.addMeddraStudyDisease(meddraStudyDisease);
        }
        if ("removeMeddraStudyDisease".equals(action)) {
            study.getMeddraStudyDiseases().remove(Integer.parseInt(selected));
        }


        if ("addStudyDisease".equals(action)) {
            String[] diseases = study.getDiseaseTermIds();
            log.debug("Study Diseases Size : " + study.getCtepStudyDiseases().size());
            for (String diseaseId : diseases) {
                log.debug("Disease Id : " + diseaseId);
                //StudyDisease studyDisease = new StudyDisease();
                //studyDisease.setDiseaseTerm(diseaseTermDao.getById(Integer.parseInt(diseaseId)));
                //study.addStudyDisease(studyDisease);
                CtepStudyDisease ctepStudyDisease = new CtepStudyDisease();
                ctepStudyDisease.setTerm(diseaseTermDao.getById(Integer.parseInt(diseaseId)));
                study.addCtepStudyDisease(ctepStudyDisease);

            }
        } else if ("removeStudyDisease".equals(action)) {
            study.getCtepStudyDiseases().remove(Integer.parseInt(selected));
        }
    }

    public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
        this.diseaseTermDao = diseaseTermDao;
    }
    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }
}
