package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

import java.util.List;

/**
 * @author Biju Joseph
 */

@CaaersUseCases({CREATE_PARTICIPANT})
public class ParticipantTest extends CaaersNoSecurityTestCase {
    private Participant participant;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        participant = new Participant();
    }

    public void testGetStudies() throws Exception {

        Study study1 = Fixtures.createStudy("S1");
        study1.setId(1);

        Fixtures.assignParticipant(participant, study1, Fixtures.SITE);
        Study study2 = Fixtures.createStudy("S2");
        study2.setId(2);

        Fixtures.assignParticipant(participant, study2, Fixtures.SITE);
        Study study3 = Fixtures.createStudy("S3");
        study3.setId(3);

        Fixtures.assignParticipant(participant, study3, Fixtures.SITE);

        List<Study> actual = participant.getStudies();
        assertEquals("Wrong number of studies found", 3, actual.size());
        assertEquals("Wrong first study", "S1", actual.get(0).getShortTitle());
        assertEquals("Wrong second study", "S2", actual.get(1).getShortTitle());
        assertEquals("Wrong third study", "S3", actual.get(2).getShortTitle());
    }

    public void testCheckStudiesCanNotBeAssignedOnSameSiteAgain() throws Exception {
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S1"), Fixtures.SITE, 1);
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S2"), Fixtures.SITE, 1);
        Fixtures.assignParticipant(participant, Fixtures.createStudy("S3"), Fixtures.SITE, 2);

        List<Study> actual = participant.getStudies();
        assertEquals("Wrong number of studies found", 2, actual.size());
    }
}
