/**
 * 
 */
package gov.nih.nci.ctms.demo.comp;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.DataServiceConstants;
import gov.nih.nci.cagrid.data.client.DataServiceClient;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.jbi.messaging.DeliveryChannel;
import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOut;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
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
public class DataServiceProcessor implements GridServiceProcessor {

	private String queryXPath;

	public String getQueryXPath() {
		return queryXPath;
	}

	public void setQueryXPath(String queryXPath) {
		this.queryXPath = queryXPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ctms.demo.comp.MessageExchangeProcessor#process(javax.jbi.messaging.MessageExchange,
	 *      gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient)
	 */
	public void process(MessageExchange exchange, DeliveryChannel channel,
			ServiceSecurityClient client) throws Exception {
		

		if (!(client instanceof DataServiceClient)) {
			throw new IllegalArgumentException("client must be of type "
					+ DataServiceClient.class.getName() + ", got: "
					+ client.getClass().getName());
		}

		if (exchange.getRole() == MessageExchange.Role.PROVIDER) {

			if (exchange instanceof InOut == false) {
				throw new UnsupportedOperationException("Unsupported MEP: "
						+ exchange.getPattern());
			}
			if (exchange.getStatus() == ExchangeStatus.ACTIVE) {
				if (exchange.getMessage("in") != null) {

					NormalizedMessage in = exchange.getMessage("in");

					String inMsg = new SourceTransformer().contentToString(in);

					String xmlQuery = null;
					try {
						XPath xpathEngine = XPathFactory.newInstance()
								.newXPath();

						DocumentBuilder builder = DocumentBuilderFactory
								.newInstance().newDocumentBuilder();
						Document doc = builder.parse(new ByteArrayInputStream(
								inMsg.getBytes()));

						Node queryNode = (Node) xpathEngine.evaluate(
								getQueryXPath(), doc, XPathConstants.NODE);

						StringWriter w = new StringWriter();
						TransformerFactory transformerFactory = TransformerFactory
								.newInstance();
						Transformer transformer = transformerFactory
								.newTransformer();
						transformer.transform(new DOMSource(queryNode),
								new StreamResult(w));
						xmlQuery = w.getBuffer().toString();

					} catch (Exception ex) {
						throw new RuntimeException("Error parsing content: "
								+ ex.getMessage(), ex);
					}

					String xmlResult = null;
					try{
						xmlResult = runQuery((DataServiceClient) client,	xmlQuery);
					}catch(Exception ex){
						ex.printStackTrace();
						throw ex;
					}

					NormalizedMessage out = exchange.createMessage();
					out.setContent(new StreamSource(new ByteArrayInputStream(
							xmlResult.getBytes())));
					exchange.setMessage(out, "out");
					channel.send(exchange);

				} else if (exchange.getFault() != null) {
					exchange.setStatus(ExchangeStatus.DONE);
					channel.send(exchange);
				} else {
					throw new IllegalStateException(
							"Provider exchange is ACTIVE, but no in or fault is provided");
				}
			}

		} else if (exchange.getRole() == MessageExchange.Role.CONSUMER) {
			throw new UnsupportedOperationException(
					"This component does not send messages.");
		} else {
			throw new IllegalStateException("Unkown role: "
					+ exchange.getRole());
		}

	}

	private String runQuery(DataServiceClient client, String xmlQuery)
			throws Exception {

		String xmlResult = null;

		StringReader reader = new StringReader(xmlQuery);
		CQLQuery cql = null;
		try {
			cql = (CQLQuery) Utils.deserializeObject(reader, CQLQuery.class);

		} catch (Exception ex) {
			throw new RuntimeException("Error deserializing query: "
					+ ex.getMessage(), ex);
		}

		CQLQueryResults result = null;
		try {
			result = client.query(cql);
		} catch (Exception ex) {
			throw new RuntimeException("Error running query: "
					+ ex.getMessage(), ex);
		}

		try {
			StringWriter writer = new StringWriter();
			Utils.serializeObject(result,
					DataServiceConstants.CQL_RESULT_SET_QNAME, writer);

			xmlResult = writer.getBuffer().toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error serializing query results: "
					+ ex.getMessage(), ex);
		}

		return xmlResult;
	}

}
