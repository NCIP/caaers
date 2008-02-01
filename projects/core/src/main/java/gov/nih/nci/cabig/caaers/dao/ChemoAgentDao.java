package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ChemoAgent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class ChemoAgentDao extends CaaersDao<ChemoAgent> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    @Override
	public Class<ChemoAgent> domainClass() {
        return ChemoAgent.class;
    }
    @Override
    public ChemoAgent getById(int id) {
    	return super.getById(id);
    }
    public List<ChemoAgent> getBySubname(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }

    @SuppressWarnings("unchecked")
    public List<ChemoAgent> getAll() {
        return findAll("o.name");
    }

}
