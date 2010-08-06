package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gov.nih.nci.security.authorization.domainobjects.User;

public class ResearchStaffCommand {
    protected final Log log = LogFactory.getLog(getClass());
    protected ResearchStaff researchStaff;
    protected List<ConfigProperty> allRoles;
    protected User csmUser;
    
    protected boolean shouldSync;
    private boolean oldShouldSync;
    protected boolean canSync;

    protected List<SiteResearchStaffCommandHelper> siteResearchStaffCommandHelper;
    protected Date activeDate;
    boolean isPO = SecurityUtils.hasAuthorityOf(UserGroupType.person_and_organization_information_manager);
    boolean isUA = SecurityUtils.hasAuthorityOf(UserGroupType.user_administrator);

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

    public List<SiteResearchStaffCommandHelper> getSiteResearchStaffCommandHelper() {
        return siteResearchStaffCommandHelper;
    }

    public void setSiteResearchStaffCommandHelper(List<SiteResearchStaffCommandHelper> siteResearchStaffCommandHelper) {
        this.siteResearchStaffCommandHelper = siteResearchStaffCommandHelper;
    }

    public SiteResearchStaffRole getSiteResearchStaffRoleByCode(List<SiteResearchStaffRole> roles, String code) {
        for (SiteResearchStaffRole srsr: roles) {
            if (srsr.getRoleCode().equals(code)) return srsr;
        }
        return null;
    }

    public void buildResearchStaffCommandHelpers() {
        if (this.getSiteResearchStaffCommandHelper() == null) this.setSiteResearchStaffCommandHelper(new ArrayList<SiteResearchStaffCommandHelper>());

        for (SiteResearchStaff srs : getResearchStaff().getSiteResearchStaffs()) {
            SiteResearchStaffCommandHelper srsch = new SiteResearchStaffCommandHelper();
            srsch.setId(srs.getId());
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
            getSiteResearchStaffCommandHelper().add(srsch);
        }
        
    }

    public void addSiteResearchStaffCommandHelper() {
        if (this.getSiteResearchStaffCommandHelper() == null) this.setSiteResearchStaffCommandHelper(new ArrayList<SiteResearchStaffCommandHelper>());
        SiteResearchStaffCommandHelper srs = new SiteResearchStaffCommandHelper();
        if (srs.getRsRoles() == null) srs.setRsRoles(new ArrayList<SiteResearchStaffRoleCommandHelper>());
        for (ConfigProperty cp : this.getAllRoles()) {
            SiteResearchStaffRoleCommandHelper role = new SiteResearchStaffRoleCommandHelper();
            role.setRoleCode(cp.getCode());
            role.setStartDate(new Date(System.currentTimeMillis()));
            role.setChecked(Boolean.FALSE);
            srs.getRsRoles().add(role);
        }
        this.getSiteResearchStaffCommandHelper().add(srs);
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public boolean getPO() {
        return isPO;
    }

    public boolean getUA() {
        return isUA;
    }

    public boolean isCreateFlow(){
       return researchStaff == null || researchStaff.getId() == null;
    }

    public User getCsmUser() {
        return csmUser;
    }

    public void setCsmUser(User csmUser) {
        this.csmUser = csmUser;
    }

    public boolean isShouldSync() {
        return shouldSync;
    }

    public void setShouldSync(boolean shouldSync) {
        this.oldShouldSync = this.shouldSync;
        this.shouldSync = shouldSync;
    }

    public boolean isCanSync() {
        return canSync;
    }

    public void setCanSync(boolean canSync) {
        this.canSync = canSync;
    }

    /**
     * If true, in create mode we could continue the CSM operation. 
     */
    public boolean canProceedCSMOperation(){
       if(csmUser == null) return true;
       return canSync;
    }
}
