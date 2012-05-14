<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="search"/>

<style>
.yui-pg-page { padding: 5pt; }
.yui-dt-label .yui-dt-sortable { color: white; }
.yui-dt table { width: 100%; }
div.yui-dt-liner a {color : black;}
tr.yui-dt-even { background-color: #FFF; border-bottom: 1px gray solid;}
tr.yui-dt-odd { background-color: #EDF5FF; border-bottom: 2px blue dotted; padding: 2px; }
</style>

<script>
function buildTable(form) {
	$('indicator').className='';
	var name = $('name').value;

	var parameterMap = getParameterMap(form);
	search.getDevices(parameterMap, name, ajaxCallBack);
    $('bigSearch').show();
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
}

var linkFormatter = function(elCell, oRecord, oColumn, oData) {
        var orgId = oRecord.getData("id");
        elCell.innerHTML = "<a href='deviceEdit?deviceID=" + orgId + "'>" + oData + "</a>";
};

var myColumnDefs = [
    {key:"commonName",             label:"Common Name",          sortable:true,      resizeable:true, formatter: linkFormatter},
    {key:"brandName",              label:"Brand Name",           sortable:true,      resizeable:true},
    {key:"type",                   label:"Type",                sortable:true,      resizeable:true},
    {key:"retiredIndicator",                label:"Retired",                sortable:true,      resizeable:true, formatter: retireFormatter}
];

var myFields = [
    {key:'id',              parser:"integer"},
    {key:'commonName',      parser:"string"},
    {key:'brandName',       parser:"string"},
    {key:'type',            parser:"string"},
    {key:'retiredIndicator',         parser:"string"}
];
</script>

<div class="tabpane">

        <admin:agent3rdLevelMenu selected="searchDevice" />

<div class="content">

    <form:form name="searchForm" id="searchForm" method="post">

            <chrome:box title="Search Devices" autopad="false">
            <tags:instructions code="admin.devices.search.instructions" />
            <div class="row">
                <div class="label"><caaers:message code="LBL_device.search.field" /></div>
                <div class="value"><input id="name" type="text" name="name"/></div>
            </div>
            <div class="row">
                <div class="value">
                    <tags:button type="button" value="Search" color="blue" icon="search" onclick="buildTable('assembler');" size="small"/>
                    <tags:indicator id="indicator" />
                </div>
            </div>
            </chrome:box>

          <div class="endpanes" />
          <div class="endpanes" />

    </form:form>

           <div id="bigSearch" style="display:none;">
               <form:form id="assembler">
                   <div>
                       <input type="hidden" name="_name" id="_name">
                       <input type="hidden" name="_nsc" id="_nsc">
                   </div>
                   <chrome:box title="Search Results">
                       <chrome:division id="single-fields">
                           <div id="tableDiv">
                               <c:out value="${assembler}" escapeXml="false"/>
                           </div>
                       </chrome:division>
                   </chrome:box>
               </form:form>
           </div>
       </div>

</div>