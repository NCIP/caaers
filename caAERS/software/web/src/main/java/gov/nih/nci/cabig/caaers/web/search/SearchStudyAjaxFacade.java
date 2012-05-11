package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.SiteResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.*;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.extremecomponents.table.view.CsvView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

import static gov.nih.nci.cabig.caaers.domain.DateValue.stringToDateValue;

public class SearchStudyAjaxFacade extends AbstractAjaxFacade {
    private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

    private StudyRepository studyRepository;
    private StudyDao studyDao;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
    private ParticipantDao participantDao;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigatorDao investigatorDao;
    private InvestigatorRepository investigatorRepository; 
    private ResearchStaffDao researchStaffDao;
    private ResearchStaffRepository researchStaffRepository;
    private AdverseEventDao adverseEventDao;
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private AgentRepository agentRepository;
    private DeviceRepository deviceRepository;

    private static Class<?>[] CONTROLLERS = {};

    public SearchStudyAjaxFacade() {
    }

    public SearchStudyAjaxFacade(final StudyDao studyDao,
                                 final ParticipantDao participantDoa,
                                 final AdverseEventDao adverseEventDao,
                                 final ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao,
                                 final OrganizationDao organizationDao) {
        this.studyDao = studyDao;
        participantDao = participantDoa;
        this.adverseEventDao = adverseEventDao;
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
        this.organizationDao = organizationDao;
    }

    public Object build(final TableModel model, final Collection<StudySearchableAjaxableDomainObject> studies) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(studies);
        table.setVar("study");
        table.setAction(model.getContext().getContextPath() + "/pages/search/study");
        table.setTitle("");
        table.setShowPagination(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        //only support filtering & sorting in local authentication mode. 
        table.setFilterable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        table.setSortable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        if(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal()){
        	table.setRowsDisplayed(100);
        }
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        
        model.addTable(table);

        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_studies.txt");
        model.addExport(export);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);

        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.study.StudyLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        Column columnShortTitle = model.getColumnInstance();
        columnShortTitle.setProperty("shortTitle");
        model.addColumn(columnShortTitle);

        Column columnSponsorCode = model.getColumnInstance();
        columnSponsorCode.setTitle("Sponsor");
        columnSponsorCode.setProperty("primarySponsorCode");
        model.addColumn(columnSponsorCode);

        Column columnPhaseCode = model.getColumnInstance();
        columnPhaseCode.setTitle("Phase");
        columnPhaseCode.setProperty("phaseCode");
        model.addColumn(columnPhaseCode);

        Column columnStatusCode = model.getColumnInstance();
        columnStatusCode.setProperty("status");
        model.addColumn(columnStatusCode);

