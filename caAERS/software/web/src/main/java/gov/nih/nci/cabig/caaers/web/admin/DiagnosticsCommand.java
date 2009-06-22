package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;


public class DiagnosticsCommand extends ConfigurationCommand{
	
	private boolean smtpTestResult;
	private String caExchangeHandShakeResult;
	private String labViewerUrl;
	private String pscUrl;
	private String caExchangeUrl;
	private String smtpHost;
	private String smtpPort;
	private Throwable smtpException;
	private String smtpError;
	
	public DiagnosticsCommand(Configuration configuration){
		super(configuration);
	}

	public boolean isSmtpTestResult() {
		return smtpTestResult;
	}

	public void setSmtpTestResult(boolean smtpTestResult) {
		this.smtpTestResult = smtpTestResult;
	}


	public String getCaExchangeHandShakeResult() {
		return caExchangeHandShakeResult;
	}

	public void setCaExchangeHandShakeResult(String caExchangeHandShakeResult) {
		this.caExchangeHandShakeResult = caExchangeHandShakeResult;
	}
	
	public String getLabViewerUrl() {
		labViewerUrl =  super.configuration.get(Configuration.LABVIEWER_BASE_URL);
		return labViewerUrl;
	}

	public String getCaExchangeUrl() {
		caExchangeUrl =  configuration.get(Configuration.CAEXCHANGE_URL);
		return caExchangeUrl;
	}
	
	public String getSmtpHost(){
		smtpHost =  configuration.get(Configuration.SMTP_ADDRESS);
		return smtpHost;
	}
	
	public String getSmtpPort(){
		smtpPort =  "" +configuration.get(Configuration.SMTP_PORT);
		return smtpPort;
	}

	public String getPscUrl() {
		pscUrl = configuration.get(Configuration.PSC_BASE_URL);
		return pscUrl;
	}

	public Throwable getSmtpException() {
		return smtpException;
	}

	public void setSmtpException(Throwable smtpException) {
		this.smtpException = smtpException;
	}

	public String getSmtpError() {
		if(smtpException != null){
			smtpError = smtpException.getMessage();
		}
		return smtpError;
	}
	
}
