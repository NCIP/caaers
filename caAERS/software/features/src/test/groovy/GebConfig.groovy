





import org.openqa.selenium.htmlunit.HtmlUnitDriver

driver = { new HtmlUnitDriver(true)}
//baseUrl = "https://localhost:8443/caaers/"
baseUrl = "https://dev.semanticbits.com/caaers/"
reportsDir = "target/geb-reports"
reportOnTestFailureOnly = true

// when system property 'geb.env'
environments {
	'dev' {
		baseUrl = "https://dev.semanticbits.com/caaers/"
	}
	'qa' {
		baseUrl = "https://oracle.qa.semanticbits.com/caaers/"
	}
	'firefox' {
		driver = "firefox"
	}
	'ie' {
		driver = "ie"
	}
	'dev-ie' {
		baseUrl = "https://dev.semanticbits.com/caaers/"
		driver = "ie"
	}
	'dev-firefox' {
		baseUrl = "https://dev.semanticbits.com/caaers/"
		driver = "firefox"
	}
	'qa-ie' {
		baseUrl = "https://oracle.qa.semanticbits.com/caaers/"
		driver = "ie"
	}
	'qa-firefox' {
		baseUrl = "https://oracle.qa.semanticbits.com/caaers/"
		driver = "firefox"
	}
}