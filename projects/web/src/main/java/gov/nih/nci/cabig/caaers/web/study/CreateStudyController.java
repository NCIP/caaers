package gov.nih.nci.cabig.caaers.web.study;

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
 * @author Priyatam
 */
public class CreateStudyController extends StudyController<Study> {

	/**
	 * Layout Tabs
	 * @param request - flow the Flow object
	 */

	@Override
	protected void layoutTabs(final Flow<Study> flow) {
		flow.addTab(new DetailsTab());
		flow.addTab(new StudyTherapiesTab());
		flow.addTab(new AgentsTab());
		flow.addTab(new TreatmentAssignmentTab());
		flow.addTab(new DiseaseTab());
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
		Study study = new Study();
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
		
		return study;
	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
		Study study = (Study) command;


		// check for study therapy
		updateStudyTherapies(study);

		// saveResearchStaff the study by calling merge, as the study might be assocated
		// to different copy of same object (eg: Organization, with same id)
		// in different screens (hibernate session)
		studyDao.merge(study);

		return new ModelAndView("forward:view?type=confirm", errors.getModel());
	}
}