<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Choose a Study</title>

<tags:dwrJavascriptLink objects="searchStudy"/>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Search for a Study</title>
<style>
    .yui-dt table { width: 100%; }
    #yui-dt0-th-primarySponsorCode {width: 130px;}
    /*#yui-dt0-th-shortTitle {width: 350px;}*/
    #yui-dt0-th-primaryIdentifierValue{width: 150px;}
    #yui-dt0-th-phaseCode{width: 100px;}
    #yui-dt0-th-active {width: 50px;}
</style>

<script>

	function onEventHighlightRow(){
		alert("hightlight row");
	}

    function sync() {
       $('searchText').value = $F('searchText_');
    }

    function onKey(e) {
        var keynum = getKeyNum(e);
        if (keynum == 13) {
            Event.stop(e);
            buildTable('assembler', true);
        } else return;
    }

    function buildTable(form, validate) {
        var text = $F('searchText_');

        if (text == '') {
            if (validate) jQuery('#flashErrors').show();
        } else {
            jQuery('#indicator').show();
            jQuery('#flashErrors').hide();
            var parameterMap = getParameterMap(form);
            parameterMap["organizationID"] = "<c:out value="${command.organization.id}" />";
            searchStudy.getStudiesForCreateParticipant(parameterMap, "", text, "${command.organization.nciInstituteCode}", ajaxCallBack);
            jQuery('#indicator').hide();
            jQuery('#bigSearch').show();
        }
    }

/*
    ValidationManager.submitPostProcess = function(formElement, flag) {
//        flag = true;
        $('searchText').value = $('searchText_').value;
        $('_searchType').value = $('searchType').value;
        
*/
/*
        if (formElement.id != 'command') {
            return true
        } else {
            $('assignment.studySubjectIdentifier').value = $('studySubjectIdentifierInput').value
        }
        return flag;
*//*

        return flag;
    }
*/

	function initializeOnRowHighlightedYUITableNoPagination(tableId, responseData, columnDefs, fields) {
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
		            width: "100%",
		            formatRow : rowFormatter
		        };
		
		        this.activeDataTable = new YAHOO.widget.DataTable("row", columDefs, activeDataSource, myConfigs);
		      //  this.rowHighlightDataTable = new YAHOO.widget.DataTable("row",  columDefs, activeDataSource, { 
	          //          caption:"Example: Row Highlighting" 
	         //       }); 
		        // Enable row highlighting 
		        this.activeDataTable.subscribe("rowMouseoverEvent", this.activeDataTable.onEventHighlightRow); 
		        this.activeDataTable.subscribe("rowMouseoutEvent", this.activeDataTable.onEventUnhighlightRow); 
			//    this.activeDataTable.subscribe("rowMouseoverEvent", this.activeDataTable.onEventHighlightRow);
		      //  this.activeDataTable.subscribe("rowMouseoutEvent", this.activeDataTable.onEventUnhighlightRow);
		    }
		}
	

    function ajaxCallBack(jsonResult) {
        $('indicator').className='indicator'
        initializeOnRowHighlightedYUITableNoPagination("tableDiv", jsonResult, myColumnDefs, myFields);
        hideCoppaSearchDisclaimer();

        var value = jQuery('input:radio[name=study]:checked').val();
        if (isNaN(value)) {
            var _value = "<c:out value="${command.searchText}"/>";
            var _el = jQuery('#_study' + _value);
            if (_el) {
                _el.attr('checked', 'checked');
                var _studyId = jQuery(_el).val();
                _elStudyIdText = jQuery('input:text[name=study]');
                jQuery(_elStudyIdText).val(_studyId);
                if (jQuery("#ids")) {
                    jQuery("#ids").show();
                }
            }
        } else {
        }

    }

    var linkFormatter = function(elCell, oRecord, oColumn, oData) {
        elCell.innerHTML = oData;
    };

    var radioFormatter = function(elCell, oRecord, oColumn, oData) {
        var _id = oRecord.getData("id");
        var _piv = oRecord.getData("primaryIdentifierValue");
        var _checked = "";
        <c:if test="${not empty command.study.id}">
            if (${command.study.id} == _id) {
                _checked = "checked";
                if ($("ids")) $("ids").show();
            }
        </c:if>
        elCell.innerHTML = "<input id='_study" + _piv + "' type='radio' " + _checked + " value='" + _id + "' name='study' onclick='$(\"command\").study.value = " + _id + "; if ($(\"ids\")) $(\"ids\").show();'>&nbsp;&nbsp;";
    };
    
    var pivRadioFormatter = function(elCell, oRecord, oColumn, oData) {
        var _piv = oRecord.getData("primaryIdentifierValue");
        elCell.innerHTML = "<label for='_study" + _piv + "'>" + _piv + "</label>";
    };
    
    var shortTitleRadioFormatter = function(elCell, oRecord, oColumn, oData) {
    	var _piv = oRecord.getData("primaryIdentifierValue");
        var _st = oRecord.getData("shortTitle");
        elCell.innerHTML = "<label for='_study" + _piv + "'>" + _st + "</label>";
    };
    

    var myColumnDefs = [
        {key:"active", label:"Select", sortable:true, resizeable:true, formatter : radioFormatter},
        {key:"primaryIdentifierValue", label:"Study ID", sortable:true, resizeable:true, formatter : pivRadioFormatter},
        {key:"shortTitle", label:"Title", sortable:true, resizeable:true, formatter : shortTitleRadioFormatter}
    ];

    var myFields = [
        {key:'id', parser:"string"},
        {key:'primaryIdentifierValue', parser:"string"},
        {key:'shortTitle', parser:"string"},
        {key:'status', parser:"string"},
        {key:'phaseCode', parser:"string"},
        {key:'dataEntryStatus', parser:"string"},
        {key:'fundingSponsorIdentifierValue', parser:"string"},
        {key:'primarySponsorCode', parser:"string"}
    ];

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

