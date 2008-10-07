<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <tags:dwrJavascriptLink objects="searchStudy"/>
    <tags:includeScriptaculous/>

    <tags:javascriptLink name="extremecomponents"/>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Search for a Study</title>
    <script>
        function submitPage(s) {

            document.getElementById("command").submit();
        }
        function navRollOver(obj, state) {
            document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
        }


        function updateTargetPage(s) {
            document.checkEligibility.nextView.value = s;
            document.checkEligibility.submit();
        }

        function resetSites(btn) {
            var classValue = 'siteStudy_' + btn.value;
            $$('.sitesRadioBtn').each(function(input) {
                if (input.classNames().toArray().indexOf(classValue) < 0) {
                    input.checked = false;
                }
            });
        }

        function resetStudyAndSites(button) {
//            resetSites(button);/
            $('studySite').value = button.value;
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
                searchStudy.getTableForAssignParticipant(parameterMap, type, text, showTable)
                $('indicator').hide();

                $('bigSearch').show();
            }
        }


        ValidationManager.submitPostProcess = function(formElement, flag) {
            flag = true;
            $('searchText').value = $('searchText_').value
            if (formElement.id != 'command') {
                return true
            } else {

                if (removeSpaces($('studySubjectIdentifier').value) == '')
                {
                    $('studySubjectIdentifier').value = $('studySubjectIdentifierInput').value
                }

            }
            return flag;
        }

    </script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<p id="instructions">

<div class="instructions">
    <div class="summarylabel"><b>Subject</b></div>
    <div class="summaryvalue">${command.participant.fullName}</div>
</div>
</p>

<chrome:box autopad="true" title="Search Criteria">

    <form:form id="searchForm" method="post" cssClass="standard">
        <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
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
                                <input type="button" onClick="buildTable('assembler', true);" value="Search"/>&nbsp;
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
                    <input id="studySubjectIdentifierInput" type="text" maxlength="2000" value="" name="studySubjectIdentifierInput"/>
                </chrome:division>

            </div>
            
        </form:form>

    </chrome:box>


<%--STANDARD FORM --%>
            <form:form  id="command">
                <tags:tabFields tab="${tab}"/>
                <tags:tabControls tab="${tab}" flow="${flow}"/>
                <form:hidden path="studySite"/>
                <form:hidden path="studySubjectIdentifier"/>
                <form:hidden path="searchText"/>
            </form:form>

<%--STANDARD FORM --%>

        </chrome:box>
        </div>

<script>
    Event.observe(window, "load", function(){
        buildTable('assembler', false);
    })
</script>

</body>
</html>

