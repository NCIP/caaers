package gov.nih.nci.cabig.caaers.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly=true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> {

	private static final List<String> SUBSTRING_MATCH_PROPERTIES
			= Arrays.asList("firstName", "lastName");
	private static final List<String> EXACT_MATCH_PROPERTIES
			= Collections.emptyList();
	private static final List<Object> EXTRA_PARAMS
			= Collections.emptyList();

	public List<ResearchStaff> getAll() {
        return getHibernateTemplate().find("from ResearchStaff");
    }  
	
    public Class<ResearchStaff> domainClass() {
        return ResearchStaff.class;
    }
    
    @Transactional(readOnly=false)
    public void save(ResearchStaff researchStaff) {
        getHibernateTemplate().saveOrUpdate(researchStaff);                        
    }     
    
    public List<ResearchStaff> getBySubnames(String[] subnames) {
        return findBySubname(subnames,null,EXTRA_PARAMS,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}
}