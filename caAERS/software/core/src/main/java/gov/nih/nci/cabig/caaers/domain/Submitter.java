package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class represents the Submitter domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("S")
public class Submitter extends ReportPerson {
}
