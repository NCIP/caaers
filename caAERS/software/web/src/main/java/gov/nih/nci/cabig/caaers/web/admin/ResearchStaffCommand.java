package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResearchStaffCommand {
    protected final Log log = LogFactory.getLog(getClass());
    protected ResearchStaff researchStaff;
    protected List<ConfigProperty> allRoles;

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
}
