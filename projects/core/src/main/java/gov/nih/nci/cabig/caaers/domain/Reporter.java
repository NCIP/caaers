package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class represents the Reporter domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("R")
public class Reporter extends ReportPerson {
}
