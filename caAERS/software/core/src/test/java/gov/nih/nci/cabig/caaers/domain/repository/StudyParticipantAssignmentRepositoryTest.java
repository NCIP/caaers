package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import org.dbunit.operation.DatabaseOperation;

import java.util.List;

/**
 * @author Ion C. Olaru
 *
 * */
public class StudyParticipantAssignmentRepositoryTest extends CaaersDbTestCase {
    StudyParticipantAssignmentRepository r;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        r = (StudyParticipantAssignmentRepository)getDeployedApplicationContext().getBean("studyParticipantAssignmentRepository");
    }

    public void testGetAllAssignments() {
        List l = r.getAllAssignments();
        assertNotNull(l);
        assertEquals(2, l.size());
    }

}
