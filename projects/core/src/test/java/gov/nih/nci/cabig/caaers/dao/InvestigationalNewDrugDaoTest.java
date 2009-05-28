package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.InvestigatorHeldIND;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationHeldIND;

import java.util.HashMap;
import java.util.List;

@CaaersUseCases( { CREATE_STUDY })
public class InvestigationalNewDrugDaoTest extends DaoTestCase<InvestigationalNewDrugDao> {
    OrganizationDao orgDao;

    InvestigatorDao invDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        orgDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
        invDao = (InvestigatorDao) getApplicationContext().getBean("investigatorDao");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDomainClass() {
        assertEquals("Domain class should be same", getDao().domainClass().getName(),
                        InvestigationalNewDrug.class.getName());
    }

    public void testSave() {
        int id = 0;
        {
            Organization org = orgDao.getById(-1001);
            InvestigationalNewDrug idrug = new InvestigationalNewDrug();
            idrug.setIndNumber(12345);
            OrganizationHeldIND holder = new OrganizationHeldIND();

            holder.setOrganization(org);
            idrug.setINDHolder(holder);
            holder.setInvestigationalNewDrug(idrug);
            getDao().save(idrug);
            id = idrug.getId();
            log.debug("ID of the InvestigationalNewDrug :" + idrug.getId());
        }
        interruptSession();
        {

            InvestigationalNewDrug nDrug = getDao().getById(id);
            Organization org = orgDao.getById(-1001);
            assertEquals("IND number should be same", new Integer(12345), nDrug.getIndNumber());
            System.out.println(nDrug.getINDHolder());
            assertEquals("The organization Id should be same", org.getId(),
                            ((OrganizationHeldIND) nDrug.getINDHolder()).getOrganization().getId());
            
            assertEquals("12345 : " + nDrug.getHolderName() , nDrug.getNumberAndHolderName());
        }

        // Investigator Held
        {
            Investigator inv = invDao.getById(-100);
            InvestigationalNewDrug idrug = new InvestigationalNewDrug();
            idrug.setIndNumber(12346);
            InvestigatorHeldIND holder = new InvestigatorHeldIND();
            holder.setInvestigator(inv);
            idrug.setINDHolder(holder);
            holder.setInvestigationalNewDrug(idrug);
            getDao().save(idrug);
            id = idrug.getId();
            log.debug("ID of the InvestigationalNewDrug :" + idrug.getId());

        }
        interruptSession();
        {
            InvestigationalNewDrug nDrug = getDao().getById(id);
            assertEquals("IND number should be same", new Integer(12346), nDrug.getIndNumber());
        }
    }

    public void testSearchInvestigationalNewDrugs() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sponsorName", "N");
        // map.put("xstrINDNumber", "8");
        List<InvestigationalNewDrug> inds = getDao().searchInvestigationalNewDrugs(map);
        for (InvestigationalNewDrug ind : inds)
            System.out.println(ind);
        assertEquals("Search Result size", 2, inds.size());
    }

    public void testFindByIds() throws Exception {
        List<InvestigationalNewDrug> drugs = getDao().findByIds(new String[] { "-881" });
        assertNotNull("There should be Investigational New Drugs available", drugs);
        assertEquals("IND number should be -881", -881, (int) drugs.get(0).getIndNumber());
    }
    
    public void testFetchCtepInd(){
    	InvestigationalNewDrug nDrug = getDao().fetchCtepInd();
    	assertEquals("IND number should be same", new Integer(-111), nDrug.getIndNumber());
    }

    public void testFetchDcpInd(){
    	InvestigationalNewDrug nDrug = getDao().fetchDcpInd();
    	assertEquals("IND number should be same", new Integer(-222), nDrug.getIndNumber());
    }
    
}
