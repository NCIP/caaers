package gov.nih.nci.cabig.caaers.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

//import SearchAndRegisterController.LOV;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.StudyService;

public class SearchStudyRegisterController extends SimpleFormController {
	private static Log log = LogFactory
			.getLog(SearchStudyRegisterController.class);

	private StudyService studyService;
	
	public SearchStudyRegisterController() {
		setCommandClass(SearchStudyCommand.class);
		setFormView("reg_enroll_study");
		setSuccessView("reg_study_search");
	}

	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {
		SearchStudyCommand searchRegisterCommand = (SearchStudyCommand) oCommand;
		String type=searchRegisterCommand.getSearchType();
		String searchtext=searchRegisterCommand.getSearchTypeText();
		Study study = new Study();
		System.out
				.println("-----------------------------------------------------------------------");
		System.out.println("type: " + type);
		System.out.println("searchText: " + searchtext);
		System.out
				.println("-----------------------------------------------------------------------");
		log.debug("search string = " + searchtext + "; type = " + type);

		// Populate the Study Object with properties
		if ("st".equals(type))
			study.setShortTitle(searchtext);
		else if ("lt".equals(type))
			study.setLongTitle(searchtext);
		else if ("d".equals(type))
			study.setDescription(searchtext);
//		else if ("pic".equals(type))
//			study.setPrincipalInvestigatorCode(searchtext);
//		else if ("pin".equals(type))
//			study.setPrincipalInvestigatorName(searchtext);
		else if ("psc".equals(type))
			study.setPrimarySponsorCode(searchtext);
//		else if ("psn".equals(type))
//			study.setPrimarySponsorName(searchtext);
		else if ("pc".equals(type))
			study.setPhaseCode(searchtext);

		List<Study> studies = studyService.search(study);
		if (studies == null || studies.size() == 0) {
			System.out
					.println("----------------------studies is null----------------------");
			studies = new ArrayList<Study>();
			Study temp = new Study();
			temp.setId(0);
			temp.setShortTitle("CALGB_TEST_MOCK");
		} else
			System.out
					.println("----------------------studies is not null----------------------");
		log.debug("Search results size " + studies.size());
		for (int i = 0; i < studies.size(); i++) {
			if (studies.get(i).getStudySites() == null
					|| studies.get(i).getStudySites().size() == 0) {
				System.out.println("removing study[" + i + "] from studies");
				studies.remove(i);
				i--;
			}
		}
		log.debug("Search results size after filtering " + studies.size());
		System.out.println("Search results size " + studies.size());
		Map map = errors.getModel();
		map.put("studies", studies);
		map.put("searchType", getSearchType());
    	map.put("participantId", request.getParameter("participantId"));		
		ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
		return modelAndView;
	}

	protected Map<String, Object> referenceData(
			HttpServletRequest httpServletRequest) throws Exception {
		Map<String, Object> refdata = new HashMap<String, Object>();

		refdata.put("searchType", getSearchType());
		return refdata;
	}
	
	private List<LOV> getSearchType() {
		List<LOV> col = new ArrayList<LOV>();
		LOV lov1 = new LOV("st",  "Short Title");
		LOV lov2 = new LOV("lt",  "Long Title");
		LOV lov3 = new LOV("d",   "Description");
		LOV lov4 = new LOV("pic", "Principal Investigator Code");
		LOV lov5 = new LOV("pin", "Principal Investigator Name");
		LOV lov6 = new LOV("psc", "Primary Sponsor Code");
		LOV lov7 = new LOV("psn", "Primary Sponsor Name");
		LOV lov8 = new LOV("pc",  "Phase Code");
		
		col.add(lov1);
		col.add(lov2);
		col.add(lov3);
		col.add(lov4);
		col.add(lov5);
		col.add(lov6);
		col.add(lov7);
		col.add(lov8);

		return col;
	}

	public class LOV {

		private String code;

		private String desc;

		LOV(String code, String desc) {
			this.code = code;
			this.desc = desc;

		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}