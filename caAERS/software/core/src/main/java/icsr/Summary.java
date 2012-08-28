
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
 *         &lt;element ref="{}narrativeincludeclinical" minOccurs="0"/>
 *         &lt;element ref="{}reportercomment" minOccurs="0"/>
 *         &lt;element ref="{}senderdiagnosismeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}senderdiagnosis" minOccurs="0"/>
 *         &lt;element ref="{}sendercomment" minOccurs="0"/>
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
    "narrativeincludeclinical",
    "reportercomment",
    "senderdiagnosismeddraversion",
    "senderdiagnosis",
    "sendercomment"
})
@XmlRootElement(name = "summary")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Summary {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Narrativeincludeclinical narrativeincludeclinical;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reportercomment reportercomment;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderdiagnosismeddraversion senderdiagnosismeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Senderdiagnosis senderdiagnosis;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sendercomment sendercomment;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the narrativeincludeclinical property.
     * 
     * @return
     *     possible object is
     *     {@link Narrativeincludeclinical }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Narrativeincludeclinical getNarrativeincludeclinical() {
        return narrativeincludeclinical;
    }

    /**
     * Sets the value of the narrativeincludeclinical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Narrativeincludeclinical }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setNarrativeincludeclinical(Narrativeincludeclinical value) {
        this.narrativeincludeclinical = value;
    }

    /**
     * Gets the value of the reportercomment property.
     * 
     * @return
     *     possible object is
     *     {@link Reportercomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reportercomment getReportercomment() {
        return reportercomment;
    }

    /**
     * Sets the value of the reportercomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reportercomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReportercomment(Reportercomment value) {
        this.reportercomment = value;
    }

    /**
     * Gets the value of the senderdiagnosismeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Senderdiagnosismeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderdiagnosismeddraversion getSenderdiagnosismeddraversion() {
        return senderdiagnosismeddraversion;
    }

    /**
     * Sets the value of the senderdiagnosismeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderdiagnosismeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderdiagnosismeddraversion(Senderdiagnosismeddraversion value) {
        this.senderdiagnosismeddraversion = value;
    }

    /**
     * Gets the value of the senderdiagnosis property.
     * 
     * @return
     *     possible object is
     *     {@link Senderdiagnosis }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Senderdiagnosis getSenderdiagnosis() {
        return senderdiagnosis;
    }

    /**
     * Sets the value of the senderdiagnosis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senderdiagnosis }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSenderdiagnosis(Senderdiagnosis value) {
        this.senderdiagnosis = value;
    }

    /**
     * Gets the value of the sendercomment property.
     * 
     * @return
     *     possible object is
     *     {@link Sendercomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sendercomment getSendercomment() {
        return sendercomment;
    }

    /**
     * Sets the value of the sendercomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sendercomment }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSendercomment(Sendercomment value) {
        this.sendercomment = value;
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
