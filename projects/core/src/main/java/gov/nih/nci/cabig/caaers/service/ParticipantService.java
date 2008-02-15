package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

public interface ParticipantService {

    /**
     * Search using a sample. Populate a Participant object
     *
     * @param Participant object
     * @return List of Participant objects based on the sample participant object
     * @throws Runtime exception
     */
    public List<Participant> search(Participant participant) throws Exception;

    /**
     * Will return the body surface area
     *
     * @param height
     * @param weight
     * @return
     */
    public double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM);

    public List<Participant> searchParticipant(final ParticipantQuery query);    
	  
}