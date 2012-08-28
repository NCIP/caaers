
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
 *         &lt;element ref="{}parentmdepisodemeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalepisodename" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalstartdateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalstartdate" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalcontinue" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalenddateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalenddate" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalcomment" minOccurs="0"/>
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
    "parentmdepisodemeddraversion",
    "parentmedicalepisodename",
    "parentmedicalstartdateformat",
    "parentmedicalstartdate",
    "parentmedicalcontinue",
    "parentmedicalenddateformat",
    "parentmedicalenddate",
    "parentmedicalcomment"
})
@XmlRootElement(name = "parentmedicalhistoryepisode")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Parentmedicalhistoryepisode {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmdepisodemeddraversion parentmdepisodemeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalepisodename parentmedicalepisodename;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalstartdateformat parentmedicalstartdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalstartdate parentmedicalstartdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalcontinue parentmedicalcontinue;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalenddateformat parentmedicalenddateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalenddate parentmedicalenddate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalcomment parentmedicalcomment;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the parentmdepisodemeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmdepisodemeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmdepisodemeddraversion getParentmdepisodemeddraversion() {
        return parentmdepisodemeddraversion;
    }

    /**
     * Sets the value of the parentmdepisodemeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmdepisodemeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmdepisodemeddraversion(Parentmdepisodemeddraversion value) {
        this.parentmdepisodemeddraversion = value;
    }

    /**
     * Gets the value of the parentmedicalepisodename property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalepisodename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalepisodename getParentmedicalepisodename() {
        return parentmedicalepisodename;
    }

    /**
     * Sets the value of the parentmedicalepisodename property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalepisodename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalepisodename(Parentmedicalepisodename value) {
        this.parentmedicalepisodename = value;
    }

    /**
     * Gets the value of the parentmedicalstartdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalstartdateformat getParentmedicalstartdateformat() {
        return parentmedicalstartdateformat;
    }

    /**
     * Sets the value of the parentmedicalstartdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalstartdateformat(Parentmedicalstartdateformat value) {
        this.parentmedicalstartdateformat = value;
    }

    /**
     * Gets the value of the parentmedicalstartdate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalstartdate getParentmedicalstartdate() {
        return parentmedicalstartdate;
    }

    /**
     * Sets the value of the parentmedicalstartdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalstartdate(Parentmedicalstartdate value) {
        this.parentmedicalstartdate = value;
    }

    /**
     * Gets the value of the parentmedicalcontinue property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalcontinue }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalcontinue getParentmedicalcontinue() {
        return parentmedicalcontinue;
    }

    /**
     * Sets the value of the parentmedicalcontinue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalcontinue }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalcontinue(Parentmedicalcontinue value) {
        this.parentmedicalcontinue = value;
    }

    /**
     * Gets the value of the parentmedicalenddateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalenddateformat getParentmedicalenddateformat() {
        return parentmedicalenddateformat;
    }

    /**
     * Sets the value of the parentmedicalenddateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalenddateformat(Parentmedicalenddateformat value) {
        this.parentmedicalenddateformat = value;
    }

    /**
     * Gets the value of the parentmedicalenddate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalenddate getParentmedicalenddate() {
        return parentmedicalenddate;
    }

    /**
     * Sets the value of the parentmedicalenddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalenddate(Parentmedicalenddate value) {
        this.parentmedicalenddate = value;
    }

    /**
     * Gets the value of the parentmedicalcomment property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalcomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalcomment getParentmedicalcomment() {
        return parentmedicalcomment;
    }

    /**
     * Sets the value of the parentmedicalcomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalcomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalcomment(Parentmedicalcomment value) {
        this.parentmedicalcomment = value;
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
