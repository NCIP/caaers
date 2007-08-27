package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;


/**
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("S")
public class Submitter extends ReportPerson {
}
