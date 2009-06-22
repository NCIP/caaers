/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.jbi.messaging.NormalizedMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class XPathBasicAuthenticationTransformer implements
		BasicAuthenticationTransformer {

	private String usernameXPath;

	private String passwordXPath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ctms.demo.trans.BasicAuthenticationTransformer#transform(javax.jbi.messaging.NormalizedMessage)
	 */
	public BasicAuthenticationCredential transform(NormalizedMessage inMsg)
			throws InvalidCredentialException {

		String inXml = null;
		try {
			inXml = new SourceTransformer().contentToString(inMsg);
			
		} catch (Exception ex) {
			throw new RuntimeException("Error getting message content: "
					+ ex.getMessage(), ex);
		}

		return transform(inXml);
	}

	public BasicAuthenticationCredential transform(String xml)
			throws InvalidCredentialException {
		BasicAuthenticationCredential bac = new BasicAuthenticationCredential();


		String username = null;
		String password = null;

		try {
			XPath xpathEngine = XPathFactory.newInstance().newXPath();

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));

			Node usernameNode = (Node) xpathEngine.evaluate(getUsernameXPath(),
					doc, XPathConstants.NODE);
			Node passwordNode = (Node) xpathEngine.evaluate(getPasswordXPath(),
					doc, XPathConstants.NODE);

			username = toString(usernameNode);
			password = toString(passwordNode);

		} catch (Exception ex) {
			throw new RuntimeException("Error parsing content: "
					+ ex.getMessage(), ex);
		}
		if (username == null) {
			throw new InvalidCredentialException("Couldn't find username.");
		}
		if (password == null) {
			throw new InvalidCredentialException("Couldn't find password.");
		}

		bac.setUserId(username);
		bac.setPassword(password);
		return bac;
	}

	private String toString(Node node) throws Exception {
		StringWriter w = new StringWriter();
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty("omit-xml-declaration", "yes");
		transformer.transform(new DOMSource(node), new StreamResult(w));
		return w.getBuffer().toString();
	}

	public String getPasswordXPath() {
		return passwordXPath;
	}

	public void setPasswordXPath(String passwordXPath) {
		this.passwordXPath = passwordXPath;
	}

	public String getUsernameXPath() {
		return usernameXPath;
	}

	public void setUsernameXPath(String usernameXPath) {
		this.usernameXPath = usernameXPath;
	}

}
