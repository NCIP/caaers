package gov.nih.nci.cabig.caaers.web.ws.facade;

import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

/**
 * 
 * The facade layer to AdEERS.
 *
 * @author: Ramakrishna
 */
public interface AdeersIntegrationFacade {

    /**
     * Will synch caAERS organizations with AdEERS Organizations.
     * @return the correlation Id
     */
    String synchOrganizations();

    /**
     * Will synch caAERS Agents with AdEERS Agents.
     * @return the correlation Id
     */
    String synchAgents();

    /**
     * Will synch caAERS Devices with AdEERS Devices.
     * @return the correlation Id
     */
    String synchDevices();

    /**
     * Will synch caAERS PriorTherapyLOV with AdEERS PriorTherapyLOV.
     * @return the correlation Id
     */
    String synchPriorTherapyLOV();

    /**
     * Will synch caAERS PreExistingConditionLOV with AdEERS PreExistingConditionLOV.
     * @return the correlation Id
     */
    String synchPreExistingConditionLOV();
	
    /**
     * Will synch caAERS ASAEL with AdEERS ASAEL.
     * @return the correlation Id
     */
    String synchASAEL();
    
    /**
     * Will get Study details from AdEERS.
     * @return the correlation Id
     */
    
    String getStudy();
    
    /**
     * Will synch caAERS studies with AdEERS studies.
     * @return the correlation Id
     */
    String synchStudies();
    
    /**
     * Will search AdEERS studies.
     * @return list of caAERS domain studies
     */
    List<Study> searchStudies();
   
}
