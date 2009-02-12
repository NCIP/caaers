package gov.nih.nci.cabig.caaers.web.selenium;


import java.util.Calendar;


public class CaaersStudyTest extends CaaersSeleniumTestCase {
	String studyId = "N027D-test1-"+Calendar.getInstance().getTimeInMillis();
	

	public void testCreateCTCStudy() throws Exception {
		try {
			checkLogin();
			createCTCStudy(studyId+"-ctc");
			assertTrue("Create Study failure", selenium
					.isTextPresent("The following study is saved successfully"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("test failed");
		}
	}
	
	public void testCreateMeddraStudy() throws Exception {
		try {
			checkLogin();
			createMeddraStudy(studyId+"-meddra");
			assertTrue("Create Study failure", selenium
					.isTextPresent("The following study is saved successfully"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("test failed");
		}
	}
	public void testSearchStudy() throws Exception {
		try {
			checkLogin();
			studyId = "N027D";
			searchStudy(studyId);
			assertTrue(
					"Study with id:" + studyId + "was not found",
					selenium
							.isTextPresent("Phase I Study of CCI-779 and Temozolomide in Combination with Radiation Therapy in Glioblastoma Multiforme"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("test failed");
		}
	}

	public void testEditStudy() throws Exception {
		try {
			checkLogin();
			//studyId=studyId+"-ctc";
			studyId="N027D";
			searchStudy(studyId);
			String studyLink = selenium
					.getAttribute("//a[text()='"+studyId+"']@href");
			selenium.click("link="+studyId);
			selenium.waitForPageToLoad("30000");
			editStudy();
			assertTrue("Create Study failure", true);
			assertTrue("Create Study failure", selenium.isTextPresent("Information saved successfully"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("test failed");
		}
	}

}
