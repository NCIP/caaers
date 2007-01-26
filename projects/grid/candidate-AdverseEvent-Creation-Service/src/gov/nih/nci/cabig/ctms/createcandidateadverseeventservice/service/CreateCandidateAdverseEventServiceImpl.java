package gov.nih.nci.cabig.ctms.createcandidateadverseeventservice.service;

import gov.nih.nci.caaers.grid.beans.StudySubjectAssignment;
import gov.nih.nci.cabig.caaers.api.AdverseEventService;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class CreateCandidateAdverseEventServiceImpl extends CreateCandidateAdverseEventServiceImplBase {

	private ApplicationContext ctx;
	private AdverseEventService aeService;
	
	public CreateCandidateAdverseEventServiceImpl() throws RemoteException {
		super();
        this.ctx = new ClassPathXmlApplicationContext(
                        new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
        aeService = (AdverseEventService)ctx.getBean("adverseEventServiceAPI");
	}
	

	public java.lang.String processMessage(gov.nih.nci.caaers.grid.beans.AdverseEvent newAdverseEventMessage) throws RemoteException, gov.nih.nci.cabig.ctms.createcandidateadverseeventservice.stubs.types.CandidateAdverseEventCreationException {
		StudySubjectAssignment studySubject=  newAdverseEventMessage.getStudySubjectAssignment();
		gov.nih.nci.caaers.grid.beans.Study study = studySubject.getStudy();
		//Constructing caAERS Study
		Study caaersStudy = new Study();
		Identifier studyId = new Identifier();
		studyId.setSource(study.getAssigningAuthority());
		studyId.setType(study.getName());
		studyId.setValue(study.getIdentifier());
		String mrn = studySubject.getStudySubjectIdentifier();
		caaersStudy.addIdentifier(studyId);
		
		//Constructing the Participant
		Participant subject = new Participant();
		Identifier id = new Identifier();
		id.setType("MRN");
		id.setValue(mrn);
		List<Identifier> identifiers = new ArrayList<Identifier>();
		identifiers.add(id);
		subject.setIdentifiers(identifiers);
		
		//Create Site
		Site site = null;
		
		gov.nih.nci.caaers.grid.beans.Activity[] activities = studySubject.getActivity();
		gov.nih.nci.caaers.grid.beans.Activity firstActivity = activities[0];
		
		gov.nih.nci.caaers.grid.beans.LabTest[] tests = firstActivity.getLabTest();
		//LabTest firstLabTest = tests[0];
		//LabResult result = firstLabTest.getLabResult();
		
		
		List<Lab> labs = new ArrayList<Lab>();
		for (int i = 0; i < tests.length; i++) {
			Lab lab = new Lab();
			gov.nih.nci.caaers.grid.beans.LabResult labResult = new gov.nih.nci.caaers.grid.beans.LabResult();
			gov.nih.nci.caaers.grid.beans.LabTest test = tests[i];
			gov.nih.nci.caaers.grid.beans.LabResult result = test.getLabResult();
			lab.setName(test.getCode());
			LabValue nadirLabValue = new LabValue();
			nadirLabValue.setValue(result.getTextResult());
			lab.setNadir(nadirLabValue);
			lab.setUnits(result.getUnitOfMeasureCode());
			labs.add(lab);
			
		}
		AdverseEvent ae = new AdverseEvent();
		ae.setDetectionDate(firstActivity.getEndDateTime().getTime());
		
		return aeService.createCandidateAdverseEvent(caaersStudy, subject, site, ae, labs);
	}

}

