/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import java.util.List;

public class SiteResearchStaffCommandHelper {
    protected Integer id;
    protected List<SiteResearchStaffRoleCommandHelper> rsRoles;

    public List<SiteResearchStaffRoleCommandHelper> getRsRoles() {
        return rsRoles;
    }

    public void setRsRoles(List<SiteResearchStaffRoleCommandHelper> rsRoles) {
        this.rsRoles = rsRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

