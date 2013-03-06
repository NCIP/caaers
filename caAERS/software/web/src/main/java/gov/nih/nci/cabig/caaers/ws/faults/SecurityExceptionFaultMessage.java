/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.faults;

import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;

import javax.xml.ws.WebFault;

@WebFault(name = "SecurityExceptionFault", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common")
public class SecurityExceptionFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SecurityExceptionFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public SecurityExceptionFaultMessage(String message, SecurityExceptionFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public SecurityExceptionFaultMessage(String message, SecurityExceptionFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: schema.integration.caaers.cabig.nci.nih.gov.common.SecurityExceptionFault
     */
    public SecurityExceptionFault getFaultInfo() {
        return faultInfo;
    }

}
