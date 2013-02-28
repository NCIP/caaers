/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.query.StudyHavingStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudySitesQuery;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchStudyAjaxFacade {

    private Class<?>[] CONTROLLERS = {AssignParticipantController.class};
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private StudyRepository studyRepository;
    private boolean coppaMode;
    private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

    public List<StudyAjaxableDomainObject> getStudiesTable(Map parameterMap, String type, String text, HttpServletRequest request) {
/*
        StudyQuery sq = new StudyQuery();

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        String sType;
        String sText;
        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();

            if ("idtf".equals(sType)) {
                sq.filterByIdentifierValue(sText);
            } else if ("st".equals(sType)) {
                sq.filterByShortTitle(sText);
            }
        }
*/

//        List<Study> studies = studyRepository.search(sq, type, text, coppaMode);
        List<Study> studies = studyRepository.getAllStudiesByShortTitleOrIdentifiers(text);

        List<StudyAjaxableDomainObject> rs = new ArrayList<StudyAjaxableDomainObject>();
        for (Study s : studies) {
            StudyAjaxableDomainObject as = new StudyAjaxableDomainObject();
            as.setId(s.getId());
            as.setStatus(s.getStatus());
            as.setShortTitle(s.getShortTitle());
            as.setPrimaryIdentifierValue(s.getPrimaryIdentifierValue());
            as.setPhaseCode(s.getPhaseCode());
            as.setPrimarySponsorCode(s.getPrimarySponsorCode());
            as.setExternalId(s.getExternalId() != null ? s.getExternalId().trim() : "");
            as.setFundingSponsorIdentifierValue(s.getFundingSponsorIdentifierValue());
            as.setDataEntryStatus(s.getDataEntryStatus() != null ? s.getDataEntryStatus() : false);
            rs.add(as);
        }
        return rs;
        
    }

    // Create Subject flow
    public List<StudyAjaxableDomainObject> getStudiesForCreateParticipant(Map parameterMap, String type, String text, String nciCode, HttpServletRequest request) {

        List<Study> studies; 
        List<StudyAjaxableDomainObject> rs = new ArrayList<StudyAjaxableDomainObject>();
        
        if (StringUtils.isNotEmpty(text)) {
            StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
            query.joinStudyOrganization();
            query.filterByDataEntryStatus(true);
            query.filterByStudySiteNciInstituteCode(nciCode);
            query.filterByShortTitleOrIdentifiers(text);
            query.filterBySST();
            query.orderBy("s.id DESC");
            studies = studyRepository.find(query);

            for (Study s : studies) {
                StudyAjaxableDomainObject sado = new StudyAjaxableDomainObject();
                sado.setId(s.getId());
                sado.setShortTitle(s.getShortTitle());
                sado.setStatus(s.getStatus());
                sado.setDataEntryStatus(s.getDataEntryStatus());
                sado.setPrimaryIdentifierValue(s.getPrimaryIdentifierValue());
                sado.setPhaseCode(s.getPhaseCode());
                sado.setPrimarySponsorCode(s.getPrimarySponsorCode());
                sado.setFundingSponsorIdentifierValue(s.getFundingSponsorIdentifierValue());
                rs.add(sado);
            }
        }

        return rs;
    }

    // ASSIGN Study Search
    public List<StudySiteAjaxableDomainObject> getTableForAssignParticipant(Map parameterMap, String type, String text, HttpServletRequest request) {
        int organizationID;
        try {
            organizationID = Integer.parseInt((String) parameterMap.get("organizationID"));
        } catch (Exception e) {
            organizationID = 0;
        }

        List<StudySite> studySites = getStudySites(text, organizationID, true);
        List<StudySiteAjaxableDomainObject> rs = new ArrayList<StudySiteAjaxableDomainObject>();

        for (StudySite ss : studySites) {
            StudySiteAjaxableDomainObject ssado = new StudySiteAjaxableDomainObject();
            ssado.setId(ss.getId());
            ssado.setPrimaryId(ss.getStudy().getPrimaryIdentifierValue());
            ssado.setStudyShortTitle(ss.getStudy().getShortTitle());
            ssado.setStatus(ss.getStudy().getStatus());
            ssado.setDataEntryStatus(ss.getStudy().getDataEntryStatus());
            ssado.setStudyPhase(ss.getStudy().getPhaseCode());
            ssado.setNciInstituteCode(ss.getStudy().getPrimaryFundingSponsor().getOrganization().getNciInstituteCode());
            ssado.setName(ss.getOrganization().getFullName());  //CAAERS-4565
            ssado.setFundingSponsorIdentifierValue(ss.getStudy().getFundingSponsorIdentifierValue());
            rs.add(ssado);
        }

        return rs;
    }


    public List<StudySite> getStudySites(String text, int organizationID, boolean hideIncomplete) {
        StudySitesQuery studySitesQuery = new StudySitesQuery();
        if (organizationID > 0) studySitesQuery.filterByOrganizationId(organizationID);
        studySitesQuery.filterByDataEntryComplete(hideIncomplete);
        studySitesQuery.filterByShortTitleOrIdentifiers(text);
        List<StudySite> studySites = studyRepository.search(studySitesQuery, "st", text, coppaMode);
        return studySites;
    }

    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }

    private Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : CONTROLLERS) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }

    public Class<?>[] getCONTROLLERS() {
        return CONTROLLERS;
    }

    public void setCONTROLLERS(Class<?>[] CONTROLLERS) {
        this.CONTROLLERS = CONTROLLERS;
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

	public void setCoppaMode(boolean coppaMode) {
		this.coppaMode = coppaMode;
	}
}
