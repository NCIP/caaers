package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator;

public class TabPrivilageAndObjectIdGenerator extends AbstractPrivilegeAndObjectIdGenerator {

    protected String getKeyValue(Object object) {
        return ((Tab) object).getClass().getName();
    }

    protected boolean supports(Object object) {
        return object instanceof Tab;
    }

}
