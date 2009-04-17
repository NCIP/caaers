package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;

public interface InvestigatorRepository {
  /**
   * This method will create or modify an investigator in the system. 
   * @param investigator
   * @param changeURL
   */
  public void save(Investigator  investigator, String changeURL);
  public List<Investigator> searchInvestigator(final InvestigatorQuery query);
  public List<Investigator> getBySubnames(final String[] subnames);
  public void convertToRemote(Investigator localInvestigator, Investigator remoteInvestigator);
}
