package gov.nih.nci.cabig.caaers.rules.common.adapter;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

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
		String xml = XMLUtil.marshal(ruleSet);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

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
							new StreamSource(new StringReader(xml)),
							new StreamResult(
									new FileOutputStream(
											"C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml")));
			
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
		
		Package package1 = new Package();
		try { 
			Reader ruleReader = new InputStreamReader(
					new FileInputStream ("C:\\Docume~1\\SUJITH\\Desktop\\RuleSet_Drools.xml"));
			ruleReader = new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray()));
			
			PackageBuilder packageBuilder = new PackageBuilder(conf);
			packageBuilder.addPackageFromXml(ruleReader);
			package1 = packageBuilder.getPackage();
		} catch (IOException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (DroolsParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return package1;
	}
}