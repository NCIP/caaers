package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 */
public interface CodedEnum<C> {
    C getCode();
    String getDisplayName();
}
