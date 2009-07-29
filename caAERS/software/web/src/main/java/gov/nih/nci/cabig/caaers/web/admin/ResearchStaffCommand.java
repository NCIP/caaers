package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;

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

    public SiteResearchStaffRole getSiteResearchStaffRoleByCode(List<SiteResearchStaffRole> roles, String code) {
        for (SiteResearchStaffRole srsr: roles) {
            if (srsr.getRoleCode().equals(code)) return srsr;
        }
        return null;
    }

    public void buildResearchStaffCommandHelpers() {
        if (this.getSrs() == null) this.setSrs(new ArrayList<SiteResearchStaffCommandHelper>());

        for (SiteResearchStaff srs : getResearchStaff().getSiteResearchStaffs()) {
            SiteResearchStaffCommandHelper srsch = new SiteResearchStaffCommandHelper();
            srsch.setRsRoles(new ArrayList<SiteResearchStaffRoleCommandHelper>());
            
            List<SiteResearchStaffRole> roles = srs.getSiteResearchStaffRoles();

            for (ConfigProperty cp : this.getAllRoles()) {
                SiteResearchStaffRole srsr = getSiteResearchStaffRoleByCode(roles, cp.getCode());
                SiteResearchStaffRoleCommandHelper role = new SiteResearchStaffRoleCommandHelper();
                role.setRoleCode(cp.getCode());
                
                if (srsr != null) {
                    role.setStartDate(srsr.getStartDate());
                    role.setEndDate(srsr.getEndDate());
                    role.setChecked(Boolean.TRUE);
                } else {
                    role.setStartDate(new Date(System.currentTimeMillis()));
                    role.setChecked(Boolean.FALSE);
                }

                srsch.getRsRoles().add(role);
            }
            getSrs().add(srsch);
        }
        
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
