package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
/**
 * This class represents the InvestigationalNewDrug domain object associated with the Adverse event
 * report.
 * 
 * @author
 * 
 */
@Entity
@Table(name = "investigational_new_drugs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_investigational_new_dru_id") })
public class InvestigationalNewDrug extends AbstractMutableDomainObject {

    /** The log. */
    static private Log log = LogFactory.getLog(InvestigationalNewDrug.class);

	/** The CTE p_ ind. */
	public static int CTEP_IND = -111;
	
	/** The DC p_ ind. */
	public static int DCP_IND = -222;
    
    /** The Constant STRING_CTEP_IND. */
    public static final String STRING_CTEP_IND = "CTEP IND";
    
    /** The Constant STRING_DCP_IND. */
    public static final String STRING_DCP_IND = "DCP IND";

    /** The ind number. */
    private Integer indNumber;
    
    /** The i nd holder. */
    private INDHolder iNDHolder;

    private String status;

//    /** The study agent ind associations. */
//    private List<StudyAgentINDAssociation> studyAgentINDAssociations;

    /** The holder name. */
    private transient String holderName;

    /**
     * Gets the ind number.
     *
     * @return the ind number
     */
    public Integer getIndNumber() {
        return indNumber;
    }

    /**
     * Sets the ind number.
     *
     * @param number the new ind number
     */
    public void setIndNumber(Integer number) {
        indNumber = number;
    }

    /**
     * Gets the iND holder.
     *
     * @return the iND holder
     */
    @OneToOne(mappedBy = "investigationalNewDrug", fetch = FetchType.EAGER)
    @Cascade( { CascadeType.ALL })
    public INDHolder getINDHolder() {
        return iNDHolder;
    }

    /**
     * Sets the iND holder.
     *
     * @param holder the new iND holder
     */
    public void setINDHolder(INDHolder holder) {
        iNDHolder = holder;
        holderName = holder.getName();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    public boolean isActive(){
        return status == null || status.equals(ActiveInactiveStatus.AC.getCode());
    }

    @Transient
    public boolean isInactive(){
        return status != null && status.equals(ActiveInactiveStatus.IN.getCode());
    }

    //    /**
//     * Gets the study agent ind associations.
//     *
//     * @return the study agent ind associations
//     */
//    @OneToMany(mappedBy = "investigationalNewDrug", orphanRemoval = true)
//    @Cascade( { CascadeType.DELETE  })
//    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
//    public List<StudyAgentINDAssociation> getStudyAgentINDAssociations() {
//        return studyAgentINDAssociations;
//    }
//
//    /**
//     * Sets the study agent ind associations.
//     *
//     * @param studyAgentINDAssociations the new study agent ind associations
//     */
//    public void setStudyAgentINDAssociations(List<StudyAgentINDAssociation> studyAgentINDAssociations) {
//        this.studyAgentINDAssociations = studyAgentINDAssociations;
//    }

    /**
     * Gets the holder name.
     *
     * @return the holder name
     */
    @Transient
    public String getHolderName() {
        if (holderName != null) return holderName;
        return (iNDHolder != null) ? iNDHolder.getName() : "";
    }

    /**
     * Sets the holder name.
     *
     * @param holderName the new holder name
     */
    @Transient
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /**
     * Gets the str ind no.
     *
     * @return the str ind no
     */
    @Transient
    public String getStrINDNo() {
    	if(indNumber == null) return "";
        return String.valueOf(indNumber);
    }

    /**
     * Sets the str ind no.
     *
     * @param strINDNo the new str ind no
     */
    public void setStrINDNo(String strINDNo) {
        if (strINDNo.equals(STRING_CTEP_IND)) indNumber = CTEP_IND;
        else if (strINDNo.equals(STRING_DCP_IND)) indNumber = DCP_IND;
        else {
            int n = 0;
            try {
                n = Integer.parseInt(strINDNo);
            } catch (NumberFormatException e) {
                log.error("Unrecognized String came as an Agent #: " + strINDNo);
            }
            indNumber = new Integer(n);
        };
    }
    
    /**
     * Gets the number and holder name.
     *
     * @return the number and holder name
     */
    @Transient
    public String getNumberAndHolderName(){
    	return getStrINDNo() + " : " + getHolderName();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InvestigationalNewDrug[" + indNumber + " : " + getHolderName() + "]";
    }
}
