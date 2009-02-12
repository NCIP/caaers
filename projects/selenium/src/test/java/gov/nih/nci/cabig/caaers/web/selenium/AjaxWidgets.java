package gov.nih.nci.cabig.caaers.web.selenium;

import junit.framework.*;

import com.thoughtworks.selenium.*;

/**
 * @author Karthik Iyer
 * 
 */
public class AjaxWidgets extends TestCase {
	Selenium selenium;

	public AjaxWidgets(Selenium selenium) {
		super();
		this.selenium = selenium;
	}

	public void clickNext(String nextButton) {
		selenium.click(nextButton);
		selenium.waitForPageToLoad("30000");
	}

	public void confirmOK(String expectedMessage) throws InterruptedException {
		/*
		 * confirmMessage should have the following format: original: Are you
		 * sure you want to delete this? transformed: ^Are you sure you want to
		 * delete this[\\s\\S]$
		 */
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (selenium.isConfirmationPresent())
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		String actual = selenium.getConfirmation();
		// selenium.chooseOkOnNextConfirmation();
		// System.out.println(s+" confirmMessage: "+confirmMessage);
		assertTrue("Did not see matching confirm message. " + "\n Expected: "
				+ expectedMessage + "\n Actual: " + actual, actual
				.matches(expectedMessage));
	}

	public void login() throws Exception {
		selenium.open("/caaers/public/login");
		selenium.type("j_username", "SYSTEM_ADMIN");
		selenium.type("j_password", "system_admin");
		selenium.click("power_btn");
		selenium.waitForPageToLoad("30000");

	}

	public void typeAutosuggest(String element, String text, String elemPresent)
			throws InterruptedException {
		selenium.click(element);
		String elemValue = selenium.getValue(element);

		for (int i = 0; i < elemValue.length(); i++)
			selenium.typeKeys(element, "\b");
		selenium.typeKeys(element, text);
		waitForElementPresent("//div[@id='" + elemPresent + "']/ul/li");

		selenium.click("//div[@id='" + elemPresent + "']/ul/li[1]");
		selenium.click(element);
	}

	public void waitForElementPresent(String xPathElement)
			throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("Timed out waiting for element to be created: "
						+ xPathElement);
			try {
				if (selenium.isElementPresent(xPathElement))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);

		}
	}

	public void waitForElementNotPresent(String xPathElement)
			throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("Timed out waiting for element to be removed: "
						+ xPathElement);
			try {
				if (!selenium.isElementPresent(xPathElement))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void addPanel(String clickableElement, String waitForElementPresent)
			throws InterruptedException {
		selenium.click(clickableElement);
		waitForElementPresent(waitForElementPresent);
	}

	public void removePanel(String clickableElement, String confirmMessage)
			throws InterruptedException {
		// clickableElement should be set to the delete 'X' used to delete
		// panels
		// Set confirmMessage to null if no confirmation dialog will appear
		selenium.click(clickableElement);
		if (confirmMessage != null)
			confirmOK(confirmMessage);
		waitForElementNotPresent(clickableElement);
	}

	public void removeLastPanel(String clickableElement, String confirmMessage)
			throws Exception {
		/*
		 * Removes the last panel which has the clickable element. It should be
		 * of the following form: Original: //input[@id=
		 * 'participant.organizationIdentifiers[1].primaryIndicator']/parent::td/parent::tr/descendant::img[@alt='delete']
		 * Use: //input[@id=
		 * 'participant.organizationIdentifiers[?].primaryIndicator']/parent::td/parent::tr/descendant::img[@alt='delete']
		 * 
		 * ClickableElement should be set to the delete 'X' used to delete
		 * panels. Set confirmMessage to null if no confirmation dialog will
		 * appear.
		 */
		String latestClickableElement = computeLatestElement(clickableElement,
				true);
		selenium.click(latestClickableElement);
		if (confirmMessage != null)
			confirmOK(confirmMessage);
		waitForElementNotPresent(latestClickableElement);
	}

	public void addLastPanel(String clickableElement,
			String waitForElementPresent) throws Exception {
		/*
		 * waitForElementPresent should have an array index in the Name. For ex:
		 * if element is://span[@id=
		 * 'assignment.preExistingConditions[0].preExistingCondition.text'] then
		 * fashion the string as//span[@id=
		 * 'assignment.preExistingConditions[?].preExistingCondition.text']
		 */
		String latestElement = computeLatestElement(waitForElementPresent,
				false);
		selenium.click(clickableElement);
		waitForElementPresent(latestElement);
	}

	public int computeLatestElementIndex(String waitForElementPresent,
			boolean exists) throws Exception {
		String[] parts = split(waitForElementPresent);

		boolean loop = true;
		int i = 0;
		for (; loop; i++) {
			if (!(selenium.isElementPresent(parts[0] + i + parts[1])))
				loop = false;
			/*System.out.println("\n checking through: " + parts[0] + i
					+ parts[1]);*/
		}
		i--;
		if (exists) {
			// return parts[0] + (--i) + parts[1];
			return (--i);
		} else
			return i;

	}

	public String[] split(String waitForElementPresent) throws Exception {
		if (waitForElementPresent.indexOf('?') == -1)
			throw new Exception("Element is malformed. \n Value passed:\n"
					+ waitForElementPresent
					+ "\n does not have '?' character as required.");
		String parts[] = waitForElementPresent.split("\\?");
		if (parts.length < 2) {
			String temp[] = { parts[0], "" };
			parts = temp;
		}
		return parts;
	}

	public String computeLatestElement(String waitForElementPresent,
			boolean exists) throws Exception {
		String parts[] = split(waitForElementPresent);
		return parts[0]
				+ computeLatestElementIndex(waitForElementPresent, exists)
				+ parts[1];
	}

	public void clickCalendar(String linkId) throws InterruptedException {
		/*
		 * selenium.clickAt("//a[@id='" + linkId + "']", "");
		 * waitForElementPresent("//div[text()='Today']");
		 * selenium.clickAt("//div[text()='Today']", "");
		 * selenium.clickAt("//div[text()='Today']", "");
		 */
		selenium.click("//a[@id='participant.dateOfBirth-calbutton']");
		waitForElementPresent("//div[text()='Today']");
		selenium.click("//div[text()='Today']");
		selenium.clickAt("//div[text()='Today']", "(0,0)");
	}
}