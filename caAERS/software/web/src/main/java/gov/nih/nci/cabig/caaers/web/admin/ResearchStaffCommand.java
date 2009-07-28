package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResearchStaffCommand {
    protected final Log log = LogFactory.getLog(getClass());
    protected ResearchStaff researchStaff;
    protected List<ConfigProperty> allRoles;

    protected List<SiteResearchStaffCommandHelper> srs;

    public ResearchStaff getResearchStaff() {
        return researchStaff;
    }

    public void setResearchStaff(ResearchStaff researchStaff) {
        this.researchStaff = researchStaff;
    }

    public List<ConfigProperty> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<ConfigProperty> allRoles) {
        this.allRoles = allRoles;
    }

    public List<SiteResearchStaffCommandHelper> getSrs() {
        return srs;
    }

    public void setSrs(List<SiteResearchStaffCommandHelper> srs) {
        this.srs = srs;
    }

    public void addSiteResearchStaffCommandHelper() {
        if (this.getSrs() == null) this.setSrs(new ArrayList<SiteResearchStaffCommandHelper>());
        SiteResearchStaffCommandHelper srs = new SiteResearchStaffCommandHelper();
        if (srs.getRsRoles() == null) srs.setRsRoles(new ArrayList<SiteResearchStaffRoleCommandHelper>());
        for (ConfigProperty cp : this.getAllRoles()) {
            SiteResearchStaffRoleCommandHelper role = new SiteResearchStaffRoleCommandHelper();
            role.setRoleCode(cp.getCode());
            role.setStartDate(new Date(System.currentTimeMillis()));
            role.setChecked(Boolean.FALSE);
            srs.getRsRoles().add(role);
        }
        this.getSrs().add(srs);
    }
}
