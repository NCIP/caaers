package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Identifier;
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
     * Will sync caAERS organizations with AdEERS Organizations.
     * @return the correlation Id
     */
    String syncOrganizations();

    /**
     * Will sync caAERS Agents with AdEERS Agents.
     * @return the correlation Id
     */
    String syncAgents();

    /**
     * Will sync caAERS Devices with AdEERS Devices.
     * @return the correlation Id
     */
    String syncDevices();

    /**
     * Will sync caAERS PriorTherapyLOV with AdEERS PriorTherapyLOV.
     * @return the correlation Id
     */
    String syncPriorTherapyLOV();

    /**
     * Will sync caAERS PreExistingConditionLOV with AdEERS PreExistingConditionLOV.
     * @return the correlation Id
     */
    String syncPreExistingConditionLOV();
	
    /**
     * Will sync caAERS ASAEL with AdEERS ASAEL.
     * @return the correlation Id
     */
    String syncASAEL();

    /**
     * Will sync caAERS studies with AdEERS studies.
     * @return the correlation Id
     */
    String syncStudies();


    /**
     * Will Import a new study into caAERS from Adeers.
     * @param sponsorIdentifierValue
     * @return
     */
    String importStudy(String sponsorIdentifierValue);

    /**
     * Will update the study defition with the study definition from Adeers
     * @param id
     * @param force
     * @return
     */
    String updateStudy(Integer id, boolean  force);

    /**
     * Search AdEERS studies by ShortTitle or Identifier value
     * @return list of caAERS domain studies
     */
    List<Study> searchStudies(String searchText);
    
    /**
     * Will sync caAERS Labs with AdEERS Labs.
     * @return the correlation Id
     */
    String syncLabs();

   
}
