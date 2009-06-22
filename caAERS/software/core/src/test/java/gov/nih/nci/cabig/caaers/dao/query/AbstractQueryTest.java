package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AbstractQueryTest extends TestCase {
	AbstractQuery query;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		query = new AbstractQuery("select * from emp"){
			
		};
	}
	public void testGetQueryString() {
		query.andWhere("x = 1");
		query.orWhere("y = 2");
		query.orWhere("z = 3");
		assertEquals("select * from emp WHERE x = 1 AND ( y = 2 OR z = 3 )",query.getQueryString());
	}
	
	public void testGetQueryString_WithoutAnd(){
		query.orWhere("y = 2");
		query.orWhere("z = 3");
		assertEquals("select * from emp OR y = 2 OR z = 3",query.getQueryString());
	}
	
	
}