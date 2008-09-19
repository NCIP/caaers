<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Search for a Study</title>
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

	function resetSites(btn){
		var classValue= 'siteStudy_' + btn.value;
		$$('.sitesRadioBtn').each(function(input){
		   if(input.classNames().toArray().indexOf(classValue) < 0){
		   	  input.checked=false;
		   }
		});
	}
	
	function resetStudy(study_id){
		$$('.studyRadioBtn').each(function(input){
			if(input.classNames().toArray().indexOf(study_id) < 0){
				input.checked=false;
			}else{
				input.checked=true;
			}
		});
	}

    function onAjaxStudySearch() {
    }

    function ajaxStudySearch(searchText, searchType) {
        // START tags:tabMethod

    <tags:tabMethod
           method="searchStudies"
           viewName="par/ajax/reg_studySearchResult"
           onComplete="onAjaxStudySearch"
           divElement="'searchResults'"
           formName="'searchForm'"
           params="" />

        // END tags:tabMethod
    }
    
</script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->

<chrome:box autopad="true" title="Search Criteria">
    <form:form id="searchForm" method="post" cssClass="standard">

<table border="0" cellspacing="0" cellpadding="0" class="search" width="100%">
    <tr>
        <td>

        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
            </tr>
            <tr>
                <td class="searchType">Search for a Study&nbsp;&nbsp;</td>
                <td><form:select path="searchType"><form:options items="${studySearchType}" itemLabel="desc"itemValue="code" /></form:select></td>
                <td><form:input path="searchText" size="25" /></td>
                    <c:set var="targetPage" value="${assignType == 'study' ? '_target0' : '_target1'}"/>
                <td><input type="button" onclick="ajaxStudySearch();" value="Search" /></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4" class="notation">
                    <span class="labels">(<span class="red">*</span><em>Required Information</em>)</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ^ Minimum two characters for search.
                </td>
            </tr>
        </table>

        </td>
        </tr>
    </table>
        
    </form:form>
</chrome:box>

<p id="instructions">Please choose a Study and then press Continue to proceed</p>
<tags:tabForm tab="${tab}" flow="${flow}" title="Results" willSave="false">

    <jsp:attribute name="singleFields">
        <div id="searchResults" style="width:100%; border: 0px red dotted;">
            <c:if test="${fn:length(command.studies) > 0}">
                <ec:table autoIncludeParameters="false" items="command.studies" var="study"
                    action="${pageContext.request.contextPath}/pages/newParticipant"
                    imagePath="${pageContext.request.contextPath}/images/table/*.gif"
                    filterable="false"
                    showPagination="false" form="command"
                    cellspacing="0" cellpadding="0" border="0" width="100%" style=""
                    styleClass="">
                    <ec:row highlightRow="true">
                        <ec:column property="primaryIdentifier" title="Primary ID" />
                        <ec:column property="shortTitle" title="Short Title" />
                        <ec:column property="primarySponsorCode" title="Funding Sponsor" />
                        <ec:column property="phaseCode" title="Phase" />
                        <ec:column property="status" title="Status" />
                        <ec:column title="Sites" property="status">
                           <table>
                                <c:forEach items="${study.studySites}" var="site">
                                   <tr><td>
                                       <form:radiobutton cssClass="sitesRadioBtn siteStudy_${study.id}" onclick="if ($('ids')) $('ids').show();" path="studySite" value="${site.id}"/>${site.organization.name }
                                   </td></tr>
                                </c:forEach>
                           </table>
                        </ec:column>
                    </ec:row>
                </ec:table>
            </c:if>
        </div>

<div id="ids" style="display: <c:if test="${fn:length(command.studies) == 0}">none</c:if>;">

        <br />
        <%--A=<c:out value="${command.assignment == null}" />--%>
        Study Subject Identifier: <ui:text path="studySubjectIdentifier" />
</div>    
    </jsp:attribute>

</tags:tabForm>
</body>
</html>

