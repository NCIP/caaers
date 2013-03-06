/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

import java.util.List;

public interface DecoratedList<E>  extends List<E> {
	void setInternalList(List<E> list);
	List<? extends E> getInternalList(); 
}
