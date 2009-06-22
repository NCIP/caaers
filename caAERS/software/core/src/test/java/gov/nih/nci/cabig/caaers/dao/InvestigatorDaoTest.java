package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Kulasekaran
 */
@CaaersUseCases( { STUDY_ABSTRACTION })
public class InvestigatorDaoTest extends DaoTestCase<InvestigatorDao> {
	
    public void testGetById() throws Exception {
        Investigator investigator = getDao().getById(-100);
        assertNotNull("Investigator not found", investigator);
        assertEquals("Wrong last name", "Scott", investigator.getLastName());
        assertEquals("Wrong first name", "Dilbert", investigator.getFirstName());
    }
    
    public void testGetByLoginId() {
    	Investigator inv = getDao().getByLoginId("abcd");
    	assertNotNull(inv);
    	assertEquals("karthik", inv.getFirstName());
    }

    public void testSaveLocalInvestigator() throws Exception {
        Integer savedId;
        {
            Investigator investigator = new LocalInvestigator();
            investigator.setFirstName("Jeff");
            investigator.setLastName("Someone");

            investigator.setEmailAddress("abc@def.com");
            investigator.setPhoneNumber("123-456-789");

            getDao().save(investigator);
            savedId = investigator.getId();
            assertNotNull("The saved investigator id", savedId);
        }

        interruptSession();

        {
        	System.out.println(savedId);
            Investigator loaded = getDao().getById(savedId);
            assertNotNull("Could not reload investigator id " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
        }
    }

    
    public void testGetBySubname(){
    	List<Investigator> invs = getDao().getBySubnames(new String[]{"kar"});
    	assertEquals(1, invs.size());
    }
    
    public void testGetInvestigatorById(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	assertEquals("karthik", inv.getFirstName());
    }
    
    public void testRefresh(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	assertEquals("karthik", inv.getFirstName());
    	getDao().refresh(inv);
    	assertEquals("karthik", inv.getFirstName());
    }
    
    public void testLock(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	assertEquals("karthik", inv.getFirstName());
    	getDao().lock(inv);
    	assertEquals("karthik", inv.getFirstName());
    }
    
    public void testStringToDate() throws Exception{
    	assertNotNull(getDao().stringToDate("02/02/2008"));
    }
    
    public void testFlush(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	getDao().flush();
    }
    
    public void testClearSession(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	getDao().clearSession();
    }
    
    public void testReassociate(){
    	Investigator inv = getDao().getInvestigatorById(-200);
    	assertNotNull(inv);
    	getDao().reassociate(inv);
    }
    
    public void testFindBySubname(){
    	List<Investigator> list = getDao().findBySubname(null, null, null);
    	assertNotNull(list);
    	assertTrue(list.isEmpty());
    }
    
    public void testFindBySubnameWithValidSubname(){
    	List<Investigator> list = getDao().findBySubname(new String[]{"kar"}, Arrays.asList(new Object[]{"firstName","lastName"}), Collections.EMPTY_LIST);
    	assertNotNull(list);
    	assertEquals(1,list.size());
    }
    
    public void testBuildSubnameQuery(){
    	String extraConditions = "cond1";
    	List<Object> extraParameters = Arrays.asList(new Object[]{"lmno"});
    	String joins = "join dept";
    	List<Object> params = new ArrayList<Object>();
    	params.add("one");
    	List<String> substringMatchProperties = Arrays.asList(new String[]{"s", "j"});
    	List<String> exactMatchProperties = Arrays.asList(new String[]{"m", "n"});
    	
    	String query = getDao().buildSubnameQuery(new String[]{"abc", "def"}, extraConditions, extraParameters, joins, params, substringMatchProperties, exactMatchProperties);
    	assertEquals(" select distinct o from gov.nih.nci.cabig.caaers.domain.Investigator o join dept where cond1 and (LOWER(s) LIKE ? or LOWER(j) LIKE ? or LOWER(m) LIKE ? or LOWER(n) LIKE ?) and (LOWER(s) LIKE ? or LOWER(j) LIKE ? or LOWER(m) LIKE ? or LOWER(n) LIKE ?)", query);
    }
    
    public void testGetByIquery(){
		List<Investigator> investigators = new ArrayList<Investigator>();
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByOrganization("-1003");
        investigators = getDao().getLocalInvestigator(investigatorQuery);
        assertNotNull(investigators);
        assertEquals(2, investigators.size());
	}
}
