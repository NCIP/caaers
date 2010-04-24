package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.List;

/**
 * @author Ion C. Olaru
 */
public interface TerminologyRepository {

	public List<CtcCategory> getAllCtcCategories(Integer versionID);
	public List<CtcTerm> getCtcTerm(String categoryName, Integer ctcaeVersion, String termName);

}