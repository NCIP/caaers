package gov.nih.nci.cabig.caaers.rules.adapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.v1_0.RuleSet;

import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

/**
 * 
 * Adaptor class for converting the Rule in CaAERS format to 
 * JBoss Rules format.
 * 
 * Only this class knows about JBoss Rules and Caeers Generic Rule 
 * format
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class JBossXSLTRuleAdapter implements RuleAdapter {

	public Object adapt(RuleSet ruleSet) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(RuleSet.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.marshalDocument(ruleSet, "UTF-8", null, new FileOutputStream(
					"C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_General.xml"));
		} catch (JiBXException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		}

		System.setProperty("javax.xml.transform.TransformerFactory",
				"org.apache.xalan.processor.TransformerFactoryImpl");
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			Templates translet = transformerFactory
					.newTemplates(new StreamSource(
							"C:\\projects\\caAERS\\head\\trunk\\projects\\rules\\src\\main\\resources\\jboss_rules.xsl"));
			Transformer transformer = translet.newTransformer();
			transformer
					.transform(
							new StreamSource(
									"C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_General.xml"),
							new StreamResult(
									new FileOutputStream(
											"C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml")));
		} catch (TransformerConfigurationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (TransformerException e) {
			throw new RuleException(e.getMessage(), e);
		}
 
		Package package1 = new Package();
		try { 
			Reader ruleReader = new InputStreamReader(
					new FileInputStream ("C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml"));
			PackageBuilder packageBuilder = new PackageBuilder();
			packageBuilder.addPackageFromXml(ruleReader);
			package1 = packageBuilder.getPackage();
		} catch (DroolsParserException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuleException(e.getMessage(), e);
		}

		return package1;
	}
	 
}
