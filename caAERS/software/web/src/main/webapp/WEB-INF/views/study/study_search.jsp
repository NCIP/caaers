<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Search Studies</title>
<tags:dwrJavascriptLink objects="searchStudy"/>
<tags:dwrJavascriptLink objects="createStudy"/>
<style type="text/css">
        /* Override default lable length */
		div.row {margin-top:0px; margin-bottom:0px;}
       	div.row div.label { width: 7em; }
        div.row div.value { margin-left: 7em; }
        .endpanes {	clear: both; }
</style>

<style>
    .yui-dt table { width: 100%; }
</style>

<script>

    jQuery(document).ready(function() {
        doSearch();
    });

    function doSearch() {
        $("searchCriteria[0].searchText").value = "%";
        buildTable('assembler');
        $('bigSearch').show();
    }

function buildTable(form) {

//	var type = $F('searchCriteria[0].searchType')
	var text = $F('searchCriteria[0].searchText')

	if(text == ''){
		$('error').innerHTML="<font color='#FF0000'>Provide at least one character in the search field</font>"
	}else{
		$('error').innerHTML=""
		$('indicator').className='';
		showCoppaSearchDisclaimer();
        var parameterMap = getParameterMap(form);
		searchStudy.getStudiesTable(parameterMap, "", text, ajaxCallBack);
	}
}

function fireAction(action, selected){
	document.getElementById("_action").value=action;
	document.getElementById("_selected").value=selected;
	document.searchForm.submit();
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

    var actionsFormatter = function (elCell, oRecord, oColumn, oData) {
        var _id = oRecord.getData("id");
        var _ssi = oRecord.getData("fundingSponsorIdentifierValue");
        var _active = oRecord.getData("dataEntryStatus");
        elCell.innerHTML = "<img src='<c:url value="/images/orange-actions.gif" />?${requestScope.webCacheId}' border='0' onmouseover=\"showDashboardStudiesMenuOptions(this, roles_map, '" + _ssi + "', " + _id + ", '" + _active + "')\" style=\"cursor: pointer; margin-right: 15px;\">";
    };


    function doEdit(_id) {
        window.location = "<c:url value="/pages/study/edit?studyId=" />" + _id;
    }

    function addStudySite(_id, _complete) {
        window.location = "<c:url value="/pages/study/edit?tabName=SitesTab&studyId=" />" + _id;
    }

    function addStudyInvestigators(_id, _complete) {
        window.location = "<c:url value="/pages/study/edit?tabName=InvestigatorsTab&studyId=" />" + _id;
    }

    function addStudyPersonnel(_id, _complete) {
        window.location = "<c:url value="/pages/study/edit?tabName=PersonnelTab&studyId=" />" + _id;
    }

    function doRegisterSubject(_id) {
        window.location = "<c:url value="/pages/participant/create?studyId=" />" + _id;
    }

    var myColumnDefs = [
    {key:"primaryIdentifierValue", label:"Primary identifier", sortable:true, resizeable:true, minWidth:150, maxWidth:150},
    {key:"shortTitle", label:"Title", sortable:true, resizeable:true},
    {key:"actions", label:"&nbsp;", sortable:true, resizeable:true, formatter: actionsFormatter}
];

var myFields = [
    {key:'id',                      parser:"string"},
    {key:'primaryIdentifierValue',  parser:"string"},
    {key:'fundingSponsorIdentifierValue',  parser:"string"},
    {key:'dataEntryStatus',  parser:"boolean"},
    {key:'phaseCode',               parser:"string"},
    {key:'primarySponsorCode',      parser:"string"},
    {key:'shortTitle',              parser:"string"},
    {key:'externalId',              parser:"string"}
];

function onKey(e) {
    var keynum = getKeyNum(e);

    if (keynum == 13) {
        Event.stop(e);
        buildTable('searchForm');
        $('bigSearch').show();
    } else return;
}

    

</script>
</head>
<body>

<chrome:box title="Search Criteria" autopad="true">
<tags:instructions code="study.search.top" />
    <form:form name="searchForm" id="searchForm" method="post">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
       <div>
			<input type="hidden" name="_selected" id="_selected" value="">
			<input type="hidden" name="_action" id="_action" value="">
		</div>
        <div class="content">

        	<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs" style="float:left;">
            	<div class="label">&nbsp;</div>
                <div class="value">
<%--
                	<form:select path="searchCriteria[${status.index}].searchType" id="searchCriteria[${status.index}].searchType">
						<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
					</form:select>
--%>
					<form:input path="searchCriteria[${status.index}].searchText" id="searchCriteria[${status.index}].searchText" size="25" onkeydown="onKey(event);" />
					<div id="error"></div>
				</div>
			</div>
		 	</c:forEach>

            <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('searchForm'); $('bigSearch').show();"/>
            <tags:indicator id="indicator" />
            <span id="coppa-search-disclaimer" class="coppa-search-disclaimer" style="display:none;">
                <caaers:message code="${ configuration.authenticationMode eq 'local' ? 'study.search.message' : 'coppa.search.message'}" />
            </span>
        </div>
    </form:form>
</chrome:box>

<div class="row" style="float:right;">
</div>

<div id="bigSearch" style="display:none;">

<chrome:box title="Results">
<form:form id="assembler">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/>
		</div>
	</chrome:division>
</form:form>
</chrome:box>
</div>


<div id="please_wait" style="display: none;" class="flash-message info" >
    <h3><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_please.wait" /></h3>
    <br><br>
    <div><caaers:message code="LBL_study.in.process" /></div>
</div>
<div id="error_page" style="display: none;" class="flash-message error" ><div><caaers:message code="LBL_study.process.error" /></div><br><span id="_errorMessage">.</span></div>

</body>
</html>
