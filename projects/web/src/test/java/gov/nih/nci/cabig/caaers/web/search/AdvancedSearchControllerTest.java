package gov.nih.nci.cabig.caaers.web.search;

import org.springframework.validation.BindException;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import com.semanticbits.core.CQL2HQL;

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
	
	public void testDeleteMe() throws Exception{
		CQL2HQL transformer = new CQL2HQL();
		CQLQuery cQuery = new CQLQuery();
		Object object = new Object();
		object.setName("gov.nih.nci.cabig.caaers.domain.Participant");
		Attribute attribute = new Attribute();
		attribute.setName("firstName");
		attribute.setValue("a");
		attribute.setPredicate(Predicate.LIKE);
		object.setAttribute(attribute);
		cQuery.setTarget(object);
		String hQuery = transformer.translate(cQuery, true, true);
		System.out.println("hQuery = " + hQuery);
	}
}