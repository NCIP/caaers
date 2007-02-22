package gov.nih.nci.cabig.caaers.tools.editors;

import java.beans.PropertyEditorSupport;

import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;

/**
 * A {@link java.beans.PropertyEditor} that supports binding domain objects by their IDs
 *
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC.  Refactor into a shared library. */
public class NullIdDaoBasedEditor extends PropertyEditorSupport {
    private CaaersDao<?> dao;

    public NullIdDaoBasedEditor(CaaersDao<?> dao) {
        this.dao = dao;
    }

    @Override
    public void setValue(Object value) {
        if (value != null && !(dao.domainClass().isAssignableFrom(value.getClass()))) {
            throw new IllegalArgumentException("This editor only handles instances of " + dao.domainClass().getName());
        }
        setValue((DomainObject) value);
    }

    private void setValue(DomainObject value) {
    	/*
        if (value != null && value.getId() == null) {
            throw new IllegalArgumentException("This editor can't handle values without IDs");
        }
        */
        super.setValue(value);
    }

    @Override
    public String getAsText() {
        DomainObject domainObj = (DomainObject) getValue();
        if (domainObj == null) {
            return null;
        } else {
        	return domainObj.getId() == null ? "" :  domainObj.getId().toString();
            //return domainObj.getId().toString();
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        DomainObject newValue;
        if (text == null) {
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
