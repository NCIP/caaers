package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the Agent domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * 
 */
@Entity
@Table(name = "agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_agents_id") })
public class Agent extends AbstractMutableDomainObject {

    private String name;

    private String description;

    private String nscNumber;

    private String displayName;

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    @Column(name = "nsc")
    public String getNscNumber() {
        return nscNumber;
    }

    public void setNscNumber(String nsc) {
        this.nscNumber = nsc;
    }
    
    @Transient
    public String getDisplayName(){
    	if(StringUtils.isNotEmpty(nscNumber)){
    		return nscNumber + "::" + name;
    	}
    	return name;
    }
    
    public void setDisplayName(String displayName){
    	this.displayName = displayName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Agent that = (Agent) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (nscNumber != null ? !nscNumber.equals(that.nscNumber) : that.nscNumber != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        result = 29 * result + (nscNumber != null ? nscNumber.hashCode() : 0);
        return result;
    }

}
