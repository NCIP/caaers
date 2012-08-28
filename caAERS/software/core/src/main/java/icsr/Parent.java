
package icsr;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}parentidentification" minOccurs="0"/>
 *         &lt;element ref="{}parentbirthdateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentbirthdate" minOccurs="0"/>
 *         &lt;element ref="{}parentage" minOccurs="0"/>
 *         &lt;element ref="{}parentageunit" minOccurs="0"/>
 *         &lt;element ref="{}parentlastmenstrualdateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentlastmenstrualdate" minOccurs="0"/>
 *         &lt;element ref="{}parentweight" minOccurs="0"/>
 *         &lt;element ref="{}parentheight" minOccurs="0"/>
 *         &lt;element ref="{}parentsex" minOccurs="0"/>
 *         &lt;element ref="{}parentmedicalrelevanttext" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element ref="{}parentmedicalhistoryepisode" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}parentpastdrugtherapy" maxOccurs="unbounded" minOccurs="0"/>
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
    "parentidentification",
    "parentbirthdateformat",
    "parentbirthdate",
    "parentage",
    "parentageunit",
    "parentlastmenstrualdateformat",
    "parentlastmenstrualdate",
    "parentweight",
    "parentheight",
    "parentsex",
    "parentmedicalrelevanttext",
    "parentmedicalhistoryepisode",
    "parentpastdrugtherapy"
})
@XmlRootElement(name = "parent")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Parent {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentidentification parentidentification;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentbirthdateformat parentbirthdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentbirthdate parentbirthdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentage parentage;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentageunit parentageunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentlastmenstrualdateformat parentlastmenstrualdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentlastmenstrualdate parentlastmenstrualdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentweight parentweight;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentheight parentheight;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentsex parentsex;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentmedicalrelevanttext parentmedicalrelevanttext;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Parentmedicalhistoryepisode> parentmedicalhistoryepisode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Parentpastdrugtherapy> parentpastdrugtherapy;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the parentidentification property.
     * 
     * @return
     *     possible object is
     *     {@link Parentidentification }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentidentification getParentidentification() {
        return parentidentification;
    }

    /**
     * Sets the value of the parentidentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentidentification }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentidentification(Parentidentification value) {
        this.parentidentification = value;
    }

    /**
     * Gets the value of the parentbirthdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentbirthdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentbirthdateformat getParentbirthdateformat() {
        return parentbirthdateformat;
    }

    /**
     * Sets the value of the parentbirthdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentbirthdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentbirthdateformat(Parentbirthdateformat value) {
        this.parentbirthdateformat = value;
    }

    /**
     * Gets the value of the parentbirthdate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentbirthdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentbirthdate getParentbirthdate() {
        return parentbirthdate;
    }

    /**
     * Sets the value of the parentbirthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentbirthdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentbirthdate(Parentbirthdate value) {
        this.parentbirthdate = value;
    }

    /**
     * Gets the value of the parentage property.
     * 
     * @return
     *     possible object is
     *     {@link Parentage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentage getParentage() {
        return parentage;
    }

    /**
     * Sets the value of the parentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentage(Parentage value) {
        this.parentage = value;
    }

    /**
     * Gets the value of the parentageunit property.
     * 
     * @return
     *     possible object is
     *     {@link Parentageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentageunit getParentageunit() {
        return parentageunit;
    }

    /**
     * Sets the value of the parentageunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentageunit(Parentageunit value) {
        this.parentageunit = value;
    }

    /**
     * Gets the value of the parentlastmenstrualdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentlastmenstrualdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentlastmenstrualdateformat getParentlastmenstrualdateformat() {
        return parentlastmenstrualdateformat;
    }

    /**
     * Sets the value of the parentlastmenstrualdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentlastmenstrualdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentlastmenstrualdateformat(Parentlastmenstrualdateformat value) {
        this.parentlastmenstrualdateformat = value;
    }

    /**
     * Gets the value of the parentlastmenstrualdate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentlastmenstrualdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentlastmenstrualdate getParentlastmenstrualdate() {
        return parentlastmenstrualdate;
    }

    /**
     * Sets the value of the parentlastmenstrualdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentlastmenstrualdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentlastmenstrualdate(Parentlastmenstrualdate value) {
        this.parentlastmenstrualdate = value;
    }

    /**
     * Gets the value of the parentweight property.
     * 
     * @return
     *     possible object is
     *     {@link Parentweight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentweight getParentweight() {
        return parentweight;
    }

    /**
     * Sets the value of the parentweight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentweight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentweight(Parentweight value) {
        this.parentweight = value;
    }

    /**
     * Gets the value of the parentheight property.
     * 
     * @return
     *     possible object is
     *     {@link Parentheight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentheight getParentheight() {
        return parentheight;
    }

    /**
     * Sets the value of the parentheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentheight }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentheight(Parentheight value) {
        this.parentheight = value;
    }

    /**
     * Gets the value of the parentsex property.
     * 
     * @return
     *     possible object is
     *     {@link Parentsex }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentsex getParentsex() {
        return parentsex;
    }

    /**
     * Sets the value of the parentsex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentsex }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentsex(Parentsex value) {
        this.parentsex = value;
    }

    /**
     * Gets the value of the parentmedicalrelevanttext property.
     * 
     * @return
     *     possible object is
     *     {@link Parentmedicalrelevanttext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentmedicalrelevanttext getParentmedicalrelevanttext() {
        return parentmedicalrelevanttext;
    }

    /**
     * Sets the value of the parentmedicalrelevanttext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentmedicalrelevanttext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentmedicalrelevanttext(Parentmedicalrelevanttext value) {
        this.parentmedicalrelevanttext = value;
    }

    /**
     * Gets the value of the parentmedicalhistoryepisode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentmedicalhistoryepisode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentmedicalhistoryepisode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parentmedicalhistoryepisode }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Parentmedicalhistoryepisode> getParentmedicalhistoryepisode() {
        if (parentmedicalhistoryepisode == null) {
            parentmedicalhistoryepisode = new ArrayList<Parentmedicalhistoryepisode>();
        }
        return this.parentmedicalhistoryepisode;
    }

    /**
     * Gets the value of the parentpastdrugtherapy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentpastdrugtherapy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentpastdrugtherapy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parentpastdrugtherapy }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Parentpastdrugtherapy> getParentpastdrugtherapy() {
        if (parentpastdrugtherapy == null) {
            parentpastdrugtherapy = new ArrayList<Parentpastdrugtherapy>();
        }
        return this.parentpastdrugtherapy;
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
