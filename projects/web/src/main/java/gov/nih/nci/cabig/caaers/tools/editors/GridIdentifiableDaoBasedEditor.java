package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.GridIdentifiable;

/**
 * A {@link java.beans.PropertyEditor} that supports binding domain objects by their IDs or their
 * grid IDs.
 *
 * @see DaoBasedEditor
 * @see GridIdentifiableDao
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC.  Refactor into a shared library. */
public class GridIdentifiableDaoBasedEditor extends DaoBasedEditor {
    private GridIdentifiableDao<?> gridDao;

    public GridIdentifiableDaoBasedEditor(GridIdentifiableDao<?> dao) {
        super(dao);
        gridDao = dao;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            super.setAsText(text);
            return;
        } catch (IllegalArgumentException iae) {
            // Fall through
        }

        GridIdentifiable value = gridDao.getByGridId(text);
        if (value == null) {
            throw new IllegalArgumentException("There is no "
                + gridDao.domainClass().getSimpleName() + " with id or gridId " + text);
        } else {
            setValue(value);
        }
    }
}
