package gov.nih.nci.cabig.caaers.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * This wrapper class projects only a specific type of elements in the contained list. <br />
 * <br />
 * <b>Eg:</b><br />
 * Say a list consists of the following objects <br />
 * [a1,b1,a2,a3,b2,c1,c2,b3,null,b4], where a1..n, b1..n , c1..n corresponds to objects of type A,
 * B, C respectively.
 * 
 * <pre><code>
 *     List&lt;Object&gt; list = .....;//[a1,b1,a2,a3,b2,c1,c2,b3,null,b4]
 *     ProjectedList&lt;B&gt; bList = new ProjectedList&lt;B&gt;(B.class, list);
 *     System.out.println(bList);// prints [b1, b2, b3, null, b4]
 *     ProjectedList&lt;C&gt; cList = new ProjectedList&lt;C&gt;(C.class, list);
 *     System.out.println(cList);// prints [c1, c2, null]
 *     cList.set(1, c8); 
 *     System.out.println(cList);// prints [c1,c8, null]
 *     System.out.println(list);//[a1,b1,a2,a3,b2,c1,c8,b3,null,b4]
 *     
 *     bList.set(3,b5);
 *     System.out.println(cList);// prints [c1,c8]
 *     System.out.println(bList);// prints [b1, b2, b3, b5, b4]
 * </code></pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Jul 7, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class ProjectedList<E> implements DecoratedList<E> {
    /** LisObject that is decorated */
    private List<? super E> list;

    /** The class that is to be projected */
    public Class<E> klass;

    public ProjectedList(List<? super E> list, Class<E> klass) {
        this.list = list;
        this.klass = klass;
    }

    /**
     * Adds the object in the target list
     */
    public boolean add(E o) {
        return list.add(o);
    }

    /**
     * Adds the element <code>E</code> aObject ohe specified index. If the index is &gt;= the size
     * of the projected list, the <code>element</code> will be appended to the
     * <code>target list</code>. If the index is &lt; the size of the projected list,the
     * <code>element</code> will be inserted right after the <code>n-th (n = index)</code>
     * occurence of object of type <code>klass</code> in the <code>target list</code>.
     */
    public void add(int index, E element) {
        int size = size();
        if (index >= size) {
            list.add(element);
        } else {
            int i = -1, orgIndex = -1;
            for (Object o : list) {
                orgIndex++;
                if (o == null || klass.equals(o.getClass())) i++;
                if (i == index) break;
            }
            list.add(i, element);
        }
    }

    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public void clear() {
        if (true) throw new UnsupportedOperationException("Not supported");
    }

    /**
     * Will return true if the object<code>o</code> is available in the list that is decorated.
     * The behaviour here is slightly different from the traditional behaviour of lists.
     */
    public boolean contains(Object o) {
        return list.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    /**
     * Return the element of the projected list identified at position(<code>index</code>). Will
     * loop through the <code>target list</code> and returns the <code>n-th(n = index)</code>
     * element of type <code>klass</code>
     */
    public E get(int index) {
        int i = -1;
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) i++;
            if (i == index) return (E) o;
        }
        return null;
    }

    public int indexOf(Object o) {
        if (true) throw new UnsupportedOperationException("Not supported");
        return 0;
    }

    public boolean isEmpty() {
        return !listIterator().hasNext();
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public int lastIndexOf(Object o) {
        if (true) throw new UnsupportedOperationException("Not supported");
        return 0;
    }

    @SuppressWarnings("unchecked")
    public ListIterator<E> listIterator() {
        ArrayList<E> newList = new ArrayList<E>();
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) newList.add((E) o);
        }
        return newList.listIterator();
    }

    @SuppressWarnings("unchecked")
    public ListIterator<E> listIterator(int index) {
        ArrayList<E> newList = new ArrayList<E>();
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) newList.add((E) o);
        }
        return newList.listIterator(index);
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        int i = -1;
        int orgIndex = -1;
        for (Object o : list) {
            orgIndex++;
            if (o == null || klass.equals(o.getClass())) i++;
            if (i == index) return (E) list.remove(orgIndex);
        }
        return null;
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        if (true) throw new UnsupportedOperationException("Not supported");
        return false;
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        int i = -1;
        int orgIndex = -1;
        for (Object o : list) {
            orgIndex++;
            if (o == null || klass.equals(o.getClass())) i++;
            if (i == index) break;
        }
        if (i < 0) throw new IllegalArgumentException("The last index of the projected object is "
                        + i + ", so unable to replace object at " + index);
        return (E) list.set(orgIndex, element);

    }

    public int size() {
        int i = 0;
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) i++;
        }
        return i;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        if (true) throw new UnsupportedOperationException("Not supported");
        return null;
    }

    public Object[] toArray() {
        ArrayList<E> newList = new ArrayList<E>();
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) newList.add((E) o);
        }
        return newList.toArray();
    }

    public <K> K[] toArray(K[] a) {
        ArrayList<E> newList = new ArrayList<E>();
        for (Object o : list) {
            if (o == null || klass.equals(o.getClass())) newList.add((E) o);
        }
        return newList.toArray(a);
    }
    
    public void setInternalList(List<E> list) {
    	this.list = list;
    }
    @SuppressWarnings("unchecked")
	public List<E> getInternalList() {
    	return (List<E>)this.list;
    }

}