</script>

</head>
<body>

<par:summary subjectFullName="${command.participant.fullName}" studyShortTitle="${command.study.shortTitle}"/>

<chrome:box autopad="true" title="Search Criteria">
	<div id="cell"></div> 
	<div id="row"></div> 
	<div id="column"></div> 

    <form:form id="searchForm" method="post" cssClass="standard">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
        <tags:jsErrorsMessage/>

        <div class="errors" id="flashErrors" style="display: none;">
            <span id="command_errors">Provide at least one character in the search field.</span>
        </div>

        <p><tags:instructions code="instruction_subject_as2s.searchstudy"/></p>

        <div class="row">
            <div class="label"></div>
            <div class="value" style="margin-left: 100px;">
                <input type="text" size="25" onkeydown="onKey(event);" value="${command.searchText}" id="searchText_">
                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="sync(); buildTable('assembler', true);"/>
                <img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="indicator">
                <c:set var="targetPage" value="${assignType == 'study' ? '_target0' : '_target1'}"/>
            </div>
        </div>

    </form:form>
</chrome:box>


<div id="bigSearch" style="display:none;">

    <chrome:box title="Results">
        <form:form id="assembler">
        	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
            <div>
                <input type="hidden" name="_prop" id="prop">
                <input type="hidden" name="_value" id="value">
            </div>
            <p><tags:instructions code="instruction_subject_as2s.searchstudyresults"/></p>

            <chrome:division id="single-fields">
                <div id="tableDiv"></div>
            </chrome:division>

            <div id="ids" style="display:none;">
                <br/>
                <chrome:division title="Study Subject Identifier">
                    <p><tags:instructions code="instruction_subject_enter.choosestudy.sid"/></p>
                    <label for="studySubjectIdentifierInput"><tags:requiredIndicator/>&nbsp;<b>Study subject identifier</b></label>
                    <input id="studySubjectIdentifierInput" type="text" maxlength="2000" value="${command.assignment.studySubjectIdentifier}" name="studySubjectIdentifierInput" class="${not empty command.assignment.studySubjectIdentifier ? 'valueOK' : 'required'}" onkeyup="$('assignment.studySubjectIdentifier').value = this.value"/>
                </chrome:division>
            </div>
        </form:form>
    </chrome:box>
</div>
<%--STANDARD FORM --%>

<form:form  id="command">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
    <tags:tabFields tab="${tab}"/>
    <tags:tabControls tab="${tab}" flow="${flow}"/>

    <form:hidden path="searchText"/>
    <div style="display:none;">
        <form:input path="study" title="Study"/>
        <form:input path="assignment.studySubjectIdentifier" title="Study Subject Identifier"/>
    </div>
</form:form>

<%--STANDARD FORM --%>

<script>
    jQuery(document).ready(function() {
        buildTable('assembler', false);
        jQuery('#flow-prev').click(function() {
            ValidationManager.validate = false;
            Errors.clear();
        });
    });
</script>


</body>
</html>
