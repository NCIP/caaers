package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.Investigator;

public interface InvestigatorRepository {
  /**
   * This method will create or modify an investigator in the system. 
   * @param investigator
   * @param changeURL
   */
  public void save(Investigator  investigator, String changeURL);
}
