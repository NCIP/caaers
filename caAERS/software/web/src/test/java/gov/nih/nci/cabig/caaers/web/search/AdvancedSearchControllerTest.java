package gov.nih.nci.cabig.caaers.web.search;

import org.springframework.validation.BindException;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 *
 * @author Sameer Sawant
 */
public class AdvancedSearchControllerTest extends WebTestCase {
	
	protected AdvancedSearchController controller;
	protected BindException errors;
	protected AdvancedSearchCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
	
		controller = new AdvancedSearchController();
	}
	
}