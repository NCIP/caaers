package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Exposes webservice API to manage ResearchStaff. The implementation here delegetes requests to
 * DefaultResearchStaffMigratorService.
 */

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService", serviceName="ResearchStaffMigratorService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ResearchStaffManagementWebService implements ResearchStaffMigratorService {

    private DefaultResearchStaffMigratorService impl;

    @WebMethod
    public CaaersServiceResponse saveResearchStaff(@WebParam(name="Staff") Staff staff) {
        return impl.saveResearchStaff(staff);
    }

    public DefaultResearchStaffMigratorService getImpl() {
        return impl;
    }

    public void setImpl(DefaultResearchStaffMigratorService impl) {
        this.impl = impl;
    }
}
