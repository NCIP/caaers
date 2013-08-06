/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

public class AdeersResponseToE2BAckRouteBuilder {

	private static String responseXSLBase = "xslt/e2b/response/";

	private String outputEDIDir;	
		
	private Caaers2AdeersRouteBuilder routeBuilder;

	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;
        
        routeBuilder.from("direct:routeAdEERSResponseSink")
	        .process(track(ADEERS_REPORT_SUBMISSION_RESPONSE))	        
			.to(routeBuilder.getFileTracker().fileURI(ADEERS_REPORT_SUBMISSION_RESPONSE))
			.processRef("adeersResponseProcessor")
			.to("xslt:" + responseXSLBase + "adeersResponse2ACK.xsl")			
			.process(track(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))
			.to(routeBuilder.getFileTracker().fileURI(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))			
			.to("file://"+outputEDIDir);
	}	

	public String getOutputEDIDir() {
		return outputEDIDir;
	}


	public void setOutputEDIDir(String outputEDIDir) {
		this.outputEDIDir = outputEDIDir;
	}
		
}
