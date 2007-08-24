package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public EditStudyController() {
		setBindOnNewForm(true);
	}

	// /LOGIC

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
		Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));

		if (log.isDebugEnabled()) {
			log.debug("Retrieved Study :" + String.valueOf(study));
		}

		// update the INDType of StudyAgents
		if (study.getStudyAgentsInternal() != null && study.getStudyAgentsInternal().size() > 0) {
			for (StudyAgent sa : study.getStudyAgentsInternal()) {
				// update the IND Type.
				List<StudyAgentINDAssociation> sas = sa.getStudyAgentINDAssociationsInternal();
				if (sas == null || sas.isEmpty()) {
					sa.setIndType(0);
				}
				else if (sas.get(0).getInvestigationalNewDrug().getIndNumber() == -111) {
					sa.setIndType(1);
				}
				else {
					sa.setIndType(2);
				}

			}
		}

		return study;
	}

	@Override
	protected Study save(final Study study, final Errors errors) {
		if (errors.hasErrors()) {
			return study;
		}
		Study mergedStudy = getDao().merge(study);
		mergedStudy.setStudySiteIndex(study.getStudySiteIndex());
		getDao().initialize(mergedStudy);

		updateOrganizationAssignedIdentifier(study, mergedStudy);
		updateStudyCordinatingCentere(mergedStudy);

		if (!study.getIdentifiersLazy().isEmpty()) {
			for (Identifier identifier : study.getIdentifiersLazy()) {
				if (identifier.getId() == null) {
					mergedStudy.addIdentifier(identifier);
				}

			}
		}
		// mergedStudy.setStudyTherapies(study.getStudyTherapies());
		// now check for study therapies.
		mergedStudy.setChemoTherapyType(study.getChemoTherapyType());
		mergedStudy.setDeviceTherapyType(study.getDeviceTherapyType());
		mergedStudy.setRadiationTherapyType(study.getRadiationTherapyType());
		mergedStudy.setSurgeryTherapyType(study.getSurgeryTherapyType());
		updateStudyTherapies(mergedStudy);

		getDao().save(mergedStudy);
		return mergedStudy;
	}

	private void updateOrganizationAssignedIdentifier(final Study study, final Study mergedStudy) {

		OrganizationAssignedIdentifier organizationAssignedIdentifier = study.getOrganizationAssignedIdentifier();
		if (study.getMultiInstitutionIndicator().equals(Boolean.TRUE)
				&& organizationAssignedIdentifier.getOrganization() != null
				&& organizationAssignedIdentifier.getId() == null) {

			mergedStudy.addIdentifier(organizationAssignedIdentifier);

			StudyCoordinatingCenter studyCoordinatingCenter = new StudyCoordinatingCenter();

			studyCoordinatingCenter.setStudy(mergedStudy);
			studyCoordinatingCenter.setOrganization(organizationAssignedIdentifier.getOrganization());
			mergedStudy.addStudyOrganization(studyCoordinatingCenter);
		}
		else if (study.getMultiInstitutionIndicator().equals(Boolean.FALSE)
				&& organizationAssignedIdentifier.getId() != null) {

			// remove both study cordinating centere and organization identifier
			StudyCoordinatingCenter studyCoordinatingCenter = mergedStudy.getStudyCoordinatingCenter();
			mergedStudy.removeStudyOrganization(studyCoordinatingCenter);
			mergedStudy.removeIdentifier(organizationAssignedIdentifier);

		}
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
		flow.addTab(new SitesTab());
		flow.addTab(new InvestigatorsTab());
		flow.addTab(new PersonnelTab());
		flow.addTab(new IdentifiersTab());
		flow.addTab(new StudyAmendmentTab());

	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
		Study study = (Study) command;
		updateStudyCordinatingCentere(study);
		studyDao.merge(study);
		return new ModelAndView(new RedirectView("search"));
	}

	@Override
	protected boolean shouldSave(final HttpServletRequest request, final Study command, final Tab<Study> tab) {
		// supress for ajax and delete requests
		Object isAjax = findInRequest(request, "_isAjax");
		if (isAjax != null) {
			return false;
		}

		String action = (String) super.findInRequest(request, "_action");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
			return false;
		}
		return super.shouldSave(request, command, tab) && tab.getNumber() != 0; // dont save if it is overview page
	}

	private void updateStudyCordinatingCentere(final Study mergedStudy) {
		OrganizationAssignedIdentifier organizationAssignedIdentifier = mergedStudy.getOrganizationAssignedIdentifier();
		if (mergedStudy.getMultiInstitutionIndicator().equals(Boolean.TRUE)
				&& organizationAssignedIdentifier.getOrganization() != null) {

			StudyCoordinatingCenter studyCoordinatingCenter = mergedStudy.getStudyCoordinatingCenter();
			if (!studyCoordinatingCenter.getOrganization().getId().equals(
					organizationAssignedIdentifier.getOrganization().getId())) {
				studyCoordinatingCenter.setOrganization(organizationAssignedIdentifier.getOrganization());
				studyCoordinatingCenter.setStudy(mergedStudy);
				mergedStudy.addStudyOrganization(studyCoordinatingCenter);
			}

		}
	}
}