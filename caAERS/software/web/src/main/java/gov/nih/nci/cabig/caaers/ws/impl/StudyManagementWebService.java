package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.StudyProcessor;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.Studies;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Will expose the webservice methods and delegates it to StudyProcessorImpl
 * @author Biju Joseph
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.StudyProcessor", serviceName="StudyService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class StudyManagementWebService implements StudyProcessor {
    
    private StudyProcessorImpl impl;

    /**
     * This operation will accept a Study which is a jaxb study and creates it.
     * This operation should allow for response.
     * Need to modify schema for Response
     * @param xmlStudies
     */
    @WebMethod
    public CaaersServiceResponse createStudy(@WebParam(name="Studies") Studies xmlStudies){
        return impl.createStudy(xmlStudies);
    }


    public StudyProcessorImpl getImpl() {
        return impl;
    }

    public void setImpl(StudyProcessorImpl impl) {
        this.impl = impl;
    }

    /**
     * This operation will accept a Study which is a jaxb Study and updates it.
     * This operation should allow for response.
     * Need to modify schema for Response
     * @param xmlStudies
     */
    @WebMethod
    public CaaersServiceResponse updateStudy(@WebParam(name="Studies") Studies xmlStudies){
        return impl.updateStudy(xmlStudies);
    }


}
