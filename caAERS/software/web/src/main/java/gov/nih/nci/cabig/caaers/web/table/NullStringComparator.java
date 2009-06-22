package gov.nih.nci.cabig.caaers.web.table;


import java.util.Comparator;

import org.apache.commons.collections.comparators.NullComparator;

/**
 * A Comparator that will compare nulls to be either lower or higher than
 * other String objects.
 */
public class NullStringComparator extends NullComparator {
    private Comparator<String> stringComparator;

    public NullStringComparator(Comparator comparator) {
        super(comparator);
    }

    public NullStringComparator() {
        super();
        this.stringComparator = String.CASE_INSENSITIVE_ORDER;
    }

    public int compare(Object o1, Object o2) {
        if (o1 != null && o2 != null && o1 instanceof String && o2 instanceof String) {


            return stringComparator.compare((String) o1, (String) o2);
        } else {
            return super.compare(o1, o2);
        }
    }


    public Comparator<String> getStringComparator() {
        return stringComparator;
    }

    public void setStringComparator(Comparator<String> stringComparator) {
        this.stringComparator = stringComparator;
    }
}
