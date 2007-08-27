package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Rhett Sutphin
 */
@Entity
@DiscriminatorValue("R")
public class Reporter extends ReportPerson {
}
