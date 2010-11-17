package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventSeriousness;
import ess.caaers.nci.nih.gov.CtcTerm;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import _21090.org.iso.CD;

public class GridToDomainObjectConverterTest extends CaaersDbNoSecurityTestCase{
	
	private GridToDomainObjectConverter gridToDomainObjectConverter;
	@Override
	public void setUp () throws Exception {
		super.setUp();
		gridToDomainObjectConverter = (GridToDomainObjectConverter)getApplicationContext().getBean("gridToDomainObjectConverter");
	}
	
	public void testConvertAdverseEvent() {
		AdverseEvent g = new AdverseEvent();
		g.setComment(ISO21090Helper.ST("comment"));
		g.setResolutionDate(ISO21090Helper.TSDateTime("20101102000000"));
		g.setLocationDescription(ISO21090Helper.ST("location"));
		g.setExpectedIndicator(ISO21090Helper.BL(true));
		g.setGradeCode(ISO21090Helper.CD("3"));
		g.setReportedDate(ISO21090Helper.TSDateTime("20101002000000"));
		g.setHospitalizationRequiredIndicator(ISO21090Helper.BL(false));
		g.setPostReportUpdateDate(ISO21090Helper.TSDateTime("20101106000000"));
		g.setIdentifier(ISO21090Helper.II("123"));
		g.setOnsetDate(ISO21090Helper.TSDateTime("20101102000000"));
		CD cd = new CD();
		cd.setOriginalText(ISO21090Helper.EDText("result"));
		g.setResult(cd);
		g.setProbabilityCode(ISO21090Helper.CD("2"));
		
		AdverseEventSeriousness aes = new AdverseEventSeriousness();
		CD cd1 = ISO21090Helper.CD("4");
		cd1.setOriginalText(ISO21090Helper.EDText("other "));

		aes.setCode(cd1);
		AdverseEventSeriousness[] aesList = new AdverseEventSeriousness[1];
		aesList[0] = aes;
		g.setAdverseEventSeriousness(aesList);
		
		
		
		CtcTerm ctcTerm = new CtcTerm();
		CD cd2 = ISO21090Helper.CD("");
		cd2.setOriginalText(ISO21090Helper.EDText("Tinnitus"));
		ctcTerm.setCtepCode(cd2);
		g.setAdverseEventCtcTerm(ctcTerm);
		/*
		StringWriter s = new StringWriter();
		try {
			Utils.serializeObject(g, new QName(AdverseEventConstants.SERVICE_NS,"AdverseEvent"), s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s.toString());
		*/
		gridToDomainObjectConverter.convertAdverseEvent(g);
		
		//Registration registration=(Registration)Utils.deserializeObject(new FileReader(xmlPath), Registration.class, new FileInputStream(new File(clientConfigPath)));		
		
	}

}
