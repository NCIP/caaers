package gov.nih.nci.cabig.caaers.ws.impl;

import org.junit.Test;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.api.impl.SafetyReportServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BaseAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

public class SafetyReportManagementServiceImplTest extends AbstractTestCase {
	
	SafetyReportManagementServiceImpl impl = new SafetyReportManagementServiceImpl();
	
	public void setup() {
		impl.setSafetySvcImpl(new MockImpl());
	}
	
	@Test
	public void testSubmitSafetyReportError() {
		impl.setSafetySvcImpl(null);
		CaaersServiceResponse response = impl.submitSafetyReport(null);
		assertTrue(response.getServiceResponse().getWsError().get(0).getErrorDesc().endsWith("Error occured in " + Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.SYSTEM_NAME)));
	}
	
	private class MockImpl extends SafetyReportServiceImpl {

		@Override
		public CaaersServiceResponse initiateSafetyReportAction(
				BaseAdverseEventReport baseAadverseEventReport)
				throws Exception {
			if(baseAadverseEventReport == null) {
				throw new Exception("test");
			}
			CaaersServiceResponse response = Helper.createResponse();
			return response;
		}

		@Override
		public CaaersServiceResponse submitSafetyReport(
				AdverseEventReport adverseEventReport) throws Exception {
			if (adverseEventReport == null) {
				throw new Exception("test");
			}
			CaaersServiceResponse response = Helper.createResponse();
			return response;
		}

		@Override
		public CaaersServiceResponse saveSafetyReport(
				AdverseEventReport adverseEventReport) {
			CaaersServiceResponse response = Helper.createResponse();
			if(adverseEventReport == null) {
				Helper.populateError(response, "ACK-TEST", "This is a unit test error.");
				return response;
			}
			return response;
		}
		
	}

}
