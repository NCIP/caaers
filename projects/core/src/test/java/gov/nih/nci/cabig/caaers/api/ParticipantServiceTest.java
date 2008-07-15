package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ParticipantServiceTest extends CaaersDbTestCase{
	
	private ParticipantServiceResponse participantServiceResponse;
	private ParticipantService participantService = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.webservice.participant.Participants participants = null;
	private File xmlFile = null;
	boolean authorizationOnByDefault;
	private ParticipantDao participantDao;
	Participant updatedParticipant = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		authorizationOnByDefault = enableAuthorization(false);
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.participant");
		unmarshaller = jaxbContext.createUnmarshaller();
		participantService = (ParticipantService)getDeployedApplicationContext().getBean("participantServiceImpl");
		participantDao = (ParticipantDao)getDeployedApplicationContext().getBean("participantDao");
		
		
		
	}
	
	private void createParticipant(String fileLoc){
		try {
			xmlFile = getResources(fileLoc)[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = participantService.createParticipant(participantDto);
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	
	public void testUpdateParticipantForUpdateAttr(){
		
		try {
			
			createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");
			
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForUpdateAttr.xml")[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = participantService.updateParticipant(participantDto);
					assertEquals("0", participantServiceResponse.getResponsecode());
					List<Participant> matches = participantDao.getBySubnames(new String[] { "Richard Updated" });
					assertEquals(1, matches.size());
					updatedParticipant = matches.get(0);
					assertEquals("Herd Updated", updatedParticipant.getLastName());
					assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
					assertEquals("Leing Updated", updatedParticipant.getMiddleName());
					assertEquals(new DateValue(1,1,1960), updatedParticipant.getDateOfBirth());
					assertEquals("Not Reported", updatedParticipant.getGender());
					assertEquals("Black or African American", updatedParticipant.getRace());
					assertEquals("Non Hispanic or Latino", updatedParticipant.getEthnicity());
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} finally{
			if(updatedParticipant != null){
				participantDao.delete(updatedParticipant);
			}
		}
	}
	
	public void testUpdateParticipantForIdentifierAdd(){
		try {
			createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");
			
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForIdentifierAdd.xml")[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = new ParticipantServiceResponse();
					participantServiceResponse = participantService.updateParticipant(participantDto);
					assertEquals("0", participantServiceResponse.getResponsecode());
					List<Participant> matches = participantDao.getBySubnames(new String[] { "Richard Updated" });
					assertEquals(1, matches.size());
					updatedParticipant = matches.get(0);
					updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());
					
					assertEquals("Herd Updated", updatedParticipant.getLastName());
					assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
					assertEquals("Leing Updated", updatedParticipant.getMiddleName());
					assertEquals(new DateValue(1,1,1960), updatedParticipant.getDateOfBirth());
					assertEquals("Not Reported", updatedParticipant.getGender());
					assertEquals("Black or African American", updatedParticipant.getRace());
					assertEquals("Non Hispanic or Latino", updatedParticipant.getEthnicity());
					
					assertEquals(2, updatedParticipant.getIdentifiers().size());
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} finally{
			if(updatedParticipant != null){
				participantDao.delete(updatedParticipant);
			}
		}
	}
	
	
	public void testUpdateParticipantForIdentifierRemove(){
		try {
			
			createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForIdentifierAdd.xml");
			
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForIdentifierRemove.xml")[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = new ParticipantServiceResponse();
					participantServiceResponse = participantService.updateParticipant(participantDto);
					assertEquals("0", participantServiceResponse.getResponsecode());
					List<Participant> matches = participantDao.getBySubnames(new String[] { "Richard Updated" });
					assertEquals(1, matches.size());
					updatedParticipant = matches.get(0);
					updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());
					
					assertEquals("Herd Updated", updatedParticipant.getLastName());
					assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
					assertEquals("Leing Updated", updatedParticipant.getMiddleName());
					assertEquals(new DateValue(1,1,1960), updatedParticipant.getDateOfBirth());
					assertEquals("Not Reported", updatedParticipant.getGender());
					assertEquals("Black or African American", updatedParticipant.getRace());
					assertEquals("Non Hispanic or Latino", updatedParticipant.getEthnicity());
					
					assertEquals(1, updatedParticipant.getIdentifiers().size());
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} finally{
			if(updatedParticipant != null){
				participantDao.delete(updatedParticipant);
			}
		}
	}
	
	
	public void testUpdateParticipantForAssignmentsAdd(){
		try {
			createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/CreateParticipant.xml");
			
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForAssignmentsAdd.xml")[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = new ParticipantServiceResponse();
					participantServiceResponse = participantService.updateParticipant(participantDto);
					assertEquals("0", participantServiceResponse.getResponsecode());
					List<Participant> matches = participantDao.getBySubnames(new String[] { "Richard Updated" });
					assertEquals(1, matches.size());
					updatedParticipant = matches.get(0);
					updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());
					
					assertEquals("Herd Updated", updatedParticipant.getLastName());
					assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
					assertEquals("Leing Updated", updatedParticipant.getMiddleName());
					assertEquals(new DateValue(1,1,1960), updatedParticipant.getDateOfBirth());
					assertEquals("Not Reported", updatedParticipant.getGender());
					assertEquals("Black or African American", updatedParticipant.getRace());
					assertEquals("Non Hispanic or Latino", updatedParticipant.getEthnicity());
					
					assertEquals(2, updatedParticipant.getAssignments().size());
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} finally{
			if(updatedParticipant != null){
				participantDao.delete(updatedParticipant);
			}
		}
	}
	
	public void testUpdateParticipantForAssignmentsRemove(){
		try {
			
			createParticipant("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForAssignmentsAdd.xml");
			
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/participantdata/UpdateParticipantForAssignmentsRemove.xml")[0].getFile();
			participants = (Participants)unmarshaller.unmarshal(xmlFile);

			List<ParticipantType> participantList = participants.getParticipant();
			
			if(participantList!=null && !participantList.isEmpty()){
				for(ParticipantType  participantDto : participantList){
					participantServiceResponse = new ParticipantServiceResponse();
					participantServiceResponse = participantService.updateParticipant(participantDto);
					assertEquals("0", participantServiceResponse.getResponsecode());
					List<Participant> matches = participantDao.getBySubnames(new String[] { "Richard Updated" });
					assertEquals(1, matches.size());
					updatedParticipant = matches.get(0);
					updatedParticipant = participantDao.getParticipantById(updatedParticipant.getId());
					
					assertEquals("Herd Updated", updatedParticipant.getLastName());
					assertEquals("MaidenName Updated", updatedParticipant.getMaidenName());
					assertEquals("Leing Updated", updatedParticipant.getMiddleName());
					assertEquals(new DateValue(1,1,1960), updatedParticipant.getDateOfBirth());
					assertEquals("Not Reported", updatedParticipant.getGender());
					assertEquals("Black or African American", updatedParticipant.getRace());
					assertEquals("Non Hispanic or Latino", updatedParticipant.getEthnicity());
					
					assertEquals(1, updatedParticipant.getAssignments().size());
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} finally{
			if(updatedParticipant != null){
				participantDao.delete(updatedParticipant);
			}
		}
	}
	
	@Override
    protected void tearDown() throws Exception {
        super.tearDown();
        enableAuthorization(authorizationOnByDefault);
    }
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	private boolean enableAuthorization(boolean on) {
        AuthorizationSwitch sw = (AuthorizationSwitch) getDeployedApplicationContext().getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(on);
        return current;
    }
}
