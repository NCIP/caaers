
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
 *         &lt;element ref="{}parentdrugname" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugstartdateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugstartdate" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugenddateformat" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugenddate" minOccurs="0"/>
 *         &lt;element ref="{}parentdrgindicationmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugindication" minOccurs="0"/>
 *         &lt;element ref="{}parentdrgreactionmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}parentdrugreaction" minOccurs="0"/>
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
    "parentdrugname",
    "parentdrugstartdateformat",
    "parentdrugstartdate",
    "parentdrugenddateformat",
    "parentdrugenddate",
    "parentdrgindicationmeddraversion",
    "parentdrugindication",
    "parentdrgreactionmeddraversion",
    "parentdrugreaction"
})
@XmlRootElement(name = "parentpastdrugtherapy")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Parentpastdrugtherapy {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugname parentdrugname;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugstartdateformat parentdrugstartdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugstartdate parentdrugstartdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugenddateformat parentdrugenddateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugenddate parentdrugenddate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrgindicationmeddraversion parentdrgindicationmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugindication parentdrugindication;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrgreactionmeddraversion parentdrgreactionmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parentdrugreaction parentdrugreaction;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the parentdrugname property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugname getParentdrugname() {
        return parentdrugname;
    }

    /**
     * Sets the value of the parentdrugname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugname(Parentdrugname value) {
        this.parentdrugname = value;
    }

    /**
     * Gets the value of the parentdrugstartdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugstartdateformat getParentdrugstartdateformat() {
        return parentdrugstartdateformat;
    }

    /**
     * Sets the value of the parentdrugstartdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugstartdateformat(Parentdrugstartdateformat value) {
        this.parentdrugstartdateformat = value;
    }

    /**
     * Gets the value of the parentdrugstartdate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugstartdate getParentdrugstartdate() {
        return parentdrugstartdate;
    }

    /**
     * Sets the value of the parentdrugstartdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugstartdate(Parentdrugstartdate value) {
        this.parentdrugstartdate = value;
    }

    /**
     * Gets the value of the parentdrugenddateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugenddateformat getParentdrugenddateformat() {
        return parentdrugenddateformat;
    }

    /**
     * Sets the value of the parentdrugenddateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugenddateformat(Parentdrugenddateformat value) {
        this.parentdrugenddateformat = value;
    }

    /**
     * Gets the value of the parentdrugenddate property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugenddate getParentdrugenddate() {
        return parentdrugenddate;
    }

    /**
     * Sets the value of the parentdrugenddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugenddate(Parentdrugenddate value) {
        this.parentdrugenddate = value;
    }

    /**
     * Gets the value of the parentdrgindicationmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrgindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrgindicationmeddraversion getParentdrgindicationmeddraversion() {
        return parentdrgindicationmeddraversion;
    }

    /**
     * Sets the value of the parentdrgindicationmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrgindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrgindicationmeddraversion(Parentdrgindicationmeddraversion value) {
        this.parentdrgindicationmeddraversion = value;
    }

    /**
     * Gets the value of the parentdrugindication property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugindication getParentdrugindication() {
        return parentdrugindication;
    }

    /**
     * Sets the value of the parentdrugindication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugindication(Parentdrugindication value) {
        this.parentdrugindication = value;
    }

    /**
     * Gets the value of the parentdrgreactionmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrgreactionmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrgreactionmeddraversion getParentdrgreactionmeddraversion() {
        return parentdrgreactionmeddraversion;
    }

    /**
     * Sets the value of the parentdrgreactionmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrgreactionmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrgreactionmeddraversion(Parentdrgreactionmeddraversion value) {
        this.parentdrgreactionmeddraversion = value;
    }

    /**
     * Gets the value of the parentdrugreaction property.
     * 
     * @return
     *     possible object is
     *     {@link Parentdrugreaction }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parentdrugreaction getParentdrugreaction() {
        return parentdrugreaction;
    }

    /**
     * Sets the value of the parentdrugreaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parentdrugreaction }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParentdrugreaction(Parentdrugreaction value) {
        this.parentdrugreaction = value;
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
