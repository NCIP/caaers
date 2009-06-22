package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Krikor Krumlian
 */

public class NewParticipantCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private String[] studySiteArray;

    private String searchTypeText;

    private String searchType;

    private String searchText;

    private List<Study> studies = new ArrayList<Study>();

    private List<StudySite> studySites = new ArrayList<StudySite>();

    private Organization organization;

    private Participant participant;

    private String studySubjectIdentifier;

    public NewParticipantCommand() {
        participant = new Participant();
        participant.setDateOfBirth(new DateValue(null, null, null));
    }

    public NewParticipantCommand(final Participant participant) {
        this.participant = participant;
    }

    public String[] getStudySiteArray() {
        return studySiteArray;
    }

    public void setStudySiteArray(final String[] studySiteArray) {
        this.studySiteArray = studySiteArray;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(final String searchType) {
        this.searchType = searchType;
    }

    public String getSearchTypeText() {
        return searchTypeText;
    }

    public void setSearchTypeText(final String searchTypeText) {
        this.searchTypeText = searchTypeText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(final List<Study> studies) {
        this.studies = studies;
    }

    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(final List<StudySite> studySites) {
        this.studySites = studySites;

    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(final Participant participant) {
        this.participant = participant;
    }

    public String getStudySubjectIdentifier() {
        return studySubjectIdentifier;
    }

    public void setStudySubjectIdentifier(final String studySubjectIdentifier) {
        this.studySubjectIdentifier = studySubjectIdentifier;
    }
}
