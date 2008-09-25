package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class DiseaseTab extends StudyTab {
    private static Log log = LogFactory.getLog(DiseaseTab.class);

    private DiseaseTermDao diseaseTermDao;

    private LowLevelTermDao lowLevelTermDao;

    private MeddraVersionDao meddraVersionDao;

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
     */
    @Override
    protected void validate(Study command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        HashMap<String, DiseaseTerm> ctepTermMap = new HashMap<String, DiseaseTerm>();
        for (CtepStudyDisease ctepDisease : command.getCtepStudyDiseases()) {
            ctepTermMap.put(ctepDisease.getTerm().getId().toString(), ctepDisease.getDiseaseTerm());
        }

        String[] newCTEPTermIds = command.getDiseaseTermIds();
        if (newCTEPTermIds != null) {
            for (String newCTEPTermId : newCTEPTermIds) {
                if (ctepTermMap.containsKey(newCTEPTermId)) {
                    errors.reject("DUPLICATE", "'" + ctepTermMap.get(newCTEPTermId).getFullName()
                                    + "' is already associated to this study");
                }
            }
        }

        HashMap<String, LowLevelTerm> medraTermMap = new HashMap<String, LowLevelTerm>();
        for (MeddraStudyDisease meddraStudyDisease : command.getMeddraStudyDiseases()) {
            medraTermMap.put(meddraStudyDisease.getTerm().getId().toString(), meddraStudyDisease
                            .getTerm());
        }
        if (command.getDiseaseLlt() != null) {
            if (medraTermMap.containsKey(command.getDiseaseLlt())) {
                errors.reject("DUPLICATE", "'"
                                + medraTermMap.get(command.getDiseaseLlt()).getFullName()
                                + "' is already associated to this study");
                command.setDiseaseLlt(null);
            }
        }
    }

    @Override
    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        super.postProcess(request, command, errors);
        if (!errors.hasErrors()) {
            handleStudyDiseaseAction(command, request.getParameter("_action"), request
                            .getParameter("_selected"));
            command.setDiseaseLlt(null);
        }
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Study command) {
        Map<String, Object> refdata = super.referenceData(command);
        //refdata.put("meddraVersion",
        //                command.getAeTerminology().getMeddraVersion() != null ? command
        //                                .getAeTerminology().getMeddraVersion().getName()
        //                                : meddraVersionDao.getAll().get(0).getName());
        refdata
                        .put("diseaseTerminology", command.getDiseaseTerminology()
                                        .getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP ? "CTEP"
                                        : "MEDDRA");
        if(command.getDiseaseTerminology().getDiseaseCodeTerm().equals(DiseaseCodeTerm.MEDDRA)){
        	refdata.put("meddraVersionId", command.getDiseaseTerminology().getMeddraVersion().getId());
        	refdata.put("meddraVersion", command.getDiseaseTerminology().getMeddraVersion().getName());
        }
        return refdata;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(Study command) {

        return super.createFieldGroups(command);
    }

    private void handleStudyDiseaseAction(Study study, String action, String selected) {
        if ("addMeddraStudyDisease".equals(action)
                        && study.getDiseaseLlt().length() > 0
                        && study.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA) {
            String diseaseCode = study.getDiseaseLlt();
            MeddraStudyDisease meddraStudyDisease = new MeddraStudyDisease();
            // meddraStudyDisease.setMeddraCode(diseaseCode);
            meddraStudyDisease
                            .setTerm(lowLevelTermDao.getById(Integer.parseInt(diseaseCode)) == null ? lowLevelTermDao
                                            .getById(1)
                                            : lowLevelTermDao
                                                            .getById(Integer.parseInt(diseaseCode)));
            study.addMeddraStudyDisease(meddraStudyDisease);
        }
        if ("removeMeddraStudyDisease".equals(action)) {
            study.getMeddraStudyDiseases().remove(Integer.parseInt(selected));
        }

        if ("addStudyDisease".equals(action)
                        && study.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP) {
            String[] diseases = study.getDiseaseTermIds();
            log.debug("Study Diseases Size : " + study.getCtepStudyDiseases().size());
            for (String diseaseId : diseases) {
                log.debug("Disease Id : " + diseaseId);
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

    public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }

}
