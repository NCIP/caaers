/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventSeriousness;
import ess.caaers.nci.nih.gov.CtcTerm;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import _21090.org.iso.CD;

public class AdverseEventQueryImplTest extends CaaersDbNoSecurityTestCase{
	
	private AdverseEventQueryImpl adverseEventQueryImpl;
	@Override
	public void setUp () throws Exception {
		super.setUp();
		adverseEventQueryImpl = (AdverseEventQueryImpl)getApplicationContext().getBean("adverseEventQueryImpl");
	}
	
	public void testGet() {
		adverseEventQueryImpl.findAdverseEvents(getDataWithHospitalization());

	}
	
	public AdverseEvent getDataWithComments() {
		AdverseEvent g = new AdverseEvent();
		g.setComment(ISO21090Helper.ST("That was some other big AE SR"));
		//g.setIdentifier(ISO21090Helper.II("2"));
		//g.setHospitalizationRequiredIndicator(ISO21090Helper.BL(true));
		return g;
	}
	
	public AdverseEvent getDataWithHospitalization() {
		AdverseEvent g = new AdverseEvent();
		g.setHospitalizationRequiredIndicator(ISO21090Helper.BL(true));
		g.setOnsetDate(ISO21090Helper.TSDateTime("20070912123500"));
		g.setResolutionDate(ISO21090Helper.TSDateTime("20070912123500"));
		g.setGradeCode(ISO21090Helper.CD("5"));
		g.setProbabilityCode(ISO21090Helper.CD("3"));
		CtcTerm ctc = new CtcTerm();
		CD cd = new CD ();
		cd.setCode("10014004");
		cd.setOriginalText(ISO21090Helper.EDText("Ear disorder"));
		ctc.setCtepTerm(cd);
		g.setAdverseEventCtcTerm(ctc);
		return g;
	}
	
	public AdverseEvent getData() {
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
		ctcTerm.setCtepTerm(cd2);
		g.setAdverseEventCtcTerm(ctcTerm);
		
		return g;		
	}

}
