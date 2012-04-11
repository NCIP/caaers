
package gov.nih.nci.cabig.caaers.ws.faults;

import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;

import javax.xml.ws.WebFault;

@WebFault(name = "SecurityExceptionFault", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov")
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
