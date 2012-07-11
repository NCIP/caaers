<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>

    <tags:dwrJavascriptLink objects="searchStudy"/>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Search for a Study</title>
    <style>
        .yui-dt table { width: 100%; }
        #yui-dt0-th-name-liner {width: 200px;}
    </style>

    <script>
        function submitPage(s) {
                alert($('studySite').value);
            return;
            document.getElementById("command").submit();
        }
        function navRollOver(obj, state) {
            document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
        }


        function updateTargetPage(s) {
            document.checkEligibility.nextView.value = s;
            document.checkEligibility.submit();
        }

/*
        function resetSites(btn) {
            var classValue = 'siteStudy_' + btn.value;
            $$('.sitesRadioBtn').each(function(input) {
                if (input.classNames().toArray().indexOf(classValue) < 0) {
                    input.checked = false;
                }
            });
        }
*/

        function resetStudyAndSites(button) {
            $('command').studySite.value = button.value;
            if ($('ids')) $('ids').show();
        }

        function resetStudyAndSitesById(id) {
            $('command').studySite.value = id;
            if ($('ids')) $('ids').show();
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
                if (validate) jQuery('#flashErrors').hide();
                $('indicator').show();

                var parameterMap = getParameterMap(form);
                parameterMap["organizationID"] = "<c:out value="${command.participant.assignments[0].studySite.organization.id}" />";
                searchStudy.getTableForAssignParticipant(parameterMap, "", text, ajaxCallBack);
                $('indicator').hide();
                $('bigSearch').show();
            }
        }


        ValidationManager.submitPostProcess = function(formElement, flag) {
            flag = true;
            $('searchText').value = $('searchText_').value;
            if (formElement.id != 'command') {
                return true
            } else {
                $('studySubjectIdentifier').value = $('studySubjectIdentifierInput').value
            }
            return flag;
        }

        function ajaxCallBack(jsonResult) {
            $('indicator').className='indicator'
            initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
            hideCoppaSearchDisclaimer();
        }

        var linkFormatter = function(elCell, oRecord, oColumn, oData) {
                var orgId = oRecord.getData("id");
                elCell.innerHTML = "<a href='asaelEdit?agentID=" + orgId + "'>" + oData + "</a>";
        };

        var radioFormatter = function(elCell, oRecord, oColumn, oData) {

                var _ss = 0;
                var _checked = "";
                var _id = oRecord.getData("id");
            
                <c:if test="${command.studySite.id > 0}">
                    _ss =  ${command.studySite.id};
                </c:if>

                if (_ss == _id) {
                    _checked = "checked";
                }

                elCell.innerHTML = "<input " + _checked + " type=\"radio\" class=\"sitesRadioBtn siteStudy_"+ _id +"\" onclick=\"resetStudyAndSites(this);\" value=\"" + _id + "\" id=\"studySite" + _id + "\" name=\"studySite\">&nbsp;" + oData;
        };
        
        var pivRadioFormatter = function(elCell, oRecord, oColumn, oData) {
        	var _id = oRecord.getData("id");
	        var _piv = oRecord.getData("primaryId");
	        elCell.innerHTML = "<label for='studySite" + _id + "'>" + _piv + "</label>";
    	};
    
	    var shortTitleRadioFormatter = function(elCell, oRecord, oColumn, oData) {
	    	var _id = oRecord.getData("id");
	        var _st = oRecord.getData("studyShortTitle");
	        elCell.innerHTML = "<label for='studySite" + _id + "'>" + _st + "</label>";
	    };

        var actionsFormatter = function(elCell, oRecord, oColumn, oData) {
            var _id = oRecord.getData("id");
            var _ssi = oRecord.getData("fundingSponsorIdentifierValue");
            var _active = oRecord.getData("dataEntryStatus");
            elCell.innerHTML = "<img src='<c:url value="/images/orange-actions.gif" />' border='0' onmouseover=\"showDashboardStudiesMenuOptions(this, roles_map, '" + _ssi + "', " + _id + ", '" + _active + "')\" style=\"cursor: pointer; margin-right: 15px;\">";
        };

        var myColumnDefs = [
            {key:"primaryId", label:"Study ID", sortable:true, resizeable:true, formatter: pivRadioFormatter},
            {key:"studyShortTitle", label:"Title", sortable:true, resizeable:true, formatter: shortTitleRadioFormatter},
            {key:"name", label:"Study site", sortable:true, resizeable:true, formatter: radioFormatter}
            // {key:"actions", label:"&nbsp;", sortable:true, resizeable:true, formatter: actionsFormatter}
        ];

        var myFields = [
            {key:'id', parser:"string"},
            {key:'primaryId', parser:"string"},
            {key:'studyShortTitle', parser:"string"},
            {key:'status', parser:"string"},
            {key:'dataEntryStatus', parser:"string"},
            {key:'fundingSponsorIdentifierValue', parser:"string"},
            {key:'studyPhase', parser:"string"},
            {key:'nciInstituteCode', parser:"string"},
            {key:'name', parser:"string"}
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
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<par:summary subjectFullName="${command.participant.fullName}" studyShortTitle="${command.study.shortTitle}"/>

<chrome:box autopad="true" title="Search Criteria">

    <form:form id="searchForm" method="post" cssClass="standard">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
        <tags:jsErrorsMessage/>

        <div class="errors" id="flashErrors" style="display: none;">
            <span id="command_errors">Provide at least one character in the search field.</span>
        </div>

        <div class="row">
            <div class="label"></div>
            <div class="value">
                <input type="text" size="25" onkeydown="onKey(event);" value="${command.searchText}" id="searchText_">&nbsp;
                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler', true);"/>
                <img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="indicator"></td>
            </div>
        </div>

        <c:set var="targetPage" value="${assignType == 'study' ? '_target0' : '_target1'}"/>

    </form:form>
</chrome:box>


<div id="bigSearch" style="display:none;">

    <chrome:box title="Results">

    <chrome:box title="" noBackground="true">
        <form:form id="assembler">
        	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
            <div>
                <input type="hidden" name="_prop" id="prop">
                <input type="hidden" name="_value" id="value">
            </div>
            <p><tags:instructions code="instruction_subject_as2s.searchstudyresults"/></p>
            <chrome:division id="single-fields">
                <div id="tableDiv">
                    <c:out value="${assembler}" escapeXml="false"/>
                </div>
            </chrome:division>

            <div id="ids" style="display:none;">
                <br/>
                <chrome:division title="Study Subject Identifier">
                    <p><tags:instructions code="instruction_subject_enter.choosestudy.sid"/></p>
                    <label for="studySubjectIdentifierInput"><tags:requiredIndicator/>&nbsp;<b>Study subject identifier</b></label>
                    <input id="studySubjectIdentifierInput" type="text" maxlength="2000" value="${command.studySubjectIdentifier}" name="studySubjectIdentifierInput" class="${not empty command.studySubjectIdentifier ? 'valueOK' : 'required'}"/>
                </chrome:division>

            </div>
            
        </form:form>
    </chrome:box>
        
<%--STANDARD FORM --%>
            <form:form  id="command">
            	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
                <tags:tabFields tab="${tab}"/>
                <tags:tabControls tab="${tab}" flow="${flow}"/>
                <form:hidden path="studySubjectIdentifier"/>
                <form:hidden path="searchText"/>
                <form:hidden path="searchType" id="_searchType"/>
                <input type=hidden name="studySite">
            </form:form>

<%--STANDARD FORM --%>

        </chrome:box>
        </div>

<script>
    Event.observe(window, "load", function(){
        buildTable('assembler', false);
        <c:if test="${command.studySite.id > 0}">
            var _ss =  ${command.studySite.id};
            resetStudyAndSitesById(_ss);
        </c:if>
    })
</script>

<div id="please_wait" style="display: none;" class="flash-message info" >
    <h3><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_please.wait" /></h3>
    <br><br>
    <div><caaers:message code="LBL_study.in.process" /></div>
</div>
<div id="error_page" style="display: none;" class="flash-message error" ><div><caaers:message code="LBL_study.process.error" /></div><br><span id="_errorMessage">.</span></div>

</body>
</html>

