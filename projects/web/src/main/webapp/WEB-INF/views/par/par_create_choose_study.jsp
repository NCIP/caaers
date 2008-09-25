<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>choose a Study</title>
<script>
function submitPage(s){
	document.getElementById("command").submit();
}
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function doNothing(){
}

function updateTargetPage(s){
		document.checkEligibility.nextView.value=s;
		document.checkEligibility.submit();
}

function onAjaxStudySearch() {
    $('bigSearch').show(); 
}

function ajaxStudySearch(searchText, searchType) {
    // START tags:tabMethod

<tags:tabMethod
       method="searchStudies"
       viewName="par/ajax/par_studySearchResult"
       onComplete="onAjaxStudySearch"
       divElement="'searchResults'"
       formName="'searchForm'"
       params="ggg" />
    
    // END tags:tabMethod
}

</script>
    <style>
        div.row div.label {
            float : left;
            font-weight: normal;
            margin-left: 0.5em;
            margin-right: 0.5em;
            text-align: right;
            width: 15em;
        }
    </style>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->

<p id="instructions">
    <b>Subject</b>&nbsp;${command.participant.fullName}
</p>

<chrome:box autopad="true" title="Search Criteria">
  	<p><tags:instructions code="instruction_subject_enter.choosestudy"/></p>

    <form:form id="searchForm" method="post">
    	<div><input type="hidden" name="_action" value="go"></div>


        <table border="0" cellspacing="0" cellpadding="0" border=1 width="100%">
        <tr>
            <td valign="top">
                    <table border="0" cellspacing="0" cellpadding="0" class="search">
                    <tr>
                        <td>
                            <tags:requiredIndicator />&nbsp;
                            <form:select path="searchType"><form:options items="${searchType}" itemLabel="desc" itemValue="code" /></form:select>
                        </td>
                        <td><form:input path="searchText" size="25" /></td>
                        <td><input type="button" value="Search" onClick="ajaxStudySearch($('searchText').value, $('searchType').value);"></td>
                    </tr>
                    </table>
            </td>
            <td valign="top">
            </td>
            </tr>
            </table>
    </form:form>
</chrome:box>

<c:set var="display" value="none" />
<c:if test="${fn:length(command.studies) > 0}">
    <c:set var="display" value="''" />
</c:if>
<div id="bigSearch" style="border:0px green dotted; display:${display};">
<tags:tabForm tab="${tab}" flow="${flow}" formName="createParticipantForm" hideErrorDetails="false" willSave="false" title="Results">
	<jsp:attribute name="instructions">
	 <c:if test="${fn:length(command.studies) gt 0}">
	 	Please choose one or more study from the below listing.
	 </c:if>
	</jsp:attribute>

    <jsp:attribute name="singleFields">

        <div id="searchResults" style="width:100%; border: 0px red dotted;">
            <c:if test="${fn:length(command.studies) > 0}">

                <%--
                    Display the previews results when the user hit BACK button either on the browser or in the page form.
                --%>
                    <ec:table items="command.studies" var="study"
                                action="${pageContext.request.contextPath}/pages/participant/create"
                                imagePath="${pageContext.request.contextPath}/images/table/*.gif"
                                filterable="false"
                                showPagination="false"
                                form="command"
                                cellspacing="0" cellpadding="2" border="0" width="100%" style=""
                                styleClass=""
                                autoIncludeParameters="false"
                                sortable="true">
                        <ec:row highlightRow="true">
                            <ec:column property="transient0" style="width:20px" filterable="false" sortable="true" title="&nbsp;">
                                <form:radiobutton path="study" value="${study.id}" onclick="if ($('ids')) $('ids').show();"/>
                            </ec:column>
                            <ec:column property="primaryIdentifier" title="Primary ID" />
                            <ec:column property="shortTitle" title="Short Title" />
                            <ec:column property="primarySponsorCode" title="Funding Sponsor" />
                            <ec:column property="phaseCode" title="Phase" />
                            <ec:column property="status" title="Status" />
                        </ec:row>
                    </ec:table>
                    <input type="hidden" name="_action" value="" />

            </c:if>
        </div>

        <div id="ids" style="display: <c:if test="${fn:length(command.studies) == 0}">none</c:if>;">
            <br />
            <chrome:division title="Study Subject Identifier">
            <p><tags:instructions code="instruction_subject_enter.choosestudy.sid"/></p>
<%--
                <c:forEach items="${fieldGroups.studySubjectIdentifier.fields}" var="field">
                    <tags:renderRow field="${field}"/> 
                </c:forEach>
--%>
                <ui:row path="${fieldGroups.studySubjectIdentifier.fields[0].propertyName}">
                    <jsp:attribute name="label"><ui:label required="true" path="${fieldGroups.studySubjectIdentifier.fields[0].propertyName}" text="${fieldGroups.studySubjectIdentifier.fields[0].displayName}"/></jsp:attribute>
                    <jsp:attribute name="value"><ui:text path="${fieldGroups.studySubjectIdentifier.fields[0].propertyName}" /></jsp:attribute>
                </ui:row>
            </chrome:division>
        </div>
        
    </jsp:attribute>
</tags:tabForm>
</div>    

</body>
</html>
