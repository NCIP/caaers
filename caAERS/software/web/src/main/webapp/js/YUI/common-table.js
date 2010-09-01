//@author : Ion C. Olaru
/**
 * Initialize YUI data-table.
 *
 * http://developer.yahoo.com/yui/datatable/#start
 * @param tableId       - DOM element to build the YUI table for
 * @param responseData  - JSON data to populate the YUI table with
 * @param columnDefs    - Column definition for the YUI table
 * @param fields        - fields to be displayed for the YUI table
 * 
 */
function initializeYUITable(tableId, responseData, columnDefs, fields) {

    YAHOO.example.CellSelection = new function() {
        var columDefs = columnDefs.clone();
        var tableFields = fields.clone();

        var activeDataSource = new YAHOO.util.DataSource(responseData);
        var rowFormatter = function(elTr, oRecord) {
	        return true;
	    };
        activeDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        activeDataSource.responseSchema = {
            fields: tableFields
        };

        var myConfigs = {
            draggableColumns : false,
            paginator : new YAHOO.widget.Paginator({
                rowsPerPage: 20
            }),
            width: "100%",
            formatRow : rowFormatter
        };

        this.activeDataTable = new YAHOO.widget.DataTable(tableId, columDefs, activeDataSource, myConfigs);
        this.activeDataTable.subscribe("rowMouseoverEvent", this.activeDataTable.onEventHighlightRow);
        this.activeDataTable.subscribe("rowMouseoutEvent", this.activeDataTable.onEventUnhighlightRow);
    }

}