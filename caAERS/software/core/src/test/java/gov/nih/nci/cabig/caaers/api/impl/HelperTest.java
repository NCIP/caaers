/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class HelperTest extends TestCase {

    public void testCreateResponse() throws Exception {
         CaaersServiceResponse response = Helper.createResponse();
        assertNotNull(response.getServiceResponse());
        assertNotNull(response.getServiceResponse().getEntityProcessingOutcomes());
        assertNotNull(response.getServiceResponse().getWsError());
        assertTrue(response.getServiceResponse().getWsError().isEmpty());
        assertTrue(response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome().isEmpty());
        assertEquals(Status.PROCESSED, response.getServiceResponse().getStatus());
    }

    public void testCreateProcessingOutcomeType() throws Exception {
        EntityProcessingOutcomeType t = Helper.createProcessingOutcomeType(false, "k","j","l","n", null);
        assertEquals("k", t.getKlassName());
        assertEquals("j", t.getBusinessIdentifier());
        assertEquals("l", t.getDataBaseId());
        assertEquals("n", t.getCorrelationId());
        assertTrue(t.getMessage().isEmpty());
        
    }

    public void testPopulateError() throws Exception {
        CaaersServiceResponse response = Helper.createResponse();
        assertTrue(response.getServiceResponse().getWsError().isEmpty());
        Helper.populateError(response, "c", "d");
        assertEquals(1, response.getServiceResponse().getWsError().size());
        assertEquals("c" , response.getServiceResponse().getWsError().get(0).getErrorCode());
        assertEquals("d" , response.getServiceResponse().getWsError().get(0).getErrorDesc());
    }

    public void testPopulateMessage() throws Exception {
        CaaersServiceResponse response = Helper.createResponse();
        assertNull(response.getServiceResponse().getMessage());
        Helper.populateMessage(response, "x");
        assertEquals("x",response.getServiceResponse().getMessage());
        assertTrue(response.getServiceResponse().getWsError().isEmpty());
        assertTrue(response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome().isEmpty());
        assertEquals(Status.PROCESSED, response.getServiceResponse().getStatus());
    }


    public void testPopulateProcessingOutcome() throws Exception {
        ProcessingOutcome outcome = Helper.createOutcome(String.class, "j", false, "done", "complete");
        CaaersServiceResponse response = Helper.createResponse();
        Helper.populateProcessingOutcome(response, outcome);
        assertEquals(1, response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome().size());
        EntityProcessingOutcomeType entityProcessingOutcomeType = response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome().get(0);
        assertEquals("j", entityProcessingOutcomeType.getBusinessIdentifier());
    }


}
