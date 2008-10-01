package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public class EditStudyController extends StudyController<Study> {

    private static final Log log = LogFactory.getLog(EditStudyController.class);

    private Task task;

    public EditStudyController() {
        setBindOnNewForm(true);
    }

    // /LOGIC

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        request.getSession().removeAttribute(CreateStudyAjaxFacade.CREATE_STUDY_FORM_NAME);

        Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));
        //to support backward compatability, epochs has to be preinitalized
        if (study.getEpochs() == null || study.getEpochs().isEmpty()) {
            study.addEpoch(new Epoch("Baseline", 0));
            study.addEpoch(new Epoch("Treatment", 1));
            study.addEpoch(new Epoch("Post-treatment", 2));
        }

        if (log.isDebugEnabled()) {
            log.debug("Retrieved Study :" + String.valueOf(study));
        }

        return study;
    }

    @Override
    protected Study save(final Study study, final Errors errors) {
        if (errors.hasErrors()) {
            return study;
        }
        Study mergedStudy = getDao().merge(study);

        getDao().initialize(mergedStudy);

        // update the studySiteIndex
        mergedStudy.setStudySiteIndex(study.getStudySiteIndex());

        // now check for study therapies.
        mergedStudy.setDrugAdministrationTherapyType(study.getDrugAdministrationTherapyType());
        mergedStudy.setDeviceTherapyType(study.getDeviceTherapyType());
        mergedStudy.setRadiationTherapyType(study.getRadiationTherapyType());
        mergedStudy.setSurgeryTherapyType(study.getSurgeryTherapyType());

        mergedStudy.setAdeersPDFType(study.getAdeersPDFType());
        mergedStudy.setCaaersXMLType(study.getCaaersXMLType());
        mergedStudy.setCiomsPDFType(study.getCiomsPDFType());
        mergedStudy.setCiomsSaePDFType(study.getCiomsSaePDFType());
        mergedStudy.setDcpSAEPDFType(study.getDcpSAEPDFType());
        mergedStudy.setMedwatchPDFType(study.getMedwatchPDFType());
        return mergedStudy;
    }

    @Override
    protected boolean isSummaryEnabled() {
        return true;
    }

    @Override
    protected void layoutTabs(final Flow<Study> flow) {
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
        flow.addTab(new DetailsTab());
        flow.addTab(new StudyTherapiesTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new TreatmentAssignmentTab());
        flow.addTab(new DiseaseTab());
        flow.addTab(new SolicitedAdverseEventTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new IdentifiersTab());

    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(final HttpServletRequest request, final Object command,
                                final Errors errors, final int page) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, command, errors, page);

        refdata.put("currentTask", task);
        Study study = (Study) command;
        if (isSummaryEnabled()) {
            List<ListValues> summary = new ArrayList<ListValues>();
            if (study.getShortTitle() != null) {
                summary.add(new ListValues("Short title", study.getShortTitle()));
            }
            if (study.getPrimaryIdentifier() != null) {
                summary.add(new ListValues("Primary identifier", study.getPrimaryIdentifier()
                        .toString()));
            }
            if (study.getPhaseCode() != null) {
                summary.add(new ListValues("Phase", study.getPhaseCode().toString()));
            }

            if (study.getPrimarySponsorCode() != null) {
                summary.add(new ListValues("Funding sponsor", study
                        .getPrimaryFundingSponsorOrganization().getName()));
            }
            if (study.getStudyCoordinatingCenter().getOrganization() != null) {
                summary.add(new ListValues("Coordinating center", study
                        .getStudyCoordinatingCenter().getOrganization().getName()));
            }

            // if (page != 1) {
            refdata.put("studySummary", summary);
            // }
        }

        return refdata;
    }

    @Override
    protected ModelAndView processFinish(final HttpServletRequest request,
                                         final HttpServletResponse response, final Object command,
                                         final BindException errors) throws Exception {
        Study study = (Study) command;
        studyDao.merge(study);
        return new ModelAndView(new RedirectView("search"));
    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final Study command,
                                 final Tab<Study> tab) {
        // supress for ajax and delete requests
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null) {
            return false;
        }

        String action = (String) super.findInRequest(request, "_action");
        if (org.apache.commons.lang.StringUtils.equals(action, "removeInv")) {
            return true;
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
            return false;
        }
        return super.shouldSave(request, command, tab) && tab.getNumber() != 0; // dont study if it
        // is overview page
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}