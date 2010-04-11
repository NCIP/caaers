package gov.nih.nci.cabig.caaers.testdata.loader.participant;

import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
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



        //validate the xml
        boolean valid = XmlValidator.validateAgainstSchema(TestDataFileUtils.getContent(f), "classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd" , detailsBuffer);
        if(!valid) return false;

        boolean loadStatus = true;
        Participants participants = getParticipants(f);
        for(ParticipantType p : participants.getParticipant()){
            DomainObjectImportOutcome<Participant> outcome = service.processParticipant(p);
            loadStatus |= outcome.hasErrors();
            if(outcome.hasErrors()) detailsBuffer.append(outcome.toString()).append("\n");
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
