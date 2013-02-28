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
 * The Interface TerminologyRepository.
 *
 * @author Ion C. Olaru
 */
public interface TerminologyRepository {

	/**
	 * Gets the all ctc categories.
	 *
	 * @param versionID the version id
	 * @return the all ctc categories
	 */
	public List<CtcCategory> getAllCtcCategories(Integer versionID);
	
	/**
	 * Gets the ctc term.
	 *
	 * @param categoryName the category name
	 * @param ctcVersionName the ctcae version
	 * @param termName the term name
	 * @return the ctc term
	 */
	public List<CtcTerm> getCtcTerm(String categoryName, String ctcVersionName, String termName);

}
