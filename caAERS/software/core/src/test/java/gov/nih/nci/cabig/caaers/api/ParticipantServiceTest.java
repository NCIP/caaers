package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.index.ParticipantIndexDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.index.ParticipantIndex;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ParticipantServiceTest extends CaaersDbNoSecurityTestCase {

    private CaaersServiceResponse caaersServiceResponse;
    private ParticipantServiceImpl participantService = null;
    private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private gov.nih.nci.cabig.caaers.integration.schema.participant.Participants participants = null;
    private File xmlFile = null;
    private ParticipantDao participantDao;
    private ParticipantIndexDao participantIndexDao;
	private StudyDao studyDao;
    Participant updatedParticipant = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.participant");
        unmarshaller = jaxbContext.createUnmarshaller();
        participantService = (ParticipantServiceImpl)getDeployedApplicationContext().getBean("participantServiceImpl");
        participantDao = (ParticipantDao) getDeployedApplicationContext().getBean("participantDao");
        studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");
        participantIndexDao = (ParticipantIndexDao) getDeployedApplicationContext().getBean("participantIndexDao");

    }

    private void createParticipant(String fileLoc) {
        try {
            xmlFile = getResources(fileLoc)[0].getFile();
            participants = (gov.nih.nci.cabig.caaers.integration.schema.participant.Participants)unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.createParticipant(participants);


        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        }
    }
    
    public void testCreateParticipantNewSST() {
    	Participant createdParticipant = null;
    	try {

            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipantNewSST.xml");

            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard"});
            assertEquals(1, matches.size());
            createdParticipant= matches.get(0);
            assertEquals("Herd", createdParticipant.getLastName());
            assertEquals("maidenName", createdParticipant.getMaidenName());
            assertEquals("Leing", createdParticipant.getMiddleName());
            assertEquals(new DateValue(1, 1, 2001), createdParticipant.getDateOfBirth());
            assertEquals("Male", createdParticipant.getGender());
            assertEquals("Asian", createdParticipant.getRace());
            assertEquals("Hispanic or Latino", createdParticipant.getEthnicity());
            
            Study study = studyDao.getById(-1);
            assertNotNull(study);
            List<StudyOrganization> studyOrganizations = study.getStudyOrganizations();
            assertNotNull(studyOrganizations);
            assertEquals(4, studyOrganizations.size());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (createdParticipant != null) {
                participantDao.delete(createdParticipant);
            }
        }
    }
    
    
    
    public void testCreateParticipant() {
    	Participant createdParticipant = null;
    	try {

            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard"});
          //  System.out.println("size is " + matches.size());
            assertEquals(1, matches.size());
            createdParticipant= matches.get(0);
            assertEquals("Herd", createdParticipant.getLastName());
            assertEquals("maidenName", createdParticipant.getMaidenName());
            assertEquals("Leing", createdParticipant.getMiddleName());
            assertEquals(new DateValue(1, 1, 2001), createdParticipant.getDateOfBirth());
            assertEquals("Male", createdParticipant.getGender());
            assertEquals("Asian", createdParticipant.getRace());
            assertEquals("Hispanic or Latino", createdParticipant.getEthnicity());
            //}
            //}

        } catch (Exception e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (createdParticipant != null) {
                participantDao.delete(createdParticipant);
            }
        }
    }
    
    public void testUpdateParticipantForUpdateAttr() {

        try {

            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForUpdateAttr.xml")[0].getFile();
            participants = (gov.nih.nci.cabig.caaers.integration.schema.participant.Participants)unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);

            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard Updated"});
            assertEquals(1, matches.size());
            updatedParticipant = matches.get(0);
            assertEquals("Herd Updated", updatedParticipant.getLastName());
            assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
            assertEquals("Leing Updated", updatedParticipant.getMiddleName());
            assertEquals(new DateValue(1, 1, 1960), updatedParticipant.getDateOfBirth());
            assertEquals("Not Reported", updatedParticipant.getGender());
            assertEquals("Black or African American", updatedParticipant.getRace());
            assertEquals("Not Hispanic or Latino", updatedParticipant.getEthnicity());
            //}
            //}

        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (updatedParticipant != null) {
                participantDao.delete(updatedParticipant);
            }
        }
    }
    
    
    public void testDeleteParticipant() {

        try {

            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForUpdateAttr.xml")[0].getFile();
            participants = (gov.nih.nci.cabig.caaers.integration.schema.participant.Participants)unmarshaller.unmarshal(xmlFile);
            
            // load the saved db participant
            
            Participant dbParticipant = participantDao.getBySubnames(new String[]{"Richard"}).get(0);
            assertNotNull("Participant with name Richard is not created",dbParticipant);
            

            String userName = "SYSTEM";
            ParticipantIndex participantIndex = new ParticipantIndex();
            participantIndex.setLoginId(userName);
            participantIndex.setParticipant(dbParticipant);
            participantIndex.setRoleCode(1);
            
            IndexEntry e1 = new IndexEntry(UserGroupType.system_administrator);
            e1.addEntityId(dbParticipant.getId());
            
            participantIndexDao.updateIndex(userName, UserGroupType.system_administrator.getCode(),e1 , null);
            List<IndexEntry> entries = participantIndexDao.queryAllIndexEntries("SYSTEM");
            assertEquals(1, entries.size());

            caaersServiceResponse = participantService.deleteParticipant(participants);
            
            // verify that participant is successfully deleted
            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard"});
            assertEquals(0, matches.size());
            
            // verify that index entries are successfully deleted along with participant
            entries = participantIndexDao.queryAllIndexEntries("SYSTEM");
            assertEquals(0, entries.size());

        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (updatedParticipant != null) {
                participantDao.delete(updatedParticipant);
            }
        }
    }

    public void testUpdateParticipantForIdentifierAdd() {
        try {
            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForIdentifierAdd.xml")[0].getFile();
            participants = (gov.nih.nci.cabig.caaers.integration.schema.participant.Participants)unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);
            
            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard Updated"});
            assertEquals(1, matches.size());
            updatedParticipant = matches.get(0);
            updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());

            assertEquals("Herd Updated", updatedParticipant.getLastName());
            assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
            assertEquals("Leing Updated", updatedParticipant.getMiddleName());
            assertEquals(new DateValue(1, 1, 1960), updatedParticipant.getDateOfBirth());
            assertEquals("Not Reported", updatedParticipant.getGender());
            assertEquals("Black or African American", updatedParticipant.getRace());
            assertEquals("Not Hispanic or Latino", updatedParticipant.getEthnicity());

            assertEquals(2, updatedParticipant.getIdentifiers().size());
