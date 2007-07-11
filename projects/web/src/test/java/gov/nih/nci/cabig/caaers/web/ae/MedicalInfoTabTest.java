package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class MedicalInfoTabTest extends AeTabTestCase {
    private ConfigProperty configurationProperty;

    @Override
    protected void setUp() throws Exception {
        // TODO: this makes me stupider for having done it
        configurationProperty = new ConfigProperty();
        configurationProperty.setMap(new HashMap<String, List<Lov>>());
        configurationProperty.getMap().put("bpsRefData",
            createMirroredLov("0 = zero", "1 = one", "2 = two"));
        configurationProperty.getMap().put("heightUnitsRefData",
            createMirroredLov("Inch", "Centimeter"));
        configurationProperty.getMap().put("weightUnitsRefData",
            createMirroredLov("Pound", "Kilogram"));
        super.setUp();
    }

    private List<Lov> createMirroredLov(String... values) {
        List<Lov> lov = new ArrayList<Lov>(values.length);
        for (String value : values) {
            lov.add(new Lov(value, value));
        }
        return lov;
    }

    @Override
    protected AeTab createTab() {
        MedicalInfoTab tab = new MedicalInfoTab();
        tab.setConfigurationProperty(configurationProperty);
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getDiseaseHistory().addMetastaticDiseaseSite(new MetastaticDiseaseSite());
    }

    public void testParticipantFieldsPresent() throws Exception {
        assertFieldProperties("participant",
            "aeReport.participantHistory.height",
            "aeReport.participantHistory.heightUnitOfMeasure",
            "aeReport.participantHistory.weight",
            "aeReport.participantHistory.weightUnitOfMeasure",
            "aeReport.participantHistory.baselinePerformanceStatus"
        );
    }

    public void testStaticDiseaseFieldsPresent() throws Exception {
        assertFieldProperties("disease",
            "aeReport.diseaseHistory.ctepStudyDisease",
            "aeReport.diseaseHistory.otherPrimaryDiseaseCode",
            "aeReport.diseaseHistory.anatomicSite",
            "aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode",
            "aeReport.diseaseHistory.dateOfInitialPathologicDiagnosis"
        );
    }

    public void testMetastaticDiseaseFieldsPresent() throws Exception {
        assertFieldProperties("metastatic3",
            "aeReport.diseaseHistory.metastaticDiseaseSite[3].anatomicSite",
            "aeReport.diseaseHistory.metastaticDiseaseSite[3].otherMetastaticDiseaseSite"
        );
    }

    public void testBaselineOptions() throws Exception {
        //  TODO: baseline status is going to change into an enum
        Map<Object,Object> actualOptions = getActualSelectFieldOptions(
            "participant", "aeReport.participantHistory.baselinePerformanceStatus");
        assertEquals("Wrong number of options: " + actualOptions, 4, actualOptions.size());
        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
        Map.Entry<Object, Object> entry = iterator.next();
        assertKeyAndValue("Null value missing", "", "Please select", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 0th option",
            "0 = zero", "0 = zero", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 1st option",
            "1 = one", "1 = one", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 2nd option",
            "2 = two", "2 = two", entry);
        assertFalse(iterator.hasNext());
    }

    public void testHeightOptions() throws Exception {
        Map<Object,Object> actualOptions = getActualSelectFieldOptions(
            "participant", "aeReport.participantHistory.heightUnitOfMeasure");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
        Map.Entry<Object, Object> entry = iterator.next();
        assertKeyAndValue("Wrong 0th option",
            "Inch", "Inch", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 1st option",
            "Centimeter", "Centimeter", entry);
        assertFalse(iterator.hasNext());
    }

    public void testWeightOptions() throws Exception {
        Map<Object,Object> actualOptions = getActualSelectFieldOptions(
            "participant", "aeReport.participantHistory.weightUnitOfMeasure");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
        Map.Entry<Object, Object> entry = iterator.next();
        assertKeyAndValue("Wrong 0th option",
            "Pound", "Pound", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 1st option",
            "Kilogram", "Kilogram", entry);
        assertFalse(iterator.hasNext());
    }

    private static <K, V> void assertKeyAndValue(String message, K expectedKey, V expectedValue, Map.Entry<K, V> actual) {
        assertEquals(message + ": wrong key", expectedKey, actual.getKey());
        assertEquals(message + ": wrong value", expectedValue, actual.getValue());
    }
}
