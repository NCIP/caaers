<!-- BEGIN views\par\reg_participant_search.jsp -->
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Search for a Subject</title>

    <script>
function submitPage(s){
	document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm").submit();
}
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function doNothing(){
}

function onAjaxSubjectSearch() {
    $('bigResult').show();
    // alert("onAjaxStudySearch");
}

function ajaxSubjectSearch(searchText, searchType) {
    // START tags:tabMethod
        var text = $F('searchText')

        if(text == '' ||text.length==1){
            $('error').innerHTML="<font color='#FF0000'>Provide at least two character in the search field</font>"
        }else{
            $('error').innerHTML=""


<tags:tabMethod
       method="searchSubjects"
       viewName="par/ajax/reg_subjectSearchResult"
       onComplete="onAjaxSubjectSearch"
       divElement="'searchResults'"
       formName="'searchForm'"
       params="" />

    // END tags:tabMethod
}
}
</script>

</head>
<body>

<chrome:box autopad="true" title="Search Criteria">
    <form:form id="searchForm" method="post" cssClass="standard" title="ABC">
        <table border="0" cellspacing="2" cellpadding="2" class="search" width="100%">
            <tr>
                <td class="searchType">Search for a subject</td>
                <td>
                    <form:select path="searchType">
                        <form:options items="${participantSearchType}" itemLabel="desc" itemValue="code" />
                    </form:select>
                </td>
                <td><form:input path="searchText" size="30" /></td>
                <c:set var="targetPage" value="${assignType == 'study' ? '_target1' : '_target0'}"/>
                <td width="100%"><input type="button" onClick="ajaxSubjectSearch();" value="Search" /></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td class="notation"><div id="error"></div>
			</td>

            </tr>
        </table>
    </form:form>
</chrome:box>

<c:set var="display" value="none" />
<c:if test="${fn:length(command.participantSearchResults) > 0}">
    <c:set var="display" value="''" />
</c:if>

<div id="bigResult" style="display:${display};">
<tags:tabForm tab="${tab}" flow="${flow}" title="Results" willSave="false">
<jsp:attribute name="singleFields">
    <div id="searchResults" style="width:100%; border: 0px red dotted;">
        <c:if test="${fn:length(command.participantSearchResults) > 0}">

        <ec:table autoIncludeParameters="false"
                  items="command.participantSearchResults"
                  var="participant"
                  action="${pageContext.request.contextPath}/pages/home"
                  imagePath="${pageContext.request.contextPath}/images/table/*.gif"
                  filterable="false"
                  showPagination="false"
                  cellspacing="0" cellpadding="0" border="0" width="100%" style="" styleClass="">

        <ec:row highlightRow="true">
            <ec:column property="kk" style="width:10px" filterable="false" sortable="false" title=" ">
                <form:radiobutton path="participant" value="${participant.id}" />
            </ec:column>
            <ec:column property="primaryIdentifier" title="Primary ID"/>
            <ec:column property="firstName" title="First Name"/>
            <ec:column property="lastName" title="Last Name" />
            <ec:column property="dateOfBirth" title="Date of Birth"/>
            <ec:column property="gender" title="Gender" />
            <ec:column property="race" title="Race" />
            <ec:column property="ethnicity" title="Ethnicity" />
        </ec:row>
        </ec:table>
    </c:if>

    </div>
</jsp:attribute>
</tags:tabForm>
</div>

</body>
</html>

<!-- END views\par\reg_participant_search.jsp -->