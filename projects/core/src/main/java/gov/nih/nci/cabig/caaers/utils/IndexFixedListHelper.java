package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This will help in maintaining single instance of an IndexFixedList. 
 * 
 * Eg:
 *  <pre>
 *  	@Entity
 *  	public class Study {
 *  		IndexFixedListHelper listHelper;
 *  		public Study(){
 *  			listHelper = new IndexFixedListHelper();
 *          }
 *          
 *          @Transient
 *          public void setIdentifiers(List<IndexFixedList> identifiers){
 *          	//do 
 *          }
 *          public List<IndexFixedList> getIdentifiers(){
 *          	return helper.getIndexFixedList(Identifier.class);
 *          }
 *          
 *          @OneToMany
 *          public List<IndexFixedList> getIdentifersInternal(){
 *          	return listHelper.getInternalList(Identifier.class);
 *          }
 *          public void setIdentifiersInternal(List<IndexFixedList> identifiers){
 *          	listHelper.setInternalList(Identifiers.class, identifiers);
 *          }
 *          
 *      }
 *  </pre>
 * @author Biju Joseph
 *
 */
public class IndexFixedListHelper {
	Map<Class<?>, IndexFixedList<?>> cache = new HashMap<Class<?>, IndexFixedList<?>>();
	
	public <T> void add(Class<T> klass){
		setInternalList(klass, new ArrayList<T>());
	}
	public <T> IndexFixedList<T> setInternalList(Class<T> klass, List<T> list){
		IndexFixedList<T> iList = new IndexFixedList<T>(list);
		cache.put(klass, iList);
		return iList;
	}
	
	@SuppressWarnings("unchecked")
	public <T> IndexFixedList<T> getIndexFixeList(Class<T> klass){
		return (IndexFixedList<T>) cache.get(klass);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getInternalList(Class<T> klass){
		return (List<T>) cache.get(klass).getInternalList();
	}
}
