package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Date;

/**
 * @author
 */
public class SurgeryInterventionTest extends AbstractTestCase {

    private SurgeryIntervention surgeryIntervention;

    private String treatmentArm;

    private String description;


    private Date interventionDate;
    private InterventionSite interventionSite;


    @Override
    protected void setUp() throws Exception {
        super.setUp();


        treatmentArm = "treatment arm";
        description = "desc";
        surgeryIntervention = new SurgeryIntervention();

        surgeryIntervention.setDescription(description);
        surgeryIntervention.setId(1);
        interventionDate = new Date();
        surgeryIntervention.setInterventionDate(interventionDate);
        interventionSite = new InterventionSite();
        surgeryIntervention.setInterventionSite(interventionSite);

        surgeryIntervention.setGridId("grid id");
        surgeryIntervention.setReport(new ExpeditedAdverseEventReport());
        surgeryIntervention.setTreatmentArm(treatmentArm);
        surgeryIntervention.setVersion(3);


    }

    public void testCopyBasicProperties() {
        SurgeryIntervention copiedSurgeryIntervention = surgeryIntervention.copy();


        assertNull("must not coy id", copiedSurgeryIntervention.getId());

        assertNull("must not coy grid id", copiedSurgeryIntervention.getGridId());
        assertNull("must not coy version number", copiedSurgeryIntervention.getVersion());
        assertNull("must not coy expeditedReport", copiedSurgeryIntervention.getReport());
        assertEquals(interventionDate, copiedSurgeryIntervention.getInterventionDate());
        assertEquals(interventionSite, copiedSurgeryIntervention.getInterventionSite());
        assertSame("interventionSite must refer same object", interventionSite, copiedSurgeryIntervention.getInterventionSite());
        assertEquals(description, copiedSurgeryIntervention.getDescription());
        assertEquals(treatmentArm, copiedSurgeryIntervention.getTreatmentArm());


    }

}
