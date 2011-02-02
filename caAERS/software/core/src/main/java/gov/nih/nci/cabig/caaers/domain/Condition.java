package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class Condition.
 *
 * @author Ion C. Olaru
 */

@Entity
@Table(name = "conditions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_conditions_id") })

public class Condition extends AbstractMutableDomainObject {
    
    /** The condition name. */
    private String conditionName;

    /**
     * Gets the condition name.
     *
     * @return the condition name
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * Sets the condition name.
     *
     * @param conditionName the new condition name
     */
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
}