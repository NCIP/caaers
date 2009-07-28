package gov.nih.nci.cabig.caaers.web.admin;

import java.util.List;
import java.util.Date;

public class SiteResearchStaffRoleCommandHelper {
    private String roleCode;
    private Date startDate;
    private Date endDate;
    private Boolean checked;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}