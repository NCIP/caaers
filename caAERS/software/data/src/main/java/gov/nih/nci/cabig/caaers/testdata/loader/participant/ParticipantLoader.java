package gov.nih.nci.cabig.caaers.testdata.loader.participant;

import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
import gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class will load subjects
 * Used to load test data.
 * @author: Biju Joseph
 */
public class ParticipantLoader extends DataLoader {

    ParticipantServiceImpl service;

    public ParticipantLoader(ApplicationContext appContext) throws Exception {
        this(appContext, TestDataFileUtils.getSubjectTestDataFolder().getPath());
    }

    public ParticipantLoader(ApplicationContext appContext, String loc) throws Exception {
        super(appContext, loc, "gov.nih.nci.cabig.caaers.webservice.participant");
        service = (ParticipantServiceImpl) appContext.getBean("participantServiceImpl");
    }

    @Override
    public boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception {

        boolean loadStatus = true;
        CaaersServiceResponse response = service.createParticipant(getParticipants(f));
        for(String wsError : response.getResponse().getMessage()){
            loadStatus=false;
            detailsBuffer.append(wsError).append("\n");
        }

        return loadStatus;
    }



	/**
	 * Will read the participant fro file
	 * @return
	 * @throws Exception
	 */
	public Participants getParticipants(File f) throws Exception{

		return (Participants) unmarshaller.unmarshal(new FileInputStream(f));

	}
}
