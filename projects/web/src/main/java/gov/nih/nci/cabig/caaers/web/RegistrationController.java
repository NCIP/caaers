package gov.nih.nci.cabig.caaers.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

//import gov.nih.nci.cabig.caaers.dao.ArmDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
//import gov.nih.nci.cabig.caaers.domain.Arm;
//import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Participant;
//import gov.nih.nci.cabig.caaers.domain.ScheduledArm;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
//import gov.nih.nci.cabig.caaers.utils.web.ControllerTools;

public class RegistrationController extends AbstractWizardFormController {
    private static Log log = LogFactory.getLog(RegistrationController.class);

    private final String[] pages={"reg_submit"};
    private final String[] viewNames={"reviewAndSubmitView"};
	private ParticipantDao participantDao;
	private StudySiteDao studySiteDao;
	//private ArmDao armDao;

	public RegistrationController() {
		setPages(pages);
		setCommandClass(StudyParticipantAssignment.class);
	}
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        //ControllerTools.registerDomainObjectEditor(binder, armDao);
    }

	@Override
	protected boolean isFinishRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(request.getParameter("nextView")==null||request.getParameter("nextView").equals(""))
			return false;
		String viewName=request.getParameter("nextView");
		if(viewName.equalsIgnoreCase("processFinish"))
			return true;
		return false;
	}
	
	public StudySiteDao getStudySiteDao() {
		return studySiteDao;
	}

	public void setStudySiteDao(StudySiteDao studySiteDao) {
		this.studySiteDao = studySiteDao;
	}

	@Override
	protected int getTargetPage(HttpServletRequest request, int no) {
		System.out.println("getTargetPage() function called....");
		String viewName=request.getParameter("nextView");
		for(int i=0 ;i< viewNames.length ; i++){
			if(viewNames[i].equals(viewName)){
				System.out.println("ViewName in request is : "+ viewName+"at index "+i);
				return i;
			}
		}
		return 0;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getTargetPage() function called....");
		StudyParticipantAssignment studyParticipantAssignment= new StudyParticipantAssignment();
		//studyParticipantAssignment.setStudyParticipantIdentifier("TESTID_Kruttik_BIG");
		//studyParticipantAssignment.setStartDate(new Date());
		//studyParticipantAssignment.setEligibilityIndicator(new Boolean(false));
		StudySite studySite=null;
		Participant participant=null;
		if(request.getParameter("studySiteId")!=null && request.getParameter("participantId")!=null){
			System.out.println("Parameters found as.."+request.getParameter("studySiteId")+"  and "+request.getParameter("participantId"));
			if(studySiteDao!=null){
				studySite=studySiteDao.getById(Integer.parseInt(request.getParameter("studySiteId")));
				//System.out.println("RoleCode = "+studySite.getRoleCode());
			}
			else
				System.out.println("studySiteDao is null");
			if(participantDao!=null){
				participant=participantDao.getById(Integer.parseInt(request.getParameter("participantId")));
				System.out.println("First Name = "+participant.getFirstName());
			}
			else
				System.out.println("participantDao is null");
			int size=participant.getAssignments().size();
			System.out.println("-------------participant.getAssignments().size() is "+size+"---------------");
			int size1=studySite.getStudyParticipantAssignments().size();
			System.out.println("-------------studySite.getStudyParticipantAssignments().size() is "+size1+"---------------");
			System.out.println("Kr" + studySite.getStudy().getShortTitle());
			studyParticipantAssignment.setStudySite(studySite);
			studyParticipantAssignment.setParticipant(participant);
			studyParticipantAssignment.setDateOfEnrollment(new Date());
		}
		
		return studyParticipantAssignment;
	}
	
	protected Map<String, Object> referenceData(HttpServletRequest httpServletRequest) throws Exception {
    	// Currently the static data is a hack, once DB design is approved for an LOV this will be
    	// replaced with LOVDao to get the static data from individual tables
    	Map<String, Object> refdata = new HashMap<String, Object>();
        return refdata;
    }

	
	private List<StringBean> getRandomizedList(){
		List<StringBean> col = new ArrayList<StringBean>();		
    	col.add(new StringBean("Y"));
    	col.add(new StringBean("N"));
    	return col;
	}
	
	public class LOV {
		
		private String code;
		private String desc;
		
		LOV(String code, String desc)
		{
			this.code=code;
			this.desc=desc;
			
		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDesc(){
			return desc;
		}
			
		public void setDesc(String desc){
			this.desc=desc;
		}
	}
	
	public class StringBean {
	
		String str;
		
		StringBean(String str)
		{
			this.str=str;
		}
		
		public void setStr(String str){
			this.str=str;
		}
		
		public String getStr(){
			return str;
		}
		
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException arg3) throws Exception {
		log.debug("In process Finish...");
		
		StudyParticipantAssignment studyParticipantAssignment=(StudyParticipantAssignment)command;
		int size1=studyParticipantAssignment.getStudySite().getStudyParticipantAssignments().size();
		System.out.println("-------------studySite.getStudyParticipantAssignments().size() is "+size1+"---------------");
		studyParticipantAssignment.getParticipant().addStudyParticipantAssignment(studyParticipantAssignment);
		participantDao.save(studyParticipantAssignment.getParticipant());
		response.sendRedirect("home");
		return null;
	}


	public ParticipantDao getParticipantDao() {
		return participantDao;
	}


	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
	/*
	public ArmDao getArmDao() {
		return armDao;
	}
	public void setArmDao(ArmDao armDao) {
		this.armDao = armDao;
	}*/
}