        return model.assemble();
    }


    public Object buildParticipant(final TableModel model, final Collection participants) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/search/participant");
        table.setTitle("");
        table.setShowPagination(true);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortable(true);
        
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        model.addTable(table);

        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_participants.txt");
        model.addExport(export);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);

        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        model.addColumn(columnFirstName);

        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        model.addColumn(columnLastName);

        Column colummGender = model.getColumnInstance();
        colummGender.setProperty("gender");
        model.addColumn(colummGender);

        Column colummRace = model.getColumnInstance();
        colummRace.setProperty("race");
        model.addColumn(colummRace);

        Column colummEthnicity = model.getColumnInstance();
        colummEthnicity.setProperty("ethnicity");
        model.addColumn(colummEthnicity);

        Column columnStudyPrimaryIdentifier = model.getColumnInstance();
        //columnStudyPrimaryIdentifier.setProperty("test");
        //columnStudyPrimaryIdentifier.setSortable(false);
        columnStudyPrimaryIdentifier.setTitle("Associated Study ID(s)");
        columnStudyPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantStudyLinkDisplayCell");
        model.addColumn(columnStudyPrimaryIdentifier);

        return model.assemble();
    }

    /**
     * YUI result
     * 
     * */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> getINDTable(final Map parameterMap, final String type, final String text,final HttpServletRequest request) throws Exception {
        List<InvestigationalNewDrug> items = new ArrayList<InvestigationalNewDrug>();
        HashMap<String, String> map = new HashMap<String, String>();
        if (type != null && text != null) {
            String[] fields = type.split(",");
            String[] values = text.split(",");
            for (int i = 0; i < fields.length; i++) {
                map.put(fields[i], values[i]);
            }
            items = investigationalNewDrugDao.searchInvestigationalNewDrugs(map);
        }
        return ObjectTools.reduceAll(items, "indNumber", "holderName");
    }

    @SuppressWarnings("finally")
    private List<StudySearchableAjaxableDomainObject> constructExecuteStudyQuery(final String type, final String text) throws ParseException {
        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        Map<String, String> propValue = new HashMap<String, String>();

        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        // map the html properties to the model properties
        Map<String, String> m = new HashMap<String, String>();
        m.put("prop0", "studyIdentifier");
        m.put("prop1", "studyShortTitle");
        m.put("prop2", "participantIdentifier");
        m.put("prop3", "participantFirstName");
        m.put("prop4", "participantLastName");
        m.put("prop5", "participantEthnicity");
        m.put("prop6", "participantGender");
        m.put("prop7", "participantDateOfBirth");

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            // Create a map of property key ,and search criteria
            propValue.put(m.get(sType), sText);


        }


        query.filterStudiesWithMatchingIdentifierOnly(propValue.get("studyIdentifier"));

        query.filterStudiesWithMatchingShortTitleOnly(propValue.get("studyShortTitle"));
        query.filterByParticipant(propValue.get("participantFirstName"), propValue.get("participantLastName"),
                propValue.get("participantEthnicity"), propValue.get("participantIdentifier"), propValue.get("participantGender"),
                stringToDateValue(propValue.get("participantDateOfBirth")));

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        return studySearchableAjaxableDomainObjects;


    }

    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> constructExecuteParticipantQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        Map<String, String> propValue = new HashMap<String, String>();
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = new ArrayList<ParticipantAjaxableDomainObject>();

        // map the html properties to the model properties
        Map<String, String> m = new HashMap<String, String>();
        m.put("prop0", "studyIdentifier");
        m.put("prop1", "studyShortTitle");
        m.put("prop2", "participantIdentifier");
        m.put("prop3", "participantFirstName");
        m.put("prop4", "participantLastName");
        m.put("prop5", "participantEthnicity");
        m.put("prop6", "participantGender");
        m.put("prop7", "participantDateOfBirth");

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            // Create a map of property key ,and search criteria
            propValue.put(m.get(sType), sText);
        }
        
        try {
        	query.filterByPrimaryIdentifiers();
			query.filterParticipants(propValue);
//			query.filterByStudyIdentifierValue(propValue.get("studyIdentifier"));
//			query.filterByStudyShortTitle(propValue.get("studyShortTitle"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Query Parsing Error : constructExecuteParticipantQuery", e);
		}
 
		participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        return participantAjaxableDomainObjects;
        /*
        try {
            participants = participantDao.searchParticipant(propValue);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }
        */
    }

    @SuppressWarnings("finally")
    private List<Organization> constructExecuteOrganizationQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        List<Organization> organizations = new ArrayList<Organization>();

        OrganizationQuery organizationQuery = new OrganizationQuery();
        

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("name")) {
                organizationQuery.filterByOrganizationName(sText);
            }
            if (sType.equals("nciInstituteCode")) {
            	organizationQuery.filterByNciCodeExactMatch(sText);
            }

        }

        organizationQuery.filterByRetiredStatus(false);

        try {
            organizations = organizationRepository.searchOrganization(organizationQuery);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return organizations;
        }
    }

    @SuppressWarnings("finally")
    private List<Investigator> constructExecuteInvestigatorQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        List<Investigator> investigators = new ArrayList<Investigator>();

        InvestigatorQuery investigatorQuery = new InvestigatorQuery();

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("firstName")) {
                investigatorQuery.filterByFirstName(sText);
            } else if (sType.equals("nciIdentifier")) {
                investigatorQuery.filterByNciIdentifier(sText);
            } else if (sType.equals("lastName")) {
                investigatorQuery.filterByLastName(sText);
            } else if (sType.equals("organization")) {
            	investigatorQuery.filterByOrganization(sText);
            }
        }

        try {
            //investigators = investigatorRepository.searchInvestigator(investigatorQuery,type,text);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return investigators;
        }
    }

    @SuppressWarnings("finally")
    private List<SiteResearchStaff> constructExecuteSiteResearchStaffQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();

        SiteResearchStaffQuery query = new SiteResearchStaffQuery();

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("firstName")) {
                query.filterByFirstName(sText);
            } else if (sType.equals("lastName")) {
                query.filterByLastName(sText);
            } else if (sType.equals("organization")) {
            	query.filterByOrganization(sText);
            }
        }

        try {
            //siteResearchStaffs = researchStaffRepository.getSiteResearchStaff(query,type,text);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return siteResearchStaffs;
        }
    }

    /*
      * Ajax Call hits this method to generate table
      */
    public String getTable(final Map parameterMap, final String type, final String text,
                           final HttpServletRequest request) {

        // Use this code to view the contents of parameterMap
        if (parameterMap != null) {

            for (Object key : parameterMap.keySet()) {
                log.debug(key.toString() + " -- " + parameterMap.get(key));
            }
        }

        try {
            List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = new ArrayList<StudySearchableAjaxableDomainObject>();
            if (type != null && text != null) {
                studySearchableAjaxableDomainObjects = constructExecuteStudyQuery(type, text);
            }
            log.debug("Studies:?: " + studySearchableAjaxableDomainObjects.size());

            Context context = null;
            if (parameterMap == null) {
                context = new HttpServletRequestContext(request);
            } else {
                context = new HttpServletRequestContext(request, parameterMap);
            }

            TableModel model = new TableModelImpl(context);

            return build(model, studySearchableAjaxableDomainObjects).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /*
      * Ajax Call hits this method to generate table
      */
    public String getParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        //List<Participant> participants = new ArrayList<Participant>();
    	List<ParticipantAjaxableDomainObject> participants= new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = constructExecuteParticipantQuery(type, text);
        }
        log.debug("Participants :: " + participants.size());

        Context context = null;
        if (parameterMap == null) {
            context = new HttpServletRequestContext(request);
        } else {
            context = new HttpServletRequestContext(request, parameterMap);
        }

        TableModel model = new TableModelImpl(context);
        // LimitFactory limitFactory = new TableLimitFactory(context);
        // Limit limit = new TableLimit(limitFactory);
        // limit.setRowAttributes(totalRows, DEFAULT_ROWS_DISPLAYED);
        // model.setLimit(limit);

        try {
            return buildParticipant(model, participants).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     *
     * YUI 
     * 
     * */
    public List<Organization> getOrganizationTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {
        List<Organization> organizations = new ArrayList<Organization>();
        if (type != null && text != null) {
            organizations = constructExecuteOrganizationQuery(type, text);
        }

        List<Organization> rs = new ArrayList<Organization>();
        for (Organization o : organizations) {
            LocalOrganization lo = new LocalOrganization();
            lo.setId(o.getId());
            lo.setName(o.getName());
            lo.setNciInstituteCode(o.getNciInstituteCode());
            lo.setExternalId(o.getExternalId() != null ? o.getExternalId().trim() : "");
            rs.add(lo);
        }
        return rs;
        //return ObjectTools.reduceAll(organizations, "id", "name", "nciInstituteCode", "externalId");
    }

    public List<UserAjaxableDomainObject> getInvestigatorTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        List<Investigator> investigators = new ArrayList<Investigator>();
        if (type != null && text != null) {
            investigators = constructExecuteInvestigatorQuery(type, text);
        }

        List<UserAjaxableDomainObject> rs = new ArrayList<UserAjaxableDomainObject>();
        for (Investigator i : investigators) {
            UserAjaxableDomainObject rsado = new UserAjaxableDomainObject();
            rsado.setFirstName(i.getFirstName());
            rsado.setLastName(i.getLastName());
            rsado.setMiddleName(i.getMiddleName());

            StringBuffer sb = new StringBuffer();
            for (SiteInvestigator si : i.getSiteInvestigators()) {
                sb.append(si.getOrganization().getName() + "<br>");
            }
            rsado.setOrganization(sb.toString());

            rsado.setId(i.getId());
            rsado.setActive(i.isActive() ? "Active" : "Inactive");
            rsado.setNumber(i.getNciIdentifier() != null ? i.getNciIdentifier() : "");
            rsado.setExternalId(i.getExternalId() != null ? i.getExternalId().trim() : "");
            rs.add(rsado);
        }


        return rs;
    }

    public List<Agent> getAgentsTable(final Map parameterMap, final String text, final String nsc, final HttpServletRequest request) {
        List<Agent> agents = new ArrayList<Agent>();
        if (text != null) {
            agents = agentRepository.getAgentsBySubnames(new String[]{text, nsc});
        }
        return ObjectTools.reduceAll(agents, "id", "name", "nscNumber");
    }

    public List<Device> getDevices(final Map parameterMap, final String text, final HttpServletRequest request) {
        List<Device> devices = new ArrayList<Device>();
        if (text != null) {
            devices = deviceRepository.getByMatchText(text);
        }
        return ObjectTools.reduceAll(devices, "id", "commonName", "brandName", "type");
    }

    public List<UserAjaxableDomainObject> getResearchStaffTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {
        
        List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();
        if (type != null && text != null) {
            siteResearchStaffs = constructExecuteSiteResearchStaffQuery(type, text);
        }

        // List<UserAjaxableDomainObject> rs = new ArrayList<UserAjaxableDomainObject>();
        Set<UserAjaxableDomainObject> set = new HashSet<UserAjaxableDomainObject>();
        
        for (SiteResearchStaff srs : siteResearchStaffs) {
            UserAjaxableDomainObject rsado = new UserAjaxableDomainObject();
            rsado.setFirstName(srs.getResearchStaff().getFirstName());
            rsado.setLastName(srs.getResearchStaff().getLastName());
            rsado.setMiddleName(srs.getResearchStaff().getMiddleName());

            StringBuffer sb = new StringBuffer("");
            for (SiteResearchStaff site : srs.getResearchStaff().getSiteResearchStaffs()) {
                sb.append(site.getOrganization().getName() + "<br>");
            }
            rsado.setOrganization(sb.toString());

            rsado.setId(srs.getResearchStaff().getId());
            rsado.setNumber(srs.getResearchStaff().getNciIdentifier() != null ? srs.getResearchStaff().getNciIdentifier() : "");
            rsado.setExternalId(srs.getResearchStaff().getExternalId() != null ? srs.getResearchStaff().getExternalId().trim() : "");
            rsado.setActive(srs.isActive() ? "Active" : "Inactive");
            set.add(rsado);
        }

        return new ArrayList<UserAjaxableDomainObject>(set);
    }



    /**
     * Builds a table
     *
     * @param items          - A list consisting of item that should be painted in each row
     * @param actionPath     - The path of the actions
     * @param exportFileName - The file to which the table data to be exported
     * @param cvObjects      - A ColumnValue Object, which has the details of the columns in the table.
     */
    @SuppressWarnings("unchecked")
    public Object buildTable(final TableModel model, final Collection items, final String actionPath,
                             final String exportFileName, final ColumnValueObject... cvObjects) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(items);
        table.setAction(model.getContext().getContextPath() + actionPath);
        table.setTitle("");
        table.setShowPagination(false);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(true);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        table.setSortable(true);
        model.addTable(table);
/*
        if (StringUtils.isNotEmpty(exportFileName)) {
            Export export = model.getExportInstance();
            export.setView(TableConstants.VIEW_CSV);
            export.setViewResolver(TableConstants.VIEW_CSV);
            export.setImageName(TableConstants.VIEW_CSV);
            export.setText(TableConstants.VIEW_CSV);
            export.addAttribute(CsvView.DELIMITER, "|");
            export.setFileName(exportFileName);
            model.addExport(export);
        }
*/
        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
        for (ColumnValueObject cv : cvObjects) {
            Column col = model.getColumnInstance();
            col.setTitle(cv.title);
            col.setProperty(cv.propertyName);
            col.setSortable(cv.sortable);
            if (StringUtils.isNotEmpty(cv.alias)) {
                col.setAlias(cv.alias);
            }
            if (StringUtils.isNotEmpty(cv.format)) {
                col.setFormat(cv.format);
            }
            if (StringUtils.isNotEmpty(cv.cellDisplay)) {
                col.setCellDisplay(cv.cellDisplay);
            }
            if (StringUtils.isNotEmpty(cv.cellType)) {
                col.setCell(cv.cellType);
            }
            model.addColumn(col);
        }

        return model.assemble();
    }

 

    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> getParticipants(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;

        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        //ParticipantQuery participantQuery = new ParticipantQuery();
        //participantQuery.leftJoinFetchOnIdentifiers();
        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            
            if (sType.equals("firstName")) {
            	query.filterByFirstName(sText);
            } else if (sType.equals("identifier")) {
            	query.filterByParticipantIdentifierValue(sText);
            } else if (sType.equals("lastName")) {
            	query.filterByLastName(sText);
            }
        }

        try {
            participants = participantAjaxableDomainObjectRepository.findParticipants(query);
            // System.out.println("Q1: " + query.getQueryString());
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }
    }

    public List<ParticipantAjaxableDomainObject> buildParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = getParticipants(type, text);
        }
        return participants;
    }

    public Object buildPartcipantTable(final TableModel model, final List<ParticipantAjaxableDomainObject> participants) throws Exception {
        Table table = model.getTableInstance();
        table.setTableId("ajaxTable");
        table.setForm("assembler");
        table.setItems(participants);
        table.setAction(model.getContext().getContextPath() + "/pages/participant/search");
        table.setTitle("");
        table.setShowPagination(true);
        table.setOnInvokeAction("buildTable('assembler')");
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        //only support filtering & sorting in local authentication mode. 
        table.setFilterable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());
        table.setSortable(Configuration.LAST_LOADED_CONFIGURATION.isAuthenticationModeLocal());

        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");

        model.addTable(table);

