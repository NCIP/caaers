package gov.nih.nci.cabig.caaers.dao;

import java.util.Date;
import java.util.List;

import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Search;

/**
 *
 * @author Sameer Sawant
 */
public class SearchDaoTest extends DaoNoSecurityTestCase<SearchDao> {

	public void testSave() throws Exception {
		Search search = new Search();
		search.setLoginId("testLogin");
		search.setName("testName");
		search.setDescription("testDescription");
		search.setCriteriaXml("testCriteria");
		search.setCreatedDate(new Date());
		getDao().save(search);
		assertNotNull("No ID for new search", search.getId());
        int saveId = search.getId();
        interruptSession();
        
        Search s = getDao().getById(saveId);
        CoreTestCase.assertNotNull("Search wasnt saved successfully", s);
	}
	
	public void testGetByLogin() throws Exception {
		List<Search> searchList = getDao().getByLogin("SYSTEM_ADMIN");
		CoreTestCase.assertNotNull("testGetbyLogin didnt fetch the search list", searchList);
		CoreTestCase.assertEquals("Incorrect number of searches fetched by getByLogin method", 4, searchList.size());
	}
	
	public void testGetByLoginAndName() throws Exception{
		List<Search> searchList = getDao().getByLoginAndName("SYSTEM_ADMIN", "Search1");
		CoreTestCase.assertNotNull("testGetbyLoginAndName didnt fetch the search", searchList);
		CoreTestCase.assertEquals("Incorrect number of searches fetched by getByLoginAndName method", 1, searchList.size());
	}
	
	public void testDeleteByLoginIdAndName() throws Exception{
		boolean successfull = getDao().deleteByLoginIdAndName("Search1", "SYSTEM_ADMIN");
		List<Search> searchList = getDao().getByLogin("SYSTEM_ADMIN");
		assertTrue("deleteByLoginIdAndName failed", successfull);
		assertEquals("Incorrect number of searches on deletion", 3, searchList.size());
	}
}