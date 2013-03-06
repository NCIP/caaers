/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
