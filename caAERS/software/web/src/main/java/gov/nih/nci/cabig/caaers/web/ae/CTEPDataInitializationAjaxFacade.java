package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;

public class CTEPDataInitializationAjaxFacade extends AbstractAjaxFacade{
	private ProxyWebServiceFacade proxyWebServiceFacade;
	 private static Class<?>[] CONTROLLERS = {CTEPESYSDataImportController.class};

	public void setProxyWebServiceFacade(
			ProxyWebServiceFacade proxyWebServiceFacade) {
		this.proxyWebServiceFacade = proxyWebServiceFacade;
	}

	public int importCTEPData(boolean ctcaeChecked, boolean devicesChecked, boolean preExistingConditionsChecked, 
			boolean therapiesChecked, boolean agentDoseMeasureChecked, boolean lobChecked,
			boolean agentsChecked, boolean asaelChecked, boolean organizationsChecked) {
		
		if(devicesChecked) proxyWebServiceFacade.syncDevices();
		if(preExistingConditionsChecked) proxyWebServiceFacade.syncPreExistingConditionLOV();
		if(therapiesChecked) proxyWebServiceFacade.syncPriorTherapyLOV();
		if(agentsChecked) proxyWebServiceFacade.syncAgents();
		if(asaelChecked) proxyWebServiceFacade.syncASAEL();
		if(organizationsChecked) proxyWebServiceFacade.syncOrganizations();
		
		return 1;

	}

	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}
