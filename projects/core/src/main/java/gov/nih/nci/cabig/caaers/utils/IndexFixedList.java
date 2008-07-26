package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IndexFixedList<E> implements DecoratedList<E>{
	
	List<E> internalList;
	List<E> tempList;
	
	public IndexFixedList(List<E> orgList){
		this.internalList = orgList;
		tempList = new ArrayList<E>(orgList);
	}
	
	public boolean add(E o) {
		tempList.add(o);
		return internalList.add(o);
	}

	public void add(int arg0, E arg1) {
		throw new UnsupportedOperationException("Can only add an items at the end");
		
	}

	public boolean addAll(Collection<? extends E> c) {
		tempList.addAll(c);
		return internalList.addAll(c);
	}

	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		if(true)
			throw new UnsupportedOperationException("Can only add an items at the end");
		return false;
	}

	public void clear() {
		internalList.clear();
		tempList.clear();
	}

	public boolean contains(Object o) {
		return internalList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return internalList.containsAll(c);
	}

	public E get(int i) {
		return tempList.get(i);
	}

	public int indexOf(Object arg0) {
		if(true) throw new UnsupportedOperationException("Cannot find this easily in this kind of list");
		return -1;
	}

	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	public Iterator<E> iterator() {
		return tempList.iterator();
	}

	public int lastIndexOf(Object arg0) {
		if(true) throw new UnsupportedOperationException("Cannot find this easily in this kind of list");
		return -1;
	}

	public ListIterator<E> listIterator() {
		return tempList.listIterator();
	}

	public ListIterator<E> listIterator(int i) {
		return tempList.listIterator(i);
	}

	public E remove(int i) {
		Object o = tempList.set(i, null);
		internalList.remove(o);
		return (E)o;
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
		return tempList.size();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return tempList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return tempList.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return tempList.toArray(arg0);
	}
	
	public List<E> getInternalList() {
		return internalList;
	}

	public void setInternalList(List<E> internalList) {
		this.internalList = internalList;
	}

	
}
