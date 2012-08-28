
package icsr;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}safetyreportversion" minOccurs="0"/>
 *         &lt;element ref="{}safetyreportid"/>
 *         &lt;element ref="{}primarysourcecountry" minOccurs="0"/>
 *         &lt;element ref="{}occurcountry" minOccurs="0"/>
 *         &lt;element ref="{}transmissiondateformat" minOccurs="0"/>
 *         &lt;element ref="{}transmissiondate" minOccurs="0"/>
 *         &lt;element ref="{}reporttype" minOccurs="0"/>
 *         &lt;element ref="{}serious" minOccurs="0"/>
 *         &lt;element ref="{}seriousnessdeath" minOccurs="0"/>
 *         &lt;element ref="{}seriousnesslifethreatening" minOccurs="0"/>
 *         &lt;element ref="{}seriousnesshospitalization" minOccurs="0"/>
 *         &lt;element ref="{}seriousnessdisabling" minOccurs="0"/>
 *         &lt;element ref="{}seriousnesscongenitalanomali" minOccurs="0"/>
 *         &lt;element ref="{}seriousnessother" minOccurs="0"/>
 *         &lt;element ref="{}receivedateformat" minOccurs="0"/>
 *         &lt;element ref="{}receivedate" minOccurs="0"/>
 *         &lt;element ref="{}receiptdateformat" minOccurs="0"/>
 *         &lt;element ref="{}receiptdate" minOccurs="0"/>
 *         &lt;element ref="{}additionaldocument" minOccurs="0"/>
 *         &lt;element ref="{}documentlist" minOccurs="0"/>
 *         &lt;element ref="{}fulfillexpeditecriteria" minOccurs="0"/>
 *         &lt;element ref="{}authoritynumb" minOccurs="0"/>
 *         &lt;element ref="{}companynumb" minOccurs="0"/>
 *         &lt;element ref="{}duplicate" minOccurs="0"/>
 *         &lt;element ref="{}casenullification" minOccurs="0"/>
 *         &lt;element ref="{}nullificationreason" minOccurs="0"/>
 *         &lt;element ref="{}medicallyconfirm" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element ref="{}reportduplicate" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}linkedreport" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}primarysource" maxOccurs="unbounded"/>
 *           &lt;element ref="{}sender"/>
 *           &lt;element ref="{}receiver"/>
 *           &lt;element ref="{}patient"/>
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
    "safetyreportversion",
    "safetyreportid",
    "primarysourcecountry",
    "occurcountry",
    "transmissiondateformat",
    "transmissiondate",
    "reporttype",
    "serious",
    "seriousnessdeath",
    "seriousnesslifethreatening",
    "seriousnesshospitalization",
    "seriousnessdisabling",
    "seriousnesscongenitalanomali",
    "seriousnessother",
    "receivedateformat",
    "receivedate",
    "receiptdateformat",
    "receiptdate",
    "additionaldocument",
    "documentlist",
    "fulfillexpeditecriteria",
    "authoritynumb",
    "companynumb",
    "duplicate",
    "casenullification",
    "nullificationreason",
    "medicallyconfirm",
    "reportduplicate",
    "linkedreport",
    "primarysource",
    "sender",
    "receiver",
    "patient"
})
@XmlRootElement(name = "safetyreport")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Safetyreport {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Safetyreportversion safetyreportversion;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Safetyreportid safetyreportid;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Primarysourcecountry primarysourcecountry;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Occurcountry occurcountry;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Transmissiondateformat transmissiondateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Transmissiondate transmissiondate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reporttype reporttype;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Serious serious;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnessdeath seriousnessdeath;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnesslifethreatening seriousnesslifethreatening;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnesshospitalization seriousnesshospitalization;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnessdisabling seriousnessdisabling;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnesscongenitalanomali seriousnesscongenitalanomali;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Seriousnessother seriousnessother;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receivedateformat receivedateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receivedate receivedate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receiptdateformat receiptdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receiptdate receiptdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Additionaldocument additionaldocument;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Documentlist documentlist;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Fulfillexpeditecriteria fulfillexpeditecriteria;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Authoritynumb authoritynumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Companynumb companynumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Duplicate duplicate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Casenullification casenullification;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Nullificationreason nullificationreason;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Medicallyconfirm medicallyconfirm;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Reportduplicate> reportduplicate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Linkedreport> linkedreport;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Primarysource> primarysource;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Sender sender;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Receiver receiver;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patient patient;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

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
     * Gets the value of the primarysourcecountry property.
     * 
     * @return
     *     possible object is
     *     {@link Primarysourcecountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Primarysourcecountry getPrimarysourcecountry() {
        return primarysourcecountry;
    }

    /**
     * Sets the value of the primarysourcecountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Primarysourcecountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPrimarysourcecountry(Primarysourcecountry value) {
        this.primarysourcecountry = value;
    }

    /**
     * Gets the value of the occurcountry property.
     * 
     * @return
     *     possible object is
     *     {@link Occurcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Occurcountry getOccurcountry() {
        return occurcountry;
    }

    /**
     * Sets the value of the occurcountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Occurcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setOccurcountry(Occurcountry value) {
        this.occurcountry = value;
    }

    /**
     * Gets the value of the transmissiondateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Transmissiondateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Transmissiondateformat getTransmissiondateformat() {
        return transmissiondateformat;
    }

    /**
     * Sets the value of the transmissiondateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transmissiondateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTransmissiondateformat(Transmissiondateformat value) {
        this.transmissiondateformat = value;
    }

    /**
     * Gets the value of the transmissiondate property.
     * 
     * @return
     *     possible object is
     *     {@link Transmissiondate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Transmissiondate getTransmissiondate() {
        return transmissiondate;
    }

    /**
     * Sets the value of the transmissiondate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transmissiondate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTransmissiondate(Transmissiondate value) {
        this.transmissiondate = value;
    }

    /**
     * Gets the value of the reporttype property.
     * 
     * @return
     *     possible object is
     *     {@link Reporttype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reporttype getReporttype() {
        return reporttype;
    }

    /**
     * Sets the value of the reporttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reporttype }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReporttype(Reporttype value) {
        this.reporttype = value;
    }

    /**
     * Gets the value of the serious property.
     * 
     * @return
     *     possible object is
     *     {@link Serious }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Serious getSerious() {
        return serious;
    }

    /**
     * Sets the value of the serious property.
     * 
     * @param value
     *     allowed object is
     *     {@link Serious }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSerious(Serious value) {
        this.serious = value;
    }

    /**
     * Gets the value of the seriousnessdeath property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnessdeath }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnessdeath getSeriousnessdeath() {
        return seriousnessdeath;
    }

    /**
     * Sets the value of the seriousnessdeath property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnessdeath }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnessdeath(Seriousnessdeath value) {
        this.seriousnessdeath = value;
    }

    /**
     * Gets the value of the seriousnesslifethreatening property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnesslifethreatening }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnesslifethreatening getSeriousnesslifethreatening() {
        return seriousnesslifethreatening;
    }

    /**
     * Sets the value of the seriousnesslifethreatening property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnesslifethreatening }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnesslifethreatening(Seriousnesslifethreatening value) {
        this.seriousnesslifethreatening = value;
    }

    /**
     * Gets the value of the seriousnesshospitalization property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnesshospitalization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnesshospitalization getSeriousnesshospitalization() {
        return seriousnesshospitalization;
    }

    /**
     * Sets the value of the seriousnesshospitalization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnesshospitalization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnesshospitalization(Seriousnesshospitalization value) {
        this.seriousnesshospitalization = value;
    }

    /**
     * Gets the value of the seriousnessdisabling property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnessdisabling }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnessdisabling getSeriousnessdisabling() {
        return seriousnessdisabling;
    }

    /**
     * Sets the value of the seriousnessdisabling property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnessdisabling }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnessdisabling(Seriousnessdisabling value) {
        this.seriousnessdisabling = value;
    }

    /**
     * Gets the value of the seriousnesscongenitalanomali property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnesscongenitalanomali }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnesscongenitalanomali getSeriousnesscongenitalanomali() {
        return seriousnesscongenitalanomali;
    }

    /**
     * Sets the value of the seriousnesscongenitalanomali property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnesscongenitalanomali }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnesscongenitalanomali(Seriousnesscongenitalanomali value) {
        this.seriousnesscongenitalanomali = value;
    }

    /**
     * Gets the value of the seriousnessother property.
     * 
     * @return
     *     possible object is
     *     {@link Seriousnessother }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Seriousnessother getSeriousnessother() {
        return seriousnessother;
    }

    /**
     * Sets the value of the seriousnessother property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seriousnessother }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSeriousnessother(Seriousnessother value) {
        this.seriousnessother = value;
    }

    /**
     * Gets the value of the receivedateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Receivedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Receivedateformat getReceivedateformat() {
        return receivedateformat;
    }

    /**
     * Sets the value of the receivedateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receivedateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReceivedateformat(Receivedateformat value) {
        this.receivedateformat = value;
    }

    /**
     * Gets the value of the receivedate property.
     * 
     * @return
     *     possible object is
     *     {@link Receivedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Receivedate getReceivedate() {
        return receivedate;
    }

    /**
     * Sets the value of the receivedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receivedate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReceivedate(Receivedate value) {
        this.receivedate = value;
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
     * Gets the value of the additionaldocument property.
     * 
     * @return
     *     possible object is
     *     {@link Additionaldocument }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Additionaldocument getAdditionaldocument() {
        return additionaldocument;
    }

    /**
     * Sets the value of the additionaldocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link Additionaldocument }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAdditionaldocument(Additionaldocument value) {
        this.additionaldocument = value;
    }

    /**
     * Gets the value of the documentlist property.
     * 
     * @return
     *     possible object is
     *     {@link Documentlist }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Documentlist getDocumentlist() {
        return documentlist;
    }

    /**
     * Sets the value of the documentlist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documentlist }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDocumentlist(Documentlist value) {
        this.documentlist = value;
    }

    /**
     * Gets the value of the fulfillexpeditecriteria property.
     * 
     * @return
     *     possible object is
     *     {@link Fulfillexpeditecriteria }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Fulfillexpeditecriteria getFulfillexpeditecriteria() {
        return fulfillexpeditecriteria;
    }

    /**
     * Sets the value of the fulfillexpeditecriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fulfillexpeditecriteria }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setFulfillexpeditecriteria(Fulfillexpeditecriteria value) {
        this.fulfillexpeditecriteria = value;
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
     * Gets the value of the duplicate property.
     * 
     * @return
     *     possible object is
     *     {@link Duplicate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Duplicate getDuplicate() {
        return duplicate;
    }

    /**
     * Sets the value of the duplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duplicate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDuplicate(Duplicate value) {
        this.duplicate = value;
    }

    /**
     * Gets the value of the casenullification property.
     * 
     * @return
     *     possible object is
     *     {@link Casenullification }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Casenullification getCasenullification() {
        return casenullification;
    }

    /**
     * Sets the value of the casenullification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Casenullification }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setCasenullification(Casenullification value) {
        this.casenullification = value;
    }

    /**
     * Gets the value of the nullificationreason property.
     * 
     * @return
     *     possible object is
     *     {@link Nullificationreason }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Nullificationreason getNullificationreason() {
        return nullificationreason;
    }

    /**
     * Sets the value of the nullificationreason property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nullificationreason }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setNullificationreason(Nullificationreason value) {
        this.nullificationreason = value;
    }

    /**
     * Gets the value of the medicallyconfirm property.
     * 
     * @return
     *     possible object is
     *     {@link Medicallyconfirm }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Medicallyconfirm getMedicallyconfirm() {
        return medicallyconfirm;
    }

    /**
     * Sets the value of the medicallyconfirm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Medicallyconfirm }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMedicallyconfirm(Medicallyconfirm value) {
        this.medicallyconfirm = value;
    }

    /**
     * Gets the value of the reportduplicate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportduplicate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportduplicate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reportduplicate }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Reportduplicate> getReportduplicate() {
        if (reportduplicate == null) {
            reportduplicate = new ArrayList<Reportduplicate>();
        }
        return this.reportduplicate;
    }

    /**
     * Gets the value of the linkedreport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkedreport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkedreport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Linkedreport }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Linkedreport> getLinkedreport() {
        if (linkedreport == null) {
            linkedreport = new ArrayList<Linkedreport>();
        }
        return this.linkedreport;
    }

    /**
     * Gets the value of the primarysource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primarysource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimarysource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Primarysource }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Primarysource> getPrimarysource() {
        if (primarysource == null) {
            primarysource = new ArrayList<Primarysource>();
        }
        return this.primarysource;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link Sender }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Sender getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sender }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setSender(Sender value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link Receiver }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receiver }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReceiver(Receiver value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatient(Patient value) {
        this.patient = value;
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
