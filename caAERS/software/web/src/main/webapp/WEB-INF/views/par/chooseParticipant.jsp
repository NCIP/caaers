<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<link rel="stylesheet" type="text/css"
      href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Search for a Participant</title>
    <script>
        function submitPage(s) {
            document.getElementById("command").submit();
        }
        function navRollOver(obj, state) {
            document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
        }
        function doNothing() {
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

        function resetStudy(participant_id) {
            $$('.participantRadioBtn').each(function(input) {
                if (input.classNames().toArray().indexOf(participant_id) < 0) {
                    input.checked = false;
                } else {
                    input.checked = true;
                }
            });
        }
    </script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->

<chrome:box autopad="true">
    <form:form id="searchForm" method="post" cssClass="standard" commandName="command">
        <table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
            </tr>
            <tr>
                <td class="searchType">
                    Search for a Participant&nbsp;&nbsp;
                </td>
                <td><form:select path="participantType">
                    <form:options items="${participantSearchType}" itemLabel="desc" itemValue="code"/>
                </form:select></td>
                <td><form:input path="participantText" size="25"/></td>


                <td>
                    <input type="hidden" name="execution" value="${flowExecutionKey}">
                    <input type="hidden" name="_eventId" value="search">
                    <input type="submit" value="Search" id="submitSearch" align="middle" width="22"
                           height="10" border="0">

                </td>

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
    </form:form>
</chrome:box>

<p id="instructions">Please choose a Participant and then press Continue to proceed</p>
<form:form id="searchParticipant" method="post" cssClass="standard" commandName="command">
    <input type="hidden" name="execution" value="${flowExecutionKey}">
                        <input type="hidden" name="_eventId" value="confirm">
                        <input type="submit" value="Confirm" id="submitForm" align="middle" width="22"
                               height="10" border="0">


    <ec:table autoIncludeParameters="false"
              items="participants"
              var="participant"
              action="${pageContext.request.contextPath}/pages/home"
              imagePath="${pageContext.request.contextPath}/images/table/*.gif"
              filterable="false"
              showPagination="false"
              cellspacing="0" cellpadding="0" border="0" width="80%" style="" styleClass="">
        <ec:row highlightRow="true">
            <ec:column property="kk" style="width:10px" filterable="false" sortable="false" title=" ">
                <form:radiobutton path="participantId" value="${participant.id}"/>
            </ec:column>

            <ec:column property="primaryIdentifier" title="Primary ID"/>
            <ec:column property="firstName" title="First Name"/>
            <ec:column property="lastName" title="Last Name"/>
            <ec:column property="dateOfBirth" title="Date of Birth"/>
            <ec:column property="gender" title="Gender"/>
            <ec:column property="race" title="Race"/>
            <ec:column property="ethnicity" title="Ethnicity"/>

        </ec:row>
    </ec:table>

    <%--<div id="submit">--%>
        <%--<input type="hidden" name="execution" value="${flowExecutionKey}">--%>
        <%--<input type="hidden" name="_eventId" value="cancel">--%>
        <%--<input type="submit" value="Confirm This" id="confirm" align="middle" width="22"--%>
               <%--height="10" border="0">--%>
    <%--</div>--%>

    <!--<div id="submit">-->
        <!--<input type="hidden" name="execution" value="${flowExecutionKey}">-->
        <!--<input type="hidden" name="_eventId" value="confirm">-->
        <!--<input type="submit" value="Confirm" id="confirm" align="middle" width="22"-->
               <!--height="10" border="0">-->
    <!--</div>-->

  <td>

                </td>
    <%--<div id="cancel">--%>
        <%--<input type="hidden" name="execution" value="${flowExecutionKey}">--%>
        <%--<input type="hidden" name="_eventId" value="cancel">--%>
        <%--<input type="submit" value="cancel" id="cancelForm" align="middle" width="22"--%>
               <%--height="10" border="0">--%>
    <%--</div>--%>

</form:form>
</body>
</html>