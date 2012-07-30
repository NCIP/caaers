package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.acegisecurity.Authentication;
import org.acegisecurity.BadCredentialsException;

/**
 * @author: Biju Joseph
 */
public class GlobusCredentialAuthenticationRequestPopulatorTest extends WebTestCase {

    GlobusCredentialAuthenticationRequestPopulator populator;
    
    public void setUp() throws Exception{
        super.setUp();
        populator = new GlobusCredentialAuthenticationRequestPopulator();
        populator.setGridProxyParameterName("x");
    }

    public void testPopulate() throws Exception {
      try{
          populator.populate(request)  ;
      }catch (BadCredentialsException e){
            assertEquals("No grid proxy found in request.", e.getMessage());
      }

      request.setParameter("x" , "y");
        try{
            Authentication a = populator.populate(request)  ;
        }catch (BadCredentialsException e){
            e.printStackTrace();
            assertEquals("Error parsing grid proxy: No certificates loaded; nested exception is org.globus.gsi.GlobusCredentialException: No certificates loaded", e.getMessage());
        }

    }

    public void testGetGridProxyParameterName() throws Exception {
        populator.setGridProxyParameterName("x");
        assertEquals("x", populator.getGridProxyParameterName());
    }


}
