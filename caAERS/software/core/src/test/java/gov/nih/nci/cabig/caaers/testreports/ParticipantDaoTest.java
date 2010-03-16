package gov.nih.nci.cabig.caaers.testreports;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author Biju Joseph
 * @author Srini
 */
public class ParticipantDaoTest extends DaoTestCase {
   /*
        NOTE : BJ - removed the testForReport() method, as this is not the primary responsibility of the DAO
    */
    private ParticipantDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = (ParticipantDao) getApplicationContext().getBean("participantDao");
    }
    
    public void testGetAll(){
        List<Participant> participantList = dao.getAll();
        assertNotNull(participantList);
        assertEquals(3, participantList.size());
        
    }
    
}
