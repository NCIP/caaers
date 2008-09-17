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
    // alert("onAjaxStudySearch");
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

<chrome:box autopad="true" title="Search Criteria">
  	<p class="instructions">Choose the search criteria, type in a minimum of two characters and then click <b>Search</b></p>

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
                                action="${pageContext.request.contextPath}/pages/newParticipant"
                                imagePath="${pageContext.request.contextPath}/images/table/*.gif"
                                filterable="false"
                                showPagination="false" form="command"
                                cellspacing="0" cellpadding="2" border="0" width="100%" style=""
                                styleClass=""
                                autoIncludeParameters="false" >
                        <ec:row highlightRow="true">
                            <ec:column property="transient0" style="width:20px" filterable="false" sortable="false" title="&nbsp;">
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
                <c:forEach items="${fieldGroups.studySubjectIdentifier.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
            </chrome:division>
        </div>
        
    </jsp:attribute>
</tags:tabForm>

</body>
</html>
