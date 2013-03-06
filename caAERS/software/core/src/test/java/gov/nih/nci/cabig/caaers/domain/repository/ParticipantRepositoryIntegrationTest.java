/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class ParticipantRepositoryIntegrationTest extends CaaersDbTestCase {

    ParticipantRepository participantRepository = (ParticipantRepository) getApplicationContext().getBean(
            "participantRepository");

    public String getTestDataFileName() {
        String fileName = "testdata/ParticipantRepositoryTest.xml";
        return fileName;
    }

    public void testSearchParticipantByExample() throws Exception {
        Participant participant = new Participant();
        participant.setFirstName("Dilbert");

        List<Participant> participants = participantRepository.search(participant);
        assertNotNull("Participant is null", participants);

    }

}
