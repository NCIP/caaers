package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class SystemRulesDeployer {
	private static final Log log = LogFactory.getLog(SystemRulesDeployer.class);
	 

	public SystemRulesDeployer(RuleDeploymentService ruleDeploymentService) {
		if(log.isInfoEnabled()) log.info("Begining system rule loading......");
		try{
			
			//1. create the base pattern.
			String pattern = "classpath*:" + 
			    SystemRulesDeployer.class.getPackage().getName().replace(".", "/") + 
			    "/*.xml";
			
			//2. Load the rule files, that are to be loaded in repository
			// and load them only if they are not already loaded.
			for(Resource resource : getResources(pattern)){
				if(log.isDebugEnabled()) log.debug("Loading rule file :" + resource.getURL().toString());
				try {
					String ruleXml = getFileContext(resource.getInputStream());
					org.drools.rule.Package rulePackage = XMLUtil.unmarshalToPackage(ruleXml);
					String ruleBindUri = rulePackage.getName();
					
					if(log.isDebugEnabled()) log.debug("Registering at bindUri : " + ruleBindUri + "\r\n\r\n" + ruleXml);
					ruleDeploymentService.registerRulePackage(ruleBindUri, rulePackage);
					if(log.isDebugEnabled()) log.debug("Sucessfully deployed rule at bindUri :" + ruleBindUri);
				} catch (RuntimeException e) {
					log.debug("It seems the system rule is already available, so ignoring", e);
				}	
				
			}
			
		}catch(Exception e){
			log.warn("Error while loading system rules :", e);
		}
		if(log.isInfoEnabled()) log.debug("Finished system rule loading......");
	}
	
	public static Resource[] getResources(String pattern) throws IOException {
	    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (log.isDebugEnabled()) log.debug("Looking for resources matching " + pattern);
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	public static String getFileContext(InputStream in) throws Exception{
		BufferedReader ds = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuffer xml = new StringBuffer();
		while((line = ds.readLine()) != null){
			xml.append(line);
		}
		return xml.toString();
	}
	
	
}
