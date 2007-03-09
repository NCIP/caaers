/**
 * 
 */
package gov.nih.nci.cabig.ctms.grid.ae.common;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public interface AdverseEventConsumer {

    void register(gov.nih.nci.cabig.ctms.grid.ae.beans.AENotificationType aeNotification)
                    throws gov.nih.nci.cabig.ctms.grid.ae.stubs.types.InvalidRegistration,
                    gov.nih.nci.cabig.ctms.grid.ae.stubs.types.RegistrationFailed;

}
