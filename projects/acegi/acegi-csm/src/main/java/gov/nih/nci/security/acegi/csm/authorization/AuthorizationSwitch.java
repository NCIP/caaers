/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * @author Rhett Sutphin
 */
public class AuthorizationSwitch {
    private static final Log log = LogFactory.getLog(AuthorizationSwitch.class);

    private boolean on = true;

    public AuthorizationSwitch() {
        if (log.isDebugEnabled()) log.debug(this + " created");
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        if (log.isDebugEnabled()) log.debug(this + " turned " + (on ? "on" : "off"));
        this.on = on;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[0x")
            .append(System.identityHashCode(this)).append(']').toString();
    }
}
