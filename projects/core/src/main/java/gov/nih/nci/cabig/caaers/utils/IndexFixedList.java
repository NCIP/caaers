package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IndexFixedList<E> implements DecoratedList<E>{
	
	List<E> internalList;
	List<Integer> deletedEntries = new ArrayList<Integer>();
	
	public IndexFixedList(List<E> orgList){
		this.internalList = orgList;
	}
	
	public boolean add(E o) {
		return internalList.add(o);
	}

	public void add(int arg0, E arg1) {
		throw new UnsupportedOperationException("Can only add an items at the end");
		
	}

	public boolean addAll(Collection<? extends E> c) {
		return internalList.addAll(c);
	}

	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		if(true)
			throw new UnsupportedOperationException("Can only add an items at the end");
		return false;
	}

	public void clear() {
		internalList.clear();
		deletedEntries.clear();
	}

	public boolean contains(Object o) {
		return internalList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return internalList.containsAll(c);
	}

	public E get(int i) {
		return internalList.get(correctedIndex(i));
	}

	public int indexOf(Object arg0) {
		if(true) throw new UnsupportedOperationException("Cannot find this easily in this kind of list");
		return -1;
	}

	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	public Iterator<E> iterator() {
		return internalList.iterator();
	}

	public int lastIndexOf(Object arg0) {
		if(true) throw new UnsupportedOperationException("Cannot find this easily in this kind of list");
		return -1;
	}

	public ListIterator<E> listIterator() {
		return internalList.listIterator();
	}

	public ListIterator<E> listIterator(int arg0) {
		if(true) throw new UnsupportedOperationException("Cannot determine this easily in this kind of list");
		return null;
	}

	public E remove(int i) {
		deletedEntries.add(i);
		return internalList.remove(correctedIndex(i));
	}

	public boolean remove(Object arg0) {
		if(true) throw new UnsupportedOperationException("Cannot remove this easily in this kind of list");
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		if(true) throw new UnsupportedOperationException("Cannot remove this easily in this kind of list");
		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		if(true) throw new UnsupportedOperationException("Cannot remove this easily in this kind of list");
		return false;
	}

	public E set(int i, E o) {
		if(true) throw new UnsupportedOperationException("Cannot do this easily in this kind of list");
		return null;
	}

	public int size() {
		return internalList.size() + deletedEntries.size();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return internalList.subList(correctedIndex(fromIndex), correctedIndex(toIndex));
	}

	public Object[] toArray() {
		if(true) throw new UnsupportedOperationException("Cannot do this easily in this kind of list");
		return null;
	}

	public <T> T[] toArray(T[] arg0) {
		if(true) throw new UnsupportedOperationException("Cannot do this easily in this kind of list");
		return null;
	}
	
	
	
	
	public List<E> getInternalList() {
		return internalList;
	}

	public void setInternalList(List<E> internalList) {
		this.internalList = internalList;
	}

	public int correctedIndex(int i){
		if(deletedEntries.isEmpty()) return i;
		int j = i;
		for(Integer k : deletedEntries){
			if(k < i) j--;
		}
		return j;
	}
	
}
