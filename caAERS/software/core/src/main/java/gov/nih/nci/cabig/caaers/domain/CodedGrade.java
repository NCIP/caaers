package gov.nih.nci.cabig.caaers.domain;

/**
 * An adapter interface so that {@link Grade} and {@link CtcGrade} can be used interchangeably.
 * 
 * @author Rhett Sutphin
 */
public interface CodedGrade {
    Integer getCode();

    String getDisplayName();
    
   String getName(); 
}
