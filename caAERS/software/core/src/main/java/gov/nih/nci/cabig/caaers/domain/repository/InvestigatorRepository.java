/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import java.util.HashMap;
import java.util.List;

 
/**
 * The Interface InvestigatorRepository.
 */
public interface InvestigatorRepository {
  
  /**
   * This method will create or modify an investigator in the system.
   *
   * @param investigator the investigator
   * @param changeURL the change url
   * @return the investigator
   */
  public Investigator save(Investigator  investigator, String changeURL);
  
  /**
   * Search investigator.
   *
   * @param query the query
   * @return the list
   */
  public List<Investigator> searchInvestigator(final InvestigatorQuery query);
  
  /**
   * Search investigator.
   *
   * @param query the query
   * @param searchCriteriaMap the search criteria map
   * @return the list
   */
  public List<Investigator> searchInvestigator(InvestigatorQuery query,HashMap searchCriteriaMap);
  
  /**
   * Gets the by subnames.
   *
   * @param subnames the subnames
   * @param siteId the site id
   * @return the by subnames
   */
  public List<SiteInvestigator> getBySubnames(final String[] subnames,int siteId);
  
  /**
   * Convert to remote.
   *
   * @param localInvestigator the local investigator
   * @param remoteInvestigator the remote investigator
   */
  public void convertToRemote(Investigator localInvestigator, Investigator remoteInvestigator);

/**
 * Gets the by id.
 *
 * @param id the id
 * @return the by id
 */
public Investigator getById(int id);
}
