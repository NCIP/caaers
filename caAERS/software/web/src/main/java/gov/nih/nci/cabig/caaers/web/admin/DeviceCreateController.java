/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/*
* @author Ion C. Olaru
*
* */

public class DeviceCreateController extends DeviceController {

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        super.formBackingObject(request);
        DeviceCommand c = new DeviceCommand();
        c.setDevice(new Device());
        return c;
    }

    @Override
	public FlowFactory<DeviceCommand> getFlowFactory() {
		return new FlowFactory<DeviceCommand>() {
			public Flow<DeviceCommand> createFlow(DeviceCommand cmd) {
				Flow<DeviceCommand> flow = new Flow<DeviceCommand>("Devices");
				flow.addTab(new DeviceTab<DeviceCommand>("Device", "Device", "admin/deviceCreateForm"));
				return flow;
			}
		};
	}

}
