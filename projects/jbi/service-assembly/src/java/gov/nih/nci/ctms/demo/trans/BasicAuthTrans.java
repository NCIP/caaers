/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import java.io.ByteArrayInputStream;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;

import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.servicemix.components.util.TransformComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class BasicAuthTrans extends TransformComponentSupport {

	private BasicAuthenticationTransformer basicAuthenticationTransformer;

	/**
	 * The name of the property that should be set to the
	 * BasicAuthenticationCredential object.
	 */
	private String basicAuthenticationCredentialProperty;

	public BasicAuthenticationTransformer getBasicAuthenticationTransformer() {
		return basicAuthenticationTransformer;
	}

	public void setBasicAuthenticationTransformer(
			BasicAuthenticationTransformer basicAuthenticationTransformer) {
		this.basicAuthenticationTransformer = basicAuthenticationTransformer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.servicemix.components.util.TransformComponentSupport#transform(javax.jbi.messaging.MessageExchange,
	 *      javax.jbi.messaging.NormalizedMessage,
	 *      javax.jbi.messaging.NormalizedMessage)
	 */
	@Override
	protected boolean transform(MessageExchange exchange,
			NormalizedMessage inMsg, NormalizedMessage outMsg) throws Exception {

		try {
			
			BasicAuthenticationCredential bac = null;
			String xml = new SourceTransformer().contentToString(inMsg);
			BasicAuthenticationTransformer trans = getBasicAuthenticationTransformer();
			if(trans instanceof XPathBasicAuthenticationTransformer){
				bac = ((XPathBasicAuthenticationTransformer)trans).transform(xml);
			}else{
				bac = getBasicAuthenticationTransformer().transform(inMsg);
			}
			outMsg.setProperty(getBasicAuthenticationCredentialProperty(), bac);
			outMsg.setContent(new StreamSource(new ByteArrayInputStream(xml
					.getBytes())));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return true;
	}

	public String getBasicAuthenticationCredentialProperty() {
		return basicAuthenticationCredentialProperty;
	}

	public void setBasicAuthenticationCredentialProperty(
			String basicAuthenticationCredentialProperty) {
		this.basicAuthenticationCredentialProperty = basicAuthenticationCredentialProperty;
	}

}
