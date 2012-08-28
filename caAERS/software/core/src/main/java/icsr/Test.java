
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
 *         &lt;element ref="{}testdateformat" minOccurs="0"/>
 *         &lt;element ref="{}testdate" minOccurs="0"/>
 *         &lt;element ref="{}testname" minOccurs="0"/>
 *         &lt;element ref="{}testresult" minOccurs="0"/>
 *         &lt;element ref="{}testunit" minOccurs="0"/>
 *         &lt;element ref="{}lowtestrange" minOccurs="0"/>
 *         &lt;element ref="{}hightestrange" minOccurs="0"/>
 *         &lt;element ref="{}moreinformation" minOccurs="0"/>
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
    "testdateformat",
    "testdate",
    "testname",
    "testresult",
    "testunit",
    "lowtestrange",
    "hightestrange",
    "moreinformation"
})
@XmlRootElement(name = "test")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Test {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Testdateformat testdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Testdate testdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Testname testname;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Testresult testresult;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Testunit testunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Lowtestrange lowtestrange;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Hightestrange hightestrange;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Moreinformation moreinformation;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the testdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Testdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Testdateformat getTestdateformat() {
        return testdateformat;
    }

    /**
     * Sets the value of the testdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Testdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTestdateformat(Testdateformat value) {
        this.testdateformat = value;
    }

    /**
     * Gets the value of the testdate property.
     * 
     * @return
     *     possible object is
     *     {@link Testdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Testdate getTestdate() {
        return testdate;
    }

    /**
     * Sets the value of the testdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Testdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTestdate(Testdate value) {
        this.testdate = value;
    }

    /**
     * Gets the value of the testname property.
     * 
     * @return
     *     possible object is
     *     {@link Testname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Testname getTestname() {
        return testname;
    }

    /**
     * Sets the value of the testname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Testname }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTestname(Testname value) {
        this.testname = value;
    }

    /**
     * Gets the value of the testresult property.
     * 
     * @return
     *     possible object is
     *     {@link Testresult }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Testresult getTestresult() {
        return testresult;
    }

    /**
     * Sets the value of the testresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Testresult }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTestresult(Testresult value) {
        this.testresult = value;
    }

    /**
     * Gets the value of the testunit property.
     * 
     * @return
     *     possible object is
     *     {@link Testunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Testunit getTestunit() {
        return testunit;
    }

    /**
     * Sets the value of the testunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Testunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTestunit(Testunit value) {
        this.testunit = value;
    }

    /**
     * Gets the value of the lowtestrange property.
     * 
     * @return
     *     possible object is
     *     {@link Lowtestrange }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Lowtestrange getLowtestrange() {
        return lowtestrange;
    }

    /**
     * Sets the value of the lowtestrange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lowtestrange }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLowtestrange(Lowtestrange value) {
        this.lowtestrange = value;
    }

    /**
     * Gets the value of the hightestrange property.
     * 
     * @return
     *     possible object is
     *     {@link Hightestrange }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Hightestrange getHightestrange() {
        return hightestrange;
    }

    /**
     * Sets the value of the hightestrange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hightestrange }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setHightestrange(Hightestrange value) {
        this.hightestrange = value;
    }

    /**
     * Gets the value of the moreinformation property.
     * 
     * @return
     *     possible object is
     *     {@link Moreinformation }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Moreinformation getMoreinformation() {
        return moreinformation;
    }

    /**
     * Sets the value of the moreinformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Moreinformation }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMoreinformation(Moreinformation value) {
        this.moreinformation = value;
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
