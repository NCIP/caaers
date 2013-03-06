<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>

    <tags:dwrJavascriptLink objects="searchStudy"/>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Search for a Study</title>
    <style>
        .yui-pg-page { padding: 5pt; }
        .yui-dt-label .yui-dt-sortable { color: white; }
        .yui-dt table { width: 100%; }
        div.yui-dt-liner a {color : black;}

        tr.yui-dt-even { background-color: #FFF; border-bottom: 1px gray solid;}
        tr.yui-dt-odd { background-color: #EDF5FF; border-bottom: 2px blue dotted; padding: 2px; }

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
            // START tags:tabMethod

            var text = $F('searchText_');

            if (text == '') {
                if (validate) $('error').innerHTML = "<font color='#FF0000'>Provide at least one character in the search field.</font>"
            } else {
                //$('error').innerHTML = ""
                var type = $('searchType').options[$('searchType').selectedIndex].value;
                $('indicator').show();

                var parameterMap = getParameterMap(form);
                parameterMap["organizationID"] = "<c:out value="${command.participant.assignments[0].studySite.organization.id}" />";
                searchStudy.getTableForAssignParticipant(parameterMap, type, text, ajaxCallBack);
                $('indicator').hide();
                $('bigSearch').show();
            }
        }


        ValidationManager.submitPostProcess = function(formElement, flag) {
            flag = true;
            $('searchText').value = $('searchText_').value;
            $('_searchType').value = $('searchType').value;
            // $('_studySite').value = $('studySite').value;
            if (formElement.id != 'command') {
                return true
            } else {

//                if (removeSpaces($('studySubjectIdentifier').value) == '')
//                {
                    $('studySubjectIdentifier').value = $('studySubjectIdentifierInput').value
//                }

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

                elCell.innerHTML = "<input " + _checked + " type=\"radio\" class=\"sitesRadioBtn siteStudy_"+ _id +"\" onclick=\"resetStudyAndSites(this);\" value=\"" + _id + "\" id=\"studySite" + _id + "\" name=\"studySite\">" + oData;
        };

        var myColumnDefs = [
            {key:"primaryId", label:"Study ID", sortable:true, resizeable:true},
            {key:"studyShortTitle", label:"Short Title", sortable:true, resizeable:true},
            {key:"status", label:"Status", sortable:true, resizeable:true},
            {key:"studyPhase", label:"Phase", sortable:true, resizeable:true},
            {key:"nciInstituteCode", label:"Funding Sponsor", sortable:true, resizeable:true},
            {key:"name", label:"Study Site", sortable:true, resizeable:true, formatter: radioFormatter}
        ];

        var myFields = [
            {key:'id', parser:"string"},
            {key:'primaryId', parser:"string"},
            {key:'studyShortTitle', parser:"string"},
            {key:'status', parser:"string"},
            {key:'studyPhase', parser:"string"},
            {key:'nciInstituteCode', parser:"string"},
            {key:'name', parser:"string"}
        ];
        
    </script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<p id="instructions">

<div class="row">
    <div class="summarylabel"><b>Subject</b></div>
    <div class="summaryvalue">${command.participant.fullName}</div>
</div>
</p>

<chrome:box autopad="true" title="Search Criteria">

    <form:form id="searchForm" method="post" cssClass="standard">
        <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
        <tags:jsErrorsMessage/>
        
        <p><tags:instructions code="instruction_subject_as2s.searchstudy"/></p>
        <table border="0" cellspacing="0" cellpadding="0" class="search" width="100%">
            <tr>
                <td>

                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr></tr>
                        <tr>
                            <td class="searchType">Search for a study&nbsp;&nbsp;</td>
                            <td><form:select path="searchType"><form:options items="${studySearchType}" itemLabel="desc" itemValue="code"/></form:select></td>
                            <td>
                                <input type="text" size="25" onkeydown="onKey(event);" value="${command.searchText}" id="searchText_">
                                <%--<form:input path="searchText" size="25" onkeydown="onKey(event);" />&nbsp;--%>
                                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler', true);"/>

                                <img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="indicator"></td>
                                <c:set var="targetPage" value="${assignType == 'study' ? '_target0' : '_target1'}"/>
                        </tr>
                        <tr>
                            <td></td>
                            <td class="notation" colspan="2">
                                <div id="error"></div>
                            </td>

                    </table>

                </td>
            </tr>
        </table>

    </form:form>
</chrome:box>


<div id="bigSearch" style="display:none;">

    <chrome:box title="Results">

    <chrome:box title="" noBackground="true">
        <form:form id="assembler">
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
                    <label for="studySubjectIdentifierInput"><tags:requiredIndicator/>&nbsp;Study subject identifier</label>
                    <input id="studySubjectIdentifierInput" type="text" maxlength="2000" value="${command.studySubjectIdentifier}" name="studySubjectIdentifierInput" class="${not empty command.studySubjectIdentifier ? 'valueOK' : 'required'}"/>
                </chrome:division>

            </div>
            
        </form:form>
    </chrome:box>
        
<%--STANDARD FORM --%>
            <form:form  id="command">
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

</body>
</html>

