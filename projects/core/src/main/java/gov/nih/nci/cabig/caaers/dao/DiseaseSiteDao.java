package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DiseaseSite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * @author kulasekaran
 * 
 */
public class DiseaseSiteDao extends CaaersDao<DiseaseSite> {
	
	private static final List<String> SUBSTRING_MATCH_PROPERTIES
			= Arrays.asList("name");
	private static final List<String> EXACT_MATCH_PROPERTIES
			= Collections.emptyList();
	private static final List<Object> EXTRA_PARAMS
			= Collections.emptyList();
	
    @Override
	public Class<DiseaseSite> domainClass() {
        return DiseaseSite.class;
    } 
    
    public List<DiseaseSite> getBySubnames(String[] subnames) {
        return findBySubname(subnames,null,EXTRA_PARAMS,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}

    public List<DiseaseSite> getAll() {
        return getHibernateTemplate().find("from DiseaseSite");
    }
    
}

