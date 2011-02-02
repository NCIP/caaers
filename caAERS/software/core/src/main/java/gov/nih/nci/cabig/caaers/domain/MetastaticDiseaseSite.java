package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the MetastaticDiseaseSite domain object associated with the Adverse event
 * report.
 *
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@Entity
@Table(name = "metastatic_disease_sites")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_metastatic_disease_site_id")})
// TODO: this sort of class might be better as a component rather than another domain object
public class MetastaticDiseaseSite extends AbstractMutableDomainObject {
    
    /** The other site. */
    private String otherSite;

    /** The coded site. */
    private AnatomicSite codedSite;

    /**
     * Gets the other site.
     *
     * @return the other site
     */
    public String getOtherSite() {
        return otherSite;
    }

    /**
     * Sets the other site.
     *
     * @param otherSite the new other site
     */
    public void setOtherSite(String otherSite) {
        this.otherSite = otherSite;
    }

    /**
     * Gets the coded site.
     *
     * @return the coded site
     */
    @OneToOne
    @JoinColumn(name = "coded_site_id")
    //@Cascade(value = {CascadeType.ALL})
    public AnatomicSite getCodedSite() {
        return codedSite;
    }

    /**
     * Sets the coded site.
     *
     * @param codedSite the new coded site
     */
    public void setCodedSite(AnatomicSite codedSite) {
        this.codedSite = codedSite;
    }

    /**
     * Equals.
     *
     * @param codedSite the coded site
     * @param otherSite the other site
     * @return true, if successful
     */
    public boolean equals( AnatomicSite codedSite,String otherSite){
    	return StringUtils.equals(this.otherSite, otherSite) && ObjectUtils.equals(codedSite, this.codedSite);
    }
    
    ///OBJECT METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MetastaticDiseaseSite other = (MetastaticDiseaseSite) obj;
        if (codedSite == null) {
            if (other.codedSite != null)
                return false;
        } else if (!codedSite.equals(other.codedSite))
            return false;
        if (otherSite == null) {
            if (other.otherSite != null)
                return false;
        } else if (!otherSite.equals(other.otherSite))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((codedSite == null) ? 0 : codedSite.hashCode());
        result = prime * result
                + ((otherSite == null) ? 0 : otherSite.hashCode());
        return result;
    }


    /**
     * Creates the report metastatic disease site.
     *
     * @param studyParticipantMetastaticDiseaseSite the study participant metastatic disease site
     * @return the metastatic disease site
     */
    public static MetastaticDiseaseSite createReportMetastaticDiseaseSite(StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite) {
        if (studyParticipantMetastaticDiseaseSite != null) {
            MetastaticDiseaseSite metastaticDiseaseSite = copyBasicProperties(studyParticipantMetastaticDiseaseSite);

            return metastaticDiseaseSite;
        }
        return null;

    }

    /**
     * Copy basic properties.
     *
     * @param object the object
     * @return the metastatic disease site
     */
    private static MetastaticDiseaseSite copyBasicProperties(Object object) {
        MetastaticDiseaseSite metastaticDiseaseSite = new MetastaticDiseaseSite();
        BeanUtils.copyProperties(object, metastaticDiseaseSite, new String[]{"id", "gridId", "version"});
        return metastaticDiseaseSite;
    }

    /**
     * Copy.
     *
     * @return the metastatic disease site
     */
    public MetastaticDiseaseSite copy() {
        return copyBasicProperties(this);
    }
}
