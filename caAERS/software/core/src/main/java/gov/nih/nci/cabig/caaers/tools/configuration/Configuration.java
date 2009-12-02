package gov.nih.nci.cabig.caaers.tools.configuration;

import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperties;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;
import gov.nih.nci.cabig.ctms.tools.configuration.DatabaseBackedConfiguration;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Rhett Sutphin
 */
public class Configuration extends DatabaseBackedConfiguration implements InitializingBean{
	private String authenticationMode;
	public static Configuration LAST_LOADED_CONFIGURATION; 
    private static final ConfigurationProperties PROPERTIES = new ConfigurationProperties(new ClassPathResource("details.properties", Configuration.class));
    public static final ConfigurationProperty<Boolean> SHOW_DEBUG_INFORMATION = PROPERTIES.add(new ConfigurationProperty.Bool("showDebugInformation"));
    public static final ConfigurationProperty<String> PSC_BASE_URL = PROPERTIES.add(new ConfigurationProperty.Text("pscBaseUrl"));
    public static final ConfigurationProperty<String> LABVIEWER_BASE_URL = PROPERTIES.add(new ConfigurationProperty.Text("labViewerBaseUrl"));
    public static final ConfigurationProperty<String> ESB_URL = PROPERTIES.add(new ConfigurationProperty.Text("esbUrl"));
    public static final ConfigurationProperty<String> CAEXCHANGE_URL = PROPERTIES.add(new ConfigurationProperty.Text("caExchangeUrl"));
    public static final ConfigurationProperty<String> SMTP_ADDRESS = PROPERTIES.add(new ConfigurationProperty.Text("smtpAddress"));
    public static final ConfigurationProperty<Integer> SMTP_PORT = PROPERTIES.add(new ConfigurationProperty.Int("smtpPort"));
    public static final ConfigurationProperty<String> SMTP_USER = PROPERTIES.add(new ConfigurationProperty.Text("smtpUser"));
    public static final ConfigurationProperty<String> SMTP_PASSWORD = PROPERTIES.add(new ConfigurationProperty.Text("smtpPassword"));
    public static final ConfigurationProperty<Boolean> SMTP_SSL_ENABLED = PROPERTIES.add(new ConfigurationProperty.Bool("smtpSSLEnabled"));
    public static final ConfigurationProperty<String> SYSTEM_FROM_EMAIL = PROPERTIES.add(new ConfigurationProperty.Text("systemFromEmail"));
    public static final ConfigurationProperty<String> CAAERS_BASE_URL = PROPERTIES.add(new ConfigurationProperty.Text("caaersBaseUrl"));
    public static final ConfigurationProperty<String> CAAERS_HELP_URL = PROPERTIES.add(new ConfigurationProperty.Text("caaersBaseHelpUrl"));
    public static final ConfigurationProperty<Boolean> ENABLE_WORKFLOW = PROPERTIES.add(new ConfigurationProperty.Bool("enableWorkflow"));
    public static final ConfigurationProperty<Boolean> UNIDENTIFIED_MODE = PROPERTIES.add(new ConfigurationProperty.Bool("unidentifiedMode"));
    public static final ConfigurationProperty<String> AUTO_COMPLETER_DELAY = PROPERTIES.add(new ConfigurationProperty.Text("autoCompleterDelay"));
    public static final ConfigurationProperty<String> AUTO_COMPLETER_CHARS = PROPERTIES.add(new ConfigurationProperty.Text("autoCompleterChars"));
    public static final ConfigurationProperty<String> HTTP_SESSION_TIMEOUT_WARNING = PROPERTIES.add(new ConfigurationProperty.Text("httpSessionWarning"));
    public static final ConfigurationProperty<String> HTTP_SESSION_TIMEOUT_WAIT = PROPERTIES.add(new ConfigurationProperty.Text("httpSessionWarningWait"));

    public ConfigurationProperties getProperties() {
        return PROPERTIES;
    }
    
    public void afterPropertiesSet() throws Exception {
    	Configuration.LAST_LOADED_CONFIGURATION = this;
    }

	public String getAuthenticationMode() {
		return authenticationMode;
	}

	public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}
	public boolean isAuthenticationModeLocal(){
		if(true) return false;
		return StringUtils.equalsIgnoreCase("local", authenticationMode);
	}
	
	public boolean isAuthenticationModeWebSSO(){
		if(true) return true;
		return StringUtils.equalsIgnoreCase("webSSO", authenticationMode);
	}
}
