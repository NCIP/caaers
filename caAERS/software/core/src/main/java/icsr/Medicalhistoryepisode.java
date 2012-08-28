
package icsr;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}patientepisodenamemeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}patientepisodename" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalstartdateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalstartdate" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalcontinue" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalenddateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalenddate" minOccurs="0"/>
 *         &lt;element ref="{}patientmedicalcomment" minOccurs="0"/>
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
    "patientepisodenamemeddraversion",
    "patientepisodename",
    "patientmedicalstartdateformat",
    "patientmedicalstartdate",
    "patientmedicalcontinue",
    "patientmedicalenddateformat",
    "patientmedicalenddate",
    "patientmedicalcomment"
})
@XmlRootElement(name = "medicalhistoryepisode")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Medicalhistoryepisode {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientepisodenamemeddraversion patientepisodenamemeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientepisodename patientepisodename;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalstartdateformat patientmedicalstartdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalstartdate patientmedicalstartdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalcontinue patientmedicalcontinue;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalenddateformat patientmedicalenddateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalenddate patientmedicalenddate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientmedicalcomment patientmedicalcomment;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the patientepisodenamemeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Patientepisodenamemeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientepisodenamemeddraversion getPatientepisodenamemeddraversion() {
        return patientepisodenamemeddraversion;
    }

    /**
     * Sets the value of the patientepisodenamemeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientepisodenamemeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientepisodenamemeddraversion(Patientepisodenamemeddraversion value) {
        this.patientepisodenamemeddraversion = value;
    }

    /**
     * Gets the value of the patientepisodename property.
     * 
     * @return
     *     possible object is
     *     {@link Patientepisodename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientepisodename getPatientepisodename() {
        return patientepisodename;
    }

    /**
     * Sets the value of the patientepisodename property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientepisodename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientepisodename(Patientepisodename value) {
        this.patientepisodename = value;
    }

    /**
     * Gets the value of the patientmedicalstartdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalstartdateformat getPatientmedicalstartdateformat() {
        return patientmedicalstartdateformat;
    }

    /**
     * Sets the value of the patientmedicalstartdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalstartdateformat(Patientmedicalstartdateformat value) {
        this.patientmedicalstartdateformat = value;
    }

    /**
     * Gets the value of the patientmedicalstartdate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalstartdate getPatientmedicalstartdate() {
        return patientmedicalstartdate;
    }

    /**
     * Sets the value of the patientmedicalstartdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalstartdate(Patientmedicalstartdate value) {
        this.patientmedicalstartdate = value;
    }

    /**
     * Gets the value of the patientmedicalcontinue property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalcontinue }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalcontinue getPatientmedicalcontinue() {
        return patientmedicalcontinue;
    }

    /**
     * Sets the value of the patientmedicalcontinue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalcontinue }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalcontinue(Patientmedicalcontinue value) {
        this.patientmedicalcontinue = value;
    }

    /**
     * Gets the value of the patientmedicalenddateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalenddateformat getPatientmedicalenddateformat() {
        return patientmedicalenddateformat;
    }

    /**
     * Sets the value of the patientmedicalenddateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalenddateformat(Patientmedicalenddateformat value) {
        this.patientmedicalenddateformat = value;
    }

    /**
     * Gets the value of the patientmedicalenddate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalenddate getPatientmedicalenddate() {
        return patientmedicalenddate;
    }

    /**
     * Sets the value of the patientmedicalenddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalenddate(Patientmedicalenddate value) {
        this.patientmedicalenddate = value;
    }

    /**
     * Gets the value of the patientmedicalcomment property.
     * 
     * @return
     *     possible object is
     *     {@link Patientmedicalcomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientmedicalcomment getPatientmedicalcomment() {
        return patientmedicalcomment;
    }

    /**
     * Sets the value of the patientmedicalcomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientmedicalcomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientmedicalcomment(Patientmedicalcomment value) {
        this.patientmedicalcomment = value;
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
