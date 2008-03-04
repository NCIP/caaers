package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyHavingStudySiteQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateParticipantController extends ParticipantController<NewParticipantCommand> {

    private static Log log = LogFactory.getLog(CreateParticipantController.class);

    private StudyDao studyDao;

    private StudySiteDao studySiteDao;

    private ConfigProperty configurationProperty;

    @Override
    protected void layoutTabs(final Flow<NewParticipantCommand> flow) {
        flow.addTab(new CreateParticipantTab());
        flow.addTab(new SelectStudyForParticipantTab());
        flow.addTab(new AssignStudySubjectIdentifierForNewParticipantTab());
        flow.addTab(new ReviewParticipantTab());

    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws Exception {
        log.debug("Entering formBackingObject ...");
        NewParticipantCommand participantCommand = new NewParticipantCommand();
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);
        organizationAssignedIdentifier.setType(configurationProperty.getMap().get(
                        "participantIdentifiersType").get(0).getCode());
        participantCommand.getParticipant().addIdentifier(organizationAssignedIdentifier);
        return participantCommand;
    }

    @Override
    protected void onBind(final HttpServletRequest request, final Object command,
                    final BindException errors) throws Exception {
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
            } else if ("idtf".equals(type)) {
                query.filterByIdentifierValue(searchtext);
            }
            studies = studyDao.find(query);
            participantCommand.setStudies(studies);
            participantCommand.setSearchTypeText("");
            participantCommand.setSearchType("");

        }

        // This will happen every-time studySiteArray is populated
        if (participantCommand.getStudySiteArray() != null) {
            Set<String> studySiteIdSet = new java.util.HashSet<String>(java.util.Arrays
                            .asList(participantCommand.getStudySiteArray()));
            for (String siteId : studySiteIdSet) {
                StudySite studySite = studySiteDao.getById(Integer.parseInt(siteId));
                studySites.add(studySite);

            }
            Participant participant = participantCommand.getParticipant();
            List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();
            for (int i = 0; i < studySites.size(); i++) {
                final StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment(
                                participant, studySites.get(i));
                studyParticipantAssignment.setStudySubjectIdentifier(participantCommand
                                .getStudySubjectIdentifier());
                assignments.add(studyParticipantAssignment);
            }
            participant.setAssignments(assignments);
            participantCommand.setStudySites(studySites);
        }
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(request, command);
    }

    @Required
    public void setStudySiteDao(final StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }

    @Required
    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    @Required
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }
}
