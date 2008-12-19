package gov.nih.nci.cabig.caaers.utils;

import groovy.util.MapEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * This is a set implementation that allows duplicate, entry only if the addAll call has duplicate items in it.
 * <code>
 *   {@link CustomLinkedSet} s = new {@link CustomLinkedSet}(1);
 *   s.add('red');
 *   s.add('blue');
 *   s.add('red'); //ignored
 *   s.add('red')' //add - because threshold is 1
 * </code>  
 * @author biju
 *
 * @param <E>
 */
public class CustomLinkedSet<E> implements Set<E>{
	
	private ArrayList<E> list = new ArrayList<E>();
	private Map<E, Integer> currentAdditionMap = new HashMap<E, Integer>();
	private int threshold = 0;
	
	public CustomLinkedSet(){
		
	}

	public boolean add(E o) {
		return list.add(o);
	}
	
	public int noOfEntries(E o){
		int i = 0;
		for(E e : list){
			if(e.equals(o)) i++;
		}
		Integer i1 = currentAdditionMap.get(o);
		if(i1 == null) i1 = 0;
		return Math.abs(i-i1);
	}
	
	public boolean addAll(Collection<? extends E> c) {
		boolean retValue = true;
		currentAdditionMap.clear();
		
		
		for(E o : c){
			int count = noOfEntries(o);
			Integer i = currentAdditionMap.get(o);
			if(i == null) i = 0;
			if(count > 0){
				
				
				
				if(i <= count){
					currentAdditionMap.put(o, i+1);//ignore 
					
				}else {
					retValue &= list.add(o);
					currentAdditionMap.put(o, i+1);
				}
			}else {
				retValue &= list.add(o);
				currentAdditionMap.put(o, i + 1);
			}
		}
		return retValue;
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<E> iterator() {
		return list.iterator();
	}

	public boolean remove(Object o) {
		currentAdditionMap.remove(o);
		return list.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		for(Object o : c){
			currentAdditionMap.remove(o);
		}
		
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		Set keySet = currentAdditionMap.keySet();
		for(Object o : c){
			keySet.remove(o);
		}
		for(Object o : keySet){
			currentAdditionMap.remove(o);
		}
		
		return list.retainAll(c);
	}

	public int size() {
		return list.size();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	public int getThreshold() {
		return threshold;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return list.toString();
	}
}
