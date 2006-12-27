/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import java.io.ByteArrayInputStream;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;

import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.transform.stream.StreamSource;

import org.apache.servicemix.components.util.TransformComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class GridProxyTrans extends TransformComponentSupport {

	private GridProxyTransformer gridProxyTransformer;

	private String basicAuthenticationCredentialProperty;

	private String gridProxyProperty;

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
		
		BasicAuthenticationCredential bac = null;
		try {
			bac = (BasicAuthenticationCredential) inMsg
					.getProperty(getBasicAuthenticationCredentialProperty());
		} catch (ClassCastException ex) {
			throw new RuntimeException(
					getBasicAuthenticationCredentialProperty()
							+ " of wrong type: "
							+ inMsg.getProperty(
									getBasicAuthenticationCredentialProperty())
									.getClass().getName());
		} catch (Exception ex) {
			throw new RuntimeException("Error retrieving " + getBasicAuthenticationCredentialProperty());
		}
		
		if(bac == null){
			throw new RuntimeException("Couldn't find " + getBasicAuthenticationCredentialProperty());
		}
		
		GlobusCredential proxy = getGridProxyTransformer().transform(bac);
		outMsg.setProperty(getGridProxyProperty(), proxy);
		String xml = new SourceTransformer().contentToString(inMsg);
		outMsg.setContent(new StreamSource(new ByteArrayInputStream(xml
				.getBytes())));
		

		return true;
	}

	public String getBasicAuthenticationCredentialProperty() {
		return basicAuthenticationCredentialProperty;
	}

	public void setBasicAuthenticationCredentialProperty(
			String basicAuthenticationCredentialProperty) {
		this.basicAuthenticationCredentialProperty = basicAuthenticationCredentialProperty;
	}

	public String getGridProxyProperty() {
		return gridProxyProperty;
	}

	public void setGridProxyProperty(String gridProxyProperty) {
		this.gridProxyProperty = gridProxyProperty;
	}

	public GridProxyTransformer getGridProxyTransformer() {
		return gridProxyTransformer;
	}

	public void setGridProxyTransformer(
			GridProxyTransformer gridProxyTransformer) {
		this.gridProxyTransformer = gridProxyTransformer;
	}

}
