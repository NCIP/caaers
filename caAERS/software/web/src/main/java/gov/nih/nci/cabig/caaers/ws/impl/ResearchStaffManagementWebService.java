package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.ws.ResearchStaffService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Exposes webservice API to manage ResearchStaff. The implementation here delegates requests to
 * DefaultResearchStaffMigratorService.
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.ResearchStaffService", serviceName="ResearchStaffService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ResearchStaffManagementWebService implements ResearchStaffService {

    private DefaultResearchStaffMigratorService impl;

    @WebMethod
    public CaaersServiceResponse saveResearchStaff(@WebParam(name="Staff", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff") Staff staff) {
        return impl.saveResearchStaff(staff);
    }

    public DefaultResearchStaffMigratorService getImpl() {
        return impl;
    }

    public void setImpl(DefaultResearchStaffMigratorService impl) {
        this.impl = impl;
    }
}
