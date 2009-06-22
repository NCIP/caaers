package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;

/**
 * @author Biju Joseph
 */
public class AdverseEventHistoryAjaxFacade {

    private AuditHistoryRepository auditHistoryRepository;
    private static final Log log = LogFactory.getLog(CreateAdverseEventAjaxFacade.class);


    public String getAdeverseEventHistory(HttpServletRequest request, String adverseEventId) throws Exception {

//        try {

//            if (adverseEventId != null && StringUtils.isNumeric(adverseEventId)) {
//
//                List<DataAuditEvent> auditHistories = auditHistoryRepository.getAuditDetailsForEntity(AdverseEvent.class, Integer.parseInt(adverseEventId));
//
//                TableModel model = getTableModel(null, request);
//
//
//                addTableAndRowToModel(model, "historyTable", auditHistories, null);
//
//                Column userColumn = model.getColumnInstance();
//                userColumn.setTitle("User Name");
//                userColumn.setProperty("info.username");
//                userColumn.setSortable(false);
//                model.addColumn(userColumn);
//
//                Column dataAndTimeColumn = model.getColumnInstance();
//                dataAndTimeColumn.setTitle("Date & Time");
//                dataAndTimeColumn.setProperty("info.time");
//                dataAndTimeColumn.setSortable(false);
//                model.addColumn(dataAndTimeColumn);
//
//
//                Column gradeColumn = model.getColumnInstance();
//                gradeColumn.setTitle("Grade");
//                //you need to set this property..though its not used while getting cell value
//                gradeColumn.setProperty("operation");
//                gradeColumn.setCell("gov.nih.nci.cabig.caaers.web.ae.cell.GradeDisplayHistoryCell");
//                model.addColumn(gradeColumn);
//
//                Column atributionColumn = model.getColumnInstance();
//                atributionColumn.setTitle("Attribution");
//                //you need to set this property..though its not used while getting cell value
//                atributionColumn.setProperty("operation");
//                atributionColumn.setCell("gov.nih.nci.cabig.caaers.web.ae.cell.AttributionDisplayHistoryCell");
//                model.addColumn(atributionColumn);
//
//                Column hospitalizationColumn = model.getColumnInstance();
//                hospitalizationColumn.setTitle("Hospitalization");
//                //you need to set this property..though its not used while getting cell value
//                hospitalizationColumn.setProperty("operation");
//                hospitalizationColumn.setCell("gov.nih.nci.cabig.caaers.web.ae.cell.HospitalizationDisplayHistoryCell");
//                model.addColumn(hospitalizationColumn);
//
//                Column expected = model.getColumnInstance();
//                expected.setTitle("Expected");
//                //you need to set this property..though its not used while getting cell value
//                expected.setProperty("operation");
//                expected.setCell("gov.nih.nci.cabig.caaers.web.ae.cell.ExpectedDisplayHistoryCell");
//                model.addColumn(expected);
//
//                Column operationColumn = model.getColumnInstance();
//                operationColumn.setTitle("Operation");
//                operationColumn.setProperty("operation");
//                operationColumn.setSortable(false);
//                model.addColumn(operationColumn);
//
//
//                return model.assemble().toString();
//return "";
//            } else {
//                log.error("request does not have adeverse event id or adverse event id is not numeric:" + adverseEventId);
//
//            }
//
//
//        }
//        catch (Exception e) {
//            log.error("error while retriving the audit History" + e.toString() + " message" + e.getMessage());
//        }
//
        return "";

    }

    private void addTableAndRowToModel(final TableModel model, final String tableId, final Object items, final String onInvokeAction) {
        Table table = model.getTableInstance();
        table.setForm("command");
        table.setTableId(tableId);
        table.setTitle("");
        table.setAutoIncludeParameters(Boolean.FALSE);
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(false);
        table.setShowPagination(false);
        table.setItems(items);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");
        table.setOnInvokeAction(onInvokeAction);
        model.addTable(table);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
    }

    private TableModel getTableModel(Map parameterMap, HttpServletRequest request) {
        Context context = null;
        if (parameterMap == null) {
            context = new HttpServletRequestContext(request);
        } else {
            context = new HttpServletRequestContext(request, parameterMap);
        }

        TableModel model = new TableModelImpl(context);
        return model;
    }

    public void setAuditHistoryRepository(final AuditHistoryRepository auditHistoryRepository) {
        this.auditHistoryRepository = auditHistoryRepository;
    }
}
