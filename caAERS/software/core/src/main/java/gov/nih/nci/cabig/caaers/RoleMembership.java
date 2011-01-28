package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.HashSet;
import java.util.Set;

/**
 * Will mimic the SuiteRoleMembership
 * @see gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership
 * @author: Biju Joseph
 */
public class RoleMembership {

    private UserGroupType role;
    private Set<String> organizationNCICodes;
    private Set<String> studyIdentifiers;
    private boolean allSite;
    private boolean allStudy;

    public RoleMembership(UserGroupType role){
        this.role = role;
        organizationNCICodes = new HashSet<String>();
        studyIdentifiers = new HashSet<String>();
    }

    public boolean isActive(){
      return  allSite || allStudy || (!organizationNCICodes.isEmpty() ) || (!studyIdentifiers.isEmpty());
    }
    public boolean isAllSite() {
        return allSite;
    }

    public void setAllSite(boolean allSite) {
        this.allSite = allSite;
    }

    public boolean isAllStudy() {
        return allStudy;
    }

    public void setAllStudy(boolean allStudy) {
        this.allStudy = allStudy;
    }

    public Set<String> getOrganizationNCICodes() {
        return organizationNCICodes;
    }

    public void setOrganizationNCICodes(Set<String> organizationNCICodes) {
        this.organizationNCICodes = organizationNCICodes;
    }

    public UserGroupType getRole() {
        return role;
    }

    public void setRole(UserGroupType role) {
        this.role = role;
    }

    public Set<String> getStudyIdentifiers() {
        return studyIdentifiers;
    }

    public void setStudyIdentifiers(Set<String> studyIdentifiers) {
        this.studyIdentifiers = studyIdentifiers;
    }

    public void addOrganizationNCICode(String nciCode){
        organizationNCICodes.add(nciCode);
    }

    public void addStudyIdentifier(String id){
        studyIdentifiers.add(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RoleMembership [");
        sb.append("role :").append(String.valueOf(role))
          .append("allStudy :").append(allStudy)
          .append("allSite :").append(allSite)
          .append("organizationNCICodes :").append(organizationNCICodes.toString())
          .append("studyIdentifiers :").append(studyIdentifiers.toString())
          .append("]");
        return sb.toString();
    }
}
