package gov.nih.nci.cabig.caaers.tools;

import java.util.List;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.list.AbstractListDecorator;

/**
 * Just like {@link org.apache.commons.collections15.list.LazyList}, except that it uses a
 * {@link org.apache.commons.collections15.Transformer}&lt;Integer, E&gt; instead of a
 * {@link org.apache.commons.collections15.Factory}&lt;E&gt;. The transformer is provided with the
 * index whenever a new object needs to be created.
 * 
 * @author Rhett Sutphin
 */
public class IndexedLazyList<E> extends AbstractListDecorator<E> {
    private Transformer<Integer, ? extends E> transformer;

    /**
     * Constructor that wraps (not copies).
     * 
     * @param list
     *                the list to decorate, must not be null
     * @param transformer
     *                the factory to use for creation, must not be null
     * @throws IllegalArgumentException
     *                 if list or factory is null
     */
    protected IndexedLazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        super(list);
        if (transformer == null) {
            throw new IllegalArgumentException("Transformer must not be null");
        }
        this.transformer = transformer;
    }

    public static <E> List<E> decorate(List<E> list, Transformer<Integer, ? extends E> factory) {
        return new IndexedLazyList<E>(list, factory);
    }

    /**
     * Decorate the get method to perform the lazy behaviour. <p/> If the requested index is greater
     * than the current size, the list will grow to the new size and a new object will be returned
     * from the factory. Indexes in-between the old size and the requested size are left with a
     * placeholder that is replaced with a factory object when requested.
     * 
     * @param index
     *                the index to retrieve
     */
    @Override
    public E get(int index) {
        int size = getList().size();
        if (index < size) {
            // within bounds, get the object
            E object = getList().get(index);
            if (object == null) {
                // item is a place holder, create new one, set and return
                object = transformer.transform(index);
                getList().set(index, object);
                return object;
            } else {
                // good and ready to go
                return object;
            }
        } else {
            // we have to grow the list
            for (int i = size; i < index; i++) {
                getList().add(null);
            }
            // create our last object, set and return
            E object = transformer.transform(index);
            getList().add(object);
            return object;
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sub = getList().subList(fromIndex, toIndex);
        return new IndexedLazyList<E>(sub, transformer);
    }
}
