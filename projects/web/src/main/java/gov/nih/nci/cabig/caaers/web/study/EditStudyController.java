package gov.nih.nci.cabig.caaers.web.study;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public class EditStudyController extends StudyController<Study> {

	private static final Log log = LogFactory.getLog(EditStudyController.class);

	public EditStudyController() {
		setBindOnNewForm(true);
	}

	///LOGIC

    @Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));
        if(log.isDebugEnabled()) log.debug("Retrieved Study :" + String.valueOf(study));

        //update the INDType of StudyAgents
        if(study.getStudyAgentsInternal() != null && study.getStudyAgentsInternal().size() > 0){
        	for(StudyAgent sa : study.getStudyAgentsInternal()){
        		//update the IND Type.
        		List<StudyAgentINDAssociation> sas  = sa.getStudyAgentINDAssociationsInternal();
        		if(sas == null || sas.isEmpty()) sa.setIndType(0);
        		else if(sas.get(0).getInvestigationalNewDrug().getIndNumber() == -111) sa.setIndType(1);
        		else sa.setIndType(2);

        	}
        }

        return study;
    }

    @Override
	protected Study save(Study study, Errors errors) {
    	if( errors.hasErrors()) return study;
    	Study mergedStudy = getDao().merge(study);
    	mergedStudy.setStudySiteIndex(study.getStudySiteIndex());
    	getDao().initialize(mergedStudy);
    	getDao().save(mergedStudy);
    	return mergedStudy;
    }


	@Override
	protected boolean isSummaryEnabled() {
        return true;
    }

	@Override
	protected void layoutTabs(Flow<Study> flow) {
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
        flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
    		Object command, BindException errors) throws Exception {
    	 Study study = (Study) command;
    	 studyDao.merge(study);
        return new ModelAndView(new RedirectView("search"));
    }

	@Override
	protected boolean shouldSave(HttpServletRequest request, Study command, Tab<Study> tab) {
		return super.shouldSave(request, command, tab) &&
		tab.getNumber() != 0; //dont save if it is overview page
	}


}