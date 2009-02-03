package gov.nih.nci.cabig.caaers.web.selenium;
import org.apache.tools.ant.*;
public class CaaersRulesTest extends Task {
	String rulesDir = "C:\\Documents and Settings\\Karthik Iyer\\Desktop\\caaers\\rules\\1.7";
	

	public void execute() {
		CaaersSeleniumTestCase cstc = new CaaersSeleniumTestCase();
		try {
			cstc.setUp();
			cstc.checkLogin();
			cstc.uploadRules(rulesDir);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	}
