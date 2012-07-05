<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>

<style type="text/css">
        /* Override default lable length */
       	div.row div.label { width: 9em; } 
        div.row div.value { margin-left: 10em; }
        .yui-dt table { width: 100%; }
        #yui-dt0-th-actions { width: 70px;}
</style>
    
<title>Search Subjects</title>
<tags:dwrJavascriptLink objects="search"/>

<script>

  function textName_OnKeyDown(e){
  		var keynum;                                 
                    if(window.event) // IE                              
                    {
                            keynum = e.keyCode
                    }
                    else if(e.which) // Netscape/Firefox/Opera
                    {
                            keynum = e.which
                    }                   

                    if (keynum == 13) 
                    {
                            doSearch();
                            return false;
                    }
  
  }

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

var actionsFormatter = function(elCell, oRecord, oColumn, oData) {
    var _id = oRecord.getData("id");
    var _assId = oRecord.getData("assignmentId");
    var _stId = oRecord.getData("studyId");
    elCell.innerHTML = "<img src='<c:url value="/images/orange-actions.gif" />' border='0' onmouseover='showDashboardSubjectsAssignmentsMenuOptions(this, roles_map, " + _id + ", " + _stId + ", " + _assId + ")' style='cursor: pointer; margin-right: 15px;'>";
};

var myColumnDefs = [
    {key:"fullName", label:"Full name", sortable:true, resizeable:true, formatter: nameFormatter},
    {key:"primaryIdentifierValue", label:"Primary ID", sortable:true, resizeable:true},
    {key:"studyPrimaryIdentifier", label:"Study ID", sortable:true, resizeable:true},
    {key:"studySubjectIdentifiersCSV", label:"Study Subject Identifiers", sortable:true, resizeable:true},
    {key:"actions", label:"&nbsp;", sortable:true, formatter:actionsFormatter}
];

var myFields = [
    {key:'id', parser:"string"},
    {key:'firstName', parser:"string"},
    {key:'lastName', parser:"string"},
    {key:'primaryIdentifierValue', parser:"string"},
    {key:'studySubjectIdentifiersCSV', parser:"string"},
    {key:'studyPrimaryIdentifier', parser:"string"},
    {key:'assignmentId', parser:"string"},
    {key:'studyId', parser:"string"}
];

    function enterAdverseEvents(_studyId, _subjectId) {
        document.location = "<c:url value="/pages/ae/captureRoutine?" />" + "studyId=" + _studyId + "&subjectId=" + _subjectId;
    }

    function editMedicalHistory(_studyId, _subjectId, _assignmentId) {
        document.location = "<c:url value="/pages/participant/edit?" />" + "participantId=" + _subjectId + "&assignmentId=" + _assignmentId + "&tabName=EditSubjectMedHistoryTab";
    }

    function editSubjectDetails(_studyId, _subjectId) {
        document.location = "<c:url value="/pages/participant/edit?" />" + "participantId=" + _subjectId;
    }

    function assignToStudy(_studyId, _subjectId) {
      //  document.location = "<c:url value="/pages/participant/assignParticipant?" />" + "participantId=" + _subjectId;
        window.location = "<c:url value="/pages/participant/assignParticipant?tabName=AssignStudyTab&participantId=" />" +_subjectId;
    }

</script>
</head>
<body>

<div class="content">

<form:form name="searchForm" id="searchForm" method="post">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
	<p><tags:instructions code="instruction_subject_search"/></p>

    <chrome:box title="Search Criteria" cssClass="mpaired" autopad="false">
        <div class="row">
            <div class="label">&nbsp;</div>
            <div class="value">
                <input id="searchText" type="text" name="searchText" onkeydown="return textName_OnKeyDown(event)"/>&nbsp;
                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="doSearch();"/>&nbsp;<tags:indicator id="indicator"/>
            </div>
        </div>
    </chrome:box>

 </form:form>
<br>			
<br>

<form:form id="assembler" >
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>

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