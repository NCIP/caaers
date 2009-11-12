package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;

import org.easymock.classextension.EasyMock;

import java.util.List;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;

public class BlankFormGeneratorTest extends CaaersTestCase {
	private BlankFormGenerator g;
    StudyDao studyDao;
    Study s;
    Epoch e;
    String PDFFile;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");

        PDFFile = System.getProperty("java.io.tmpdir") + File.separator + "AE-Blank-Test.pdf";
        g = new BlankFormGenerator();
        s = new LocalStudy();
        s.setShortTitle("ST");
        s.setLongTitle("LT");
        s.setId(55588);
        
        List<Identifier> i = new ArrayList<Identifier>();
        Identifier id = new Identifier();
        id.setPrimaryIndicator(true);
        id.setValue("1222");
        i.add(id);
        s.setIdentifiers(i);

        e = new Epoch();
        e.setName("PT");
        e.setDescriptionText("DT");
        e.setId(88);

        SolicitedAdverseEvent sae = new SolicitedAdverseEvent();
        s.addEpoch(e);
        e.addArm(new Arm());
        List<SolicitedAdverseEvent> sael = new ArrayList<SolicitedAdverseEvent>();
        SolicitedAdverseEvent sae1 = new SolicitedAdverseEvent();
        sae1.setCtcterm(new CtcTerm());
        sae1.getCtcterm().setTerm("Nausea");
        sael.add(sae1);

        sae1 = new SolicitedAdverseEvent();
        sae1.setCtcterm(new CtcTerm());
        sae1.getCtcterm().setTerm("Bone Pain");
        sael.add(sae1);

        e.getArms().get(0).setSolicitedAdverseEvents(sael);
	}

	public void testGenerateCaaersXml() throws Exception {
        g.generatePdf(g.serialize(s, e), PDFFile);
        File f = new File(PDFFile);
        assertTrue(f.exists());
        assertEquals("AE-Blank-Test.pdf", f.getName());
        assertTrue(f.length() > 0);
	}

//    public void testXMLgeneration() {
//        try {
//            String xml = g.serialize(s, e);
//            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:studies xmlns:ns2=\"http://webservice.caaers.cabig.nci.nih.gov\"><ns2:study id=\"55588\"><shortTitle>ST</shortTitle><longTitle>LT</longTitle><multiInstitutionIndicator>false</multiInstitutionIndicator><adeersReporting>false</adeersReporting><drugAdministrationTherapyType>false</drugAdministrationTherapyType><deviceTherapyType>false</deviceTherapyType><radiationTherapyType>false</radiationTherapyType><surgeryTherapyType>false</surgeryTherapyType><behavioralTherapyType>false</behavioralTherapyType><biologicalTherapyType>false</biologicalTherapyType><geneticTherapyType>false</geneticTherapyType><dietarySupplementTherapyType>false</dietarySupplementTherapyType><otherTherapyType>false</otherTherapyType><identifiers><ns2:systemAssignedIdentifier><value>1222</value><primaryIndicator>true</primaryIndicator></ns2:systemAssignedIdentifier></identifiers><evaluationPeriods><ns2:evaluationPeriod><name>PT</name><descriptionText>DT</descriptionText><epochOrder>0</epochOrder><solicitedAdverseEvents><ns2:solicitedAdverseEvent><name>Nausea</name></ns2:solicitedAdverseEvent><ns2:solicitedAdverseEvent><name>Bone Pain</name></ns2:solicitedAdverseEvent></solicitedAdverseEvents></ns2:evaluationPeriod></evaluationPeriods></ns2:study></ns2:studies>".trim(), xml.trim());
//          
//        } catch (Exception e1) {
//            e1.printStackTrace();  
//        }
//
//    }

}
