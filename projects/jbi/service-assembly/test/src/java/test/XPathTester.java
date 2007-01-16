package test;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XPathTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String fileName = "test/resources/eg2.xml";
//		String xpath = "/msg/content/CQLQuery";
		String xpath = "//*[local-name()='certificateAsString']/text()";

		XPath xpathEngine = XPathFactory.newInstance().newXPath();

		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = builder.parse(new File(fileName));

		Node node = (Node) xpathEngine
				.evaluate(xpath, doc, XPathConstants.NODE);

		StringWriter w = new StringWriter();
		TransformerFactory transformerFactory = TransformerFactory
		.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");
		transformer.transform(new DOMSource(node), new StreamResult(w));
		System.out.println(w.getBuffer());
	}

}
