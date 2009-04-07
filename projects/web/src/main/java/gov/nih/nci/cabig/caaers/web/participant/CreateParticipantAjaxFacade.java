package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.BaseCommandController;

/**
 * @author Saurabh Agrawal
 */
public class CreateParticipantAjaxFacade {

    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    public static final String AJAX_INDEX_PARAMETER = "index";
    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String CREATE_PARTICIPANT_FORM_NAME = CreateParticipantController.class.getName() + ".FORM.command";
    public static final String EDIT_PARTICIPANT_FORM_NAME = EditParticipantController.class.getName() + ".FORM.command";
    public static final String CREATE_PARTICIPANT_REPLACED_FORM_NAME = CREATE_PARTICIPANT_FORM_NAME + ".to-replace";
    public static final String EDIT_PARTICIPANT_REPLACED_FORM_NAME = EDIT_PARTICIPANT_FORM_NAME + ".to-replace";

    private static final Log log = LogFactory.getLog(CreateParticipantAjaxFacade.class);

    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;

    /*
    * Ajax Call hits this method to generate table
    */
    public String getParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {

        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
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

        try {
            return buildParticipant(model, participants).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
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
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");
        table.setSortable(true);
        table.setShowPagination(true);
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
        columnPrimaryIdentifier.setSortable(true);
        columnPrimaryIdentifier.setFilterable(true);
        columnPrimaryIdentifier.setProperty("primaryIdentifier.value");
        columnPrimaryIdentifier.setAlias("primaryIdentifier");
        columnPrimaryIdentifier.setTitle("Primary ID");
        columnPrimaryIdentifier.setCell("gov.nih.nci.cabig.caaers.web.search.cell.SelectedParticipantCell");

        model.addColumn(columnPrimaryIdentifier);


        addFirstName(model);

        addLastName(model);

        addGender(model);

        addRace(model);

        addEthnicity(model);


        return model.assemble();
    }

    private void addEthnicity(TableModel model) {
        Column colummEthnicity = model.getColumnInstance();
        colummEthnicity.setProperty("ethnicity");
        model.addColumn(colummEthnicity);
    }

    private void addRace(TableModel model) {
        Column colummRace = model.getColumnInstance();
        colummRace.setProperty("race");
        model.addColumn(colummRace);
    }

    private void addGender(TableModel model) {
        Column colummGender = model.getColumnInstance();
        colummGender.setProperty("gender");
        model.addColumn(colummGender);
    }

    private void addLastName(TableModel model) {
        Column columnLastName = model.getColumnInstance();
        columnLastName.setProperty("lastName");
        columnLastName.setSortable(true);
        columnLastName.setFilterable(true);
        model.addColumn(columnLastName);
    }

    private void addFirstName(TableModel model) {
        Column columnFirstName = model.getColumnInstance();
        columnFirstName.setProperty("firstName");
        columnFirstName.setSortable(true);
        columnFirstName.setFilterable(true);
        model.addColumn(columnFirstName);
    }

    private void addPrimaryIdentifier(TableModel model) {
        Column columnPrimaryIdentifier = model.getColumnInstance();
        columnPrimaryIdentifier.setSortable(true);
        columnPrimaryIdentifier.setFilterable(true);
        columnPrimaryIdentifier.setProperty("primaryIdentifier.value");
        columnPrimaryIdentifier.setTitle("Primary ID");
        model.addColumn(columnPrimaryIdentifier);

    }

    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> constructExecuteParticipantQuery(final String searchType, final String searchText) {
    	
    	StringTokenizer typeToken = new StringTokenizer(searchType, ",");
        StringTokenizer textToken = new StringTokenizer(searchText, ",");
        log.debug("type :: " + searchType);
        log.debug("text :: " + searchText);
        String sType, sText;
        
        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            
            if (sType.equals("fn")) {
            	query.filterByFirstName(sText);
            } else if (sType.equals("idtf")) {
            	query.filterByParticipantIdentifierValue(sText);                
            } else if (sType.equals("ln")) {
            	query.filterByLastName(sText);
            }
        }
        
        try {
            participants = participantAjaxableDomainObjectRepository.findParticipants(query);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }

    }


    private NewParticipantCommand getParticipantCommand(final HttpServletRequest request) {
        NewParticipantCommand newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(CREATE_PARTICIPANT_REPLACED_FORM_NAME);
        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(CREATE_PARTICIPANT_FORM_NAME);
        }
        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(EDIT_PARTICIPANT_REPLACED_FORM_NAME);
        }

        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(EDIT_PARTICIPANT_FORM_NAME);
        }

        request.setAttribute(BaseCommandController.DEFAULT_COMMAND_NAME, newParticipantCommand);
        return newParticipantCommand;
    }

    public List<Organization> matchOrganization(final String text) {
        //List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(text));
    	List<Organization> orgs = organizationRepository.restrictBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode");
    }

    private String[] extractSubnames(final String text) {
        return text.split("\\s+");
    }

    public String addIdentifier(final int index, final int type) {
        HttpServletRequest request = getHttpServletRequest();
        NewParticipantCommand newParticipantCommand = getParticipantCommand(request);

        if (type == 1) {
            newParticipantCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        } else if (type == 2) {
            newParticipantCommand.getParticipant().getIdentifiers().add(new OrganizationAssignedIdentifier());
        }

        request.setAttribute("listEditorIndex", index);
        request.setAttribute(AJAX_INDEX_PARAMETER, newParticipantCommand.getParticipant().getIdentifiers().size() - 1);
        request.setAttribute("type", type);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "newParticipantCommandIdentifierSection");
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");

        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        request.setAttribute(AJAX_INDEX_PARAMETER, index);

        return html;
    }

    public boolean deleteIdentifier(final int index) {
        NewParticipantCommand newParticipantCommand = getParticipantCommand(getHttpServletRequest());
        return newParticipantCommand.getParticipant().getIdentifiers().remove(index) != null;
    }

    private String getOutputFromJsp(final String jspResource) {
        String html = "Error in rendering...";
        try {
            html = WebContextFactory.get().forwardToString(jspResource);
        } catch (ServletException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        return html;
    }

    private String getCurrentPageContextRelative(final WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();

        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

    // //// CONFIGURATION
    @Required

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
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


}
