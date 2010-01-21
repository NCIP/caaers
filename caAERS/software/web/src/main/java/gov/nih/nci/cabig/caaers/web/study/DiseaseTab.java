package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.ConditionDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * @author Ion Oluru
 * @author Biju Joseph
 */
public class DiseaseTab extends StudyTab {
    private static Log log = LogFactory.getLog(DiseaseTab.class);

    private DiseaseTermDao diseaseTermDao;
    private LowLevelTermDao lowLevelTermDao;
    private ConditionDao conditionDao;
    private HashMap<String, Condition> conditionMap;
    
    public DiseaseTab() {
        super("Disease", "Disease", "study/study_diseases");
    }

    /*
     * If CTEP Study Disease 1. Check if the existing CTEP disease ID is mentioned in
     * study.getDiseaseTermIds() 2. Throw error, saying that the selected disease term already
     * exists.
     * 
     * If Medra Study Disease 1. Check if the existing MEDRA disease (LowLevelTerm) is mentioned in
     * study.getDiseaseLlt() 2. Throw error, saying that the selected disease already present.
     * 
     * If Study Condition, if already existing will throw error. 
     */
    @Override
    protected void validate(StudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        HashMap<String, DiseaseTerm> ctepTermMap = new HashMap<String, DiseaseTerm>();
        for (CtepStudyDisease ctepDisease : command.getStudy().getCtepStudyDiseases()) {
        	if(ctepDisease.isRetired()) continue;
            ctepTermMap.put(ctepDisease.getTerm().getId().toString(), ctepDisease.getDiseaseTerm());
        }

        String[] newCTEPTermIds = command.getDiseaseTermIds();
        if (newCTEPTermIds != null) {
            for (String newCTEPTermId : newCTEPTermIds) {
                if (ctepTermMap.containsKey(newCTEPTermId)) {
                    errors.reject("STU_005", new Object[]{ctepTermMap.get(newCTEPTermId).getFullName()}, "'" + ctepTermMap.get(newCTEPTermId).getFullName() + "' is already associated to this study");
                }
            }
        }

        HashMap<String, LowLevelTerm> medraTermMap = new HashMap<String, LowLevelTerm>();
        for (MeddraStudyDisease meddraStudyDisease : command.getStudy().getMeddraStudyDiseases()) {
        	if(meddraStudyDisease.isRetired()) continue;
            medraTermMap.put(meddraStudyDisease.getTerm().getId().toString(), meddraStudyDisease.getTerm());
        }

        if (command.getDiseaseLlt() != null) {
            if (medraTermMap.containsKey(command.getDiseaseLlt())) {
                errors.reject("STU_005",new Object[]{medraTermMap.get(command.getDiseaseLlt()).getFullName()}, "'" + medraTermMap.get(command.getDiseaseLlt()).getFullName() + "' is already associated to this study");
                command.setDiseaseLlt(null);
            }
        }

        if (checkDuplicateConditionById(conditionMap, command.getCondition())) {
            errors.reject("DUPLICATE_STUDY_CONDITION", new Object[] {(conditionMap.get(command.getCondition())).getConditionName()}, "");
        }
    }

    @Override
    public void postProcess(HttpServletRequest request, StudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        if (!errors.hasErrors()) {
            handleStudyDiseaseAction(errors, command, request.getParameter("_action"), request.getParameter("_selected"), request);
            command.setDiseaseLlt(null);
        }
    }

    @Override
    public void onBind(HttpServletRequest request, StudyCommand command, Errors errors) {
        super.onBind(request, command, errors);
        if (command.getPrimaryStudyDisease() == null) return;

        if (command.getStudy().getCtepStudyDiseases() != null) {
            for (byte i=0; i< command.getStudy().getCtepStudyDiseases().size(); i++) {
                CtepStudyDisease d = command.getStudy().getCtepStudyDiseases().get(i);
                d.setLeadDisease(i == command.getPrimaryStudyDisease().intValue());
            }
        }
        if (command.getStudy().getMeddraStudyDiseases() != null) {
            for (byte i=0; i< command.getStudy().getMeddraStudyDiseases().size(); i++) {
                MeddraStudyDisease d = command.getStudy().getMeddraStudyDiseases().get(i);
                d.setLeadDisease(i == command.getPrimaryStudyDisease().intValue());
            }
        }
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        Study study = command.getStudy();

        // this will hold the Study Conditions' IDs as keys
        conditionMap = new HashMap<String, Condition>();
        for (StudyCondition studyCondition : study.getStudyConditions()) {
        	if(studyCondition.isRetired()) continue;
            conditionMap.put(studyCondition.getTerm().getId().toString(), studyCondition.getTerm());
        }

        if (command.getStudy().getCtepStudyDiseases() != null) {
            for (byte i=0; i< command.getStudy().getCtepStudyDiseases().size(); i++) {
                CtepStudyDisease d = command.getStudy().getCtepStudyDiseases().get(i);
                if (d.getLeadDisease() != null && d.getLeadDisease()) command.setPrimaryStudyDisease(new Integer(i));
            }
        }

        if (command.getStudy().getMeddraStudyDiseases() != null) {
            for (byte i=0; i< command.getStudy().getMeddraStudyDiseases().size(); i++) {
                MeddraStudyDisease d = command.getStudy().getMeddraStudyDiseases().get(i);
                if (d.getLeadDisease() != null && d.getLeadDisease()) command.setPrimaryStudyDisease(new Integer(i));
            }
        }

        String diseaseTerminology = "MEDDRA";
        if (study.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP) diseaseTerminology = "CTEP";
        else if (study.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.OTHER) diseaseTerminology = "OTHER";
        refdata.put("diseaseTerminology", diseaseTerminology);

        if (study.getDiseaseTerminology().getDiseaseCodeTerm().equals(DiseaseCodeTerm.MEDDRA)) {
            refdata.put("meddraVersionId", study.getDiseaseTerminology().getMeddraVersion().getId());
            refdata.put("meddraVersion", study.getDiseaseTerminology().getMeddraVersion().getName());
        }
        return refdata;
    }

