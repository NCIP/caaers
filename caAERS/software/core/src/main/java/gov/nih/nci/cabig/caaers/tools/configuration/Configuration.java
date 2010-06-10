package gov.nih.nci.cabig.caaers.tools.configuration;

import gov.nih.nci.cabig.ctms.tools.configuration.*;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;

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
    private static final DefaultConfigurationProperties PROPERTIES = new DefaultConfigurationProperties(new ClassPathResource("details.properties", Configuration.class));
    public static final ConfigurationProperty<Boolean> SHOW_DEBUG_INFORMATION = PROPERTIES.add(new DefaultConfigurationProperty.Bool("showDebugInformation"));
    public static final ConfigurationProperty<String> PSC_BASE_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("pscBaseUrl"));
    public static final ConfigurationProperty<String> LABVIEWER_BASE_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("labViewerBaseUrl"));
    public static final ConfigurationProperty<String> ESB_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("esbUrl"));
    public static final ConfigurationProperty<String> CAEXCHANGE_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("caExchangeUrl"));
    public static final ConfigurationProperty<String> CAEXCHANGE_NONGRID_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("caExchangeNonGridUrl"));
    public static final ConfigurationProperty<String> CAEXCHANGE_NONGRID_USERNAME = PROPERTIES.add(new DefaultConfigurationProperty.Text("caExchangeNonGridUserName"));
    public static final ConfigurationProperty<String> CAEXCHANGE_NONGRID_PASSWORD = PROPERTIES.add(new DefaultConfigurationProperty.Text("caExchangeNonGridPassword"));
    public static final ConfigurationProperty<String> SMTP_ADDRESS = PROPERTIES.add(new DefaultConfigurationProperty.Text("smtpAddress"));
    public static final ConfigurationProperty<Integer> SMTP_PORT = PROPERTIES.add(new DefaultConfigurationProperty.Int("smtpPort"));
    public static final ConfigurationProperty<String> SMTP_USER = PROPERTIES.add(new DefaultConfigurationProperty.Text("smtpUser"));
    public static final ConfigurationProperty<String> SMTP_PASSWORD = PROPERTIES.add(new DefaultConfigurationProperty.Text("smtpPassword"));
    public static final ConfigurationProperty<Boolean> SMTP_SSL_ENABLED = PROPERTIES.add(new DefaultConfigurationProperty.Bool("smtpSSLEnabled"));
    public static final ConfigurationProperty<String> SYSTEM_FROM_EMAIL = PROPERTIES.add(new DefaultConfigurationProperty.Text("systemFromEmail"));
    public static final ConfigurationProperty<String> CAAERS_BASE_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("caaersBaseUrl"));
    public static final ConfigurationProperty<String> CAAERS_HELP_URL = PROPERTIES.add(new DefaultConfigurationProperty.Text("caaersBaseHelpUrl"));
    public static final ConfigurationProperty<Boolean> ENABLE_WORKFLOW = PROPERTIES.add(new DefaultConfigurationProperty.Bool("enableWorkflow"));
    public static final ConfigurationProperty<Boolean> UNIDENTIFIED_MODE = PROPERTIES.add(new DefaultConfigurationProperty.Bool("unidentifiedMode"));
    public static final ConfigurationProperty<String> AUTO_COMPLETER_DELAY = PROPERTIES.add(new DefaultConfigurationProperty.Text("autoCompleterDelay"));
    public static final ConfigurationProperty<String> AUTO_COMPLETER_CHARS = PROPERTIES.add(new DefaultConfigurationProperty.Text("autoCompleterChars"));
    public static final ConfigurationProperty<String> HTTP_SESSION_TIMEOUT_WARNING = PROPERTIES.add(new DefaultConfigurationProperty.Text("httpSessionWarning"));
    public static final ConfigurationProperty<String> HTTP_SESSION_TIMEOUT_WAIT = PROPERTIES.add(new DefaultConfigurationProperty.Text("httpSessionWarningWait"));
    public static final ConfigurationProperty<Integer> PA_SEARCH_LIMIT = PROPERTIES.add(new DefaultConfigurationProperty.Int("paLimit"));
    public static final ConfigurationProperty<Integer> PO_SEARCH_LIMIT = PROPERTIES.add(new DefaultConfigurationProperty.Int("poLimit"));
    public static final ConfigurationProperty<Boolean> SYNCHRONOUS_EVENTS = PROPERTIES.add(new DefaultConfigurationProperty.Bool("synchronousSpringEvents"));
    
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
		return StringUtils.equalsIgnoreCase("local", authenticationMode);
	}
	
	public boolean isAuthenticationModeWebSSO(){
		return StringUtils.equalsIgnoreCase("webSSO", authenticationMode);
	}
}
