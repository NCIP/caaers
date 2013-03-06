/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.security.authorization.ObjectPrivilegeGenerator;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Map;

/**
 * @author Ion C. Olaru
 * @author Biju Joseph (refactoring)
 *
 */
public class TabObjectPrivilegeGenerator implements ObjectPrivilegeGenerator {

    protected Map<String, String> objectPrivilegeMap;

    public String resolve(Object o) {
        Tab t = (Tab) o;
        return objectPrivilegeMap.get(t.getClass().getName());
    }

    public void setObjectPrivilegeMap(Map<String, String> objectPrivilegeMap) {
        this.objectPrivilegeMap = objectPrivilegeMap;
    }
}
