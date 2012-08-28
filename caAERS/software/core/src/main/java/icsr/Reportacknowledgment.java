
package icsr;

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
 *         &lt;element ref="{}safetyreportid"/>
 *         &lt;element ref="{}safetyreportversion" minOccurs="0"/>
 *         &lt;element ref="{}localreportnumb" minOccurs="0"/>
 *         &lt;element ref="{}authoritynumb" minOccurs="0"/>
 *         &lt;element ref="{}companynumb" minOccurs="0"/>
 *         &lt;element ref="{}receiptdateformat" minOccurs="0"/>
 *         &lt;element ref="{}receiptdate" minOccurs="0"/>
 *         &lt;element ref="{}reportacknowledgmentcode"/>
 *         &lt;element ref="{}errormessagecomment" minOccurs="0"/>
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
    "safetyreportid",
    "safetyreportversion",
    "localreportnumb",
    "authoritynumb",
    "companynumb",
    "receiptdateformat",
    "receiptdate",
    "reportacknowledgmentcode",
    "errormessagecomment"
})
@XmlRootElement(name = "reportacknowledgment")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Reportacknowledgment {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Safetyreportid safetyreportid;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Safetyreportversion safetyreportversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Localreportnumb localreportnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Authoritynumb authoritynumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Companynumb companynumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receiptdateformat receiptdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receiptdate receiptdate;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reportacknowledgmentcode reportacknowledgmentcode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Errormessagecomment errormessagecomment;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the safetyreportid property.
     * 
     * @return
     *     possible object is
     *     {@link Safetyreportid }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Safetyreportid getSafetyreportid() {
        return safetyreportid;
    }

    /**
     * Sets the value of the safetyreportid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Safetyreportid }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSafetyreportid(Safetyreportid value) {
        this.safetyreportid = value;
    }

    /**
     * Gets the value of the safetyreportversion property.
     * 
     * @return
     *     possible object is
     *     {@link Safetyreportversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Safetyreportversion getSafetyreportversion() {
        return safetyreportversion;
    }

    /**
     * Sets the value of the safetyreportversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Safetyreportversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSafetyreportversion(Safetyreportversion value) {
        this.safetyreportversion = value;
    }

    /**
     * Gets the value of the localreportnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Localreportnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Localreportnumb getLocalreportnumb() {
        return localreportnumb;
    }

    /**
     * Sets the value of the localreportnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Localreportnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLocalreportnumb(Localreportnumb value) {
        this.localreportnumb = value;
    }

    /**
     * Gets the value of the authoritynumb property.
     * 
     * @return
     *     possible object is
     *     {@link Authoritynumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Authoritynumb getAuthoritynumb() {
        return authoritynumb;
    }

    /**
     * Sets the value of the authoritynumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Authoritynumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAuthoritynumb(Authoritynumb value) {
        this.authoritynumb = value;
    }

    /**
     * Gets the value of the companynumb property.
     * 
     * @return
     *     possible object is
     *     {@link Companynumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Companynumb getCompanynumb() {
        return companynumb;
    }

    /**
     * Sets the value of the companynumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Companynumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCompanynumb(Companynumb value) {
        this.companynumb = value;
    }

    /**
     * Gets the value of the receiptdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Receiptdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Receiptdateformat getReceiptdateformat() {
        return receiptdateformat;
    }

    /**
     * Sets the value of the receiptdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receiptdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReceiptdateformat(Receiptdateformat value) {
        this.receiptdateformat = value;
    }

    /**
     * Gets the value of the receiptdate property.
     * 
     * @return
     *     possible object is
     *     {@link Receiptdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Receiptdate getReceiptdate() {
        return receiptdate;
    }

    /**
     * Sets the value of the receiptdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receiptdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReceiptdate(Receiptdate value) {
        this.receiptdate = value;
    }

    /**
     * Gets the value of the reportacknowledgmentcode property.
     * 
     * @return
     *     possible object is
     *     {@link Reportacknowledgmentcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reportacknowledgmentcode getReportacknowledgmentcode() {
        return reportacknowledgmentcode;
    }

    /**
     * Sets the value of the reportacknowledgmentcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reportacknowledgmentcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReportacknowledgmentcode(Reportacknowledgmentcode value) {
        this.reportacknowledgmentcode = value;
    }

    /**
     * Gets the value of the errormessagecomment property.
     * 
     * @return
     *     possible object is
     *     {@link Errormessagecomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Errormessagecomment getErrormessagecomment() {
        return errormessagecomment;
    }

    /**
     * Sets the value of the errormessagecomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Errormessagecomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setErrormessagecomment(Errormessagecomment value) {
        this.errormessagecomment = value;
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
