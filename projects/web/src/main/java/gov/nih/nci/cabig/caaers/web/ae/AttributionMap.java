package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.tools.IndexedLazyList;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.list.AbstractListDecorator;
import org.apache.commons.collections15.map.LazyMap;

/**
 * This is a nested collection view of an AdverseEventReport, exposing the various attributions in a
 * way that's friendly to the form view and the data binder. It's a map of lists of lists. The
 * keys/indices are
 * <ul>
 * <li>The attribution key (from the constants in {@link ExpeditedAdverseEventInputCommand})
 * <li>The adverse event index (relative to the AE report)
 * <li>The cause (study agent, concomitant medication, disease, etc.) index within the AE
 * </ul>
 * 
 * @author Rhett Sutphin
 */
public class AttributionMap extends LazyMap<String, List<List<Attribution>>> {
    public AttributionMap(ExpeditedAdverseEventReport aeReport) {
        super(new HashMap<String, List<List<Attribution>>>(), new AeAttributionListTransformer(aeReport));
    }

    private static class AeAttributionListTransformer implements Transformer<String, List<List<Attribution>>> {
        private ExpeditedAdverseEventReport aeReport;

        public AeAttributionListTransformer(ExpeditedAdverseEventReport aeReport) {
            this.aeReport = aeReport;
        }

        public List<List<Attribution>> transform(String s) {
            CauseAndAttributionAccessor<?, ?> accessor = CauseAndAttributionAccessor.getByKey(s);
            if (accessor == null) {
                throw new IllegalArgumentException("Don't know how to map " + s + " to a attributions collection");
            }
            return new AdverseEventsList(aeReport, accessor);
        }
    }

    private static class AdverseEventsList extends AbstractListDecorator<List<Attribution>> {
        private ExpeditedAdverseEventReport adverseEventReport;

        private CauseAndAttributionAccessor<?, ?> accessor;

        public AdverseEventsList(ExpeditedAdverseEventReport adverseEventReport, CauseAndAttributionAccessor<?, ?> accessor) {
            this.adverseEventReport = adverseEventReport;
            this.accessor = accessor;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<Attribution> get(int i) {
            return new AttributionsList(adverseEventReport.getAdverseEvents().get(i), accessor);
        }
    }

    private static class AttributionsList<C extends DomainObject, A extends AdverseEventAttribution<C>>implements List<Attribution> {
        private AdverseEvent adverseEvent;

        private CauseAndAttributionAccessor<C, A> accessor;

        private List<A> attributions;

        public AttributionsList(AdverseEvent adverseEvent, CauseAndAttributionAccessor<C, A> accessor) {
            this.adverseEvent = adverseEvent;
            this.accessor = accessor;
        }

        @SuppressWarnings( { "unchecked" })
        private List<A> getAeAttributions() {
            if (attributions == null) {
                List<A> realAttribs = accessor.getAttributionsList(adverseEvent);
                attributions = IndexedLazyList.decorate(realAttribs, new Transformer<Integer, A>() {
                    public A transform(Integer integer) {
                        return createAeAttribution(integer);
                    }
                });
            }
            return attributions;
        }

        public Attribution get(int i) {
            return getAeAttributions().get(i).getAttribution();
        }

        public int size() {
            return getAeAttributions().size();
        }

        public boolean isEmpty() {
            return getAeAttributions().isEmpty();
        }

        public Attribution set(int i, Attribution attribution) {
            Attribution previous;
            if (getAeAttributions().size() <= i) {
                previous = null;
            } else {
                previous = getAeAttributions().get(i).getAttribution();
            }
            AdverseEventAttribution<C> aeAttrib = getAeAttributions().get(i);
            aeAttrib.setAttribution(attribution);
            return previous;
        }

        public boolean add(Attribution o) {
            A aeAttrib = getAeAttributions().get(size());
            if (o != null) aeAttrib.setAttribution(o);
            return true;
        }

        private A createAeAttribution(int i) {
            A aeAttrib = accessor.createAttribution();
            aeAttrib.setAdverseEvent(adverseEvent);
            aeAttrib.setCause(accessor.findCause(i, adverseEvent.getReport()));
            return aeAttrib;
        }

        // //// UNIMPLEMENTED LIST METHODS

        public boolean contains(Object o) {
            throw new UnsupportedOperationException("contains not implemented");
        }

        public Iterator<Attribution> iterator() {
            throw new UnsupportedOperationException("iterator not implemented");
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not implemented");
        }

        public <T> T[] toArray(T[] a) {
            throw new UnsupportedOperationException("toArray not implemented");
        }

        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not implemented");
        }

        public boolean containsAll(Collection<?> c) {
            throw new UnsupportedOperationException("containsAll not implemented");
        }

        public boolean addAll(Collection<? extends Attribution> c) {
            throw new UnsupportedOperationException("addAll not implemented");
        }

        public boolean addAll(int index, Collection<? extends Attribution> c) {
            throw new UnsupportedOperationException("addAll not implemented");
        }

        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException("removeAll not implemented");
        }

        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException("retainAll not implemented");
        }

        public void clear() {
            throw new UnsupportedOperationException("clear not implemented");
        }

        public void add(int index, Attribution element) {
            throw new UnsupportedOperationException("add not implemented");
        }

        public Attribution remove(int index) {
            throw new UnsupportedOperationException("remove not implemented");
        }

        public int indexOf(Object o) {
            throw new UnsupportedOperationException("indexOf not implemented");
        }

        public int lastIndexOf(Object o) {
            throw new UnsupportedOperationException("lastIndexOf not implemented");
        }

        public ListIterator<Attribution> listIterator() {
            throw new UnsupportedOperationException("listIterator not implemented");
        }

        public ListIterator<Attribution> listIterator(int index) {
            throw new UnsupportedOperationException("listIterator not implemented");
        }

        public List<Attribution> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("subList not implemented");
        }
    }
}
