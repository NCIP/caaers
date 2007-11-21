package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("R")
public class Reporter extends ReportPerson {
}