    private boolean checkDuplicateConditionById(Map conditions, String conditionName) {
        if (conditionMap == null) return false;
        return (conditionMap.containsKey(conditionName));
    }

    private boolean checkDuplicateConditionByText(Map conditions, String conditionName) {
        Iterator it = conditionMap.keySet().iterator();
        while (it.hasNext()) {
            Condition c = (Condition)conditionMap.get(it.next());
            if (conditionName.equals(c.getConditionName())) return true;
        }
        return false;
    }

    private void handleStudyDiseaseAction(Errors errors, StudyCommand command, String action, String selected, HttpServletRequest request) {

        if ("addMeddraStudyDisease".equals(action) && command.getDiseaseLlt().length() > 0 && command.getStudy().getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA) {
            String diseaseCode = command.getDiseaseLlt();
            MeddraStudyDisease meddraStudyDisease = new MeddraStudyDisease();
            // meddraStudyDisease.setMeddraCode(diseaseCode);
            meddraStudyDisease.setTerm(lowLevelTermDao.getById(Integer.parseInt(diseaseCode)) == null ? lowLevelTermDao.getById(1): lowLevelTermDao.getById(Integer.parseInt(diseaseCode)));
            command.getStudy().addMeddraStudyDisease(meddraStudyDisease);
        }

        if ("removeMeddraStudyDisease".equals(action)) {
            command.deleteMeddraStudyDiseaseAtIndex(Integer.parseInt(selected));
        }

        if (action.equals("addOtherCondition")) {

            Condition condition = null;
            int conditionId = 0;

            try {
                conditionId = Integer.parseInt(command.getCondition());
            } catch (NumberFormatException e) {
                log.warn("Incorrect ID for the Condition Object.");
                e.printStackTrace();
            }
            StudyCondition studyCondition = new StudyCondition();

            if (conditionId > 0) {
                condition = conditionDao.getById(conditionId);
                studyCondition.setTerm(condition);
            } else {

                Condition newCondition = new Condition();
                if (StringUtils.isNotBlank(request.getParameter("condition-input")))
                    newCondition.setConditionName(StringEscapeUtils.escapeHtml(request.getParameter("condition-input")));

                if (checkDuplicateConditionByText(conditionMap, newCondition.getConditionName())) {
                    errors.reject("DUPLICATE_STUDY_CONDITION", new Object[] {newCondition.getConditionName()}, "");
                } else {
                    conditionDao.save(newCondition);
                    studyCondition.setTerm(newCondition);
                }
                
            }
            if (!errors.hasErrors()) command.getStudy().addStudyCondition(studyCondition);
        }

        if (action.equals("removeOtherCondition")) {
            try {
                command.deleteStudyConditionAtIndex(Integer.parseInt(selected));
            } catch (IndexOutOfBoundsException e) {
                log.warn("No <StudyCondition> at the position: " + selected);
            }
        }

        if ("addStudyDisease".equals(action) && command.getStudy().getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP) {
            String[] diseases = command.getDiseaseTermIds();
            log.debug("Study Diseases Size : " + command.getStudy().getCtepStudyDiseases().size());
            for (String diseaseId : diseases) {
                log.debug("Disease Id : " + diseaseId);
                CtepStudyDisease ctepStudyDisease = new CtepStudyDisease();
                ctepStudyDisease.setTerm(diseaseTermDao.getById(Integer.parseInt(diseaseId)));
                command.getStudy().addCtepStudyDisease(ctepStudyDisease);

            }
        } else if ("removeStudyDisease".equals(action)) {
            command.deleteCtepStudyDiseaseAtIndex(Integer.parseInt(selected));
        }
    }

    public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
        this.diseaseTermDao = diseaseTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }


    public ConditionDao getConditionDao() {
        return conditionDao;
    }

    public void setConditionDao(ConditionDao conditionDao) {
        this.conditionDao = conditionDao;
    }
}
