
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
 *         &lt;element ref="{}patientdrugname" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugstartdateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugstartdate" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugenddateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugenddate" minOccurs="0"/>
 *         &lt;element ref="{}patientindicationmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugindication" minOccurs="0"/>
 *         &lt;element ref="{}patientdrgreactionmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}patientdrugreaction" minOccurs="0"/>
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
    "patientdrugname",
    "patientdrugstartdateformat",
    "patientdrugstartdate",
    "patientdrugenddateformat",
    "patientdrugenddate",
    "patientindicationmeddraversion",
    "patientdrugindication",
    "patientdrgreactionmeddraversion",
    "patientdrugreaction"
})
@XmlRootElement(name = "patientpastdrugtherapy")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Patientpastdrugtherapy {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugname patientdrugname;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugstartdateformat patientdrugstartdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugstartdate patientdrugstartdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugenddateformat patientdrugenddateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugenddate patientdrugenddate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientindicationmeddraversion patientindicationmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugindication patientdrugindication;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrgreactionmeddraversion patientdrgreactionmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdrugreaction patientdrugreaction;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the patientdrugname property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugname getPatientdrugname() {
        return patientdrugname;
    }

    /**
     * Sets the value of the patientdrugname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugname(Patientdrugname value) {
        this.patientdrugname = value;
    }

    /**
     * Gets the value of the patientdrugstartdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugstartdateformat getPatientdrugstartdateformat() {
        return patientdrugstartdateformat;
    }

    /**
     * Sets the value of the patientdrugstartdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugstartdateformat(Patientdrugstartdateformat value) {
        this.patientdrugstartdateformat = value;
    }

    /**
     * Gets the value of the patientdrugstartdate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugstartdate getPatientdrugstartdate() {
        return patientdrugstartdate;
    }

    /**
     * Sets the value of the patientdrugstartdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugstartdate(Patientdrugstartdate value) {
        this.patientdrugstartdate = value;
    }

    /**
     * Gets the value of the patientdrugenddateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugenddateformat getPatientdrugenddateformat() {
        return patientdrugenddateformat;
    }

    /**
     * Sets the value of the patientdrugenddateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugenddateformat(Patientdrugenddateformat value) {
        this.patientdrugenddateformat = value;
    }

    /**
     * Gets the value of the patientdrugenddate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugenddate getPatientdrugenddate() {
        return patientdrugenddate;
    }

    /**
     * Sets the value of the patientdrugenddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugenddate(Patientdrugenddate value) {
        this.patientdrugenddate = value;
    }

    /**
     * Gets the value of the patientindicationmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Patientindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientindicationmeddraversion getPatientindicationmeddraversion() {
        return patientindicationmeddraversion;
    }

    /**
     * Sets the value of the patientindicationmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientindicationmeddraversion(Patientindicationmeddraversion value) {
        this.patientindicationmeddraversion = value;
    }

    /**
     * Gets the value of the patientdrugindication property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugindication getPatientdrugindication() {
        return patientdrugindication;
    }

    /**
     * Sets the value of the patientdrugindication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugindication(Patientdrugindication value) {
        this.patientdrugindication = value;
    }

    /**
     * Gets the value of the patientdrgreactionmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrgreactionmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrgreactionmeddraversion getPatientdrgreactionmeddraversion() {
        return patientdrgreactionmeddraversion;
    }

    /**
     * Sets the value of the patientdrgreactionmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrgreactionmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrgreactionmeddraversion(Patientdrgreactionmeddraversion value) {
        this.patientdrgreactionmeddraversion = value;
    }

    /**
     * Gets the value of the patientdrugreaction property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdrugreaction }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdrugreaction getPatientdrugreaction() {
        return patientdrugreaction;
    }

    /**
     * Sets the value of the patientdrugreaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdrugreaction }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdrugreaction(Patientdrugreaction value) {
        this.patientdrugreaction = value;
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
