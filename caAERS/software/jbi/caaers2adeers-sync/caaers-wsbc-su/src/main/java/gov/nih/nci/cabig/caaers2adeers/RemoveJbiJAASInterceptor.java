/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * @author kruttikagarwal
 * This class will remove JbiJAASInterceptor which is by default actvated in Servicemix to handle
 * ws-security headers, which means that we need to update the user-passowrd.properties file in conf/servicemix to add username password.
 * Instead we want to rely on the WSS4jInInterceptor.
 */
public class RemoveJbiJAASInterceptor extends AbstractPhaseInterceptor<Message> {

	public RemoveJbiJAASInterceptor() {
		super(Phase.READ);
	}

	public void handleMessage(Message message) throws Fault {
		for (Interceptor interceptor : message.getInterceptorChain()) {
			if (interceptor
					.getClass()
					.getName()
					.equals("org.apache.servicemix.cxfbc.interceptors.JbiJAASInterceptor")) {
				message.getInterceptorChain().remove(interceptor);
			}
		}
	}

}
