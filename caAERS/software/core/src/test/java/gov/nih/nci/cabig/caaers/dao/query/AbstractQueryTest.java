/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
		assertEquals("select * from emp WHERE y = 2 OR z = 3",query.getQueryString());
	}

	public void test2(){
		query.orWhere("y = 3");
		assertEquals("select * from emp WHERE y = 3", query.getQueryString());
	}

	public void testOneAnd(){
		query.andWhere("dept = 'ABC'");
		assertEquals("select * from emp WHERE dept = 'ABC'", query.getQueryString());
	}

	public void testWithMultipleAndAndOr(){
		query.join("dept on dept.id = emp.id");
		query.andWhere("j = 1");
		query.andWhere("m = 5");
		query.orWhere("y = 3");
		query.orWhere("y = 4");
		assertEquals("select * from emp join dept on dept.id = emp.id WHERE j = 1 AND m = 5 AND ( y = 3 OR y = 4 )", query.getQueryString());
	}

	public void testCreateDateQueryForLessThan() {
		AbstractQuery adverseEventQuery = new AbstractQuery("select distinct ae, aeRp from  AdverseEvent ae join ae.reportingPeriod aeRp") {
		};
		
		String startDateQuery = "";
		String endDateQuery = "";
		String courseStartDateQuery = "";
		String courseEndDateQuery = "";
		try {
			startDateQuery = adverseEventQuery.createDateQuery("ae.startDate", "04/01/2015", "<");
			endDateQuery = adverseEventQuery.createDateQuery("ae.endDate", "04/10/2015", "<");
			courseStartDateQuery = adverseEventQuery.createDateQuery("aeRp.startDate", "04/01/2015", "<");
			courseEndDateQuery = adverseEventQuery.createDateQuery("aeRp.endDate", "04/10/2015", "<");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(startDateQuery, "(year(ae.startDate) < '2015' OR (year(ae.startDate) = '2015' AND month(ae.startDate) < '4') OR (year(ae.startDate) = '2015' AND month(ae.startDate) = '4' AND day(ae.startDate) < '1'))");
		assertEquals(endDateQuery, "(year(ae.endDate) < '2015' OR (year(ae.endDate) = '2015' AND month(ae.endDate) < '4') OR (year(ae.endDate) = '2015' AND month(ae.endDate) = '4' AND day(ae.endDate) < '10'))");
		assertEquals(courseStartDateQuery, "(year(aeRp.startDate) < '2015' OR (year(aeRp.startDate) = '2015' AND month(aeRp.startDate) < '4') OR (year(aeRp.startDate) = '2015' AND month(aeRp.startDate) = '4' AND day(aeRp.startDate) < '1'))");
		assertEquals(courseEndDateQuery, "(year(aeRp.endDate) < '2015' OR (year(aeRp.endDate) = '2015' AND month(aeRp.endDate) < '4') OR (year(aeRp.endDate) = '2015' AND month(aeRp.endDate) = '4' AND day(aeRp.endDate) < '10'))");
	}

	public void testCreateDateQueryForLessThanOrEqualTo() {
		AbstractQuery adverseEventQuery = new AbstractQuery("select distinct ae, aeRp from  AdverseEvent ae join ae.reportingPeriod aeRp") {
		};
		
		String startDateQuery = "";
		String endDateQuery = "";
		String courseStartDateQuery = "";
		String courseEndDateQuery = "";
		try {
			startDateQuery = adverseEventQuery.createDateQuery("ae.startDate", "04/01/2015", "<=");
			endDateQuery = adverseEventQuery.createDateQuery("ae.endDate", "04/10/2015", "<=");
			courseStartDateQuery = adverseEventQuery.createDateQuery("aeRp.startDate", "04/01/2015", "<=");
			courseEndDateQuery = adverseEventQuery.createDateQuery("aeRp.endDate", "04/10/2015", "<=");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(startDateQuery, "(year(ae.startDate) < '2015' OR (year(ae.startDate) = '2015' AND month(ae.startDate) < '4') OR (year(ae.startDate) = '2015' AND month(ae.startDate) = '4' AND day(ae.startDate) <= '1'))");
		assertEquals(endDateQuery, "(year(ae.endDate) < '2015' OR (year(ae.endDate) = '2015' AND month(ae.endDate) < '4') OR (year(ae.endDate) = '2015' AND month(ae.endDate) = '4' AND day(ae.endDate) <= '10'))");
		assertEquals(courseStartDateQuery, "(year(aeRp.startDate) < '2015' OR (year(aeRp.startDate) = '2015' AND month(aeRp.startDate) < '4') OR (year(aeRp.startDate) = '2015' AND month(aeRp.startDate) = '4' AND day(aeRp.startDate) <= '1'))");
		assertEquals(courseEndDateQuery, "(year(aeRp.endDate) < '2015' OR (year(aeRp.endDate) = '2015' AND month(aeRp.endDate) < '4') OR (year(aeRp.endDate) = '2015' AND month(aeRp.endDate) = '4' AND day(aeRp.endDate) <= '10'))");
	}

}
