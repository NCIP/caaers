/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.faults;

import gov.nih.nci.cabig.caaers.integration.schema.common.BaseFault;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersFaultInfo;
import gov.nih.nci.cabig.caaers.integration.schema.common.Fault;

import javax.xml.ws.WebFault;

@WebFault(name = "CaaersFault", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common")
public class CaaersFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CaaersFaultInfo faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public CaaersFault(String message, CaaersFaultInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public CaaersFault(String message, CaaersFaultInfo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: schema.integration.caaers.cabig.nci.nih.gov.common.CaaersFaultInfo
     */
    public CaaersFaultInfo getFaultInfo() {
        return faultInfo;
    }
    
    /**
     * 
     * @return
     *    adds a BaseFault to faultInfo
     */
    public void addCaaersFault(Fault fault) {
    	getFaultInfo().getFault().add(fault);
    }
    
    /**
     * 
     * @return
     *    clears faultInfo
     */
    public void clearCaaersFaults() {
    	getFaultInfo().getFault().clear();
    }

}
