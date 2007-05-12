package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Krikor Krumlian
 */
public class PriorTherapyDao extends CaaersDao<PriorTherapy> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("text", "meddraTerm", "meddraCode");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    public Class<PriorTherapy> domainClass() {
        return PriorTherapy.class;
    }

    public List<PriorTherapy> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
}
