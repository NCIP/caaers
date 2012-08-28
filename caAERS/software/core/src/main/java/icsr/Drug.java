
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
 *         &lt;element ref="{}drugcharacterization" minOccurs="0"/>
 *         &lt;element ref="{}medicinalproduct" minOccurs="0"/>
 *         &lt;element ref="{}obtaindrugcountry" minOccurs="0"/>
 *         &lt;element ref="{}drugbatchnumb" minOccurs="0"/>
 *         &lt;element ref="{}drugauthorizationnumb" minOccurs="0"/>
 *         &lt;element ref="{}drugauthorizationcountry" minOccurs="0"/>
 *         &lt;element ref="{}drugauthorizationholder" minOccurs="0"/>
 *         &lt;element ref="{}drugstructuredosagenumb" minOccurs="0"/>
 *         &lt;element ref="{}drugstructuredosageunit" minOccurs="0"/>
 *         &lt;element ref="{}drugseparatedosagenumb" minOccurs="0"/>
 *         &lt;element ref="{}drugintervaldosageunitnumb" minOccurs="0"/>
 *         &lt;element ref="{}drugintervaldosagedefinition" minOccurs="0"/>
 *         &lt;element ref="{}drugcumulativedosagenumb" minOccurs="0"/>
 *         &lt;element ref="{}drugcumulativedosageunit" minOccurs="0"/>
 *         &lt;element ref="{}drugdosagetext" minOccurs="0"/>
 *         &lt;element ref="{}drugdosageform" minOccurs="0"/>
 *         &lt;element ref="{}drugadministrationroute" minOccurs="0"/>
 *         &lt;element ref="{}drugparadministration" minOccurs="0"/>
 *         &lt;element ref="{}reactiongestationperiod" minOccurs="0"/>
 *         &lt;element ref="{}reactiongestationperiodunit" minOccurs="0"/>
 *         &lt;element ref="{}drugindicationmeddraversion" minOccurs="0"/>
 *         &lt;element ref="{}drugindication" minOccurs="0"/>
 *         &lt;element ref="{}drugstartdateformat" minOccurs="0"/>
 *         &lt;element ref="{}drugstartdate" minOccurs="0"/>
 *         &lt;element ref="{}drugstartperiod" minOccurs="0"/>
 *         &lt;element ref="{}drugstartperiodunit" minOccurs="0"/>
 *         &lt;element ref="{}druglastperiod" minOccurs="0"/>
 *         &lt;element ref="{}druglastperiodunit" minOccurs="0"/>
 *         &lt;element ref="{}drugenddateformat" minOccurs="0"/>
 *         &lt;element ref="{}drugenddate" minOccurs="0"/>
 *         &lt;element ref="{}drugtreatmentduration" minOccurs="0"/>
 *         &lt;element ref="{}drugtreatmentdurationunit" minOccurs="0"/>
 *         &lt;element ref="{}actiondrug" minOccurs="0"/>
 *         &lt;element ref="{}drugrecurreadministration" minOccurs="0"/>
 *         &lt;element ref="{}drugadditional" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element ref="{}activesubstance" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}drugrecurrence" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}drugreactionrelatedness" maxOccurs="unbounded" minOccurs="0"/>
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
    "drugcharacterization",
    "medicinalproduct",
    "obtaindrugcountry",
    "drugbatchnumb",
    "drugauthorizationnumb",
    "drugauthorizationcountry",
    "drugauthorizationholder",
    "drugstructuredosagenumb",
    "drugstructuredosageunit",
    "drugseparatedosagenumb",
    "drugintervaldosageunitnumb",
    "drugintervaldosagedefinition",
    "drugcumulativedosagenumb",
    "drugcumulativedosageunit",
    "drugdosagetext",
    "drugdosageform",
    "drugadministrationroute",
    "drugparadministration",
    "reactiongestationperiod",
    "reactiongestationperiodunit",
    "drugindicationmeddraversion",
    "drugindication",
    "drugstartdateformat",
    "drugstartdate",
    "drugstartperiod",
    "drugstartperiodunit",
    "druglastperiod",
    "druglastperiodunit",
    "drugenddateformat",
    "drugenddate",
    "drugtreatmentduration",
    "drugtreatmentdurationunit",
    "actiondrug",
    "drugrecurreadministration",
    "drugadditional",
    "activesubstance",
    "drugrecurrence",
    "drugreactionrelatedness"
})
@XmlRootElement(name = "drug")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Drug {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugcharacterization drugcharacterization;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Medicinalproduct medicinalproduct;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Obtaindrugcountry obtaindrugcountry;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugbatchnumb drugbatchnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugauthorizationnumb drugauthorizationnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugauthorizationcountry drugauthorizationcountry;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugauthorizationholder drugauthorizationholder;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstructuredosagenumb drugstructuredosagenumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstructuredosageunit drugstructuredosageunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugseparatedosagenumb drugseparatedosagenumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugintervaldosageunitnumb drugintervaldosageunitnumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugintervaldosagedefinition drugintervaldosagedefinition;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugcumulativedosagenumb drugcumulativedosagenumb;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugcumulativedosageunit drugcumulativedosageunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugdosagetext drugdosagetext;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugdosageform drugdosageform;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugadministrationroute drugadministrationroute;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugparadministration drugparadministration;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reactiongestationperiod reactiongestationperiod;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Reactiongestationperiodunit reactiongestationperiodunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugindicationmeddraversion drugindicationmeddraversion;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugindication drugindication;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstartdateformat drugstartdateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstartdate drugstartdate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstartperiod drugstartperiod;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugstartperiodunit drugstartperiodunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Druglastperiod druglastperiod;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Druglastperiodunit druglastperiodunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugenddateformat drugenddateformat;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugenddate drugenddate;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugtreatmentduration drugtreatmentduration;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugtreatmentdurationunit drugtreatmentdurationunit;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Actiondrug actiondrug;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugrecurreadministration drugrecurreadministration;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected Drugadditional drugadditional;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Activesubstance> activesubstance;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Drugrecurrence> drugrecurrence;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Drugreactionrelatedness> drugreactionrelatedness;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;

    /**
     * Gets the value of the drugcharacterization property.
     * 
     * @return
     *     possible object is
     *     {@link Drugcharacterization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugcharacterization getDrugcharacterization() {
        return drugcharacterization;
    }

    /**
     * Sets the value of the drugcharacterization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugcharacterization }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugcharacterization(Drugcharacterization value) {
        this.drugcharacterization = value;
    }

    /**
     * Gets the value of the medicinalproduct property.
     * 
     * @return
     *     possible object is
     *     {@link Medicinalproduct }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Medicinalproduct getMedicinalproduct() {
        return medicinalproduct;
    }

    /**
     * Sets the value of the medicinalproduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Medicinalproduct }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setMedicinalproduct(Medicinalproduct value) {
        this.medicinalproduct = value;
    }

    /**
     * Gets the value of the obtaindrugcountry property.
     * 
     * @return
     *     possible object is
     *     {@link Obtaindrugcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Obtaindrugcountry getObtaindrugcountry() {
        return obtaindrugcountry;
    }

    /**
     * Sets the value of the obtaindrugcountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obtaindrugcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setObtaindrugcountry(Obtaindrugcountry value) {
        this.obtaindrugcountry = value;
    }

    /**
     * Gets the value of the drugbatchnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugbatchnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugbatchnumb getDrugbatchnumb() {
        return drugbatchnumb;
    }

    /**
     * Sets the value of the drugbatchnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugbatchnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugbatchnumb(Drugbatchnumb value) {
        this.drugbatchnumb = value;
    }

    /**
     * Gets the value of the drugauthorizationnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugauthorizationnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugauthorizationnumb getDrugauthorizationnumb() {
        return drugauthorizationnumb;
    }

    /**
     * Sets the value of the drugauthorizationnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugauthorizationnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugauthorizationnumb(Drugauthorizationnumb value) {
        this.drugauthorizationnumb = value;
    }

    /**
     * Gets the value of the drugauthorizationcountry property.
     * 
     * @return
     *     possible object is
     *     {@link Drugauthorizationcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugauthorizationcountry getDrugauthorizationcountry() {
        return drugauthorizationcountry;
    }

    /**
     * Sets the value of the drugauthorizationcountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugauthorizationcountry }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugauthorizationcountry(Drugauthorizationcountry value) {
        this.drugauthorizationcountry = value;
    }

    /**
     * Gets the value of the drugauthorizationholder property.
     * 
     * @return
     *     possible object is
     *     {@link Drugauthorizationholder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugauthorizationholder getDrugauthorizationholder() {
        return drugauthorizationholder;
    }

    /**
     * Sets the value of the drugauthorizationholder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugauthorizationholder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugauthorizationholder(Drugauthorizationholder value) {
        this.drugauthorizationholder = value;
    }

    /**
     * Gets the value of the drugstructuredosagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstructuredosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstructuredosagenumb getDrugstructuredosagenumb() {
        return drugstructuredosagenumb;
    }

    /**
     * Sets the value of the drugstructuredosagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstructuredosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstructuredosagenumb(Drugstructuredosagenumb value) {
        this.drugstructuredosagenumb = value;
    }

    /**
     * Gets the value of the drugstructuredosageunit property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstructuredosageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstructuredosageunit getDrugstructuredosageunit() {
        return drugstructuredosageunit;
    }

    /**
     * Sets the value of the drugstructuredosageunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstructuredosageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstructuredosageunit(Drugstructuredosageunit value) {
        this.drugstructuredosageunit = value;
    }

    /**
     * Gets the value of the drugseparatedosagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugseparatedosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugseparatedosagenumb getDrugseparatedosagenumb() {
        return drugseparatedosagenumb;
    }

    /**
     * Sets the value of the drugseparatedosagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugseparatedosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugseparatedosagenumb(Drugseparatedosagenumb value) {
        this.drugseparatedosagenumb = value;
    }

    /**
     * Gets the value of the drugintervaldosageunitnumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugintervaldosageunitnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugintervaldosageunitnumb getDrugintervaldosageunitnumb() {
        return drugintervaldosageunitnumb;
    }

    /**
     * Sets the value of the drugintervaldosageunitnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugintervaldosageunitnumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugintervaldosageunitnumb(Drugintervaldosageunitnumb value) {
        this.drugintervaldosageunitnumb = value;
    }

    /**
     * Gets the value of the drugintervaldosagedefinition property.
     * 
     * @return
     *     possible object is
     *     {@link Drugintervaldosagedefinition }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugintervaldosagedefinition getDrugintervaldosagedefinition() {
        return drugintervaldosagedefinition;
    }

    /**
     * Sets the value of the drugintervaldosagedefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugintervaldosagedefinition }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugintervaldosagedefinition(Drugintervaldosagedefinition value) {
        this.drugintervaldosagedefinition = value;
    }

    /**
     * Gets the value of the drugcumulativedosagenumb property.
     * 
     * @return
     *     possible object is
     *     {@link Drugcumulativedosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugcumulativedosagenumb getDrugcumulativedosagenumb() {
        return drugcumulativedosagenumb;
    }

    /**
     * Sets the value of the drugcumulativedosagenumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugcumulativedosagenumb }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugcumulativedosagenumb(Drugcumulativedosagenumb value) {
        this.drugcumulativedosagenumb = value;
    }

    /**
     * Gets the value of the drugcumulativedosageunit property.
     * 
     * @return
     *     possible object is
     *     {@link Drugcumulativedosageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugcumulativedosageunit getDrugcumulativedosageunit() {
        return drugcumulativedosageunit;
    }

    /**
     * Sets the value of the drugcumulativedosageunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugcumulativedosageunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugcumulativedosageunit(Drugcumulativedosageunit value) {
        this.drugcumulativedosageunit = value;
    }

    /**
     * Gets the value of the drugdosagetext property.
     * 
     * @return
     *     possible object is
     *     {@link Drugdosagetext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugdosagetext getDrugdosagetext() {
        return drugdosagetext;
    }

    /**
     * Sets the value of the drugdosagetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugdosagetext }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugdosagetext(Drugdosagetext value) {
        this.drugdosagetext = value;
    }

    /**
     * Gets the value of the drugdosageform property.
     * 
     * @return
     *     possible object is
     *     {@link Drugdosageform }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugdosageform getDrugdosageform() {
        return drugdosageform;
    }

    /**
     * Sets the value of the drugdosageform property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugdosageform }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugdosageform(Drugdosageform value) {
        this.drugdosageform = value;
    }

    /**
     * Gets the value of the drugadministrationroute property.
     * 
     * @return
     *     possible object is
     *     {@link Drugadministrationroute }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugadministrationroute getDrugadministrationroute() {
        return drugadministrationroute;
    }

    /**
     * Sets the value of the drugadministrationroute property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugadministrationroute }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugadministrationroute(Drugadministrationroute value) {
        this.drugadministrationroute = value;
    }

    /**
     * Gets the value of the drugparadministration property.
     * 
     * @return
     *     possible object is
     *     {@link Drugparadministration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugparadministration getDrugparadministration() {
        return drugparadministration;
    }

    /**
     * Sets the value of the drugparadministration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugparadministration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugparadministration(Drugparadministration value) {
        this.drugparadministration = value;
    }

    /**
     * Gets the value of the reactiongestationperiod property.
     * 
     * @return
     *     possible object is
     *     {@link Reactiongestationperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reactiongestationperiod getReactiongestationperiod() {
        return reactiongestationperiod;
    }

    /**
     * Sets the value of the reactiongestationperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reactiongestationperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReactiongestationperiod(Reactiongestationperiod value) {
        this.reactiongestationperiod = value;
    }

    /**
     * Gets the value of the reactiongestationperiodunit property.
     * 
     * @return
     *     possible object is
     *     {@link Reactiongestationperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Reactiongestationperiodunit getReactiongestationperiodunit() {
        return reactiongestationperiodunit;
    }

    /**
     * Sets the value of the reactiongestationperiodunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reactiongestationperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setReactiongestationperiodunit(Reactiongestationperiodunit value) {
        this.reactiongestationperiodunit = value;
    }

    /**
     * Gets the value of the drugindicationmeddraversion property.
     * 
     * @return
     *     possible object is
     *     {@link Drugindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugindicationmeddraversion getDrugindicationmeddraversion() {
        return drugindicationmeddraversion;
    }

    /**
     * Sets the value of the drugindicationmeddraversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugindicationmeddraversion }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugindicationmeddraversion(Drugindicationmeddraversion value) {
        this.drugindicationmeddraversion = value;
    }

    /**
     * Gets the value of the drugindication property.
     * 
     * @return
     *     possible object is
     *     {@link Drugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugindication getDrugindication() {
        return drugindication;
    }

    /**
     * Sets the value of the drugindication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugindication }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugindication(Drugindication value) {
        this.drugindication = value;
    }

    /**
     * Gets the value of the drugstartdateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstartdateformat getDrugstartdateformat() {
        return drugstartdateformat;
    }

    /**
     * Sets the value of the drugstartdateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstartdateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstartdateformat(Drugstartdateformat value) {
        this.drugstartdateformat = value;
    }

    /**
     * Gets the value of the drugstartdate property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstartdate getDrugstartdate() {
        return drugstartdate;
    }

    /**
     * Sets the value of the drugstartdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstartdate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstartdate(Drugstartdate value) {
        this.drugstartdate = value;
    }

    /**
     * Gets the value of the drugstartperiod property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstartperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstartperiod getDrugstartperiod() {
        return drugstartperiod;
    }

    /**
     * Sets the value of the drugstartperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstartperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstartperiod(Drugstartperiod value) {
        this.drugstartperiod = value;
    }

    /**
     * Gets the value of the drugstartperiodunit property.
     * 
     * @return
     *     possible object is
     *     {@link Drugstartperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugstartperiodunit getDrugstartperiodunit() {
        return drugstartperiodunit;
    }

    /**
     * Sets the value of the drugstartperiodunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugstartperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugstartperiodunit(Drugstartperiodunit value) {
        this.drugstartperiodunit = value;
    }

    /**
     * Gets the value of the druglastperiod property.
     * 
     * @return
     *     possible object is
     *     {@link Druglastperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Druglastperiod getDruglastperiod() {
        return druglastperiod;
    }

    /**
     * Sets the value of the druglastperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Druglastperiod }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDruglastperiod(Druglastperiod value) {
        this.druglastperiod = value;
    }

    /**
     * Gets the value of the druglastperiodunit property.
     * 
     * @return
     *     possible object is
     *     {@link Druglastperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Druglastperiodunit getDruglastperiodunit() {
        return druglastperiodunit;
    }

    /**
     * Sets the value of the druglastperiodunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Druglastperiodunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDruglastperiodunit(Druglastperiodunit value) {
        this.druglastperiodunit = value;
    }

    /**
     * Gets the value of the drugenddateformat property.
     * 
     * @return
     *     possible object is
     *     {@link Drugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugenddateformat getDrugenddateformat() {
        return drugenddateformat;
    }

    /**
     * Sets the value of the drugenddateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugenddateformat }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugenddateformat(Drugenddateformat value) {
        this.drugenddateformat = value;
    }

    /**
     * Gets the value of the drugenddate property.
     * 
     * @return
     *     possible object is
     *     {@link Drugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugenddate getDrugenddate() {
        return drugenddate;
    }

    /**
     * Sets the value of the drugenddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugenddate }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugenddate(Drugenddate value) {
        this.drugenddate = value;
    }

    /**
     * Gets the value of the drugtreatmentduration property.
     * 
     * @return
     *     possible object is
     *     {@link Drugtreatmentduration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugtreatmentduration getDrugtreatmentduration() {
        return drugtreatmentduration;
    }

    /**
     * Sets the value of the drugtreatmentduration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugtreatmentduration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugtreatmentduration(Drugtreatmentduration value) {
        this.drugtreatmentduration = value;
    }

    /**
     * Gets the value of the drugtreatmentdurationunit property.
     * 
     * @return
     *     possible object is
     *     {@link Drugtreatmentdurationunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugtreatmentdurationunit getDrugtreatmentdurationunit() {
        return drugtreatmentdurationunit;
    }

    /**
     * Sets the value of the drugtreatmentdurationunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugtreatmentdurationunit }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugtreatmentdurationunit(Drugtreatmentdurationunit value) {
        this.drugtreatmentdurationunit = value;
    }

    /**
     * Gets the value of the actiondrug property.
     * 
     * @return
     *     possible object is
     *     {@link Actiondrug }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Actiondrug getActiondrug() {
        return actiondrug;
    }

    /**
     * Sets the value of the actiondrug property.
     * 
     * @param value
     *     allowed object is
     *     {@link Actiondrug }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setActiondrug(Actiondrug value) {
        this.actiondrug = value;
    }

    /**
     * Gets the value of the drugrecurreadministration property.
     * 
     * @return
     *     possible object is
     *     {@link Drugrecurreadministration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugrecurreadministration getDrugrecurreadministration() {
        return drugrecurreadministration;
    }

    /**
     * Sets the value of the drugrecurreadministration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugrecurreadministration }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugrecurreadministration(Drugrecurreadministration value) {
        this.drugrecurreadministration = value;
    }

    /**
     * Gets the value of the drugadditional property.
     * 
     * @return
     *     possible object is
     *     {@link Drugadditional }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public Drugadditional getDrugadditional() {
        return drugadditional;
    }

    /**
     * Sets the value of the drugadditional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drugadditional }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setDrugadditional(Drugadditional value) {
        this.drugadditional = value;
    }

    /**
     * Gets the value of the activesubstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activesubstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivesubstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Activesubstance }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Activesubstance> getActivesubstance() {
        if (activesubstance == null) {
            activesubstance = new ArrayList<Activesubstance>();
        }
        return this.activesubstance;
    }

    /**
     * Gets the value of the drugrecurrence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugrecurrence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugrecurrence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drugrecurrence }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Drugrecurrence> getDrugrecurrence() {
        if (drugrecurrence == null) {
            drugrecurrence = new ArrayList<Drugrecurrence>();
        }
        return this.drugrecurrence;
    }

    /**
     * Gets the value of the drugreactionrelatedness property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugreactionrelatedness property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugreactionrelatedness().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drugreactionrelatedness }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2012-08-28T03:28:08-04:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Drugreactionrelatedness> getDrugreactionrelatedness() {
        if (drugreactionrelatedness == null) {
            drugreactionrelatedness = new ArrayList<Drugreactionrelatedness>();
        }
        return this.drugreactionrelatedness;
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
