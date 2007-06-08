package gov.nih.nci.cabig.caaers.rules.common.adapter;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.rule.Package;
/*import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
*/
/**
 * Adaptor class for converting the Rule in CaAERS format to JBoss Rules format.
 * 
 * This class serializes the RuleSet information passed in to XML format.
 * This XML is passed through an XSLT pipeline which knows how to convert the XML
 * to another XML format which is applicable to JBoss Rules.
 * 
 * The JBoss Rule XML is then loaded and compiled. The net output being the Package.
 * 
 * The Package object is then returned.
 * 
 * Only this class knows about JBoss Rules and Caeers Generic Rule format
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class JBossXSLTRuleAdapter implements RuleAdapter {

	public Object adapt(RuleSet ruleSet) {
		List<String> imports = ruleSet.getImport();
		
		System.out.println("Size of imports:"+imports.size());
		for(String s: imports){
			System.out.println(s);
		}
		
		String xml = XMLUtil.marshal(ruleSet);
		System.out.println("Marshalled:"+xml);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		System.setProperty("javax.xml.transform.TransformerFactory",
				"org.apache.xalan.processor.TransformerFactoryImpl");
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			//Templates translet = transformerFactory.newTemplates(new StreamSource("C:\\projects\\caAERS\\head\\trunk\\projects\\rules\\src\\main\\resources\\jboss_rules.xsl"));
			
			//Templates translet = transformerFactory.newTemplates(new StreamSource( Thread.currentThread().getContextClassLoader().getResourceAsStream("jboss_rules.xsl")));
			
			

			Templates translet = transformerFactory.newTemplates(new StreamSource( Thread.currentThread().getContextClassLoader().getResourceAsStream("new_jobss_rules.xsl")));

			
			Transformer transformer = translet.newTransformer();
			
			//For Testing Purpose
			if(Boolean.TRUE.equals(System.getProperty("caaers.rules.debug"))) {
				transformer
						.transform(
								new StreamSource(new StringReader(xml)),
								new StreamResult(
										new FileOutputStream(
												"C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml")));
			}
			
			transformer.transform(new StreamSource(new StringReader(xml)),
					new StreamResult(outputStream));			
			
		} catch (TransformerConfigurationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (TransformerException e) {
			throw new RuleException(e.getMessage(), e);
		}
 
		
		PackageBuilderConfiguration conf = new PackageBuilderConfiguration();
		conf.setCompiler( PackageBuilderConfiguration.JANINO );
		//conf.setJavaLanguageLevel( "1.4" );
		
		Package droolsPackage = new Package();
		try {
			Reader ruleReader = null;
			
			//For Testing Purpose
			if(Boolean.TRUE.equals(System.getProperty("caaers.rules.debug"))) {
				ruleReader = new InputStreamReader(
					new FileInputStream ("C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml"));
			}
			ruleReader = new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray()));
			
			outputStream.writeTo(System.out);
			//System.out.println(outputStream.toByteArray());
			PackageBuilder packageBuilder = new PackageBuilder(conf);
			packageBuilder.addPackageFromXml(ruleReader);
			droolsPackage = packageBuilder.getPackage();
		} catch (IOException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (DroolsParserException e) {
			throw new RuleException(e.getMessage(), e);
		}

		return droolsPackage;
	}
}