package gov.nih.nci.cabig.caaers.utils;

import java.util.List;

public interface DecoratedList<E>  extends List<E> {
	void setInternalList(List<E> list);
	List<? extends E> getInternalList(); 
}