/*        Export export = model.getExportInstance();
        export.setView(TableConstants.VIEW_CSV);
        export.setViewResolver(TableConstants.VIEW_CSV);
        export.setImageName(TableConstants.VIEW_CSV);
        export.setText(TableConstants.VIEW_CSV);
        export.addAttribute(CsvView.DELIMITER, "|");
        export.setFileName("caaers_participants.txt");
        model.addExport(export);*/

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);

        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        columnFirstName.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnFirstName);

        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        model.addColumn(columnLastName);

        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(false);
        columnPrimaryIdentifier.setProperty("primaryIdentifierValue");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantLinkDisplayCell");
        model.addColumn(columnPrimaryIdentifier);

        Column assignmentIdentifiers = model.getColumnInstance();
        assignmentIdentifiers.setProperty("studySubjectIdentifiersCSV");
        assignmentIdentifiers.setTitle("Study Subject Identifiers");
        assignmentIdentifiers.setCell("gov.nih.nci.cabig.caaers.web.search.ParticipantAssignmentsDisplayCell");
        model.addColumn(assignmentIdentifiers);

        return model.assemble();

    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(final StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public AdverseEventDao getAdverseEventDao() {
        return adverseEventDao;
    }

    public void setAdverseEventDao(final AdverseEventDao adverseEventDao) {
        this.adverseEventDao = adverseEventDao;
    }

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(final ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }


    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }
    @Required
	public void setParticipantAjaxableDomainObjectRepository(
			ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
		this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}
	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public ResearchStaffRepository getResearchStaffRepository() {
		return researchStaffRepository;
	}

	@Required
	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
	
	@Required
	public void setInvestigatorRepository(InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Class<?>[] controllers() {
        return CONTROLLERS; 
    }
}

class ColumnValueObject {
    public String title;

    public String propertyName;

    public String alias;

    public String format;

    public String cellDisplay;

    public String cellType;

    public boolean sortable;

    public ColumnValueObject(final String propertyName, final String title, final String alias, final String format,
                             final String cellDisplay, final String cellType) {
        this.title = title;
        this.propertyName = propertyName;
        this.alias = alias;
        this.format = format;
        this.cellDisplay = cellDisplay;
        this.cellType = cellType;
    }

    public static ColumnValueObject create(final String propertyName) {
        return ColumnValueObject.create(propertyName, null, null);
    }

    public static ColumnValueObject create(final String propertyName, final String title) {
        return ColumnValueObject.create(propertyName, title, null);
    }

    public static ColumnValueObject create(final String propertyName, final String title, final String alias) {
        return new ColumnValueObject(propertyName, title, alias, null, null, null);
    }
}
