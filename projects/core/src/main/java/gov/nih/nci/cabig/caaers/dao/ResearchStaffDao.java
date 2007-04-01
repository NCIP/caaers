package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

/**
 * @author Kulasekaran
 * 
 */
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> {
    
    @Override
    public Class<ResearchStaff> domainClass() {
        return ResearchStaff.class;
    }
    
    public void save(ResearchStaff researchStaff) {
        getHibernateTemplate().saveOrUpdate(researchStaff);                        
    }     
}