package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.CaaersError;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Wrapper interceptor to add grid identifiers to objects which support them.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class GridIdentifierInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -4003680120893817442L;

    // private GridIdentifierCreator gridIdentifierCreator = new CaBigGridIdentifierCreator();
    private GridIdentifierCreator gridIdentifierCreator;

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames,
                    Type[] types) {
        boolean localMod = false;
        if (entity instanceof MutableDomainObject) {
            int gridIdIdx = findBigId(propertyNames);
            if (gridIdIdx < 0) throw new CaaersError(
                            "Object implements MutableDomainObject but doesn't have gridId property; class: "
                                            + entity.getClass().getName());
            if (state[gridIdIdx] == null) {
                state[gridIdIdx] = gridIdentifierCreator.getGridIdentifier(UUID.randomUUID()
                                .toString());
                localMod = true;
            }
        }
        return localMod;
    }

    private int findBigId(String[] propertyNames) {
        for (int i = 0; i < propertyNames.length; i++) {
            if ("gridId".equals(propertyNames[i])) return i;
        }
        return -1; // defer throwing exception so we can report class
    }

    public GridIdentifierCreator getGridIdentifierCreator() {
        return gridIdentifierCreator;
    }

    public void setGridIdentifierCreator(GridIdentifierCreator gridIdentifierCreator) {
        this.gridIdentifierCreator = gridIdentifierCreator;
    }

}