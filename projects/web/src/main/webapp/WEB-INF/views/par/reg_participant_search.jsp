<!-- BEGIN views\par\reg_participant_search.jsp -->
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Search for a Subject</title>
    <tags:includeScriptaculous/>

    <tags:javascriptLink name="extremecomponents"/>
    <tags:dwrJavascriptLink objects="createParticipant"/>

    <script type="text/javascript">

        function navRollOver(obj, state) {
            document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
        }

        
        function onKey(e) {
            var keynum = getKeyNum(e);

            if (keynum == 13) {
                Event.stop(e);
                buildTable('assembler');
            } else return;
        }

        function buildTable(form) {

            var text = $F('searchText');
            $('indicator').show();

            if (text == '') {
                $('error').innerHTML = "<font color='#FF0000'>Provide at least one character in the search field.</font>";
            } else {
                $('error').innerHTML = ""
                $('indicator').className = ''
                var type = $('searchType').options[$('searchType').selectedIndex].value;


                var parameterMap = getParameterMap(form);
                createParticipant.getParticipantTable(parameterMap, type, text, showTable)

                $('bigSearch').show();

            }
        }

        function selectParticipant(selectedParticipant){
             $('participant').value=selectedParticipant;
        }


    </script>

</head>
<body>


<tags:tabForm tab="${tab}" flow="${flow}" title="Search Criteria" willSave="false">
<jsp:attribute name="singleFields">
    <table border="0" cellspacing="2" cellpadding="2" class="search" width="100%">
        <p><tags:instructions code="instruction_subject_as2s.searchsub"/></p>
        <tr>
            <form:hidden  path="participant"/>
            <td class="searchType">Search for subject by</td>
            <td>
                <form:select path="searchType">
                    <form:options items="${participantSearchType}" itemLabel="desc" itemValue="code"/>
                </form:select>
            </td>
            <td><form:input path="searchText" size="30" onkeydown="onKey(event);"/></td>
            <c:set var="targetPage" value="${assignType == 'study' ? '_target1' : '_target0'}"/>
            <td width="100%"><input type="button" onClick="buildTable('assembler');" value="Search"/>&nbsp;
                <img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="indicator">
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td class="notation">
                <div id="error"></div>
            </td>

        </tr>

    </table>

</jsp:attribute>
</tags:tabForm>


<div id="bigSearch" style="display:none;">
    <form:form id="assembler">


        <div>
            <input type="hidden" name="_prop" id="prop">
            <input type="hidden" name="_value" id="value">
        </div>
        <chrome:box title="Results">
            <p><tags:instructions code="instruction_subject_as2s.searchsubresults"/></p>
            <chrome:division id="single-fields">
                <div id="tableDiv">
                    <c:out value="${assembler}" escapeXml="false"/>
                </div>
            </chrome:division>
        </chrome:box>
    </form:form>
</div>

</body>
</html>

<!-- END views\par\reg_participant_search.jsp -->