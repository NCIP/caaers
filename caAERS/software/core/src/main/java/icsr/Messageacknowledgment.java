
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
 *         &lt;element ref="{}icsrmessagenumb"/>
 *         &lt;element ref="{}localmessagenumb" minOccurs="0"/>
 *         &lt;element ref="{}icsrmessagesenderidentifier"/>
 *         &lt;element ref="{}icsrmessagereceiveridentifier"/>
 *         &lt;element ref="{}icsrmessagedateformat"/>
 *         &lt;element ref="{}icsrmessagedate"/>
 *         &lt;element ref="{}transmissionacknowledgmentcode"/>
 *         &lt;element ref="{}parsingerrormessage" minOccurs="0"/>
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
    "icsrmessagenumb",
    "localmessagenumb",
    "icsrmessagesenderidentifier",
    "icsrmessagereceiveridentifier",
    "icsrmessagedateformat",
    "icsrmessagedate",
    "transmissionacknowledgmentcode",
    "parsingerrormessage"
})
@XmlRootElement(name = "messageacknowledgment")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Messageacknowledgment {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Icsrmessagenumb icsrmessagenumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Localmessagenumb localmessagenumb;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Icsrmessagesenderidentifier icsrmessagesenderidentifier;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Icsrmessagereceiveridentifier icsrmessagereceiveridentifier;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Icsrmessagedateformat icsrmessagedateformat;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Icsrmessagedate icsrmessagedate;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Transmissionacknowledgmentcode transmissionacknowledgmentcode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Parsingerrormessage parsingerrormessage;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the icsrmessagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Icsrmessagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Icsrmessagenumb getIcsrmessagenumb() {
        return icsrmessagenumb;
    }

    /**
     * Sets the value of the icsrmessagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icsrmessagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setIcsrmessagenumb(Icsrmessagenumb value) {
        this.icsrmessagenumb = value;
    }

    /**
     * Gets the value of the localmessagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Localmessagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Localmessagenumb getLocalmessagenumb() {
        return localmessagenumb;
    }

    /**
     * Sets the value of the localmessagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Localmessagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLocalmessagenumb(Localmessagenumb value) {
        this.localmessagenumb = value;
    }

    /**
     * Gets the value of the icsrmessagesenderidentifier property.
     * 
     * @return
     *     possible object is
     *     {@link Icsrmessagesenderidentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Icsrmessagesenderidentifier getIcsrmessagesenderidentifier() {
        return icsrmessagesenderidentifier;
    }

    /**
     * Sets the value of the icsrmessagesenderidentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icsrmessagesenderidentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setIcsrmessagesenderidentifier(Icsrmessagesenderidentifier value) {
        this.icsrmessagesenderidentifier = value;
    }

    /**
     * Gets the value of the icsrmessagereceiveridentifier property.
     * 
     * @return
     *     possible object is
     *     {@link Icsrmessagereceiveridentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Icsrmessagereceiveridentifier getIcsrmessagereceiveridentifier() {
        return icsrmessagereceiveridentifier;
    }

    /**
     * Sets the value of the icsrmessagereceiveridentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icsrmessagereceiveridentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setIcsrmessagereceiveridentifier(Icsrmessagereceiveridentifier value) {
        this.icsrmessagereceiveridentifier = value;
    }

    /**
     * Gets the value of the icsrmessagedateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Icsrmessagedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Icsrmessagedateformat getIcsrmessagedateformat() {
        return icsrmessagedateformat;
    }

    /**
     * Sets the value of the icsrmessagedateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icsrmessagedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setIcsrmessagedateformat(Icsrmessagedateformat value) {
        this.icsrmessagedateformat = value;
    }

    /**
     * Gets the value of the icsrmessagedate property.
     * 
     * @return
     *     possible object is
     *     {@link Icsrmessagedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Icsrmessagedate getIcsrmessagedate() {
        return icsrmessagedate;
    }

    /**
     * Sets the value of the icsrmessagedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icsrmessagedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setIcsrmessagedate(Icsrmessagedate value) {
        this.icsrmessagedate = value;
    }

    /**
     * Gets the value of the transmissionacknowledgmentcode property.
     * 
     * @return
     *     possible object is
     *     {@link Transmissionacknowledgmentcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Transmissionacknowledgmentcode getTransmissionacknowledgmentcode() {
        return transmissionacknowledgmentcode;
    }

    /**
     * Sets the value of the transmissionacknowledgmentcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transmissionacknowledgmentcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTransmissionacknowledgmentcode(Transmissionacknowledgmentcode value) {
        this.transmissionacknowledgmentcode = value;
    }

    /**
     * Gets the value of the parsingerrormessage property.
     * 
     * @return
     *     possible object is
     *     {@link Parsingerrormessage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Parsingerrormessage getParsingerrormessage() {
        return parsingerrormessage;
    }

    /**
     * Sets the value of the parsingerrormessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parsingerrormessage }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setParsingerrormessage(Parsingerrormessage value) {
        this.parsingerrormessage = value;
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
