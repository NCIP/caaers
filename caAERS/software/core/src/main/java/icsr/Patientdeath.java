
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
 *         &lt;element ref="{}patientdeathdateformat" minOccurs="0"/>
 *         &lt;element ref="{}patientdeathdate" minOccurs="0"/>
 *         &lt;element ref="{}patientautopsyyesno" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element ref="{}patientdeathcause" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}patientautopsy" maxOccurs="unbounded" minOccurs="0"/>
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
    "patientdeathdateformat",
    "patientdeathdate",
    "patientautopsyyesno",
    "patientdeathcause",
    "patientautopsy"
})
@XmlRootElement(name = "patientdeath")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Patientdeath {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdeathdateformat patientdeathdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientdeathdate patientdeathdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Patientautopsyyesno patientautopsyyesno;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Patientdeathcause> patientdeathcause;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Patientautopsy> patientautopsy;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the patientdeathdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdeathdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdeathdateformat getPatientdeathdateformat() {
        return patientdeathdateformat;
    }

    /**
     * Sets the value of the patientdeathdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdeathdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdeathdateformat(Patientdeathdateformat value) {
        this.patientdeathdateformat = value;
    }

    /**
     * Gets the value of the patientdeathdate property.
     * 
     * @return
     *     possible object is
     *     {@link Patientdeathdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientdeathdate getPatientdeathdate() {
        return patientdeathdate;
    }

    /**
     * Sets the value of the patientdeathdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientdeathdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientdeathdate(Patientdeathdate value) {
        this.patientdeathdate = value;
    }

    /**
     * Gets the value of the patientautopsyyesno property.
     * 
     * @return
     *     possible object is
     *     {@link Patientautopsyyesno }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Patientautopsyyesno getPatientautopsyyesno() {
        return patientautopsyyesno;
    }

    /**
     * Sets the value of the patientautopsyyesno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patientautopsyyesno }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setPatientautopsyyesno(Patientautopsyyesno value) {
        this.patientautopsyyesno = value;
    }

    /**
     * Gets the value of the patientdeathcause property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientdeathcause property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientdeathcause().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patientdeathcause }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Patientdeathcause> getPatientdeathcause() {
        if (patientdeathcause == null) {
            patientdeathcause = new ArrayList<Patientdeathcause>();
        }
        return this.patientdeathcause;
    }

    /**
     * Gets the value of the patientautopsy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientautopsy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientautopsy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patientautopsy }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Patientautopsy> getPatientautopsy() {
        if (patientautopsy == null) {
            patientautopsy = new ArrayList<Patientautopsy>();
        }
        return this.patientautopsy;
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
