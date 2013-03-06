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

public interface InvestigatorRepository {
  /**
   * This method will create or modify an investigator in the system. 
   * @param investigator
   * @param changeURL
   */
  public Investigator save(Investigator  investigator, String changeURL);
  public List<Investigator> searchInvestigator(final InvestigatorQuery query);
  public List<Investigator> searchInvestigator(InvestigatorQuery query,HashMap searchCriteriaMap);
  public List<SiteInvestigator> getBySubnames(final String[] subnames,int siteId);
  public void convertToRemote(Investigator localInvestigator, Investigator remoteInvestigator);
/**
 * @param id
 * @return
 */
public Investigator getById(int id);
}
