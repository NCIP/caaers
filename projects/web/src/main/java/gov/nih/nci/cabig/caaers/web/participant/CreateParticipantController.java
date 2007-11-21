package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyHavingStudySiteQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;

public class CreateParticipantController extends ParticipantController<NewParticipantCommand> {

	private static Log log = LogFactory.getLog(CreateParticipantController.class);

	private StudyDao studyDao;

	private StudyService studyService;

	private StudySiteDao studySiteDao;

	private ListValues listValues;
	private ConfigProperty configurationProperty;
	
	@Override
	protected void layoutTabs(final Flow<NewParticipantCommand> flow) {
		flow.addTab(new CreateParticipantTab());
		flow.addTab(new SelectStudyForParticipantTab());
		flow.addTab(new ReviewParticipantTab());
		// getFlow().addTab(new Tab("Confirmation", "Confirmation", "par/par_confirmation"));
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws Exception {
		log.debug("Entering formBackingObject ...");
		NewParticipantCommand participantCommand = new NewParticipantCommand();
		List<Identifier> identifiers = new ArrayList<Identifier>();
		OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
		organizationAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);
		organizationAssignedIdentifier.setType(configurationProperty.getMap().get("participantIdentifiersType").get(0).getCode());

		// identifiers.add();
		participantCommand.getParticipant().setIdentifiers(identifiers);
		return participantCommand;
	}

	@Override
	protected void onBind(final HttpServletRequest request, final Object command, final BindException errors)
			throws Exception {
		log.debug("Entering onBind...");
		NewParticipantCommand participantCommand = (NewParticipantCommand) command;
		String searchtext = participantCommand.getSearchText();
		String type = participantCommand.getSearchType();
		List<StudySite> studySites = new ArrayList<StudySite>();
		List<Study> studies = null;
		// This will happen on page #2
		if (searchtext != null && type != null && !searchtext.equals("")) {

			log.debug("Search text : " + searchtext + "Type : " + type);
			// studySite.setOrganization(participantCommand.getOrganization());

			participantCommand.setStudies(new ArrayList<Study>());
			StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
			query.filterByStudySiteName(participantCommand.getOrganization().getName());

			if ("st".equals(type)) {
				query.filterByStudyShortTile(searchtext);
				// study.setShortTitle(searchtext);
			}
			// else if ("lt".equals(type)) {
			// study.setLongTitle(searchtext);
			// }
			else if ("idtf".equals(type)) {
				// Identifier identifier = new Identifier();
				// identifier.setValue(searchtext);
				// study.addIdentifier(identifier);
				query.filterByIdentifierValue(searchtext);
				// studies = studyDao.searchByExample(study, true);

			}
			studies = studyDao.find(query);
			participantCommand.setStudies(studies);
			participantCommand.setSearchTypeText("");
			participantCommand.setSearchType("");

		}

		// This will happen every-time studySiteArray is populated
		if (participantCommand.getStudySiteArray() != null) {
			Set<String> studySiteIdSet = new java.util.HashSet<String>(java.util.Arrays.asList(participantCommand
					.getStudySiteArray()));
			for (String siteId : studySiteIdSet) {
				StudySite studySite = studySiteDao.getById(Integer.parseInt(siteId));
				studySites.add(studySite);

			}
			Participant participant = participantCommand.getParticipant();
			List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();
			for (int i = 0; i < studySites.size(); i++) {
				assignments.add(new StudyParticipantAssignment(participant, studySites.get(i)));
			}
			participant.setAssignments(assignments);
			participantCommand.setStudySites(studySites);
		}
	}

	@Required
	public void setStudySiteDao(final StudySiteDao studySiteDao) {
		this.studySiteDao = studySiteDao;
	}

	@Required
	public void setStudyDao(final StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	@Required
	public void setStudyService(final StudyService studyService) {
		this.studyService = studyService;
	}

	@Required
	public void setListValues(final ListValues listValues) {
		this.listValues = listValues;
	}
	
	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}
	@Required
	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
}
