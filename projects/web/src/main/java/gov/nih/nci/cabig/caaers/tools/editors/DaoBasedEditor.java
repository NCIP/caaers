package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.ctms.dao.DomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * A {@link java.beans.PropertyEditor} that supports binding domain objects by their IDs
 *
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC.  Refactor into a shared library. */
/* TODO: after moving to CTMS commons, add tests for the config params */
public class DaoBasedEditor extends PropertyEditorSupport {
    private boolean strictIdChecking, nullForBlanks;
    private DomainObjectDao<?> dao;

    /**
     * Same as <code>{@link #DaoBasedEditor(DomainObjectDao, boolean, boolean)}(dao, false, true)</code>
     */
    public DaoBasedEditor(DomainObjectDao<?> dao) {
        this(dao, false, true);
    }

    /**
     * @param dao The dao against which to resolve provided IDs
     * @param strictIdChecking Whether or not to allow {@link #setValue} where the value has no ID.
     *      Due to the sometimes-odd way spring uses PEs, you probably want this to be false.
     * @param nullForBlanks Whether to treat a blank string as "no object".  If false, you'll get a
     *      NumberFormatException for blank strings.
     */
    public DaoBasedEditor(DomainObjectDao<?> dao, boolean strictIdChecking, boolean nullForBlanks) {
        this.dao = dao;
        this.strictIdChecking = strictIdChecking;
        this.nullForBlanks = nullForBlanks;
    }

    @Override
    public void setValue(Object value) {
        if (value != null && !(dao.domainClass().isAssignableFrom(value.getClass()))) {
            throw new IllegalArgumentException("This editor only handles instances of " + dao.domainClass().getName());
        }
        setValue((DomainObject) value);
    }

    private void setValue(DomainObject value) {
        if (value != null && value.getId() == null && strictIdChecking) {
            throw new IllegalArgumentException("This editor can't handle values without IDs");
        }
        super.setValue(value);
    }

    @Override
    public String getAsText() {
        DomainObject domainObj = (DomainObject) getValue();
        if (domainObj == null) {
            return null;
        } else {
            Integer id = domainObj.getId();
            return id == null ? null : id.toString();
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        DomainObject newValue;
        if (text == null) {
            newValue = null;
        } else if (nullForBlanks && StringUtils.isBlank(text)) {
            newValue = null;
        } else {
            Integer id = new Integer(text);
            newValue = dao.getById(id);
            if (newValue == null) {
                throw new IllegalArgumentException("There is no " + dao.domainClass().getSimpleName() + " with id=" + id);
            }
        }
        setValue(newValue);
    }
}
