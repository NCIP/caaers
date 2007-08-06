package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kulasekaran
 *
 */
@Transactional(readOnly=true)
public class InvestigatorDao extends GridIdentifiableDao<Investigator> {
	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");
	private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();
    @Override
	public Class<Investigator> domainClass() {
        return Investigator.class;
    }

    @Transactional(readOnly=false)
    public void save(Investigator investigator) {
        getHibernateTemplate().saveOrUpdate(investigator);
    }
    public List<Investigator> getBySubnames(String[] subnames) {
        return findBySubname(subnames,SUBSTRING_MATCH_PROPERTIES,EXACT_MATCH_PROPERTIES);
	}
}