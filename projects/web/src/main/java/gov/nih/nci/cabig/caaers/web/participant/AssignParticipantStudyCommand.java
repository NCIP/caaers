package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class AssignParticipantStudyCommand extends ParticipantInputCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private List<Participant> participantSearchResults = new ArrayList<Participant>();
    private List<StudySite> studySites = new ArrayList<StudySite>();
    private StudySite studySite;

    public List<Participant> getParticipantSearchResults() {
        return participantSearchResults;
    }

    public void setParticipantSearchResults(final List<Participant> participantSearchResults) {
        this.participantSearchResults = participantSearchResults;
    }

    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(final List<StudySite> studySites) {
        this.studySites = studySites;
    }

    public StudySite getStudySite() {
        return studySite;
    }

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }
    
}
