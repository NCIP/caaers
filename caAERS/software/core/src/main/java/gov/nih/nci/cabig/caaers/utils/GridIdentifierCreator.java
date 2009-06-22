package gov.nih.nci.cabig.caaers.utils;

/**
 * @author Rhett Sutphin
 */
public interface GridIdentifierCreator {

    /**
     * @param identification
     *                is a string that is used by the caAERS application to store the uniquely
     *                identifyable information for that resource.
     */
    String getGridIdentifier(String identification);
}