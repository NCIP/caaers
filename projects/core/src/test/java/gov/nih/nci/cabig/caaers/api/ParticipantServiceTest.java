package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ParticipantServiceTest extends CaaersDbTestCase {

    private CaaersServiceResponse caaersServiceResponse;
    private ParticipantService participantService = null;
    private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private gov.nih.nci.cabig.caaers.webservice.participant.Participants participants = null;
    private File xmlFile = null;
    private ParticipantDao participantDao;
    Participant updatedParticipant = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.participant");
        unmarshaller = jaxbContext.createUnmarshaller();
        participantService = (ParticipantService) getDeployedApplicationContext().getBean("participantServiceImpl");
        participantDao = (ParticipantDao) getDeployedApplicationContext().getBean("participantDao");


    }

    private void createParticipant(String fileLoc) {
        try {
            xmlFile = getResources(fileLoc)[0].getFile();
            participants = (Participants) unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.createParticipant(participants);


        } catch (IOException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("Error running test: " + e.getMessage());
        }
    }
    
    public void testCreateParticipant() {
    	Participant createdParticipant = null;
    	try {

            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            SecurityTestUtils.switchToSuperuser();

            assertEquals("0", caaersServiceResponse.getResponse().getResponsecode());
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
            participants = (Participants) unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);
            SecurityTestUtils.switchToSuperuser();

            assertEquals("0", caaersServiceResponse.getResponse().getResponsecode());
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

    public void testUpdateParticipantForIdentifierAdd() {
        try {
            createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");

            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForIdentifierAdd.xml")[0].getFile();
            participants = (Participants) unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);
            SecurityTestUtils.switchToSuperuser();

            assertEquals("0", caaersServiceResponse.getResponse().getResponsecode());
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
            participants = (Participants) unmarshaller.unmarshal(xmlFile);

            caaersServiceResponse = participantService.updateParticipant(participants);
            SecurityTestUtils.switchToSuperuser();

            assertEquals("0", caaersServiceResponse.getResponse().getResponsecode());
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
