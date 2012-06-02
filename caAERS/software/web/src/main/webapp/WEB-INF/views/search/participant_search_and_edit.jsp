<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>

<style type="text/css">
        /* Override default lable length */
       	div.row div.label { width: 9em; } 
        div.row div.value { margin-left: 10em; }
</style>

<style>
    .yui-dt table { width: 100%; }
</style>
    
<title>Search Subjects</title>
<tags:dwrJavascriptLink objects="search"/>

<script>

    function doSearch() {
        buildTable('assembler');
        jQuery('#bigSearch').show();
    }

function buildTable(form) {
	$('indicator').className='';

    var text = jQuery("#searchText").val();

/*
	$('prop').value=type;
	$('value').value=text;
*/

	var parameterMap = getParameterMap(form);
	search.buildParticipantTable(parameterMap, "", text, ajaxCallBack);
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

var nameFormatter = function(elCell, oRecord, oColumn, oData) {
    var _id = oRecord.getData("id");
    var _fname = oRecord.getData("firstName");
    var _lname = oRecord.getData("lastName");
    elCell.innerHTML = _fname + "&nbsp;" + _lname;
};

var myColumnDefs = [
    {key:"fullName", label:"Full name", sortable:true, resizeable:true, formatter: nameFormatter},
    {key:"primaryIdentifierValue", label:"Primary ID", sortable:true, resizeable:true},
    {key:"studySubjectIdentifiersCSV", label:"Study Subject Identifiers", sortable:true, resizeable:true}
];

var myFields = [
    {key:'id', parser:"string"},
    {key:'firstName', parser:"string"},
    {key:'lastName', parser:"string"},
    {key:'primaryIdentifierValue', parser:"string"},
    {key:'studySubjectIdentifiersCSV', parser:"string"}
];
    
</script>
</head>
<body>

<div class="content">

<form:form name="searchForm" id="searchForm" method="post">
	<p><tags:instructions code="instruction_subject_search"/></p>

    <chrome:box title="Search Criteria" cssClass="mpaired" autopad="false">
        <div class="row">
            <div class="label">&nbsp;</div>
            <div class="value">
                <input id="searchText" type="text" name="searchText"/>&nbsp;
                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="doSearch();"/>&nbsp;<tags:indicator id="indicator"/>
            </div>
        </div>
    </chrome:box>

 </form:form>
<br>			
<br>

<form:form id="assembler" >

    <c:set var="display" value="none" />

    <div id="bigSearch" style="border:0px green dotted; display:${display};">
    <chrome:box title="Results">
     	<chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/>
		</div>
		</chrome:division>
	</chrome:box>
    </div>
    
</form:form>
</div>
</body>
</html>