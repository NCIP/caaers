package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Kulasekaran
 */
public class SiteInvestigatorDao extends GridIdentifiableDao<SiteInvestigator> {
    
	private static final List<String> SUBSTRING_MATCH_PROPERTIES
		= Arrays.asList("investigator.firstName", "investigator.lastName");
	private static final List<String> EXACT_MATCH_PROPERTIES
		= Collections.emptyList();
	private static final List<Object> EXTRA_PARAMS
		= Collections.emptyList();

    public Class<SiteInvestigator> domainClass() {
        return SiteInvestigator.class;
    } 
    
	@SuppressWarnings("unchecked")
    public SiteInvestigator getSiteInvestigator(Site site, Investigator investigator) {
        return CollectionUtils.firstElement(
            (List<SiteInvestigator>) getHibernateTemplate().find(
                "from SiteInvestigator a where a.Site = ? and a.investigator = ?",
                new Object[] { site, investigator })
        );
    }
	
	@SuppressWarnings("unchecked")
    public List<SiteInvestigator> getSiteInvestigators(Site site) {
        return 
            (List<SiteInvestigator>) getHibernateTemplate().find(
                "from SiteInvestigator a where a.site = ?",
                new Object[] { site }
        ); 		
    }
	
	public List<SiteInvestigator> getBySubnames(String[] subnames, int site) {
        return findBySubname(subnames,"o.site.id = '"+site+"'",EXTRA_PARAMS,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}	
}
