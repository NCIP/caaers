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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class JBossXSLTRuleAdapter implements RuleAdapter {
	private static final Log log = LogFactory.getLog(JBossXSLTRuleAdapter.class);
	
	public Package adapt(RuleSet ruleSet){
		if(true){
			throw new RuntimeException("Deprecated, dont use JBossXSLTRuleAdapter, instead use CaAERSJBossXSLTRuleAdapter");
		}
		return null;
	}
	
}