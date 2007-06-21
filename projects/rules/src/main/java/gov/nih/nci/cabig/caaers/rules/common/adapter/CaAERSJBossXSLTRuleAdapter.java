package gov.nih.nci.cabig.caaers.rules.common.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.utils.XsltTransformer;

public class CaAERSJBossXSLTRuleAdapter implements RuleAdapter{

public Object adapt(RuleSet ruleSet) {
		 
		/**
		    * Add adverseEventEvaluationResult here and remove it
		    * from everywhere else
		    * Since this is a hidden column, it should not be used 
		    * for authoring. It is used only at runtime. So it make
		    * sense to add to the condition here.
		    *  IMP: This is only required for caAERS project. 
		    */
		   List<Rule> rules = ruleSet.getRule();
		   for(Rule r: rules){
			   Column column_fixed = new Column();
			   column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
			   column_fixed.setIdentifier("adverseEventEvaluationResult");
			   r.getCondition().getColumn().add(column_fixed);
				
		   }
		   
		   /**
		    * End of block ------------------------------
		    */
		   
		List<String> imports = ruleSet.getImport();
		
		System.out.println("Size of imports:"+imports.size());
		for(String s: imports){
			System.out.println(s);
		}
		
		String xml1 = XMLUtil.marshal(ruleSet);
		System.out.println("Marshalled:" + xml1);

		XsltTransformer xsltTransformer = new XsltTransformer();
		
		String xml = ""; 
			
		try
		{
			xml = xsltTransformer.toXml(xml1, "jboss-rules-intermediate.xsl");
		} 
		catch (Exception e)
		{
			System.out.println("Exception while transforming to New Scheme: " + e.getMessage());
			e.printStackTrace();
		}
		
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
