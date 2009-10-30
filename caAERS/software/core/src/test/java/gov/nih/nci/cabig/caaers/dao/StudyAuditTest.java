package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cabig.ctms.audit.domain.AuditHistory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.dao.DataAccessException;

import java.util.Calendar;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * User: Mehul Gulati
 * Date: Oct 27, 2009
 */
public class StudyAuditTest extends DaoNoSecurityTestCase<StudyDao>  {

    StudyDao studyDao;
    public void setUp() throws Exception{
        super.setUp();
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
    }
    public void testAuditStudy() throws Exception {
        Integer id;
        {
                    System.out.println("Entering stuff");
                    Study newStudy = new LocalStudy();
                    newStudy.setShortTitle("Short Title Inserted");
                    newStudy.setLongTitle("Long Title Inserted");
                    newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
                    newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
                    newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
                    newStudy.setAdeersReporting(Boolean.TRUE);
                    studyDao.save(newStudy);
                    assertNotNull("No ID for newly saved study", newStudy.getId());
                    id = newStudy.getId();

        }
        interruptSession();
        {
            JdbcTemplate jdbcTemplate = (JdbcTemplate) getDeployedApplicationContext().getBean("jdbcTemplate");
            Integer objectId = (Integer) jdbcTemplate.execute(new StatementCallback() {
            public Object doInStatement(Statement st) throws SQLException,
                    DataAccessException {
                ResultSet rs = st.executeQuery("select object_id from audit_events where class_name = 'gov.nih.nci.cabig.caaers.domain.LocalStudy'");
                if (rs.next()) {
                  return rs.getInt(1);
                }
                return null;
            }
        });
        assertEquals(id, objectId);
        }
    }




}
