package gov.nih.nci.cabig.caaers.web.selenium;

import java.io.File;

import org.apache.tools.ant.*;

public class CaaersRulesTestBootstrap extends Task {
	String rulesDir = null;

	public void setRulesDir(String rulesDir) {
		this.rulesDir = rulesDir;
	}

	public void validate() {
		if (rulesDir == null)
			throw new BuildException("The rulesDir attribute has not been set");
		File rulesLoc = new File(rulesDir);
		if (!rulesLoc.exists())
			throw new BuildException(
					"The directory specified by the rulesDir attribute"
							+ " does not exist");
	}

	public void execute() {
		//validate();
		System.out.println("In task class");
		CaaersSeleniumTestCase cstc = new CaaersSeleniumTestCase();
		try {
			cstc.setUp();
			cstc.waitForCaaersStartup();
			cstc.uploadRules(); 
			//cstc.uploadRules("C:/workspace/caaers-trunk/selenium/src/test/resources");
			cstc.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String args[]){
		CaaersRulesTestBootstrap crtb = new CaaersRulesTestBootstrap();
		crtb.setRulesDir("C:\\workspace\\caaers-trunk\\selenium\\src\\test\\resources\\rules");
		crtb.execute();
	}

}
