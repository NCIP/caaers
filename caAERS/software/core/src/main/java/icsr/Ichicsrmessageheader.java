
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
 *         &lt;element ref="{}messagetype"/>
 *         &lt;element ref="{}messageformatversion"/>
 *         &lt;element ref="{}messageformatrelease"/>
 *         &lt;element ref="{}messagenumb"/>
 *         &lt;element ref="{}messagesenderidentifier"/>
 *         &lt;element ref="{}messagereceiveridentifier"/>
 *         &lt;element ref="{}messagedateformat"/>
 *         &lt;element ref="{}messagedate"/>
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
    "messagetype",
    "messageformatversion",
    "messageformatrelease",
    "messagenumb",
    "messagesenderidentifier",
    "messagereceiveridentifier",
    "messagedateformat",
    "messagedate"
})
@XmlRootElement(name = "ichicsrmessageheader")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Ichicsrmessageheader {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagetype messagetype;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messageformatversion messageformatversion;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messageformatrelease messageformatrelease;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagenumb messagenumb;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagesenderidentifier messagesenderidentifier;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagereceiveridentifier messagereceiveridentifier;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagedateformat messagedateformat;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Messagedate messagedate;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the messagetype property.
     * 
     * @return
     *     possible object is
     *     {@link Messagetype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagetype getMessagetype() {
        return messagetype;
    }

    /**
     * Sets the value of the messagetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagetype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagetype(Messagetype value) {
        this.messagetype = value;
    }

    /**
     * Gets the value of the messageformatversion property.
     * 
     * @return
     *     possible object is
     *     {@link Messageformatversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messageformatversion getMessageformatversion() {
        return messageformatversion;
    }

    /**
     * Sets the value of the messageformatversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messageformatversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessageformatversion(Messageformatversion value) {
        this.messageformatversion = value;
    }

    /**
     * Gets the value of the messageformatrelease property.
     * 
     * @return
     *     possible object is
     *     {@link Messageformatrelease }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messageformatrelease getMessageformatrelease() {
        return messageformatrelease;
    }

    /**
     * Sets the value of the messageformatrelease property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messageformatrelease }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessageformatrelease(Messageformatrelease value) {
        this.messageformatrelease = value;
    }

    /**
     * Gets the value of the messagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Messagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagenumb getMessagenumb() {
        return messagenumb;
    }

    /**
     * Sets the value of the messagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagenumb(Messagenumb value) {
        this.messagenumb = value;
    }

    /**
     * Gets the value of the messagesenderidentifier property.
     * 
     * @return
     *     possible object is
     *     {@link Messagesenderidentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagesenderidentifier getMessagesenderidentifier() {
        return messagesenderidentifier;
    }

    /**
     * Sets the value of the messagesenderidentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagesenderidentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagesenderidentifier(Messagesenderidentifier value) {
        this.messagesenderidentifier = value;
    }

    /**
     * Gets the value of the messagereceiveridentifier property.
     * 
     * @return
     *     possible object is
     *     {@link Messagereceiveridentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagereceiveridentifier getMessagereceiveridentifier() {
        return messagereceiveridentifier;
    }

    /**
     * Sets the value of the messagereceiveridentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagereceiveridentifier }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagereceiveridentifier(Messagereceiveridentifier value) {
        this.messagereceiveridentifier = value;
    }

    /**
     * Gets the value of the messagedateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Messagedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagedateformat getMessagedateformat() {
        return messagedateformat;
    }

    /**
     * Sets the value of the messagedateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagedateformat(Messagedateformat value) {
        this.messagedateformat = value;
    }

    /**
     * Gets the value of the messagedate property.
     * 
     * @return
     *     possible object is
     *     {@link Messagedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Messagedate getMessagedate() {
        return messagedate;
    }

    /**
     * Sets the value of the messagedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messagedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMessagedate(Messagedate value) {
        this.messagedate = value;
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
