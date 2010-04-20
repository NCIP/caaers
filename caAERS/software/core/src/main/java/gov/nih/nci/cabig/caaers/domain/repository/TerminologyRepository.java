package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import java.util.List;

/**
 * @author Ion C. Olaru
 */
public interface TerminologyRepository {

	public List<CtcCategory> getAllCtcCategories(Integer versionID);

}