package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import javax.servlet.http.HttpServletRequest;

/*
* @author Ion C. Olaru
* 
* */

public class DeviceEditController extends DeviceController {

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        super.formBackingObject(request);
        Device device = null;
        device = deviceRepository.getById(Integer.parseInt(request.getParameter("deviceID")));
        DeviceCommand c = new DeviceCommand();
        c.setDevice(device);
        return c;
    }


    @Override
	public FlowFactory<DeviceCommand> getFlowFactory() {
		return new FlowFactory<DeviceCommand>() {
			public Flow<DeviceCommand> createFlow(DeviceCommand cmd) {
				Flow<DeviceCommand> flow = new Flow<DeviceCommand>("Devices");
				flow.addTab(new DeviceTab<DeviceCommand>("Device", "Device", "admin/deviceEditForm"));
				return flow;
			}
		};
	}

}