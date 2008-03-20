package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;

import java.math.BigDecimal;
import java.util.List;

public class ParticipantServiceImpl  implements ParticipantService {

    ParticipantDao participantDao;

    StudySiteDao studySiteDao;

    /**
     * Search using a sample. Populate a Participant object
     * 
     * @param participant
     *                object
     * @return List of Participant objects based on the sample participant object
     * @throws Runtime
     *                 exception
     */
    public List<Participant> search(Participant participant) throws Exception {
        return participantDao.searchByExample(participant);
    }

    /**
     * Will calculate the body surface area using Mosteller formula
     */
    public double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM) {

        ParticipantHistory participantHistory = new ParticipantHistory();
        ParticipantHistory.Measure ht = new ParticipantHistory.Measure();
        ht.setQuantity(new BigDecimal(height));
        ht.setUnit(heightUOM);

        participantHistory.setHeight(ht);

        ParticipantHistory.Measure wt = new ParticipantHistory.Measure();
        wt.setQuantity(new BigDecimal(weight));
        wt.setUnit(weightUOM);

        participantHistory.setWeight(wt);

        return participantHistory.getBodySurfaceArea();
    }

    public List<Participant> searchParticipant(final ParticipantQuery query) {
        return participantDao.searchParticipant(query);
    }


    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }



    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }

}
