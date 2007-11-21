package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

import java.util.List;

@CaaersUseCases({ CREATE_PARTICIPANT})
public class ParticipantTest extends CaaersTestCase {
    private Participant participant;
    private static final Organization SITE = Fixtures.createOrganization("Round abouts here");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        participant = new Participant();
    }

    public void testGetStudies() throws Exception {
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S1"), SITE);
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S2"), SITE);
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S3"), SITE);

        List<Study> actual = participant.getStudies();
        assertEquals("Wrong number of studies found", 3, actual.size());
        assertEquals("Wrong first study", "S1", actual.get(0).getShortTitle());
        assertEquals("Wrong second study", "S2", actual.get(1).getShortTitle());
        assertEquals("Wrong third study", "S3", actual.get(2).getShortTitle());
    }
}