//				}
//			}

        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (updatedParticipant != null) {
                participantDao.delete(updatedParticipant);
            }
        }
    }


    public void testUpdateParticipantForAssignmentsAdd() {
        try {
            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForAssignmentsAdd.xml")[0].getFile();
            participants = (gov.nih.nci.cabig.caaers.integration.schema.participant.Participants)unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);

            assertEquals("0", caaersServiceResponse.getServiceResponse().getResponsecode());
            List<Participant> matches = participantDao.getBySubnames(new String[]{"Richard Updated"});
            assertEquals(1, matches.size());
            updatedParticipant = matches.get(0);
            updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());

            assertEquals("Herd Updated", updatedParticipant.getLastName());
            assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
            assertEquals("Leing Updated", updatedParticipant.getMiddleName());
            assertEquals(new DateValue(1, 1, 1960), updatedParticipant.getDateOfBirth());
            assertEquals("Not Reported", updatedParticipant.getGender());
            assertEquals("Black or African American", updatedParticipant.getRace());
            assertEquals("Not Hispanic or Latino", updatedParticipant.getEthnicity());

            assertEquals(2, updatedParticipant.getAssignments().size());
//				}
//			}

        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } finally {
            if (updatedParticipant != null) {
                participantDao.delete(updatedParticipant);
            }
        }
    }

    private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }

}
