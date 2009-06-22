package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Date;

/**
 * @author
 */
public class RadiationInterventionTest extends AbstractTestCase {

    private RadiationIntervention radiationIntervention;

    private String treatmentArm;

    private String description;

    private RadiationAdministration administration;

    private String dosage;

    private String dosageUnit;

    private Date lastTreatmentDate;

    // schedule
    private String fractionNumber;

    private String daysElapsed;

    private String adjustment;


    @Override
    protected void setUp() throws Exception {
        super.setUp();


        treatmentArm = "treatment arm";
        description = "desc";
        administration = RadiationAdministration.BT_NOS;
        dosage = "dosage";
        dosageUnit = "unit";
        lastTreatmentDate = new Date();

        lastTreatmentDate = new Date();
        fractionNumber = "fraction number";
        daysElapsed = "days elapsed";
        adjustment = "adjustment";
        radiationIntervention = new RadiationIntervention();

        radiationIntervention.setAdjustment(adjustment);
        radiationIntervention.setAdministration(administration);
        radiationIntervention.setDaysElapsed(daysElapsed);
        radiationIntervention.setDescription(description);
        radiationIntervention.setDosage(dosage);
        radiationIntervention.setDosageUnit(dosageUnit);
        radiationIntervention.setFractionNumber(fractionNumber);
        radiationIntervention.setGridId("grid id");
        radiationIntervention.setLastTreatmentDate(lastTreatmentDate);
        radiationIntervention.setReport(new ExpeditedAdverseEventReport());
        radiationIntervention.setTreatmentArm(treatmentArm);
        radiationIntervention.setVersion(3);


    }

    public void testCopyBasicProperties() {
        RadiationIntervention copiedRadiationIntervention = radiationIntervention.copy();


        assertNull("must not coy id", copiedRadiationIntervention.getId());

        assertNull("must not coy grid id", copiedRadiationIntervention.getGridId());
        assertNull("must not coy version number", copiedRadiationIntervention.getVersion());
        assertNull("must not coy expeditedReport", copiedRadiationIntervention.getReport());
        assertEquals(adjustment, copiedRadiationIntervention.getAdjustment());
        assertEquals(administration, copiedRadiationIntervention.getAdministration());
        assertEquals(daysElapsed, copiedRadiationIntervention.getDaysElapsed());
        assertEquals(description, copiedRadiationIntervention.getDescription());
        assertEquals(dosage, copiedRadiationIntervention.getDosage());
        assertEquals(dosageUnit, copiedRadiationIntervention.getDosageUnit());
        assertEquals(fractionNumber, copiedRadiationIntervention.getFractionNumber());
        assertEquals(lastTreatmentDate, copiedRadiationIntervention.getLastTreatmentDate());
        assertEquals(treatmentArm, copiedRadiationIntervention.getTreatmentArm());


    }

}
