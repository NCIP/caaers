package gov.nih.nci.cabig.caaers.domain;

/**
 * @author ArunKumarK
 * @author Ion C. Olaru
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.*;

 
/**
 * The Class Epoch.
 */
@Entity
@Table(name = "epochs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_epochs_id")})
public class Epoch extends AbstractMutableRetireableDomainObject {

    /** The Constant NAME_BASELINE. */
    public static final String NAME_BASELINE = "Baseline";
    
    /** The Constant NAME_PRETREATMENT. */
    public static final String NAME_PRETREATMENT = "Pre-Treatment";
    
    /** The Constant NAME_TREATMENT. */
    public static final String NAME_TREATMENT = "Treatment";
    
    /** The Constant NAME_POSTTREATMENT. */
    public static final String NAME_POSTTREATMENT = "Post-treatment";

    /** The name. */
    private String name;
    
    /** The description text. */
    private String descriptionText = "";
    
    /** The epoch order. */
    private Integer epochOrder;
    
    /** The arms. */
    private List<Arm> arms = new ArrayList<Arm>();

    /**
     * Instantiates a new epoch.
     */
    public Epoch() {
    }

    /**
     * Instantiates a new epoch.
     *
     * @param epochName the epoch name
     * @param epochOrder the epoch order
     * @param armNames the arm names
     */
    public Epoch(String epochName, Integer epochOrder, String... armNames) {
        this.name = epochName;
        this.epochOrder = epochOrder;

        if (armNames.length == 0) {
            addArm(new Arm(epochName));
        } else {
            for (String armName : armNames) {
                addArm(new Arm(armName));
            }
        }

    }

    /**
     * Gets the arms.
     *
     * @return the arms
     */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "epoch_id", nullable = false)
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<Arm> getArms() {
        return arms;
    }

    /**
     * Sets the arms.
     *
     * @param arms the new arms
     */
    public void setArms(List<Arm> arms) {
        this.arms = arms;
    }

    /**
     * Adds the arm.
     *
     * @param arm the arm
     * @return true, if successful
     */
    public boolean addArm(Arm arm) {
        return arms.add(arm);
    }

    /**
     * Removes the arm.
     *
     * @param arm the arm
     * @return true, if successful
     */
    public boolean removeArm(Arm arm) {
        if (arms != null && arms.size() != 0)
            return arms.remove(arm);
        return false;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description text.
     *
     * @return the description text
     */
    @Column(name = "description")
    public String getDescriptionText() {
        return descriptionText;
    }

    /**
     * Sets the description text.
     *
     * @param descriptionText the new description text
     */
    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    /**
     * Gets the epoch order.
     *
     * @return the epoch order
     */
    @Column(name = "order_no")
    public Integer getEpochOrder() {
        return epochOrder;
    }

    /**
     * Sets the epoch order.
     *
     * @param epochOrder the new epoch order
     */
    public void setEpochOrder(Integer epochOrder) {
        this.epochOrder = epochOrder;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[").append(name);
    	if(arms != null && arms.get(0) != null && arms.get(0).getSolicitedAdverseEvents() != null)
    		sb.append("  {").append(arms.get(0).getSolicitedAdverseEvents()).append("}");
    	sb.append("]");
    	return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epoch)) return false;

        Epoch epoch = (Epoch) o;
        if(getId() != null && epoch.getId() != null && epoch.getId().equals(getId())) return true;

        if (name != null && epoch.getName() != null && epoch.getName().equals(name)) return true;

        return false;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
