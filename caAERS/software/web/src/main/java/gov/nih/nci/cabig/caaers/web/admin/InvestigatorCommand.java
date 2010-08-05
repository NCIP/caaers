package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.security.authorization.domainobjects.User;

/**
 * @author: Biju Joseph
 */
public class InvestigatorCommand {

    private Investigator investigator;

    protected User csmUser;

    protected boolean shouldSync;
    private boolean oldShouldSync;
    protected boolean canSync;

    public Investigator getInvestigator() {
        return investigator;
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }



    public boolean isCreateFlow(){
       return investigator == null || investigator.getId() == null;
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
        return oldShouldSync && shouldSync  && canSync;
    }
}
