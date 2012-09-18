package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationCommandTest extends TestCase {
    List<AdverseEventDTO> externalAes;
    List<AdverseEventDTO> internalAes;
   public void setUp(){
      externalAes = new ArrayList<AdverseEventDTO>();
      internalAes = new ArrayList<AdverseEventDTO>();
       populateInternalAes(internalAes);
       populateExternalAes(externalAes);
   }
   public void testConstruction(){
       AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
       assertEquals(4, command.getInternalAeList().size());
       assertEquals(6, command.getExternalAeList().size());
       assertEquals(3, command.getUnMappedInternalAeList().size());
       assertEquals(5, command.getUnMappedExternalAeList().size());
       assertEquals(1, command.getMatchedAeMapping().size());



       command.link(14,4);

       assertEquals(4, command.getInternalAeList().size());
       assertEquals(6, command.getExternalAeList().size());
       assertEquals(2, command.getUnMappedInternalAeList().size());
       assertEquals(4, command.getUnMappedExternalAeList().size());
       assertEquals(2, command.getMatchedAeMapping().size()) ;


   }

    public void testLink(){
        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
        assertNotNull(command.find(14, command.getInternalAeList()));
        assertNotNull(command.find(14, command.getUnMappedInternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
        command.link(14,4);
        assertNotNull(command.find(14, command.getInternalAeList()));
        assertNull(command.find(14, command.getUnMappedInternalAeList()));
        assertNull(command.find(4, command.getUnMappedExternalAeList()));
    }

    public void testUnlink(){
        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
        assertNotNull(command.find(14, command.getInternalAeList()));
        assertNotNull(command.find(14, command.getUnMappedInternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
        command.link(14,4);
        assertNotNull(command.find(14, command.getInternalAeList()));
        assertNull(command.find(14, command.getUnMappedInternalAeList()));
        assertNull(command.find(4, command.getUnMappedExternalAeList()));
        command.unlink(14,4);
        assertNotNull(command.find(14, command.getInternalAeList()));
        assertNotNull(command.find(14, command.getUnMappedInternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
    }

    public void testReject(){
        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
        assertTrue(command.getRejectedExternalAeList().isEmpty());
        assertNotNull(command.find(4, command.getExternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
        command.reject(4);
        assertNull(command.find(4, command.getUnMappedExternalAeList()));
        assertNotNull(command.find(4, command.getRejectedExternalAeList()));
    }


    public void testUnreject(){
        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
        assertTrue(command.getRejectedExternalAeList().isEmpty());
        assertNotNull(command.find(4, command.getExternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
        command.reject(4);
        assertNull(command.find(4, command.getUnMappedExternalAeList()));
        assertNotNull(command.find(4, command.getRejectedExternalAeList()));
        command.unreject(4);
        System.out.println(command.getRejectedExternalAeList());
        assertTrue(command.getRejectedExternalAeList().isEmpty());
        assertNotNull(command.find(4, command.getExternalAeList()));
        assertNotNull(command.find(4, command.getUnMappedExternalAeList()));
    }
    
    private AdverseEventDTO mockAdverseEvent(int id, int termId, String termCode, String term, String grade, String startDate, String endDate, String verbatim, String whySerious, String attibution){
        AdverseEventDTO dto = new AdverseEventDTO();
        dto.setId(id);
        TermDTO t = new TermDTO();
        t.setId(termId);
        t.setCode(termCode);
        t.setName(term);
        dto.setTerm(t);
        dto.setGrade(grade);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setVerbatim(verbatim);
        dto.setWhySerious(whySerious);
        dto.setAttribution(attibution);
        return dto;
    }

    private void populateInternalAes(List<AdverseEventDTO> aeList){
        AdverseEventDTO dto = null;


        dto = mockAdverseEvent(12, 102, "5490", "Nausea", "Severe", "10/09/2011", "10/11/2011", "Redness in left eye", "", "Probable");
        dto.setSource("caAERS");
        dto.setExternalID("92");
        aeList.add(dto);

        dto = mockAdverseEvent(14, 104, "5492", "Lymphopenia", "Moderate", "10/09/2011", "10/13/2011", "", "", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);

        dto = mockAdverseEvent(15, 205, "7493", "Proctitis", "Mild", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);

        dto = mockAdverseEvent(16, 706, "6496", "Alkaline phosphatase", "Moderate", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);
    }
    private void populateExternalAes(List<AdverseEventDTO> aeList){
        AdverseEventDTO dto = null;

        dto = mockAdverseEvent(1, 101, "4490", "Pain", "Mild", "10/10/2011", "10/11/2011", "Redness in eye", "Hospitalized", "Likely");
        dto.setSource("Force");
        dto.setExternalID("91");
        aeList.add(dto);

        dto = mockAdverseEvent(2, 102, "5490", "Nausea", "Severe", "10/09/2011", "10/12/2011", "Red eye", "", "Probable");
        dto.setSource("Force");
        dto.setExternalID("92");
        aeList.add(dto);

        dto = mockAdverseEvent(3, 103, "5491", "Vomiting", "Severe", "10/09/2011", "10/10/2011", "Red neck", "", "Probable");
        dto.setSource("Force");
        dto.setExternalID("93");
        aeList.add(dto);

        dto = mockAdverseEvent(4, 104, "5492", "Lymphopenia", "Moderate", "10/09/2011", "10/13/2011", "", "", "Definite");
        dto.setSource("Force");
        dto.setExternalID("94");
        aeList.add(dto);

        dto = mockAdverseEvent(5, 105, "5493", "Fistula-intestinal", "Mild", "10/09/2011", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("Force");
        dto.setExternalID("95");
        aeList.add(dto);

        dto = mockAdverseEvent(6, 106, "5496", "Gastritis", "Mild", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("Force");
        dto.setExternalID("96");
        aeList.add(dto);
    }
}
