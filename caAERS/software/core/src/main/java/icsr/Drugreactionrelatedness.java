
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
 *         &lt;element ref="{}drugreactionassesmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}drugreactionasses" minOccurs="0"/>
 *         &lt;element ref="{}drugassessmentsource" minOccurs="0"/>
 *         &lt;element ref="{}drugassessmentmethod" minOccurs="0"/>
 *         &lt;element ref="{}drugresult" minOccurs="0"/>
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
    "drugreactionassesmeddraversion",
    "drugreactionasses",
    "drugassessmentsource",
    "drugassessmentmethod",
    "drugresult"
})
@XmlRootElement(name = "drugreactionrelatedness")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Drugreactionrelatedness {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugreactionassesmeddraversion drugreactionassesmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugreactionasses drugreactionasses;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugassessmentsource drugassessmentsource;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugassessmentmethod drugassessmentmethod;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugresult drugresult;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the drugreactionassesmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Drugreactionassesmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugreactionassesmeddraversion getDrugreactionassesmeddraversion() {
        return drugreactionassesmeddraversion;
    }

    /**
     * Sets the value of the drugreactionassesmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugreactionassesmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugreactionassesmeddraversion(Drugreactionassesmeddraversion value) {
        this.drugreactionassesmeddraversion = value;
    }

    /**
     * Gets the value of the drugreactionasses property.
     * 
     * @return
     *     possible object is
     *     {@link Drugreactionasses }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugreactionasses getDrugreactionasses() {
        return drugreactionasses;
    }

    /**
     * Sets the value of the drugreactionasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugreactionasses }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugreactionasses(Drugreactionasses value) {
        this.drugreactionasses = value;
    }

    /**
     * Gets the value of the drugassessmentsource property.
     * 
     * @return
     *     possible object is
     *     {@link Drugassessmentsource }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugassessmentsource getDrugassessmentsource() {
        return drugassessmentsource;
    }

    /**
     * Sets the value of the drugassessmentsource property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugassessmentsource }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugassessmentsource(Drugassessmentsource value) {
        this.drugassessmentsource = value;
    }

    /**
     * Gets the value of the drugassessmentmethod property.
     * 
     * @return
     *     possible object is
     *     {@link Drugassessmentmethod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugassessmentmethod getDrugassessmentmethod() {
        return drugassessmentmethod;
    }

    /**
     * Sets the value of the drugassessmentmethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugassessmentmethod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugassessmentmethod(Drugassessmentmethod value) {
        this.drugassessmentmethod = value;
    }

    /**
     * Gets the value of the drugresult property.
     * 
     * @return
     *     possible object is
     *     {@link Drugresult }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugresult getDrugresult() {
        return drugresult;
    }

    /**
     * Sets the value of the drugresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugresult }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugresult(Drugresult value) {
        this.drugresult = value;
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
