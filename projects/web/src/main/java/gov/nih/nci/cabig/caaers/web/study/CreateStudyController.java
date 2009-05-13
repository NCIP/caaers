package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Study Controller for 'Create' Workflow
 * 
 */
public class CreateStudyController extends StudyController<StudyCommand> {

    /**
     * Layout Tabs
     * @param flow - flow the Flow object
     */

	
    @Override
    protected void layoutTabs(final Flow<StudyCommand> flow) {
    	/**
    	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
    	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
    	 */
        flow.addTab(new DetailsTab());
        flow.addTab(new StudyTherapiesTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new TreatmentAssignmentTab());
        flow.addTab(new DiseaseTab());
        flow.addTab(new SolicitedAdverseEventTab());
        flow.addTab(new ExpectedAEsTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));

    }

    /**
     * Creates an Study(empty study), with a empty Sponsor,CoordinatingCenter and Identifiers.
     */
    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {

        StudyCommand cmd = new StudyCommand();
        Study study = new Study();        
        cmd.setStudy(study);

        StudyFundingSponsor sponsor = new StudyFundingSponsor();
        sponsor.setPrimary(true);
        study.addStudyFundingSponsor(sponsor);

        StudyCoordinatingCenter cordinatCenter = new StudyCoordinatingCenter();
        study.addStudyOrganization(cordinatCenter);

        OrganizationAssignedIdentifier sponsorIdentifier = new OrganizationAssignedIdentifier();
        sponsorIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
        study.addIdentifier(sponsorIdentifier);

        OrganizationAssignedIdentifier ccIdentifier = new OrganizationAssignedIdentifier();
        ccIdentifier.setPrimaryIndicator(true);
        ccIdentifier.setType(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        study.addIdentifier(ccIdentifier);
        study.addEpoch(new Epoch(Epoch.NAME_BASELINE, 0));
        study.addEpoch(new Epoch(Epoch.NAME_TREATMENT, 1));
        study.addEpoch(new Epoch(Epoch.NAME_POSTTREATMENT, 2));
        
        cmd.setWorkflowEnabled(getConfiguration().get(getConfiguration().ENABLE_WORKFLOW));
        
        return cmd;
    }

    @Override
    protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response, final Object command, final BindException errors) throws Exception {

        StudyCommand cmd = (StudyCommand) command;
        // saveResearchStaff the study by calling merge, as the study might be assocated
        // to different copy of same object (eg: Organization, with same id)
        // in different screens (hibernate session)
        studyDao.merge(cmd.getStudy());

        ModelAndView mv = new ModelAndView("forward:view?type=confirm", errors.getModel());

        return mv;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(request, command);
    }



}
