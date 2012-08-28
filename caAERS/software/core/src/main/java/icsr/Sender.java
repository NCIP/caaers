
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
 *         &lt;element ref="{}sendertype" minOccurs="0"/>
 *         &lt;element ref="{}senderorganization" minOccurs="0"/>
 *         &lt;element ref="{}senderdepartment" minOccurs="0"/>
 *         &lt;element ref="{}sendertitle" minOccurs="0"/>
 *         &lt;element ref="{}sendergivename" minOccurs="0"/>
 *         &lt;element ref="{}sendermiddlename" minOccurs="0"/>
 *         &lt;element ref="{}senderfamilyname" minOccurs="0"/>
 *         &lt;element ref="{}senderstreetaddress" minOccurs="0"/>
 *         &lt;element ref="{}sendercity" minOccurs="0"/>
 *         &lt;element ref="{}senderstate" minOccurs="0"/>
 *         &lt;element ref="{}senderpostcode" minOccurs="0"/>
 *         &lt;element ref="{}sendercountrycode" minOccurs="0"/>
 *         &lt;element ref="{}sendertel" minOccurs="0"/>
 *         &lt;element ref="{}sendertelextension" minOccurs="0"/>
 *         &lt;element ref="{}sendertelcountrycode" minOccurs="0"/>
 *         &lt;element ref="{}senderfax" minOccurs="0"/>
 *         &lt;element ref="{}senderfaxextension" minOccurs="0"/>
 *         &lt;element ref="{}senderfaxcountrycode" minOccurs="0"/>
 *         &lt;element ref="{}senderemailaddress" minOccurs="0"/>
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
    "sendertype",
    "senderorganization",
    "senderdepartment",
    "sendertitle",
    "sendergivename",
    "sendermiddlename",
    "senderfamilyname",
    "senderstreetaddress",
    "sendercity",
    "senderstate",
    "senderpostcode",
    "sendercountrycode",
    "sendertel",
    "sendertelextension",
    "sendertelcountrycode",
    "senderfax",
    "senderfaxextension",
    "senderfaxcountrycode",
    "senderemailaddress"
})
@XmlRootElement(name = "sender")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Sender {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendertype sendertype;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderorganization senderorganization;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderdepartment senderdepartment;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendertitle sendertitle;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendergivename sendergivename;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendermiddlename sendermiddlename;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderfamilyname senderfamilyname;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderstreetaddress senderstreetaddress;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendercity sendercity;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderstate senderstate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderpostcode senderpostcode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendercountrycode sendercountrycode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendertel sendertel;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendertelextension sendertelextension;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendertelcountrycode sendertelcountrycode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderfax senderfax;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderfaxextension senderfaxextension;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderfaxcountrycode senderfaxcountrycode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderemailaddress senderemailaddress;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the sendertype property.
     * 
     * @return
     *     possible object is
     *     {@link Sendertype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendertype getSendertype() {
        return sendertype;
    }

    /**
     * Sets the value of the sendertype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendertype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendertype(Sendertype value) {
        this.sendertype = value;
    }

    /**
     * Gets the value of the senderorganization property.
     * 
     * @return
     *     possible object is
     *     {@link Senderorganization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderorganization getSenderorganization() {
        return senderorganization;
    }

    /**
     * Sets the value of the senderorganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderorganization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderorganization(Senderorganization value) {
        this.senderorganization = value;
    }

    /**
     * Gets the value of the senderdepartment property.
     * 
     * @return
     *     possible object is
     *     {@link Senderdepartment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderdepartment getSenderdepartment() {
        return senderdepartment;
    }

    /**
     * Sets the value of the senderdepartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderdepartment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderdepartment(Senderdepartment value) {
        this.senderdepartment = value;
    }

    /**
     * Gets the value of the sendertitle property.
     * 
     * @return
     *     possible object is
     *     {@link Sendertitle }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendertitle getSendertitle() {
        return sendertitle;
    }

    /**
     * Sets the value of the sendertitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendertitle }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendertitle(Sendertitle value) {
        this.sendertitle = value;
    }

    /**
     * Gets the value of the sendergivename property.
     * 
     * @return
     *     possible object is
     *     {@link Sendergivename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendergivename getSendergivename() {
        return sendergivename;
    }

    /**
     * Sets the value of the sendergivename property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendergivename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendergivename(Sendergivename value) {
        this.sendergivename = value;
    }

    /**
     * Gets the value of the sendermiddlename property.
     * 
     * @return
     *     possible object is
     *     {@link Sendermiddlename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendermiddlename getSendermiddlename() {
        return sendermiddlename;
    }

    /**
     * Sets the value of the sendermiddlename property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendermiddlename }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendermiddlename(Sendermiddlename value) {
        this.sendermiddlename = value;
    }

    /**
     * Gets the value of the senderfamilyname property.
     * 
     * @return
     *     possible object is
     *     {@link Senderfamilyname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderfamilyname getSenderfamilyname() {
        return senderfamilyname;
    }

    /**
     * Sets the value of the senderfamilyname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderfamilyname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderfamilyname(Senderfamilyname value) {
        this.senderfamilyname = value;
    }

    /**
     * Gets the value of the senderstreetaddress property.
     * 
     * @return
     *     possible object is
     *     {@link Senderstreetaddress }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderstreetaddress getSenderstreetaddress() {
        return senderstreetaddress;
    }

    /**
     * Sets the value of the senderstreetaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderstreetaddress }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderstreetaddress(Senderstreetaddress value) {
        this.senderstreetaddress = value;
    }

    /**
     * Gets the value of the sendercity property.
     * 
     * @return
     *     possible object is
     *     {@link Sendercity }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendercity getSendercity() {
        return sendercity;
    }

    /**
     * Sets the value of the sendercity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendercity }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendercity(Sendercity value) {
        this.sendercity = value;
    }

    /**
     * Gets the value of the senderstate property.
     * 
     * @return
     *     possible object is
     *     {@link Senderstate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderstate getSenderstate() {
        return senderstate;
    }

    /**
     * Sets the value of the senderstate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderstate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderstate(Senderstate value) {
        this.senderstate = value;
    }

    /**
     * Gets the value of the senderpostcode property.
     * 
     * @return
     *     possible object is
     *     {@link Senderpostcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderpostcode getSenderpostcode() {
        return senderpostcode;
    }

    /**
     * Sets the value of the senderpostcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderpostcode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderpostcode(Senderpostcode value) {
        this.senderpostcode = value;
    }

    /**
     * Gets the value of the sendercountrycode property.
     * 
     * @return
     *     possible object is
     *     {@link Sendercountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendercountrycode getSendercountrycode() {
        return sendercountrycode;
    }

    /**
     * Sets the value of the sendercountrycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendercountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendercountrycode(Sendercountrycode value) {
        this.sendercountrycode = value;
    }

    /**
     * Gets the value of the sendertel property.
     * 
     * @return
     *     possible object is
     *     {@link Sendertel }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendertel getSendertel() {
        return sendertel;
    }

    /**
     * Sets the value of the sendertel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendertel }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendertel(Sendertel value) {
        this.sendertel = value;
    }

    /**
     * Gets the value of the sendertelextension property.
     * 
     * @return
     *     possible object is
     *     {@link Sendertelextension }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendertelextension getSendertelextension() {
        return sendertelextension;
    }

    /**
     * Sets the value of the sendertelextension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendertelextension }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendertelextension(Sendertelextension value) {
        this.sendertelextension = value;
    }

    /**
     * Gets the value of the sendertelcountrycode property.
     * 
     * @return
     *     possible object is
     *     {@link Sendertelcountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendertelcountrycode getSendertelcountrycode() {
        return sendertelcountrycode;
    }

    /**
     * Sets the value of the sendertelcountrycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendertelcountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendertelcountrycode(Sendertelcountrycode value) {
        this.sendertelcountrycode = value;
    }

    /**
     * Gets the value of the senderfax property.
     * 
     * @return
     *     possible object is
     *     {@link Senderfax }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderfax getSenderfax() {
        return senderfax;
    }

    /**
     * Sets the value of the senderfax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderfax }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderfax(Senderfax value) {
        this.senderfax = value;
    }

    /**
     * Gets the value of the senderfaxextension property.
     * 
     * @return
     *     possible object is
     *     {@link Senderfaxextension }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderfaxextension getSenderfaxextension() {
        return senderfaxextension;
    }

    /**
     * Sets the value of the senderfaxextension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderfaxextension }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderfaxextension(Senderfaxextension value) {
        this.senderfaxextension = value;
    }

    /**
     * Gets the value of the senderfaxcountrycode property.
     * 
     * @return
     *     possible object is
     *     {@link Senderfaxcountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderfaxcountrycode getSenderfaxcountrycode() {
        return senderfaxcountrycode;
    }

    /**
     * Sets the value of the senderfaxcountrycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderfaxcountrycode }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderfaxcountrycode(Senderfaxcountrycode value) {
        this.senderfaxcountrycode = value;
    }

    /**
     * Gets the value of the senderemailaddress property.
     * 
     * @return
     *     possible object is
     *     {@link Senderemailaddress }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderemailaddress getSenderemailaddress() {
        return senderemailaddress;
    }

    /**
     * Sets the value of the senderemailaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderemailaddress }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderemailaddress(Senderemailaddress value) {
        this.senderemailaddress = value;
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
