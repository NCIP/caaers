package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Krikor Krumlian
 */
public class PreExistingConditionDao extends CaaersDao<PreExistingCondition> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("text", "meddraLlt");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    public Class<PreExistingCondition> domainClass() {
        return PreExistingCondition.class;
    }

    public List<PreExistingCondition> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
}
