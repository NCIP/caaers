package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
/**
 * This class represents the Notification domain object associated with Safety signalling.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
@Entity
@Table(name = "notifications")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_notifications_id") })
public class Notification extends AbstractMutableDomainObject implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6073805433214950491L;

	/** The email. */
	private String roles;
	private String emails;

    /** The content. */
    private String content;

    /** The subject. */
    private String subject;

    /** The name. */
    @Column(unique = true, nullable = false)
    private String name;

    private Study study;
    


    public Notification() {
    }

    @Transient
    public List<String> getRecipientRoles() {
        if(StringUtils.isEmpty(roles)) return new ArrayList<String>();
       return Arrays.asList(StringUtils.split(roles, ','));
    }

    public void setRecipientRoles(List<String> recipientRoles) {
        roles = StringUtils.join(recipientRoles.toArray(), ',');
    }

    @Transient
    public List<String> getRecipientEmails() {
        if(StringUtils.isEmpty(emails)) return new ArrayList<String>();
        return Arrays.asList(StringUtils.split(emails, ','));
    }

    public void setRecipientEmails(List<String> recipientEmails) {
        emails = StringUtils.join(recipientEmails.toArray(), ',');
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_id")
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    
    public ValidationErrors validate(){
        ValidationErrors validationErrors = new ValidationErrors();
        if(StringUtils.isEmpty(emails) && StringUtils.isEmpty(roles)){
            validationErrors.addValidationError("NF_001", "Invalid recipient information");
        }
        for(String email : getRecipientEmails()) {
            if(!GenericValidator.isEmail(email)){
                validationErrors.addValidationError("NF_001", "Invalid recipient information");
                break;
            }
        }

        if(StringUtils.isEmpty(name)) validationErrors.addValidationError("NF_005", "Name cannot be empty");
        if(StringUtils.isEmpty(subject)) validationErrors.addValidationError("NF_002", "Subject cannot be empty");
        if(study == null) validationErrors.addValidationError("NF_003", "Study cannot be empty");
        if(StringUtils.isEmpty(content)) validationErrors.addValidationError("NF_004", "Content cannot be empty");

        return validationErrors;
    }
}
