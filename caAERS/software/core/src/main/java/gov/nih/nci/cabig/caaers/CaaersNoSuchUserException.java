/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers;

public class CaaersNoSuchUserException extends CaaersSystemException {
  public CaaersNoSuchUserException(String message) {
	  super(message);
  }
  public CaaersNoSuchUserException(String message, Throwable t) {
	  super(message, t);
  }

}
