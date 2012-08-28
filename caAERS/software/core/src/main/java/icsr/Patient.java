
package icsr;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}patientinitial" minOccurs="0"/>
 *         &lt;element ref="{}patientgpmedicalrecordnumb" minOccurs="0"/>
 *         &lt;element ref="{}patientspecialistrecordnumb" minOccurs="0"/>
 *         &lt;element ref="{}patienthospitalrecordnumb" minOccurs="0"/>
 *         &lt;element ref="{}patientinvestigationnumb" minOccurs="0"/>
 *         &lt;element ref="{}patientbirthdateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientbirthdate" minOccurs="0"/>
 *         &lt;element ref="{}patientonsetage" minOccurs="0"/>
 *         &lt;element ref="{}patientonsetageunit" minOccurs="0"/>
 *         &lt;element ref="{}gestationperiod" minOccurs="0"/>
 *         &lt;element ref="{}gestationperiodunit" minOccurs="0"/>
 *         &lt;element ref="{}patientagegroup" minOccurs="0"/>
 *         &lt;element ref="{}patientweight" minOccurs="0"/>
 *         &lt;element ref="{}patientheight" minOccurs="0"/>
 *         &lt;element ref="{}patientsex" minOccurs="0"/>
 *         &lt;element ref="{}lastmenstrualdateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientlastmenstrualdate" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalhistorytext" minOccurs="0"/>
 *         &lt;element ref="{}resultstestsprocedures" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element ref="{}medicalhistoryepisode" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}patientpastdrugtherapy" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}patientdeath" minOccurs="0"/>
 *           &lt;element ref="{}parent" minOccurs="0"/>
 *           &lt;element ref="{}reaction" maxOccurs="unbounded"/>
 *           &lt;element ref="{}test" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}drug" maxOccurs="unbounded"/>
 *           &lt;element ref="{}summary" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "patientinitial",
    "patientgpmedicalrecordnumb",
    "patientspecialistrecordnumb",
    "patienthospitalrecordnumb",
    "patientinvestigationnumb",
    "patientbirthdateformat",
    "patientbirthdate",
    "patientonsetage",
    "patientonsetageunit",
    "gestationperiod",
    "gestationperiodunit",
    "patientagegroup",
    "patientweight",
    "patientheight",
    "patientsex",
    "lastmenstrualdateformat",
    "patientlastmenstrualdate",
    "patientmedicalhistorytext",
    "resultstestsprocedures",
    "medicalhistoryepisode",
    "patientpastdrugtherapy",
    "patientdeath",
    "parent",
    "reaction",
    "test",
    "drug",
    "summary"
})
@XmlRootElement(name = "patient")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Patient {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientinitial patientinitial;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientgpmedicalrecordnumb patientgpmedicalrecordnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientspecialistrecordnumb patientspecialistrecordnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patienthospitalrecordnumb patienthospitalrecordnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientinvestigationnumb patientinvestigationnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientbirthdateformat patientbirthdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientbirthdate patientbirthdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientonsetage patientonsetage;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientonsetageunit patientonsetageunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Gestationperiod gestationperiod;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Gestationperiodunit gestationperiodunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientagegroup patientagegroup;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientweight patientweight;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientheight patientheight;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientsex patientsex;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Lastmenstrualdateformat lastmenstrualdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientlastmenstrualdate patientlastmenstrualdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalhistorytext patientmedicalhistorytext;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Resultstestsprocedures resultstestsprocedures;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Medicalhistoryepisode> medicalhistoryepisode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Patientpastdrugtherapy> patientpastdrugtherapy;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdeath patientdeath;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parent parent;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Reaction> reaction;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Test> test;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Drug> drug;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Summary summary;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the patientinitial property.
     * 
     * @return
     *     possible object is
     *     {@link Patientinitial }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientinitial getPatientinitial() {
        return patientinitial;
    }

    /**
     * Sets the value of the patientinitial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientinitial }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientinitial(Patientinitial value) {
        this.patientinitial = value;
    }

    /**
     * Gets the value of the patientgpmedicalrecordnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Patientgpmedicalrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientgpmedicalrecordnumb getPatientgpmedicalrecordnumb() {
        return patientgpmedicalrecordnumb;
    }

    /**
     * Sets the value of the patientgpmedicalrecordnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientgpmedicalrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientgpmedicalrecordnumb(Patientgpmedicalrecordnumb value) {
        this.patientgpmedicalrecordnumb = value;
    }

    /**
     * Gets the value of the patientspecialistrecordnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Patientspecialistrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientspecialistrecordnumb getPatientspecialistrecordnumb() {
        return patientspecialistrecordnumb;
    }

    /**
     * Sets the value of the patientspecialistrecordnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientspecialistrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientspecialistrecordnumb(Patientspecialistrecordnumb value) {
        this.patientspecialistrecordnumb = value;
    }

    /**
     * Gets the value of the patienthospitalrecordnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Patienthospitalrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patienthospitalrecordnumb getPatienthospitalrecordnumb() {
        return patienthospitalrecordnumb;
    }

    /**
     * Sets the value of the patienthospitalrecordnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patienthospitalrecordnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatienthospitalrecordnumb(Patienthospitalrecordnumb value) {
        this.patienthospitalrecordnumb = value;
    }

    /**
     * Gets the value of the patientinvestigationnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Patientinvestigationnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientinvestigationnumb getPatientinvestigationnumb() {
        return patientinvestigationnumb;
    }

    /**
     * Sets the value of the patientinvestigationnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientinvestigationnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientinvestigationnumb(Patientinvestigationnumb value) {
        this.patientinvestigationnumb = value;
    }

    /**
     * Gets the value of the patientbirthdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientbirthdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientbirthdateformat getPatientbirthdateformat() {
        return patientbirthdateformat;
    }

    /**
     * Sets the value of the patientbirthdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientbirthdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientbirthdateformat(Patientbirthdateformat value) {
        this.patientbirthdateformat = value;
    }

    /**
     * Gets the value of the patientbirthdate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientbirthdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientbirthdate getPatientbirthdate() {
        return patientbirthdate;
    }

    /**
     * Sets the value of the patientbirthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientbirthdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientbirthdate(Patientbirthdate value) {
        this.patientbirthdate = value;
    }

    /**
     * Gets the value of the patientonsetage property.
     * 
     * @return
     *     possible object is
     *     {@link Patientonsetage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientonsetage getPatientonsetage() {
        return patientonsetage;
    }

    /**
     * Sets the value of the patientonsetage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientonsetage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientonsetage(Patientonsetage value) {
        this.patientonsetage = value;
    }

    /**
     * Gets the value of the patientonsetageunit property.
     * 
     * @return
     *     possible object is
     *     {@link Patientonsetageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientonsetageunit getPatientonsetageunit() {
        return patientonsetageunit;
    }

    /**
     * Sets the value of the patientonsetageunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientonsetageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientonsetageunit(Patientonsetageunit value) {
        this.patientonsetageunit = value;
    }

    /**
     * Gets the value of the gestationperiod property.
     * 
     * @return
     *     possible object is
     *     {@link Gestationperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Gestationperiod getGestationperiod() {
        return gestationperiod;
    }

    /**
     * Sets the value of the gestationperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gestationperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setGestationperiod(Gestationperiod value) {
        this.gestationperiod = value;
    }

    /**
     * Gets the value of the gestationperiodunit property.
     * 
     * @return
     *     possible object is
     *     {@link Gestationperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Gestationperiodunit getGestationperiodunit() {
        return gestationperiodunit;
    }

    /**
     * Sets the value of the gestationperiodunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gestationperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setGestationperiodunit(Gestationperiodunit value) {
        this.gestationperiodunit = value;
    }

    /**
     * Gets the value of the patientagegroup property.
     * 
     * @return
     *     possible object is
     *     {@link Patientagegroup }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientagegroup getPatientagegroup() {
        return patientagegroup;
    }

    /**
     * Sets the value of the patientagegroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientagegroup }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientagegroup(Patientagegroup value) {
        this.patientagegroup = value;
    }

    /**
     * Gets the value of the patientweight property.
     * 
     * @return
     *     possible object is
     *     {@link Patientweight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientweight getPatientweight() {
        return patientweight;
    }

    /**
     * Sets the value of the patientweight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientweight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientweight(Patientweight value) {
        this.patientweight = value;
    }

    /**
     * Gets the value of the patientheight property.
     * 
     * @return
     *     possible object is
     *     {@link Patientheight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientheight getPatientheight() {
        return patientheight;
    }

    /**
     * Sets the value of the patientheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientheight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientheight(Patientheight value) {
        this.patientheight = value;
    }

    /**
     * Gets the value of the patientsex property.
     * 
     * @return
     *     possible object is
     *     {@link Patientsex }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientsex getPatientsex() {
        return patientsex;
    }

    /**
     * Sets the value of the patientsex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientsex }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientsex(Patientsex value) {
        this.patientsex = value;
    }

    /**
     * Gets the value of the lastmenstrualdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Lastmenstrualdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Lastmenstrualdateformat getLastmenstrualdateformat() {
        return lastmenstrualdateformat;
    }

    /**
     * Sets the value of the lastmenstrualdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lastmenstrualdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLastmenstrualdateformat(Lastmenstrualdateformat value) {
        this.lastmenstrualdateformat = value;
    }

    /**
     * Gets the value of the patientlastmenstrualdate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientlastmenstrualdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientlastmenstrualdate getPatientlastmenstrualdate() {
        return patientlastmenstrualdate;
    }

    /**
     * Sets the value of the patientlastmenstrualdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientlastmenstrualdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientlastmenstrualdate(Patientlastmenstrualdate value) {
        this.patientlastmenstrualdate = value;
    }

    /**
     * Gets the value of the patientmedicalhistorytext property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalhistorytext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalhistorytext getPatientmedicalhistorytext() {
        return patientmedicalhistorytext;
    }

    /**
     * Sets the value of the patientmedicalhistorytext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalhistorytext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalhistorytext(Patientmedicalhistorytext value) {
        this.patientmedicalhistorytext = value;
    }

    /**
     * Gets the value of the resultstestsprocedures property.
     * 
     * @return
     *     possible object is
     *     {@link Resultstestsprocedures }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Resultstestsprocedures getResultstestsprocedures() {
        return resultstestsprocedures;
    }

    /**
     * Sets the value of the resultstestsprocedures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultstestsprocedures }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setResultstestsprocedures(Resultstestsprocedures value) {
        this.resultstestsprocedures = value;
    }

    /**
     * Gets the value of the medicalhistoryepisode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the medicalhistoryepisode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedicalhistoryepisode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Medicalhistoryepisode }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Medicalhistoryepisode> getMedicalhistoryepisode() {
        if (medicalhistoryepisode == null) {
            medicalhistoryepisode = new ArrayList<Medicalhistoryepisode>();
        }
        return this.medicalhistoryepisode;
    }

    /**
     * Gets the value of the patientpastdrugtherapy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientpastdrugtherapy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientpastdrugtherapy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patientpastdrugtherapy }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Patientpastdrugtherapy> getPatientpastdrugtherapy() {
        if (patientpastdrugtherapy == null) {
            patientpastdrugtherapy = new ArrayList<Patientpastdrugtherapy>();
        }
        return this.patientpastdrugtherapy;
    }

    /**
     * Gets the value of the patientdeath property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdeath }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdeath getPatientdeath() {
        return patientdeath;
    }

    /**
     * Sets the value of the patientdeath property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdeath }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdeath(Patientdeath value) {
        this.patientdeath = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link Parent }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parent getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parent }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParent(Parent value) {
        this.parent = value;
    }

    /**
     * Gets the value of the reaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reaction }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Reaction> getReaction() {
        if (reaction == null) {
            reaction = new ArrayList<Reaction>();
        }
        return this.reaction;
    }

    /**
     * Gets the value of the test property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the test property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Test }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Test> getTest() {
        if (test == null) {
            test = new ArrayList<Test>();
        }
        return this.test;
    }

    /**
     * Gets the value of the drug property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drug property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrug().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drug }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Drug> getDrug() {
        if (drug == null) {
            drug = new ArrayList<Drug>();
        }
        return this.drug;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link Summary }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Summary getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Summary }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSummary(Summary value) {
        this.summary = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLang(String value) {
        this.lang = value;
    }

}
